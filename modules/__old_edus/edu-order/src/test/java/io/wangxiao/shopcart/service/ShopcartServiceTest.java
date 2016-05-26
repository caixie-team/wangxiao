package io.wangxiao.shopcart.service;

import io.wangxiao.course.entity.Course;
import io.wangxiao.order.entity.Shopcart;
import io.wangxiao.order.service.ShopcartService;
import com.google.gson.Gson;
import org.beetl.sql.core.SQLManager;
import org.beetl.sql.ext.spring.SpringBeetlSql;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.GenericGroovyXmlContextLoader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by bison on 1/22/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/applicationContext.groovy", loader = GenericGroovyXmlContextLoader.class)
public class ShopcartServiceTest {


    @Autowired
    ShopcartService shopcartService;

    @Autowired
    SpringBeetlSql springBeetlSql;

    private Gson gson = new Gson();


    @Test
    public void testSelectDtos() {
        SQLManager dao = springBeetlSql.getSQLMananger();
        Map map = new HashMap<>();
        map.put("userId", 13);
        map.put("type", 1);
        List<Shopcart> shopcarts = dao.select("shopcart.dtos", Shopcart.class, map);
        for (Shopcart car : shopcarts) {

            System.out.println(gson.fromJson(gson.toJson(car.getTails()), Course.class).getContext());
        }

//        System.out.println(gson.toJson(dao.unique(Course.class, 1)));
    }


    @Test
    public void testToGenSql() throws Exception {
        SQLManager sql = springBeetlSql.getSQLMananger();
        sql.genSQLTemplateToConsole("edu_shopcart");
    }
}
