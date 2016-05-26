package io.wangxiao.edu.app.controller.edu;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.commons.service.cache.CacheKit;
import io.wangxiao.commons.service.gain.GuidGeneratorService;
import io.wangxiao.commons.util.ObjectUtils;
import io.wangxiao.commons.util.PreventInfusion;
import io.wangxiao.commons.util.Security.PurseSecurityUtils;
import io.wangxiao.commons.util.StringUtils;
import io.wangxiao.commons.util.web.WebUtils;
import io.wangxiao.edu.app.common.AppBaseController;
import io.wangxiao.edu.app.entity.app.AppUpdate;
import io.wangxiao.edu.app.entity.website.AppWebsiteImages;
import io.wangxiao.edu.app.service.edu.AppUpdateService;
import io.wangxiao.edu.app.service.edu.EduAppService;
import io.wangxiao.edu.app.service.website.AppTopicService;
import io.wangxiao.edu.app.service.website.AppWebsiteImagesService;
import io.wangxiao.edu.common.constants.MemConstans;
import io.wangxiao.edu.common.util.DESCoder;
import io.wangxiao.edu.home.constants.enums.*;
import io.wangxiao.edu.home.constants.web.OrderConstans;
import io.wangxiao.edu.home.constants.web.WebContants;
import io.wangxiao.edu.home.entity.course.*;
import io.wangxiao.edu.home.entity.member.MemberType;
import io.wangxiao.edu.home.entity.order.Trxorder;
import io.wangxiao.edu.home.entity.user.*;
import io.wangxiao.edu.home.service.article.ArticleService;
import io.wangxiao.edu.home.service.course.*;
import io.wangxiao.edu.home.service.member.MemberRecordService;
import io.wangxiao.edu.home.service.member.MemberTypeService;
import io.wangxiao.edu.home.service.order.TrxHessianService;
import io.wangxiao.edu.home.service.order.TrxorderService;
import io.wangxiao.edu.home.service.user.*;
import io.wangxiao.edu.home.service.website.WebsiteProfileService;
import io.wangxiao.edu.sysuser.entity.QuerySubject;
import io.wangxiao.edu.sysuser.entity.Subject;
import io.wangxiao.edu.sysuser.service.SubjectService;
import lombok.Getter;
import lombok.Setter;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
@RequestMapping("/webapp")
public class AppController extends AppBaseController {
    private static Logger logger = Logger.getLogger(AppController.class);

    @Autowired
    private EduAppService eduAppService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserLoginLogService userLoginLogService;
    @Autowired
    private UserIntegralService userIntegralService;
    @Autowired
    private SubjectService subjectService;
    @Autowired
    private CourseService courseService;
    @Setter
    @Getter
    private QueryCourse queryCourse;
    @Autowired
    private TrxorderService trxorderService;
    @Autowired
    private MemberRecordService memberRecordService;
    @Autowired
    private CourseKpointService courseKpointService;
    @Autowired
    private CourseProfileService courseProfileService;
    @Autowired
    private ArticleService articleService;
    @Autowired
    private AppWebsiteImagesService appWebsiteImagesService;
    @Autowired
    private CourseFavoritesService courseFavoritesService;
    @Autowired
    private CourseSubjectService courseSubjectService;
    @Autowired
    private CourseTeacherService courseTeacherService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private AppTopicService appTopicService;
    @Autowired
    private UserAccountService userAccountService;
    @Autowired
    private WebsiteProfileService websiteProfileService;
    @Autowired
    private TrxHessianService trxHessianService;
    @Autowired
    GuidGeneratorService guidGeneratorService;
    @Autowired
    private CourseStudyhistoryService courseStudyhistoryService;
    @Autowired
    private AppUpdateService appUpdateService;
    @Autowired
    private MemberTypeService memberTypeService;
    CacheKit cacheKit = CacheKit.getInstance();
    @Autowired
    private UserExpandService userExpandService;

    /**
     * 删除播放记录 传入播放记录ID（ids） ,多个可用“,”隔开
     *
     * @param request
     * @return
     */
    @RequestMapping("/deleteplayrecord")
    @ResponseBody
    public Map<String, Object> deletePlayRecord(HttpServletRequest request) {
        Map<String, Object> json = null;
        try {
            String ids = request.getParameter("ids");
            if (ids != null && ids.trim().length() > 0) {
                if (ids.trim().endsWith(",")) {
                    ids = ids.trim().substring(0, ids.trim().length() - 1);
                }
                eduAppService.deletePlayRecord(ids);
            } else {
                json = this.getJsonMap(false, "请选择要删除的数据!", null);
            }
        } catch (Exception e) {
            json = this.getJsonMap(false, "系统繁忙，请稍后再操作!", null);
            logger.error("deletePlayRecord()--error", e);
        }
        return json;
    }

    /**
     * 删除收藏 传入收藏ID（favIds），可多个 ，用“,”隔开
     *
     * @param request
     * @return Map<String,Object>
     */
    @RequestMapping("/deletehouse")
    @ResponseBody
    public Map<String, Object> deleteHouse(HttpServletRequest request) {
        Map<String, Object> json = null;
        try {
            String favIds = request.getParameter("favIds");
            if (favIds != null && favIds.trim().length() > 0) {
                if (favIds.trim().endsWith(",")) {
                    favIds = favIds.trim().substring(0, favIds.trim().length() - 1);
                }
                courseFavoritesService.deleteCourseFavoritesById(favIds);
                json = this.getJsonMap(true, "删除成功", null);
            } else {
                json = this.getJsonMap(false, "请选择要删除的收藏！", null);
            }
        } catch (Exception e) {
            json = this.getJsonMap(false, "系统繁忙，请稍后再操作！", null);
            logger.error("deleteHouse()--error", e);
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
    public Map<String, Object> alipayNotify(@RequestParam(value = "userId", required = true) long userId,
                                            @RequestParam(value = "totalFee", required = true) String totalFee,
                                            @RequestParam(value = "orderNo", required = true) String orderNo,
                                            @RequestParam(value = "outTradeNo", required = true) String outTradeNo,
                                            @RequestParam(value = "payType", required = true) String payType) {
        Map<String, Object> json = null;
        try {
//            System.out.println("/order/paysuccess:userId=" + userId + ";totalFee=" + totalFee + ";orderNo=" + orderNo
//                    + ";outTradeNo=" + outTradeNo + ";payType" + payType);
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
     * App支付前检测接口，如果用户账号的余额足够支付，就用金额支付
     *
     * @param orderId 订单ID
     * @param userId  用户ID
     * @param payType 支付类
     * @return
     */
    @RequestMapping("/order/payment")
    @ResponseBody
    public Map<String, Object> coursePayment(@RequestParam(value = "orderId", required = true) long orderId,
                                             @RequestParam(value = "userId", required = true) long userId,
                                             @RequestParam(value = "payType", required = true) String payType) {
        Map<String, Object> json = null;
        try {
            // 查询订单
            Trxorder trxorder = trxorderService.getTrxorderById(orderId);
            if (trxorder == null) {
                json = this.getJsonMap(false, "订单不存在", "2");
                return json;
            }
            if (trxorder.getUserId() != userId) {
                json = this.getJsonMap(false, "订单不属于该用户的", "2");
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
                        resultMap.put("payType", trxorder.getPayType());// 订单支付类型
                        String out_trade_no = guidGeneratorService.gainCode("PAY", true);
                        // 商户网站订单（也就是外部订单号，是通过客户网站传给支付宝，不可以重复）
                        resultMap.put("outTradeNo", out_trade_no);
                        Map<String, String> keyMap = new HashMap<String, String>();
                        keyMap.put("partnerId", "");// 合作者ID
                        keyMap.put("seller", "");// 收款账号
                        keyMap.put("private", "");// 商户密钥
                        keyMap.put("public", "");// 支付宝公钥
                        resultMap.put("keys", keyMap);
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
     * 创建订单
     *
     * @param courseId 课程ID串，如果同时购买多个课程传入 12,12,15
     * @param userId   用户ID
     * @return Map<String,Object>
     */
    @SuppressWarnings("unused")
    @RequestMapping("/create/pay/order")
    @ResponseBody
    public Map<String, Object> createOrder(HttpServletRequest request,
                                           @RequestParam(value = "courseId", required = true) String courseId,
                                           @RequestParam(value = "userId", required = true) long userId) {
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
            sourceMap.put("courseId", courseId);
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
            // 微信支付生成支付url
            if (request.getParameter("payType").toUpperCase().equals(PayType.WEIXIN.toString())) {
                String wxPayUrl = getWxpayUrl(request, trxorder.getRequestId());
                resultMap.put("wxPayUrl", wxPayUrl);
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
     * 微信支付请求
     *
     * @param request
     * @return
     */
    public String getWxpayUrl(HttpServletRequest request, String requestId) {

        try {
            logger.info("+++getWxpayUrl requestId:" + requestId);
            Map<String, String> websitemap = getWxpayInfo();// 获得微信支付配置
            String timeStanmp = DESCoder.getTimeStamp();// timestamp 时间点
            String nonceStr = DESCoder.getNonceStr();// noncestr 随机字符串
            SortedMap<String, String> signParams = new TreeMap<String, String>();
            signParams.put("appid", websitemap.get("appid"));
            signParams.put("appkey", websitemap.get("appkey"));
            signParams.put("timestamp", timeStanmp);
            signParams.put("noncestr", nonceStr);
            signParams.put("productid", requestId);// 对外支付订单号

            String sign = DESCoder.createSHA1Sign(signParams);
            String params = "sign=" + sign + "&appid=" + websitemap.get("appid") + "&productid=" + requestId
                    + "&timestamp=" + timeStanmp + "&noncestr=" + nonceStr;
            return "weixin://wxpay/bizpayurl?" + params;
        } catch (Exception e) {
            this.setExceptionRequest(request, e);
            return "";
        }

    }

    /**
     * 获取微信 密钥
     *
     * @return
     */
    public Map<String, String> getWxpayInfo() {
        // 获得微信支付配置
        Map<String, Object> weixinMap = websiteProfileService
                .getWebsiteProfileByType(WebSiteProfileType.weixin.toString());
        // 获得详细info
        @SuppressWarnings("unchecked")
        Map<String, Object> map = (Map<String, Object>) weixinMap.get(WebSiteProfileType.weixin.toString());
        if (map != null) {
            Map<String, String> websitemap = new HashMap<String, String>();
            websitemap.put("appid", map.get("wxAppID").toString());// appId
            websitemap.put("appkey", map.get("wxPaySignKey").toString());// paySignKey
            // 商户key
            websitemap.put("partner", map.get("wxPartner").toString());// partner
            // 商户号
            websitemap.put("partnerkey", map.get("wxPartnerkey").toString());// partnerkey
            // 商户号key
            return websitemap;
        }
        return null;
    }

    /**
     * 获取话题列表
     *
     * @return
     */
    @RequestMapping("/queryTopicList")
    @ResponseBody
    public Map<String, Object> queryTopicList(HttpServletRequest request, @ModelAttribute("page") PageEntity page) {
        Map<String, Object> json = null;
        try {
            int currentPage = request.getParameter("currentPage") == null ? 1
                    : Integer.parseInt(request.getParameter("currentPage"));
            page.setCurrentPage(currentPage);
            page.setPageSize(10);
            Map<String, Object> map = new HashMap<String, Object>();

            List<Map<String, Object>> list = appTopicService.queryAppTopicPage(map, page);
            Map<String, Object> resultMap = new TreeMap<String, Object>();
            resultMap.put("topicList", list);
            resultMap.put("page", page);
            json = this.getJsonMap(true, "查询成功", resultMap);
        } catch (Exception e) {
            json = this.getJsonMap(false, "系统繁忙，请稍后再操作", null);
            logger.error("queryTopicList()--error", e);
        }
        return json;
    }

    /***
     * 统计用户使用记录接口
     *
     * @param request
     * @return
     */
    @RequestMapping("/countuserapply")
    @ResponseBody
    public Map<String, Object> countUserApply(HttpServletRequest request) {
        Map<String, Object> json = null;
        try {
            String ip = getRemoteHost(request);
            long userId = Long.parseLong(request.getParameter("userId"));// 用户ID
            String size = request.getParameter("size");// 手机尺寸
            String type = request.getParameter("type");// 手机型号
            String brand = request.getParameter("brand");// 手机品牌
            String beginFalg = request.getParameter("beginFalg");// 1 开始使用的时候调用
            // 2 结束的时候调用
            Map<String, Object> websiteUse = new HashMap<String, Object>();
            websiteUse.put("size", size);
            websiteUse.put("type", type);
            websiteUse.put("brand", brand);
            websiteUse.put("ip", ip);
            websiteUse.put("userId", userId);
            if (beginFalg.equals("1")) {
                websiteUse.put("createTime", new Date());
                websiteUse.put("beginTime", new Date());
                eduAppService.insertWebsiteUse(websiteUse);
            } else {
                websiteUse.put("endTime", new Date());
                eduAppService.updateWebsiteUseForEndtime(websiteUse);
            }
            json = this.getJsonMap(true, "操作成功", null);
        } catch (Exception e) {
            json = this.getJsonMap(false, "系统繁忙", null);
            logger.error("countUserApply()---error", e);
        }
        return json;
    }

    /***
     * 查询用户购买过的课程列表
     *
     * @param request
     * @return
     */
    @RequestMapping("/querycusbuycourselist")
    @ResponseBody
    public Map<String, Object> queryUserBuyCourse(HttpServletRequest request) {
        Map<String, Object> json = null;
        try {
            long userId = Long.parseLong(request.getParameter("userId"));
            // 查询购买过的课程
            List<CourseDto> courseDtos = courseService.getUserBuyCourseList(userId, SellType.COURSE.toString());
            List<Map<String, Object>> courseList = new ArrayList<Map<String, Object>>();
            if (courseDtos != null && courseDtos.size() > 0) {
                for (CourseDto cd : courseDtos) {
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("name", cd.getName());
                    map.put("logo", cd.getLogo());
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
     * app点击学习记录进行播放接口
     *
     * @param request
     * @return
     */
    @RequestMapping("/playkpoint")
    @ResponseBody
    public Map<String, Object> playKpoint(HttpServletRequest request) {
        Map<String, Object> json = null;
        try {
            long courseId = Long.parseLong(request.getParameter("courseId"));
            long userId = Long.parseLong(request.getParameter("userId"));
            long kpointId = Long.parseLong(request.getParameter("kpointId"));
            Map<String, Object> course = eduAppService.getCourseById(courseId);
            // 判断该课程是否可以观看
            boolean isok = false;
            // 判断是否购买课程且未到期
            boolean isok1 = trxorderService.checkCourseLook(courseId, userId);
            boolean isok2 = false;
            // 如果未购买或已过期，再判断是不是有会员权限
            if (isok1 != true) {
                isok2 = memberRecordService.checkUserMember(userId, (long) course.get("courseId"));
            }
            if (isok1 || isok2) {// 已购买或已开通会员
                isok = true;
            }
            if (isok != true) {
                json = this.getJsonMap(false, "您没有观看权限", null);
                return json;// 不符合观看资格返回课程详情
            }
            // 得到视频
            Map<String, Object> ck = eduAppService.getCourseKpointById(kpointId);
            /*
			 * //查询是否有该课程观看记录 CourseStudyhistory tempHistory = new
			 * CourseStudyhistory(); tempHistory.setCourseId(courseId);
			 * tempHistory.setUserId(userId); List<CourseStudyhistory>
			 * courseStudyList =
			 * courseStudyhistoryService.getCourseStudyhistoryList(tempHistory);
			 * if(courseStudyList==null || courseStudyList.size()==0){
			 * //更新观看人数加1
			 * courseProfileService.updateCourseProfile(CourseProfileType.
			 * watchpersoncount.toString(), tempHistory.getCourseId(),
			 * 1L,CourseProfile.ADD); }
			 */
            json = this.getJsonMap(true, "查询成功", ck);
        } catch (Exception e) {
            json = this.getJsonMap(false, "系统繁忙", null);
            logger.error("palyKpoint()---error", e);
        }
        return json;
    }

    /**
     * app查询用户学习记录列表
     *
     * @param request
     * @return
     */
    @RequestMapping("/userlearningrecords")
    @ResponseBody
    public Map<String, Object> queryUserLearningRecords(HttpServletRequest request,
                                                        @ModelAttribute("page") PageEntity page) {
        Map<String, Object> json = null;
        try {

            int currentPage = Integer.parseInt(request.getParameter("currentPage"));
            page.setCurrentPage(currentPage);
            page.setPageSize(10);
            String pageSize = request.getParameter("pageSize");
            if (pageSize != null && pageSize.trim().length() > 0) {
                page.setPageSize(Integer.parseInt(pageSize));
            }

            // 得到用户ID
            long userId = Integer.parseInt(request.getParameter("userId"));
            Map<String, Object> courseStudyhistory = new HashMap<String, Object>();
            courseStudyhistory.put("userId", userId);
            // 查询用户学习记录
            List<Map<String, Object>> studylist = eduAppService.getCourseStudyhistoryListByCondition(courseStudyhistory,
                    page);
            Map<String, Object> dataMap = new HashMap<String, Object>();
            dataMap.put("studylist", studylist);
            dataMap.put("totalPageSize", page.getTotalPageSize());
            json = this.getJsonMap(true, "查询成功", dataMap);
        } catch (Exception e) {
            json = this.getJsonMap(false, "系统繁忙", null);
            logger.error("queryUserLearningRecords()----error", e);
        }
        return json;
    }

    /***
     * app查询用户收藏列表
     *
     * @param request
     * @return
     */
    @RequestMapping("/usercollectionlist")
    @ResponseBody
    public Map<String, Object> queryUserCollectionCourseList(HttpServletRequest request,
                                                             @ModelAttribute("page") PageEntity page) {
        Map<String, Object> json = null;
        try {
            long userId = Long.parseLong(request.getParameter("userId"));
            int currentPage = Integer.parseInt(request.getParameter("currentPage"));
            page.setCurrentPage(currentPage);
            page.setPageSize(10);
            String pageSize = request.getParameter("pageSize");
            if (pageSize != null && pageSize.trim().length() > 0) {
                page.setPageSize(Integer.parseInt(pageSize));
            }
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("userId", userId);
            // 用户收藏课程
            List<Map<String, Object>> courseList = eduAppService.getCourseFavoritesByUserId(map, page);
            Map<String, Object> dataMap = new HashMap<String, Object>();
            dataMap.put("courseList", courseList);
            dataMap.put("totalPageSize", page.getTotalPageSize());
            json = this.getJsonMap(true, "查询成功", dataMap);
        } catch (Exception e) {
            json = this.getJsonMap(false, "系统繁忙", null);
            logger.error("queryUserCollectionCourseList()--error", e);
        }
        return json;
    }

    /***
     * app 收藏课程接口 传入userId（用户ID）和cousrseId（课程ID）
     *
     * @param request
     * @return
     */
    @RequestMapping("/collectioncourse")
    @ResponseBody
    public Map<String, Object> collectionCourse(HttpServletRequest request) {
        Map<String, Object> json = null;
        try {
            long userId = Long.parseLong(request.getParameter("userId"));// 用户ID
            long courseId = Long.parseLong(request.getParameter("courseId"));// 课程ID
            CourseFavorites courseFavorites = new CourseFavorites();
            courseFavorites.setCourseId(courseId);
            courseFavorites.setUserId(userId);
            courseFavorites.setAddTime(new Date());
            String falg = courseFavoritesService.addCourseFavorites(courseFavorites);
            if (falg.equals(WebContants.OWNED)) {
                json = this.getJsonMap(false, "您已经收藏过该课程了", null);
            } else if (falg.equals(WebContants.SUCCESS)) {
                json = this.getJsonMap(true, "收藏成功", null);
            }
        } catch (Exception e) {
            json = this.getJsonMap(false, "系统繁忙", null);
            logger.error("collectionCourse()---error", e);
        }
        return json;
    }

    /***
     * app查询资讯详情
     *
     * @param request
     * @return
     */
    @RequestMapping("/showarticleinfo")
    @ResponseBody
    public Map<String, Object> queryArticleInfo(HttpServletRequest request) {
        Map<String, Object> json = null;
        try {
            long articleId = Long.parseLong(request.getParameter("articleId"));
            Map<String, Object> article = eduAppService.getArticleById(articleId);
            articleService.updateArticleClickTimes(Long.parseLong(article.get("id") + ""));// 更新浏览次数

            Map<String, Object> queryArticle = new HashMap<String, Object>();
            queryArticle.put("id", articleId);

            // 上一篇
            queryArticle.put("type", 1);
            Map<String, Object> upArticle = eduAppService.queryArticleUpOrDown(queryArticle);
            // 下一篇
            queryArticle.put("type", 2);
            Map<String, Object> downArticle = eduAppService.queryArticleUpOrDown(queryArticle);
            Map<String, Object> dataMap = new HashMap<String, Object>();
            dataMap.put("article", article);
            dataMap.put("upArticle", upArticle);
            dataMap.put("downArticle", downArticle);
            json = this.getJsonMap(true, "查询成功", dataMap);
        } catch (Exception e) {
            json = this.getJsonMap(false, "系统繁忙", null);
            logger.error("queryArticleInfo()--error", e);
        }
        return json;
    }

    /***
     * app资讯列表 接收currentPage，pageSize参数，pageSize页面显示记录数，可不传，默认为10
     * currentPage当前页数 必传
     *
     * @param request
     * @return
     */
    @RequestMapping("/queryarticlelist")
    @ResponseBody
    public Map<String, Object> queryArticleList(HttpServletRequest request) {
        Map<String, Object> json = null;
        try {
            int currentPage = Integer.parseInt(request.getParameter("currentPage"));
            PageEntity page = new PageEntity();
            page.setCurrentPage(currentPage);
            page.setPageSize(10);
            String pageSize = request.getParameter("pageSize");
            if (pageSize != null && pageSize.trim().length() > 0) {
                page.setPageSize(Integer.parseInt(pageSize));
            }
            Map<String, Object> article = new HashMap<String, Object>();
            List<Map<String, Object>> articleList = eduAppService.queryArticleListPage(article, page);
            Map<String, Object> dataMap = new HashMap<String, Object>();
            dataMap.put("articleList", articleList);
            dataMap.put("totalPageSize", page.getTotalPageSize());
            json = this.getJsonMap(true, "查询成功", dataMap);
        } catch (Exception e) {
            json = this.getJsonMap(false, "系统繁忙", null);
            logger.error("queryArticleList()----error", e);
        }
        return json;
    }

    /***
     * app查询课程详情
     *
     * @param request
     * @return
     */
    @RequestMapping("/showcourseinfo")
    @ResponseBody
    public Map<String, Object> queryCourseInfo(HttpServletRequest request) {
        Map<String, Object> json = null;
        try {
            String courseId = request.getParameter("courseId");
            String userId = request.getParameter("userId");
            Map<String, Object> dataMap = new HashMap<String, Object>();
            if (courseId != null && courseId.trim().length() > 0) {
                Course course = courseService.getCourseById(Long.parseLong(courseId));
                if (course == null) {
                    json = this.getJsonMap(false, "课程不存在或已删除", null);
                }
                // 查询课程专业
                CourseSubject courseSubject = new CourseSubject();
                courseSubject.setCourseId(course.getId());
                List<CourseSubject> courseSubjectList = courseSubjectService.getCourseSubjectList(courseSubject);
                if (ObjectUtils.isNotNull(courseSubjectList)) {
                    course.setSubjectId(courseSubjectList.get(0).getSubjectId());
                }
                // 查询课程老师
                // 查询课程关联的教师
                CourseTeacher courseTeacher = new CourseTeacher();
                courseTeacher.setCourseId(course.getId());
                List<CourseTeacher> courseTeacherList = courseTeacherService.getCourseTeacherList(courseTeacher);
                if (courseTeacherList != null && courseTeacherList.size() > 0) {
                    List<Teacher> teacherList = new ArrayList<Teacher>();
                    for (CourseTeacher ct : courseTeacherList) {
                        Teacher teacher = teacherService.getTeacherById(ct.getTeacherId());
                        teacherList.add(teacher);
                    }
                    course.setTeacherList(teacherList);
                }
                // 判断该课程是否可以观看
                boolean isok = false;
                // 判断是否购买课程且未到期
                boolean isok1 = trxorderService.checkCourseLook(Long.parseLong(courseId), Long.parseLong(userId));
                boolean isok2 = false;
                if (isok1 != true) {
                    isok2 = memberRecordService.checkUserMember(Long.parseLong(userId), course.getId());
                }
                if (isok1 || isok2) {// 已购买或已开通会员
                    isok = true;
                }
                // 显示目录树list,课程时也放到此list.size为1而已
                List<CourseDto> courseDtos = new ArrayList<CourseDto>();
                // 获取套餐的课程列表
                if (course.getSellType().equalsIgnoreCase(SellType.PACKAGE.toString())) {
                    List<Long> alist = new ArrayList<Long>();
                    alist.add(course.getId());
                    courseDtos = courseService.getCourseListPackage(alist);
                }
                // 获取课程目录
                List<Object> allVideoList = getVideoList(course, courseDtos);

                Map<String, Object> map = new HashMap<>();
                map.put("subjectId", course.getSubjectId());
                map.put("courseId", course.getId());
                map.put("num", 5);
                // 同类课程推荐
                List<Map<String, Object>> courseList = eduAppService.queryAppSubjectCourse(map);
                List<Map<String, Object>> boundCourseList = new ArrayList<Map<String, Object>>();
                if (courseList != null) {
                    for (Map<String, Object> cd : courseList) {
                        Map<String, Object> m = new HashMap<String, Object>();
                        m.put("courseId", cd.get("courseId"));
                        m.put("courseName", cd.get("name"));
                        m.put("teahcerList", cd.get("teacherList"));
                        m.put("logo", cd.get("logo"));
                        m.put("lessionnum", cd.get("lessionnum"));
                        m.put("pageViewcount", cd.get("pageViewcount"));
                        m.put("currentPrice", cd.get("currentPrice"));
                        m.put("sourcePrice", cd.get("sourcePrice"));
                        boundCourseList.add(m);
                    }
                }

                // 重新处理课程详情信息，因为转换成JSON会出错
                Map<String, Object> courseMap = new HashMap<String, Object>();
                courseMap.put("courseId", course.getId());
                courseMap.put("currentprice", course.getCurrentprice());
                courseMap.put("name", course.getName());
                courseMap.put("teacherList", course.getTeacherList());
                courseMap.put("courseLogo", course.getLogo());
                courseMap.put("context", course.getContext());
                // 课程的会员信息
                List<MemberType> memberTypes = memberTypeService.getMemberTypesBycourse(course.getId());
                if (ObjectUtils.isNotNull(memberTypes) && memberTypes.size() > 0) {
                    courseMap.put("memberType", memberTypes.get(0).getTitle());
                }
                // 返回数据
                String type = request.getParameter("type");
                if (type != null && type.equals("IOS")) {
                    isok = true;
                }
                dataMap.put("isok", isok);// 是否可以直接观看 true可以，false不可以
                dataMap.put("catalogList", allVideoList);
                dataMap.put("course", courseMap);
                dataMap.put("boundCourseList", boundCourseList);
                // 更新课程的浏览数量
                courseProfileService.updateCourseProfile(CourseProfileType.viewcount.toString(), course.getId(), 1L,
                        CourseProfile.ADD);
                if (!isok) {
                    json = this.getJsonMap(true, "您未购买该课程", dataMap);
                } else {
                    json = this.getJsonMap(true, "查询成功", dataMap);
                }
            }
        } catch (Exception e) {
            json = this.getJsonMap(false, "系统繁忙", null);
            logger.error("queryCourseInfo()--error", e);
            this.setExceptionRequest(request, e);
        }
        return json;
    }

    /**
     * 获取课程目录
     *
     * @param course
     * @param courseDtos
     * @return
     */
    private List<Object> getVideoList(Course course, List<CourseDto> courseDtos) {
        List<Map<String, Object>> videoList = null;
        // 查询课程目录
        CourseKpoint courseKpoint = new CourseKpoint();
        List<Object> allVideoList = new ArrayList<Object>();
        if (courseDtos != null && courseDtos.size() > 0) {
            for (CourseDto cd : courseDtos) {
                courseKpoint.setCourseId(cd.getId());
                List<CourseKpoint> courseKpointList = courseKpointService.getCourseKpointList(courseKpoint);
                if (courseKpointList != null && courseKpointList.size() > 0) {
                    videoList = hendList(courseKpointList, cd);
                    allVideoList.add(videoList);
                }
            }
        } else {
            allVideoList = new ArrayList<Object>();
            courseKpoint.setCourseId(course.getId());
            List<CourseKpoint> courseKpointList = courseKpointService.getCourseKpointList(courseKpoint);
            videoList = hendList(courseKpointList, course);
            allVideoList.add(videoList);
        }
        return allVideoList;
    }

    private List<Map<String, Object>> hendList(List<CourseKpoint> list, Course course) {
        Map<String, Object> maps = new HashMap<String, Object>();
        maps.put("courseName", course.getName());
        Map<String, Object> map = null;
        // 第一层次
        List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
        // 第二层次
        List<Map<String, Object>> childList = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < list.size(); i++) {// 循环得到第一层
            CourseKpoint ck = list.get(i);
            if (ck.getParentId() == 0) {
                map = new HashMap<String, Object>();
                map.put("videoName", ck.getName());
                map.put("kpointId", ck.getId());
                map.put("videoUrl", ck.getVideourl());
                map.put("videoType", ck.getVideotype());
                map.put("isfree", ck.getIsfree());
                map.put("type", ck.getType());
                map.put("kpointCourseId", ck.getCourseId());
                listMap.add(map);
            }
        }
        for (Map<String, Object> mapp : listMap) {// 循环得到第二层
            long kpointId = Long.parseLong(mapp.get("kpointId").toString());
            for (int j = 0; j < list.size(); j++) {
                CourseKpoint cck = list.get(j);
                if (cck.getParentId() == kpointId) {
                    map = new HashMap<String, Object>();
                    map.put("kpointId", cck.getId());
                    map.put("courseId", cck.getCourseId());
                    map.put("videoName", cck.getName());
                    map.put("videoUrl", cck.getVideourl());
                    map.put("videoId", cck.getId());
                    map.put("isfree", cck.getIsfree());
                    map.put("videoType", cck.getVideotype());
                    map.put("type", cck.getType());
                    map.put("kpointCourseId", cck.getCourseId());
                    childList.add(map);
                }
            }
            mapp.put("childList", childList);
            childList = new ArrayList<Map<String, Object>>();
        }
        listMap.add(0, maps);
        return listMap;
    }

    /**
     * 查询所有专业的接口
     */
    @RequestMapping("/allsubjectlist")
    @ResponseBody
    public Map<String, Object> queryAllSubject(HttpServletRequest request) {
        Map<String, Object> json = null;
        try {
            QuerySubject query = new QuerySubject();
            query.setParentId(0L);
            query.setSubjectId(-1L);
            List<Subject> subjectList = subjectService.getSubjectListByLevel(query);
            json = this.getJsonMap(true, "查询成功", subjectList);
        } catch (Exception e) {
            json = this.getJsonMap(false, "系统繁忙", null);
            logger.error("queryAllSubject()---error", e);
            this.setExceptionRequest(request, e);
        }
        return json;
    }

    /**
     * App（首页/课程分类列表）查询所有的课程列表接口 currentPage当前页面数
     * pageSize页面显示记录数，如果为null则用户默认设置10条
     */
    @RequestMapping("/appalltypecourselist")
    @ResponseBody
    public Map<String, Object> appAllSubjectCourseList(HttpServletRequest request,
                                                       @ModelAttribute("page") PageEntity page) {
        Map<String, Object> json = null;
        try {
            // =========条件==============
            String order = request.getParameter("order");
            String courseName = request.getParameter("courseName");
            String subjectId = request.getParameter("subjectId");
            String freeCourse = request.getParameter("freeCourse");
            String hotCourse = request.getParameter("hotCourse");
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("order", order);
            map.put("courseName", courseName);
            map.put("subjectId", 0);
            map.put("freeCourse", freeCourse);
            map.put("hotCourse", hotCourse);
            if (subjectId != null && subjectId.trim().length() > 0) {
                map.put("subjectId", Integer.parseInt(subjectId));
            }
            // =========条件==============
            int currentPage = Integer.parseInt(request.getParameter("currentPage"));
            page.setPageSize(10);
            String pageSize = request.getParameter("pageSize");
            if (pageSize != null) {
                page.setPageSize(Integer.parseInt(pageSize));
            }
            page.setCurrentPage(currentPage);
            // 搜索课程列表
            List<Map<String, Object>> courseList = eduAppService.queryAppAllCourse(map, page);

            Map<String, Object> dataMap = new HashMap<String, Object>();
            dataMap.put("courseList", courseList);
            dataMap.put("totalPage", page.getTotalPageSize());
            json = this.getJsonMap(true, "查询成功", dataMap);
        } catch (Exception e) {
            json = this.getJsonMap(false, "系统繁忙", null);
            this.setExceptionRequest(request, e);
            logger.error("appAllSubjectCourseList()--error", e);
        }
        return json;
    }

    /***
     * app查询首页banner图片
     *
     * @return
     */
    @RequestMapping("/queryindexbanner")
    @ResponseBody
    public Map<String, Object> queryIndexBanner(HttpServletRequest requset) {
        Map<String, Object> json = null;
        try {
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("count", 4);
            paramMap.put("keyWord", "indexCenterBanner");
            Map<String, List<AppWebsiteImages>> map = appWebsiteImagesService.getIndexPageBanner(paramMap);
            json = this.getJsonMap(true, "查询成功", map);
        } catch (Exception e) {
            json = this.getJsonMap(false, "系统繁忙", null);
            logger.error("queryIndexBanner()---error", e);
        }
        return json;
    }

    /***
     * app查询配置图片
     *
     * @return
     */
    @RequestMapping("/queryAppConfigImage")
    @ResponseBody
    public Map<String, Object> queryAppConfigImage(HttpServletRequest requset) {
        Map<String, Object> json = null;
        try {
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("count", 1);
            paramMap.put("keyWord", "appLogo");
            Map<String, List<AppWebsiteImages>> appLogo = appWebsiteImagesService.getIndexPageBanner(paramMap);
            paramMap.put("count", 0);
            paramMap.put("keyWord", "loadBanner");
            Map<String, List<AppWebsiteImages>> loadBanner = appWebsiteImagesService.getIndexPageBanner(paramMap);

            Map<String, Object> map = new HashMap<String, Object>();
            map.put("appLogo", appLogo.get("appLogo"));
            map.put("loadBanner", loadBanner.get("loadBanner"));

            json = this.getJsonMap(true, "查询成功", map);
        } catch (Exception e) {
            json = this.getJsonMap(false, "系统繁忙", null);
            logger.error("queryIndexBanner()---error", e);
        }
        return json;
    }

    /***
     * app查询首页推荐课程
     *
     * @param request
     * @return
     */
    @RequestMapping("/queryindexcourseDetails")
    @ResponseBody
    public Map<String, Object> queryWebsiteCourseDetails(HttpServletRequest request) {
        Map<String, Object> json = null;
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("recommendId", 7);
            map.put("count", 4);
            Map<String, List<Map<String, Object>>> recommendMap = eduAppService.getWebWebsiteCourseDetails();
            json = this.getJsonMap(true, "查询成功", recommendMap.get("1"));
        } catch (Exception e) {
            logger.error("queryWebsiteCourseDetails", e);
            json = this.getJsonMap(false, "系统繁忙", null);
        }
        return json;
    }

    /**
     * App首页查询所有的课程列表接口 currentPage当前页面数 pageSize页面显示记录数，如果为null则用户默认设置10条
     */
    @RequestMapping("/appallcourselist")
    @ResponseBody
    public Map<String, Object> appAllCourseList(HttpServletRequest request, @ModelAttribute("page") PageEntity page) {
        Map<String, Object> json = null;
        Map<String, Object> dataMap = new HashMap<String, Object>();
        try {
            int currentPage = Integer.parseInt(request.getParameter("currentPage"));
            page.setPageSize(10);
            String pageSize = request.getParameter("pageSize");
            if (pageSize != null) {
                page.setPageSize(Integer.parseInt(pageSize));
            }
            page.setCurrentPage(currentPage);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("order", null);
            map.put("courseName", null);
            map.put("subjectId", 0);
            // 搜索课程列表
            List<Map<String, Object>> courseList = eduAppService.queryAppAllCourse(map, page);

            dataMap.put("courseList", courseList);
            dataMap.put("totalPage", page.getTotalPageSize());
            json = this.getJsonMap(true, "查询成功", dataMap);
        } catch (Exception e) {
            json = this.getJsonMap(false, "系统繁忙", null);
            this.setExceptionRequest(request, e);
            logger.error("appAllCourseList()--error", e);
        }
        return json;
    }

    /***
     * app注册接口
     *
     * @param request
     * @return
     */
    @RequestMapping("/appregister")
    @ResponseBody
    public Map<String, Object> appRegister(HttpServletRequest request) {
        Map<String, Object> json = null;
        try {
            String mobile = request.getParameter("mobile");// 手机号
            String email = request.getParameter("email");// 邮箱
            String userPassword = request.getParameter("userPassword");// 密码
            String confirmPwd = request.getParameter("confirmPwd");// 确认密码

            if (mobile == null)
                mobile = "";
            if (email == null)
                email = "";
            Pattern pat = Pattern.compile("^1[0-9]{10}$");
            Matcher mat = pat.matcher(mobile);
            if (!mat.matches()) {
                json = this.getJsonMap(false, "请输入正确的手机号", null);
                return json;
            }
            pat = Pattern.compile("^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$");
            mat = pat.matcher(email);
            if (!mat.matches()) {
                json = this.getJsonMap(false, "请输入正确的邮箱", null);
                return json;
            }
            if (userPassword == null || userPassword.trim().length() == 0) {
                json = this.getJsonMap(false, "请输入密码", null);
                return json;
            }
            if (!userPassword.equals(confirmPwd)) {
                json = this.getJsonMap(false, "两次密码不一致", null);
                return json;
            }
            if (PreventInfusion.sql_inj(userPassword)) {
                json = this.getJsonMap(false, "不能输入非法数据", null);
                return json;
            }
            Map<String, Object> user = new HashMap<String, Object>();
            user.put("email", email.trim().toLowerCase());// 邮箱存储时转为小写
            user.put("mobile", mobile);
            List<Map<String, Object>> list = eduAppService.getUserList(user);
            if (ObjectUtils.isNotNull(list)) {
                json = this.getJsonMap(false, "邮箱已经被使用", null);
                return json;
            }
            int ismobile = eduAppService.getUserByMobile(user);
            // 验证手机唯一
            if (ismobile != 0) {
                json = this.getJsonMap(false, "手机号已经被使用", null);
                return json;
            }
            // 邮箱注册
            String userIp = WebUtils.getIpAddr(request);
            User userNew = new User();
            userNew.setEmail(email.trim().toLowerCase());
            userNew.setMobile(mobile);
            userNew.setPassword(userPassword);
            userNew.setUserip(userIp);
            userNew.setRegisterFrom(UserExpandFrom.appFrom.toString());// app注册
            userService.addUser(userNew);// 添加用户
            // 注册送积分
            userIntegralService.addUserIntegral(IntegralKeyword.register.toString(), userNew.getId(), 0L, 0L, "");
            // 添加登录记录
            UserLoginLog userLoginLog = new UserLoginLog();
            userLoginLog.setLoginIp(WebUtils.getIpAddr(request));
            userLoginLog.setUserId(userNew.getId());
            userLoginLog.setLoginTime(new Date());
            userLoginLogService.addUserLoginLog(userLoginLog);
            // 登陆赠送积分
            userIntegralService.addUserIntegral(IntegralKeyword.login.toString(), userNew.getId(), 0L, 0L, "");
            userNew.setPassword("");
            userNew.setCustomerkey("");
            userNew.setUserip("");
            json = this.getJsonMap(true, "注册成功", user);
        } catch (Exception e) {
            json = this.getJsonMap(false, "系统繁忙", null);
            this.setExceptionRequest(request, e);
            logger.error("appRegister()---error", e);
        }
        return json;
    }

    /***
     * app登录接口 ，接收两个参数 account帐号 userPassword密码，该接口支持邮箱和手机号登录
     *
     * @param request
     * @return
     */
    @RequestMapping("/applogin")
    @ResponseBody
    public Map<String, Object> appLogin(HttpServletRequest request) {
        Map<String, Object> json = null;
        try {
            String account = request.getParameter("account");
            String userPassword = request.getParameter("userPassword");
            if (PreventInfusion.sql_inj(account) || PreventInfusion.sql_inj(account)) {
                json = this.getJsonMap(false, "不能输入非法字符", null);
                return json;
            }
            if (account == null || account.trim().length() == 0) {
                json = this.getJsonMap(false, "请输入用户帐号", null);
                return json;
            }
            if (userPassword == null || userPassword.trim().length() == 0) {
                json = this.getJsonMap(false, "请输入密码", null);
                return json;
            }
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("email", account.trim());
            // 根据邮箱查询用户
            List<Map<String, Object>> userList = eduAppService.getUserListForLogin(map);

            Map<String, Object> user = new HashMap<String, Object>();
            // 如果用户不存在，则根据手机号来查询
            if (userList == null || userList.size() == 0) {
                userList = eduAppService.getUserListForTelLogin(map);
                map = null;
                if (userList != null && userList.size() > 0) {
                    user = userList.get(0);
                }
            } else {
                user = null;
                user = userList.get(0);
            }
            if (user == null || user.size() == 0) {
                json = this.getJsonMap(false, "用户帐号不存在", null);
                return json;
            }
            String password = PurseSecurityUtils.secrect(userPassword, user.get("customerkey") + "");
            if (!password.equals(user.get("password"))) {
                json = this.getJsonMap(false, "用户密码不正确", null);
                return json;
            }
            UserExpandDto userExpandDto = userService.queryUserExpand(Integer.parseInt(user.get("id") + ""));
            Map<String, Object> userMap = new HashMap<String, Object>();
            userMap.put("id", userExpandDto.getId());
            userMap.put("nickname", userExpandDto.getNickname());
            userMap.put("avatar", userExpandDto.getAvatar());
            json = this.getJsonMap(true, "登录成功", userMap);
            // 添加登录记录
            UserLoginLog userLoginLog = new UserLoginLog();
            userLoginLog.setLoginIp(WebUtils.getIpAddr(request));
            userLoginLog.setUserId((long) user.get("id"));
            userLoginLog.setLoginTime(new Date());
            userLoginLogService.addUserLoginLog(userLoginLog);
            // 登陆赠送积分
            userIntegralService.addUserIntegral(IntegralKeyword.login.toString(), (long) user.get("id"), 0L, 0L, "");
        } catch (Exception e) {
            json = this.getJsonMap(false, "系统繁忙", null);
            this.setExceptionRequest(request, e);
            logger.error("applogin()---error", e);
        }
        return json;
    }

    /***
     * app反馈接口
     *
     * @param request
     * @return
     */
    @RequestMapping("/appfreeback")
    @ResponseBody
    public Map<String, Object> appFreeBack(HttpServletRequest request) {
        Map<String, Object> json = null;
        try {
            String userId = request.getParameter("userId");
            String content = request.getParameter("content");
            // 获取用户
            Map<String, Object> user = eduAppService.getUserById(Long.parseLong(userId));
            Map<String, Object> userFeedback = new HashMap<String, Object>();
            userFeedback.put("userId", Long.parseLong(userId));
            userFeedback.put("content", content);
            userFeedback.put("email", user.get("email") + "");
            userFeedback.put("mobile", user.get("mobile") + "");
            userFeedback.put("nickname", user.get("nickname") + "");
            userFeedback.put("createTimr", new Date());
            eduAppService.addUserFeedback(userFeedback);
            json = this.getJsonMap(true, "操作成功", null);
        } catch (Exception e) {
            json = this.getJsonMap(false, "系统繁忙", null);
            this.setExceptionRequest(request, e);
            logger.error("appFreeBack()---error", e);
        }
        return json;
    }

    /**
     * 添加学习记录
     *
     * @param request
     * @return
     */
    @RequestMapping("/couser/playertimes")
    @ResponseBody
    public Object addCoursePlayerTime(HttpServletRequest request, @ModelAttribute("courseId") Long courseId,
                                      @ModelAttribute("kpointId") Long kpointId, @ModelAttribute("userId") Long userId) {
        // 要更新3个表 edu_course_profile,edu_course_studyhistory,edu_course_kpoint
        Map<String, Object> json = null;
        try {
            CourseStudyhistory courseStudyhistory = new CourseStudyhistory();
            courseStudyhistory.setCourseId(courseId);
            courseStudyhistory.setKpointId(kpointId);
            courseStudyhistory.setUserId(userId);
            courseStudyhistoryService.playertimes(courseStudyhistory);
            // 添加播放次数同时添加积分
            userIntegralService.addUserIntegral(IntegralKeyword.watch_video.toString(), getLoginUserId(request),
                    kpointId, 0L, "");
            json = this.getJsonMap(true, "success", null);
        } catch (Exception e) {
            setExceptionRequest(request, e);
            json = this.getJsonMap(false, "error", null);
        }
        return json;
    }

    /**
     * app更新接口
     *
     * @param request
     * @return Map<String,Object>
     */
    @RequestMapping("/app/update")
    @ResponseBody
    public Map<String, Object> updateApp(HttpServletRequest request) {
        Map<String, Object> json = null;
        try {
            List<AppUpdate> appList = appUpdateService.queryAllList();
            json = this.getJsonMap(true, null, appList);
        } catch (Exception e) {
            json = this.getJsonMap(false, "系统繁忙，请稍后再操作！", null);
            logger.error("updateApp()---error", e);
        }
        return json;
    }

    /**
     * 判断节点是否可以播放
     *
     * @return
     */
    @RequestMapping("/app/check/kpoint")
    @ResponseBody
    public Map<String, Object> checkPlayKopint(HttpServletRequest request,
                                               @RequestParam(value = "kpointId", required = true) Long kpointId,
                                               @RequestParam(value = "userId", required = true) Long userId) {
        Map<String, Object> json = null;
        try {
            // 查询节点
            CourseKpoint courseKpoint = courseKpointService.getCourseKpointById(kpointId);
            json = this.getJsonMap(false, "fail", null);
            // 当传入数据不正确时直接返回
            if (ObjectUtils.isNull(courseKpoint)) {
                return json;
            }
            boolean isok = true;
            boolean isok1 = false;
            boolean isok2 = false;
            if (courseKpoint.getIsfree() == 2) {// 判断节点不可以试听
                isok1 = trxorderService.checkCourseLook(courseKpoint.getCourseId(), userId);
                if (isok1 == false) {// 判断该课程不可以观看
                    if (userId != 0) {
                        isok2 = memberRecordService.checkUserMember(getLoginUserId(request),
                                courseKpoint.getCourseId());
                        if (isok2 == false) {// 判断会员不可以观看
                            isok = false;
                        }
                    } else {
                        isok = false;
                    }
                }
            }
            if (isok) {// 添加播放记录，返回可播放验证true
                CourseStudyhistory courseStudyhistory = new CourseStudyhistory();
                courseStudyhistory.setCourseId(courseKpoint.getCourseId());
                courseStudyhistory.setKpointId(kpointId);
                courseStudyhistory.setUserId(userId);
                courseStudyhistoryService.playertimes(courseStudyhistory);
                // 添加播放次数同时添加积分
                userIntegralService.addUserIntegral(IntegralKeyword.watch_video.toString(), getLoginUserId(request),
                        kpointId, 0L, "");
                json = this.getJsonMap(true, "success", null);
            }
        } catch (Exception e) {
            logger.error("AppController.checkPlayKopint", e);
            json = this.getJsonMap(false, "error", null);
        }
        return json;
    }

    /**
     * 个人信息
     */
    @RequestMapping("/user/info")
    @ResponseBody
    public Map<String, Object> userInfo(@RequestParam("userId") Long userId) {
        Map<String, Object> json = null;
        try {
            UserExpandDto userExpandDto = userExpandService.getUserExpandByUids(userId);
            json = this.getJsonMap(true, "查询成功", userExpandDto);
        } catch (Exception e) {
            logger.error("userInfo----error", e);
            json = this.getJsonMap(false, "系统繁忙，请稍后再试", null);
        }
        return json;
    }

    /**
     * 个人头像修改
     */
    @RequestMapping("/user/avatar")
    @ResponseBody
    public Map<String, Object> userUpdateAvatar(@RequestParam("userId") Long userId,
                                                @RequestParam("avatar") String avatar) {
        Map<String, Object> json = null;
        try {
            UserExpand userExpand = new UserExpand();
            userExpand.setAvatar(avatar);
            userExpand.setCusId(userId);
            userExpandService.updateUserExpand(userExpand);
            cacheKit.remove(MemConstans.USEREXPAND_INFO + userId);
            json = this.getJsonMap(true, "修改成功", null);
        } catch (Exception e) {
            logger.error("userUpdateAvatar----error", e);
            json = this.getJsonMap(false, "系统繁忙，请稍后再试", null);
        }
        return json;
    }
}
