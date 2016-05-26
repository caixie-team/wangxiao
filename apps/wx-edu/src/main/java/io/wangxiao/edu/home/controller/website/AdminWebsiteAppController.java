package io.wangxiao.edu.home.controller.website;

import com.google.gson.Gson;
import io.wangxiao.edu.home.common.EduBaseController;
import io.wangxiao.edu.home.constants.enums.WebSiteProfileType;
import io.wangxiao.edu.home.entity.website.WebsiteProfile;
import io.wangxiao.edu.home.service.website.WebsiteProfileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 网站配置管理
 */
@Controller
@RequestMapping("/admin")
public class AdminWebsiteAppController extends EduBaseController {

    private static final Logger logger = LoggerFactory.getLogger(AdminWebsiteAppController.class);

    @Autowired
    private WebsiteProfileService websiteProfileService;

    private static final String appInfo = getViewPath("/admin/website/app/app_info");//app配置管理页面

    /**
     * 查询app配置 根据Type
     *
     * @param request
     * @return
     */
    @RequestMapping("/website/app/info")
    public String getWebSiteList(HttpServletRequest request) {
        try {
            //查询所有--存MemCache缓存
            Map<String, Object> map = websiteProfileService.getWebsiteProfileByType(WebSiteProfileType.app.toString());
            request.setAttribute("appMap", map);
            request.setAttribute("msg", request.getParameter("msg"));
        } catch (Exception e) {
            logger.error("getWebSiteList", e);
        }
        return appInfo;
    }

    /**
     * 更新管理根据类型
     *
     * @param request
     * @return
     */
    @RequestMapping("/website/app/update")
    public String updateAppInfo(HttpServletRequest request) {
        String msg = "";
        try {
            Gson gson = new Gson();
            Map<String, String> map = new HashMap<>();
            map.put("android_v", request.getParameter("android_v"));// 安卓版本号
            map.put("android_url", request.getParameter("android_url"));// 安卓更新地址
            map.put("ios_v", request.getParameter("ios_v"));// ios版本号
            map.put("ios_url", request.getParameter("ios_url"));// ios更新地址

            //将map转化json串
            WebsiteProfile websiteApp = new WebsiteProfile();// 创建websiteApp
            websiteApp.setType(WebSiteProfileType.app.toString());
            websiteApp.setDesciption(gson.toJson(map));
            websiteProfileService.updateWebsiteProfile(websiteApp);
            msg = "success";
        } catch (Exception e) {
            logger.error("AdminWebsiteAppController.updateAppInfo", e);
            msg = "error";
        }
        return "redirect:/admin/website/app/info?msg=" + msg;
    }

}
