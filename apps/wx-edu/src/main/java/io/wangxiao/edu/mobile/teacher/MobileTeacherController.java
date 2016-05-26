package io.wangxiao.edu.mobile.teacher;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.commons.util.ObjectUtils;
import io.wangxiao.edu.home.common.MobileBaseController;
import io.wangxiao.edu.home.entity.course.QueryTeacher;
import io.wangxiao.edu.home.entity.course.Teacher;
import io.wangxiao.edu.home.service.course.TeacherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class MobileTeacherController extends MobileBaseController {

    private static final Logger logger = LoggerFactory.getLogger(MobileTeacherController.class);
    // 教师列表
    private static final String teacherlist = getViewPath("/teacher/teacher-list");
    // 教师列表
    private static final String teacherAjaxlist = getViewPath("/teacher/teacher-list-ajax");
    // 教师详情
    private static final String teacherinfo = getViewPath("/teacher/teacher-info");

    @Autowired
    private TeacherService teacherService;

    // 绑定属性 封装参数
    @InitBinder("teacher")
    public void initQueryTeacher(HttpServletRequest request, ServletRequestDataBinder binder) {
        binder.setFieldDefaultPrefix("teacher.");
    }

    /**
     * 教师列表展示
     *
     * @return
     */
    @RequestMapping("/mobile/teacher/list")
    public String teacherlist(HttpServletRequest request, @ModelAttribute("page") PageEntity page) {
        try {

            // 页面传来的数据放到page中

            page.setPageSize(6);
            page.setCurrentPage(1);
            // 查询老师
            Teacher teacher = new Teacher();
            List<QueryTeacher> teacherList = teacherService.queryTeacherListPage(teacher, page);
            request.setAttribute("teacherList", teacherList);
            request.setAttribute("page", page);
        } catch (Exception e) {
            logger.error("MobileTeacherController.teacherlist", e);
            return setExceptionRequest(request, e);
        }
        return teacherlist;
    }

    /**
     * 教师列表展示ajax
     *
     * @return
     */
    @RequestMapping("/mobile/teacher/ajax/list")
    public String teacherAjaxlist(HttpServletRequest request, @ModelAttribute("page") PageEntity page) {
        try {

            // 页面传来的数据放到page中

            page.setPageSize(6);
            // 查询老师
            Teacher teacher = new Teacher();
            List<QueryTeacher> teacherList = teacherService.queryTeacherListPage(teacher, page);
            request.setAttribute("teacherList", teacherList);
            request.setAttribute("page", page);
        } catch (Exception e) {
            logger.error("MobileTeacherController.teacherAjaxlist", e);
            return setExceptionRequest(request, e);
        }
        return teacherAjaxlist;
    }

    /**
     * 教师详情
     *
     * @return
     */
    @RequestMapping("/mobile/teacher/{teacherId}")
    public String teacher(HttpServletRequest request, @PathVariable Long teacherId, Model model) {
        try {
            // 查询老师
            Teacher teacher = teacherService.getTeacherById(teacherId);
            if (ObjectUtils.isNull(teacher)) {
                request.setAttribute("msg", "对不起该教师不存在或者已删除");
                return "redirect:/front/success";
            }
            model.addAttribute("teacher", teacher);

        } catch (Exception e) {
            logger.error("MobileTeacherController.teacherinfo", e);
            return setExceptionRequest(request, e);
        }
        return teacherinfo;
    }


}