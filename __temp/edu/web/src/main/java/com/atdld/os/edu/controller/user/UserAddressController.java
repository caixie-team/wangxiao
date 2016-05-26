package com.atdld.os.edu.controller.user;


import java.util.List;

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

import com.atdld.os.core.util.ObjectUtils;
import com.atdld.os.edu.common.EduBaseController;
import com.atdld.os.edu.controller.course.CourseController;
import com.atdld.os.edu.entity.user.UserAddress;
import com.atdld.os.edu.service.user.UserAddressService;

/**
 * UserAddress管理接口 User:  Date: 2014-05-27
 */
@Controller
public class UserAddressController extends EduBaseController {
	private static final Logger logger = LoggerFactory.getLogger(CourseController.class);

	// 地址管理
	private String address = getViewPath("/ucenter/address");// 收货地址
	
	
	@Autowired
	private UserAddressService userAddressService;

	@InitBinder("userAddress")
	public void initBinderuserAddress(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("userAddress.");
	}
	
	/**
	 * 修改UserAddress
	 * 
	 * @param userAddress
	 *            要修改的UserAddress
	 */
	public void updateUserAddress(UserAddress userAddress) {
		userAddressService.updateUserAddress(userAddress);
	}
	/**
	 * 修改头像
	 */
	@RequestMapping("/uc/address")
	public String userAddress(HttpServletRequest request) {
		try {
			//查询我全部的收货地址
			UserAddress userAddress = new UserAddress();
			userAddress.setUserId(getLoginUserId(request));
			List<UserAddress> userAddressList = userAddressService.getUserAddressList(userAddress);
			request.setAttribute("userAddressList", userAddressList);
		} catch (Exception e) {
			logger.error("UserController.userAddress", e);
			return setExceptionRequest(request, e);
		}
		return address;
	}
	/**
	 * 添加送货地址
	 */
	@RequestMapping("/uc/address/add")
	public String addUserAddress(HttpServletRequest request,@ModelAttribute UserAddress userAddress) {
		try {
			if(ObjectUtils.isNull(userAddress.getId())){
				//添加送货地址
				userAddress.setUserId(getLoginUserId(request));
				userAddress.setSendTime(1);
				userAddressService.addUserAddress(userAddress);
			}else{
				//修改送货地址
				userAddress.setUserId(getLoginUserId(request));
				userAddressService.updateUserAddress(userAddress);
			}
			
		} catch (Exception e) {
			logger.error("UserController.addUserAddress", e);
			return setExceptionRequest(request, e);
		}
		return "redirect:/uc/address";
	}
	/**
	 * 删除送货地址
	 */
	@RequestMapping("/uc/address/del/{id}")
	public String delUserAddress(HttpServletRequest request,@PathVariable Long id) {
		try {
			//删除送货地址
			userAddressService.deleteUserAddressById(id);
		} catch (Exception e) {
			logger.error("UserController.delUserAddress", e);
			return setExceptionRequest(request, e);
		}
		return "redirect:/uc/address";
	}
	/**
	 * 设为常用地址
	 */
	@RequestMapping("/uc/address/common/{id}")
	public String commonUserAddress(HttpServletRequest request,@PathVariable Long id) {
		try {
			//设为常用地址
			userAddressService.updateUserAddressById(id, getLoginUserId(request));
		} catch (Exception e) {
			logger.error("UserController.commonUserAddress", e);
			return setExceptionRequest(request, e);
		}
		return "redirect:/uc/address";
	}
}