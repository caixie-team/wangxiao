package io.wangxiao.edu.home.controller.member;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.commons.util.DateUtils;
import io.wangxiao.commons.util.ObjectUtils;
import io.wangxiao.edu.common.contoller.SingletonLoginUtils;
import io.wangxiao.edu.common.util.FileExportImportUtil;
import io.wangxiao.edu.home.common.EduBaseController;
import io.wangxiao.edu.home.constants.enums.TrxOrderStatus;
import io.wangxiao.edu.home.entity.member.*;
import io.wangxiao.edu.home.entity.user.User;
import io.wangxiao.edu.home.service.member.MemberOrderService;
import io.wangxiao.edu.home.service.member.MemberRecordService;
import io.wangxiao.edu.home.service.member.MemberTypeService;
import org.apache.commons.httpclient.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminMemberOrderController extends EduBaseController {
    private static final Logger logger = LoggerFactory.getLogger(AdminMemberOrderController.class);
    @Autowired
    private MemberOrderService memberOrderService;
    @Autowired
    private MemberRecordService memberRecordService;
    @Autowired
    private MemberTypeService memberTypeService;

    private static final String getMemberRecords = getViewPath("/admin/member/member_record_list");
    private static final String getMemberOrders = getViewPath("/admin/member/member_order_list");
    private static final String memberOrderDetail = getViewPath("/admin/member/member_order_detail");
    private static final String getMemberRecord = getViewPath("/admin/member/member_order_opt_info");// 返回到开通详情页

    // 创建群 绑定变量名字和属性，把参数封装到类

    @InitBinder("memberRecord")
    public void initBinderMemberRecord(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("memberRecord.");
    }

    @InitBinder("memberOrder")
    public void initBinderMemberOrder(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("memberOrder.");
    }

    @InitBinder("memberRecordDTO")
    public void initBinderMemverRecordDTO(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("memberRecordDTO.");
    }

    @InitBinder("queryMemberOrder")
    public void initBinderQueryMemberOrder(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("queryMemberOrder.");
    }

    /**
     * 会员记录列表
     *
     * @return
     */
    @RequestMapping("/memberrecord/memberrecords")
    public ModelAndView getMemberRecords(MemberRecordDTO memberRecordDTO, @ModelAttribute("page") PageEntity page, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(getMemberRecords);
        try {

            page.setPageSize(10);
            List<MemberRecordDTO> memberRecordDTOs = memberRecordService.getMemberRecordPage(memberRecordDTO, page);
            modelAndView.addObject("memberRecordDTOs", memberRecordDTOs);
            modelAndView.addObject("page", page);
            List<MemberType> memberTypes = memberTypeService.getWebMemberTypes();
            modelAndView.addObject("memberTypes", memberTypes);
        } catch (Exception e) {
            logger.error("AdminMemberController.getMemberRecords--会员记录列表出错", e);
            return new ModelAndView(setExceptionRequest(request, e));
        }
        return modelAndView;
    }

    /**
     * 会员订单列表
     *
     * @return
     */
    @RequestMapping("/memberorder/memberorders")
    public ModelAndView getMemberOrders(QueryMemberOrder queryMemberOrder, @ModelAttribute("page") PageEntity page, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(getMemberOrders);
        try {

            page.setPageSize(10);
            List<MemberOrderDTO> memberOrderDTOs = memberOrderService.getMemberOrderPage(queryMemberOrder, page);
            modelAndView.addObject("memberOrderDTOs", memberOrderDTOs);
            modelAndView.addObject("page", page);
            List<MemberType> memberTypes = memberTypeService.getWebMemberTypes();
            modelAndView.addObject("memberTypes", memberTypes);
        } catch (Exception e) {
            logger.error("AdminMemberController.getMemberOrders--会员订单列表出错", e);
            return new ModelAndView(setExceptionRequest(request, e));
        }
        return modelAndView;
    }

    /**
     * 会员订单详情
     *
     * @return
     */
    @RequestMapping("/member/detail/{id}")
    public ModelAndView getMemberOrderDetail(@PathVariable Long id, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(memberOrderDetail);
        try {
            MemberOrderDTO memberOrderDTO = memberOrderService.getMemberOrderDTOById(id);
            modelAndView.addObject("memberOrderDTO", memberOrderDTO);
            List<MemberType> memberTypes = memberTypeService.getWebMemberTypes();
            modelAndView.addObject("memberTypes", memberTypes);
        } catch (Exception e) {
            logger.error("AdminMemberController.getMemberOrderDetail--会员订单详情出错", e);
            return new ModelAndView(setExceptionRequest(request, e));
        }
        return modelAndView;
    }

    /**
     * 获得会员开通记录详情
     *
     * @param request
     * @param model
     * @param id
     * @return
     */
    @RequestMapping("/member/mrecordinfo/{id}")
    public String getMemberRecord(HttpServletRequest request, Model model, @PathVariable Long id) {
        try {
            // 查询开通记录详情
            MemberRecordDTO memberRecordDTO = memberRecordService.getMemberRecordInfo(id);
            model.addAttribute("memberRecordDTO", memberRecordDTO);
        } catch (Exception e) {
            logger.error("getMemberRecord", e);
            return setExceptionRequest(request, e);
        }
        return getMemberRecord;
    }

    /**
     * 延期操作
     *
     * @param request
     * @param memberRecordDTO
     * @return
     */
    @RequestMapping("/member/updatemrecord")
    @ResponseBody
    public Map<String, Object> updateMemberRecordEndDate(HttpServletRequest request, @ModelAttribute MemberRecordDTO memberRecordDTO) {
        Map<String, Object> json = null;
        try {
            if (ObjectUtils.isNotNull(memberRecordDTO) && memberRecordDTO.getId() != 0) {
                // 获得详细信息
                MemberRecord memberRecord = memberRecordService.getMemberRecordById(memberRecordDTO.getId());
                long dVlaue = memberRecord.getEndDate().getTime() - memberRecordDTO.getEndDate().getTime();
                if (dVlaue < 0) {
                    // set 过期时间
                    memberRecord.setEndDate(memberRecordDTO.getEndDate());
                    User user = new User();
                    user.setId(SingletonLoginUtils.getLoginUserId(request));
                    user.setNickname(getSysLoginLoginName(request));
                    memberRecordService.updateMemberRecordEndDate(memberRecord, user);
                    json = this.getJsonMap(true, "修改成功", null);
                    return json;
                } else {
                    json = this.getJsonMap(false, "设置过期时间错误", null);
                    return json;
                }
            } else {
                json = this.getJsonMap(false, "请求数据错误", null);
            }
        } catch (Exception e) {
            logger.error("updateMemberRecordEndDate", e);
        }
        return json;
    }

    /**
     * 导出会员订单
     */
    @RequestMapping("/memberorder/export")
    public void exportMemberOrder(QueryMemberOrder queryMemberOrder, HttpServletRequest request, HttpServletResponse response) {
        try {
            // 指定文件生成路径
            String dir = request.getSession().getServletContext().getRealPath("/excelfile/memberOrder");
            // 文件名
            String expName = "会员订单_" + DateUtils.getStringDateShort();
            // 表头信息
            String[] headName = {"ID", "订单编号 ", "用户email", "原始金额", "优惠金额", "实付金额", "下单时间", "支付时间", "会员类型", "开通天数", "支付状态", "支付类型 "};
            // 拆分为一万条数据每Excel，防止内存使用太大
            PageEntity page = new PageEntity();
            page.setPageSize(10000);
            memberOrderService.getMemberOrderPage(queryMemberOrder, page);
            int num = page.getTotalPageSize();// 总页数
            List<File> srcfile = new ArrayList<File>();// 生成的excel的文件的list
            for (int i = 1; i <= num; i++) {// 循环生成num个xls文件
                page.setCurrentPage(i);
                List<MemberOrderDTO> memberOrderDTOs = memberOrderService.getMemberOrderPage(queryMemberOrder, page);
                List<List<String>> list = memberOrderJoint(memberOrderDTOs);
                File file = FileExportImportUtil.createExcel(headName, list, expName + "_" + i, dir);
                srcfile.add(file);
            }
            FileExportImportUtil.createRar(response, dir, srcfile, expName);// 生成的多excel的压缩包
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 会员订单excel格式拼接
     *
     * @param MemberOrders
     * @return
     */
    public List<List<String>> memberOrderJoint(List<MemberOrderDTO> memberOrderDTOs) {
        List<List<String>> list = new ArrayList<List<String>>();
        for (int i = 0; i < memberOrderDTOs.size(); i++) {
            MemberOrderDTO memberOrderDTO = memberOrderDTOs.get(i);
            List<String> small = new ArrayList<String>();
            small.add(memberOrderDTO.getId().toString());
            small.add(memberOrderDTO.getRequestId());
            small.add(memberOrderDTO.getEmail());
            small.add(memberOrderDTO.getOrderAmount().toString());
            small.add(memberOrderDTO.getCouponAmount().toString());
            small.add(memberOrderDTO.getAmount().toString());
            small.add(DateUtil.formatDate(memberOrderDTO.getCreateTime(), "yyyy-MM-dd HH:mm:ss"));
            if (memberOrderDTO.getPayTime() != null) {
                small.add(DateUtil.formatDate(memberOrderDTO.getPayTime(), "yyyy-MM-dd HH:mm:ss"));
            } else {
                small.add("");
            }
            small.add(memberOrderDTO.getMemberTitle());
            small.add(Integer.toString(memberOrderDTO.getMemberDays()));
            if (memberOrderDTO.getTrxStatus().equals(TrxOrderStatus.INIT.toString())) {
                small.add("未支付");
            }
            if (memberOrderDTO.getTrxStatus().equals(TrxOrderStatus.SUCCESS.toString())) {
                small.add("已支付");
            }
            if (memberOrderDTO.getPayType().equals("ALIPAY")) {
                small.add("支付宝");
            } else if (memberOrderDTO.getPayType().equals("KUAIQIAN")) {
                small.add("快钱");
            } else if (memberOrderDTO.getPayType().equals("WEIXIN")) {
                small.add("微信");
            } else if (memberOrderDTO.getPayType().equals("CARD")) {
                small.add("课程卡");
            } else if (memberOrderDTO.getPayType().equals("FREE")) {
                small.add("赠送");
            } else if (memberOrderDTO.getPayType().equals("INTEGRAL")) {
                small.add("积分");
            } else if (memberOrderDTO.getPayType().equals("ACCOUNT")) {
                small.add("账户");
            }
            list.add(small);
        }
        return list;
    }

    /**
     * 会员关闭
     *
     * @param request
     * @param memberRecord
     * @return
     */
    @RequestMapping("/member/close")
    @ResponseBody
    public Map<String, Object> updateMemberStatus(HttpServletRequest request, @ModelAttribute("memberRecord") MemberRecord memberRecord) {
        Map<String, Object> json = null;
        try {
            if (ObjectUtils.isNotNull(memberRecord) && memberRecord.getId() != 0) {
                memberRecordService.updateMemberStatus(memberRecord);
                json = this.getJsonMap(true, "修改成功", null);
                return json;
            }
            json = this.getJsonMap(false, "请求数据错误", null);
        } catch (Exception e) {
            logger.error("updateMemberStatus", e);
            json = this.getJsonMap(false, "系统错误", null);
        }
        return json;
    }
}