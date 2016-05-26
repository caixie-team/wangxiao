package io.wangxiao.course.service;

import io.wangxiao.core.Condition;
import io.wangxiao.core.Pagination;
import co.bluepx.edu.course.BaseTest;
import io.wangxiao.website.entity.WebsiteNavigate;
import io.wangxiao.website.service.WebsiteNavigateService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class WebsiteNavigateServiceTest extends BaseTest{

//    @Autowired
//    WebsiteProfileService websiteProfileService;

    @Autowired
    WebsiteNavigateService websiteNavigateService;

    @Test
    public void testQueryNavByType() {
        Pagination pagination = new Pagination();

        //Type
        Condition condition = Condition.parseCondition("type_string_eq");
        condition.setValue("INDEX");

        List<WebsiteNavigate> websiteNavigates = websiteNavigateService.findAll();

        for (WebsiteNavigate websiteNavigate : websiteNavigates) {
            System.out.println(websiteNavigate.getName());
        }

    }

}
