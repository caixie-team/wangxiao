package io.wangxiao.edu.home.controller.user;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.commons.util.ObjectUtils;
import io.wangxiao.edu.home.common.EduBaseController;
import io.wangxiao.edu.home.entity.user.UserIntegralGift;
import io.wangxiao.edu.home.service.user.UserIntegralGiftService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @description 礼品管理
 */
@RequestMapping("/admin")
@Controller
public class AdminUserIntegralGiftController extends EduBaseController {

    private static final Logger logger = LoggerFactory.getLogger(AdminUserIntegralGiftController.class);

    private static final String userIntegerGiftList = getViewPath("/admin/user/userIntegralGift_list");// 礼品管理页面
    private static final String toAddUserIntegralGift = getViewPath("/admin/user/addUserIntegralGift");// 添加礼品页面
    private static final String updateUserIntegralGift = getViewPath("/admin/user/userIntegralgift_update");// 积分礼品修改页面
    private static final String updateUserIntegralCourseGift = getViewPath("/admin/user/userIntegralgift_course_update");// 积分礼品修改页面
    @Autowired
    private UserIntegralGiftService userIntegralGiftService;

    // 绑定变量参数
    @InitBinder("userIntegralGift")
    public void initBindGift(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("userIntegralGift.");
    }

    /**
     * 跳转添加页面
     *
     * @return
     */
    @RequestMapping("/user/toaddgift")
    public String toAddUserIntegralGift() {
        return toAddUserIntegralGift;
    }

    /**
     * 添加礼品
     *
     * @param request
     * @param userIntegralGift
     * @return
     */
    @RequestMapping("/user/addgift")
    public String addUserIntegralGift(HttpServletRequest request, @ModelAttribute UserIntegralGift userIntegralGift) {
        try {
            if (ObjectUtils.isNotNull(userIntegralGift)) {
                // 添加礼品
                userIntegralGift.setCreateTime(new Date());// 初始化添加时间
                userIntegralGift.setUpdateTime(new Date());// 初始化更新时间
                userIntegralGift.setStatus(1L);// 设置状态 1正常 2删除
                userIntegralGiftService.addUserIntegralGift(userIntegralGift);
            }
        } catch (Exception e) {
            logger.error("addUserIntegralGift", e);
        }
        return "redirect:/admin/user/giftlist";
    }

    /**
     * 查询礼品列表
     *
     * @param request
     * @param model
     * @param userIntegralGift
     * @param page
     * @return
     */
    @RequestMapping("/user/giftlist")
    public String getUserIntegerGiftList(HttpServletRequest request, Model model, @ModelAttribute UserIntegralGift userIntegralGift, @ModelAttribute("page") PageEntity page) {
        try {
            // 设置分页参数

            page.setPageSize(10);
            // 查询礼品列表
            List<UserIntegralGift> userIntegralGiftList = userIntegralGiftService.getUserIntegralGiftListPage(userIntegralGift, page);
            model.addAttribute("userIntegralGiftList", userIntegralGiftList);
            model.addAttribute("page", page);
        } catch (Exception e) {
            logger.error("AdminUserIntegralGiftController.getUserIntegerGiftList", e);
            return setExceptionRequest(request, e);
        }
        return userIntegerGiftList;
    }

    /**
     * 查询详情 跳转到更新页面
     *
     * @param request
     * @param model
     * @param id
     * @return
     */
    @RequestMapping("/user/toupdategift/{id}")
    public String getUserIntegerGift(HttpServletRequest request, Model model, @PathVariable("id") Long id) {
        String returnUrl = "";
        try {
            // 查询详情
            UserIntegralGift userIntegralGift = userIntegralGiftService.getUserIntegralGiftById(id);
            if (ObjectUtils.isNotNull(userIntegralGift)) {
                if (userIntegralGift.getCourseId() != 0) {// 跳转不同的页面 课程礼品页面
                    returnUrl = updateUserIntegralCourseGift;// 根据CourseId 判断
                } else {
                    returnUrl = updateUserIntegralGift;// 积分礼品
                }
            }
            model.addAttribute("userIntegralGift", userIntegralGift);
        } catch (Exception e) {
            logger.error("getUserIntegerGift", e);
            return setExceptionRequest(request, e);
        }
        return returnUrl;
    }

    /**
     * 更新礼品
     *
     * @param request
     * @param userIntegralGift
     * @return
     */
    @RequestMapping("/user/updategift")
    public String updateUserIntegerGift(HttpServletRequest request, @ModelAttribute UserIntegralGift userIntegralGift) {
        try {
            if (ObjectUtils.isNotNull(userIntegralGift)) {
                userIntegralGift.setUpdateTime(new Date());// 更新时间
                // 更新礼品
                userIntegralGiftService.updateUserIntegralGift(userIntegralGift);
            }
        } catch (Exception e) {
            logger.error("updateUserIntegerGift", e);
            setExceptionRequest(request, e);
        }
        return "redirect:/admin/user/giftlist";
    }

    /**
     * 删除礼品
     *
     * @param request
     * @param id
     * @return
     */
    @RequestMapping("/user/deletegift")
    @ResponseBody
    public Map<String, Object> deleteUserIntegerGift(HttpServletRequest request, @RequestParam("id") Long id) {
        Map<String, Object> json = null;
        try {
            userIntegralGiftService.deleteUserIntegralGiftById(id);
            json = this.getJsonMap(true, "删除成功", null);
        } catch (Exception e) {
            logger.error("deleteUserIntegerGift", e);
            json = this.getJsonMap(false, "系统繁忙，稍后重试", null);
        }
        return json;
    }
}
