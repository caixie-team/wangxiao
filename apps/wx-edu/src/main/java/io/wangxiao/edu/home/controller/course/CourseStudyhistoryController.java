package io.wangxiao.edu.home.controller.course;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.edu.home.common.EduBaseController;
import io.wangxiao.edu.home.entity.course.CourseDto;
import io.wangxiao.edu.home.entity.course.CourseStudyhistory;
import io.wangxiao.edu.home.service.course.CourseService;
import io.wangxiao.edu.home.service.course.CourseStudyhistoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
public class CourseStudyhistoryController extends EduBaseController {

    @Autowired
    private CourseStudyhistoryService courseStudyhistoryService;
    private String studyHistory = getViewPath("/ucenter/study_list");// 学习记录
    private String ajax_studyHistory = getViewPath("/course/ajax_study_history");// 学习记录ajax

    private Logger logger = LoggerFactory.getLogger(CourseStudyhistoryController.class);
    @Autowired
    private CourseService courseService;

    /**
     * 学习记录
     *
     * @param request
     * @param page
     * @return
     */
    @RequestMapping("/uc/study")
    public ModelAndView studyHistory(HttpServletRequest request, @ModelAttribute("page") PageEntity page) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(studyHistory);
        CourseStudyhistory courseStudyhistory = new CourseStudyhistory();
        try {
            page.setPageSize(3);

            courseStudyhistory.setUserId(getLoginUserId(request));
            List<CourseStudyhistory> studylist = courseStudyhistoryService.getCourseStudyhistoryListByCondition(courseStudyhistory, page);
            // 获得所有推荐课程
            Map<String, List<CourseDto>> mapCourseList = courseService.getCourseListByHomePage(0L);
            modelAndView.addObject("studylist", studylist);
            modelAndView.addObject("mapCourseList", mapCourseList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return modelAndView;
    }

    /**
     * 学习记录 ajax
     *
     * @param request
     * @return
     */
    @RequestMapping("/course/ajax/his")
    public Object studyHistory(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            PageEntity page = new PageEntity();
            CourseStudyhistory courseStudyhistory = new CourseStudyhistory();
            courseStudyhistory.setUserId(getLoginUserId(request));
            List<CourseStudyhistory> studylist = courseStudyhistoryService.getCourseStudyhistoryListByCondition(courseStudyhistory, page);
            modelAndView.addObject("studylist", studylist);
            modelAndView.setViewName(ajax_studyHistory);
        } catch (Exception e) {
            return new ModelAndView(this.setExceptionRequest(request, e));
        }
        return modelAndView;
    }

    @RequestMapping("/ajax/updatePlayTime")
    @ResponseBody
    public Map<String, Object> updatePlayTime(HttpServletRequest request, @ModelAttribute CourseStudyhistory courseStudyhistory, @ModelAttribute PageEntity page) {
        Map<String, Object> json = null;
        try {
            Long userId = getLoginUserId(request);
            courseStudyhistory.setUserId(userId);
            courseStudyhistory.setPlayTime(1L);
            courseStudyhistoryService.updateCourseStudyhistoryPlayTime(courseStudyhistory);
            json = this.getJsonMap(true, "success", null);
        } catch (Exception e) {
            logger.error("CourseStudyhistoryController.updatePlayTime", e);
            json = this.getJsonMap(false, "error", null);
        }
        return json;
    }
}