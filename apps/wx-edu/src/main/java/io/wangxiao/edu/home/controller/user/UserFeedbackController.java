package io.wangxiao.edu.home.controller.user;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.edu.home.common.EduBaseController;
import io.wangxiao.edu.home.entity.user.UserFeedback;
import io.wangxiao.edu.home.entity.user.UserFeedbacks;
import io.wangxiao.edu.home.service.user.UserFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class UserFeedbackController extends EduBaseController {

    @Autowired
    private UserFeedbackService userFeedbackService;


    private static final String free_back = getViewPath("/front/feed_back");
    private static final String admin_back_list = getViewPath("/admin/feed/feed_list");

    // 绑定变量名字和属性，参数封装进类
    @InitBinder("userFeedback")
    public void userFeedback(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("userFeedback.");
    }

    @InitBinder("userFeedbacks")
    public void userFeedbacks(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("userFeedbacks.");
    }

    /**
     * 修改UserFeedback
     *
     * @param userFeedback 要修改的UserFeedback
     */
    public void updateUserFeedback(UserFeedback userFeedback) {
        userFeedbackService.updateUserFeedback(userFeedback);
    }

    /**
     * 用户反馈添加
     *
     * @param userFeedback
     * @return
     */
    @RequestMapping("/front/addfreeback")
    @ResponseBody
    public Map<String, Object> addUserFeedback(HttpServletRequest request, @ModelAttribute UserFeedback userFeedback) {
        Map<String, Object> json = null;
        try {
            userFeedback.setCreateTime(new Date());
            userFeedbackService.addUserFeedback(userFeedback);
            json = this.getJsonMap(true, "success", null);
        } catch (Exception e) {
            e.printStackTrace();
            json = this.getJsonMap(true, "false", null);
        }
        return json;
    }

    /**
     * 页面跳转
     *
     * @return
     */
    @RequestMapping("/front/to_free_back")
    public String toAddFreeBack() {
        return free_back;
    }

    /**
     * 用户反馈页面
     *
     * @param request
     * @param page
     * @param userFeedback
     * @return
     */
    @RequestMapping("/admin/feed/feedList")
    public ModelAndView getFeedListByCondition(HttpServletRequest request, @ModelAttribute("page") PageEntity page, @ModelAttribute("userFeedbacks") UserFeedbacks userFeedbacks) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(admin_back_list);
        try {

            List<UserFeedback> userFeedbackList = userFeedbackService.getUserFeedbackListCondtion(userFeedbacks, page);
            modelAndView.addObject("userFeedbackList", userFeedbackList);
            modelAndView.addObject("userFeedbacks", userFeedbacks);
            modelAndView.addObject("page", page);
        } catch (Exception e) {
            setExceptionRequest(request, e);
            e.printStackTrace();
        }
        return modelAndView;
    }
}