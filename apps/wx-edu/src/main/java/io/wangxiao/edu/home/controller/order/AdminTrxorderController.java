package io.wangxiao.edu.home.controller.order;

import com.google.gson.JsonObject;
import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.commons.service.cache.CacheKit;
import io.wangxiao.commons.service.gain.GuidGeneratorService;
import io.wangxiao.commons.util.DateUtils;
import io.wangxiao.commons.util.ObjectUtils;
import io.wangxiao.edu.common.constants.MemConstans;
import io.wangxiao.edu.common.contoller.SingletonLoginUtils;
import io.wangxiao.edu.common.util.FileExportImportUtil;
import io.wangxiao.edu.home.common.EduBaseController;
import io.wangxiao.edu.home.constants.enums.*;
import io.wangxiao.edu.home.entity.coupon.CouponCode;
import io.wangxiao.edu.home.entity.coupon.CouponCodeDTO;
import io.wangxiao.edu.home.entity.course.CourseDto;
import io.wangxiao.edu.home.entity.course.QueryCourse;
import io.wangxiao.edu.home.entity.order.*;
import io.wangxiao.edu.home.entity.user.UserAccount;
import io.wangxiao.edu.home.service.coupon.CouponCodeService;
import io.wangxiao.edu.home.service.coupon.CouponService;
import io.wangxiao.edu.home.service.course.CourseService;
import io.wangxiao.edu.home.service.order.TrxdetailOptRecordService;
import io.wangxiao.edu.home.service.order.TrxorderDetailService;
import io.wangxiao.edu.home.service.order.TrxorderOptRecordService;
import io.wangxiao.edu.home.service.order.TrxorderService;
import io.wangxiao.edu.home.service.user.UserAccountService;
import io.wangxiao.edu.home.service.user.UserService;
import io.wangxiao.edu.sysuser.entity.QuerySubject;
import io.wangxiao.edu.sysuser.entity.Subject;
import io.wangxiao.edu.sysuser.entity.SysUserOptRecord;
import io.wangxiao.edu.sysuser.service.SubjectService;
import io.wangxiao.edu.sysuser.service.SysUserOptRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 后台订单接口管理 AdminTrxorderController
 */
@Controller
@RequestMapping("/admin")
public class AdminTrxorderController extends EduBaseController {

    private static final Logger logger = LoggerFactory.getLogger(AdminTrxorderController.class);

    private static final String orderlist = getViewPath("/admin/order/trxorder_list");
    private static final String orderInfo = getViewPath("/admin/order/trxorder_info");
    private static final String orerDetailList = getViewPath("/admin/order/trxorder_detail_list");
    private static final String orderDetailInfo = getViewPath("/admin/order/order_detail_info");
    private static final String redirectDetailList = "redirect:/admin/order/orderDetailList";
    private static final String showCourseList = getViewPath("/admin/order/cou_list");// 课程列表

    private static final String orderOptRecordList = getViewPath("/admin/order/order_opt_record_list"); //订单操作记录
    private static final String orderDetailOptRecordList = getViewPath("/admin/order/orderDetail_opt_record_list"); //订单流水操作记录


    @Autowired
    private TrxorderService trxorderService;

    @Autowired
    private TrxorderDetailService trxorderDetailService;

    @Autowired
    private TrxorderOptRecordService trxorderOptRecordService;// 订单操作服务类

    @Autowired
    private TrxdetailOptRecordService trxdetailOptRecordService;// 流水操作服务
    @Autowired
    private CouponCodeService couponCodeService;// 优惠券编码
    @Autowired
    private CouponService couponService;// 优惠券
    @Autowired
    private UserAccountService userAccountService;// 退费时冲入账户
    @Autowired
    private GuidGeneratorService guidGeneratorService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private SubjectService subjectService;
    @Autowired
    private UserService userService;
    @Autowired
    private SysUserOptRecordService sysUserOptRecordService;

    // 绑定属性 封装参数
    @InitBinder("queryTrxorder")
    public void initQueryTrxorder(HttpServletRequest request, ServletRequestDataBinder binder) {
        binder.setFieldDefaultPrefix("queryTrxorder.");
    }

    // 绑定属性 封装参数
    @InitBinder("trxorder")
    public void inittrxorder(HttpServletRequest request, ServletRequestDataBinder binder) {
        binder.setFieldDefaultPrefix("trxorder.");
    }

    // 绑定属性 封装参数
    @InitBinder("trxorderDetail")
    public void InittrxorderDetail(HttpServletRequest request, ServletRequestDataBinder binder) {
        binder.setFieldDefaultPrefix("trxorderDetail.");
    }

    // 绑定属性 封装参数
    @InitBinder("queryTrxorderDetail")
    public void InitQueryTrxorderDetail(HttpServletRequest request, ServletRequestDataBinder binder) {
        binder.setFieldDefaultPrefix("queryTrxorderDetail.");
    }

    // 绑定属性 封装参数
    @InitBinder("queryCourse")
    public void InitQueryCourse(HttpServletRequest request, ServletRequestDataBinder binder) {
        binder.setFieldDefaultPrefix("queryCourse.");
    }

    @InitBinder("trxorderOptRecord")
    public void initBinderTrxorderOptRecord(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("trxorderOptRecord.");
    }

    @InitBinder("trxdetailOptRecord")
    public void initBinderTrxdetailOptRecord(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("trxdetailOptRecord.");
    }

    private CacheKit cacheKit = CacheKit.getInstance();

    /**
     * 查询订单分页列表
     *
     * @param queryTrxorder
     * @return
     */
    @RequestMapping("/order/orderPageResult")
    public ModelAndView queryOrderPageResultByCondition(HttpServletRequest request, @ModelAttribute("queryTrxorder") QueryTrxorder queryTrxorder, @ModelAttribute("page") PageEntity page) {
        ModelAndView model = new ModelAndView();
        model.setViewName(orderlist);
        try {
            page.setPageSize(10);
            List<QueryTrxorder> orderList = trxorderService.queryOrderPageResult(queryTrxorder, page);
            model.addObject("orderList", orderList);
            model.addObject("page", page);
            model.addObject("queryTrxorder", queryTrxorder);
        } catch (Exception e) {
            logger.error("queryOrderPageResultByCondition.error------", e);
            return new ModelAndView(this.setExceptionRequest(request, e));
        }
        return model;
    }


    /**
     * 查看订单
     *
     * @param queryTrxorder
     * @return
     */
    @RequestMapping("/order/orderinfo/{id}")
    public ModelAndView getOrderInfo(@PathVariable Long id, HttpServletRequest request) {
        ModelAndView model = new ModelAndView();
        model.setViewName(orderInfo);
        try {
            QueryTrxorder order = trxorderService.getOrderInfoById(id);
            model.addObject("order", order);
        } catch (Exception e) {
            logger.error("getOrderInfo.error------", e);
            return new ModelAndView(this.setExceptionRequest(request, e));
        }
        return model;
    }

    /**
     * 流水导出
     *
     * @param request
     * @param response
     * @param queryTrxorder
     * @param page
     */
    @RequestMapping("/order/export")
    public void exportOrderResult(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("queryTrxorder") QueryTrxorder queryTrxorder) {
        try {
            // 指定文件生成路径
            String dir = request.getSession().getServletContext().getRealPath("/excelfile/order");
            // 文件名
            String expName = "订单信息_" + DateUtils.getStringDateShort();
            // 表头信息
            String[] headName = {"ID", "用户邮箱", "手机号", "下单时间", "支付时间", "原始价格", "优惠价格", "实际支付", "订单编号", "交易状态", "支付类型"};
            // 拆分为一万条数据每Excel，防止内存使用太大
            PageEntity page = new PageEntity();
            page.setPageSize(10000);
            trxorderService.queryOrderPageResult(queryTrxorder, page);
            int num = page.getTotalPageSize();// 总页数
            List<File> srcfile = new ArrayList<File>();// 生成的excel的文件的list
            for (int i = 1; i <= num; i++) {// 循环生成num个xls文件
                page.setCurrentPage(i);
                List<QueryTrxorder> orderList = trxorderService.queryOrderPageResult(queryTrxorder, page);
                List<List<String>> list = orderJoint(orderList);
                File file = FileExportImportUtil.createExcel(headName, list, expName + "_" + i, dir);
                srcfile.add(file);
            }
            FileExportImportUtil.createRar(response, dir, srcfile, expName);// 生成的多excel的压缩包
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 订单excel格式拼接
     *
     * @param couponCodes
     * @return
     */
    public List<List<String>> orderJoint(List<QueryTrxorder> orderList) {
        List<List<String>> list = new ArrayList<List<String>>();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (int i = 0; i < orderList.size(); i++) {
            List<String> small = new ArrayList<String>();
            small.add(orderList.get(i).getId() + "");
            small.add(orderList.get(i).getEmail());
            small.add(orderList.get(i).getMobile());
            small.add(format.format(orderList.get(i).getCreateTime()));
            if (orderList.get(i).getPayTime() != null) {
                small.add(format.format(orderList.get(i).getPayTime()));
            } else {
                small.add("");
            }
            small.add(orderList.get(i).getOrderAmount() + "");
            small.add(orderList.get(i).getCouponAmount() + "");
            small.add(orderList.get(i).getAmount() + "");
            small.add(orderList.get(i).getRequestId() + "");
            if (orderList.get(i).getTrxStatus().equals("INIT")) {
                small.add("未支付");
            } else if (orderList.get(i).getTrxStatus().equals("SUCCESS")) {
                small.add("已支付");
            } else if (orderList.get(i).getTrxStatus().equals("REFUND")) {
                small.add("退款");
            } else if (orderList.get(i).getTrxStatus().equals("CANCEL")) {
                small.add("取消");
            }
            if (orderList.get(i).getPayType().equals("ALIPAY")) {
                small.add("支付宝");
            } else if (orderList.get(i).getPayType().equals("KUAIQIAN")) {
                small.add("快钱");
            } else if (orderList.get(i).getPayType().equals("WEIXIN")) {
                small.add("微信");
            } else if (orderList.get(i).getPayType().equals("CARD")) {
                small.add("课程卡");
            } else if (orderList.get(i).getPayType().equals("FREE")) {
                small.add("赠送");
            } else if (orderList.get(i).getPayType().equals("INTEGRAL")) {
                small.add("积分");
            } else if (orderList.get(i).getPayType().equals("ACCOUNT")) {
                small.add("账户");
            }
            list.add(small);
        }
        return list;
    }

    /**
     * 审核订单 修改 订单和流水的状态
     *
     * @param trxorder
     * @version guozhenzhou
     * @Date 2014-09-27
     */
    @RequestMapping("/order/verify")
    @ResponseBody
    public Map<String, Object> verifyOrder(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("trxorder") Trxorder trxorder) {
        Map<String, Object> json = null;
        try {
            if (trxorder != null) {
                Date date = new Date();
                trxorder = trxorderService.getTrxorderById(trxorder.getId());
                trxorder.setPayTime(date);
                trxorder.setTrxStatus(TrxOrderStatus.SUCCESS.toString());

                Trxorder beforeRecord = trxorderService.getTrxorderById(trxorder.getId());
                trxorderService.updateTrxorder(trxorder); // 修改订单状态
                Trxorder afterRecord = trxorderService.getTrxorderById(trxorder.getId());
                SysUserOptRecord record = new SysUserOptRecord(request, "审核订单", "订单记录表-" + trxorder.getId(), beforeRecord, afterRecord);
                if (record != null) {
                    sysUserOptRecordService.addRecord(record);
                }


                // 更改优惠券信息
                if (trxorder.getCouponCodeId() > 0) {// 订单使用了优惠券
                    CouponCodeDTO couponCodeDTO = couponCodeService.getCouponCodeDTO(trxorder.getCouponCodeId());
                    couponService.updateCouponUserNum(couponCodeDTO.getCouponId());// 更新优惠券使用数
                    if (couponCodeDTO.getUseType() == 2) {// 非无限次使用权的优惠券编码才更新状态
                        CouponCode couponCode = new CouponCode();
                        couponCode.setPayTime(new Date());
                        couponCode.setStatus(2);// 已使用
                        couponCode.setTrxorderId(trxorder.getId());// 订单id
                        couponCode.setRequestId(trxorder.getRequestId());// 订单请求号
                        couponCode.setUserId(trxorder.getUserId());// 用户id
                        couponCode.setUseTime(new Date());// 使用时间
                        couponCode.setId(trxorder.getCouponCodeId());
                        couponCodeService.updateCouponCode(couponCode);// 更新优惠券编码使用后的信息
                    }

                }

                List<TrxorderDetail> trxDetailList = new ArrayList<TrxorderDetail>();
                TrxorderDetail queryorder = new TrxorderDetail();
                queryorder.setRequestId(trxorder.getRequestId());
                // 记录订单操作记录
                TrxorderOptRecord opt = new TrxorderOptRecord();
                opt.setCreatetime(date);
                opt.setDesc("订单编号" + trxorder.getRequestId() + "审核");

                opt.setOptuser(SingletonLoginUtils.getLoginUserId(request));
                opt.setOptusername(this.getSysLoginLoginName(request));
                opt.setOrderId(trxorder.getId());
                opt.setType("审核");
                trxorderOptRecordService.addTrxorderOptRecord(opt);
                // 获取订单下的流水列表
                trxDetailList = trxorderDetailService.getTrxorderDetailList(queryorder);

                if (trxDetailList != null && trxDetailList.size() > 0) {
                    for (TrxorderDetail orderDetail : trxDetailList) {
                        orderDetail.setPayTime(trxorder.getPayTime());
                        orderDetail.setAuthStatus(AuthStatus.SUCCESS.toString());
                        orderDetail.setTrxStatus(trxorder.getTrxStatus());
                        trxorderDetailService.updateTrxorderDetail(orderDetail); // 修改流水状态
                        // 记录流水操作记录
                        TrxdetailOptRecord detailOpt = new TrxdetailOptRecord();
                        detailOpt.setCreateTime(date);
                        detailOpt.setDesc("流水号" + orderDetail.getId() + "已经审核");
                        detailOpt.setOptuser(SingletonLoginUtils.getLoginUserId(request));
                        detailOpt.setOptusername(getSysLoginLoginName(request));
                        detailOpt.setTrxorderDetailId(orderDetail.getId());
                        detailOpt.setTrxorderId(trxorder.getId());
                        detailOpt.setType("审核");
                        trxdetailOptRecordService.addTrxdetailOptRecord(detailOpt);
                        // 记录用户总操作记录
                        Map<String, Object> descMap = new HashMap<String, Object>();
                        descMap.put(orderDetail.getId() + "", "流水号");
                        descMap.put("type", "审核");
                        descMap.put("desc", detailOpt.getDesc());
                        descMap.put("courseId", orderDetail.getCourseId());
                        descMap.put("courseName", orderDetail.getCourseName());
                        userService.addUserOptRecord(orderDetail.getUserId(), UserOptType.AUDIT.toString(), SingletonLoginUtils.getLoginUserId(request), this.getSysLoginLoginName(request), orderDetail.getId(), gson.toJson(descMap));
                    }
                    json = this.getJsonMap(true, "审核成功", null);
                }
            }
        } catch (Exception e) {
            logger.error("verifyOrder.error", e);
            json = this.getJsonMap(false, "请求出错", null);
        }
        return json;
    }

    /**
     * 查看流水 根据订单id
     *
     * @param requestId
     */
    @RequestMapping("/order/orderDetailList")
    public ModelAndView queryOrderDetailListByCondition(@ModelAttribute("page") PageEntity page, HttpServletRequest request, HttpServletResponse response, @ModelAttribute("trxorderDetail") QueryTrxorderDetail trxorderDetail) {
        ModelAndView model = new ModelAndView();
        model.setViewName(orerDetailList);
        try {

            page.setPageSize(10);
            List<QueryTrxorderDetail> orderDetailList = trxorderDetailService.queryTrxorderDetailByOrder(trxorderDetail, page);
            model.addObject("orderDetailList", orderDetailList);
            model.addObject("trxorderDetail", trxorderDetail);
            model.addObject("page", page);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("queryOrderDetailListByCondition.error---", e);
            return new ModelAndView(this.setExceptionRequest(request, e));
        }
        return model;
    }

    @RequestMapping("/orderDetails/export")
    public void exportOrderDetails(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("trxorderDetail") QueryTrxorderDetail trxorderDetail) {
        try {
            // 指定文件生成路径
            String dir = request.getSession().getServletContext().getRealPath("/excelfile//orderDetail");
            // 文件名
            String expName = "流水信息_" + DateUtils.getStringDateShort();
            // 表头信息
            String[] headName = {"ID", "昵称", "订单过期时间", "课程过期时间", "支付确认时间", "原始价格", "销售价格", "课程", "订单号", "课程状态", "订单状态"};
            // 拆分为一万条数据每Excel，防止内存使用太大
            PageEntity page = new PageEntity();
            page.setPageSize(10000);
            trxorderDetailService.queryTrxorderDetailByOrder(trxorderDetail, page);
            int num = page.getTotalPageSize();// 总页数
            List<File> srcfile = new ArrayList<File>();// 生成的excel的文件的list
            for (int i = 1; i <= num; i++) {// 循环生成num个xls文件
                page.setCurrentPage(i);
                List<QueryTrxorderDetail> orderDetailList = trxorderDetailService.queryTrxorderDetailByOrder(trxorderDetail, page);
                List<List<String>> list = orderDetailJoint(orderDetailList);
                File file = FileExportImportUtil.createExcel(headName, list, expName + "_" + i, dir);
                srcfile.add(file);
            }
            FileExportImportUtil.createRar(response, dir, srcfile, expName);// 生成的多excel的压缩包
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 订单流水excel格式拼接
     *
     * @param couponCodes
     * @return
     */
    public List<List<String>> orderDetailJoint(List<QueryTrxorderDetail> orderDetailList) {
        List<List<String>> list = new ArrayList<List<String>>();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (int i = 0; i < orderDetailList.size(); i++) {
            List<String> small = new ArrayList<String>();
            small.add(orderDetailList.get(i).getId() + "");
            small.add(orderDetailList.get(i).getUserName());
            if (orderDetailList.get(i).getLoseAbsTime() != null) {
                small.add(format.format(orderDetailList.get(i).getLoseAbsTime()));
            } else {
                small.add("");
            }
            if (orderDetailList.get(i).getAuthTime() != null) {
                small.add(format.format(orderDetailList.get(i).getAuthTime()));
            } else {
                small.add("");
            }
            if (orderDetailList.get(i).getPayTime() != null) {
                small.add(format.format(orderDetailList.get(i).getPayTime()));
            } else {
                small.add("");
            }
            small.add(orderDetailList.get(i).getSourcePrice() + "");
            small.add(orderDetailList.get(i).getCurrentPrice() + "");
            small.add(orderDetailList.get(i).getCourseName());
            small.add(orderDetailList.get(i).getRequestId());
            if (orderDetailList.get(i).getAuthStatus().equals("INIT")) {
                small.add("未付款");
            } else if (orderDetailList.get(i).getAuthStatus().equals("SUCCESS")) {
                small.add("支付成功");
            } else if (orderDetailList.get(i).getAuthStatus().equals("REFUND")) {
                small.add("已退款");
            } else if (orderDetailList.get(i).getAuthStatus().equals("CLOSED")) {
                small.add("已关闭");
            } else if (orderDetailList.get(i).getAuthStatus().equals("LOSED")) {
                small.add("已过期");
            }

            if (orderDetailList.get(i).getTrxStatus().equals("INIT")) {
                small.add("未付款");
            } else if (orderDetailList.get(i).getTrxStatus().equals("SUCCESS")) {
                small.add("已付款");
            } else if (orderDetailList.get(i).getTrxStatus().equals("REFUND")) {
                small.add("已退款");
            } else if (orderDetailList.get(i).getTrxStatus().equals("CLOSED")) {
                small.add("已关闭");
            } else if (orderDetailList.get(i).getTrxStatus().equals("CANCEL")) {
                small.add("已取消");
            }
            list.add(small);
        }
        return list;
    }

    /**
     * 退费 ：修改订单和流水状态
     *
     * @param 订单 trxorder
     * @version 2014-09-28
     */
    @RequestMapping("/order/refund")
    @ResponseBody
    public Map<String, Object> refundOrder(HttpServletRequest request, @ModelAttribute() Trxorder trxorder) {
        Map<String, Object> json = null;
        try {
            if (trxorder != null) {
                trxorder = trxorderService.getTrxorderById(trxorder.getId());
                if (trxorder.getTrxStatus().equals(TrxOrderStatus.SUCCESS.toString())) {

                }
                trxorder.setTrxStatus(TrxOrderStatus.REFUND.toString());
                Trxorder beforeRecord = trxorderService.getTrxorderById(trxorder.getId());
                trxorderService.updateTrxorder(trxorder);
                Trxorder afterRecord = trxorderService.getTrxorderById(trxorder.getId());
                SysUserOptRecord record = new SysUserOptRecord(request, "退费", "订单记录表-" + trxorder.getId(), beforeRecord, afterRecord);
                if (record != null) sysUserOptRecordService.addRecord(record);
                // 记录订单操作记录
                TrxorderOptRecord opt = new TrxorderOptRecord();
                Date date = new Date();
                opt.setCreatetime(date);
                opt.setDesc("订单号" + trxorder.getRequestId() + "已退费");

                opt.setOptuser(SingletonLoginUtils.getLoginUserId(request));
                opt.setOptusername(this.getSysLoginLoginName(request));
                opt.setOrderId(trxorder.getId());
                opt.setType("退费");
                trxorderOptRecordService.addTrxorderOptRecord(opt);
                TrxorderDetail queryorder = new TrxorderDetail();
                queryorder.setRequestId(trxorder.getRequestId());
                // 获取订单下的流水列表
                List<TrxorderDetail> trxDetailList = trxorderDetailService.getTrxorderDetailList(queryorder);
                if (trxDetailList != null && trxDetailList.size() > 0) {
                    for (TrxorderDetail orderDetail : trxDetailList) {
                        orderDetail.setTrxStatus(trxorder.getTrxStatus());
                        orderDetail.setAuthStatus(AuthStatus.REFUND.toString());
                        orderDetail.setDescription("已退费");
                        orderDetail.setLastUpdateTime(date);
                        trxorderDetailService.updateTrxorderDetail(orderDetail);
                        cacheKit.remove(MemConstans.USER_CANLOOK + "_" + orderDetail.getCourseId() + "_" + orderDetail.getUserId());
                        // 记录流水操作记录
                        TrxdetailOptRecord detailOpt = new TrxdetailOptRecord();
                        detailOpt.setCreateTime(date);
                        detailOpt.setDesc("流水号" + orderDetail.getId() + "已经退费");
                        detailOpt.setOptuser(SingletonLoginUtils.getLoginUserId(request));
                        detailOpt.setOptusername(getSysLoginLoginName(request));
                        detailOpt.setTrxorderDetailId(orderDetail.getId());
                        detailOpt.setTrxorderId(trxorder.getId());
                        detailOpt.setType("退费");
                        trxdetailOptRecordService.addTrxdetailOptRecord(detailOpt);
                        // 记录用户总操作记录
                        Map<String, Object> descMap = new HashMap<String, Object>();
                        descMap.put(trxorder.getId() + "", "订单号" + trxorder.getId());
                        descMap.put(AuthStatus.REFUND.toString(), "退费");
                        descMap.put("desc", detailOpt.getDesc());
                        descMap.put("bizId", "流水id");
                        userService.addUserOptRecord(trxorder.getUserId(), UserOptType.REFUND.toString(), SingletonLoginUtils.getLoginUserId(request), this.getSysLoginLoginName(request), orderDetail.getId(), gson.toJson(descMap));
                    }
                    UserAccount userAccount = userAccountService.getUserAccountByUserId(trxorder.getUserId());
                    String out_no = guidGeneratorService.gainCode("REFUND", true);
                    userAccountService.credit(userAccount, trxorder.getAmount(), AccountType.VM, AccountHistoryType.REFUND, trxorder.getUserId(), trxorder.getId(), trxorder.getRequestId(), out_no, new Date(), "", true, AccountBizType.COURSE);
                    json = this.getJsonMap(true, "退费成功", null);
                } else {
                    json = this.getJsonMap(false, "流水不能为空", null);
                }

            } else {
                json = this.getJsonMap(false, "订单参数为空", null);
            }
        } catch (Exception e) {
            logger.error("refundOrder.error-----", e);
            json = this.getJsonMap(false, "请求错误", null);
        }
        return json;
    }

    /**
     * 到流水详情页面
     *
     * @param detailId
     * @version 2014-09-28
     */
    @RequestMapping("/order/detailinfo")
    @ResponseBody
    public ModelAndView orderDetailInfo(HttpServletRequest request, @ModelAttribute("detailId") Long detailId, @ModelAttribute() QueryTrxorderDetail queryTrxorderDetail) {
        ModelAndView model = new ModelAndView();
        try {
            if (detailId != null && !"".equals(detailId)) {
                model.setViewName(orderDetailInfo);
                queryTrxorderDetail = trxorderDetailService.queryQueryTrxorderDetailById(detailId);
                model.addObject("queryTrxorderDetail", queryTrxorderDetail);
            }
        } catch (Exception e) {
            logger.error("delayCourse.error---", e);
            return new ModelAndView(this.setExceptionRequest(request, e));
        }
        return model;
    }

    /**
     * 课程延期:只有订单状态为SUCCESS 并且 流水状态为LOSED 才可以进行延期操作
     *
     * @param 流水 :TrxorderDetail
     * @Date 2014-09-28
     */
    @RequestMapping("/order/delayorder")
    @ResponseBody
    public Map<String, Object> delayCourseOrder(@ModelAttribute() TrxorderDetail trxorderDetail, HttpServletRequest request) {
        Map<String, Object> json = null;
        try {
            if (trxorderDetail != null) {
                Date d = new Date();
                Date delaytime = trxorderDetail.getAuthTime();
                TrxorderDetail orderdetail = new TrxorderDetail();
                orderdetail = trxorderDetailService.getTrxorderDetailById(trxorderDetail.getId());
                orderdetail.setAuthStatus(AuthStatus.SUCCESS.toString());
                orderdetail.setAuthTime(delaytime);
                orderdetail.setLastUpdateTime(d);
                orderdetail.setDescription("课程延期");

                TrxorderDetail beforeRecord = trxorderDetailService.getTrxorderDetailById(trxorderDetail.getId());
                trxorderDetailService.updateTrxorderDetail(orderdetail);
                TrxorderDetail afterRecord = trxorderDetailService.getTrxorderDetailById(trxorderDetail.getId());
                SysUserOptRecord record = new SysUserOptRecord(request, "课程延期", "订单流水记录表-" + trxorderDetail.getId(), beforeRecord, afterRecord);
                if (record != null) sysUserOptRecordService.addRecord(record);

                // 记录流水操作记录
                TrxdetailOptRecord detailOpt = new TrxdetailOptRecord();
                detailOpt.setCreateTime(d);
                detailOpt.setDesc("流水号" + orderdetail.getId() + "课程延期");
                detailOpt.setOptuser(SingletonLoginUtils.getLoginUserId(request));
                detailOpt.setOptusername(getSysLoginLoginName(request));
                detailOpt.setTrxorderDetailId(orderdetail.getId());
                detailOpt.setTrxorderId(orderdetail.getTrxorderId());
                detailOpt.setType("延期");
                trxdetailOptRecordService.addTrxdetailOptRecord(detailOpt);
                // 记录用户总操作记录
                Map<String, Object> descMap = new HashMap<String, Object>();
                descMap.put(orderdetail.getId() + "", "流水号");
                descMap.put("type", "延期");
                descMap.put("desc", detailOpt.getDesc());
                descMap.put("courseId", orderdetail.getCourseId());
                descMap.put("courseName", orderdetail.getCourseName());
                descMap.put("bizId", "流水id");
                userService.addUserOptRecord(orderdetail.getUserId(), UserOptType.DEPAL.toString(), SingletonLoginUtils.getLoginUserId(request), getSysLoginLoginName(request), orderdetail.getId(), gson.toJson(descMap));
                json = this.getJsonMap(true, "延期成功", orderdetail);
            } else {
                json = this.getJsonMap(false, "参数不能为空", null);
            }
        } catch (Exception e) {
            logger.error("delayCourseOrder.error----", e);
            json = this.getJsonMap(false, "请求错误", null);
        }
        return json;
    }

    /**
     * 关闭课程：更改状态为closed
     *
     * @param detailId
     */
    @RequestMapping("/order/closeCourse")
    public ModelAndView closeCourse(@ModelAttribute("detailId") Long detailId, HttpServletRequest request) {
        ModelAndView model = new ModelAndView();
        try {
            model.setViewName(redirectDetailList);
            if (detailId != null && !"".equals(detailId)) {
                Date d = new Date();
                TrxorderDetail trxorderDetail = trxorderDetailService.getTrxorderDetailById(detailId);
                trxorderDetail.setAuthStatus(AuthStatus.CLOSED.toString());
                trxorderDetail.setDescription("关闭课程");
                trxorderDetail.setLastUpdateTime(d);

                TrxorderDetail beforeRecord = trxorderDetailService.getTrxorderDetailById(detailId);
                trxorderDetailService.updateTrxorderDetail(trxorderDetail);
                TrxorderDetail afterRecord = trxorderDetailService.getTrxorderDetailById(detailId);
                SysUserOptRecord record = new SysUserOptRecord(request, "关闭课程", "订单流水记录表-" + detailId, beforeRecord, afterRecord);
                if (record != null) {
                    sysUserOptRecordService.addRecord(record);
                }
                // 记录流水操作记录
                TrxdetailOptRecord detailOpt = new TrxdetailOptRecord();
                detailOpt.setCreateTime(d);
                detailOpt.setDesc("流水号" + trxorderDetail.getId() + "关闭课程");
                detailOpt.setOptuser(SingletonLoginUtils.getLoginUserId(request));
                detailOpt.setOptusername(this.getSysLoginLoginName(request));
                detailOpt.setTrxorderDetailId(trxorderDetail.getId());
                detailOpt.setTrxorderId(trxorderDetail.getTrxorderId());
                detailOpt.setType("关闭课程");
                trxdetailOptRecordService.addTrxdetailOptRecord(detailOpt);
                // 记录用户总操作记录
                Map<String, Object> descMap = new HashMap<String, Object>();
                descMap.put(trxorderDetail.getId() + "", "流水号");
                descMap.put("type", "关闭课程");
                descMap.put("desc", detailOpt.getDesc());
                descMap.put("courseId", trxorderDetail.getCourseId());
                descMap.put("courseName", trxorderDetail.getCourseName());
                descMap.put("bizId", "流水id");
                userService.addUserOptRecord(trxorderDetail.getUserId(), UserOptType.CLOSED.toString(), SingletonLoginUtils.getLoginUserId(request), this.getSysLoginLoginName(request), trxorderDetail.getId(), gson.toJson(descMap));
            }

        } catch (Exception e) {
            logger.error("closeCourse.error---", e);
            return new ModelAndView(this.setExceptionRequest(request, e));
        }
        return model;
    }

    /**
     * 换课
     *
     * @param orderDetailId courseId courseName
     */
    @RequestMapping("/order/changgecourse")
    @ResponseBody
    public Map<String, Object> changeCourse(HttpServletRequest request, HttpServletResponse response, @ModelAttribute TrxorderDetail trxorderDetail) {
        Map<String, Object> json = null;
        try {
            if (ObjectUtils.isNotNull(trxorderDetail) && trxorderDetail.getId() != 0) {
                TrxorderDetail trxorderDetail1 = new TrxorderDetail();
                trxorderDetail1 = trxorderDetailService.getTrxorderDetailById(trxorderDetail.getId());
                trxorderDetail1.setCourseId(trxorderDetail.getCourseId());
                trxorderDetail1.setCourseName(trxorderDetail.getCourseName());
                trxorderDetail1.setLastUpdateTime(new Date());
                TrxorderDetail beforeRecord = trxorderDetailService.getTrxorderDetailById(trxorderDetail.getId());
                trxorderDetailService.updateTrxorderDetail(trxorderDetail1);
                TrxorderDetail aftereRecord = trxorderDetailService.getTrxorderDetailById(trxorderDetail.getId());
                SysUserOptRecord record = new SysUserOptRecord(request, "更换课程", "订单流水记录表-" + trxorderDetail.getId(), beforeRecord, aftereRecord);
                if (record != null)
                    sysUserOptRecordService.addRecord(record);
                json = this.getJsonMap(true, "修改成功", trxorderDetail);
                JsonObject sysuser = SingletonLoginUtils.getLoginUser(request);
                // 记录用户总操作记录
                Map<String, Object> descMap = new HashMap<String, Object>();
                descMap.put(trxorderDetail.getId() + "", "流水号");
                descMap.put("type", "换课");
                descMap.put("desc", "操作_换课");
                descMap.put("courseId", trxorderDetail.getCourseId());
                descMap.put("courseName", trxorderDetail.getCourseName());
                descMap.put("bizId", "流水id");
                userService.addUserOptRecord(trxorderDetail.getUserId(), UserOptType.CHANGECOURSE.toString(), sysuser.get("id").getAsLong(), getSysLoginLoginName(request), trxorderDetail.getId(), gson.toJson(descMap));
            } else {
                json = this.getJsonMap(false, "订单号不能为空", null);
            }

        } catch (Exception e) {
            logger.error("changeCourse.error---", e);
            json = this.getJsonMap(false, "请求错误", null);
        }
        return json;
    }

    /**
     * 换课 选着课程
     */

    @RequestMapping("/order/courselist")
    public String showCourseList(HttpServletRequest request, @ModelAttribute("page") PageEntity page, @ModelAttribute("queryCourse") QueryCourse queryCourse) {
        try {
            // 页面传来的数据放到page中

            page.setPageSize(12);
            // 添加时间倒叙
            queryCourse.setOrder(2);
            // 搜索课程列表
            List<CourseDto> courseList = courseService.getCourseListPage(queryCourse, page);
            request.setAttribute("courseList", courseList);
            request.setAttribute("page", page);
            request.setAttribute("course", queryCourse);
            // 获得一级项目
            QuerySubject querySubject = new QuerySubject();
            List<Subject> subjectList = subjectService.getSubjectList(querySubject);
            request.setAttribute("subjectList", gson.toJson(subjectList));
        } catch (Exception e) {
            logger.error("AdminTrxorderController.showCourseList", e);
            return setExceptionRequest(request, e);
        }
        return showCourseList;
    }

    /**
     * 获取订单操作记录
     *
     * @param trxorderOptRecord
     * @param model
     * @return
     */
    @RequestMapping("/order/getOrderOptRecordList")
    public String getOrderOptRecordList(@ModelAttribute("trxorderOptRecord") TrxorderOptRecord trxorderOptRecord, Model model, @ModelAttribute("page") PageEntity page) {
        try {
            page.setPageSize(12);
            List<TrxorderOptRecord> trxorderOptRecordList = trxorderOptRecordService.getTrxorderOptRecordListPage(trxorderOptRecord, page);
            model.addAttribute("trxorderOptRecordList", trxorderOptRecordList);
            model.addAttribute("page", page);
            model.addAttribute("trxorderOptRecord", trxorderOptRecord);
        } catch (Exception e) {
            logger.error("AdminTrxorderController.getOrderOptRecordList", e);
        }
        return orderOptRecordList;
    }

    /**
     * 获取订单流水操作记录
     *
     * @param trxdetailOptRecord
     * @param model
     * @return
     */
    @RequestMapping("/order/getOrderDetailOptRecordList")
    public String getOrderDetailOptRecordList(@ModelAttribute("trxdetailOptRecord") TrxdetailOptRecord trxdetailOptRecord, Model model, @ModelAttribute("page") PageEntity page) {
        try {
            page.setPageSize(12);
            List<TrxdetailOptRecord> trxdetailOptRecordList = trxdetailOptRecordService.getTrxdetailOptRecordListPage(trxdetailOptRecord, page);
            model.addAttribute("trxdetailOptRecordList", trxdetailOptRecordList);
            model.addAttribute("page", page);
            model.addAttribute("trxdetailOptRecord", trxdetailOptRecord);
        } catch (Exception e) {
            logger.error("AdminTrxorderController.getOrderDetailOptRecordList", e);
        }
        return orderDetailOptRecordList;
    }
}
