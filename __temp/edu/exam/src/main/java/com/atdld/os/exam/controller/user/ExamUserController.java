package com.atdld.os.exam.controller.user;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonObject;
import com.atdld.os.common.contoller.SingletonLoginUtils;
import com.atdld.os.common.service.WebHessianService;
import com.atdld.os.core.util.ObjectUtils;
import com.atdld.os.exam.controller.common.ExamBaseController;

/**
 * @author :
 * @ClassName com.atdld.os.sns.controller.user.UserAction
 * @description
 * @Create Date : 2014-1-17 下午5:26:00
 */
@Controller
public class ExamUserController extends ExamBaseController {

    private static final Logger logger = Logger.getLogger(ExamUserController.class);

    @Autowired
    private WebHessianService webHessianService;

    // 去登录页面 登录页面 注释掉

	/*
     * @RequestMapping("/login") public String toIndex() { return
	 * "/login/login"; }
	 */

    /**
	 * 查询登陆用户id
	 * 
	 * @return
	 */
	@RequestMapping("/user/loginuser")
	@ResponseBody
	public Object loginuser(HttpServletRequest request) {
		try {
			JsonObject userJsonObject = SingletonLoginUtils.getLoginUser(request);
			if (ObjectUtils.isNotNull(userJsonObject)) {
				userJsonObject.addProperty("password","");
				userJsonObject.addProperty("customerkey", "");
				this.setJson(true, null, userJsonObject);
			} else {
				this.setJson(false, null, null);
			}
		} catch (Exception e) {
			logger.error("UserController.exit", e);
			this.setJson(false, "", null);
		}
		return json;
	}
	
  

  


}
