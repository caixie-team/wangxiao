package io.wangxiao.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.swagger.annotations.Api;
import io.wangxiao.site.service.WebsiteNavigateService;
import io.wangxiao.site.service.WebsiteProfileService;
import org.beetl.json.JsonTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.Map;

/**
 * Created by bison on 12/29/15.
 * <p>
 * 网站配置 api
 */
@Api(value = "website-api", description = "网站信息API", position = 1)
@Controller
@RequestMapping(value = "/")
public class WebsiteController {

    // 网站信息管理
    @Autowired
    WebsiteProfileService websiteProfileService;
    // 网站导航管理
    @Autowired
    WebsiteNavigateService websiteNavigateService;

    JsonTool json = new JsonTool();
    Gson gson = new Gson();

    @ResponseBody
    @RequestMapping(value = "/navs/{type}", method = RequestMethod.GET)
    public ResponseEntity nav(@PathVariable("type") String type) {

        return null;
    }

    @RequestMapping("")
    public String welcome(Map<String, Object> model) {
        model.put("time", new Date());
        model.put("message", "Hello Spring Boot Beetl!");
//        model.put("web",json.serialize(websiteProfileService.findByType("web").getDesciption()));

        // TODO: 加入缓存(Redis 或 二级缓存) 也可以一次性查询全部,采用 findAllWebsiteProfile
        String _web = websiteProfileService.findByType("web").getDesciption();
        String _logo = websiteProfileService.findByType("logo").getDesciption();

        Type typeToken = new TypeToken<Map<String, Object>>() {
        }.getType();

        model.put("web", gson.fromJson(_web, typeToken));
        model.put("logo", gson.fromJson(_web, typeToken));
        model.put("navs", websiteNavigateService.findByType("INDEX"));

        return "index";
    }

}
