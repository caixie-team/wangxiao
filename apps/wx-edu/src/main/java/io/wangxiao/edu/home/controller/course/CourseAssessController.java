package io.wangxiao.edu.home.controller.course;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.commons.util.ObjectUtils;
import io.wangxiao.commons.util.web.WebUtils;
import io.wangxiao.edu.common.constants.CommonConstants;
import io.wangxiao.edu.home.common.EduBaseController;
import io.wangxiao.edu.home.constants.enums.IntegralKeyword;
import io.wangxiao.edu.home.constants.web.LetterConstans;
import io.wangxiao.edu.home.entity.course.CourseAssess;
import io.wangxiao.edu.home.entity.course.CourseDto;
import io.wangxiao.edu.home.entity.course.QueryCourseAssess;
import io.wangxiao.edu.home.entity.user.UserExpandDto;
import io.wangxiao.edu.home.service.course.CourseAssessService;
import io.wangxiao.edu.home.service.course.CourseService;
import io.wangxiao.edu.home.service.letter.MsgReceiveService;
import io.wangxiao.edu.home.service.user.UserExpandService;
import io.wangxiao.edu.home.service.user.UserIntegralService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class CourseAssessController extends EduBaseController {
    private static final Logger logger = LoggerFactory.getLogger(CourseAssessController.class);
    // 课程详情课程评价
    private static final String assess = getViewPath("/course/to_course_assess_ajax");
    // 播放页面课程评价
    private static final String assesskpoint = getViewPath("/course/to_kpoint_assess_ajax");

    @Autowired
    private CourseAssessService courseAssessService;
    @Autowired
    private UserIntegralService userIntegralService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private UserExpandService userExpandService;
    @Autowired
    private MsgReceiveService msgReceiveService;

    /**
     * 课程详情咨询列表
     *
     * @return
     */
    @RequestMapping("/front/ajax/assess")
    public String assess(Model model, HttpServletRequest request, @RequestParam("courseId") Long courseId, @ModelAttribute("page") PageEntity page) {
        try {
            UserExpandDto userExpandDto = userExpandService.getUserExpandByUids(getLoginUserId(request));
            if (ObjectUtils.isNotNull(userExpandDto)) {
                model.addAttribute("userImg", userExpandDto.getAvatar());
            }
            // 分页页数
            page.setPageSize(6);
            // 查询该课程下的评论
            CourseAssess courseAssess = new CourseAssess();
            courseAssess.setCourseId(courseId);
            List<QueryCourseAssess> courseAssessList = courseAssessService.getCourseAssessListPage(courseAssess, page);
            model.addAttribute("courseAssessList", courseAssessList);
            model.addAttribute("page", page);
            return assess;
        } catch (Exception e) {
            logger.error("CourseAssessController.assess", e);
            return setExceptionRequest(request, e);
        }
    }

    /**
     * 课程详情咨询列表
     *
     * @return
     */
    @RequestMapping("/front/ajax/consultation")
    public String consultation(Model model, HttpServletRequest request, @RequestParam("courseId") Long courseId, @ModelAttribute("page") PageEntity page) {
        try {
            // 分页页数
            page.setPageSize(6);
            // 查询该课程下的评论
            CourseAssess courseAssess = new CourseAssess();
            courseAssess.setCourseId(courseId);
            courseAssess.setKpointId(0L);
            List<QueryCourseAssess> courseAssessList = courseAssessService.getCourseAssessListPage(courseAssess, page);
            model.addAttribute("courseAssessList", courseAssessList);
            model.addAttribute("page", page);
            return assess;
        } catch (Exception e) {
            logger.error("CourseAssessController.assess", e);
            return setExceptionRequest(request, e);
        }
    }

    /**
     * 播放页面评价列表
     *
     * @return
     */
    @RequestMapping("/front/ajax/assesskpoint")
    public String assesskpoint(Model model, HttpServletRequest request, @RequestParam("kpointId") Long kpointId, @ModelAttribute("page") PageEntity page) {
        try {
            // 分页页数
            page.setPageSize(6);
            // 查询该课程下的评论
            CourseAssess courseAssess = new CourseAssess();
            courseAssess.setKpointId(kpointId);
            List<QueryCourseAssess> courseAssessList = courseAssessService.getCourseAssessListPage(courseAssess, page);
            model.addAttribute("courseAssessList", courseAssessList);
            model.addAttribute("page", page);
            return assesskpoint;
        } catch (Exception e) {
            logger.error("CourseAssessController.assesskpoint", e);
            return setExceptionRequest(request, e);
        }
    }

    /**
     * 课程详情评价列表
     *
     * @return
     */
    @RequestMapping("/front/addAssess")
    @ResponseBody
    public Map<String, Object> addassess(HttpServletRequest request, @ModelAttribute("courseAssess") CourseAssess courseAssess) {
        Map<String, Object> json = null;
        try {
            Long userId = getLoginUserId(request);
            CourseDto course = courseService.getCourseInfoById(courseAssess.getCourseId());
            courseAssess.setCreateTime(new Date());
            courseAssess.setUserId(userId);
            courseAssess.setStatus(0);
            courseAssess.setContent(WebUtils.replaceTagHTML(courseAssess.getContent()));
            courseAssessService.addCourseAssess(courseAssess);
            // 添加课程评论添加积分
            userIntegralService.addUserIntegral(IntegralKeyword.assess.toString(), userId, courseAssess.getCourseId(), 0L, "");
            msgReceiveService.addMessageByCusId("<a href='" + CommonConstants.contextPath + "/front/couinfo/" + courseAssess.getCourseId() + "'>" + course.getName() + "</a>:" + courseAssess.getContent(), userId, LetterConstans.LETTER_TYPE_ASSESS);
            json = this.getJsonMap(true, "", null);
            return json;
        } catch (Exception e) {
            this.setExceptionRequest(request, e);
            json = this.getJsonMap(false, "", null);
            return json;
        }
    }
}