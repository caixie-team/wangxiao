package co.bluepx.edu.course.service;

import co.bluepx.edu.core.enums.SellType;
import co.bluepx.edu.course.BaseTest;
import co.bluepx.edu.course.entity.CourseDto;
import co.bluepx.edu.course.entity.CourseKpoint;
import co.bluepx.edu.course.entity.QueryCourse;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by bison on 12/30/15.
 */
public class CourseKpointServiceTest extends BaseTest {

    @Autowired
    CourseKpointService courseKpointService;

    private Gson gson = new Gson();


    @Test
    public void findCourseKpointsByCouseId() {
        List<CourseKpoint> courseKpoints = courseKpointService.findCourseKpointsByCouseId(2L);


        System.out.println(gson.toJson(courseKpoints));

    }

}
