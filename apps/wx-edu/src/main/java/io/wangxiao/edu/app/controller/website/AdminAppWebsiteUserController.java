package io.wangxiao.edu.app.controller.website;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.edu.app.common.AppBaseController;
import io.wangxiao.edu.app.entity.website.WebsiteUse;
import io.wangxiao.edu.app.service.website.WebsiteUseService;
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
public class AdminAppWebsiteUserController extends AppBaseController {

    private static final Logger logger = LoggerFactory.getLogger(AdminAppWebsiteUserController.class);

    @Autowired
    private WebsiteUseService websiteUseService;

    private static final String usePage = getViewPath("/admin/website/statistics/use_list");

    // 创建群 绑定变量名字和属性，把参数封装到类
    @InitBinder("websiteUse")
    public void initBinderWebsiteUse(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("websiteUse.");
    }

    /**
     * 使用统计分页
     */
    @RequestMapping("/use/list")
    public String usePage(WebsiteUse websiteUse, HttpServletRequest request, @ModelAttribute("page") PageEntity page) {
        try {

            List<WebsiteUse> websiteUseList = websiteUseService.getWebsiteUsePage(websiteUse, page);
            request.setAttribute("websiteUseList", websiteUseList);
            request.setAttribute("page", page);
        } catch (Exception e) {
            logger.error("usePage", e);
            return setExceptionRequest(request, e);
        }
        return usePage;
    }

    /**
     * 添加使用记录(app调用还需修改)
     * http://127.0.0.1/admin/use/addUse?websiteLogin.ip=127.0.01
     * &size=111&ip=11&brand=11&type=11&userId=11&beginFalg=2
     */
    @RequestMapping("/use/addUse")
    @ResponseBody
    public Map<String, Object> queryUse(HttpServletRequest request, WebsiteUse websiteUse) {
        Map<String, Object> json = null;
        try {
            websiteUseService.insertWebsiteUse(websiteUse);
            json = this.getJsonMap(true, "success", websiteUse);
        } catch (Exception e) {
            logger.error("queryUse", e);
            json = this.getJsonMap(false, "false", null);
        }
        return json;
    }

}
