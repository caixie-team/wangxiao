package io.wangxiao.edu.app.controller.website;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.edu.app.common.AppBaseController;
import io.wangxiao.edu.app.entity.website.WebsiteLogin;
import io.wangxiao.edu.app.service.website.WebsiteLoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminAppWebsiteLoginController extends AppBaseController {

    private static final Logger logger = LoggerFactory.getLogger(AdminAppWebsiteLoginController.class);

    @Autowired
    private WebsiteLoginService websiteLoginService;

    private static final String loginPage = getViewPath("/admin/website/statistics/login_list");

    // 创建群 绑定变量名字和属性，把参数封装到类
    @InitBinder("websiteLogin")
    public void initBinderWebsiteLogin(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("websiteLogin.");
    }

    /**
     * 登陆统计分页
     */
    @RequestMapping("/login/list")
    public String loginPage(WebsiteLogin websiteLogin, HttpServletRequest request, @ModelAttribute("page") PageEntity page) {
        try {

            List<WebsiteLogin> websiteLoginList = websiteLoginService.getWebsiteLoginPageList(websiteLogin, page);
            request.setAttribute("websiteLoginList", websiteLoginList);
            request.setAttribute("page", page);
        } catch (Exception e) {
            logger.error("loginPage", e);
            return setExceptionRequest(request, e);
        }
        return loginPage;
    }

    /***
     * 添加登陆记录(app调用还需修改)
     */
    @RequestMapping("/appWebsite/addlogin")
    @ResponseBody
    public Map<String, Object> queryImages(HttpServletRequest request, WebsiteLogin websiteLogin) {
        Map<String, Object> json = null;
        try {
            websiteLoginService.insertWebsiteLogin(websiteLogin);
            json = this.getJsonMap(true, "success", websiteLogin);
        } catch (Exception e) {
            logger.error("queryImages", e);
            json = this.getJsonMap(false, "false", null);
        }
        return json;
    }

}
