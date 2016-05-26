package io.wangxiao.edu.home.controller.course;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.commons.util.ObjectUtils;
import io.wangxiao.commons.util.StringUtils;
import io.wangxiao.edu.home.common.EduBaseController;
import io.wangxiao.edu.home.entity.course.Course;
import io.wangxiao.edu.home.entity.course.CourseHandout;
import io.wangxiao.edu.home.service.course.CourseHandoutService;
import io.wangxiao.edu.home.service.course.CourseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminCourseHandoutController extends EduBaseController {
    private static final Logger logger = LoggerFactory.getLogger(AdminCourseHandoutController.class);

    // 讲义
    private static final String toCourseHandoutList = getViewPath("/admin/course/course_handout_list");//讲义列表
    private static final String toAddCourseHandout = getViewPath("/admin/course/course_handout_add");//添加讲义
    private static final String toUpdateCourseHandout = getViewPath("/admin/course/course_handout_update");// 修改讲义
    @Autowired
    private CourseService courseService;
    @Autowired
    private CourseHandoutService courseHandoutService;

    @InitBinder("handout")
    public void initBinderCourseHandout(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("handout.");
    }

    /**
     * 讲义列表
     *
     * @param handout
     * @param page
     * @param model
     * @return
     */
    @RequestMapping("/cou/getCourseHandoutList")
    public String getCourseHandoutList(@ModelAttribute("handout") CourseHandout handout, @ModelAttribute("page") PageEntity page, Model model) {
        try {
            page.setPageSize(10);
            List<CourseHandout> courseHandoutList = this.courseHandoutService.getCourseHandoutListPage(handout, page);
            model.addAttribute("courseHandoutList", courseHandoutList);
            model.addAttribute("page", page);
            Course course = courseService.getCourseById(handout.getCourseId());
            model.addAttribute("course", course);
        } catch (Exception e) {
            logger.error("CourseController.getCourseHandoutList", e);
        }
        return toCourseHandoutList;
    }

    /**
     * 跳转添加讲义页
     *
     * @param model
     * @param courseId
     * @return
     */
    @RequestMapping("/cou/toAddCourseHandout/{courseId}")
    public String toAddCourseHandout(Model model, @PathVariable("courseId") Long courseId) {
        model.addAttribute("courseId", courseId);
        return toAddCourseHandout;
    }

    /**
     * 添加讲义
     *
     * @param handout
     * @return
     */
    @RequestMapping("/cou/addCourseHandout")
    public String addCourseHandout(@ModelAttribute("handout") CourseHandout handout) {
        try {
            if (ObjectUtils.isNotNull(handout) && ObjectUtils.isNotNull(handout.getCourseId()) && ObjectUtils.isNotNull(handout.getName()) && ObjectUtils.isNotNull(handout.getPath())) {
                this.courseHandoutService.addCourseHandout(handout);
            }
        } catch (Exception e) {
            logger.error("AdminCourseController.addCourseHandout", e);
        }
        return "redirect:/admin/cou/getCourseHandoutList?courseId=" + handout.getCourseId();
    }

    /**
     * 跳转讲义更新页
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/cou/toUpdateCourseHandout/{id}")
    public String toUpdateCourseHandout(@PathVariable("id") Long id, Model model) {
        try {
            if (ObjectUtils.isNotNull(id) && id > 0) {
                CourseHandout courseHandout = this.courseHandoutService.getCourseHandoutById(id);
                model.addAttribute("handout", courseHandout);
            }
        } catch (Exception e) {
            logger.error("AdminCourseController.toUpdateCourseHandout");
        }
        return toUpdateCourseHandout;
    }

    /**
     * 讲义更新
     *
     * @param handout
     * @return
     */
    @RequestMapping("/cou/updateCourseHandout")
    public String updateCourseHandout(@ModelAttribute("handout") CourseHandout handout) {
        try {
            if (ObjectUtils.isNotNull(handout) && ObjectUtils.isNotNull(handout.getCourseId()) && ObjectUtils.isNotNull(handout.getName()) && ObjectUtils.isNotNull(handout.getPath())) {
                this.courseHandoutService.updateCourseHandout(handout);
            }
        } catch (Exception e) {
            logger.error("AdminCourseController.updateCourseHandout", e);
        }
        return "redirect:/admin/cou/getCourseHandoutList?courseId=" + handout.getCourseId();
    }

    /**
     * 删除讲义
     *
     * @param ids
     * @return
     */
    @RequestMapping("/cou/deleteCourseHandout")
    @ResponseBody
    public Map<String, Object> deleteCourseHandout(@RequestParam("ids") String ids) {
        Map<String, Object> json = null;
        try {
            if (StringUtils.isNotEmpty(ids)) {
                Long l = this.courseHandoutService.deleteCourseHandout(ids);
                if (ObjectUtils.isNotNull(l) && l > 0) {
                    json = this.getJsonMap(true, "success", null);
                } else {
                    json = this.getJsonMap(false, "fail", null);
                }
            }
        } catch (Exception e) {
            logger.error("AdminCourseController.updateCourseHandout", e);
            json = this.getJsonMap(false, "error", null);
        }
        return json;
    }
}