package com.atdld.os.edu.controller.user;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.JsonObject;
import com.atdld.os.common.constants.CommonConstants;
import com.atdld.os.common.constants.MemConstans;
import com.atdld.os.common.contoller.SingletonLoginUtils;
import com.atdld.os.core.controller.RandomCodeController;
import com.atdld.os.core.entity.PageEntity;
import com.atdld.os.core.service.cache.MemCache;
import com.atdld.os.core.service.email.EmailService;
import com.atdld.os.core.util.ObjectUtils;
import com.atdld.os.core.util.PreventInfusion;
import com.atdld.os.core.util.StringUtils;
import com.atdld.os.core.util.security.PurseSecurityUtils;
import com.atdld.os.core.util.web.WebUtils;
import com.atdld.os.edu.common.EduBaseController;
import com.atdld.os.edu.constants.enums.ImagesType;
import com.atdld.os.edu.constants.enums.IntegralKeyword;
import com.atdld.os.edu.constants.enums.UserExpandFrom;
import com.atdld.os.edu.constants.enums.WebSiteProfileType;
import com.atdld.os.edu.constants.web.OrderConstans;
import com.atdld.os.edu.constants.web.SnsConstants;
import com.atdld.os.edu.entity.answer.AnswerQuestion;
import com.atdld.os.edu.entity.article.Article;
import com.atdld.os.edu.entity.course.CourseDto;
import com.atdld.os.edu.entity.letter.MsgReceive;
import com.atdld.os.edu.entity.letter.QueryMsgReceive;
import com.atdld.os.edu.entity.member.MemberRecordDTO;
import com.atdld.os.edu.entity.user.QueryUserAccounthistory;
import com.atdld.os.edu.entity.user.User;
import com.atdld.os.edu.entity.user.UserAccount;
import com.atdld.os.edu.entity.user.UserAccounthistory;
import com.atdld.os.edu.entity.user.UserCode;
import com.atdld.os.edu.entity.user.UserExpand;
import com.atdld.os.edu.entity.user.UserExpandDto;
import com.atdld.os.edu.entity.user.UserForm;
import com.atdld.os.edu.entity.userprofile.UserProfile;
import com.atdld.os.edu.entity.website.WebsiteImages;
import com.atdld.os.edu.entity.website.WebsiteProfile;
import com.atdld.os.edu.service.answer.AnswerQuestionService;
import com.atdld.os.edu.service.article.ArticleService;
import com.atdld.os.edu.service.course.CourseService;
import com.atdld.os.edu.service.letter.MsgReceiveService;
import com.atdld.os.edu.service.member.MemberRecordService;
import com.atdld.os.edu.service.user.LoginOnlineService;
import com.atdld.os.edu.service.user.UserAccountService;
import com.atdld.os.edu.service.user.UserAccounthistoryService;
import com.atdld.os.edu.service.user.UserCodeService;
import com.atdld.os.edu.service.user.UserExpandService;
import com.atdld.os.edu.service.user.UserIntegralService;
import com.atdld.os.edu.service.user.UserService;
import com.atdld.os.edu.service.userprofile.UserProfileService;
import com.atdld.os.edu.service.website.WebsiteImagesService;
import com.atdld.os.edu.service.website.WebsiteProfileService;

/**
 * 
 * @ClassName com.atdld.os.user.controller.UserController
 * @description
 * @author :
 * @Create Date : 2014-1-13 上午11:32:19
 */
@Controller
public class UserController extends EduBaseController {

	private Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;
	@Autowired
	private UserExpandService userExpandService;
	@Autowired
	private UserCodeService userCodeService;
	@Autowired
	private EmailService emailService;
	@Autowired
	private CourseService courseService;
	@Autowired
	private ArticleService articleService;
	@Autowired
	private UserIntegralService userIntegralService;
	@Autowired
	private UserAccountService userAccountService;
	@Autowired
	private UserAccounthistoryService userAccounthistoryService;
	@Autowired
	private MsgReceiveService msgReceiveService;
	@Autowired
	private WebsiteImagesService websiteImagesService;
	@Autowired
	private MemberRecordService memberRecordService;
	@Autowired
	private LoginOnlineService loginOnliceService;
	@Autowired
	private WebsiteProfileService websiteProfileService;
	@Autowired
	private AnswerQuestionService answerQuestionService;
	@Autowired
	private UserProfileService userProfileService;
	MemCache memCache = MemCache.getInstance();
	private String toUpdatePwd = getViewPath("/user/change_password");
	private String registerJsp = getViewPath("/user/register");// 注册
	private String loginjsp = getViewPath("/user/login");// 登录
	private String forgetpwdjsp = getViewPath("/user/forgetpwd");// 忘记密码
	private String getpwdjsp = getViewPath("/user/getpwd");// 找回后修改密码页面
	private String uc_home = getViewPath("/ucenter/uchome");// 个人中心首页
	private String toPwd = getViewPath("/ucenter/update_pwd");// 修改密码
	private String userInfo = getViewPath("/ucenter/user_info");// 基本资料
	private String avatar = getViewPath("/ucenter/avatar");// 修改头像
	private String accoutList = getViewPath("/ucenter/account_list");// 账户历史
	private String memberInfo = getViewPath("/ucenter/member_info");// 会员历史
	private String queryUserLetter = getViewPath("/ucenter/u_letter_inbox");// 用户消息
	private String toMyCouAnswerQuestion=getViewPath("/ucenter/myAnswer");

	// 绑定变量名字和属性，参数封装进类
	@InitBinder("userForm")
	public void initBinder1(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("userForm.");
	}

	@InitBinder("queryUser")
	public void initBinderqueryUser(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("queryUser.");
	}
	
	@InitBinder("msgReceive")
	public void initBinderMsgReceive(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("msgReceive.");
	}

	
	
	// 注册
	@RequestMapping("/register")
	public String regist() {

        //return registerJsp;
        return loginjsp;
	}

	// 注册
	@RequestMapping("/front/forget_passwd")
	public String forgotpwd() {
		return forgetpwdjsp;
	}

	// 登录
	@RequestMapping("/login")
	public String toIndex() {
		return loginjsp;
	}

	@RequestMapping("/toUpdatePwd")
	public String toUpdatePwd() {
		return toUpdatePwd;
	}

	/**
	 * 用户操作受限制提示
	 * 
	 * @return
	 */
	@RequestMapping("/limitVerifyError")
	public String limitVerifyError() {
		return "/admin/login/limitVerifyError";
	}

	public void sendValidateMessage(String message, HttpServletResponse response) {
		try {
			response.setCharacterEncoding("utf-8");
			response.getWriter().write(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
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
	public Map<String, Object> doregister(@ModelAttribute UserForm userForm, ModelMap model, HttpServletRequest request, HttpServletResponse response) {
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

			if (StringUtils.isEmpty(randomCode) ||request.getSession().getAttribute(RandomCodeController.RAND_CODE)==null || !randomCode.equalsIgnoreCase(request.getSession().getAttribute(RandomCodeController.RAND_CODE).toString())) {
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
			if ( PreventInfusion.sql_inj(userForm.getPassword())) {
				this.setJson(false, "regDangerWord", null);
				return json;
			}
			User user = new User();
			user.setEmail(userForm.getEmail().toLowerCase());// 邮箱存储时转为小写
			user.setMobile(userForm.getMobile());
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
			user.setMobile(userForm.getMobile());
			user.setPassword(userForm.getPassword());
			user.setUserip(userIp);
            user.setRegisterFrom(UserExpandFrom.registerFrom.toString());//账号来源是通过注册生成的
			userService.addUser(user);
			Long upUserId = this.getUpLoginId(request);
			//返还上线用户积分
			if(upUserId!=0L)
			{
				userIntegralService.addUserIntegral(IntegralKeyword.rebate.toString(),upUserId,0L, user.getId(), "");
			}
			// 注册送积分
			userIntegralService.addUserIntegral(IntegralKeyword.register.toString(), user.getId(), 0L, 0L, "");
			// 注册时发送系统消息
			Map<String, Object> websitemap = websiteProfileService.getWebsiteProfileByType(WebSiteProfileType.web.toString());
			Map<String, Object> web = (Map<String, Object>) websitemap.get("web");
			String company = web.get("company").toString();

			String conent = "欢迎来到" + company + ",希望您能够快乐的学习";
			msgReceiveService.addSystemMessageByCusId(conent, user.getId());
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
	public Map<String, Object> dologin(@ModelAttribute UserForm userForm, HttpServletRequest request, HttpServletResponse response) {
		try {
			// 获得登录开关是否开启
			Map<String, Object> map = getLoginAndRegKeyword();
			if(ObjectUtils.isNull(map)||map.get("verifyLogin").toString().equalsIgnoreCase("OFF")){
				this.setJson(false, "", null);
				return json;
			}
			// 验证输入数据合法性
			if (PreventInfusion.sql_inj(userForm.getPassword())) {
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
	public Map<String, Object> checkEmail(@ModelAttribute UserForm userForm, HttpServletRequest request, HttpServletResponse response) {
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
				}else{
					sendValidateMessage("true", response);
				}
				
			}
		} catch (Exception e) {
			this.setExceptionRequest(request, e);
			return ERROR;
		}
		return null;
	}

	/**
	 * 修改密码
	 * 
	 * @return Map<String, Object>
	 * @thorows Exception
	 */
	@RequestMapping("/user/updatePwd")
	@ResponseBody
	public Map<String, Object> updatePwd(HttpServletRequest request, HttpServletResponse response, @RequestParam(required = true) String password, @RequestParam(required = true) String confirmPwd,
			@RequestParam(required = true) String code) {
		try {
			UserCode userCode = userCodeService.checkUserCode(code);
			if (ObjectUtils.isNull(userCode)) {
				this.setJson(false, "该链接已经使用或者已经过期", null);
				return json;
			}
			if (password.equals(confirmPwd)) {
				if (password.length() < 6) {
					this.setJson(false, "密码长度不能小于6位", null);
					return json;
				}
				User user = new User();
				user.setId(userCode.getUserId());
				user.setPassword(password);
				userService.updatePwdById(user, userCode);
				this.setJson(true, "", null);
				memCache.remove(MemConstans.USEREXPAND_INFO + userCode.getUserId());
			} else {
				this.setJson(false, "两次密码不一致", null);
			}
		} catch (Exception e) {
			this.setExceptionRequest(request, e);
			this.setJson(false, "系统异常", null);
		}

		return json;
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
			loginOnliceService.deleteLoginOnlineById(userId);
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

	/**
	 * 找回密码
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/user/forgetpwd")
	@ResponseBody
	public Object forgetpwd(HttpServletRequest request, String email, String randomCode, String mobile) {
		try {
			if (StringUtils.isNotEmpty(randomCode)) {
				if (!randomCode.equals(request.getSession().getAttribute(RandomCodeController.RAND_CODE))) {
					this.setJson(false, "验证码错误", null);
					return json;
				}
			} else {
				this.setJson(false, "验证码不能为空", null);
				return json;
			}
			if (StringUtils.isNotEmpty(email)) {
				List<String> emails = new ArrayList<String>();
				emails.add(email);
				List<User> userliList = userService.getUserIsExsitByEmail(emails);
				if (ObjectUtils.isNotNull(userliList)) {
					User user = userliList.get(0);
					UserCode userCode = new UserCode();
					userCode.setType(0L);// 找回密码type
					userCode.setContext(user.getEmail());
					userCode.setUserId(user.getId());
					userCode = userCodeService.addUserCode(userCode);
					if (ObjectUtils.isNotNull(userCode)) {
						String str = gson.toJson(userCode);
						String desCode = PurseSecurityUtils.secrect(str, CommonConstants.SecurityKey);
						String link = CommonConstants.contextPath+ "/front/getpwdcode?code=" + URLEncoder.encode(desCode, "utf-8");
						// 获得网站配置
						Map<String, Object> websitemap = websiteProfileService.getWebsiteProfileByType(WebSiteProfileType.web.toString());
						Map<String, Object> web = (Map<String, Object>) websitemap.get("web");
						String company = web.get("company").toString();
						emailService.sendMail(userCode.getContext(), "您好,请打开下面的链接并及时修改密码,3天内有效，<a href='" + link + "'>" + link + "</a><br/>如果无法打开，请复制链接到浏览器地址栏中", "帐号找回[" + company + "]");
						// 发送找回密码邮件
						this.setJson(true, "邮件发送成功", null);
					} else {
						this.setJson(false, "发送邮件错误,请稍后再试", null);
					}

				} else {
					this.setJson(false, "该帐号不存在", null);
				}
			}
			// this.setJson(true, "", null);
		} catch (Exception e) {
			logger.error("UserController.exit", e);
			this.setJson(false, "", null);
		}
		return json;
	}

	/**
	 * 邮件发送的地址找回密码跳转
	 * 
	 * @return
	 */
	@RequestMapping("/front/getpwdcode")
	public Object getpwdcode(HttpServletRequest request, String code) {
		try {
			UserCode userCode = userCodeService.checkUserCode(code);
			if (ObjectUtils.isNull(userCode)) {
				request.setAttribute("msg", "该链接已经使用或者已经过期");
				return msgjsp;
			}
		} catch (Exception e) {
			this.setExceptionRequest(request, e);
			request.setAttribute("msg", "系统异常");
			return msgjsp;
		}
		request.setAttribute("code", code);
		return getpwdjsp;
	}

	public boolean checkIsRight(String dbPassword, String userPassword, String userkey) {
		String despassword = PurseSecurityUtils.secrect(userPassword, userkey);
		return despassword.equals(dbPassword);
	}

	/**
	 * 跳转到提示成功页面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/front/success")
	public String gosuccess(Model model, HttpServletRequest request) {
		model.addAttribute(OrderConstans.RESMSG, request.getParameter(OrderConstans.RESMSG));
		if(request.getParameter("type")!=null){
			model.addAttribute("type",request.getParameter("type"));
		}
		return "/common/msg_success";
	}

	/**
	 * 个人中心
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/uc/home")
	public String ucenter(Model model, HttpServletRequest request) {
		try {
			// 查询免费课程
			List<CourseDto> freecourses = courseService.getFreeCourseList(getLoginUserId(request),6L);
			// 查询购买过的课程
			List<CourseDto> courseDtos = courseService.getUserBuyCourseList(getLoginUserId(request));
            //过滤直播课程
            courseDtos = courseService.filtrationLive(courseDtos);
			// 查询公告
			List<Article> noticeList = articleService.queryArticleIndex();
			model.addAttribute("noticeList", noticeList);
			// 获得所有推荐课程
			Map<String, List<CourseDto>> mapCourseList = courseService.getCourseListByHomePage(0L);
			// 获得用户积分及等级
			Map<String, Object> userIntegralMap = userIntegralService.getUserIntegralAndLevel(getLoginUserId(request));
			model.addAttribute("buycourses", courseDtos);// 购买的课程
			model.addAttribute("freecourses", freecourses);// 免费的课程 推荐6条
			model.addAttribute("mapCourseList", mapCourseList);// 推荐课程
			model.addAttribute("userIntegralMap", userIntegralMap);// 积分等级详情
			// TODO 讲师推荐未查询 js 获取 
		} catch (Exception e) {
			return setExceptionRequest(request, e);
		}
		return uc_home;
	}

	/**
	 * 个人账户信息
	 * 
	 * @param request
	 * @param page
	 * @return
	 */
	@RequestMapping("/uc/acc")
	public ModelAndView perssonAccout(HttpServletRequest request, @ModelAttribute("page") PageEntity page) {
		ModelAndView modelAndView = new ModelAndView();
		QueryUserAccounthistory queryUserAccounthistory = new QueryUserAccounthistory();
		modelAndView.setViewName(accoutList);
		try {
			this.setPage(page);
			queryUserAccounthistory.setUserId(UserController.getLoginUserId(request));
			UserAccount userAccount = userAccountService.getUserAccountByUserId(getLoginUserId(request));
			List<UserAccounthistory> accList = userAccounthistoryService.getWebUserAccountHistroyListByCondition(queryUserAccounthistory, page);
			modelAndView.addObject("userAccount", userAccount);
			modelAndView.addObject("accList", accList);
			modelAndView.addObject("page", page);
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView(this.setExceptionRequest(request, e));
		}
		return modelAndView;
	}

	/**
	 * 个人会员信息
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/uc/member")
	public ModelAndView perssonMember(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(memberInfo);
		try {
			List<MemberRecordDTO> memberRecords = memberRecordService.getMemberRecordByUser(getLoginUserId(request));
			modelAndView.addObject("memberRecords", memberRecords);
		} catch (Exception e) {
			logger.error("UserController.perssonMember", e);
			return new ModelAndView(this.setExceptionRequest(request, e));
		}
		return modelAndView;
	}

	/**
	 * 修改密码页面
	 */
	@RequestMapping("/uc/uppwd")
	public String toPwd(HttpServletRequest request) {
		return toPwd;
	}

	/**
	 * 修改密码
	 */
	@RequestMapping("/uc/user/updatepwd")
	@ResponseBody
	public Object updatepwd(HttpServletRequest request, @RequestParam("newpwd") String newpwd, @RequestParam("oldpwd") String oldpwd) {
		try {
			User user = userService.getUserById(getLoginUserId(request));
			if (checkIsRight(user.getPassword(), oldpwd, user.getCustomerkey())) {
				user.setPassword(newpwd);
				userService.updatePwdById(user, null);
				this.setJson(true, "", "");
			} else {
				this.setJson(false, "", null);
			}

			return json;
		} catch (Exception e) {
			logger.error("UserController.updatepwd", e);
			this.setJson(false, "", null);
			return json;
		}
	}

	/**
	 * 基本信息
	 */
	@RequestMapping("/uc/uinfo")
	public String userInfo(HttpServletRequest request) {
		try {
			UserExpandDto userExpandDto = userExpandService.getUserExpandByUids(getLoginUserId(request));
			request.setAttribute("queryUser", userExpandDto);
			List<UserProfile> profileList = userProfileService.getUserProfileByUserId(userExpandDto.getCusId());
			if(profileList!=null && profileList.size()>0){
				UserProfile profile = profileList.get(0);
				request.setAttribute("profile", profile);
			}
		} catch (Exception e) {
			logger.error("UserController.updatepwd", e);
			return setExceptionRequest(request, e);
		}
		return userInfo;
	}

	/**
	 * 修改基本信息
	 */
	@RequestMapping("/uc/user/update")
	@ResponseBody
	public Object userUpdateInfo(HttpServletRequest request, @ModelAttribute("queryUser") UserExpandDto queryUser) {
		try {
			// 修改用户信息
			queryUser.setId(getLoginUserId(request));
			
			Pattern emailRes = Pattern.compile("^([a-zA-Z0-9_.])+@([a-zA-Z0-9_])+(.[a-zA-Z0-9_-])+");
			Matcher mat = emailRes.matcher(queryUser.getEmail());
			boolean emailF = mat.matches();
			if (!emailF) {
				this.setJson(true, "", "emailFormatError");
				return json;
			}
			List<UserProfile> profileList = userProfileService.getUserProfileByUserId(getLoginUserId(request));
			//验证是否是第三方登录
			if(profileList!=null && profileList.size()>0){
				queryUser.setUpdateEmail("YES");
				User user = new User();
				user.setEmail(queryUser.getEmail());
				List<User> list = userService.getUserList(user);
				if(list!=null && list.size()>0){
					user = list.get(0);
					if(user.getEmail().equals(queryUser.getEmail()) && user.getId().longValue()!=queryUser.getId().longValue()){
						this.setJson(true, "", "emailHave");
						return json;
					}
				}
				
			}
			String falg = userService.updateQueryUser(queryUser);
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
	 * 修改头像
	 */
	@RequestMapping("/uc/avatar")
	public String toUserUpdateAvatar(HttpServletRequest request) {
		try {
			UserExpandDto userExpandDto = userExpandService.getUserExpandByUids(getLoginUserId(request));
			request.setAttribute("queryUser", userExpandDto);
		} catch (Exception e) {
			logger.error("UserController.userUpdateAvatar", e);
			return setExceptionRequest(request, e);
		}
		return avatar;
	}

	/**
	 * 修改头像
	 */
	@RequestMapping("/uc/user/updateavatar")
	@ResponseBody
	public Object userUpdateAvatar(HttpServletRequest request, @RequestParam("userId") Long userId, @RequestParam("avatar") String avatar) {
		try {
			UserExpand userExpand = new UserExpand();
			userExpand.setAvatar(avatar);
			userExpand.setCusId(userId);
			userExpandService.updateUserExpand(userExpand);

			JsonObject userJsonObject = SingletonLoginUtils.getLoginUser(request);
			userJsonObject.addProperty("avatar",avatar);
			String sid = WebUtils.getCookie(request, CommonConstants.USER_SINGEL_ID);
			memCache.set(sid, userJsonObject.toString(), MemConstans.USER_TIME);

			this.setJson(true, "", "");
		} catch (Exception e) {
			logger.error("UserController.userUpdateAvatar", e);
			this.setJson(false, "", "");
		}
		return json;
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
	 * 获得个人中心个人模板
	 * 
	 * @param request
	 * @param page
	 * @return
	 */
	@RequestMapping("/uc/usercover")
	@ResponseBody
	public Map<String, Object> getUserPersonalityImages(HttpServletRequest request, @ModelAttribute("page") PageEntity page) {
		try {
			// 设置分页
			this.setPage(page);
			this.getPage().setPageSize(12);
			WebsiteImages websiteImages = new WebsiteImages();
			websiteImages.setKeyWord(ImagesType.userPersonalityImages.toString());// 个人中心keyword
			// 方法公用
			List<WebsiteImages> websiteImagesList = websiteImagesService.getImgPageList(websiteImages, page);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("websiteImagesList", websiteImagesList);
			map.put("page", this.getPage());
			this.setJson(true, "success", map);
		} catch (Exception e) {
			logger.error("UserController.getUserPersonalityImages", e);
			this.setJson(false, "Images is error", null);
		}
		return json;
	}

	/**
	 * 更新个人中心用户封面
	 * 
	 * @param request
	 * @param bannerUrl
	 * @return
	 */
	@RequestMapping("/uc/updateusercover")
	@ResponseBody
	public Map<String, Object> updateUserCover(HttpServletRequest request, @RequestParam("bannerUrl") String bannerUrl) {
		try {
			if (bannerUrl!=null) {// 更新
				userExpandService.updateUserExpandBannerUrl(getLoginUserId(request), bannerUrl);
				this.setJson(true, " 更新封面成功", bannerUrl);
			} else {
				this.setJson(false, " request parameter is null", null);
			}
		} catch (Exception e) {
			logger.error("", e);
			this.setJson(false, "updated is error", null);
		}
		return json;
	}
	
	/**
     * 删除站内信收件箱
     *
     * @param msgReceive
     * @return
     */
    @RequestMapping(value = "/letter/delLetterInbox")
    @ResponseBody
    public Map<String, Object> delLetterInbox(@ModelAttribute MsgReceive msgReceive, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            msgReceive.setReceivingCusId(getLoginUserId(request));// set 用户id
            Long num = msgReceiveService.delMsgReceiveInbox(msgReceive);// 删除收件箱
            if (num.intValue() == 1) {
                map.put("message", SnsConstants.SUCCESS);// 成功
            } else {
                map.put("message", SnsConstants.FALSE);// 失败
            }
        } catch (Exception e) {
            logger.error("LetterAction.delLetterInbox", e);
            setExceptionRequest(request, e);
        }
        return map;
    }
	/**
	 * 查询站内信收件箱（无社区）
	 * 
	 * @param request
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/uc/letter")
	public ModelAndView queryUserLetter(HttpServletRequest request, @ModelAttribute("page") PageEntity page) {
		ModelAndView modelAndView = new ModelAndView(queryUserLetter);
		try {
			this.setPage(page);
			page.setPageSize(6);// 分页页数为6
			MsgReceive msgReceive = new MsgReceive();
			msgReceive.setReceivingCusId(getLoginUserId(request));// set用户id
			//msgReceiveService.updateAllReadMsgReceiveInbox(msgReceive);//
			// 更新所有收件箱为已读
			List<QueryMsgReceive> queryLetterList = msgReceiveService.queryMsgReceiveByInbox(msgReceive, this.getPage());// 查询站内信收件箱
			modelAndView.addObject("queryLetterList", queryLetterList);// 查询出的站内信放入modelAndView中
			modelAndView.addObject("page", this.getPage());// 分页参数放入modelAndView中
		} catch (Exception e) {
			logger.error("LetterAction.queryLetterByInbox", e);
			setExceptionRequest(request, e);
			ModelAndView modelAndViewError = new ModelAndView(ERROR);
			return modelAndViewError;// 返回404页面
		}
		return modelAndView;
	}
	 /**
     * 查询该用户有多少未读消息
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/letter/queryUnReadLetter")
    @ResponseBody
    public Map<String, Object> queryUnReadLetter(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            //未登录不可操作
            if (getLoginUserId(request).longValue() == 0) {
                return map;
            }
            Map<String, String> queryletter = msgReceiveService.queryUnReadMsgReceiveNumByCusId(getLoginUserId(request));// 查询该用户有多少未读消息
            map.put("entity", queryletter);// 把值放入map中返回json
        } catch (Exception e) {
            logger.error("LetterAction.queryUnReadLetter", e);
            setExceptionRequest(request, e);
        }
        return map;
    }
	
	
	@RequestMapping("/front/pro")
	@ResponseBody
	public Object frontpro(@RequestParam String type, @RequestParam(required = false) String key, @RequestParam(required = false) String value) {
		try {
			Map<String, Object> websitemap = websiteProfileService.getWebsiteProfileByType(type);
			if (StringUtils.isNotEmpty(key) && StringUtils.isNotEmpty(value)) {
				websitemap.put(key, value);
				// 将map转化json串
				JsonObject jsonObject = jsonParser.parse(gson.toJson(websitemap)).getAsJsonObject();
				if (ObjectUtils.isNotNull(jsonObject) && StringUtils.isNotEmpty(jsonObject.toString())) {// 如果不为空进行更新
					WebsiteProfile websiteProfile = new WebsiteProfile();// 创建websiteProfile
					websiteProfile.setType(type);
					websiteProfile.setDesciption(jsonObject.toString());
					websiteProfileService.updateWebsiteProfile(websiteProfile);
				}
			}
			this.setJson(true, null, websitemap);
		} catch (Exception e) {

		}
		return json;
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
	
	@RequestMapping("/uc/myCouAnswer")
	public ModelAndView myCouAnswer(HttpServletRequest request,@ModelAttribute("page") PageEntity page){
		ModelAndView model=new ModelAndView(toMyCouAnswerQuestion);
		try{
			this.setPage(page);
			List<AnswerQuestion> answerList=answerQuestionService.queryMyCouAnswerQuestionList(getLoginUserId(request), this.getPage());
			model.addObject("answerList", answerList);
			model.addObject("page", this.getPage());
		}catch(Exception e){
			logger.error("myAnswer", e);
		}
		return model;
	}
}
