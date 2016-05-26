package com.atdld.os.edu.controller.website;


import java.util.ArrayList;
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

import com.atdld.os.core.entity.PageEntity;
import com.atdld.os.core.util.ObjectUtils;
import com.atdld.os.core.util.StringUtils;
import com.atdld.os.edu.common.EduBaseController;
import com.atdld.os.edu.entity.website.WebsiteCourse;
import com.atdld.os.edu.entity.website.WebsiteCourseDetail;
import com.atdld.os.edu.entity.website.WebsiteCourseDetailDTO;
import com.atdld.os.edu.service.website.WebsiteCourseDetailService;
import com.atdld.os.edu.service.website.WebsiteCourseService;

/**
 * 推荐模块课程分类
 * 
 * @ClassName com.atdld.os.edu.controller.website.AdminWebsiteCourseController
 * @description
 * @author :
 * @Create Date : 2014年6月7日 上午9:47:26
 */
@Controller
@RequestMapping("/admin")
public class AdminWebsiteCourseController extends EduBaseController {

	private static final Logger logger = LoggerFactory.getLogger(AdminWebsiteCourseController.class);

	@Autowired
	private WebsiteCourseService websiteCourseService;
	@Autowired
	private WebsiteCourseDetailService websiteCourseDetailService;

	private static final String getWebsiteCourseList = getViewPath("/admin/website/course/websiteCourse_list");
	private static final String updateWebsiteCourse = getViewPath("/admin/website/course/websiteCourse_update");
	private static final String addWebsiteCourse = getViewPath("/admin/website/course/websiteCourse_add");
	private static final String getWebsiteCourseDetailList = getViewPath("/admin/website/course/websiteCourseDetail_list");
	private static final String updateWebsiteCourseDetail = getViewPath("/admin/website/course/websiteCourseDetail_update");
	// 创建群 绑定变量名字和属性，把参数封装到类
	@InitBinder("websiteCourse")
	public void initBinderWebsiteCourse(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("websiteCourse.");
	}
	@InitBinder("websiteCourseDetail")
	public void initBinderWebsiteCourseDetail(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("websiteCourseDetail.");
	}
	@InitBinder("websiteCourseDetailDTO")
	public void initBinderWebsiteCourseDetailDTO(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("websiteCourseDetailDTO.");
	}
	/**
	 * 添加课程课程分类
	 * 
	 * @param request
	 * @param websiteCourse
	 * @return
	 */
	@RequestMapping("/website/addCourse")
	public String addWebsiteCourse(HttpServletRequest request,WebsiteCourse websiteCourse) {
		try {
			if (ObjectUtils.isNotNull(websiteCourse)) {
				websiteCourseService.addWebsiteCourse(websiteCourse);
			}
		} catch (Exception e) {
			logger.error("AdminWebsiteCourseController.addWebsiteNavigates--添加推荐课程分类出错", e);
			return setExceptionRequest(request, e);
		}
		return "redirect:/admin/website/websiteCoursePage";
	}

	/**
	 * 查询课程分类
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/website/websiteCoursePage")
	public ModelAndView getWebsiteCourseList(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView(getWebsiteCourseList);
		try {
			// 查询模块课程分类
			List<WebsiteCourse> websiteCourseList = websiteCourseService.queryWebsiteCourseList();
			// 把websiteCourseList放到modelAndView
			modelAndView.addObject("websiteCourseList", websiteCourseList);
		} catch (Exception e) {
			logger.error("AdminWebsiteCourseController.getWebsiteCourseList--查询课程分类列表出错", e);
			return new ModelAndView(setExceptionRequest(request, e));
		}
		return modelAndView;
	}

	/**
	 * 删除课程分类
	 * 
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping("/website/delWebsiteCourseById/{id}")
	@ResponseBody
	public Map<String, Object> delWebsiteCourseById(HttpServletRequest request, @PathVariable Long id) {
		try {
			if (StringUtils.isNotEmpty(id.toString())) {
				// 删除课程分类
				websiteCourseService.deleteWebsiteCourseDetailById(id);
				this.setJson(true, "true", null);
			}
		} catch (Exception e) {
			logger.error("AdminWebsiteCourseController.deleteWebsiteCourseById--删除课程分类出错", e);
			this.setJson(false, "error", null);
		}
		return json;
	}

	/**
	 * 添加课程分类跳转
	 * 
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping("/website/doAddWebsiteCourse")
	public ModelAndView getWebsiteCourse() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(addWebsiteCourse);
		return modelAndView;
	}
	/**
	 * 更新课程分类跳转
	 * 
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping("/website/doUpdateWebsiteCourse/{id}")
	public ModelAndView doUpdateWebsiteCourse(HttpServletRequest request, @PathVariable("id") Long id) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(updateWebsiteCourse);
		try {
			// 获得websiteCourse
			WebsiteCourse websiteCourse = websiteCourseService.queryWebsiteCourseById(id);
			modelAndView.addObject("websiteCourse", websiteCourse);
		} catch (Exception e) {
			logger.error("AdminWebsiteCourseController.doUpdateWebsiteCourse--更新课程分类跳转出错", e);
			return new ModelAndView(setExceptionRequest(request, e));
		}
		return modelAndView;
	}
	/**
	 * 修改课程分类
	 * 
	 * @param request
	 * @param websiteCourse
	 * @return
	 */
	@RequestMapping("/website/updateWebsiteCourse")
	public String updateWebsiteCourse(HttpServletRequest request, WebsiteCourse websiteCourse) {
		try {
			if (ObjectUtils.isNotNull(websiteCourse)) {
				websiteCourseService.updateWebsiteCourseById(websiteCourse);
			}
		} catch (Exception e) {
			logger.error("AdminWebsiteCourseController.updateWebsiteCourse--修改课程分类分类出错", e);
			return setExceptionRequest(request, e);
		}
		return "redirect:/admin/website/websiteCoursePage";
	}
	
	/**
	 * 添加推荐课程
	 * 
	 * @param request
	 * @param WebsiteCourseDetail
	 * @return
	 */
	@RequestMapping("/website/addCourseDetail")
	@ResponseBody
	public Map<String,Object> addWebsiteCourse(HttpServletRequest request) {
		try {
			String ids=request.getParameter("ids");
			Long id=Long.parseLong(request.getParameter("recommendId"));
			int exitSize=websiteCourseDetailService.getWebsiteCourseDetails(id).size();
			WebsiteCourse websiteCourse=websiteCourseService.queryWebsiteCourseById(id);
			if (ObjectUtils.isNotNull(ids)){//添加推荐课程
				String[] idsArry=ids.split(",");
				if (websiteCourse.getCourseNum()>=(idsArry.length+exitSize)) {//未超过该分类课程上限
					List<WebsiteCourseDetail> websiteCourseDetails=new ArrayList<WebsiteCourseDetail>();
					for(int i=0;i<idsArry.length;i++){
						WebsiteCourseDetail websiteCourseDetail=new WebsiteCourseDetail();
						websiteCourseDetail.setCourseId(Long.parseLong(idsArry[i]));//课程id
						websiteCourseDetail.setOrderNum(0);//排序
						websiteCourseDetail.setRecommendId(id);//分类id
						websiteCourseDetails.add(websiteCourseDetail);
					}
					websiteCourseDetailService.addWebsiteCourseDetail(websiteCourseDetails);
					this.setJson(true, "true", null);
				}else{
					this.setJson(false, "than", websiteCourse);
				}
			}
		} catch (Exception e) {
			logger.error("AdminWebsiteCourseController.addWebsiteNavigates--添加推荐课程分类出错", e);
			this.setJson(false, "error", null);
		}
		return json;
	}

	/**
	 * 查询推荐课程列表
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/website/websiteCourseDetailPage")
	public ModelAndView getWebsiteCourseDetailList(HttpServletRequest request,WebsiteCourseDetailDTO websiteCourseDetailDTO,@ModelAttribute("page") PageEntity page) {
		ModelAndView modelAndView = new ModelAndView(getWebsiteCourseDetailList);
		try {
			// 查询推荐课程
			this.setPage(page);
			this.getPage().setPageSize(10);
			List<WebsiteCourseDetailDTO> websiteCourseDetailDTOList = websiteCourseDetailService.queryWebsiteCourseDetailList(websiteCourseDetailDTO, page);
			modelAndView.addObject("websiteCourseDetailDTOList", websiteCourseDetailDTOList);
			List<WebsiteCourse> websiteCourses=websiteCourseService.queryWebsiteCourseList();
			request.setAttribute("websiteCourses", websiteCourses);
			modelAndView.addObject("page",this.getPage());
		} catch (Exception e) {
			logger.error("AdminWebsiteCourseController.getWebsiteCourseDetailList--查询推荐课程列表出错", e);
			return new ModelAndView(setExceptionRequest(request, e));
		}
		return modelAndView;
	}

	/**
	 * 删除推荐课程
	 * 
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping("/website/delWebsiteCourseDetailById/{id}")
	@ResponseBody
	public Map<String, Object> deleteWebsiteCourseDetailById(HttpServletRequest request, @PathVariable Long id) {
		try {
			if (StringUtils.isNotEmpty(id.toString())) {
				// 删除课程分类
				websiteCourseDetailService.deleteWebsiteCourseDetail(id);
				this.setJson(true, "true", null);
			}
		} catch (Exception e) {
			logger.error("AdminWebsiteCourseController.delWebsiteCourseDetailById--删除课程分类出错", e);
			this.setJson(false, "false", null);
		}
		return json;
	}

	/**
	 * 更新推荐课程跳转
	 * 
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping("/website/doUpdateWebsiteCourseDetail/{id}")
	public ModelAndView doUpdateWebsiteCourseDetail(HttpServletRequest request, @PathVariable("id") Long id) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(updateWebsiteCourseDetail);
		try {
			// 获得websiteCourse
			WebsiteCourseDetailDTO websiteCourseDetailDTO = websiteCourseDetailService.queryWebsiteCourseDetailById(id);
			modelAndView.addObject("websiteCourseDetailDTO", websiteCourseDetailDTO);
		} catch (Exception e) {
			logger.error("AdminWebsiteCourseController.doUpdateWebsiteCourseDetail--更新推荐课程跳转出错", e);
			return new ModelAndView(setExceptionRequest(request, e));
		}
		return modelAndView;
	}
	/**
	 * 修改推荐课程
	 * 
	 * @param request
	 * @param websiteCourse
	 * @return
	 */
	@RequestMapping("/website/updateWebsiteCourseDetail")
	public String updateWebsiteCourseDetail(HttpServletRequest request, WebsiteCourseDetail websiteCourseDetail) {
		try {
			if (ObjectUtils.isNotNull(websiteCourseDetail)) {
				websiteCourseDetailService.updateWebsiteCourseDetail(websiteCourseDetail);
			}
		} catch (Exception e) {
			logger.error("AdminWebsiteCourseController.updateWebsiteCourse--修改推荐课程出错", e);
			return setExceptionRequest(request, e);
		}
		return "redirect:/admin/website/websiteCourseDetailPage";
	}
}
