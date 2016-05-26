package co.bluepx.edu.api.teacher;

import co.bluepx.edu.course.dao.TeacherDao;
import co.bluepx.edu.course.entity.Teacher;
import co.bluepx.edu.course.service.TeacherService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 教师资源
 * Created by longjianlin on 16/1/6.
 */
@Api(value = "teacher-api", description = "教师资源API", position = 1)
@RestController
@RequestMapping(value = "/api/teacher")
public class TeacherController {

    //教师服务
    @Autowired
    TeacherService teacherService;

    @ApiOperation(value = "根据 ID 查询教师详情", notes = "返回教师详情", response = Teacher.class, position = 1)
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity get(@PathVariable("id") long id) {
        return new ResponseEntity(teacherService.findById(id), HttpStatus.OK);
    }

    /**
     * 获取教师资源列表信息
     *
     * @return
     */
    @ApiOperation(value = "获取教师资源列表信息", notes = "返回教师分页列表信息", response = PageInfo.class, position = 2)
    @RequestMapping(value = "/explore", method = RequestMethod.GET)
    public ResponseEntity getTeachersByPage(@RequestParam int pageNum, @RequestParam int pageSize) {

        PageInfo<TeacherDao> pageTeacher = teacherService.findTeacherListByPage(pageNum, pageSize);

        return new ResponseEntity(pageTeacher, HttpStatus.OK);
    }






}
