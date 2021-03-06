package co.bluepx.edu.course.service;

import co.bluepx.edu.core.Condition;
import co.bluepx.edu.core.Pagination;
import co.bluepx.edu.course.BaseTest;
import co.bluepx.edu.website.entity.WebsiteNavigate;
import co.bluepx.edu.website.entity.WebsiteProfile;
import co.bluepx.edu.website.service.WebsiteNavigateService;
import co.bluepx.edu.website.service.WebsiteProfileService;
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
