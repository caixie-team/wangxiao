package io.wangxiao.edu.home.controller.user;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.edu.home.common.EduBaseController;
import io.wangxiao.edu.home.entity.user.UserIntegral;
import io.wangxiao.edu.home.service.user.UserIntegralService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 用户积分
 */
@RequestMapping("/admin")
@Controller
public class AdminUserIntegralController extends EduBaseController {

    private static final Logger logger = LoggerFactory.getLogger(AdminUserIntegralController.class);

    private static final String userIntegralList = getViewPath("/admin/user/userIntegral_list");// 用户积分列表

    @Autowired
    private UserIntegralService userIntegralService;

    // 绑定变量参数
    @InitBinder("userIntegral")
    public void initBindUserIntegral(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("userIntegral.");
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
    @RequestMapping("/user/integralist")
    public String getUserIntegralList(HttpServletRequest request, Model model, @ModelAttribute UserIntegral userIntegral, @ModelAttribute("page") PageEntity page) {
        try {
            //设置分页参数

            page.setPageSize(10);
            //查询用户积分列表
            List<UserIntegral> userIntegralList = userIntegralService.getUserIntegralListPage(userIntegral, page);
            model.addAttribute("userIntegralList", userIntegralList);
            model.addAttribute("page", page);
        } catch (Exception e) {
            logger.error("getUserIntegralList", e);
            setExceptionRequest(request, e);
        }
        return userIntegralList;
    }
}
