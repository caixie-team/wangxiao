package com.atdld.os.edu.controller.user;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.atdld.os.core.entity.PageEntity;
import com.atdld.os.edu.common.EduBaseController;
import com.atdld.os.edu.dao.user.UserDao;
import com.atdld.os.edu.entity.user.UserFeedback;
import com.atdld.os.edu.service.user.UserFeedbackService;

/**
 * UserFeedback管理接口 User:  Date: 2014-10-15
 */
@Controller
public class UserFeedbackController extends EduBaseController {

	@Autowired
	private UserFeedbackService userFeedbackService;

	
	@Autowired
	private UserDao userDao;
	
	
	private static final String free_back = getViewPath("/front/feed_back");
	private static final String admin_back_list = getViewPath("/admin/feed/feed_list");

	// 绑定变量名字和属性，参数封装进类
	@InitBinder("userFeedback")
	public void userFeedback(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("userFeedback.");
	}

	/**
	 * 修改UserFeedback
	 * 
	 * @param userFeedback
	 *            要修改的UserFeedback
	 */
	public void updateUserFeedback(UserFeedback userFeedback) {
		userFeedbackService.updateUserFeedback(userFeedback);
	}

	/**
	 * 用户反馈添加
	 * @param userFeedback
	 * @return
	 */
	@RequestMapping("/front/addfreeback")
	@ResponseBody
	public Map<String,Object> addUserFeedback(HttpServletRequest request, @ModelAttribute UserFeedback userFeedback) {
		try {
			userFeedback.setCreateTime(new Date());
			userFeedbackService.addUserFeedback(userFeedback);
			this.setJson(true, "success", null);
		} catch (Exception e) {
			e.printStackTrace();
			this.setJson(true, "false", null);
		}
		return json;
	}
	/**
	 * 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping("/front/to_free_back")
	public String toAddFreeBack() {
		return free_back;
	}
	/**
	 * 用户反馈页面
	 * @param request
	 * @param page
	 * @param userFeedback
	 * @return
	 */
	@RequestMapping("/admin/feed/feedList")
	public ModelAndView getFeedListByCondition(HttpServletRequest request, @ModelAttribute("page") PageEntity page,@ModelAttribute UserFeedback userFeedback) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(admin_back_list);
		try {
			this.setPage(page);
			List<UserFeedback> userFeedbackList = userFeedbackService.getUserFeedbackListCondtion(userFeedback, page);
			modelAndView.addObject("userFeedbackList", userFeedbackList);
			modelAndView.addObject("page", page);
		} catch (Exception e) {
			setExceptionRequest(request, e);
			e.printStackTrace();
		}
		return modelAndView;
	}
}