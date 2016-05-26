package com.atdld.os.app.controller.website;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.atdld.os.app.common.AppBaseController;
import com.atdld.os.app.entity.website.AppCourse;
import com.atdld.os.app.entity.website.AppCourseDto;
import com.atdld.os.app.entity.website.QueryAppCourseCondition;
import com.atdld.os.app.service.website.AppCourseService;
import com.atdld.os.core.entity.PageEntity;
import com.atdld.os.core.util.StringUtils;
import com.atdld.os.edu.entity.course.CourseDto;
import com.atdld.os.edu.entity.course.QueryCourse;

/**
 * app课程管理
 */
@Controller
@RequestMapping("/admin")
public class AdminAppCourseController extends AppBaseController {

	private static final Logger logger = LoggerFactory.getLogger(AdminAppCourseController.class);
	private static final String appCourseList=getViewPath("/admin/website/course/app_course_list");
	private static final String selectCourse = getViewPath("/admin/website/course/select_app_course_list");
	@Autowired
	private AppCourseService appCourseService; 
	// app课程查询条件
	@InitBinder("queryAppCourseCondition")
	public void initBinderQueryAppCourseCondition(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("queryAppCourseCondition.");
	}
	@InitBinder("queryCourse")
    public void initBinderQueryCourse(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("queryCourse.");
    }
	/**
	 * 查询app课程列表
	 * @param page
	 * @return
	 */
	@RequestMapping("/appCourse/getAppCourseList")
	public ModelAndView getAppCourseList(@ModelAttribute("queryAppCourseCondition") QueryAppCourseCondition queryAppCourseCondition,@ModelAttribute("page") PageEntity page){
		ModelAndView model=new ModelAndView(appCourseList);
		try {
			if(queryAppCourseCondition==null){
				queryAppCourseCondition=new QueryAppCourseCondition();
			}
			page.setPageSize(15);
			List<AppCourseDto> appCourseList=appCourseService.queryAppMainCourse(queryAppCourseCondition,page);
			model.addObject("appCourseList", appCourseList);
			model.addObject("queryAppCourseCondition", queryAppCourseCondition);
			model.addObject("page", page);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("AdminAppCourseController.getAppCourseList--error", e);
		}
		return model;
	}
	
	/**
	 * 删除app课程
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping("/appCourse/deleteAppCourseById/{id}")
	@ResponseBody
	public Map<String, Object> deleteAppCourseById(HttpServletRequest request, @PathVariable Long id) {
		try {
			if (StringUtils.isNotEmpty(id.toString())) {
				// 删除课程分类
				appCourseService.delAppCourseById(id);
				this.setJson(true, "true", null);
			}
		} catch (Exception e) {
			logger.error("AdminAppCourseController.deleteAppCourseById--error", e);
			this.setJson(false, "false", null);
		}
		return json;
	}
	
	/**
	 * 批量删除app课程
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping("/appCourse/deleteAppCourseBatch")
	@ResponseBody
	public Map<String, Object> deleteAppCourseBatch(HttpServletRequest request) {
		try {
			String ids = request.getParameter("ids");
			if (StringUtils.isNotEmpty(ids) && !ids.equals("")) {
				// 删除课程分类
				appCourseService.delAppCourseBatch(ids);
				this.setJson(true, "true", null);
			}
		} catch (Exception e) {
			logger.error("AdminAppCourseController.deleteAppCourseBatch--error", e);
			this.setJson(false, "false", null);
		}
		return json;
	}
	
	/**
	 * 引用课程
	 * @param queryAppCourseCondition
	 * @param page
	 * @return
	 */
	@RequestMapping("/appCourse/selectCourse")
	public ModelAndView selectCourse(@ModelAttribute("queryCourse") QueryCourse queryCourse,@ModelAttribute("page") PageEntity page){
		ModelAndView model=new ModelAndView(selectCourse);
		try {
			 // 页面传来的数据放到page中
            this.setPage(page);
            this.getPage().setPageSize(12);
            //添加时间倒叙
            queryCourse.setOrder(2);
            //只查询上架的
            queryCourse.setIsavaliable(0L);
            // 搜索课程列表
            List<CourseDto> courseList = appCourseService.getCourseListPage(queryCourse, page);
            model.addObject("courseList", courseList);
            model.addObject("page", this.getPage());
            model.addObject("course", queryCourse);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("AdminAppCourseController.selectCourse--error", e);
		}
		return model;
	}
	/**
	 * 选取app课程
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping("/appCourse/addCourse")
	@ResponseBody
	public Map<String, Object> deleteAppCourseById(HttpServletRequest request, @ModelAttribute("ids") String ids) {
		try {
			if (StringUtils.isNotEmpty(ids)) {
				String[] id=ids.split(",");
				for(int i=0;i<id.length;i++){
					int num=appCourseService.getAppCourseById(Long.parseLong(id[i]));
					if(num==0){
						AppCourse appCourse=new AppCourse();
						appCourse.setCourseId(Integer.parseInt(id[i]));
						// 删除课程分类
						appCourseService.createAppMainCourse(appCourse);
					}
				}
				this.setJson(true, "true", null);
			}
		} catch (Exception e) {
			logger.error("AdminAppCourseController.deleteAppCourseById--error", e);
			this.setJson(false, "false", null);
		}
		return json;
	}
}
