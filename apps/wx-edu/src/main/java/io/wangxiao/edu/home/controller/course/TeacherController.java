package io.wangxiao.edu.home.controller.course;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.commons.util.ObjectUtils;
import io.wangxiao.commons.util.web.VelocityHtmlUtil;
import io.wangxiao.edu.common.constants.CommonConstants;
import io.wangxiao.edu.home.common.EduBaseController;
import io.wangxiao.edu.home.entity.course.CourseDto;
import io.wangxiao.edu.home.entity.course.QueryCourse;
import io.wangxiao.edu.home.entity.course.QueryTeacher;
import io.wangxiao.edu.home.entity.course.Teacher;
import io.wangxiao.edu.home.service.course.CourseService;
import io.wangxiao.edu.home.service.course.TeacherService;
import io.wangxiao.edu.home.service.website.WebsiteImagesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class TeacherController extends EduBaseController {

    private static final Logger logger = LoggerFactory.getLogger(TeacherController.class);
    // 教师列表
    private static final String teacherlist = getViewPath("/teacher/teacher-list");
    // 教师详情
    private static final String teacherinfo = getViewPath("/teacher/teacher-info");
    // 教师课程
    private static final String ajaxCourseList = getViewPath("/teacher/ajax_course_list");
    @Autowired
    private TeacherService teacherService;

    @Autowired
    private CourseService courseService;

    /*@Autowired
    private SubjectService subjectService;*/
    @Autowired
    private WebsiteImagesService websiteImagesService;

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
    @RequestMapping("/front/teacherlist")
    public ModelAndView teacherlist(HttpServletRequest request, @ModelAttribute("page") PageEntity page, @ModelAttribute("teacher") Teacher teacher) {
        ModelAndView modelAndView = new ModelAndView(teacherlist);
        try {
            // 获得banner图
            Map<String, Object> websiteImages = websiteImagesService.getIndexPageBanner(null);
            modelAndView.addObject("websiteImages", websiteImages);
            // 页面传来的数据放到page中

            page.setPageSize(5);
            // 查询老师
            List<QueryTeacher> teacherList = teacherService.queryTeacherAndCourseListPage(teacher, page);
            modelAndView.addObject("teacherList", teacherList);
            modelAndView.addObject("page", page);
            modelAndView.addObject("teacher", teacher);
        } catch (Exception e) {
            logger.error("CourseTeacherController.teacherlist", e);
            return new ModelAndView(setExceptionRequest(request, e));
        }
        return modelAndView;
    }

    /**
     * 教师详情
     *
     * @return
     */
    @RequestMapping("/front/teacher/{teacherId}")
    public String teacher(HttpServletRequest request, @PathVariable Long teacherId, @ModelAttribute("page") PageEntity page, RedirectAttributes redirectAttributes, Model model) {
        try {
            // 查询老师
            Teacher teacher = teacherService.getTeacherById(teacherId);
            if (ObjectUtils.isNull(teacher)) {
                redirectAttributes.addAttribute("msg", "对不起该教师不存在或者已删除");
                return "redirect:/front/success";
            }
            //讲师下的课程数量
            Long teacherCourseNum = teacherService.queryTeacherCourseNum(teacherId);

//			// 讲师所讲的课程
//			page.setPageSize(6);
//			QueryCourse queryCourse = new QueryCourse();
//			queryCourse.setTeacherId(teacherId);
//			// 只查询上架的
//			queryCourse.setIsavaliable(0L);
//			List<CourseDto> courseList = courseService.getCourseListPage(queryCourse, page);
//			// 同类型的讲师，讲同样课程的老师
//			if (ObjectUtils.isNotNull(courseList)) {
//				List<Long> courseIds = new ArrayList<Long>();
//				for (CourseDto courseDto : courseList) {
//					courseIds.add(courseDto.getId());
//				}
//				List<Teacher> teacherList = teacherService.getTeachersByCourse(courseIds, teacherId);
//				model.addAttribute("teacherList", teacherList);
//			}
//
//			model.addAttribute("courseList", courseList);
            model.addAttribute("page", page);
            model.addAttribute("teacher", teacher);
            model.addAttribute("teacherCourseNum", teacherCourseNum);

        } catch (Exception e) {
            logger.error("CourseTeacherController.teacherlist", e);
            return setExceptionRequest(request, e);
        }
        return teacherinfo;
    }

    /**
     * 获取教师授课列表
     *
     * @param model
     * @param teacherId
     * @param page
     * @return
     */
    @RequestMapping("/ajax/teacherCourseList/{teacherId}")
    public String teacherCourseList(Model model, @PathVariable Long teacherId, @ModelAttribute("page") PageEntity page) {
        try {
            // 讲师所讲的课程
            page.setPageSize(6);
            QueryCourse queryCourse = new QueryCourse();
            queryCourse.setTeacherId(teacherId);
            // 只查询上架的
            queryCourse.setIsavaliable(0L);
            List<CourseDto> courseList = courseService.getCourseListPage(queryCourse, page);
//			// 同类型的讲师，讲同样课程的老师
//			if (ObjectUtils.isNotNull(courseList)) {
//				List<Long> courseIds = new ArrayList<>();
//				for (CourseDto courseDto : courseList) {
//					courseIds.add(courseDto.getId());
//				}
//				List<Teacher> teacherList = teacherService.getTeachersByCourse(courseIds, teacherId);
//				model.addAttribute("teacherList", teacherList);
//			}
            model.addAttribute("courseList", courseList);
            model.addAttribute("page", page);
        } catch (Exception e) {
            logger.error("TeacherController.teacherCourseList", e);
        }
        return ajaxCourseList;

    }

    /**
     * 个人中心教师展示
     *
     * @return
     */
    @RequestMapping("/uc/rantech")
    @ResponseBody
    public Object ranteacherlist(HttpServletRequest request, @ModelAttribute("page") PageEntity page, @ModelAttribute("teacher") Teacher teacher) {
        Map<String, Object> json = null;
        try {

            VelocityHtmlUtil velocityHtmlUtil = new VelocityHtmlUtil(request.getSession().getServletContext().getRealPath(""), "WEB-INF/view/" + getViewPath("/ucenter/teacher.vm"));
            // 页面传来的数据放到page中

            page.setPageSize(6);
            // 查询老师
            List<QueryTeacher> teacherList = teacherService.queryTeacherListPage(teacher, page);
            velocityHtmlUtil.put("teacherList", teacherList);
            velocityHtmlUtil.put("page", page);
            velocityHtmlUtil.put("staticImageServer", CommonConstants.staticImageServer);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("html", velocityHtmlUtil.getText());
            map.put("page", page);
            json = this.getJsonMap(true, null, map);
        } catch (Exception e) {
            setExceptionRequest(request, e);
            json = this.getJsonMap(false, null, null);
        }
        return json;
    }

}