package io.wangxiao.edu.app.controller.edu;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.commons.service.gain.GuidGeneratorService;
import io.wangxiao.commons.util.ObjectUtils;
import io.wangxiao.commons.util.StringUtils;
import io.wangxiao.edu.app.common.AppBaseController;
import io.wangxiao.edu.home.constants.enums.*;
import io.wangxiao.edu.home.constants.web.OrderConstans;
import io.wangxiao.edu.home.entity.course.CourseDto;
import io.wangxiao.edu.home.entity.course.QueryCourse;
import io.wangxiao.edu.home.entity.order.QueryTrxorder;
import io.wangxiao.edu.home.entity.order.TrxOrderDTO;
import io.wangxiao.edu.home.entity.order.Trxorder;
import io.wangxiao.edu.home.entity.user.UserAccount;
import io.wangxiao.edu.home.entity.user.UserExpandDto;
import io.wangxiao.edu.home.service.course.CourseService;
import io.wangxiao.edu.home.service.order.TrxHessianService;
import io.wangxiao.edu.home.service.order.TrxorderService;
import io.wangxiao.edu.home.service.user.UserAccountService;
import io.wangxiao.edu.home.service.user.UserService;
import io.wangxiao.edu.home.service.website.WebsiteProfileService;
import lombok.Getter;
import lombok.Setter;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/app")
public class AppOrderController extends AppBaseController {
    private static Logger logger = Logger.getLogger(AppOrderController.class);

    // 绑定变量名字和属性，参数封装进类
    @InitBinder("queryTrxorder")
    public void initBinderQueryTrxorder(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("queryTrxorder.");
    }

    @Autowired
    private UserService userService;
    @Autowired
    private CourseService courseService;
    @Setter
    @Getter
    private QueryCourse queryCourse;
    @Autowired
    private TrxorderService trxorderService;
    @Autowired
    private UserAccountService userAccountService;
    @Autowired
    private TrxHessianService trxHessianService;
    @Autowired
    private WebsiteProfileService websiteProfileService;
    @Autowired
    GuidGeneratorService guidGeneratorService;

    /**
     * 创建订单
     *
     * @param courseId 课程ID串，如果同时购买多个课程传入 12,12,15
     * @param userId   用户ID
     * @return Map<String,Object>
     */
    @RequestMapping("/create/pay/order")
    @ResponseBody
    public Map<String, Object> createOrder(HttpServletRequest request, @RequestParam("courseId") Long courseId,
                                           @RequestParam("userId") Long userId) {
        Map<String, Object> json = null;
        try {
            UserExpandDto user = userService.queryUserExpand(userId);
            if (user == null) {
                json = this.getJsonMap(false, "请登录手机应用", null);
                return json;
            }
            Map<String, Object> resultMap = new HashMap<String, Object>();
            /*
			 * ALIPAY,//支付宝 KUAIQIAN,//快钱 WEIXIN,//微信 CARD,// 课程卡(课程激活)
			 * INTEGRAL,//积分兑换 FREE,//免费赠送,后台赠送 ACCOUNT,//账户余额完全支付,本次订单未充值
			 * YEEPAY //易宝支付
			 */
            String payType = request.getParameter("payType");
            // 拼装参数
            Map<String, String> sourceMap = new HashMap<String, String>();
            sourceMap.put("type", "1");// 下单类型
            sourceMap.put("userid", String.valueOf(userId));
            sourceMap.put("reqchanle", ReqChanle.APP.toString());
            sourceMap.put("payType", payType);// 支付类型
            sourceMap.put("courseId", courseId + "");
            Map<String, Object> res = trxorderService.appAddTrxorder(sourceMap);
            // 课程已经过期
            if (res.get("msg") != null && res.get("msg").toString().equals("expire")) {// 如果有课程已经过期
                json = this.getJsonMap(false, res.get("message").toString(), null);
                return json;
            }
            // 没有课程
            if (res.get("msg") != null && res.get("msg").toString().equals("notCoures")) {
                json = this.getJsonMap(false, res.get("message").toString(), null);
                return json;
            }
            // 参数传入错误
            if (res.get("msg") != null && res.get("msg").toString().equals("param")) {
                json = this.getJsonMap(false, res.get("message").toString(), null);
                return json;
            }
            UserAccount userAccount = userAccountService.getUserAccountByUserId(userId);
            resultMap.put("balance", userAccount.getBalance().toString());// 用户帐号余额
            Trxorder trxorder = (Trxorder) res.get("order");
            if (userAccount == null) {
                json = this.getJsonMap(false, "您的账户已冻结！", null);
                return json;
            }
            if (userAccount.getBalance().compareTo(trxorder.getAmount()) < 0) {
                // 还需支付的金额
                resultMap.put("bankAmount", trxorder.getAmount().subtract(userAccount.getBalance()).toString());
            } else {
                // 还需支付的金额
                resultMap.put("bankAmount", "0.00");
            }
            resultMap.put("orderId", trxorder.getId());// 订单ID
            resultMap.put("orderNo", trxorder.getRequestId());// 订单号
            resultMap.put("payType", trxorder.getPayType());// 订单支付类型
            json = this.getJsonMap(true, "下单成功", resultMap);
        } catch (Exception e) {
            json = this.getJsonMap(false, "系统繁忙，请稍后再操作", null);
            logger.error("createOrder()---error", e);
        }
        return json;
    }

    /**
     * App支付前检测接口，如果用户账号的余额足够支付，就用金额支付
     *
     * @param orderId 订单ID
     * @param userId  用户ID
     * @param payType 支付类
     * @return
     */
    @RequestMapping("/order/payment")
    @ResponseBody
    public Map<String, Object> coursePayment(@RequestParam("orderId") Long orderId, @RequestParam("userId") Long userId,
                                             @RequestParam("payType") String payType) {
        Map<String, Object> json = null;
        try {
            // 查询订单
            Trxorder trxorder = trxorderService.getTrxorderById(orderId);
            if (trxorder == null) {
                json = this.getJsonMap(false, "订单不存在", null);
                return json;
            }
            if (trxorder.getUserId().intValue() != userId.intValue()) {
                json = this.getJsonMap(false, "订单不属于该用户的", null);
                return json;
            }
            if (ObjectUtils.isNotNull(trxorder)) {
                if (payType == null || !payType.equals(PayType.IOS.toString())) {// 非苹果内购支付
                    // 先查询账户的金额是否足够支付本次订单的，如果购，直接走扣账流程
                    Map<String, String> sourceMap = new HashMap<String, String>();
                    sourceMap.put("total_fee", "0.00");// 充值金额，先设置为0.尝试账户余额直接支付
                    sourceMap.put("requestId", trxorder.getRequestId());
                    sourceMap.put("userId", String.valueOf(userId));
                    sourceMap.put("payType", PayType.ACCOUNT.toString());
                    Map<String, String> res = trxHessianService.noTrxTrxOrderComplete(sourceMap);
                    System.out.println("订单返回信息:" + res.get(OrderConstans.RESCODE));
                    // 余额支付成功,直接返回支付成功页面
                    if (res.get(OrderConstans.RESCODE).equals(OrderConstans.SUCCESS)) {
                        json = this.getJsonMap(true, "订单支付成功", null);
                        return json;
                    } else if ("balance".equals(res.get(OrderConstans.RESCODE))) {// 余额不足，跳转到银行页面
                        Map<String, Object> resultMap = new HashMap<String, Object>();
                        UserAccount userAccount = userAccountService.getUserAccountByUserId(userId);
                        // 实际要支付的金额
                        resultMap.put("bankAmount", trxorder.getAmount().subtract(userAccount.getBalance()).toString());
                        resultMap.put("orderId", trxorder.getId());// 订单ID
                        resultMap.put("balance", userAccount.getBalance());// 账户余额
                        resultMap.put("orderNo", trxorder.getRequestId());// 订单号
                        String out_trade_no = guidGeneratorService.gainCode("PAY", true);// 商户网站订单（也就是外部订单号，是通过客户网站传给支付宝，不可以重复）
                        resultMap.put("out_trade_no", out_trade_no);
                        json = this.getJsonMap(false, "用户账户余额不足！", resultMap);
                    } else {// 优惠券错误信息
                        json = this.getJsonMap(false, res.get(OrderConstans.RESCODE), "2");
                        return json;
                    }
                } else {
                    Map<String, Object> resultMap = new HashMap<String, Object>();
                    // 实际要支付的金额
                    resultMap.put("bankAmount", trxorder.getAmount());
                    resultMap.put("orderId", trxorder.getId());// 订单ID
                    resultMap.put("orderNo", trxorder.getRequestId());// 订单号
                    resultMap.put("payType", trxorder.getPayType());// 订单支付类型
                    String out_trade_no = guidGeneratorService.gainCode("PAY", true);
                    // 商户网站订单（也就是外部订单号，是通过客户网站传给支付宝，不可以重复）
                    resultMap.put("outTradeNo", out_trade_no);
                    json = this.getJsonMap(true, "苹果内购支付", resultMap);
                }
            }
        } catch (Exception e) {
            json = this.getJsonMap(false, "系统繁忙，请稍后再操作！", "2");
            logger.error("coursePayment()--error", e);
        }
        return json;
    }

    /**
     * 支付回调接口
     *
     * @param userId     用户ID
     * @param totalFee   支付的总金额
     * @param orderNo    订单号
     * @param outTradeNo 外部订单号（系统生成并传给支付宝的）
     * @return Map<String,Object>
     */
    @RequestMapping("/order/paysuccess")
    @ResponseBody
    public Map<String, Object> alipayNotify(@RequestParam("userId") Long userId,
                                            @RequestParam("totalFee") String totalFee, @RequestParam("orderNo") String orderNo,
                                            @RequestParam("outTradeNo") String outTradeNo, @RequestParam("payType") String payType) {
        Map<String, Object> json = null;
        try {
            System.out.println("/order/paysuccess:userId=" + userId + ";totalFee=" + totalFee + ";orderNo=" + orderNo
                    + ";outTradeNo=" + outTradeNo + ";payType" + payType);
            Map<String, String> sourceMap = new HashMap<String, String>();
            sourceMap.put("total_fee", totalFee);
            sourceMap.put("out_trade_no", outTradeNo);
            sourceMap.put("userId", String.valueOf(userId));
            sourceMap.put("requestId", orderNo);
            sourceMap.put("payType", payType);
            Trxorder trxorder = trxorderService.getTrxorderByRequestId(orderNo);
            if (trxorder.getTrxStatus().equals(TrxOrderStatus.SUCCESS.toString())) {
                json = this.getJsonMap(true, "订单支付成功", null);
                return json;
            }
            // 必须校验支付的金额 TODO
            Map<String, String> res = trxHessianService.noTrxTrxOrderComplete(sourceMap);
            if (ObjectUtils.isNotNull(res)) {
                if (StringUtils.isNotEmpty(res.get(OrderConstans.BANKCODE))
                        && res.get(OrderConstans.BANKCODE).equalsIgnoreCase(OrderConstans.SUCCESS)) {
                    json = this.getJsonMap(true, res.get(OrderConstans.RESMSG), null);
                } else {
                    json = this.getJsonMap(false, "支付失败", null);
                }
            } else {
                json = this.getJsonMap(false, "交易数据出错", null);
            }
        } catch (Exception e) {
            json = this.getJsonMap(false, "系统繁忙，请稍后再操作", null);
            logger.error("alipayNotify()---error", e);
        }
        return json;
    }

    /**
     * 我的订单
     *
     * @param page
     * @param queryTrxorder
     * @return
     */
    @RequestMapping("/order/list")
    @ResponseBody
    public Map<String, Object> orderList(@ModelAttribute("page") PageEntity page, QueryTrxorder queryTrxorder) {
        Map<String, Object> json = null;
        try {
            List<TrxOrderDTO> orderList = trxorderService.queryOrderForWebUc(queryTrxorder, page);
            // 订单信息
            Map<String, Object> dataMap = new HashMap<>();
            dataMap.put("orderList", orderList);
            dataMap.put("page", page);
            json = this.getJsonMap(true, "查询成功", dataMap);
        } catch (Exception e) {
            logger.error("AppOrderController.orderList", e);
            json = this.getJsonMap(false, "系统繁忙", null);
        }
        return json;
    }

    /***
     * 查询用户购买过的课程列表
     *
     * @return
     */
    @RequestMapping("/buy/courses")
    @ResponseBody
    public Map<String, Object> queryUserBuyCourse(@RequestParam("userId") Long userId, HttpServletRequest request) {
        Map<String, Object> json = null;
        try {
            // 查询购买过的课程
            List<CourseDto> courseDtos = courseService.getUserBuyCourseList(getLoginUserId(request), SellType.COURSE.toString());
            List<Map<String, Object>> courseList = new ArrayList<Map<String, Object>>();
            if (courseDtos != null && courseDtos.size() > 0) {
                for (CourseDto cd : courseDtos) {
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("name", cd.getName());
                    map.put("lessionnum", cd.getLessionnum());
                    map.put("teacherList", cd.getTeacherList());
                    map.put("title", cd.getTitle());
                    map.put("courseId", cd.getId());
                    courseList.add(map);
                }
            }
            json = this.getJsonMap(true, "查询成功", courseList);
        } catch (Exception e) {
            json = this.getJsonMap(false, "系统繁忙", null);
            logger.error("queryUserBuyCourse()---error", e);
        }
        return json;
    }

    /**
     * 获取支付宝 密钥
     *
     * @return
     */
    @RequestMapping("/alipay/info")
    @ResponseBody
    public Map<String, Object> getAlipayInfo() {
        Map<String, Object> json = null;
        try {
            // 获得支付配置
            Map<String, Object> map = websiteProfileService
                    .getWebsiteProfileByType(WebSiteProfileType.alipay.toString());
            Gson gson = new Gson();
            // 获得详细info
            @SuppressWarnings("serial")
            Map<String, String> websitemap = gson.fromJson(gson.toJson(map.get(WebSiteProfileType.alipay.toString())),
                    new TypeToken<Map<String, String>>() {
                    }.getType());
            json = this.getJsonMap(true, "查询成功", websitemap);
        } catch (Exception e) {
            json = this.getJsonMap(false, "系统繁忙", null);
            logger.error("getAlipayInfo()---error", e);
        }
        return json;
    }

    /**
     * 获取微信 密钥
     *
     * @return
     */
    @RequestMapping("/weixin/info")
    @ResponseBody
    public Map<String, Object> getWeixinInfo() {
        Map<String, Object> json = null;
        try {
            // 获得支付配置
            Map<String, Object> map = websiteProfileService
                    .getWebsiteProfileByType(WebSiteProfileType.weixin.toString());
            Gson gson = new Gson();
            // 获得详细info
            @SuppressWarnings("serial")
            Map<String, String> websitemap = gson.fromJson(gson.toJson(map.get(WebSiteProfileType.weixin.toString())),
                    new TypeToken<Map<String, String>>() {
                    }.getType());
            Map<String, String> weixinmap = new HashMap<>();
            weixinmap.put("mobileAppId", websitemap.get("mobileAppId"));
            weixinmap.put("mobileMchId", websitemap.get("mobileMchId"));
            weixinmap.put("mobilePayKey", websitemap.get("mobilePayKey"));
            json = this.getJsonMap(true, "查询成功", weixinmap);
        } catch (Exception e) {
            json = this.getJsonMap(false, "系统繁忙", null);
            logger.error("getWeixinInfo()---error", e);
        }
        return json;
    }

}
