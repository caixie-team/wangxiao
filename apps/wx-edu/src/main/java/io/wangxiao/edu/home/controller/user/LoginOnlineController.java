package io.wangxiao.edu.home.controller.user;

import io.wangxiao.edu.home.common.EduBaseController;
import io.wangxiao.edu.home.entity.user.LoginOnline;
import io.wangxiao.edu.home.service.user.LoginOnlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * LoginOnline管理接口
 */
@Controller
public class LoginOnlineController extends EduBaseController {

    @Autowired
    private LoginOnlineService loginOnlineService;


    /**
     * 修改LoginOnline
     *
     * @param loginOnline 要修改的LoginOnline
     */
    public void updateLoginOnline(LoginOnline loginOnline) {
        loginOnlineService.updateLoginOnline(loginOnline);
    }


}