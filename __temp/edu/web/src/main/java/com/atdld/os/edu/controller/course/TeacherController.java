package com.atdld.os.edu.controller.course;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.atdld.os.common.constants.CommonConstants;
import com.atdld.os.core.util.web.VelocityHtmlUtil;
import com.atdld.os.sysuser.service.SubjectService;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.atdld.os.core.entity.PageEntity;
import com.atdld.os.core.util.ObjectUtils;
import com.atdld.os.edu.common.EduBaseController;
import com.atdld.os.edu.entity.course.CourseDto;
import com.atdld.os.edu.entity.course.QueryCourse;
import com.atdld.os.edu.entity.course.QueryTeacher;
import com.atdld.os.edu.entity.course.Teacher;
import com.atdld.os.edu.service.course.CourseService;
import com.atdld.os.edu.service.course.TeacherService;
import com.atdld.os.edu.service.website.WebsiteImagesService;

/**
 * Teacher管理接口 User:  Date: 2014-05-27
 */
@Controller
public class TeacherController extends EduBaseController {

	private static final Logger logger = LoggerFactory.getLogger(TeacherController.class);
	// 教师列表
	private static final String teacherlist = getViewPath("/teacher/teacher-list");
	// 教师详情
	private static final String teacherinfo = getViewPath("/teacher/teacher_info");

	@Autowired
	private TeacherService teacherService;

	@Autowired
	private CourseService courseService;

	@Autowired
	private SubjectService subjectService;
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
			//获得banner图
		    Map<String, Object> websiteImages = websiteImagesService.getIndexPageBanner(null);
		    modelAndView.addObject("websiteImages", websiteImages);
			// 页面传来的数据放到page中
			this.setPage(page);
			this.getPage().setPageSize(5);
			// 查询老师
			List<QueryTeacher> teacherList = teacherService.queryTeacherAndCourseListPage(teacher, page);
			modelAndView.addObject("teacherList", teacherList);
			modelAndView.addObject("page", this.getPage());
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
	public String teacher(HttpServletRequest request, @PathVariable Long teacherId,
			RedirectAttributes redirectAttributes,Model model) {
		try {
			// 查询老师
			Teacher teacher = teacherService.getTeacherById(teacherId);
			if(ObjectUtils.isNull(teacher)){
				redirectAttributes.addAttribute("msg","对不起该教师不存在或者已删除");
				return "redirect:/front/success";
			}
			// 讲师所江讲的课程
			this.getPage().setPageSize(12);
			PageEntity page = new PageEntity();
			page.setPageSize(6);
			QueryCourse queryCourse = new QueryCourse();
			queryCourse.setTeacherId(teacherId);
			// 只查询上架的
			queryCourse.setIsavaliable(0L);
			List<CourseDto> courseList = courseService.getCourseListPage(queryCourse, page);
			// 同类型的讲师，讲同样课程的老师
			if (ObjectUtils.isNotNull(courseList)) {
				List<Long> courseIds = new ArrayList<Long>();
				for (CourseDto courseDto : courseList) {
					courseIds.add(courseDto.getId());
				}
				List<Teacher> teacherList = teacherService.getTeachersByCourse(courseIds, teacherId);
				model.addAttribute("teacherList", teacherList);
			}

			model.addAttribute("courseList", courseList);
			model.addAttribute("page", this.getPage());
			model.addAttribute("teacher", teacher);

		} catch (Exception e) {
			logger.error("CourseTeacherController.teacherlist", e);
			return setExceptionRequest(request, e);
		}
		return teacherinfo;
	}

	/**
	 * 个人中心教师展示
	 * 
	 * @return
	 */
	@RequestMapping("/uc/rantech")
	@ResponseBody
	public Object ranteacherlist(HttpServletRequest request, @ModelAttribute("page") PageEntity page, @ModelAttribute("teacher") Teacher teacher) {
		try {

			VelocityHtmlUtil velocityHtmlUtil = new VelocityHtmlUtil(request.getSession().getServletContext().getRealPath(""), "WEB-INF/view/" + getViewPath("/ucenter/teacher.vm"));
			// 页面传来的数据放到page中
			this.setPage(page);
			this.getPage().setPageSize(6);
			// 查询老师
			List<QueryTeacher> teacherList = teacherService.queryTeacherListPage(teacher, page);
			velocityHtmlUtil.put("teacherList", teacherList);
			velocityHtmlUtil.put("page", this.getPage());
			velocityHtmlUtil.put("staticImageServer", CommonConstants.staticImageServer);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("html", velocityHtmlUtil.getText());
			map.put("page", this.getPage());
			this.setJson(true, null, map);
		} catch (Exception e) {
			setExceptionRequest(request, e);
			this.setJson(false, null, null);
		}
		return json;
	}

}