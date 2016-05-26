package io.wangxiao.edu.app.controller.edu;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.commons.util.ObjectUtils;
import io.wangxiao.edu.app.common.AppBaseController;
import io.wangxiao.edu.home.entity.course.QueryTeacher;
import io.wangxiao.edu.home.entity.course.Teacher;
import io.wangxiao.edu.home.service.course.TeacherService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/app")
public class AppTeacherController extends AppBaseController {
    private static Logger logger = Logger.getLogger(AppTeacherController.class);

    @Autowired
    private TeacherService teacherService;

    // 绑定变量名字和属性，参数封装进类
    @InitBinder("teacher")
    public void initBinderTeacher(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("teacher.");
    }

    /**
     * app分页查询教师列表
     *
     * @param page    分页
     * @param teacher 查询条件
     * @return
     */
    @RequestMapping("/teacher/list")
    @ResponseBody
    public Map<String, Object> queryTeacherList(@ModelAttribute("page") PageEntity page, Teacher teacher) {
        Map<String, Object> json = null;
        try {
            // 页面传来的数据放到page中

            page.setPageSize(10);
            // 查询list集合
            List<QueryTeacher> teacherList = teacherService.queryTeacherListPage(teacher, page);
            Map<String, Object> dataMap = new HashMap<String, Object>();
            // 封装返回参数
            dataMap.put("teacherList", teacherList);
            dataMap.put("page", page);
            json = this.getJsonMap(true, "查询成功", dataMap);
        } catch (Exception e) {
            json = this.getJsonMap(false, "系统繁忙", null);
            logger.error("queryTeacherList()----error", e);
        }
        return json;
    }

    /**
     * app查询教师详情
     *
     * @param teacherId 教师ID编号
     * @return
     */
    @RequestMapping("/teacher/info")
    @ResponseBody
    public Map<String, Object> getTeacherInfo(@RequestParam("teacherId") Long teacherId) {
        Map<String, Object> json = null;
        try {
            if (ObjectUtils.isNotNull(teacherId) && teacherId.intValue() > 0) {
                Teacher teacher = teacherService.getTeacherById(teacherId);
                Map<String, Object> dataMap = new HashMap<String, Object>();
                // 封装返回参数
                dataMap.put("teacher", teacher);
                json = this.getJsonMap(true, "查询成功", dataMap);
            } else {
                json = this.getJsonMap(true, "查询失败，该教师不存在", null);
            }
        } catch (Exception e) {
            json = this.getJsonMap(false, "系统繁忙", null);
            logger.error("getTeacherInfo()----error", e);
        }
        return json;
    }
}
