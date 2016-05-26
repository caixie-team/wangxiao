package com.atdld.os.edu.controller;

import com.atdld.os.edu.common.EduBaseController;
import com.atdld.os.edu.controller.web.WebFrontController;
import com.atdld.os.edu.entity.user.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Created by liuqinggang on 15/8/29.
 */
@Controller
public class TstCon extends EduBaseController {

    @RequestMapping("/testjson")
    @ResponseBody
    public Map<String,Object> te(){
        User user= new User();
        user.setIsavalible(98);
        user.setId(198L);
        user.setMobileIsavalible(1);
        this.setJson(true,null,user);
        return json;
    }
}
