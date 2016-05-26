package io.wangxiao.edu.app.controller.edu;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.commons.util.web.WebUtils;
import io.wangxiao.edu.app.common.AppBaseController;
import io.wangxiao.edu.home.constants.enums.IntegralKeyword;
import io.wangxiao.edu.home.entity.course.CourseAssess;
import io.wangxiao.edu.home.entity.course.QueryCourseAssess;
import io.wangxiao.edu.home.service.course.CourseAssessService;
import io.wangxiao.edu.home.service.user.UserIntegralService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/app")
public class AppCourseAssessController extends AppBaseController {
    private static Logger logger = Logger.getLogger(AppCourseAssessController.class);

    @Autowired
    private CourseAssessService courseAssessService;
    @Autowired
    private UserIntegralService userIntegralService;

    // 绑定变量名字和属性，参数封装进类
    @InitBinder("courseAssess")
    public void initBinderCourseAssess(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("courseAssess.");
    }

    /**
     * 课程Id查询课程评论列表
     *
     * @param page
     * @param courseId
     * @return
     */
    @RequestMapping("/course/assess/list")
    @ResponseBody
    public Map<String, Object> courseAssessList(@ModelAttribute("page") PageEntity page,
                                                @RequestParam("courseId") Long courseId) {
        Map<String, Object> json = null;
        try {

            page.setPageSize(10);
            // 查询该课程下的评论
            CourseAssess courseAssess = new CourseAssess();
            courseAssess.setCourseId(courseId);
            List<QueryCourseAssess> courseAssessList = courseAssessService.getCourseAssessListPage(courseAssess, page);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("assessList", courseAssessList);
            map.put("page", page);
            json = this.getJsonMap(true, "success", map);
        } catch (Exception e) {
            logger.error("AppCourseAssessController.courseAssessList", e);
            json = this.getJsonMap(false, "系统错误，请稍后重试", null);
        }
        return json;
    }

    /**
     * 添加课程评论
     *
     * @param courseAssess
     * @param userId
     * @return
     */
    @RequestMapping("/course/assess/add")
    @ResponseBody
    public Map<String, Object> addCourseAssess(CourseAssess courseAssess, @RequestParam("userId") Long userId) {
        Map<String, Object> json = null;
        try {
            courseAssess.setCreateTime(new Date());
            courseAssess.setUserId(userId);
            courseAssess.setStatus(0);
            courseAssess.setContent(WebUtils.replaceTagHref(courseAssess.getContent()));
            courseAssessService.addCourseAssess(courseAssess);
            // 添加课程评论添加积分
            userIntegralService.addUserIntegral(IntegralKeyword.assess.toString(), userId, courseAssess.getCourseId(),
                    0L, "");
            json = this.getJsonMap(true, "添加课程评论成功", null);
        } catch (Exception e) {
            logger.error("AppCourseAssessController.addCourseAssess()---error", e);
            json = this.getJsonMap(false, "系统错误，请稍后重试", null);
        }
        return json;
    }

}
