package com.atdld.os.edu.controller.web;

import com.atdld.os.edu.common.EduBaseController;
import com.atdld.os.edu.constants.enums.WebSiteProfileType;
import com.atdld.os.edu.service.website.WebsiteNavigateService;
import com.atdld.os.edu.service.website.WebsiteProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Created by bison on 12/18/15.
 */
@Controller
public class HomeController extends EduBaseController {
    @Autowired
    private WebsiteProfileService websiteProfileService;
    @Autowired
    private WebsiteNavigateService websiteNavigateService;

    @RequestMapping("/options")
    @ResponseBody
    public Object options() {
        Map<String, Object> websitemap = websiteProfileService.getWebsiteProfileByType(WebSiteProfileType.web.toString());
        // 获得LOGO配置
        Map<String, Object> logomap = websiteProfileService.getWebsiteProfileByType(WebSiteProfileType.logo.toString());
        // 网站统计代码
        Map<String, Object> tongjiemap = websiteProfileService.getWebsiteProfileByType(WebSiteProfileType.censusCode.toString());
        //网站导航配置
        Map<String, Object> navigatemap = websiteNavigateService.getWebNavigate();
        // 销售模式
        Map<String, Object> salemap = websiteProfileService.getWebsiteProfileByType(WebSiteProfileType.sale.toString());
        // 网站开关
        Map<String, Object> keywordmap = websiteProfileService.getWebsiteProfileByType(WebSiteProfileType.keyword.toString());

        json.putAll(websitemap);
        json.putAll(logomap);
        json.putAll(navigatemap);
        json.putAll(salemap);
        json.putAll(keywordmap);
        json.putAll(tongjiemap);

        return this.json;
    }

}
