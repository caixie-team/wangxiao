package co.bluepx.edu.course.service;

import co.bluepx.edu.core.enums.SellType;
import co.bluepx.edu.course.BaseTest;
import co.bluepx.edu.course.entity.CourseDto;
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
public class CourseSeviceTest extends BaseTest {

    @Autowired
    CourseService courseService;

    private Gson gson = new Gson();


    @Test
    public void testQueryRecommendCourses() {
        List<CourseDto> courseDtos = courseService.findRecommendCourses(0L);


        System.out.println(gson.toJson(courseDtos));

    }

    /**
     * 根据课程id 查询课程详细信息
     */
    @Test
    public void testFindCourseDtoById() {

        CourseDto dto = courseService.findCourseDtoById(1L);
        if (dto != null)
            System.out.println(gson.toJson(dto));
    }

    @Test
    public void testFindCourseListByPage() {
        QueryCourse queryCourse = new QueryCourse();
        queryCourse.setSubjectId(165L);
//        Pagination pagination = new Pagination();
//        pagination.setPageSize(3);
//        pagination.setCurrentPage(1);
        PageInfo<CourseDto> pageCourse = courseService.findCourseListByPage(queryCourse, 1, 3);
        System.out.println(gson.toJson(pageCourse));

    }

    @Test
    public void testFindCourseAndCourseDir(){

        CourseDto course = courseService.findCourseDtoById(2L);
        //显示目录树list,课程时也放到此list.size为1而已
        List<CourseDto> courseDtos =new ArrayList<CourseDto>();
        //获取套餐的课程列表
        if(course.getSellType().equalsIgnoreCase(SellType.PACKAGE.toString())){
            List<Long> alist= new ArrayList<Long>();
            alist.add(course.getId());
            courseDtos = courseService.findCourseListPackage(alist);
        }else{
            courseDtos.add(course);
        }

        //同类课程推荐
//        List<CourseDto> courseMatchs = courseService.findSubjectCourseList(null,course.getId(), 3);

        Map<String,Object> courseMap = new HashMap<>();
        courseMap.put("course", course);
        courseMap.put("course-dir", courseDtos);
//        courseMap.put("course-match",courseMatchs);

        System.out.println(gson.toJson(courseMap));
    }
}
