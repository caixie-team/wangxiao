package io.wangxiao.course.service;

import io.wangxiao.website.service.WebsiteNavigateService;
import org.beetl.json.JsonTool;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class WebsiteNavigateServiceTest extends BaseTest {

//    @Autowired
//    WebsiteProfileService websiteProfileService;

    @Autowired
    WebsiteNavigateService websiteNavigateService;

    @Test
    public void testQueryNavByType() {
//        Pagination pagination = new Pagination();

        JsonTool jsonTool = new JsonTool();
        System.out.println(jsonTool.serialize(websiteNavigateService.findByType("USER")));
    }

}
