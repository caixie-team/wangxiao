package io.wangxiao.edu.app.controller.edu;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import io.wangxiao.edu.home.common.EduBaseController;

@Controller
public class AppdownloadController  extends EduBaseController {
    /***
     * 电脑端下载页面
     * @param request
     * @return
     */
    @RequestMapping("/appdownload")
   public String appdownload(HttpServletRequest request){
        //String ag =UserAgentUtil.getUserAgent(request);
        return getViewPath("/downapp/index");
    }



}
