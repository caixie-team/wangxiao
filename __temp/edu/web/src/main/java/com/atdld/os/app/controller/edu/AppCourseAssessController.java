package com.atdld.os.app.controller.edu;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atdld.os.app.common.AppBaseController;
import com.atdld.os.core.entity.PageEntity;
import com.atdld.os.core.util.ObjectUtils;
import com.atdld.os.core.util.web.WebUtils;
import com.atdld.os.edu.constants.enums.IntegralKeyword;
import com.atdld.os.edu.entity.course.CourseAssess;
import com.atdld.os.edu.entity.course.CourseDto;
import com.atdld.os.edu.entity.course.QueryCourseAssess;
import com.atdld.os.edu.service.course.CourseAssessService;
import com.atdld.os.edu.service.course.CourseService;
import com.atdld.os.edu.service.user.UserIntegralService;

@Controller
@RequestMapping("/webapp")
public class AppCourseAssessController extends AppBaseController{
	private static Logger logger = Logger.getLogger(AppCourseAssessController.class);
	
	@Autowired
	private CourseAssessService courseAssessService;
	@Autowired
	private CourseService courseService;
	@Autowired
	private UserIntegralService userIntegralService;
	// 绑定变量名字和属性，参数封装进类
    @InitBinder("courseAssess")
    public void initBinderCourseAssess(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("courseAssess.");
    }
	
    /**
     * 根据课程Id查询课程评论列表
     * @param request
     * @param page
     * @param courseId
     * @return
     */
    @RequestMapping("/course/assess/list/{courseId}")
    @ResponseBody
	public Map<String, Object> showCourseAssessList(HttpServletRequest request, @ModelAttribute("page") PageEntity page, @PathVariable("courseId")Long courseId){
		try {
			//分页页数
			this.setPage(page);
            page.setPageSize(10);
            //查询该课程下的评论
            CourseAssess courseAssess = new CourseAssess();
            List<QueryCourseAssess> courseAssessList = null;
            CourseDto course = courseService.getCourseInfoById(courseId);
            if (course.getSellType().equalsIgnoreCase("PACKAGE")) { // 为套餐课程
            	String courseIds = "";
            	List<Long> ids = new ArrayList<Long>();
    			ids.add(courseId);
				List<CourseDto> courseList = courseService.getCourseListPackage(ids);
				// 遍历课程集合获得字符串
				if (ObjectUtils.isNotNull(courseList) && courseList.size() > 0) {
					for (CourseDto courseDto : courseList) {
						courseIds = courseIds + courseDto.getId() + ",";
					}
				}
				courseAssess.setCourseIds(courseIds.substring(0, courseIds.length() - 1));
				courseAssessList = courseAssessService.getAllCourseAssessListPage(courseAssess, page);
			} else { 
            	 courseAssess.setCourseId(courseId);
                 courseAssessList = courseAssessService.getCourseAssessListPage(courseAssess, page);
			} 
            Map<String,Object> map=new HashMap<String, Object>();
			map.put("assessList", courseAssessList);
			map.put("page", this.getPage());
			this.setJson(true, "success", map);
		} catch (Exception e) {
			logger.error("AppCourseAssessController.showCourseAssessList", e);
			this.setJson(false, "系统错误，请稍后重试", null);
		}
		return json;
	}
	
	
	/**
	 * 添加课程评论
	 * 
	 * @return
	 */
	@RequestMapping("/course/assess/add")
	@ResponseBody
	public Map<String, Object> addGroupPlan(HttpServletRequest request, CourseAssess courseAssess, @RequestParam("userId")Long userId) {
		try {
			courseAssess.setCreateTime(new Date());
            courseAssess.setUserId(userId);
            courseAssess.setStatus(0);
            courseAssess.setContent(WebUtils.replaceTagHref(courseAssess.getContent()));
            courseAssessService.addCourseAssess(courseAssess);
            //添加课程评论添加积分
            userIntegralService.addUserIntegral(IntegralKeyword.assess.toString(), getLoginUserId(request), courseAssess.getCourseId(), 0L, "");
			this.setJson(true, "添加课程评论成功", null);
		} catch (Exception e) {
			logger.error("AppGroupClassController.addGroupPlan", e);
			this.setJson(false, "系统错误，请稍后重试", null);
		}
		return json;
	}
	
}
