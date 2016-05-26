package co.bluepx.edu.course.service;

import co.bluepx.edu.course.BaseTest;
import co.bluepx.edu.course.dao.TeacherDao;
import co.bluepx.edu.course.entity.CourseDto;
import co.bluepx.edu.course.entity.QueryCourse;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by bison on 12/30/15.
 */
public class TeacherSeviceTest extends BaseTest {

    @Autowired
    TeacherService teacherService;

    private Gson gson = new Gson();


    @Test
    public void testFindTeacherListByPage() {
        PageInfo<TeacherDao> pageTeacher = teacherService.findTeacherListByPage( 1, 3);
        System.out.println(gson.toJson(pageTeacher));

    }
}
