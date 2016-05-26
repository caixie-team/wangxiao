package com.atdld.os.edu.controller.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.atdld.os.edu.service.user.UserIntegralTemplateService;
import com.atdld.os.edu.common.EduBaseController;
import com.atdld.os.edu.entity.user.UserIntegralTemplate;
/**
 * UserIntegralTemplate管理接口
 * User:
 * Date: 2014-05-27
 */
@Controller
public class UserIntegralTemplateController extends EduBaseController{

 	@Autowired
    private UserIntegralTemplateService userIntegralTemplateService;
    
    
    
    /**
     * 修改UserIntegralTemplate
     * @param userIntegralTemplate 要修改的UserIntegralTemplate
     */
    public void updateUserIntegralTemplate(UserIntegralTemplate userIntegralTemplate){
     	userIntegralTemplateService.updateUserIntegralTemplate(userIntegralTemplate);
    }

   
}