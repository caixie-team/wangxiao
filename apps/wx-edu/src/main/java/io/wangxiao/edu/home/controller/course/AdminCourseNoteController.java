package io.wangxiao.edu.home.controller.course;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.commons.util.ObjectUtils;
import io.wangxiao.edu.home.common.EduBaseController;
import io.wangxiao.edu.home.entity.course.CourseNote;
import io.wangxiao.edu.home.entity.course.QueryCourseNote;
import io.wangxiao.edu.home.service.course.CourseNoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @description 课程笔记后台
 */
@RequestMapping("/admin")
@Controller
public class AdminCourseNoteController extends EduBaseController {

    private static final Logger logger = LoggerFactory.getLogger(AdminCourseNoteController.class);

    private static final String toCourseNoteList = getViewPath("/admin/course/course_note_list");// 返回到课程笔记页面
    private static final String toCourseNoteInfo = getViewPath("/admin/course/courseNote_info");// 返回到课程笔记详情页面
    @Autowired
    private CourseNoteService courseNoteService;

    // 绑定变量名参数
    @InitBinder("queryCourseNote")
    public void initBinderQueryCourseNote(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("queryCourseNote.");
    }

    // 绑定变量名参数
    @InitBinder("courseNote")
    public void initBinderCourseNote(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("courseNote.");
    }

    /**
     * 查询笔记列表
     *
     * @param request
     * @param model
     * @param queryCourseNote
     * @param page
     * @return
     */
    @RequestMapping("/courseNote/list")
    public String getCourseNoteListPage(HttpServletRequest request, Model model, @ModelAttribute QueryCourseNote queryCourseNote, @ModelAttribute("page") PageEntity page) {
        try {
            // 设置分页参数

            page.setPageSize(10);
            // 查询笔记列表
            List<QueryCourseNote> queryCourseNoteList = courseNoteService.getCourseNoteListPage(queryCourseNote, page);
            model.addAttribute("queryCourseNoteList", queryCourseNoteList);
            model.addAttribute("page", page);
        } catch (Exception e) {
            logger.error("getCourseNoteListPage", e);
            return setExceptionRequest(request, e);
        }
        return toCourseNoteList;
    }

    /**
     * 查詢详情
     *
     * @param request
     * @param model
     * @param id
     * @return
     */
    @RequestMapping("/courseNote/info/{id}")
    public String getQueryCourseNoteById(HttpServletRequest request, Model model, @PathVariable("id") Long id) {
        try {
            QueryCourseNote queryCourseNote = courseNoteService.getQueryCourseNoteById(id);
            model.addAttribute("queryCourseNote", queryCourseNote);
        } catch (Exception e) {
            logger.error("getQueryCourseNoteById", e);
            return setExceptionRequest(request, e);
        }
        return toCourseNoteInfo;
    }

    /**
     * 更新 显示隐藏
     *
     * @param request
     * @param courseNote
     * @return
     */
    @RequestMapping("/courseNote/update")
    @ResponseBody
    public Map<String, Object> updateCourseAssess(HttpServletRequest request, @ModelAttribute("courseNote") CourseNote courseNote) {
        Map<String, Object> json = null;
        try {
            if (ObjectUtils.isNotNull(courseNote)) {// 更新状态
                courseNoteService.updateCourseNoteListStatus(courseNote);
                json = this.getJsonMap(true, "", null);
            }
        } catch (Exception e) {
            logger.error("updateCourseAssess", e);
            json = this.getJsonMap(false, "", null);
        }
        return json;
    }
}
