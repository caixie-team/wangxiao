package io.wangxiao.course.service;

import com.google.gson.Gson;
import io.wangxiao.course.dao.CourseDao;
import io.wangxiao.course.model.Course;
import io.wangxiao.course.model.EduCourseTeacher;
import io.wangxiao.course.vo.CourseVo;
import org.beetl.json.JsonTool;
import org.beetl.sql.core.SQLManager;
import org.beetl.sql.core.db.KeyHolder;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bison on 12/30/15.
 */
public class CourseSeviceTest extends BaseTest {

    @Autowired
    CourseService courseService;

//    @Autowired
//    SpringBeetlSql springBeetlSql;

    @Autowired
    SQLManager sql;

    @Autowired
    CourseDao courseDao;

    private Gson gson = new Gson();

    @Test
    /**
     * 测试课程添加
     */
    public void addCourse() {
        CourseVo course = new CourseVo();
        course.setName("测试课程10010");
        course.setContext("测试内容10012");
        course.setCoursetag("专业课");
        course.setCurrentprice((double) 100);
        course.setIsavaliable(0);
        course.setLogo("");
        course.setLosetype(1);
        course.setPageViewcount(1000);
        course.setSellType("");
        // 原价
        course.setSourceprice((double) 100);
        course.setTitle("课程标题");
        course.setUpdateTime(new Timestamp(10000L));
        course.setUpdateuser("更新用户");
        course.setTeacherIds("10,20,30,40");
        course.setSubjectId(20L);
        courseService.addCourse(course);
//        KeyHolder keyHolder = new KeyHolder();
//        sql.insert(Course.class,course,keyHolder);
//        System.out.println(keyHolder.getKey() + "   ids");
    }

    public void getCount() {
    }

    @Test
    public void testBathInserCourseTacher(){
        CourseVo vo = new CourseVo();
        vo.setTeacherIds("1,2,3,4,5,6");
        List<EduCourseTeacher> courseTeacherList = new ArrayList<EduCourseTeacher>();
        String[] teaherIds = vo.getTeacherIds().split(",");

        for (String id : teaherIds) {

            EduCourseTeacher courseTeacher = new EduCourseTeacher();
            courseTeacher.setCourseId(250L);
            courseTeacher.setTeacherId(Long.valueOf(id));
            courseTeacherList.add(courseTeacher);
        }
        sql.insertBatch(EduCourseTeacher.class,courseTeacherList);
    }

    @Test public void findRecommendCourses(){
        List<CourseVo> courseVos = courseService.findRecommendCourses(2L);
        JsonTool jsonTool = new JsonTool();
        System.out.println(jsonTool.serialize(courseVos));
    }


    @Test
    public void getCourseById() {
        JsonTool jsonTool = new JsonTool();
        Course course = courseService.getCourseById(250L);
        System.out.println(jsonTool.serialize(course));

    }


    @Test
    public void testFindBooksPage() {

//        QueryBookCondition query = new QueryBookCondition();
//        query.setBookId(0);
//
//        System.out.printf(gson.toJson(bookService.findBookDTOByPage(query, 1, 10)));
    }

    @Test
    public void testGenSql() throws Exception {
//        SQLManager sql = springBeetlSql.getSQLManager();

        sql.genSQLTemplateToConsole("edu_course");
    }
}
