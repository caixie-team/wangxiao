package co.bluepx.edu.course.service;

import co.bluepx.edu.course.BaseTest;
import co.bluepx.edu.course.entity.CourseDto;
import co.bluepx.edu.course.entity.QueryCourse;
import co.bluepx.edu.course.entity.QueryCourseAssess;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by bison on 12/30/15.
 */
public class CourseAssessSeviceTest extends BaseTest {

    @Autowired
    CourseAssessService courseAssessService;

    private Gson gson = new Gson();


    @Test
    public void testFindCourseAssessByPage() {

        PageInfo<QueryCourseAssess> courseAssesses = courseAssessService.findCourseAssessByPage(93L,1,6);

        System.out.println(gson.toJson(courseAssesses));

    }
    @Test
    public void testFindPackageCourseAssessByPage() {

        PageInfo<QueryCourseAssess> courseAssesses = courseAssessService.findPackageCourseAssessByPage("2,3,91",1,6);

        System.out.println(gson.toJson(courseAssesses));

    }


}
