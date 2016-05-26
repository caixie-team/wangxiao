package io.wangxiao.edu.sysuser.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import io.wangxiao.commons.controller.RandomCodeController;
import io.wangxiao.commons.service.cache.CacheKit;
import io.wangxiao.commons.util.ObjectUtils;
import io.wangxiao.commons.util.web.WebUtils;
import io.wangxiao.edu.common.constants.CommonConstants;
import io.wangxiao.edu.common.constants.MemConstans;
import io.wangxiao.edu.common.contoller.SingletonLoginUtils;
import io.wangxiao.edu.common.util.DatePwdUtil;
import io.wangxiao.edu.home.entity.user.User;
import io.wangxiao.edu.home.service.user.LoginOnlineService;
import io.wangxiao.edu.home.service.user.UserService;
import io.wangxiao.edu.sysuser.constants.SysUserConstants;
import io.wangxiao.edu.sysuser.entity.SysFunction;
import io.wangxiao.edu.sysuser.service.SysUserService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @description 系统用户登录
 */
@Controller
@RequestMapping("/admin")
public class SysLoginController extends SysBaseController {

    /**
     * 超级用户权限ID
     */
    private static final Long SUPERID = 1L;

    private static final Logger logger = LoggerFactory.getLogger(SysLoginController.class);

    public static String ERROR_LIMIT_VERIFY = "limitVerifyError";
    CacheKit cacheKit = CacheKit.getInstance();
    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private UserService userService;

    @Autowired
    private LoginOnlineService loginOnlineService;

    // 返回页面定义
    private static String LOGIN = getViewPath("/login/login");// 登录页面
    private static String LOGINSUCCESS = "redirect:/admin/sys/main";// 登录成功跳转。do
    private static String adminMain = getViewPath("/login/main");// 登录成功主页面JSP
    private static String BOTTOMFRAME = getViewPath("/login/bottomFrame");// 登录成功bottomFrame
    private static String LEFTFRAME = getViewPath("/login/leftframe");// 登录成功leftframe
    private static String RIGHTFRAME = getViewPath("/login/rightframe");// 登录成功rightframe
    private static String TOPFRAME = getViewPath("/login/topframe");// 登录成功topframe
    private static String LOGINOUT = getViewPath("/login/loginOut");// 登出页面
    private static String LOGINLOG = getViewPath("/sysuser/user_login_log");// 登陆log

    // 绑定变量名字和属性，参数封装进类
    @InitBinder("user")
    public void initBinder1(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("user.");
    }

    /**
     * 后台登陆地址
     */
    @RequestMapping("/")
    public String admin1(HttpServletRequest request) {
        Long userId = SingletonLoginUtils.getLoginUserId(request);
        if (userId > 0) {
            return "redirect:/admin/sys/main";
        }
        return LOGIN;
    }

    @RequestMapping("")
    public String admin2(HttpServletRequest request) {
        Long userId = SingletonLoginUtils.getLoginUserId(request);
        if (userId > 0) {
            return "redirect:/admin/sys/main";
        }
        return LOGIN;
    }

    /**
     * 用户登录验证
     *
     * @param user
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/sys/login")
    public ModelAndView login(@ModelAttribute("user") User user, HttpServletRequest request, HttpServletResponse response) {
        String returnPage = LOGIN;
        ModelAndView modelAndView = new ModelAndView(LOGIN);
        this.errmsg = "";
        try {
            User userFromDb = null;
            if (user != null && !StringUtils.isBlank(user.getEmail()) && !StringUtils.isBlank(user.getPassword())) {
                logger.info("+++ do user login email:" + user.getEmail());
                // 用户名长度
                if (!user.getEmail().matches("^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+")) {
                    this.errmsg = "请输入正确的邮箱地址!";
                    modelAndView.addObject("msg", errmsg);
                    modelAndView.addObject("type", "userName");
                    return modelAndView;
                }
                modelAndView.addObject("username", user.getEmail());
                // 密码长度
                if (user.getPassword().length() < 6 || user.getPassword().length() > 20) {
                    this.errmsg = "密码长度大于6，小于20!";
                    modelAndView.addObject("msg", errmsg);
                    modelAndView.addObject("type", "password");
                    return modelAndView;
                }
                // 验证码
                String randomCode = request.getParameter("randomCode");
                if (!randomCode.equals(request.getSession().getAttribute(RandomCodeController.RAND_CODE))) {
                    request.getSession().removeAttribute(RandomCodeController.RAND_CODE);
                    this.errmsg = "验证码输入错误！";
                    modelAndView.addObject("msg", errmsg);
                    modelAndView.addObject("type", "checkCode");
                    return modelAndView;
                }
                // 查询用户
                userFromDb = userService.getUserByLoginEmail(user.getEmail());
                if (ObjectUtils.isNull(userFromDb)) {
                    this.errmsg = "请输入正确的邮箱地址!";
                    modelAndView.addObject("msg", errmsg);
                    modelAndView.addObject("type", "username");
                    return modelAndView;
                }
                request.getSession().removeAttribute(RandomCodeController.RAND_CODE);
                // 验证密码是否正确

                if (userFromDb != null && DatePwdUtil.checkIsRight(userFromDb.getPassword(), user.getPassword(), userFromDb.getCustomerkey())) {
                    if (userFromDb.getLevel() == 1) {
                        userService.setLoginStatus(userFromDb, "true", request, response);
                        logger.info(" ++++ sysuser login success: " + user.getEmail() + " is logined ok !");
                        request.getSession().removeAttribute(SysUserConstants.topTabList);
                        request.getSession().removeAttribute(SysUserConstants.leftTabList);
                        cacheKit.remove(MemConstans.SYS_USER_FUNCTION + userFromDb.getId());
                        returnPage = LOGINSUCCESS;
                    } else {
                        this.errmsg = "没有后台登陆权限";
                        modelAndView.addObject("msg", errmsg);
                        modelAndView.addObject("type", "other");
                        return modelAndView;
                    }
                } else {
                    this.errmsg = "请输入正确的密码！";
                    modelAndView.addObject("msg", errmsg);
                    modelAndView.addObject("type", "password");
                    return modelAndView;
                }
            } else {
                this.errmsg = "请输入正确的邮箱地址!";
                modelAndView.addObject("msg", errmsg);
                modelAndView.addObject("type", "username");
                return modelAndView;
            }
        } catch (Exception e) {
            logger.error("+++ do login error", e);
            this.errmsg = "系统异常！";
            modelAndView.addObject("msg", errmsg);
            modelAndView.addObject("type", "other");
            return modelAndView;
        }
        logger.info("+++ return returnPage:" + returnPage);
        return new ModelAndView(returnPage);
    }


    /**
     * 讲权限设置为List<List<SysFunction>>结构
     *
     * @param funcList
     * @return
     */
    private List<List<SysFunction>> processTabUserFunctionList(List<SysFunction> funcList) {
        List<List<SysFunction>> tabList = new ArrayList<List<SysFunction>>();
        // 把第一级的筛选出来（parentId=0的）
        for (int i = 0; i < funcList.size(); i++) {
            SysFunction func = funcList.get(i);
            if (func.getParentFunctionId().longValue() == 0) {
                List<SysFunction> list = new ArrayList<SysFunction>();
                list.add(func);
                tabList.add(list);
            }
        }
        int count = funcList.size() + 1;
        while (count > funcList.size()) {
            count = funcList.size();
            for (int i = 0; i < funcList.size(); i++) {
                SysFunction func = funcList.get(i);
                for (int j = 0; j < tabList.size(); j++) {
                    List<SysFunction> list = tabList.get(j);
                    for (int k = 0; k < list.size(); k++) {
                        if (list.get(k).getFunctionId().longValue() == func.getParentFunctionId().longValue()) {
                            list.add(func);
                            funcList.remove(func);
                        }
                    }
                }
            }
        }
        return tabList;
    }

    /**
     * 获取第一级的权限
     *
     * @param funcList
     * @return
     */
    private List<SysFunction> processTopTabUser(List<SysFunction> funcList) {
        List<SysFunction> tabList = new ArrayList<SysFunction>();
        // 把第一级的筛选出来（parentId=0的）
        for (int i = 0; i < funcList.size(); i++) {
            SysFunction func = funcList.get(i);
            if (func.getParentFunctionId().longValue() == 0) {
                tabList.add(func);
            }
        }
        return tabList;
    }

    /**
     * 获取本级下的子权限
     *
     * @param funcList
     * @return
     */
    private List<List<SysFunction>> processChildList(List<SysFunction> funcList, Long parentId) {
        List<List<SysFunction>> tabList = new ArrayList<List<SysFunction>>();
        // 把第一级的筛选出来（为parentId的）
        for (int i = 0; i < funcList.size(); i++) {
            SysFunction func = funcList.get(i);
            if (func.getParentFunctionId().longValue() == parentId.longValue()) {
                List<SysFunction> list = new ArrayList<SysFunction>();
                list.add(func);
                tabList.add(list);
            }
        }
        int count = funcList.size() + 1;
        while (count > funcList.size()) {
            count = funcList.size();
            for (int i = 0; i < funcList.size(); i++) {
                SysFunction func = funcList.get(i);
                for (int j = 0; j < tabList.size(); j++) {
                    List<SysFunction> list = tabList.get(j);
                    for (int k = 0; k < list.size(); k++) {
                        if (list.get(k).getFunctionId().longValue() == func.getParentFunctionId().longValue()) {
                            list.add(func);
                            funcList.remove(func);
                        }
                    }
                }
            }
        }
        return tabList;
    }

    /**
     * 退出系统操作
     *
     * @return String
     * @throws Exception
     */
    @RequestMapping("/sys/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Long userId = SingletonLoginUtils.getLoginUserId(request);
        loginOnlineService.deleteLoginOnlineById(userId);
        String _sid = WebUtils.getCookie(request, CommonConstants.USER_SINGEL_ID);
        if (StringUtils.isNotEmpty(_sid)) {
            cacheKit.remove(_sid);
        }
        cacheKit.remove(MemConstans.USEREXPAND_INFO + userId);
        WebUtils.deleteCookie(request, response, CommonConstants.USER_SINGEL_ID);
        WebUtils.deleteCookie(request, response, CommonConstants.USER_SINGEL_NAME);
        WebUtils.deleteCookie(request, response, "usercookieuserimg");
        WebUtils.deleteCookie(request, response, "e.subject");
        return LOGINOUT;
    }

    /**
     * topframe
     *
     * @return
     */
    @RequestMapping("/sys/topframe")
    public String topframe(Model model, HttpServletRequest request) {
        JsonObject jsonObject = SingletonLoginUtils.getLoginUser(request);
        if (jsonObject.has("nickname")) {
            model.addAttribute("userName", jsonObject.get("nickname"));
        }
        Gson gson = new Gson();
        /*SysUser sysUser = gson.fromJson(gson.toJson(jsonObject), SysUser.class);
        model.addAttribute("sysuser", sysUser);*/
        return TOPFRAME;
    }

    /**
     * topframe
     *
     * @return
     */
    @RequestMapping("/sys/leftframe")
    public String leftframe() {
        return LEFTFRAME;
    }

    /**
     * 主页面
     *
     * @return
     */
    @RequestMapping("/sys/main")
    public String adminmain(HttpServletRequest request, HttpServletResponse response) {
        JsonObject sysUser = SingletonLoginUtils.getLoginUser(request);
        String sid = WebUtils.getCookie(request, CommonConstants.USER_SINGEL_ID);
        if (ObjectUtils.isNotNull(sysUser) && sysUser.get("id").getAsLong() == SUPERID) {
            List<SysFunction> userFunction = this.sysUserService.getUserFunction(null);
            cacheKit.set(MemConstans.SYS_USER_FUNCTION + sysUser.get("id"), userFunction, MemConstans.SYS_USER_TIME);
            List<SysFunction> topTabList = processTopTabUser(new ArrayList<>(userFunction));
            // 页面显示顶部只显示第一级的权限
            this.setSessionAttribute(request, SysUserConstants.topTabList, topTabList);
            // 二级权限
            this.setSessionAttribute(request, SysUserConstants.leftTabList, processChildList(new ArrayList<>(userFunction), topTabList.get(0).getFunctionId()));
        } else {
            if (ObjectUtils.isNotNull(sysUser)) {
                // 取当前用户的权限
                List<SysFunction> curUserFunctionList = (List<SysFunction>) cacheKit.get(MemConstans.SYS_USER_FUNCTION + sysUser.get("id"));
                if (ObjectUtils.isNull(curUserFunctionList)) {
                    curUserFunctionList = sysUserService.getUserFunction(Long.valueOf(sysUser.get("id").toString()));
                    cacheKit.set(MemConstans.SYS_USER_FUNCTION + sysUser.get("id"), curUserFunctionList, MemConstans.SYS_USER_TIME);
                }
                // 页面显示顶部只显示第一级的权限
                List<SysFunction> topTabList = processTopTabUser(new ArrayList<SysFunction>(curUserFunctionList));
                this.setSessionAttribute(request, SysUserConstants.topTabList, topTabList);
                // 左侧显示权限的第一个
                if (ObjectUtils.isNotNull(topTabList)) {
                    List<SysFunction> leftll = (List<SysFunction>) this.getSessionAttribute(request, SysUserConstants.leftTabList);
                    if (ObjectUtils.isNull(leftll)) {
                        this.setSessionAttribute(request, SysUserConstants.leftTabList, processChildList(new ArrayList<SysFunction>(curUserFunctionList), topTabList.get(0).getFunctionId()));
                    }
                }
            }
        }
        return "forward:/admin/statistics/web/detail";
    }

    /**
     * rightframe
     *
     * @return
     */
    @RequestMapping("/sys/rightframe")
    public String rightframe(Model model, HttpServletRequest request) {
        model.addAttribute("sysuser", SingletonLoginUtils.getLoginUser(request));
        return RIGHTFRAME;
    }

    /**
     * topframe
     *
     * @return
     */
    @RequestMapping("/sys/bottomFrame")
    public String bottomFrame() {
        return BOTTOMFRAME;
    }

}
