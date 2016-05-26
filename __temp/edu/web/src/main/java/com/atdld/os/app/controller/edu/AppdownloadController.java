package com.atdld.os.app.controller.edu;

import com.atdld.os.common.util.UserAgentUtil;
import com.atdld.os.edu.common.EduBaseController;
import com.atdld.os.sysuser.entity.SysUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2015/1/22.
 */
@Controller
public class AppdownloadController  extends EduBaseController {
    /***
     * 电脑端下载页面
     * @param request
     * @return
     */
    @RequestMapping("/appdownload")
   public String appdownload(HttpServletRequest request){
        String ag =UserAgentUtil.getUserAgent(request);
        System.out.println("+++++ag:"+ag);
        return getViewPath("/downapp/index");
    }

    /**
     * 手机端下载页面
     * @param request
     * @return
     */
    @RequestMapping("/appdownload-m")
    public String appdownloadm(HttpServletRequest request){
        String ag =UserAgentUtil.getUserAgent(request);
        System.out.println("+++++m-ag:"+ag);
        return getViewPath("/downapp/m-index");
    }

    @RequestMapping("/appdownloadAnrioid")
    public String appdownloadAnrioid(HttpServletRequest request){
        String ag =UserAgentUtil.getUserAgent(request);
        System.out.println("+++++m-ag:"+ag);
        return "redirect:/static/edu/downapp/app.apk";
    }

}
