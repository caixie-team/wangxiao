package io.wangxiao.site.service;

import org.beetl.json.JsonTool;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class WebsiteNavigateServiceTest extends BaseTest {

    JsonTool jsonTool = new JsonTool();

    @Autowired
    WebsiteNavigateService websiteNavigateService;

    @Autowired
    WebsiteProfileService websiteProfileService;

    @Test
    public void testFindProfileByType() {

        System.out.println(jsonTool.serialize(websiteProfileService.findByType("web")));
    }

    @Test
    public void testQueryNavByType() {

        System.out.println(jsonTool.serialize(websiteNavigateService.findByType("USER")));
    }

}
