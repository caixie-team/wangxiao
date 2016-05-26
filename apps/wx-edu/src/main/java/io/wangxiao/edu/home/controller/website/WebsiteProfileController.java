package io.wangxiao.edu.home.controller.website;

import io.wangxiao.edu.home.common.EduBaseController;
import io.wangxiao.edu.home.constants.enums.WebSiteProfileType;
import io.wangxiao.edu.home.service.website.WebsiteProfileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class WebsiteProfileController extends EduBaseController {

    private static final Logger logger = LoggerFactory.getLogger(WebsiteProfileController.class);
    @Autowired
    private WebsiteProfileService websiteProfileService;

    /**
     * 查询在线咨询
     *
     * @param model
     * @return
     */
    @RequestMapping("/ajax/websiteProfile/online")
    @ResponseBody
    public Map<String, Object> getAjaxWebsiteOnline(HttpServletRequest request) {
        Map<String, Object> json = null;
        try {
            // 查询在线咨询详情
            Map<String, Object> map = websiteProfileService.getWebsiteProfileByType(WebSiteProfileType.online.toString());
            json = this.getJsonMap(true, "success", map);
        } catch (Exception e) {
            logger.error("getWebsiteOnline", e);
            json = this.getJsonMap(false, "online is null", null);
        }
        return json;
    }
}