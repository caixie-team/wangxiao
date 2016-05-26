package com.atdld.os.api.help.controller;

import com.atdld.os.api.help.entity.HelpMenu;
import com.atdld.os.api.help.service.HelpMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * AdminHelpMenuController管理接口
 * User:
 * Date: 2014-09-26
 */
@Controller
@RequestMapping("/admin")
public class AdminHelpMenuController {
    @Autowired
    private HelpMenuService helpMenuService;


    /**
     * 菜单表
     *
     * @param request
     * @return
     */
    @RequestMapping("/helpMenu/list")
    @ResponseBody
    public Object getHelpMenu(HttpServletRequest request) {
        List<List<HelpMenu>> helpMenus = helpMenuService.getHelpMenuAll();//菜单集合

        return helpMenus;
    }


}

