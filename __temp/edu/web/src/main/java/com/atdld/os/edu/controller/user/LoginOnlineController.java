package com.atdld.os.edu.controller.user;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.atdld.os.edu.service.user.LoginOnlineService;
import com.atdld.os.edu.common.EduBaseController;
import com.atdld.os.edu.entity.user.LoginOnline;
/**
 * LoginOnline管理接口
 * User:
 * Date: 2014-11-18
 */
@Controller
public class LoginOnlineController extends EduBaseController{

 	@Autowired
    private LoginOnlineService loginOnlineService;
    
    
    
    /**
     * 修改LoginOnline
     * @param loginOnline 要修改的LoginOnline
     */
    public void updateLoginOnline(LoginOnline loginOnline){
     	loginOnlineService.updateLoginOnline(loginOnline);
    }

   
}