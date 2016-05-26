package com.atdld.os.edu.controller.course;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.atdld.os.core.entity.PageEntity;
import com.atdld.os.edu.service.course.CourseService;
import com.atdld.os.edu.service.course.CourseStudyhistoryService;
import com.atdld.os.edu.common.EduBaseController;
import com.atdld.os.edu.entity.course.CourseDto;
import com.atdld.os.edu.entity.course.CourseStudyhistory;
/**
 * CourseStudyhistory管理接口
 * User:
 * Date: 2014-05-27
 */
@Controller
public class CourseStudyhistoryController extends EduBaseController{

 	@Autowired
    private CourseStudyhistoryService courseStudyhistoryService;
 	private String studyHistory = getViewPath("/ucenter/study_list");// 学习记录
 	private String ajax_studyHistory = getViewPath("/course/ajax_study_history");// 学习记录ajax

    private Logger logger = LoggerFactory.getLogger(CourseStudyhistoryController.class);
 	@Autowired
    private CourseService courseService;
 	
 	/**
     * 学习记录
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
        	this.setPage(page);
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
     * @param request
     * @param page
     * @return
     */
    @RequestMapping("/course/ajax/his")
    public Object studyHistory(HttpServletRequest request ) {
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
    
   
}