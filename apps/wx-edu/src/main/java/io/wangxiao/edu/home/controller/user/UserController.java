package io.wangxiao.edu.home.controller.user;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import io.wangxiao.commons.controller.RandomCodeController;
import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.commons.service.cache.CacheKit;
import io.wangxiao.commons.service.email.EmailService;
import io.wangxiao.commons.util.ObjectUtils;
import io.wangxiao.commons.util.PreventInfusion;
import io.wangxiao.commons.util.Security.PurseSecurityUtils;
import io.wangxiao.commons.util.StringUtils;
import io.wangxiao.commons.util.web.HttpUtil;
import io.wangxiao.commons.util.web.WebUtils;
import io.wangxiao.edu.common.constants.CommonConstants;
import io.wangxiao.edu.common.constants.MemConstans;
import io.wangxiao.edu.common.contoller.SingletonLoginUtils;
import io.wangxiao.edu.common.util.EhcacheKit;
import io.wangxiao.edu.home.common.EduBaseController;
import io.wangxiao.edu.home.constants.enums.ImagesType;
import io.wangxiao.edu.home.constants.enums.IntegralKeyword;
import io.wangxiao.edu.home.constants.enums.UserExpandFrom;
import io.wangxiao.edu.home.constants.enums.WebSiteProfileType;
import io.wangxiao.edu.home.constants.web.OrderConstans;
import io.wangxiao.edu.home.entity.answer.AnswerQuestion;
import io.wangxiao.edu.home.entity.arrange.Arrange;
import io.wangxiao.edu.home.entity.arrange.ArrangeRecord;
import io.wangxiao.edu.home.entity.course.CourseDto;
import io.wangxiao.edu.home.entity.course.CourseStudyhistory;
import io.wangxiao.edu.home.entity.course.QueryCourse;
import io.wangxiao.edu.home.entity.letter.MsgReceive;
import io.wangxiao.edu.home.entity.letter.QueryMsgReceive;
import io.wangxiao.edu.home.entity.member.MemberRecordDTO;
import io.wangxiao.edu.home.entity.plan.PlanRecord;
import io.wangxiao.edu.home.entity.user.*;
import io.wangxiao.edu.home.entity.userprofile.ProfileType;
import io.wangxiao.edu.home.entity.userprofile.UserProfile;
import io.wangxiao.edu.home.entity.website.WebsiteImages;
import io.wangxiao.edu.home.entity.website.WebsiteProfile;
import io.wangxiao.edu.home.service.answer.AnswerQuestionService;
import io.wangxiao.edu.home.service.arrange.ArrangeRecordService;
import io.wangxiao.edu.home.service.arrange.ArrangeService;
import io.wangxiao.edu.home.service.course.CourseService;
import io.wangxiao.edu.home.service.course.CourseStudyhistoryService;
import io.wangxiao.edu.home.service.letter.MsgReceiveService;
import io.wangxiao.edu.home.service.member.MemberRecordService;
import io.wangxiao.edu.home.service.plan.PlanRecordService;
import io.wangxiao.edu.home.service.user.*;
import io.wangxiao.edu.home.service.userprofile.UserProfileService;
import io.wangxiao.edu.home.service.website.WebsiteImagesService;
import io.wangxiao.edu.home.service.website.WebsiteProfileService;
import io.wangxiao.edu.sysuser.entity.SysUserGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    @Autowired
    private ArrangeRecordService arrangeRecordService;//考试任务记录服务
    @Autowired
    private ArrangeService arrangeService;//考试安排服务
    @Autowired
    private UserGroupMiddleService UserGroupMiddleService;
    @Autowired
    private UserGroupService userGroupService;
    @Autowired
    private PlanRecordService planRecordService;
    @Autowired
    private CourseStudyhistoryService courseStudyhistoryService;


    CacheKit cacheKit = CacheKit.getInstance();
    static EhcacheKit ehcache = EhcacheKit.getInstance();
    private String toUpdatePwd = getViewPath("/user/change_password");
    //private String registerJsp = getViewPath("/user/register");// 注册
    private String loginjsp = getViewPath("/user/login");// 登录
    private String registerJsp = getViewPath("/user/register");// 注册
    private String forgetPassword = getViewPath("/user/forget_password");// 忘记密码
    private String getpwdjsp = getViewPath("/user/getpwd");// 找回后修改密码页面
    private String uc_home = getViewPath("/ucenter/uchome");// 个人中心首页
    private String userInfo = getViewPath("/ucenter/u-userinfo");// 基本资料
    private String avatar = getViewPath("/ucenter/avatar");// 修改头像
    private String toPwd = getViewPath("/ucenter/update_pwd");// 修改密码
    private String toMobile = getViewPath("/ucenter/update_mobile");// 修改手机号
    private String toEmail = getViewPath("/ucenter/update_email");// 修改邮箱
    private String accoutList = getViewPath("/ucenter/account_list");// 账户历史
    private String memberInfo = getViewPath("/ucenter/u-my-member");// 会员历史
    private String queryUserLetter = getViewPath("/ucenter/u-sys-news");// 用户消息
    private String myProfile = getViewPath("/ucenter/u_profile");// 我的第三方
    private String toMyCouAnswerQuestion = getViewPath("/ucenter/myAnswer");
    private String toMyTask = getViewPath("/ucenter/myTask");//我的任务
    private String toMyGroupTask = getViewPath("/ucenter/myGroupTask");//我的部门任务
    private String groupUser = getViewPath("/ucenter/myGroupUser");//我的部门员工
    private String groupArrange = getViewPath("/ucenter/myGrouprArragne");//我的部门员工

    private String jump_user = getViewPath("/user/jump_user");// 绑定邮箱手机页面
    private String register_user = getViewPath("/user/jump_register_user");// 第三方注册邮箱手机页面

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

    @InitBinder("taskRecord")
    public void initBinderTaskRecord(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("taskRecord.");
    }

    // 注册
    @RequestMapping("/register")
    public String regist() {

        return registerJsp;
    }

    // 注册
    @RequestMapping("/front/forget_passwd")
    public String forgotpwd() {
        return forgetPassword;
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
        return "/sysuser/login/limitVerifyError";
    }

    public void sendValidateMessage(String message, HttpServletResponse response) {
        try {
            response.setCharacterEncoding("utf-8");
            response.getWriter().write(message);
        } catch (Exception e) {
        }
    }

    /**
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
    public Map<String, Object> doregister(@ModelAttribute UserForm userForm, ModelMap model, HttpServletRequest request,
                                          HttpServletResponse response) {
        Map<String, Object> json = null;
        try {
            // 获得注册开关是否开启
            Map<String, Object> map = getLoginAndRegKeyword();
            if (ObjectUtils.isNull(map) || map.get("verifyRegister").toString().equalsIgnoreCase("OFF")) {
                json = this.getJsonMap(false, "noRegister", null);
                return json;
            }
/*
            if (ObjectUtils.isNull(map) || StringUtils.isEmpty(map.toString()))
*/

            if (ObjectUtils.isNull(userForm)) {// 验证表单数据是否为空
                json = this.getJsonMap(false, "formDataIsNot", null);
                return json;
            }
            String regType = request.getParameter("regType");
            String randomCode = request.getParameter("randomCode");
            Object regCode = null;
            if ("mobile".equals(regType)) {
                // 验证手机是否为空
                if (ObjectUtils.isNull(userForm.getMobile()) || StringUtils.isEmpty(userForm.getMobile())) {
                    json = this.getJsonMap(false, "mobileIsNull", null);
                    return json;
                }
                // 在手机不为空的情况下，验证手机格式是否正确
                if (ObjectUtils.isNotNull(userForm.getMobile()) && StringUtils.isNotEmpty(userForm.getMobile()) && !userForm
                        .getMobile().matches("^(13[0-9]|15[012356789]|18[0-9]|14[57]|17[012356789])[0-9]{8}$")) {
                    json = this.getJsonMap(false, "false", null);
                    return json;
                }
                regCode = cacheKit.get(MemConstans.REGISTER_PHONE_CODE + userForm.getMobile());
            } else if ("email".equals(regType)) {
                // 验证邮箱是否为空
                if (ObjectUtils.isNull(userForm.getEmail()) || StringUtils.isEmpty(userForm.getEmail())) {
                    json = this.getJsonMap(false, "emailIsNot", null);
                    return json;
                }
                // 在邮箱不为空的情况下，验证邮箱格式是否正确
                if (ObjectUtils.isNotNull(userForm.getEmail()) && StringUtils.isNotEmpty(userForm.getEmail())) {
                    Pattern emailRes = Pattern.compile("^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$");
                    Matcher mat = emailRes.matcher(userForm.getEmail().toLowerCase());
                    boolean emailF = mat.matches();
                    if (!emailF) {
                        json = this.getJsonMap(false, "emailFormatError", null);
                        return json;
                    }
                }
                regCode = cacheKit.get(MemConstans.REGISTER_EMAIL_CODE + userForm.getEmail());
            }
            // 验证码是否为空
            if (StringUtils.isEmpty(randomCode)
                    || ObjectUtils.isNull(regCode)
                    || !randomCode.equals(regCode.toString())) {
                json = this.getJsonMap(false, "验证码错误", null);
                return json;
            }

            // 验证密码是否为空
            if (ObjectUtils.isNull(userForm.getPassword()) || StringUtils.isEmpty(userForm.getPassword())) {
                json = this.getJsonMap(false, "pwdIsNull", null);
                return json;
            }
            // 验证密码一致
            if (!userForm.getPassword().equals(userForm.getConfirmPassword())) {
                json = this.getJsonMap(false, "pwdNotEqual", null);
                return json;
            }

            // 验证输入数据合法性
            if (PreventInfusion.sql_inj(userForm.getPassword())) {
                json = this.getJsonMap(false, "regDangerWord", null);
                return json;
            }
            User user = new User();
            if ("mobile".equals(regType)) {
                user.setMobile(userForm.getMobile().trim());
                int ismobile = userService.getUserByMobile(user);
                // 验证手机唯一
                if (ismobile != 0) {
                    json = this.getJsonMap(false, "regMobileExist", null);
                    return json;
                }
            } else if ("email".equals(regType)) {
                user.setEmail(userForm.getEmail().toLowerCase().trim());// 邮箱存储时转为小写
                List<User> list = userService.getUserList(user);
                // 验证邮箱唯一,邮箱是否已经注册
                if (ObjectUtils.isNotNull(list)) {
                    json = this.getJsonMap(false, "regEmailExist", null);
                    return json;
                }
            }
            // 账号注册
            String userIp = WebUtils.getIpAddr(request);
            user.setPassword(userForm.getPassword().trim());
            user.setUserip(userIp);
            String mobileFrom = request.getParameter("mobileFrom");
            if (ObjectUtils.isNotNull(mobileFrom) && "mobile".equals(mobileFrom)) {
                user.setRegisterFrom(UserExpandFrom.mobileFrom.toString());//微站注册来源
            } else {
                user.setRegisterFrom(UserExpandFrom.registerFrom.toString());// 账号来源是通过注册生成的
            }
            userService.addUser(user);
            Long upUserId = this.getUpLoginId(request);
            // 返还上线用户积分
            if (upUserId != 0L) {
                userIntegralService.addUserIntegral(IntegralKeyword.rebate.toString(), upUserId, 0L, user.getId(), "");
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
            cacheKit.remove(MemConstans.REGISTER_PHONE_CODE + userForm.getMobile());
            cacheKit.remove(MemConstans.REGISTER_EMAIL_CODE + userForm.getEmail());
            json = this.getJsonMap(true, "", null);
        } catch (Exception e) {
            logger.error("userRegist error", e);
            json = this.getJsonMap(false, "系统错误", null);
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
    public Map<String, Object> dologin(@ModelAttribute UserForm userForm, HttpServletRequest request,
                                       HttpServletResponse response) {
        Map<String, Object> json = null;
        try {
            userForm.setEmail(userForm.getEmail().trim());
            userForm.setPassword(userForm.getPassword().trim());
            // 获得登录开关是否开启
       /* Map<String, Object> map = getLoginAndRegKeyword();
        if (ObjectUtils.isNull(map) || map.get("verifyLogin").toString().equalsIgnoreCase("OFF")) {
		json = this.getJsonMap(false, "", null);
		return json;
	    }*/
            // 验证输入数据合法性
            if (PreventInfusion.sql_inj(userForm.getPassword())) {
                json = this.getJsonMap(false, "inputIllegal", null);
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
            Pattern emailRes = Pattern
                    .compile("^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$");
            mat = emailRes.matcher(userForm.getEmail());
            boolean emailF = mat.matches();
            if (emailF) {
                list = userService.getUserListForLogin(user);
            }

            // 通过数据库，验证用户是否存在
            if (ObjectUtils.isNull(list)) {
                json = this.getJsonMap(false, "formDataNot", null);
                return json;
            } else {
                user = list.get(0);
                // 验证密码是否正确
                if (checkIsRight(user.getPassword(), userForm.getPassword(), user.getCustomerkey())) {
                    String autoThirty = request.getParameter("autoThirty");// 是否30天自动登录
                    // 执行登录操作
                    userService.setLoginStatus(user, autoThirty, request, response);
                    json = this.getJsonMap(true, "success", "登录成功");
                } else {
                    json = this.getJsonMap(false, "false", "密码错误");
                }
            }
            return json;
        } catch (Exception e) {
            logger.error("Usercontroller.dologin", e);
            json = this.getJsonMap(false, "error", "系统异常");
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
    public Map<String, Object> checkEmail(@ModelAttribute UserForm userForm, HttpServletRequest request,
                                          HttpServletResponse response) {
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
        }
        return null;
    }

//    /**
//     * 验证验证码
//     *
//     * @param randomCode
//     * @param request
//     * @param response
//     * @return
//     */
//    @RequestMapping("/checkRandomCode")
//    public void checkRandomCode(@RequestParam("randomCode") String randomCode, HttpServletRequest request,
//	    HttpServletResponse response) {
//	try {
//	    if (ObjectUtils.isNull(randomCode)
//		    || !randomCode.toUpperCase().equals(request.getSession().getAttribute("COMMON_RAND_CODE"))) {
//		sendValidateMessage("false", response);
//	    } else {
//		sendValidateMessage("true", response);
//	    }
//
//	} catch (Exception e) {
//	    logger.error("CustomerWebAction.checkRandomCode", e);
//	}
//    }

    /**
     * 手机验证
     *
     * @param mobile
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/checkRegMoblie")
    public String checkRegMoblie(@RequestParam("mobile") String mobile, HttpServletRequest request,
                                 HttpServletResponse response) {
        try {
            if (StringUtils.isNotEmpty(mobile)) {
                User user = new User();
                user.setMobile(mobile);
                if (userService.getUserByMobile(user) != 0) {
                    sendValidateMessage("false", response);
                } else {
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
    public Map<String, Object> updatePwd(HttpServletRequest request, HttpServletResponse response,
                                         @RequestParam(required = true) String password, @RequestParam(required = true) String confirmPwd,
                                         @RequestParam(required = true) String code) {
        Map<String, Object> json = null;
        try {
            UserCode userCode = userCodeService.checkUserCode(code);
            if (ObjectUtils.isNull(userCode)) {
                json = this.getJsonMap(false, "该链接已经使用或者已经过期", null);
                return json;
            }
            if (password.equals(confirmPwd)) {
                if (password.length() < 6) {
                    json = this.getJsonMap(false, "密码长度不能小于6位", null);
                    return json;
                }
                User user = new User();
                user.setId(userCode.getUserId());
                user.setPassword(password);
                userService.updatePwdById(user, userCode);
                json = this.getJsonMap(true, "", null);
                cacheKit.remove(MemConstans.USEREXPAND_INFO + userCode.getUserId());
            } else {
                json = this.getJsonMap(false, "两次密码不一致", null);
            }
        } catch (Exception e) {
            this.setExceptionRequest(request, e);
            json = this.getJsonMap(false, "系统异常", null);
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
    public String exit(HttpServletRequest request, HttpServletResponse response) {
        try {

            // 前台账户退出
            String mobileFlag = request.getParameter("mobileFlag");
            Long userId = SingletonLoginUtils.getLoginUserId(request);
            loginOnliceService.deleteLoginOnlineById(userId);
            String sid = WebUtils.getCookie(request, CommonConstants.USER_SINGEL_ID);
            if (StringUtils.isNotEmpty(sid)) {
                cacheKit.remove(sid);
            }
            cacheKit.remove(MemConstans.USEREXPAND_INFO + userId);
            WebUtils.deleteCookie(request, response, CommonConstants.USER_SINGEL_ID);
            WebUtils.deleteCookie(request, response, CommonConstants.USER_SINGEL_NAME);
            WebUtils.deleteCookie(request, response, "usercookieuserimg");
            WebUtils.deleteCookie(request, response, "e.subject");
	   /* if (mobileFlag != null && mobileFlag.equals("mobile")) {
			return "redirect:/mobile/index";
	    } else {
			return "redirect:/index";
	    }*/
            return "redirect:/";
        } catch (Exception e) {
            logger.error("UserController.exit", e);
            return this.setExceptionRequest(request, e);
        }
    }

    /**
     * 查询登陆用户id
     *
     * @return
     */
    @RequestMapping("/user/loginuser")
    @ResponseBody
    public Object loginuser(HttpServletRequest request) {
        Map<String, Object> json = null;
        try {
            JsonObject userJsonObject = SingletonLoginUtils.getLoginUser(request);
            if (ObjectUtils.isNotNull(userJsonObject)) {
                userJsonObject.addProperty("password", "");
                userJsonObject.addProperty("customerkey", "");
                json = this.getJsonMap(true, null, userJsonObject);
            } else {
                json = this.getJsonMap(false, null, null);
            }
        } catch (Exception e) {
            logger.error("UserController.exit", e);
            json = this.getJsonMap(false, "", null);
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
        Map<String, Object> json = null;
        try {
            if (StringUtils.isNotEmpty(randomCode)) {
                if (!randomCode.equals(request.getSession().getAttribute(RandomCodeController.RAND_CODE))) {
                    json = this.getJsonMap(false, "验证码错误", null);
                    return json;
                }
            } else {
                json = this.getJsonMap(false, "验证码不能为空", null);
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
                        String link = CommonConstants.contextPath + "/front/getpwdcode?code="
                                + URLEncoder.encode(desCode, "utf-8");
                        // 获得网站配置
                        Map<String, Object> websitemap = websiteProfileService
                                .getWebsiteProfileByType(WebSiteProfileType.web.toString());
                        Map<String, Object> web = (Map<String, Object>) websitemap.get("web");
                        String company = web.get("company").toString();
                        emailService.sendMail(userCode.getContext(), "您好,请打开下面的链接并及时修改密码,3天内有效，<a href='" + link + "'>"
                                + link + "</a><br/>如果无法打开，请复制链接到浏览器地址栏中", "帐号找回[" + company + "]");
                        // 发送找回密码邮件
                        json = this.getJsonMap(true, "邮件发送成功", null);
                    } else {
                        json = this.getJsonMap(false, "发送邮件错误,请稍后再试", null);
                    }

                } else {
                    json = this.getJsonMap(false, "该帐号不存在", null);
                }
            }
            // json=this.getJsonMap(true, "", null);
        } catch (Exception e) {
            logger.error("UserController.exit", e);
            json = this.getJsonMap(false, "", null);
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
        model.addAttribute(OrderConstans.RESMSG, WebUtils.replaceTagHTML(request.getParameter(OrderConstans.RESMSG)));
        if (request.getParameter("type") != null) {
            model.addAttribute("type", WebUtils.replaceTagHTML(request.getParameter("type")));
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
            PageEntity page = null;
            // 获取当前用户登录id
            Long userId = getLoginUserId(request);

            // 获取用户部门学习记录信息
            List<UserGroup> groupList = userGroupService.getGroupLearningNumByUserId(userId);
            model.addAttribute("groupList", groupList);

            // 获取学习记录
            page = new PageEntity();
            page.setPageSize(5);
            CourseStudyhistory courseStudyhistory = new CourseStudyhistory();
            courseStudyhistory.setUserId(getLoginUserId(request));
            List<CourseStudyhistory> studylist = courseStudyhistoryService.getCourseStudyhistoryListByCondition(courseStudyhistory, page);
            model.addAttribute("studylist", studylist);

            // 获取计划信息
            page = new PageEntity();
            page.setPageSize(2);
            PlanRecord planRecord = new PlanRecord();
            planRecord.setUserId(userId);
            List<PlanRecord> recordList = planRecordService.getPlanRecordListPage(planRecord, page);
            model.addAttribute("recordList", recordList);

            // 获取系统配置
            Map<String, Object> profile = websiteProfileService.getWebsiteProfileByType(WebSiteProfileType.keyword.toString());
            Map<String, Object> keyword = profile.containsKey("keyword") ? (Map) profile.get("keyword") : null;
            if (keyword.containsKey("verifyExam") && "ON".equals(keyword.get("verifyExam"))) {
                // 获取考试记录
                Map<String, String> map = new HashMap<>();
                map.put("page.pageSize", 3 + "");
                map.put("cusId", userId + "");
                String resultJson = HttpUtil.doPost(CommonConstants.examPath + "/api/yzl/paperRecordListPage", map);
                model.addAttribute("examRecord", resultJson);

                // 评测记录
                page = new PageEntity();
                page.setPageSize(3);
                ArrangeRecord arrangeRecord = new ArrangeRecord();
                arrangeRecord.setUserId(userId);
                List<ArrangeRecord> arrangeRecordList = arrangeRecordService.getArrangeRecordList(arrangeRecord, page);
                request.setAttribute("arrangeRecordList", arrangeRecordList);
            }
            // 获取岗位课程信息
            page = new PageEntity();
            page.setPageSize(3);
            QueryCourse queryCourse = new QueryCourse();
            queryCourse.setUserId(userId);
            queryCourse.setIsGroup(1l);
            List<CourseDto> courseList = courseService.getUserCourseList(queryCourse, page);
            model.addAttribute("courseList", courseList);

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

            queryUserAccounthistory.setUserId(UserController.getLoginUserId(request));
            UserAccount userAccount = userAccountService.getUserAccountByUserId(getLoginUserId(request));
            List<UserAccounthistory> accList = userAccounthistoryService
                    .getWebUserAccountHistroyListByCondition(queryUserAccounthistory, page);
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
    public Object updatepwd(HttpServletRequest request, @RequestParam("newpwd") String newpwd,
                            @RequestParam("oldpwd") String oldpwd) {
        Map<String, Object> json = null;
        try {
            User user = userService.getUserById(getLoginUserId(request));
            if (checkIsRight(user.getPassword(), oldpwd, user.getCustomerkey())) {
                user.setPassword(newpwd);
                userService.updatePwdById(user, null);
                json = this.getJsonMap(true, "", "");
            } else {
                json = this.getJsonMap(false, "", null);
            }

            return json;
        } catch (Exception e) {
            logger.error("UserController.updatepwd", e);
            json = this.getJsonMap(false, "", null);
            return json;
        }
    }


    @RequestMapping("/uc/updateMobile")
    public String updateMobile(HttpServletRequest request) {
        try {
            Long userId = getLoginUserId(request);
            User user = userService.getUserById(userId);
            request.setAttribute("user", user);
        } catch (Exception e) {
            logger.error("UserController.updateMobile");
        }
        return toMobile;
    }

    @RequestMapping("/uc/updateEmail")
    public String updateEmail(HttpServletRequest request) {
        try {
            Long userId = getLoginUserId(request);
            User user = userService.getUserById(userId);
            request.setAttribute("user", user);
        } catch (Exception e) {
            logger.error("UserController.updateEmail");
        }
        return toEmail;
    }

    /**
     * 基本信息
     */
    @RequestMapping("/uc/uinfo")
    public String userInfo(HttpServletRequest request) {
        try {
            //查询用户信息
            UserExpandDto userExpandDto = userExpandService.getUserExpandByUids(getLoginUserId(request));
            request.setAttribute("queryUser", userExpandDto);
            List<UserProfile> profileList = userProfileService.getUserProfileByUserId(userExpandDto.getCusId());
            if (profileList != null && profileList.size() > 0) {
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
        Map<String, Object> json = null;
        try {
            // 修改用户信息
            queryUser.setId(getLoginUserId(request));
//			Pattern emailRes = Pattern.compile("^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$");
//			Matcher mat = emailRes.matcher(queryUser.getEmail());
//			boolean emailF = mat.matches();
//			if (!emailF) {
//				json = this.getJsonMap(true, "", "emailFormatError");
//				return json;
//			}
//			List<UserProfile> profileList = userProfileService.getUserProfileByUserId(getLoginUserId(request));
//			// 验证是否是第三方登录
//			if (profileList != null && profileList.size() > 0) {
//				queryUser.setUpdateEmail("YES");
//				User user = new User();
//				user.setEmail(queryUser.getEmail());
//				List<User> list = userService.getUserList(user);
//				if (list != null && list.size() > 0) {
//					user = list.get(0);
//					if (user.getEmail().equals(queryUser.getEmail())
//						&& user.getId().longValue() != queryUser.getId().longValue()) {
//						json = this.getJsonMap(true, "", "emailHave");
//						return json;
//					}
//				}
//			}
            String falg = userService.updateQueryUser(queryUser);
            if ("success".equals(falg)) {
                JsonObject userJsonObject = SingletonLoginUtils.getLoginUser(request);
                userJsonObject.addProperty("nickname", queryUser.getNickname());
                userJsonObject.addProperty("showname", queryUser.getShowname());
                userJsonObject.addProperty("gender", queryUser.getGender());
                String sid = WebUtils.getCookie(request, CommonConstants.USER_SINGEL_ID);
                ehcache.put(sid, userJsonObject.toString());
                cacheKit.set(sid, userJsonObject.toString(), MemConstans.USER_TIME);
            }
            json = this.getJsonMap(true, "", falg);
        } catch (Exception e) {
            logger.error("UserController.updatepwd", e);
            json = this.getJsonMap(false, "", "");
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
    public Object userUpdateAvatar(HttpServletRequest request, @RequestParam("userId") Long userId,
                                   @RequestParam("avatar") String avatar) {
        Map<String, Object> json = null;
        try {
            UserExpand userExpand = new UserExpand();
            userExpand.setAvatar(avatar);
            userExpand.setCusId(userId);
            userExpandService.updateUserExpand(userExpand);

            JsonObject userJsonObject = SingletonLoginUtils.getLoginUser(request);
            userJsonObject.addProperty("avatar", avatar);
            String sid = WebUtils.getCookie(request, CommonConstants.USER_SINGEL_ID);
            ehcache.put(sid, userJsonObject.toString());
            cacheKit.set(sid, userJsonObject.toString(), MemConstans.USER_TIME);

            json = this.getJsonMap(true, "", "");
        } catch (Exception e) {
            logger.error("UserController.userUpdateAvatar", e);
            json = this.getJsonMap(false, "", "");
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
        Map<String, Object> json = null;
        if (this.isLogin(request)) {
            json = this.getJsonMap(true, null, null);
        } else {
            json = this.getJsonMap(false, null, null);
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
    public Map<String, Object> getUserPersonalityImages(HttpServletRequest request,
                                                        @ModelAttribute("page") PageEntity page) {
        Map<String, Object> json = null;
        try {
            // 设置分页

            page.setPageSize(12);
            WebsiteImages websiteImages = new WebsiteImages();
            websiteImages.setKeyWord(ImagesType.userPersonalityImages.toString());// 个人中心keyword
            // 方法公用
            List<WebsiteImages> websiteImagesList = websiteImagesService.getImgPageList(websiteImages, page);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("websiteImagesList", websiteImagesList);
            map.put("page", page);
            json = this.getJsonMap(true, "success", map);
        } catch (Exception e) {
            logger.error("UserController.getUserPersonalityImages", e);
            json = this.getJsonMap(false, "Images is error", null);
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
    public Map<String, Object> updateUserCover(HttpServletRequest request,
                                               @RequestParam("bannerUrl") String bannerUrl) {
        Map<String, Object> json = null;
        try {
            if (bannerUrl != null) {// 更新
                userExpandService.updateUserExpandBannerUrl(getLoginUserId(request), bannerUrl);
                json = this.getJsonMap(true, " 更新封面成功", bannerUrl);
            } else {
                json = this.getJsonMap(false, " request parameter is null", null);
            }
        } catch (Exception e) {
            logger.error("", e);
            json = this.getJsonMap(false, "updated is error", null);
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
                map.put("message", "success");// 成功
            } else {
                map.put("message", "false");// 失败
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
            page.setPageSize(6);// 分页页数为6
            MsgReceive msgReceive = new MsgReceive();
            msgReceive.setReceivingCusId(getLoginUserId(request));// set用户id
            // msgReceiveService.updateAllReadMsgReceiveInbox(msgReceive);//
            // 更新所有收件箱为已读
            List<QueryMsgReceive> queryLetterList = msgReceiveService.queryMsgReceiveByInbox(msgReceive, page);// 查询站内信收件箱
            modelAndView.addObject("queryLetterList", queryLetterList);// 查询出的站内信放入modelAndView中
            modelAndView.addObject("page", page);// 分页参数放入modelAndView中
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
            // 未登录不可操作
            if (getLoginUserId(request).longValue() == 0) {
                return map;
            }
            Map<String, String> queryletter = msgReceiveService
                    .queryUnReadMsgReceiveNumByCusId(getLoginUserId(request));// 查询该用户有多少未读消息
            map.put("entity", queryletter);// 把值放入map中返回json
        } catch (Exception e) {
            logger.error("LetterAction.queryUnReadLetter", e);
            setExceptionRequest(request, e);
        }
        return map;
    }

    @RequestMapping("/front/pro")
    @ResponseBody
    public Object frontpro(@RequestParam String type, @RequestParam(required = false) String key,
                           @RequestParam(required = false) String value) {
        Map<String, Object> json = null;
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
            json = this.getJsonMap(true, null, websitemap);
        } catch (Exception e) {

        }
        return json;
    }

    public Map<String, Object> getLoginAndRegKeyword() {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            Map<String, Object> keywordmap = websiteProfileService
                    .getWebsiteProfileByType(WebSiteProfileType.keyword.toString());
            if (ObjectUtils.isNotNull(keywordmap)) {
                JsonObject jsonObject = jsonParser
                        .parse(gson.toJson(keywordmap.get(WebSiteProfileType.keyword.toString()))).getAsJsonObject();
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
    public ModelAndView myCouAnswer(HttpServletRequest request, @ModelAttribute("page") PageEntity page) {
        ModelAndView model = new ModelAndView(toMyCouAnswerQuestion);
        try {

            List<AnswerQuestion> answerList = answerQuestionService
                    .queryMyCouAnswerQuestionList(getLoginUserId(request), page);
            model.addObject("answerList", answerList);
            model.addObject("page", page);
        } catch (Exception e) {
            logger.error("myAnswer", e);
        }
        return model;
    }

    /**
     * 第三方登录
     *
     * @param request
     * @return
     */
    @RequestMapping("/uc/myProfile")
    public String myProfile(HttpServletRequest request) {
        try {
            List<UserProfile> userProfileList = userProfileService.getUserProfileByUserId(getLoginUserId(request));
            // QQ第三方信息
            UserProfile userProfileQQ = new UserProfile();
            UserProfile userProfileSina = new UserProfile();
            UserProfile userProfileWeiXin = new UserProfile();

            // 循环查出第三方信息
            for (UserProfile userProfile : userProfileList) {
                if (userProfile.getProfiletype().equals(ProfileType.QQ.toString())) {
                    // QQ
                    userProfileQQ = userProfile;
                }
                if (userProfile.getProfiletype().equals(ProfileType.SINA.toString())) {
                    // 新浪
                    userProfileSina = userProfile;
                }
                if (userProfile.getProfiletype().equals(ProfileType.WEIXIN.toString())) {
                    // 微信
                    userProfileWeiXin = userProfile;
                }
            }
            request.setAttribute("userProfileQQ", userProfileQQ);
            request.setAttribute("userProfileSina", userProfileSina);
            request.setAttribute("userProfileWeiXin", userProfileWeiXin);
        } catch (Exception e) {
            logger.error("UserController.myProfile()---error", e);
            setExceptionRequest(request, e);
        }
        return myProfile;
    }

    /**
     * 前台我的任务
     *
     * @return
     */
    @RequestMapping("/uc/myArrangeForWeb")
    public String myTaskForWeb(HttpServletRequest request, @ModelAttribute("task") Arrange arrange, @ModelAttribute("page") PageEntity page) {
        try {
            Long loginUserId = getLoginUserId(request);
            arrange.setUserId(loginUserId);
            arrange.setType(Long.valueOf(0));
            List<Arrange> tasks = arrangeService.myArrangeForWeb(arrange, page);
            request.setAttribute("tasks", tasks);
        } catch (Exception e) {
            logger.error("UserController.myTaskForWeb", e);
        }
        return toMyTask;
    }

    /**
     * 前台我的任务跳转时重复判断
     *
     * @return
     */
    @RequestMapping("/uc/myArrangeForWebisRepeat")
    @ResponseBody
    public Map<String, Object> myTaskForWebisRepeat(HttpServletRequest request, @ModelAttribute("isRepeat") Long isRepeat
            , @ModelAttribute("id") Long id) {
        Map<String, Object> json = null;
        try {
            if (isRepeat == 1) {//可重复
                json = this.getJsonMap(true, "success", null);
            } else {
                isRepeat = arrangeRecordService.getIscompleteArrange(id);
                if (isRepeat == 0) {//不可重复
                    json = this.getJsonMap(false, "error", null);
                }
                if (isRepeat == 1) {//可重复
                    json = this.getJsonMap(true, "success", null);
                }
            }
        } catch (Exception e) {
            logger.error("UserController.myTaskForWebisRepeat", e);
            json = this.getJsonMap(false, "系统错误", null);
        }
        return json;
    }

    /**
     * 我的部门任务
     *
     * @param request
     * @param arrange
     * @param page
     * @return
     */
    @RequestMapping("/uc/myGroupArrangeForWeb")
    public String myGroupTaskFroweb(HttpServletRequest request, @ModelAttribute("task") Arrange arrange, @ModelAttribute("page") PageEntity page) {
        try {
            //获取用户登录Id
            Long sysUserId = SingletonLoginUtils.getLoginUserId(request);
            SysUserGroup sysUserGroup = new SysUserGroup();
            sysUserGroup.setSysUserId(sysUserId);
            page.setPageSize(10);
            // 根据员工编号查询出所属部门
            List<UserGroupMiddle> userGroupMiddles = UserGroupMiddleService.getUserGroupByUserId(sysUserId);
            if (userGroupMiddles != null && userGroupMiddles.size() > 0) {
                for (UserGroupMiddle _userGroupMiddles : userGroupMiddles) {// 循环部门获取该用户下的所有部门
                    arrange.setUserGroupId(_userGroupMiddles.getGroupId());//获取用户所在部门编号
                }
                arrange.setType(Long.valueOf(1));
                List<Arrange> groupArrange = arrangeService.myGroupArrangeFroweb(arrange, page);
                request.setAttribute("groupTask", groupArrange);
            }
        } catch (Exception e) {
            logger.error("UserController.myGroupTaskFroweb");
        }
        return toMyGroupTask;
    }


    /**
     * 我的部门员工
     *
     * @param request
     * @return
     */
    @RequestMapping("/uc/groupuser")
    public ModelAndView myGroupUser(HttpServletRequest request, @ModelAttribute("page") PageEntity page, @ModelAttribute("arrange") Arrange arrange) {
        ModelAndView modelAndView = new ModelAndView(groupUser);
        try {
            //部门名称
            ArrangeRecord Arrange = new ArrangeRecord();
            UserGroupMiddle userGroupMiddle = new UserGroupMiddle();
            Long userid = getLoginUserId(request);
            userGroupMiddle = UserGroupMiddleService.getUserGroupUserId(userid);
            Arrange = arrangeRecordService.getGrouduserid(userid);
            UserGroup userGroup = new UserGroup();
            userGroup = userGroupService.queryUserGroupById(userGroupMiddle.getGroupId());
            //部门任务总数以及部门任务为完成数
            Long tasknumber = arrangeRecordService.getcountgruopArrange(userGroupMiddle.getGroupId());
            Long task = arrangeRecordService.getgruopArrange(userGroupMiddle.getGroupId());
            if (task == null) {
                task = 0L;
            }
            if (tasknumber == null) {
                tasknumber = 0L;
            }
            Long number = UserGroupMiddleService.getUserGroupCount(userGroupMiddle.getGroupId());//总人数

            //获取用户登录Id
            Long sysUserId = SingletonLoginUtils.getLoginUserId(request);
            SysUserGroup sysUserGroup = new SysUserGroup();
            sysUserGroup.setSysUserId(sysUserId);
            page.setPageSize(10);
            // 根据员工编号查询出所属部门
            List<UserGroupMiddle> userGroupMiddles = UserGroupMiddleService.getUserGroupByUserId(sysUserId);
            if (userGroupMiddles != null && userGroupMiddles.size() > 0) {
                for (UserGroupMiddle _userGroupMiddles : userGroupMiddles) {// 循环部门获取该用户下的所有部门
                    arrange.setUserGroupId(_userGroupMiddles.getGroupId());//获取用户所在部门编号
                }
                //执行查询部门任务
                List<Arrange> groupArrangeList = arrangeService.getGroupArrangeList(arrange, page);
                request.setAttribute("groupArrangeList", groupArrangeList);
                request.setAttribute("page", page);
                request.setAttribute("arrange", arrange);
            }
            modelAndView.addObject("number", number);
            modelAndView.addObject("groupname", userGroup.getName());
            modelAndView.addObject("tasknumber", tasknumber);
            modelAndView.addObject("task", task);
        } catch (Exception e) {
            logger.error("UserController.myGroupUser");
        }
        return modelAndView;
    }

    /**
     * 跳转二级饼状图
     */
    @RequestMapping("uc/grouparragne/{groudid}/{arrangeid}")
    public ModelAndView grouparragne(HttpServletRequest request, HttpServletResponse response, @PathVariable Long groudid, @PathVariable Long arrangeid) {
        ModelAndView modelAndView = new ModelAndView(groupArrange);
        try {
            //饼状图
            Object[][] arry = new Object[4][2];
            DecimalFormat df = new java.text.DecimalFormat("#.0");// 保留百分比一位小数
            ArrangeRecord arrangeRecord = new ArrangeRecord();
            //arrangeRecord.setUserGroupId(groudid);
            arrangeRecord.setArrangeId(arrangeid);
            List<ArrangeRecord> arrangeRecordlist = arrangeRecordService.getGroudArrange(arrangeRecord);
            List<ArrangeRecord> arrangeRecordGroudUser = arrangeRecordService.getArrangeRecordGroudUsers(arrangeRecord);
            String TaskTecord = "";
            Double taskRecordNum = (double) arrangeRecordlist.size();//考试人总数
            BigDecimal FialB = new BigDecimal(60);//<60分
            BigDecimal PassB = new BigDecimal(80);//60~79分
            BigDecimal GoodB = new BigDecimal(90);//80~89分
            BigDecimal NiceB = new BigDecimal(89);//>=90分
            Long fial = 0L;
            Long Pass = 0L;
            Long Good = 0L;
            Long Nice = 0L;
            for (int i = 0; i < arrangeRecordlist.size(); i++) {
                if (arrangeRecordlist.get(i).getScore().compareTo(FialB) == -1) {//当成绩<60时
                    fial++;
                }
                if (arrangeRecordlist.get(i).getScore().compareTo(FialB) == 1 &&
                        arrangeRecordlist.get(i).getScore().compareTo(PassB) == -1 ||
                        arrangeRecordlist.get(i).getScore().compareTo(FialB) == 0
                        ) {//当成绩>=60并且<80时
                    Pass++;
                }
                if (
                        arrangeRecordlist.get(i).getScore().compareTo(PassB) == 1 &&
                                arrangeRecordlist.get(i).getScore().compareTo(GoodB) == -1 ||
                                arrangeRecordlist.get(i).getScore().compareTo(PassB) == 0) {//当成绩>=80并且<90时
                    Good++;
                }
                if (arrangeRecordlist.get(i).getScore().compareTo(NiceB) == 1) {//当成绩>=90时
                    Nice++;
                }
            }
            Double[] Max = new Double[arrangeRecordlist.size()];//从高到低排序
            for (int i = 0, lenght = arrangeRecordlist.size(); i < lenght; i++) {
                Max[i] = arrangeRecordlist.get(i).getScore().doubleValue();
            }
            for (int i = 0, lenght = arrangeRecordlist.size(); i < lenght; i++) {
                for (int j = 0, lenghtj = arrangeRecordlist.size(); j < lenghtj; j++) {
                    Double temp = 0.0;
                    if (Max[i] > Max[j]) {
                        temp = Max[j];
                        Max[j] = Max[i];
                        Max[i] = temp;
                    }
                }
            }

            //Long number = UserGroupMiddleService.getUserGroupCount(arrangeRecord.getUserGroupId());//总人数
            if (taskRecordNum > 0) {
                float fialPersent = Float.parseFloat(df.format((fial / taskRecordNum) * 100));
                float PassPersent = Float.parseFloat(df.format((Pass / taskRecordNum) * 100));
                float GoodPersent = Float.parseFloat(df.format((Good / taskRecordNum) * 100));
                float NicePersent = Float.parseFloat(df.format((Nice / taskRecordNum) * 100));

                float fialPayPersent = 100 - PassPersent - GoodPersent - NicePersent;
                float PassPayPersent = 100 - fialPersent - GoodPersent - NicePersent;
                float GoodPayPersent = 100 - fialPersent - PassPersent - NicePersent;
                float NicePayPersent = 100 - fialPersent - PassPersent - GoodPersent;
                arry[0][0] = "低于60分：" + fial + "人";
                arry[0][1] = Float.parseFloat(df.format(fialPayPersent));

                arry[1][0] = "高于60并小于80分：" + Pass + "人";
                arry[1][1] = Float.parseFloat(df.format(PassPayPersent));

                arry[2][0] = "高于80并小于90分：" + Good + "人";
                arry[2][1] = Float.parseFloat(df.format(GoodPayPersent));

                arry[3][0] = "高于90分：" + Nice + "人";
                arry[3][1] = Float.parseFloat(df.format(NicePayPersent));

                TaskTecord = gson.toJson(arry).toString();
            }

            modelAndView.addObject("TaskTecord", TaskTecord);// 绘图数据
            modelAndView.addObject("taskRecordGroudUser", arrangeRecordGroudUser);
            //modelAndView.addObject("number", number);
            modelAndView.addObject("score", Max);
        } catch (Exception e) {
            // TODO: handle exception
        }

        return modelAndView;
    }


    /**
     * 第三方登录跳转绑定手机页面
     *
     * @param request
     * @return
     */
    @RequestMapping("/user/toBinding")
    public ModelAndView toJumpUser(HttpServletRequest request, @ModelAttribute UserForm userForm, HttpServletResponse response) {
        ModelAndView model = new ModelAndView();
        try {
            model.setViewName(jump_user);
        } catch (Exception e) {
            logger.error("UserController.toBinding", e);
        }
        return model;
    }

    /**
     * 绑定用户
     *
     * @return
     */
    @RequestMapping("/binding")
    @ResponseBody
    public Map<String, Object> doBinding(@ModelAttribute UserForm userForm, HttpServletRequest request,
                                         HttpServletResponse response) {
        Map<String, Object> json = null;
        try {
            //1.获取传递过来的appType，key，昵称，头像,帐号，密码

            //2.通过帐号获取用户信息
            // 验证输入数据合法性
            if (PreventInfusion.sql_inj(userForm.getPassword())) {
                json = this.getJsonMap(false, "inputIllegal", null);
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
            Pattern emailRes = Pattern
                    .compile("^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$");
            mat = emailRes.matcher(userForm.getEmail());
            boolean emailF = mat.matches();
            if (emailF) {
                list = userService.getUserListForLogin(user);
            }
            // 通过数据库，验证用户是否存在
            if (ObjectUtils.isNull(list)) {//2.1不存在,直接返回您绑定的用户不存在
                json = this.getJsonMap(false, "formDataNot", "填写数据错误");
                return json;
            } else {
                user = list.get(0);
                // 验证密码是否正确
                if (checkIsRight(user.getPassword(), userForm.getPassword(), user.getCustomerkey())) {
                    UserProfile userProfile = new UserProfile();
                    userProfile.setProfiletype(userForm.getProfileType());
                    userProfile.setUserid(user.getId());
                    List<UserProfile> userProfileList = userProfileService.getUserProfileList(userProfile);
                    //2.2存在，判断密码是否正确，添加第三方登录拓展表，判断头像昵称是否存在，如不存在修改
                    if (ObjectUtils.isNull(userProfileList)) {
                        //添加第三方登录信息
                        userProfile.setName(userForm.getNickName());
                        userProfile.setValue(userForm.getValue());
                        userProfile.setProfiledate(new Date());
                        userProfileService.addUserProfile(userProfile);
                        UserExpandDto _user = userExpandService.getUserExpandByUid(user.getId());
                        if (ObjectUtils.isNotNull(_user) && (StringUtils.isEmpty(_user.getAvatar()) || "".equals(_user.getAvatar()))) {
                            UserExpand userExpand = new UserExpand();
                            userExpand.setCusId(user.getId());
                            userExpand.setAvatar(userForm.getPhoto());
                            userExpandService.updateAvatarById(userExpand);
                        }
                        // 执行登录操作
                        userService.setLoginStatus(user, "", request, response);
                        json = this.getJsonMap(true, "success", "绑定成功");
                    } else {//该账户已被绑定
                        json = this.getJsonMap(false, "false", "该账户已被绑定");
                    }
                } else {
                    json = this.getJsonMap(false, "false", "密码错误");
                }
            }
            return json;
        } catch (Exception e) {
            logger.error("Usercontroller.doBinding", e);
            json = this.getJsonMap(false, "error", "系统异常");
            return json;
        }
    }

    /**
     * 第三方登录跳转绑定手机页面
     *
     * @param request
     * @return
     */
    @RequestMapping("/user/toRegisterUser")
    public ModelAndView toRegisterUser(HttpServletRequest request, @ModelAttribute UserForm userForm, HttpServletResponse response) {
        ModelAndView model = new ModelAndView();
        try {
            model.setViewName(register_user);
        } catch (Exception e) {
            logger.error("UserController.toRegisterUser", e);
        }
        return model;
    }

    /**
     * 第三方登录注册用户
     *
     * @param userForm
     * @param model
     * @param request
     * @param response
     * @return
     */
    @SuppressWarnings("unchecked")
    @RequestMapping("/registerBinding")
    @ResponseBody
    public Map<String, Object> doregisterBinding(@ModelAttribute UserForm userForm, ModelMap model, HttpServletRequest request,
                                                 HttpServletResponse response) {
        Map<String, Object> json = null;
        try {

            if (ObjectUtils.isNull(userForm)) {// 验证表单数据是否为空
                json = this.getJsonMap(false, "formDataIsNot", null);
                return json;
            }
            String regType = request.getParameter("regType");
            String randomCode = request.getParameter("randomCode");
            Object regCode = null;
            if ("mobile".equals(regType)) {
                // 验证手机是否为空
                if (ObjectUtils.isNull(userForm.getMobile()) || StringUtils.isEmpty(userForm.getMobile())) {
                    json = this.getJsonMap(false, "mobileIsNull", null);
                    return json;
                }
                // 在手机不为空的情况下，验证手机格式是否正确
                if (ObjectUtils.isNotNull(userForm.getMobile()) && StringUtils.isNotEmpty(userForm.getMobile()) && !userForm
                        .getMobile().matches("^(13[0-9]|15[012356789]|18[0-9]|14[57]|17[012356789])[0-9]{8}$")) {
                    json = this.getJsonMap(false, "false", null);
                    return json;
                }
                regCode = cacheKit.get(MemConstans.REGISTER_PHONE_CODE + userForm.getMobile());
            } else if ("email".equals(regType)) {
                randomCode = request.getParameter("randomCodeEmail");
                // 验证邮箱是否为空
                if (ObjectUtils.isNull(userForm.getEmail()) || StringUtils.isEmpty(userForm.getEmail())) {
                    json = this.getJsonMap(false, "emailIsNot", null);
                    return json;
                }
                // 在邮箱不为空的情况下，验证邮箱格式是否正确
                if (ObjectUtils.isNotNull(userForm.getEmail()) && StringUtils.isNotEmpty(userForm.getEmail())) {
                    Pattern emailRes = Pattern.compile("^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$");
                    Matcher mat = emailRes.matcher(userForm.getEmail().toLowerCase());
                    boolean emailF = mat.matches();
                    if (!emailF) {
                        json = this.getJsonMap(false, "emailFormatError", null);
                        return json;
                    }
                }
                regCode = cacheKit.get(MemConstans.REGISTER_EMAIL_CODE + userForm.getEmail());
            }
            // 验证码是否为空
            if (StringUtils.isEmpty(randomCode)
                    || ObjectUtils.isNull(regCode)
                    || !randomCode.equals(regCode.toString())) {
                json = this.getJsonMap(false, "验证码错误", null);
                return json;
            }

            // 验证密码是否为空
            if (ObjectUtils.isNull(userForm.getPassword()) || StringUtils.isEmpty(userForm.getPassword())) {
                json = this.getJsonMap(false, "pwdIsNull", null);
                return json;
            }
            // 验证密码一致
            if (!userForm.getPassword().equals(userForm.getConfirmPassword())) {
                json = this.getJsonMap(false, "pwdNotEqual", null);
                return json;
            }

            // 验证输入数据合法性
            if (PreventInfusion.sql_inj(userForm.getPassword())) {
                json = this.getJsonMap(false, "regDangerWord", null);
                return json;
            }
            User user = new User();
            if ("mobile".equals(regType)) {
                user.setMobile(userForm.getMobile());
                int ismobile = userService.getUserByMobile(user);
                // 验证手机唯一
                if (ismobile != 0) {
                    json = this.getJsonMap(false, "regMobileExist", null);
                    return json;
                }
            } else if ("email".equals(regType)) {
                user.setEmail(userForm.getEmail().toLowerCase());// 邮箱存储时转为小写
                List<User> list = userService.getUserList(user);
                // 验证邮箱唯一,邮箱是否已经注册
                if (ObjectUtils.isNotNull(list)) {
                    json = this.getJsonMap(false, "regEmailExist", null);
                    return json;
                }
            }
            // 账号注册
            String userIp = WebUtils.getIpAddr(request);
            user.setPassword(userForm.getPassword());
            user.setUserip(userIp);
            String mobileFrom = request.getParameter("mobileFrom");
            if (ObjectUtils.isNotNull(mobileFrom) && "mobile".equals(mobileFrom)) {
                user.setRegisterFrom(UserExpandFrom.mobileFrom.toString());//微站注册来源
            } else {
                user.setRegisterFrom(UserExpandFrom.registerFrom.toString());// 账号来源是通过注册生成的
            }
            user.setNickname(userForm.getNickName());
            userService.addUser(user);
            UserExpand userExpand = userExpandService.getUserExpandByUserId(user.getId());
            if (ObjectUtils.isNotNull(userExpand)) {
                userExpand.setCusId(user.getId());
                userExpand.setAvatar(userForm.getPhoto());
                userExpandService.updateAvatarById(userExpand);
            }
            //创建第三方登录信息
            UserProfile userProfile = new UserProfile();
            userProfile.setUserid(user.getId());
            userProfile.setValue(userForm.getValue());
            userProfile.setProfiletype(userForm.getProfileType());
            userProfile.setProfiledate(new Date());
            userProfile.setName(userForm.getNickName());
            userProfileService.addUserProfile(userProfile);
            Long upUserId = this.getUpLoginId(request);
            // 返还上线用户积分
            if (upUserId != 0L) {
                userIntegralService.addUserIntegral(IntegralKeyword.rebate.toString(), upUserId, 0L, user.getId(), "");
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
            cacheKit.remove(MemConstans.REGISTER_PHONE_CODE + userForm.getMobile());
            cacheKit.remove(MemConstans.REGISTER_EMAIL_CODE + userForm.getEmail());
            json = this.getJsonMap(true, "", null);
        } catch (Exception e) {
            logger.error("doregisterBinding error", e);
            json = this.getJsonMap(false, "系统错误", null);
        }
        return json;
    }

    /**
     * 个人中心
     *
     * @param request
     * @return
     */
    @RequestMapping("/mobile/weixinlogin")
    public String ucenter(HttpServletRequest request, HttpServletResponse response) {
        String mobileUrl = "/uc/home";
        try {
            String agent = request.getHeader("User-Agent");
            //logger.info("agent:",agent);
            if (agent.toLowerCase().indexOf("micromessenger") > -1) {//微信内置浏览器
                String openId = WebUtils.getCookie(request, CommonConstants.USER_SINGEL_OPENID);
                String access_token = (String) cacheKit.get(CommonConstants.USER_SINGEL_ACCESS_TOKEN + openId);
                Gson gson = new Gson();
                if (StringUtils.isEmpty(openId) || StringUtils.isEmpty(access_token)) {//cookie中没有openId
                    //code作为换取access_token的票据，每次用户授权带上的code将不一样，code只能使用一次，5分钟未被使用自动过期。
                    String code = request.getParameter("code");
                    logger.info("+++code:" + code);
                    Map<String, Object> weixinMap = websiteProfileService.getWebsiteProfileByType("weixin");
                    Map<String, Object> weixinMapVal = (Map<String, Object>) weixinMap.get("weixin");
                    String APPID = (String) weixinMapVal.get("wxAppID");
                    String APPSECRET = (String) weixinMapVal.get("wxAppSecret");
                    if (code != null) {//微信获取openId所需code
                        //code换取openid
                        //access_token=(String) weixinMapVal.get("wxToken");
                        String urlStr = "https://api.weixin.qq.com/sns/oauth2/access_token";
                        String param = "appid=" + APPID + "&secret=" + APPSECRET + "&code=" + code + "&grant_type=authorization_code";
                        String resMsg = HttpUtil.doGet(urlStr, param);
                        logger.info("param:" + param);
                        Map<String, String> ps = gson.fromJson(resMsg, new TypeToken<Map<String, String>>() {
                        }.getType());
                        logger.info("returnMap:" + ps);
                        openId = ps.get("openid");
                        access_token = ps.get("access_token");
                        logger.info("openId:" + openId);
                        if (openId == null) {
                            return "redirect:/";
                        } else {
                            //openId存入cookie 2天
                            WebUtils.setCookie(response, CommonConstants.USER_SINGEL_OPENID, openId, 2);
                            cacheKit.set(CommonConstants.USER_SINGEL_ACCESS_TOKEN + openId, access_token, 300);//5分钟
                        }
                    } else {
                        //去请求token
                        String weixin_url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + APPID
                                + "&redirect_uri="
                                + URLEncoder.encode(CommonConstants.contextPath + "/mobile/weixinlogin", "UTF-8")
                                + "&response_type=code&scope=snsapi_userinfo#wechat_redirect";
                        //response.sendRedirect(weixin_url);
                        return "redirect:" + weixin_url;
                    }
                }
                logger.info("=======================access_token:" + access_token);
                UserProfile userProfile = new UserProfile();
                userProfile.setValue(openId);
                userProfile.setProfiletype(ProfileType.WEIXIN.toString());
                List<UserProfile> list = userProfileService.getUserProfileList(userProfile);
                if (ObjectUtils.isNotNull(list)) {//已经绑定过存在的帐号
                    userProfile = list.get(0);
                    // 登录操作
                    Long cusId = userProfile.getUserid();
                    logger.info("+++ already weixin login cusId:" + cusId + ",openId:" + openId);
                    //执行登陆操作
                    User user = userService.getUserById(Long.valueOf(cusId));
                    userService.setLoginStatus(user, "true", request, response);

                } else {//未绑定过帐号
                    String user_info_url = "https://api.weixin.qq.com/sns/userinfo";
                    String infoParam = "access_token=" + access_token + "&openid=" + openId + "&lang=zh_CN";
                    String infoResMsg = HttpUtil.doGet(user_info_url, infoParam);
                    Map<String, Object> infops = gson.fromJson(infoResMsg, new TypeToken<Map<String, Object>>() {
                    }.getType());
                    String headimgurl = (String) infops.get("headimgurl");
                    String nickname = (String) infops.get("nickname");
                    cacheKit.set("nickname_" + openId, nickname, 86400);
                    logger.info("=======================" + nickname);
                    return "redirect:/user/toBinding?nickName=" + nickname + "&profileType=" + ProfileType.WEIXIN.toString() + "&value=" + openId + "&photo=" + headimgurl;
                }
            }
        } catch (Exception e) {
            logger.error("UserController.ucenter", e);
            return setExceptionRequest(request, e);
        }
        return "redirect:" + mobileUrl;
    }

    /**
     * 获取用户信息
     *
     * @param id
     * @return
     */
    @RequestMapping("/ajax/getUserName")
    @ResponseBody
    public Map<String, Object> getUserInfo(@RequestParam("id") Long id) {
        Map<String, Object> json = null;
        try {
            if (ObjectUtils.isNotNull(id)) {
                User user = userService.getUserById(id);
                if (ObjectUtils.isNotNull(user)) {
                    String userName = StringUtils.isNotEmpty(user.getNickname()) ? user.getNickname() : (StringUtils.isNotEmpty(user.getEmail()) ? user.getEmail() : user.getMobile());
                    json = this.getJsonMap(true, "success", userName);
                } else {
                    json = this.getJsonMap(false, "empty", null);
                }
            }
        } catch (Exception e) {
            logger.error("UserController.getUserName", e);
            json = this.getJsonMap(false, "error", null);
        }
        return json;
    }

    /**
     * 发送手机校验码
     *
     * @param request
     * @return
     */
    @RequestMapping("/getCheckCode")
    @ResponseBody
    public Map<String, Object> getCheckCode(@RequestParam("mobile") String mobile, HttpServletRequest request) {
        Map<String, Object> json = null;
        try {
            // 注册数量
            int regiterNum = cacheKit.get(MemConstans.REGISTER_SEND_MOBILE_CODE_NUM + mobile) == null ? 0 // regiterNum代表的是发送校验码的次数
                    : Integer.parseInt(cacheKit.get(MemConstans.REGISTER_SEND_MOBILE_CODE_NUM + mobile).toString());
            if (regiterNum < 10) {
                if (ObjectUtils.isNotNull(mobile)) {
                    Random random = new Random();
                    String verificationCode = StringUtils.getRandStr(6);

                    String content = "";
                    // 注册时发送的短信信息
                    content = "您的验证码是" + verificationCode + "，有效期5分钟，请注意保管。";
                    // 短信发送
//					SmsServiceStub serviceStub  = new SmsServiceStub();
//					serviceStub.setDestNumber(mobile);
//					serviceStub.setMsgContent(content);
//					SmsThread smsThread = new SmsThread(serviceStub);
//					smsThread.start();

                    cacheKit.set(MemConstans.REGISTER_PHONE_CODE + mobile, verificationCode,
                            MemConstans.REGISTER_PHONE_CODE_TIME);
                    regiterNum = regiterNum + 1;
                    cacheKit.set(MemConstans.REGISTER_SEND_MOBILE_CODE_NUM + mobile, regiterNum, MemConstans.REGISTER_PHONE_CODE_TIME);
                    json = this.getJsonMap(true, "success", content);
                } else {
                    json = this.getJsonMap(false, "请输入手机号码", null);
                }
            } else {
                json = this.getJsonMap(false, "获取校验码次数已达上限，请24小时后再试", null);
            }
        } catch (Exception e) {
            logger.error("getCheckCode", e);
            json = this.getJsonMap(false, "系统异常", null);
        }
        return json;
    }


    /**
     * 发送邮箱验证码
     *
     * @param request
     * @return
     */
    @RequestMapping("/sendEmailCode")
    @ResponseBody
    public Map<String, Object> sendEamilCode(HttpServletRequest request) {
        Map<String, Object> json = null;
        try {
            // 获得注册开关是否开启
            Map<String, Object> map = getLoginAndRegKeyword();
            if (ObjectUtils.isNull(map) || map.get("verifyRegister").toString().equalsIgnoreCase("OFF")) {
                json = this.getJsonMap(false, "网站暂不允许注册", null);
                return json;
            }
            String email = request.getParameter("email");
            Pattern emailRes = Pattern.compile("^([a-zA-Z0-9_.])+@([a-zA-Z0-9_])+(.[a-zA-Z0-9_-])+");
            Matcher mat = emailRes.matcher(email);
            if (!mat.matches()) {
                json = getJsonMap(false, "请输入正确的邮箱号", null);
                return json;
            }
            User user = new User();
            user.setEmail(email);// 邮箱存储时转为小写
/*			List<User> list = userService.getUserList(user);
			// 验证邮箱唯一,邮箱是否已经注册
			if (ObjectUtils.isNotNull(list)) {
				json = getJsonMap(false, "邮箱已存在", null);
				return json;
			}*/
            //生成验证码
            String code = StringUtils.getRandStr(6);
            logger.info("绑定邮箱验证码：" + code);

            cacheKit.set(MemConstans.REGISTER_EMAIL_CODE + email, code, MemConstans.REGISTER_EMAIL_CODE_TIME);

            String content = "您的验证码是：" + code + ",有效期5分钟,如非本人操作,请忽略本信息。";
            //发送邮件
            Map<String, Object> websitemap = websiteProfileService.getWebsiteProfileByType(WebSiteProfileType.web.toString());
            Map<String, Object> web = (Map<String, Object>) websitemap.get("web");
            String company = web.get("company").toString();
            emailService.sendMail(user.getEmail(), content, "验证码" + "[" + company + "]");
            json = getJsonMap(true, "验证码已发送至邮箱,请注意查收", null);
        } catch (Exception e) {
            json = getJsonMap(false, "系统繁忙，请稍后再操作", null);
            logger.error("sendEamilCode()--error", e);
        }
        return json;
    }


    @RequestMapping("/checkRandomCode")
    @ResponseBody
    public Map<String, Object> checkRandomCode(HttpServletRequest request) {
        Map<String, Object> json = null;
        try {
            String mobile = request.getParameter("mobile");
            String email = request.getParameter("email");
            String randomCode = request.getParameter("randomCode");
            Object checkCode = null;
            if (StringUtils.isNotEmpty(mobile)) {
                checkCode = cacheKit.get(MemConstans.REGISTER_PHONE_CODE + mobile);
            } else if (StringUtils.isNotEmpty(email)) {
                checkCode = cacheKit.get(MemConstans.REGISTER_EMAIL_CODE + email);
            }
            // 验证
            if (ObjectUtils.isNotNull(checkCode) && StringUtils.isNotEmpty(randomCode) && checkCode.toString().equals(randomCode)) {
                json = getJsonMap(true, "success", null);
                cacheKit.remove(MemConstans.REGISTER_PHONE_CODE + mobile);
                cacheKit.remove(MemConstans.REGISTER_EMAIL_CODE + email);
            } else {
                json = getJsonMap(false, "验证码错误", null);
            }
        } catch (Exception e) {
            logger.error("UserController.checkRandomCode", e);
            json = getJsonMap(false, "系统异常,请稍后重试", null);
        }
        return json;
    }

    @RequestMapping("/updatePassword")
    @ResponseBody
    public Map<String, Object> updatePassword(
            @RequestParam(value = "mobile", required = false) String mobile,
            @RequestParam(value = "email", required = false) String email,
            @RequestParam("password") String password,
            @RequestParam("confirmPassword") String confirmPassword) {
        Map<String, Object> json = null;
        try {
            if (StringUtils.isNotEmpty(password) && StringUtils.isNotEmpty(confirmPassword) && password.equals(confirmPassword)) {
                User user = null;
                if (StringUtils.isNotEmpty(mobile)) {
                    user = userService.getUserByLoginMobile(mobile);
                } else if (StringUtils.isNotEmpty(email)) {
                    user = userService.getUserByLoginEmail(mobile);
                }
                if (ObjectUtils.isNotNull(user)) {
                    user.setPassword(password);
                    userService.updatePwdById(user);
                    json = getJsonMap(true, "success", null);
                } else {
                    json = getJsonMap(false, "账户不存在", null);
                }
            } else {
                json = getJsonMap(false, "两次输入密码不相同", null);
            }
        } catch (Exception e) {
            logger.error("UserController.updatePassword");
            json = getJsonMap(false, "系统异常,请稍后重试", null);
        }
        return json;
    }

    @RequestMapping("/updateMobileOrEmail")
    @ResponseBody
    public Map<String, Object> updateMobileOrEmail(HttpServletRequest request) {
        Map<String, Object> json = null;
        try {
            Long userId = getLoginUserId(request);
            if (ObjectUtils.isNull(userId)) {
                json = getJsonMap(false, "用户未登录", null);
                return json;
            }
            User user = new User();
            user.setId(userId);
            String mobile = request.getParameter("mobile");
            String email = request.getParameter("email");
            String random = request.getParameter("random");
            Object _random = null;
            if (StringUtils.isNotEmpty(mobile) && !"".equals(mobile)) {
                user.setMobile(mobile);
                _random = cacheKit.get(MemConstans.REGISTER_PHONE_CODE + mobile);
            } else {
                user.setEmail(email);
                user.setUpdateEmail("YES");
                _random = cacheKit.get(MemConstans.REGISTER_EMAIL_CODE + email);
            }
            if (StringUtils.isNotEmpty(random) && ObjectUtils.isNotNull(_random) && random.equals(_random.toString())) {
                userService.updateUser(user);
                json = getJsonMap(true, "", null);
                cacheKit.remove(MemConstans.REGISTER_PHONE_CODE + mobile);
                cacheKit.remove(MemConstans.REGISTER_EMAIL_CODE + email);
            } else {
                json = getJsonMap(false, "验证码错误", null);
            }
        } catch (Exception e) {
            logger.error("UserController.updateMobileOrEmail");
            json = getJsonMap(false, "系统繁忙,请稍后重试", null);
        }
        return json;
    }
}
