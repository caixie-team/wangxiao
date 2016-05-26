package io.wangxiao.edu.home.controller.user;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.edu.home.common.EduBaseController;
import io.wangxiao.edu.home.constants.enums.IntegralKeyword;
import io.wangxiao.edu.home.entity.user.UserIntegralRecord;
import io.wangxiao.edu.home.entity.user.UserIntegralTemplate;
import io.wangxiao.edu.home.service.user.UserIntegralRecordService;
import io.wangxiao.edu.home.service.user.UserIntegralTemplateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @description 用户积分记录
 */
@RequestMapping("/admin")
@Controller
public class AdminUserIntegralRecordController extends EduBaseController {

    private static final Logger logger = LoggerFactory.getLogger(AdminUserIntegralRecordController.class);

    private static final String userIntegralList = getViewPath("/admin/user/userIntegralRecord_list");// 用户积分列表
    private static final String exchageIntegralRecord = getViewPath("/admin/user/exchangeIntegral_record");// 积分兑换记录
    @Autowired
    private UserIntegralRecordService userIntegralRecordService;
    @Autowired
    private UserIntegralTemplateService userIntegralTemplateService;

    // 绑定变量名参数
    @InitBinder("userIntegralRecord")
    public void initBinderUserIntegralRecord(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("userIntegralRecord.");
    }

    /**
     * 查询用户积分列表
     *
     * @param request
     * @param model
     * @param userIntegral
     * @param page
     * @return
     */
    @RequestMapping("/user/integralrecordlist/{userId}")
    public String getUserIntegralList(HttpServletRequest request, Model model, @PathVariable("userId") Long userId, @ModelAttribute("page") PageEntity page) {
        try {
            // 设置分页参数

            page.setPageSize(10);
            UserIntegralRecord userIntegralRecord = new UserIntegralRecord();
            userIntegralRecord.setUserId(userId);
            // 查询用户积分历史列表
            List<UserIntegralRecord> userIntegralRecordList = userIntegralRecordService.getUserIntegralRecordListPage(userIntegralRecord, page);
            model.addAttribute("userIntegralRecordList", userIntegralRecordList);
            model.addAttribute("page", page);
            model.addAttribute("userId", userId);
        } catch (Exception e) {
            logger.error("getUserIntegralList", e);
            setExceptionRequest(request, e);
        }
        return userIntegralList;
    }

    /**
     * 积分兑换记录
     *
     * @param request
     * @param model
     * @param userIntegralRecord
     * @param page
     * @return
     */
    @RequestMapping("/user/exchange")
    public String getExchangeIntegralRecord(HttpServletRequest request, Model model, @ModelAttribute UserIntegralRecord userIntegralRecord, @ModelAttribute("page") PageEntity page) {
        try {
            // 设置分页参数

            page.setPageSize(10);
            // 查询积分模板,获得礼品的id
            UserIntegralTemplate userIntegralTemplate = userIntegralTemplateService.getUserIntegralTemplateByKeyword(IntegralKeyword.gift.toString());
            // 查询积分兑换记录
            userIntegralRecord.setIntegralType(userIntegralTemplate.getId());
            List<UserIntegralRecord> userIntegralRecordList = userIntegralRecordService.getExchangeIntegralRecord(userIntegralRecord, page);
            model.addAttribute("userIntegralRecordList", userIntegralRecordList);
            model.addAttribute("page", page);
        } catch (Exception e) {
            logger.error("getExchangeIntegralRecord", e);
        }
        return exchageIntegralRecord;
    }

    /**
     * 根据Id修改兑换状态
     *
     * @param request
     * @param id
     * @return
     */
    @RequestMapping("/user/editrecord/{id}")
    public String updateIntegralRecordStatus(HttpServletRequest request, @PathVariable("id") Long id) {
        try {
            // 修改礼品兑换状态
            userIntegralRecordService.updateIntegralRecordStatus(id);
        } catch (Exception e) {
            logger.error("updateIntegralRecordStatus", e);
        }
        return "redirect:/admin/user/exchange";
    }
}
