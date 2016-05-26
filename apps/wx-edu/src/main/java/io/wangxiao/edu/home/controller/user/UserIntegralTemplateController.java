package io.wangxiao.edu.home.controller.user;


import io.wangxiao.edu.home.common.EduBaseController;
import io.wangxiao.edu.home.entity.user.UserIntegralTemplate;
import io.wangxiao.edu.home.service.user.UserIntegralTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * UserIntegralTemplate管理接口
 */
@Controller
public class UserIntegralTemplateController extends EduBaseController {

    @Autowired
    private UserIntegralTemplateService userIntegralTemplateService;


    /**
     * 修改UserIntegralTemplate
     *
     * @param userIntegralTemplate 要修改的UserIntegralTemplate
     */
    public void updateUserIntegralTemplate(UserIntegralTemplate userIntegralTemplate) {
        userIntegralTemplateService.updateUserIntegralTemplate(userIntegralTemplate);
    }


}