package com.atdld.os.edu.controller.course;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atdld.os.core.entity.PageEntity;
import com.atdld.os.core.util.ObjectUtils;
import com.atdld.os.edu.common.EduBaseController;
import com.atdld.os.edu.entity.course.CourseProfile;
import com.atdld.os.edu.entity.course.QueryCourseProfile;
import com.atdld.os.edu.entity.course.QueryTeacher;
import com.atdld.os.edu.entity.course.Teacher;
import com.atdld.os.edu.service.course.CourseTeacherService;
import com.atdld.os.edu.service.course.TeacherService;

/**
 * 
 * @ClassName com.atdld.os.edu.controller.course.AdminTeacherController
 * @description
 * @author :
 * @Create Date : 2014年9月15日 下午9:53:08
 */
@Controller
@RequestMapping("/admin")
public class AdminTeacherController extends EduBaseController {
	private static final Logger logger = LoggerFactory.getLogger(AdminTeacherController.class);

	private static final String toAddTeacher = getViewPath("/admin/course/addTeacher");// 添加页面
	private static final String toTeacherList = getViewPath("/admin/course/teacher_list");// 讲师列表页面
	private static final String toSelectTeacherList = getViewPath("/admin/course/select_teacher_list");// 讲师列表页面
	private static final String toTeacherInfo = getViewPath("/admin/course/teacher_info");// 讲师详情页
	private static final String toUpdateTeacher = getViewPath("/admin/course/teacher_update");// 更新页面
	private static final String selectCourseList = getViewPath("/admin/course/select_courseteacher_list");// 讲师列表页面

	@Autowired
	private TeacherService teacherService;
	@Autowired
	private CourseTeacherService courseTeacherService;

	// 绑定变量名字和属性，参数封装进类
	@InitBinder("teacher")
	public void initBinderTeacher(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("teacher.");
	}
	// 绑定变量名字和属性，参数封装进类
	@InitBinder("queryCourseProfile")
	public void initBinderQueryCourseProfile(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("queryCourseProfile.");
	}

	/**
	 * 跳转到添加讲师页面
	 * 
	 * @return
	 */
	@RequestMapping("/teacher/toAdd")
	public String toAddTeacher() {
		return toAddTeacher;
	}

	/**
	 * 后台添加讲师
	 * 
	 * @param request
	 * @param teacher
	 * @return
	 */
	@RequestMapping("/teacher/add")
	public String addTeacher(HttpServletRequest request, @ModelAttribute("teacher") Teacher teacher) {
		try {
			// 添加讲师
			if (ObjectUtils.isNotNull(teacher)) {
				teacher.setStatus(0L);
				teacher.setCreateTime(new Date());
				teacher.setUpdateTime(new Date());
				teacherService.addTeacher(teacher);
			}

		} catch (Exception e) {
			logger.error("AdminTeacherController.addTeacher", e);
			return setExceptionRequest(request, e);
		}
		return "redirect:/admin/teacher/list";
	}

	/**
	 * 查询讲师列表
	 * 
	 * @param request
	 * @param model
	 * @param page
	 * @param teacher
	 * @return
	 */
	@RequestMapping("/teacher/list")
	public String queryTeacherList(HttpServletRequest request, Model model, @ModelAttribute("page") PageEntity page, @ModelAttribute("teacher") Teacher teacher) {
		try {
			this.setPage(page);
			// 查詢讲师
			List<QueryTeacher> teacherList = teacherService.queryTeacherListPage(teacher, this.getPage());
			// 把返回的数据放到model中
			model.addAttribute("teacherList", teacherList);
			model.addAttribute("page", this.getPage());
		} catch (Exception e) {
			logger.error("AdminTeacherController.queryTeacherList", e);
			return setExceptionRequest(request, e);
		}
		return toTeacherList;
	}

	/**
	 * 查询讲师列表
	 * 
	 * @param request
	 * @param model
	 * @param page
	 * @param teacher
	 * @return
	 */
	@RequestMapping("/teacher/selectlist")
	public String queryselectTeacherList(HttpServletRequest request, Model model, @ModelAttribute("page") PageEntity page, @ModelAttribute("teacher") Teacher teacher) {
		try {
			this.setPage(page);
			// 查詢讲师
			List<QueryTeacher> teacherList = teacherService.queryTeacherListPage(teacher, this.getPage());
			// 把返回的数据放到model中
			model.addAttribute("teacherList", teacherList);
			model.addAttribute("page", this.getPage());
			model.addAttribute("teacher", teacher);

		} catch (Exception e) {
			logger.error("AdminTeacherController.queryTeacherList", e);
			return setExceptionRequest(request, e);
		}
		return toSelectTeacherList;
	}

	/**
	 * 根据老师id获得详情
	 * 
	 * @param request
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping("/teacher/info/{id}")
	public String queryTeacherById(HttpServletRequest request, Model model, @PathVariable("id") Long id) {
		try {
			if (ObjectUtils.isNotNull(id)) {
				// 查詢老師
				Teacher teacher = teacherService.getTeacherById(id);
				// 把返回的数据放到model中
				model.addAttribute("teacher", teacher);
			}
		} catch (Exception e) {
			logger.error("AdminTeacherController.queryTeacherById", e);
			return setExceptionRequest(request, e);
		}
		return toTeacherInfo;
	}

	/**
	 * 根据老师id获得详情
	 * 
	 * @param request
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping("/teacher/toUpdate/{id}")
	public String toUpdateTeacher(HttpServletRequest request, Model model, @PathVariable("id") Long id) {
		try {
			if (ObjectUtils.isNotNull(id)) {
				// 查詢老師
				Teacher teacher = teacherService.getTeacherById(id);
				// 把返回的数据放到model中
				model.addAttribute("teacher", teacher);
			}
		} catch (Exception e) {
			logger.error("AdminTeacherController.queryTeacherById", e);
			return setExceptionRequest(request, e);
		}
		return toUpdateTeacher;
	}

	/**
	 * 更新讲师
	 * 
	 * @param request
	 * @param teacher
	 * @return
	 */
	@RequestMapping("/teacher/update")
	public String updateTeacher(HttpServletRequest request, @ModelAttribute("teacher") Teacher teacher) {
		try {
			if (ObjectUtils.isNotNull(teacher)) {
				teacher.setUpdateTime(new Date());
				teacherService.updateTeacher(teacher);
			}
		} catch (Exception e) {
			logger.error("AdminTeacherController.updateTeacher", e);
			return setExceptionRequest(request, e);
		}
		return "redirect:/admin/teacher/list";
	}

	/**
	 * 刪除讲师
	 * 
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping("/teacher/delete/{id}")
	public String deleteTeacher(HttpServletRequest request, @PathVariable("id") Long id) {
		try {
			if (ObjectUtils.isNotNull(id)) {
				teacherService.delTeacherByStatus(id);// 刪除讲师
			}
		} catch (Exception e) {
			logger.error("AdminTeacherController.deleteTeacher", e);
			return setExceptionRequest(request, e);
		}
		return "redirect:/admin/teacher/list";
	}

	/**
	 * 通过教师id查询
	 * 
	 * @param request
	 * @param ids
	 * @return
	 */
	@RequestMapping("/teacher/queryByIds")
	@ResponseBody
	public Object queryTeacherByIds(HttpServletRequest request, @ModelAttribute("ids") String ids) {
		try {
			List<QueryTeacher> teacherList = teacherService.queryTeacherInIds(ids);
			this.setJson(true, "success", teacherList);
		} catch (Exception e) {
			logger.error("AdminTeacherController.queryTeacherByIds", e);
			this.setJson(false, "not found", null);
		}
		return json;
	}

	/**
	 * 查询讲师所讲课程
	 * 
	 * @param request
	 * @param model
	 * @param page
	 * @param teacher
	 * @return
	 */
	@RequestMapping("/teacher/selectCourseList")
	public String selectCourseList(HttpServletRequest request, Model model, @ModelAttribute("page") PageEntity page, @ModelAttribute("queryCourseProfile") QueryCourseProfile queryCourseProfile) {
		try {
			this.setPage(page);
			List<CourseProfile> list = courseTeacherService.getCourseByteacher(queryCourseProfile, page);
			// 把返回的数据放到model中
			model.addAttribute("page", this.getPage());
			model.addAttribute("list", list);
		} catch (Exception e) {
			logger.error("AdminTeacherController.selectCourseList", e);
			return setExceptionRequest(request, e);
		}
		return selectCourseList;
	}
}
