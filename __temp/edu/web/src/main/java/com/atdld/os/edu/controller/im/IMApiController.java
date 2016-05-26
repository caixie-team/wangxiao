package com.atdld.os.edu.controller.im;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonObject;
import com.atdld.os.core.service.cache.MemCache;
import com.atdld.os.core.util.ObjectUtils;
import com.atdld.os.core.util.StringUtils;
import com.atdld.os.edu.common.EduBaseController;
import com.atdld.os.edu.entity.user.UserExpand;
import com.atdld.os.edu.entity.user.UserExpandDto;
import com.atdld.os.edu.service.user.UserExpandService;

/**
 * 对外接口 （IM系统专业）
 * @author 李明星
 *
 */
@Controller()
@RequestMapping("/web/im")
public class IMApiController extends EduBaseController{
	private static Logger logger = LoggerFactory.getLogger(IMApiController.class);
	
	private MemCache memCache = MemCache.getInstance();
	@Autowired
	private UserExpandService userExpandService;
	/**
	 * 获取登录用户接口
	 * @param request
	 * @return
	 */
	@RequestMapping("/getloginuser/{sid}")
	@ResponseBody
	public Map<String,Object> qeuryLoginUser(HttpServletRequest request,@PathVariable("sid") String sid){
		try{
			this.setJson(false, null, null);
	        if (StringUtils.isNotEmpty(sid)) {
	            Object  ob =   memCache.get(sid);
	            if(ObjectUtils.isNotNull(ob)){
	                JsonObject user=  jsonParser.parse(ob.toString()).getAsJsonObject();
	                this.setJson(true, null, user);
	            }
	        }
		}catch (Exception e) {
			logger.error("qeuryLoginUser()--error",e);
		}
		return json;
	}
	
	/**
	 * 获取用户，根据用户ID
	 * @param requet
	 * @param userId
	 * @return
	 */
	@RequestMapping("/getuserById/{userId}")
	@ResponseBody
	public Map<String,Object> getUserById(HttpServletRequest requet,@PathVariable("userId") long userId){
		try{
			UserExpandDto user = userExpandService.getUserExpandByUid(userId);
			this.setJson(true, null, user);
		}catch (Exception e) {
			this.setJson(false, null, null);
			logger.error("getUserById()--error",e);
		}
		return json;
	}
}
