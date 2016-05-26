package com.atdld.os.mobile.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.gson.JsonObject;
import com.atdld.os.common.constants.CommonConstants;
import com.atdld.os.common.constants.MemConstans;
import com.atdld.os.common.contoller.SingletonLoginUtils;
import com.atdld.os.common.service.WebHessianService;
import com.atdld.os.core.controller.RandomCodeController;
import com.atdld.os.core.entity.PageEntity;
import com.atdld.os.core.service.cache.MemCache;
import com.atdld.os.core.service.email.EmailService;
import com.atdld.os.core.util.ObjectUtils;
import com.atdld.os.core.util.PreventInfusion;
import com.atdld.os.core.util.StringUtils;
import com.atdld.os.core.util.security.PurseSecurityUtils;
import com.atdld.os.core.util.web.WebUtils;
import com.atdld.os.edu.common.MobileBaseController;
import com.atdld.os.edu.constants.enums.IntegralKeyword;
import com.atdld.os.edu.constants.enums.UserExpandFrom;
import com.atdld.os.edu.constants.enums.WebSiteProfileType;
import com.atdld.os.edu.entity.user.QueryUserAccounthistory;
import com.atdld.os.edu.entity.user.User;
import com.atdld.os.edu.entity.user.UserAccount;
import com.atdld.os.edu.entity.user.UserAccounthistory;
import com.atdld.os.edu.entity.user.UserExpandDto;
import com.atdld.os.edu.entity.user.UserForm;
import com.atdld.os.edu.service.answer.AnswerQuestionService;
import com.atdld.os.edu.service.letter.MsgReceiveService;
import com.atdld.os.edu.service.user.LoginOnlineService;
import com.atdld.os.edu.service.user.UserAccountService;
import com.atdld.os.edu.service.user.UserAccounthistoryService;
import com.atdld.os.edu.service.user.UserExpandService;
import com.atdld.os.edu.service.user.UserIntegralService;
import com.atdld.os.edu.service.user.UserService;
import com.atdld.os.edu.service.website.WebsiteProfileService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @ClassName com.atdld.os.user.controller.UserController
 * @description
 * @author :
 * @Create Date : 2014-1-13 上午11:32:19
 */
@Controller
public class MobileUserController extends MobileBaseController {

	private Logger logger = LoggerFactory.getLogger(MobileUserController.class);

	
	@Autowired
	private EmailService emailService;
	@Autowired
	private AnswerQuestionService answerQuestionService;
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserAccountService userAccountService;
	@Autowired
	private UserAccounthistoryService userAccounthistoryService;
	
	@Autowired
	private WebsiteProfileService websiteProfileService;
	
	@Autowired
	private UserExpandService userExpandService;
	
	@Autowired
	private UserIntegralService userIntegralService;
	
	@Autowired
	private MsgReceiveService msgReceiveService;
	
	@Autowired
	private LoginOnlineService loginOnlineService;
	
	@Autowired
	private WebHessianService webHessianService;
	
	MemCache memCache = MemCache.getInstance();
	
	private static String loginpage = getViewPath("/user/login");
	private static String registerpage = getViewPath("/user/register");
	private static String ucenter = getViewPath("/ucenter/u-center");
	private static String userInfo = getViewPath("/ucenter/u-my-account");
	private static String toUcPwd = getViewPath("/ucenter/u-pwd-update");
	private static String accoutList = getViewPath("/ucenter/u-acc-number");
	private static String accoutListAjax = getViewPath("/ucenter/u-acc-number-ajax");


	@InitBinder("queryUser")
	public void initBinderqueryUser(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("queryUser.");
	}
	
	@InitBinder("userForm")
	public void initBinderUserForm(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("userForm.");
	}
	
	

	/**
	 * 登录页
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/mobile/login")
	public String loginpage(HttpServletRequest request) {
		return loginpage;
	}
	
	/**
	 * 注册页
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/mobile/register")
	public String registerpage(HttpServletRequest request) {
		return registerpage;
	}


	/**
	 * 个人中心
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/mobile/uc/home")
	public String ucenter( HttpServletRequest request) {
		try {
			UserExpandDto userExpandDto = userExpandService.getUserExpandByUids(getLoginUserId(request));
			request.setAttribute("user", userExpandDto);
		} catch (Exception e) {
			logger.error("UserController.ucenter", e);
			return setExceptionRequest(request, e);
		}
		return ucenter;
	}

	/**
	 * 基本信息
	 */
	@RequestMapping("/mobile/uc/uinfo")
	public String userInfo(HttpServletRequest request) {
		try {
			UserExpandDto userExpandDto = userExpandService.getUserExpandByUids(getLoginUserId(request));
			request.setAttribute("queryUser", userExpandDto);
		} catch (Exception e) {
			logger.error("UserController.updatepwd", e);
			return setExceptionRequest(request, e);
		}
		return userInfo;
	}

	/**
	 * 修改基本信息
	 */
	@RequestMapping("/mobile/uc/user/update")
	@ResponseBody
	public Object userUpdateInfo(HttpServletRequest request, @ModelAttribute("queryUser") UserExpandDto queryUser) {
		try {
			// 修改用户信息
			Map<String,String> map=new HashMap<String, String>();
			map.put("id",getLoginUserId(request)+"" );//用户id
			map.put("realName",queryUser.getRealname());//姓名
			map.put("nickName",queryUser.getNickname());//昵称
			map.put("mobile",queryUser.getMobile());//手机
			map.put("gender",queryUser.getGender()+"" );//性别
			map.put("userInfo",queryUser.getUserInfo()+"" );//简介
			String falg = webHessianService.mobileUpdateQueryUser(map);
			if ("success".equals(falg)) {
				JsonObject userJsonObject = SingletonLoginUtils.getLoginUser(request);
				userJsonObject.addProperty("nickname",queryUser.getNickname());
				userJsonObject.addProperty("showname",queryUser.getNickname());
				String sid = WebUtils.getCookie(request, CommonConstants.USER_SINGEL_ID);
				memCache.set(sid, userJsonObject.toString(), MemConstans.USER_TIME);
			}

			this.setJson(true, "", falg);
		} catch (Exception e) {
			logger.error("UserController.updatepwd", e);
			this.setJson(false, "", "");
		}
		return json;
	}

	/**
	 * 跳转修改密码
	 */
	@RequestMapping("/mobile/uc/dopwd")
	public String doUserPwd(HttpServletRequest request) {
		return toUcPwd;
	}

	/**
	 * 修改密码
	 */
	@RequestMapping("/mobile/uc/pwd")
	@ResponseBody
	public Object userPwd(HttpServletRequest request, @ModelAttribute("queryUser") UserExpandDto queryUser) {
		try {
			Map<String,String> map=new HashMap<String, String>();
			String oldpwd=request.getParameter("oldpwd");
			String newpwd=request.getParameter("newpwd");
			User user = userService.getUserById(getLoginUserId(request));
			if (checkIsRight(user.getPassword(), oldpwd, user.getCustomerkey())) {
				map.put("password", newpwd);
				map.put("userId", getLoginUserId(request)+"");
				webHessianService.mobileUpdatePwdById(map);
				this.setJson(true, "", "");
			} else {
				this.setJson(false, "", null);
			}
		} catch (Exception e) {
			logger.error("MobileUserController.updatepwd", e);
			this.setJson(false, "", null);
		}
		return json;
	}
	public boolean checkIsRight(String dbPassword, String userPassword, String userkey) {
		String despassword = PurseSecurityUtils.secrect(userPassword, userkey);
		return despassword.equals(dbPassword);
	}
	
	/**
	 * 个人账户信息
	 * 
	 * @param request
	 * @param page
	 * @return
	 */
	@RequestMapping("/mobile/uc/acc")
	public String perssonAccout(HttpServletRequest request, @ModelAttribute("page") PageEntity page) {
		QueryUserAccounthistory queryUserAccounthistory = new QueryUserAccounthistory();
		try {
			this.setPage(page);
			this.getPage().setPageSize(6);
			queryUserAccounthistory.setUserId(getLoginUserId(request));
			UserAccount userAccount = userAccountService.getUserAccountByUserId(getLoginUserId(request));
			List<UserAccounthistory> accList = userAccounthistoryService.getWebUserAccountHistroyListByCondition(queryUserAccounthistory, page);
			request.setAttribute("userAccount", userAccount);
			request.setAttribute("accList", accList);
			request.setAttribute("page", page);
		} catch (Exception e) {
			e.printStackTrace();
			return this.setExceptionRequest(request, e);
		}
		return accoutList;
	}
	
	/**
	 * 个人账户信息
	 * 
	 * @param request
	 * @param page
	 * @return
	 */
	@RequestMapping("/mobile/uc/ajax/acc")
	public String perssonAccoutAjax(HttpServletRequest request, @ModelAttribute("page") PageEntity page) {
		QueryUserAccounthistory queryUserAccounthistory = new QueryUserAccounthistory();
		try {
			this.setPage(page);
			this.getPage().setPageSize(6);
			queryUserAccounthistory.setUserId(getLoginUserId(request));
			UserAccount userAccount = userAccountService.getUserAccountByUserId(getLoginUserId(request));
			List<UserAccounthistory> accList = userAccounthistoryService.getWebUserAccountHistroyListByCondition(queryUserAccounthistory, page);
			request.setAttribute("userAccount", userAccount);
			request.setAttribute("accList", accList);
			request.setAttribute("page", page);
		} catch (Exception e) {
			e.printStackTrace();
			return this.setExceptionRequest(request, e);
		}
		return accoutListAjax;
	}
	
	
	public Map<String, Object> getLoginAndRegKeyword() {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			Map<String, Object> keywordmap = websiteProfileService.getWebsiteProfileByType(WebSiteProfileType.keyword.toString());
			if (ObjectUtils.isNotNull(keywordmap)) {
				JsonObject jsonObject = jsonParser.parse(gson.toJson(keywordmap.get(WebSiteProfileType.keyword.toString()))).getAsJsonObject();
				if (ObjectUtils.isNotNull(jsonObject) && StringUtils.isNotEmpty(jsonObject.toString())) {// 如果不为空进行更新
					map.put("verifyLogin", jsonObject.get("verifyLogin").getAsString());
					map.put("verifyRegister", jsonObject.get("verifyRegister").getAsString());
				}
			}
		} catch (Exception e) {
			logger.error("getLoginAndRegKeyword", e);
		}
		return map;
	}
	
	/**
	 * 判断是否登录
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/islogin")
	@ResponseBody
	public Map<String, Object> isuserLogin(HttpServletRequest request) {
		if (this.isLogin(request)) {
			setJson(true, null, null);
		}
		return json;
	}
	
	/**
	 * 
	 * 注册用户
	 * 
	 * @param userForm
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/doregister")
	@ResponseBody
	public Map<String, Object> doregister(UserForm userForm, HttpServletRequest request, HttpServletResponse response) {
		try {
			// 获得注册开关是否开启
			Map<String, Object> map = getLoginAndRegKeyword();
			if(ObjectUtils.isNull(map)||map.get("verifyRegister").toString().equalsIgnoreCase("OFF")){
				this.setJson(false, "", null);
				return json;
			}
			if (ObjectUtils.isNull(map) || StringUtils.isEmpty(map.toString()))
				if (ObjectUtils.isNull(userForm)) {// 验证表单数据是否为空
					this.setJson(false, "formDataIsNot", null);
					return json;
				}
			String randomCode = request.getParameter("randomCode");

			if (randomCode==null||StringUtils.isEmpty(randomCode) || request.getSession().getAttribute(RandomCodeController.RAND_CODE)==null||!randomCode.equalsIgnoreCase(request.getSession().getAttribute(RandomCodeController.RAND_CODE).toString())) {
				this.setJson(false, "验证码错误", null);
				return json;
			}
			// 验证邮箱是否为空
			if (ObjectUtils.isNull(userForm.getEmail()) || StringUtils.isEmpty(userForm.getEmail())) {
				this.setJson(false, "emailIsNot", null);
				return json;
			}
			// 在邮箱不为空的情况下，验证邮箱格式是否正确
			if (ObjectUtils.isNotNull(userForm.getEmail()) && StringUtils.isNotEmpty(userForm.getEmail())) {
                // /^\w+@\w+(\.[a-zA-Z]{2,3}){1,2}$/
				//Pattern emailRes = Pattern.compile("^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$");
                Pattern emailRes = Pattern.compile("^([a-zA-Z0-9_.])+@([a-zA-Z0-9_])+(.[a-zA-Z0-9_-])+");
				Matcher mat = emailRes.matcher(userForm.getEmail());
				boolean emailF = mat.matches();
				if (!emailF) {
					this.setJson(false, "emailFormatError", null);
					return json;
				}
			}
			// 验证密码是否为空
			if (ObjectUtils.isNull(userForm.getPassword()) || StringUtils.isEmpty(userForm.getPassword())) {
				this.setJson(false, "pwdIsNull", null);
				return json;
			}
			// 验证密码一致
			if (!userForm.getPassword().equals(userForm.getConfirmPassword())) {
				this.setJson(false, "pwdNotEqual", null);
				return json;
			}
			// 验证手机是否为空
			if (ObjectUtils.isNull(userForm.getMobile()) || StringUtils.isEmpty(userForm.getMobile())) {
				this.setJson(false, "mobileIsNull", null);
				return json;
			}
			// 在手机不为空的情况下，验证手机格式是否正确
			if (ObjectUtils.isNotNull(userForm.getMobile()) && StringUtils.isNotEmpty(userForm.getMobile()) && !userForm.getMobile().matches("^(13[0-9]|15[012356789]|18[012356789]|14[57]|17[012356789])[0-9]{8}$")) {
				this.setJson(false, "false", null);
				return json;
			}
			// 验证输入数据合法性
			if (PreventInfusion.sql_inj(userForm.getEmail()) || PreventInfusion.sql_inj(userForm.getPassword())) {
				this.setJson(false, "regDangerWord", null);
				return json;
			}
			User user = new User();
			Map<String,String> userMap=new HashMap<String, String>();
			user.setEmail(userForm.getEmail().toLowerCase());// 邮箱存储时转为小写
			user.setMobile(userForm.getMobile());
			userMap.put("email", userForm.getEmail().toLowerCase());
			userMap.put("mobile", userForm.getMobile());
			List<User> list = userService.getUserList(user);
			// 验证邮箱唯一,邮箱是否已经注册
			if (ObjectUtils.isNotNull(list)) {
				this.setJson(false, "regEmailExist", null);
				return json;
			}
			
			int ismobile = userService.getUserByMobile(user);
			// 验证手机唯一
			if (ismobile != 0) {
				this.setJson(false, "regMobileExist", null);
				return json;
			}
            request.getSession().removeAttribute(RandomCodeController.RAND_CODE);
			// 邮箱注册
			String userIp = WebUtils.getIpAddr(request);
			userMap.put("mobile", userForm.getMobile());
			userMap.put("password", userForm.getPassword());
			userMap.put("userIp", userIp);
			userMap.put("registerFrom", UserExpandFrom.registerFrom.toString());//账号来源是通过注册生成的
			Long userId=webHessianService.mobileAddUser(userMap);
			user.setId(userId);
//			Long upUserId = this.getUpLoginId(request);
//			//返还上线用户积分
//			if(upUserId!=0L)
//			{
//				userIntegralService.addUserIntegral(IntegralKeyword.rebate.toString(),upUserId,0L, user.getId(), "");
//			}
			// 注册送积分
			Map<String,String> integralMap=new HashMap<String, String>();
            integralMap.put("keyWord",IntegralKeyword.register.toString() );
            integralMap.put("userId",user.getId()+"" );
            integralMap.put("other",0L+"" );
            integralMap.put("fromUserId",0L+"" );
            integralMap.put("otherScorel","" );
            webHessianService.addUserIntegral(integralMap);
			// 注册时发送系统消息
			Map<String, Object> websitemap = websiteProfileService.getWebsiteProfileByType(WebSiteProfileType.web.toString());
			Map<String, Object> web = (Map<String, Object>) websitemap.get("web");
			String company = web.get("company").toString();

			String conent = "欢迎来到" + company + ",希望您能够快乐地学习";
			webHessianService.mobileAddSystemMessageByCusId(conent, userId);
			// 执行登录操作
			userService.setLoginStatus(user, "true", request, response);
			this.setJson(true, "", null);
		} catch (Exception e) {
			logger.error("userRegist error", e);
			this.setJson(false, "系统错误", null);
		}
		return json;
	}

	/**
	 * 快速登录，为ajax提供
	 * 
	 * @return
	 */
	@RequestMapping("/dologin")
	@ResponseBody
	public Map<String, Object> dologin(UserForm userForm, HttpServletRequest request, HttpServletResponse response) {
		try {
			// 获得登录开关是否开启
			Map<String, Object> map = getLoginAndRegKeyword();
			if(ObjectUtils.isNull(map)||map.get("verifyLogin").toString().equalsIgnoreCase("OFF")){
				this.setJson(false, "", null);
				return json;
			}
			// 验证输入数据合法性
			if (PreventInfusion.sql_inj(userForm.getEmail()) || PreventInfusion.sql_inj(userForm.getPassword())) {
				this.setJson(false, "inputIllegal", null);
				return json;
			}
			User user = new User();
			user.setEmail(userForm.getEmail().toLowerCase());
			List<User> list = null;

			// 如果是手机格式则按手机查询
			String regEx = "^1{1}[0-9]{10}$"; // 表示a或F
			Pattern pat = Pattern.compile(regEx);
			Matcher mat = pat.matcher(userForm.getEmail());
			boolean rs = mat.find();
			if (rs) {
				list = userService.getUserListForTelLogin(user);
			}

			// 如果是邮箱格式
			Pattern emailRes = Pattern.compile("^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$");
			mat = emailRes.matcher(userForm.getEmail());
			boolean emailF = mat.matches();
			if (emailF) {
				list = userService.getUserListForLogin(user);
			}

			// 通过数据库，验证用户是否存在
			if (ObjectUtils.isNull(list)) {
				this.setJson(false, "formDataNot", null);
				return json;
			} else {
				user = list.get(0);
				// 验证密码是否正确
				if (checkIsRight(user.getPassword(), userForm.getPassword(), user.getCustomerkey())) {
					String autoThirty = request.getParameter("autoThirty");// 是否30天自动登录
					// 执行登录操作
					userService.setLoginStatus(user, autoThirty, request, response);
					this.setJson(true, "success", "登录成功");
				} else {
					this.setJson(false, "false", "密码错误");
				}
			}
			return json;
		} catch (Exception e) {
			logger.error("Usercontroller.dologin", e);
			this.setJson(false, "error", "系统异常");
			return json;
		}
	}

	/**
	 * 检查email是否存在
	 * 
	 * @param userForm
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/checkEmail")
	public Map<String, Object> checkEmail(UserForm userForm, HttpServletRequest request, HttpServletResponse response) {
		try {
			if (ObjectUtils.isNotNull(userForm)) {
				User user = new User();
				user.setEmail(userForm.getEmail().toLowerCase());
				List<User> list = userService.getUserList(user);
				// 用户已经存在
				if (ObjectUtils.isNotNull(list) && list.size() > 0) {
					sendValidateMessage("false", response);
				} else {
					sendValidateMessage("true", response);
				}
			}
		} catch (Exception e) {
			logger.error("UserController.checkEmail", e);
			this.setJson(false, "", null);
		}
		return null;
	}

	/**
	 * 验证验证码
	 * 
	 * @param randomCode
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/checkRandomCode")
	public void checkRandomCode(@RequestParam("randomCode") String randomCode, HttpServletRequest request, HttpServletResponse response) {
		try {
			if (ObjectUtils.isNull(randomCode) || !randomCode.toUpperCase().equals(request.getSession().getAttribute("COMMON_RAND_CODE"))) {
				sendValidateMessage("false", response);
			} else {
				sendValidateMessage("true", response);
			}

		} catch (Exception e) {
			logger.error("CustomerWebAction.checkRandomCode", e);
		}
	}

	/**
	 * 手机验证
	 * 
	 * @param mobile
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/checkRegMoblie")
	public String checkRegMoblie(@RequestParam("mobile") String mobile, HttpServletRequest request, HttpServletResponse response) {
		try {
			if (com.atdld.os.core.util.StringUtils.isNotEmpty(mobile)) {
				User user = new User();
				user.setMobile(mobile);
				if (userService.getUserByMobile(user) != 0) {
					sendValidateMessage("false", response);
				}
				sendValidateMessage("true", response);
			}
		} catch (Exception e) {
			this.setExceptionRequest(request, e);
			return ERROR;
		}
		return null;
	}

	
	/**
	 * 用户退出
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/exit")
	public Object exit(HttpServletRequest request, HttpServletResponse response) {
		try {
			Long userId = SingletonLoginUtils.getLoginUserId(request);
			loginOnlineService.deleteLoginOnlineById(userId);
			String sid = WebUtils.getCookie(request, CommonConstants.USER_SINGEL_ID);
			if (StringUtils.isNotEmpty(sid)) {
				memCache.remove(sid);
			}
			memCache.remove(MemConstans.USEREXPAND_INFO+userId);
			WebUtils.deleteCookie(request, response, CommonConstants.USER_SINGEL_ID);
			WebUtils.deleteCookie(request, response, CommonConstants.USER_SINGEL_NAME);
			WebUtils.deleteCookie(request, response, "usercookieuserimg");
			WebUtils.deleteCookie(request, response, "e.subject");
			this.setJson(true, "", null);
		} catch (Exception e) {
			logger.error("UserController.exit", e);
			this.setJson(false, "", null);
		}
		return "redirect:/index";
	}

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
	
	public void sendValidateMessage(String message, HttpServletResponse response) {
		try {
			response.setCharacterEncoding("utf-8");
			response.getWriter().write(message);
		} catch (Exception e) {
		}
	}
}
