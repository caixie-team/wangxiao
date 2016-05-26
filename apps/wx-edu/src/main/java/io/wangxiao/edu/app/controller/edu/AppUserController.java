package io.wangxiao.edu.app.controller.edu;

import io.wangxiao.commons.controller.RandomCodeController;
import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.commons.service.cache.CacheKit;
import io.wangxiao.commons.service.gain.GuidGeneratorService;
import io.wangxiao.commons.util.ObjectUtils;
import io.wangxiao.commons.util.PreventInfusion;
import io.wangxiao.commons.util.Security.PurseSecurityUtils;
import io.wangxiao.commons.util.StringUtils;
import io.wangxiao.commons.util.web.WebUtils;
import io.wangxiao.edu.app.common.AppBaseController;
import io.wangxiao.edu.common.constants.MemConstans;
import io.wangxiao.edu.home.constants.enums.IntegralKeyword;
import io.wangxiao.edu.home.constants.enums.SellType;
import io.wangxiao.edu.home.constants.enums.UserExpandFrom;
import io.wangxiao.edu.home.entity.course.CourseDto;
import io.wangxiao.edu.home.entity.course.CourseStudyhistory;
import io.wangxiao.edu.home.entity.course.QueryCourse;
import io.wangxiao.edu.home.entity.user.*;
import io.wangxiao.edu.home.service.course.CourseService;
import io.wangxiao.edu.home.service.course.CourseStudyhistoryService;
import io.wangxiao.edu.home.service.user.*;
import lombok.Getter;
import lombok.Setter;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
@RequestMapping("/app")
public class AppUserController extends AppBaseController {
    private static Logger logger = Logger.getLogger(AppUserController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private UserLoginLogService userLoginLogService;
    @Autowired
    private UserIntegralService userIntegralService;
    @Setter
    @Getter
    private QueryCourse queryCourse;
    @Autowired
    GuidGeneratorService guidGeneratorService;
    CacheKit cacheKit = CacheKit.getInstance();
    @Autowired
    private UserExpandService userExpandService;
    @Autowired
    private UserFeedbackService userFeedbackService;
    @Autowired
    private UserAccountService userAccountService;
    @Autowired
    private UserAccounthistoryService userAccounthistoryService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private CourseStudyhistoryService courseStudyhistoryService;

    // 绑定变量名字和属性，参数封装进类
    @InitBinder("userFeedBack")
    public void initBinderQueryCourse(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("userFeedBack.");
    }

    // 绑定变量名字和属性，参数封装进类
    @InitBinder("queryUser")
    public void initBinderUserExpandDto(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("queryUser.");
    }

    /***
     * app注册接口
     *
     * @param request
     * @return
     */
    @RequestMapping("/register")
    @ResponseBody
    public Map<String, Object> appRegister(HttpServletRequest request) {
        Map<String, Object> json = null;
        try {
            String mobile = request.getParameter("mobile");// 手机号
            String email = request.getParameter("email");// 邮箱
            String userPassword = request.getParameter("userPassword");// 密码
            String confirmPwd = request.getParameter("confirmPwd");// 确认密码

            if (userPassword == null || userPassword.trim().length() == 0) {
                json = this.getJsonMap(false, "请输入密码", null);
                return json;
            }
            if (!userPassword.equals(confirmPwd)) {
                json = this.getJsonMap(false, "两次密码不一致", null);
                return json;
            }
            if (PreventInfusion.sql_inj(userPassword)) {
                json = this.getJsonMap(false, "不能输入非法数据", null);
                return json;
            }
            // 验证邮箱是否为空
            if (ObjectUtils.isNull(email) || StringUtils.isEmpty(email)) {
                json = this.getJsonMap(false, "邮箱不正确", null);
                return json;
            }
            // 在邮箱不为空的情况下，验证邮箱格式是否正确
            if (ObjectUtils.isNotNull(email) && StringUtils.isNotEmpty(email)) {
                Pattern emailRes = Pattern.compile("^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$");
                Matcher mat = emailRes.matcher(email.toLowerCase());
                boolean emailF = mat.matches();
                if (!emailF) {
                    json = this.getJsonMap(false, "邮箱不正确", null);
                    return json;
                }
            }
            // 验证手机是否为空
            if (ObjectUtils.isNull(mobile) || StringUtils.isEmpty(mobile)) {
                json = this.getJsonMap(false, "手机号不正确", null);
                return json;
            }
            // 在手机不为空的情况下，验证手机格式是否正确
            if (ObjectUtils.isNotNull(mobile) && StringUtils.isNotEmpty(mobile) && !mobile.matches("^(13[0-9]|15[012356789]|18[0-9]|14[57]|17[012356789])[0-9]{8}$")) {
                json = this.getJsonMap(false, "手机号不正确", null);
                return json;
            }
            User user = new User();
            user.setEmail(email.toLowerCase());// 邮箱存储时转为小写
            user.setMobile(mobile);
            List<User> list = userService.getUserList(user);
            // 验证邮箱唯一,邮箱是否已经注册
            if (ObjectUtils.isNotNull(list)) {
                json = this.getJsonMap(false, "邮箱已存在", null);
                return json;
            }
            int ismobile = userService.getUserByMobile(user);
            // 验证手机唯一
            if (ismobile != 0) {
                json = this.getJsonMap(false, "手机号已存在", null);
                return json;
            }
            request.getSession().removeAttribute(RandomCodeController.RAND_CODE);
            // 邮箱注册
            String userIp = WebUtils.getIpAddr(request);
            user.setMobile(mobile);
            user.setPassword(userPassword);
            user.setUserip(userIp);
            user.setRegisterFrom(UserExpandFrom.registerFrom.toString());// 账号来源是通过注册生成的
            userService.addUser(user);
            // 注册送积分
            userIntegralService.addUserIntegral(IntegralKeyword.register.toString(), user.getId(), 0L, 0L, "");

            json = this.getJsonMap(true, "注册成功", user);
        } catch (Exception e) {
            json = this.getJsonMap(false, "系统繁忙", null);
            this.setExceptionRequest(request, e);
            logger.error("appRegister()---error", e);
        }
        return json;
    }

    /***
     * app登录接口 ，接收两个参数 account帐号 userPassword密码，该接口支持邮箱和手机号登录
     *
     * @param request
     * @return
     */
    @RequestMapping("/login")
    @ResponseBody
    public Map<String, Object> appLogin(HttpServletRequest request) {
        Map<String, Object> json = null;
        try {
            String account = request.getParameter("account");
            String userPassword = request.getParameter("userPassword");
            if (PreventInfusion.sql_inj(account) || PreventInfusion.sql_inj(account)) {
                json = this.getJsonMap(false, "不能输入非法字符", null);
                return json;
            }
            if (account == null || account.trim().length() == 0) {
                json = this.getJsonMap(false, "请输入用户帐号", null);
                return json;
            }
            if (userPassword == null || userPassword.trim().length() == 0) {
                json = this.getJsonMap(false, "请输入密码", null);
                return json;
            }

            User user = new User();
            user.setEmail(account.toLowerCase());
            List<User> list = null;
            // 如果是手机格式则按手机查询
            String regEx = "^1{1}[0-9]{10}$"; // 表示a或F
            Pattern pat = Pattern.compile(regEx);
            Matcher mat = pat.matcher(account);
            boolean rs = mat.find();
            if (rs) {
                list = userService.getUserListForTelLogin(user);
            }

            // 如果是邮箱格式
            Pattern emailRes = Pattern.compile("^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$");
            mat = emailRes.matcher(account);
            boolean emailF = mat.matches();
            if (emailF) {
                list = userService.getUserListForLogin(user);
            }
            if (list == null || list.size() == 0) {
                json = this.getJsonMap(false, "用户帐号不存在", null);
                return json;
            }
            user = list.get(0);
            String password = PurseSecurityUtils.secrect(userPassword, user.getCustomerkey());
            if (!password.equals(user.getPassword())) {
                json = this.getJsonMap(false, "用户密码不正确", null);
                return json;
            }
            UserExpandDto userExpandDto = userService.queryUserExpand(user.getId());
            Map<String, Object> userMap = new HashMap<String, Object>();
            userMap.put("id", userExpandDto.getId());
            userMap.put("nickname", userExpandDto.getNickname());
            userMap.put("avatar", userExpandDto.getAvatar());
            json = this.getJsonMap(true, "登录成功", userMap);
            // 添加登录记录
            UserLoginLog userLoginLog = new UserLoginLog();
            userLoginLog.setLoginIp(WebUtils.getIpAddr(request));
            userLoginLog.setUserId(user.getId());
            userLoginLog.setLoginTime(new Date());
            userLoginLogService.addUserLoginLog(userLoginLog);
            // 登陆赠送积分
            userIntegralService.addUserIntegral(IntegralKeyword.login.toString(), user.getId(), 0L, 0L, "");
            json = this.getJsonMap(true, "登录成功", user);
        } catch (Exception e) {
            json = this.getJsonMap(false, "系统繁忙", null);
            this.setExceptionRequest(request, e);
            logger.error("applogin()---error", e);
        }
        return json;
    }

    /**
     * 个人信息
     */
    @RequestMapping("/user/info")
    @ResponseBody
    public Map<String, Object> userInfo(@RequestParam("userId") Long userId, HttpServletRequest request) {
        Map<String, Object> json = null;
        try {
            // 个人信息
            UserExpandDto userExpandDto = userExpandService.getUserExpandByUids(userId);
            // 我购买的课程数
            List<CourseDto> courseDtos = courseService.getUserBuyCourseList(getLoginUserId(request), SellType.COURSE.toString());
            // 我的播放记录数
            PageEntity page = new PageEntity();
            page.setPageSize(10);
            CourseStudyhistory courseStudyhistory = new CourseStudyhistory();
            courseStudyhistory.setUserId(userId);
            // 查询用户学习记录
            courseStudyhistoryService.getCourseStudyhistoryListByCondition(courseStudyhistory, page);
            // 我的余额
            UserAccount userAccount = userAccountService.getUserAccountByUserId(userId);
            Map<String, Object> dataMap = new HashMap<String, Object>();
            // 封装数据并返回
            dataMap.put("userExpandDto", userExpandDto);
            dataMap.put("courseNum", courseDtos.size());
            dataMap.put("courseStudyNum", page.getTotalResultSize());
            dataMap.put("balance", userAccount.getBalance());
            json = this.getJsonMap(true, "查询成功", dataMap);
        } catch (Exception e) {
            logger.error("userInfo----error", e);
            json = this.getJsonMap(false, "系统繁忙，请稍后再试", null);
        }
        return json;
    }

    /**
     * 个人头像修改
     */
    @RequestMapping("/user/avatar")
    @ResponseBody
    public Map<String, Object> userUpdateAvatar(@RequestParam("userId") Long userId, @RequestParam("avatar") String avatar) {
        Map<String, Object> json = null;
        try {
            UserExpand userExpand = new UserExpand();
            userExpand.setAvatar(avatar);
            userExpand.setCusId(userId);
            userExpandService.updateUserExpand(userExpand);
            cacheKit.remove(MemConstans.USEREXPAND_INFO + userId);
            json = this.getJsonMap(true, "修改成功", null);
        } catch (Exception e) {
            logger.error("userUpdateAvatar----error", e);
            json = this.getJsonMap(false, "系统繁忙，请稍后再试", null);
        }
        return json;
    }

    /***
     * app反馈接口
     *
     * @param request
     * @return
     */
    @RequestMapping("/feedback/add")
    @ResponseBody
    public Map<String, Object> appFreeBack(HttpServletRequest request, UserFeedback userFeedback) {
        Map<String, Object> json = null;
        try {
            // 联系方式 手机/电话/邮箱
            String contact = request.getParameter("contact");
            if (contact != null && !contact.equals("")) {
                // 如果是手机格式
                String regEx = "^1{1}[0-9]{10}$"; // 表示a或F
                Pattern pat = Pattern.compile(regEx);
                Matcher mat = pat.matcher(contact);
                boolean rs = mat.find();
                if (rs) {
                    userFeedback.setMobile(contact);
                    userFeedback.setQq("");
                    userFeedback.setEmail("");
                } else {
                    // 如果是邮箱格式
                    Pattern emailRes = Pattern.compile("^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$");
                    mat = emailRes.matcher(contact);
                    boolean emailF = mat.matches();
                    if (emailF) {
                        userFeedback.setEmail(contact);
                        userFeedback.setMobile("");
                        userFeedback.setQq("");
                    } else {
                        userFeedback.setQq(contact);
                        userFeedback.setMobile("");
                        userFeedback.setEmail("");
                    }
                }
            }
            userFeedback.setName("");
            userFeedbackService.addUserFeedback(userFeedback);
            json = this.getJsonMap(true, "true", null);
        } catch (Exception e) {
            json = this.getJsonMap(false, "系统繁忙", null);
            this.setExceptionRequest(request, e);
            logger.error("appFreeBack()---error", e);
        }
        return json;
    }

    /**
     * app查询个人账户信息
     *
     * @param page
     * @param userId
     * @return
     */
    @RequestMapping("/user/acc")
    @ResponseBody
    public Map<String, Object> perssonAccout(@ModelAttribute("page") PageEntity page, @RequestParam("userId") Long userId) {
        QueryUserAccounthistory queryUserAccounthistory = new QueryUserAccounthistory();
        Map<String, Object> json = null;
        try {

            page.setPageSize(6);
            queryUserAccounthistory.setUserId(userId);
            // 查询个人账户基本信息
            UserAccount userAccount = userAccountService.getUserAccountByUserId(userId);
            // 查询个人账户历史记录
            List<UserAccounthistory> accList = userAccounthistoryService.getWebUserAccountHistroyListByCondition(queryUserAccounthistory, page);
            Map<String, Object> dataMap = new HashMap<String, Object>();
            // 封装数据并返回
            dataMap.put("userAccount", userAccount);
            dataMap.put("accList", accList);
            dataMap.put("page", page);
            json = this.getJsonMap(true, "查询成功", dataMap);
        } catch (Exception e) {
            json = this.getJsonMap(false, "系统繁忙", null);
            logger.error("perssonAccout()---error", e);
        }
        return json;
    }

    /**
     * 修改密码
     */
    @RequestMapping("/user/update/pwd")
    @ResponseBody
    public Map<String, Object> updatePwd(@RequestParam("userId") Long userId, @RequestParam("newpwd") String newpwd, @RequestParam("oldpwd") String oldpwd) {
        Map<String, Object> json = null;
        try {
            User user = userService.getUserById(userId);
            if (checkIsRight(user.getPassword(), oldpwd, user.getCustomerkey())) {
                user.setPassword(newpwd);
                userService.updatePwdById(user, null);
                json = this.getJsonMap(true, "修改成功", "");
            } else {
                json = this.getJsonMap(false, "原有密码不正确", null);
            }
            return json;
        } catch (Exception e) {
            logger.error("AppUserController.updatepwd", e);
            json = this.getJsonMap(false, "", null);
            return json;
        }
    }

    public boolean checkIsRight(String dbPassword, String userPassword, String userkey) {
        String despassword = PurseSecurityUtils.secrect(userPassword, userkey);
        return despassword.equals(dbPassword);
    }

    /**
     * 个人中心--修改个人信息
     *
     * @param user
     * @return
     */
    @RequestMapping("/user/update/info")
    @ResponseBody
    public Map<String, Object> userUpdateInfo(@ModelAttribute("user") UserExpandDto user) {
        Map<String, Object> json = null;
        try {
            String falg = userService.updateAppQueryUser(user);
            if ("success".equals(falg)) {
                json = this.getJsonMap(true, falg, null);
            } else {
                json = this.getJsonMap(false, falg, null);
            }
        } catch (Exception e) {
            json = this.getJsonMap(false, "系统繁忙", null);
            logger.error("userUpdateInfo()---error", e);
        }
        return json;
    }
}
