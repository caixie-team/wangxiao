package com.atdld.os.edu.controller.course;

import com.atdld.os.core.entity.PageEntity;
import com.atdld.os.core.util.DateUtils;
import com.atdld.os.core.util.ObjectUtils;
import com.atdld.os.edu.common.EduBaseController;
import com.atdld.os.edu.constants.enums.SellType;
import com.atdld.os.edu.dao.course.CourseMemberDao;
import com.atdld.os.edu.entity.course.*;
import com.atdld.os.edu.entity.member.MemberType;
import com.atdld.os.edu.entity.website.WebsiteCourse;
import com.atdld.os.edu.service.course.*;
import com.atdld.os.edu.service.member.MemberTypeService;
import com.atdld.os.edu.service.website.WebsiteCourseService;
import com.atdld.os.sysuser.entity.QuerySubject;
import com.atdld.os.sysuser.entity.Subject;
import com.atdld.os.sysuser.service.SubjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Course管理接口 User:  Date: 2014-05-27
 */
@Controller
@RequestMapping("/admin")
public class AdminCourseController extends EduBaseController {
	private static final Logger logger = LoggerFactory.getLogger(AdminCourseController.class);

	// 课程列表
	private static final String showCourseList = getViewPath("/admin/course/course_list");//课程列表
	private static final String showRecommendCourseList = getViewPath("/admin/course/course_recommend_list");//课程列表(推荐课程)
	private static final String showCouponCourseList = getViewPath("/admin/course/course_coupon_list");//课程列表(优惠券限制课程)
	private static final String toAddCourse = getViewPath("/admin/course/add_course");//添加课程
	private static final String toUpdateCourse = getViewPath("/admin/course/update_course");//更新课程
	private static final String packCourseList = getViewPath("/admin/course/course_package_list");//该课程的课程包列表
	private static final String selectCourseList = getViewPath("/admin/course/select_course_list");//课程选择
	private static final String openerCourseList=getViewPath("/admin/course/course_opener_list");//小窗口课程列表页面（礼品课程使用）
	
	
	@Autowired
	private CourseService courseService;
	@Autowired
	private SubjectService subjectService;
	@Autowired
	private WebsiteCourseService websiteCourseService;
	@Autowired
    private CourseMemberDao courseMemberDao;
	@Autowired
    private MemberTypeService memberTypeService;
	@Autowired
    private CourseMemberService courseMemberService;
	@Autowired
	private CoursePackageService coursePackageService;
	// 绑定变量名字和属性，参数封装进类
	@InitBinder("queryCourse")
	public void initBinderQueryCourse(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("queryCourse.");
	}

	@InitBinder("course")
	public void initBinderCourse(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("course.");
	}

	/**
	 * 课程列表展示
	 * 
	 * @return
	 */
	@RequestMapping("/cou/list")
	public String showCourseList(HttpServletRequest request, @ModelAttribute("page") PageEntity page,
			@ModelAttribute("queryCourse") QueryCourse queryCourse) {
		try {
			// 页面传来的数据放到page中
			this.setPage(page);
			this.getPage().setPageSize(12);
			//添加时间倒叙
			queryCourse.setOrder(2);
			// 搜索课程列表
			List<CourseDto> courseList = courseService.getCourseListPage(queryCourse, page);
			List<Long> ids=new ArrayList<Long>();
			if(courseList!=null&&courseList.size()>0){
				for(CourseDto courseDto:courseList){
	            	ids.add(courseDto.getId());
	            }
			}
            
            // 获取会员的list
            Map<Long, List<MemberType>> map = courseMemberDao.getCourseMemberTypeListByCourse(ids);
            // 将会员的list放到旧的list中
            if(courseList!=null&&courseList.size()>0){
	            for(CourseDto courseDto:courseList){
	            	if(map.get(courseDto.getId())==null){
	            		courseDto.setMemberTypes(new ArrayList<MemberType>());
	            	}else{
	            		courseDto.setMemberTypes(map.get(courseDto.getId()));
	            	}
	                
	            }
            }
			request.setAttribute("courseList", courseList);
			request.setAttribute("page", this.getPage());
			request.setAttribute("course", queryCourse);
			 // 获得一级项目
            QuerySubject querySubject = new QuerySubject();
            List<Subject> subjectList = subjectService.getSubjectList(querySubject);
            request.setAttribute("subjectList", gson.toJson(subjectList));
            //会员类型
            List<MemberType> memberTypes=memberTypeService.getWebMemberTypes();
            request.setAttribute("memberTypes",memberTypes);
		} catch (Exception e) {
			logger.error("CourseController.showCourseList", e);
			return setExceptionRequest(request, e);
		}
		return showCourseList;
	}
	
	/**
	 * 课程列表(推荐课程用)
	 * 
	 * @return
	 */
	@RequestMapping("/cou/recommendCourseList")
	public String showCourseListByRecommend(HttpServletRequest request, @ModelAttribute("page") PageEntity page,
			@ModelAttribute("queryCourse") QueryCourse queryCourse) {
		try {
			// 页面传来的数据放到page中
			this.setPage(page);
			this.getPage().setPageSize(12);
			//添加时间倒叙
			queryCourse.setOrder(2);
            //只查询上架的
            queryCourse.setIsavaliable(0L);
			// 搜索课程列表
			List<CourseDto> courseList = courseService.getCourseListPage(queryCourse, page);
			request.setAttribute("courseList", courseList);
			request.setAttribute("page", this.getPage());
			request.setAttribute("course", queryCourse);
			List<WebsiteCourse> websiteCourses=websiteCourseService.queryWebsiteCourseList();
			request.setAttribute("websiteCourses", websiteCourses);
		} catch (Exception e) {
			logger.error("CourseController.showCourseListByRecommend", e);
			return setExceptionRequest(request, e);
		}
		return showRecommendCourseList;
	}

	/**
	 * 课程列表(优惠券限制课程用)
	 * 
	 * @return
	 */
	@RequestMapping("/cou/couponCourseList")
	public String showCourseListByCoupon(HttpServletRequest request, @ModelAttribute("page") PageEntity page,
			@ModelAttribute("queryCourse") QueryCourse queryCourse) {
		try {
			// 页面传来的数据放到page中
			this.setPage(page);
			this.getPage().setPageSize(12);
			//添加时间倒叙
			queryCourse.setOrder(2);
            //只查询上架的
            queryCourse.setIsavaliable(0L);
			// 搜索课程列表
			List<CourseDto> courseList = courseService.getCourseListPage(queryCourse, page);
			request.setAttribute("courseList", courseList);
			request.setAttribute("page", this.getPage());
			request.setAttribute("course", queryCourse);
			List<WebsiteCourse> websiteCourses=websiteCourseService.queryWebsiteCourseList();
			request.setAttribute("websiteCourses", websiteCourses);
		} catch (Exception e) {
			logger.error("CourseController.showCourseListByRecommend", e);
			return setExceptionRequest(request, e);
		}
		return showCouponCourseList;
	}
	
	/**
	 * 到添加课程页面
	 */
	@RequestMapping("/cou/toAddCourse")
	public String toAddCourse(HttpServletRequest request) {
		try {
			//查询专业
            QuerySubject querySubject = new QuerySubject();
            List<Subject> subjectList = subjectService.getSubjectList(querySubject);
            request.setAttribute("subjectList", gson.toJson(subjectList));
            //会员类型
            List<MemberType> memberTypes=memberTypeService.getWebMemberTypes();
            request.setAttribute("memberTypes",memberTypes);
		} catch (Exception e) {
			logger.error("CourseController.toAddCourse", e);
			return setExceptionRequest(request, e);
		}
		return toAddCourse;
	}
	@Autowired
	private CourseTeacherService courseTeacherService;
	@Autowired
	private CourseSubjectService courseSubjectService;
	/**
	 * 到更新课程页面
	 */
	@RequestMapping("/cou/toUpdate/{id}")
	public String toUpdateCourse(HttpServletRequest request, @PathVariable("id") Long id) {
		try {
			Course course = courseService.getCourseById(id);
			//查询课程关联的教师
			CourseTeacher courseTeacher = new CourseTeacher();
			courseTeacher.setCourseId(id);
			List<CourseTeacher> courseTeacherList = courseTeacherService.getCourseTeacherList(courseTeacher);
			//整理关联的教师id，放入课程里
			if(ObjectUtils.isNotNull(courseTeacherList)){
				String teacherIds = "";
				for(CourseTeacher ct:courseTeacherList){
					teacherIds+=ct.getTeacherId()+",";
				}
				teacherIds= teacherIds.substring(0, teacherIds.length()-1);
				course.setTeacherIds(teacherIds);
			}
			//查询课程专业
			CourseSubject courseSubject = new CourseSubject();
			courseSubject.setCourseId(id);
			List<CourseSubject> courseSubjectList = courseSubjectService.getCourseSubjectList(courseSubject);
			if(ObjectUtils.isNotNull(courseSubjectList)){
				course.setSubjectId(courseSubjectList.get(0).getSubjectId());
			}
			//查询课程会员
			List<MemberType> couMemberTypes = memberTypeService.getMemberTypesBycourse(id);
			if(ObjectUtils.isNotNull(couMemberTypes)){
				course.setMemberTypes(couMemberTypes);
			}
			request.setAttribute("course", course);
			//查询专业
            QuerySubject querySubject = new QuerySubject();
            List<Subject> subjectList = subjectService.getSubjectList(querySubject);
            request.setAttribute("subjectList", gson.toJson(subjectList));
            //会员类型
            List<MemberType> memberTypes=memberTypeService.getWebMemberTypes();
            request.setAttribute("memberTypes",memberTypes);
		} catch (Exception e) {
			logger.error("CourseController.toAddCourse", e);
			return setExceptionRequest(request, e);
		}
		return toUpdateCourse;
	}

	/**
	 *更新课程
	 */
	@RequestMapping("/cou/update")
	public String updateCourse(HttpServletRequest request, @ModelAttribute("course") Course course,@RequestParam(value = "courseMember") String courseMember) {
		try {
			course.setUpdateuser(getSysLoginLoginName(request));
			course.setCoursetag("");
			courseService.updateCourse(course);
			//先删除课程所属会员
			courseMemberService.delCourseMember(course.getId());
			//添加课程所属会员
			if(courseMember!=""){//课程所属会员
				List<CourseMember> courseMembers=new ArrayList<CourseMember>();
				String[] arry=courseMember.replaceAll(",", " ").trim().split(" ");//课程所属会员数组
				for(String s:arry){
					CourseMember cm=new CourseMember();
					cm.setCourseId(course.getId());//课程id
					cm.setMemberTypeId(Long.parseLong(s));//会员类型id
					courseMembers.add(cm);
				}
				courseMemberService.addCourseMember(courseMembers);//添加课程所属会员
			}
		} catch (Exception e) {
			logger.error("CourseController.toAddCourse", e);
			return setExceptionRequest(request, e);
		}
		return "redirect:/admin/cou/list";
	}
	/**
	 * 添加课程
	 */
	@RequestMapping("/cou/addCourse")
	public String addCourse(HttpServletRequest request, @ModelAttribute("course") Course course,@RequestParam(value = "courseMember") String courseMember) {
		try {
			course.setUpdateuser(getSysLoginLoginName(request));
			course.setCoursetag("");
			course.setOrderNum(0L); // 设置排序默认为0
			courseService.addCourse(course);
			//添加课程所属会员
			if(courseMember!=""){//课程所属会员
				List<CourseMember> courseMembers=new ArrayList<CourseMember>();
				String[] arry=courseMember.replaceAll(",", " ").trim().split(" ");//课程所属会员数组
				for(String s:arry){
					CourseMember cm=new CourseMember();
					cm.setCourseId(course.getId());//课程id
					cm.setMemberTypeId(Long.parseLong(s));//会员类型id
					courseMembers.add(cm);
				}
				courseMemberService.addCourseMember(courseMembers);//添加课程所属会员
			}
		} catch (Exception e) {
			logger.error("CourseController.toAddCourse", e);
			return setExceptionRequest(request, e);
		}
		return "redirect:/admin/cou/list";
	}
	/**
	 * 删除课程
	 */
	@RequestMapping("/cou/del/{id}")
	public String delCourse(HttpServletRequest request,@PathVariable("id") Long id) {
		try {
			courseService.deleteCourseById(id);
		} catch (Exception e) {
			logger.error("CourseController.delCourse", e);
			return setExceptionRequest(request, e);
		}
		return "redirect:/admin/cou/list";
	}
	/**
	 * 课程包列表
	 */
	@RequestMapping("/cou/pack/{courseId}")
	public String packCourseList(HttpServletRequest request,@PathVariable("courseId") Long courseId, Course course) {
		try {
			course.setId(courseId);			
			List<Course> CourseDtoList = courseService.getCourseListPackageByCondition(course);
			 // 获得一级项目
            QuerySubject querySubject = new QuerySubject();
            List<Subject> subjectList = subjectService.getSubjectList(querySubject);
            request.setAttribute("subjectList", gson.toJson(subjectList));
			request.setAttribute("CourseDtoList", CourseDtoList);
			request.setAttribute("course", course);
		} catch (Exception e) {
			logger.error("CourseController.packCourseList", e);
			return setExceptionRequest(request, e);
		}
		return packCourseList;
	}
	
	/**
	 * 课程包选择课程
	 * 
	 * @return
	 */
	@RequestMapping("/cou/select")
	public String selectCourseList(HttpServletRequest request, @ModelAttribute("page") PageEntity page,
			@ModelAttribute("queryCourse") QueryCourse queryCourse) {
		try {
			// 页面传来的数据放到page中
			this.setPage(page);
			this.getPage().setPageSize(12);
			//添加时间倒叙
			queryCourse.setOrder(2);
			queryCourse.setSellType(SellType.COURSE.toString());
            //只查询上架的
            queryCourse.setIsavaliable(0L);

			// 搜索课程列表
			List<CourseDto> courseList = courseService.getCourseListPage(queryCourse, page);
			request.setAttribute("courseList", courseList);
			request.setAttribute("page", this.getPage());
			request.setAttribute("course", queryCourse);
		} catch (Exception e) {
			logger.error("CourseController.selectCourseList", e);
			return setExceptionRequest(request, e);
		}
		return selectCourseList;
	}

	/**
	 * 课程包添加课程
	 */
	@RequestMapping("/cou/addPack")
	@ResponseBody
	public Map<String, Object> addtCourseList(HttpServletRequest request,@ModelAttribute("ids") String ids,@ModelAttribute("courseId") Long courseId) {
		try {
			coursePackageService.addCoursePackageBatch(ids, courseId);
			this.setJson(true, "success", null);
		} catch (Exception e) {
			logger.error("CourseController.addtCourseList", e);
			this.setJson(false, "false", null);
		}
		return json;
	}
	/**
	 * 删除课程包添加的课程
	 */
	@RequestMapping("/cou/delPack/{courseId}/{mainCourseId}")
	@ResponseBody
	public Map<String, Object> delCourseList(HttpServletRequest request,@PathVariable("courseId") Long courseId,@PathVariable("mainCourseId") Long mainCourseId) {
		try {
			CoursePackage coursePackage = new CoursePackage();
			coursePackage.setCourseId(courseId);
			coursePackage.setMainCourseId(mainCourseId);
			coursePackageService.delCoursePackage(coursePackage);
			this.setJson(true, "success", null);
		} catch (Exception e) {
			logger.error("CourseController.delCourseList", e);
			this.setJson(false, "false", null);
		}
		return json;
	}
	/**
	 * 课程列表展示
	 * 
	 * @return
	 */
	@RequestMapping("/cou/openerlist")
	public String openerCourseList(HttpServletRequest request, @ModelAttribute("page") PageEntity page,
			@ModelAttribute("queryCourse") QueryCourse queryCourse) {
		try {
			// 页面传来的数据放到page中
			this.setPage(page);
			this.getPage().setPageSize(12);
			//添加时间倒叙
			queryCourse.setOrder(2);
			// 搜索课程列表
			List<CourseDto> courseList = courseService.getCourseListPage(queryCourse, page);
			request.setAttribute("courseList", courseList);
			request.setAttribute("page", this.getPage());
			request.setAttribute("course", queryCourse);
			 // 获得一级项目
            QuerySubject querySubject = new QuerySubject();
            List<Subject> subjectList = subjectService.getSubjectList(querySubject);
            request.setAttribute("subjectList", gson.toJson(subjectList));
		} catch (Exception e) {
			logger.error("CourseController.showCourseList", e);
			return setExceptionRequest(request, e);
		}
		return openerCourseList;
	}
	
	/**
	 * 判断选中课程是否已过期
	 * */
	@RequestMapping("/cou/courseOverdue")
	@ResponseBody
	public Map<String,Object> courseOverdue(HttpServletRequest request,@RequestParam("courseId") Long courseId){
		try{
			Course course=courseService.getCourseById(courseId);
			if(ObjectUtils.isNotNull(course)){
				if(course.getLosetype()==0){
					Date date = new Date();// 今天
	                String nowStr = DateUtils.formatDate(date, "yyyy-MM-dd");
	                String authStr=DateUtils.formatDate(course.getLoseAbsTime(), "yyyy-MM-dd");
	                if( Integer.valueOf(DateUtils.getTwoDay(authStr, nowStr))+1>0){
	                	this.setJson(true, "success", null);
	                }else{
	                	this.setJson(true, "overdue", null);
	                }
				}else{
					this.setJson(true, "success", null);
				}
			}else{
				this.setJson(true, "noCourse", null);
			}
			
		}catch(Exception e){
			logger.error("CourseController.courseOverdue", e);
			this.setJson(false, "系统异常", null);
		}
		return json;
	}
	
	/**
	 * 修改课程排序值
	 * @param course
	 * @return
	 */
	@RequestMapping("/cou/updateOrderNum")
	@ResponseBody
	public Map<String, Object> updateCourseOrderNum(Course course){
		try {
			CoursePackage coursep = new CoursePackage();
			coursep.setCourseId(course.getId());
			coursep.setOrderNum(course.getOrderNum());
			coursePackageService.updateCoursePackageOrderNum(coursep);
			this.setJson(true, "修改成功", null);
		} catch (Exception e) {
			logger.error("CourseController.updateCourseOrderNum", e);
			this.setJson(false, "系统异常", null);
		}
		return json;
	}
}