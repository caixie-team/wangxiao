package com.atdld.os.edu.controller.user;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atdld.os.core.entity.PageEntity;
import com.atdld.os.core.util.ObjectUtils;
import com.atdld.os.core.util.web.WebUtils;
import com.atdld.os.edu.common.EduBaseController;
import com.atdld.os.edu.constants.enums.IntegralKeyword;
import com.atdld.os.edu.entity.user.UserAddress;
import com.atdld.os.edu.entity.user.UserIntegral;
import com.atdld.os.edu.entity.user.UserIntegralGift;
import com.atdld.os.edu.entity.user.UserIntegralRecord;
import com.atdld.os.edu.entity.user.UserIntegralTemplate;
import com.atdld.os.edu.service.user.UserAddressService;
import com.atdld.os.edu.service.user.UserExpandService;
import com.atdld.os.edu.service.user.UserIntegralGiftService;
import com.atdld.os.edu.service.user.UserIntegralRecordService;
import com.atdld.os.edu.service.user.UserIntegralService;
import com.atdld.os.edu.service.user.UserIntegralTemplateService;
import com.atdld.os.edu.service.user.UserService;

/**
 * 
 * @ClassName com.atdld.os.edu.controller.user.UserIntegralController
 * @description 个人中心 积分
 * @author :
 * @Create Date : 2014年9月30日 下午1:18:13
 */
@Controller
@RequestMapping("/uc")
public class UserIntegralController extends EduBaseController {

	private static final Logger logger = LoggerFactory.getLogger(UserIntegralController.class);

	private static final String toMyIntegralList = getViewPath("/ucenter/integral");// 个人中心积分列表
	private static final String toAjaxIntegralRecord = getViewPath("/ucenter/ajaxIntegralRecord");// 个人中心积分列表
	private static final String doExchangeIntegralGift = getViewPath("/ucenter/doExchangeIntegralGift");// 个人中心积分兑换页
	

	@Autowired
	private UserIntegralService userIntegralService;
	@Autowired
	private UserIntegralRecordService userIntegralRecordService;
	@Autowired
	private UserIntegralTemplateService userIntegralTemplateService;
	@Autowired
	private UserIntegralGiftService userIntegralGiftService;
	@Autowired
	private UserService userService;
	@Autowired
    private UserExpandService userExpandService;
	@Autowired
	private UserAddressService userAddressService;
	
	/**
	 * 查询用户积分
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/myinte")
	public String toMyIntegralList(HttpServletRequest request, Model model) {
		try {
			// 获得用户Id
			Long userId = getLoginUserId(request);
			// 根据用户Id获得积分
			UserIntegral userIntegral = userIntegralService.getUserIntegralByUserId(userId);
			// 查询积分模板
			List<UserIntegralTemplate> userIntegralTemplateList = userIntegralTemplateService.getUserIntegralTemplateList();
			model.addAttribute("userId", userId);// 用户Id放到model
			model.addAttribute("userIntegral", userIntegral);// 用户积分信息放到model
			model.addAttribute("userIntegralTemplateList", userIntegralTemplateList);// 积分模板信息放到model
		} catch (Exception e) {
			logger.error("UserIntegralController.toMyIntegralList", e);
			return setExceptionRequest(request, e);
		}
		return toMyIntegralList;
	}

	/**
	 * 获得用户的历史记录
	 * 
	 * @param request
	 * @param model
	 * @param page
	 * @return
	 */
	@RequestMapping("/ajax/intrecord")
	public String getIntegraRecord(HttpServletRequest request, Model model, @ModelAttribute("page") PageEntity page) {
		try {
			// 设置分页参数
			this.setPage(page);
			this.getPage().setPageSize(10);
			UserIntegralRecord userIntegralRecord = new UserIntegralRecord();
			userIntegralRecord.setUserId(getLoginUserId(request));// 传用户userId
			// 查询用户积分历史记录
			List<UserIntegralRecord> userIntegralRecordList = userIntegralRecordService.getUserIntegralRecordListPage(userIntegralRecord, this.getPage());
			model.addAttribute("userIntegralRecordList", userIntegralRecordList);// 用户积分记录
			model.addAttribute("page", this.getPage());// 分页参数
		} catch (Exception e) {
			logger.error("UserIntegralController.getIntegralRecord", e);
			return setExceptionRequest(request, e);
		}
		return toAjaxIntegralRecord;
	}


	
	/**
	 * 用户积分兑换礼品验证
	 * 
	 * @param request
	 * @param giftId
	 * @return
	 */
	@RequestMapping("/exchange/check/{giftId}")
	@ResponseBody
	public Map<String, Object> exchangeIntegralGiftCheck(HttpServletRequest request, @PathVariable("giftId") Long giftId) {
		try {
			//根据礼品Id查询礼品
			UserIntegralGift userIntegralGift=userIntegralGiftService.getUserIntegralGiftById(giftId);
			if(ObjectUtils.isNull(userIntegralGift)){//判断礼品是否存在
				this.setJson(false, "该礼品不存在", null);
				return json;
			}
			//查询收货地址
			UserAddress userAddress = new UserAddress();
			userAddress.setUserId(getLoginUserId(request));
			List<UserAddress> userAddressList=userAddressService.getUserAddressList(userAddress);
			
			if(ObjectUtils.isNull(userAddressList)||userAddressList.size()==0){
				this.setJson(false, "请完善收货地址再进行兑换", null);
				return json;
			}
			//查询积分模板 根据gift关键字
			UserIntegralTemplate userIntegralTemplate=userIntegralTemplateService.getUserIntegralTemplateByKeyword(IntegralKeyword.gift.toString());
			if(ObjectUtils.isNull(userIntegralTemplate)){
				this.setJson(false, "系统错误，请联系相关工作人员", null);
				return json;	
			}
			//获得用户积分
			UserIntegral userIntegral=userIntegralService.getUserIntegralByUserId(getLoginUserId(request));
			//兑换标示 是否够积分进行兑换
			//boolean isExchange=false;
			Integer isExchange=userIntegral.getCurrentScore().intValue()+userIntegralGift.getScore().intValue()*userIntegralTemplate.getScore().intValue();
			if(isExchange<0){//积分不足
				this.setJson(false, "积分不足", null);
				return json;
			}
			this.setJson(true, "success", null);
			
		} catch (Exception e) {
			logger.error("exchangeIntegralGiftCheck", e);
		}
		return json;
	}
	
	
	
	/**
	 * 用户积分兑换礼品验证
	 * 
	 * @param request
	 * @param giftId
	 * @return
	 */
	@RequestMapping("/gift/doexchange/{giftId}")
	public String doExchangeIntegralGift(HttpServletRequest request,@PathVariable("giftId") Long giftId) {
		try {
			//根据礼品Id查询礼品
			UserIntegralGift userIntegralGift=userIntegralGiftService.getUserIntegralAndCourseGiftById(giftId);
			if(ObjectUtils.isNull(userIntegralGift)){//判断礼品是否存在
				request.setAttribute("msg", "兑换的礼品不存在或已删除");
				return "redirect:/front/success";
			}
			request.setAttribute("gift", userIntegralGift);
			//查询收货地址
			UserAddress userAddress = new UserAddress();
			userAddress.setUserId(getLoginUserId(request));
			List<UserAddress> userAddressList=userAddressService.getUserAddressList(userAddress);
			request.setAttribute("userAddressList", userAddressList);
		} catch (Exception e) {
			logger.error("doExchangeIntegralGift", e);
		}
		return doExchangeIntegralGift;
	}
	
	/**
	 * 用户积分兑换礼品
	 * 
	 * @param request
	 * @param giftId
	 * @return
	 */
	@RequestMapping("/exchange/{giftId}/{addressId}")
	@ResponseBody
	public Map<String,Object> exchangeIntegralGift(HttpServletRequest request, @PathVariable("giftId") Long giftId,@PathVariable("addressId") Long addressId) {
		try {
			//根据礼品Id查询礼品
			UserIntegralGift userIntegralGift=userIntegralGiftService.getUserIntegralGiftById(giftId);
			if(ObjectUtils.isNull(userIntegralGift)){//判断礼品是否存在
				this.setJson(false, "兑换的礼品不存在或已删除",null);
				return json;
			}
			//查询收货地址
			UserAddress userAddress = userAddressService.getUserAddressById(addressId);
			if(ObjectUtils.isNull(userAddress)){//判断地址是否存在
				this.setJson(false, "请添加收货地址",null);
				return json;
			}
			//查询积分模板 根据gift关键字
			UserIntegralTemplate userIntegralTemplate=userIntegralTemplateService.getUserIntegralTemplateByKeyword(IntegralKeyword.gift.toString());
			if(ObjectUtils.isNull(userIntegralTemplate)){
				this.setJson(false, "兑换错误，请稍后重试",null);
				return json;
			}
			//获得用户积分
			UserIntegral userIntegral=userIntegralService.getUserIntegralByUserId(getLoginUserId(request));
			//兑换标示 是否够积分进行兑换
			Integer isExchange=userIntegral.getCurrentScore().intValue()+userIntegralGift.getScore().intValue()*userIntegralTemplate.getScore().intValue();
			if(isExchange<0){//积分不足
				this.setJson(false, "兑换积分不足",null);
				return json;
			}
			//积分记录
			UserIntegralRecord userIntegralRecord = new UserIntegralRecord();
			userIntegralRecord.setUserId(getLoginUserId(request));//添加用户id
			userIntegralRecord.setScore(userIntegralGift.getScore().longValue()*userIntegralTemplate.getScore().longValue());//变更分数
			userIntegralRecord.setIntegralType(userIntegralTemplate.getId());//积分类型
			userIntegralRecord.setCurrentScore(new Long(isExchange));//用户最新分数
			userIntegralRecord.setCreateTime(new Date());//创建时间
			userIntegralRecord.setOther(giftId);//礼品id 
			userIntegralRecord.setDescription(userIntegralTemplate.getName());
			userIntegralRecord.setFromUserId(0L);//来源id
			userIntegralRecord.setStatus(0L);
			if(userIntegralGift.getCourseId()>0){
				userIntegralRecord.setStatus(1L);//兑换课程直接标记为已处理
			}
			userIntegralRecord.setAddressId(userAddress.getId());
			userIntegralRecord.setAddress(userAddress.getProvinceStr()+"，"+userAddress.getCityStr()+"，"+userAddress.getTownStr()+"，"+userAddress.getAddress()+"；姓名："+userAddress.getReceiver()+"；手机："+userAddress.getMobile());
			
			boolean flag=userIntegralRecordService.addExchangeOperate(userIntegralRecord, userIntegralGift.getCourseId(),userIntegral,WebUtils.getIpAddr(request));
			if(flag){
				this.setJson(true, "兑换成功",null);
			}else{
				this.setJson(false, "兑换的课程卡不存在",null);
			}
		} catch (Exception e) {
			logger.error("exchangeIntegralGift", e);
		}
		return json;
		
	}
}