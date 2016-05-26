package com.atdld.os.edu.controller.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.atdld.os.edu.service.user.UserLevelService;
import com.atdld.os.edu.common.EduBaseController;
import com.atdld.os.edu.entity.user.UserLevel;

/**
 * 
 * @ClassName com.atdld.os.edu.controller.user.UserLevelController
 * @description
 * @author :
 * @Create Date : 2014年9月30日 下午4:52:26
 */
@Controller
@RequestMapping("/uc")
public class UserLevelController extends EduBaseController {

	private static final Logger logger = LoggerFactory.getLogger(UserLevelController.class);

	private static final String userLevelList = getViewPath("/ucenter/levelhelp");// 用户等级查看
	@Autowired
	private UserLevelService userLevelService;

	/**
	 * 获得等级规则
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/level")
	public String getUserLevelList(HttpServletRequest request, Model model) {
		try {
			// 查询用户等级规则
			List<UserLevel> userLevelist = userLevelService.getUserLevelList();
			model.addAttribute("userLevelist", userLevelist);
		} catch (Exception e) {
			logger.error("UserLevelController.getUserLevelList", e);
			return setExceptionRequest(request, e);
		}
		return userLevelList;
	}
}