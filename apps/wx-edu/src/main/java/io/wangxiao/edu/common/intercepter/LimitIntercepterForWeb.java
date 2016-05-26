package io.wangxiao.edu.common.intercepter;

import com.google.gson.JsonObject;
import io.wangxiao.commons.service.cache.CacheKit;
import io.wangxiao.commons.util.ObjectUtils;
import io.wangxiao.commons.util.PropertyUtil;
import io.wangxiao.commons.util.StringUtils;
import io.wangxiao.commons.util.web.WebUtils;
import io.wangxiao.edu.common.constants.CommonConstants;
import io.wangxiao.edu.common.constants.MemConstans;
import io.wangxiao.edu.common.contoller.SingletonLoginUtils;
import io.wangxiao.edu.sysuser.entity.SysRole;
import io.wangxiao.edu.sysuser.service.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @description 前端用户拦截
 */
public class LimitIntercepterForWeb extends HandlerInterceptorAdapter {

    Logger logger = LoggerFactory.getLogger(LimitIntercepterForWeb.class);

    CacheKit cacheKit = CacheKit.getInstance();
    @Autowired
    private RoleService roleService;
    // 读取配置文件类
    public static PropertyUtil propertyUtil = PropertyUtil.getInstance(CommonConstants.propertyFile);

    public boolean ischeck = false;//是否开启同时1个帐号只能1个人登录开关

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        super.preHandle(request, response, handler);
        if (!"1".equals(CommonConstants.l)) {
            return false;
        }

        JsonObject userJsonObject = SingletonLoginUtils.getLoginUser(request);

        if (ObjectUtils.isNotNull(userJsonObject)) {
            //获取用户角色
            List<SysRole> roleList = roleService.getRoleListByUserId(userJsonObject.get("id").getAsLong());
            if (roleList != null) {
                request.setAttribute("hasRole", true);
            } else {
                request.setAttribute("hasRole", false);
            }
            if (ischeck) {
                //登录状态的验证,是否被其他人踢掉
                String mcuurent = (String) cacheKit.get(MemConstans.USER_CURRENT_LOGINTIME + userJsonObject.get("id"));
                if (StringUtils.isNotEmpty(mcuurent) && request.getHeader("x-requested-with") == null) {//排除信息页,和异步请求
                    if (Long.valueOf(userJsonObject.get("current").toString()) != Long.valueOf(mcuurent).longValue()) {
                        //跳转到登录页面并提示信息根据msg
                        response.sendRedirect("/login?msg=other");
                        //清除登录状态
                        String sid = WebUtils.getCookie(request, CommonConstants.USER_SINGEL_ID);
                        if (StringUtils.isNotEmpty(sid)) {
                            cacheKit.remove(sid);
                        }
                        WebUtils.deleteCookie(request, response, CommonConstants.USER_SINGEL_ID);
                        WebUtils.deleteCookie(request, response, CommonConstants.USER_SINGEL_NAME);
                        WebUtils.deleteCookie(request, response, "usercookieuserimg");
                        return false;
                    }
                }
                return true;
            } else {
                return true;
            }
        }
        //未登录时跳转到登录页面
        if (WebUtils.isNotAjaxRequest(request)) {
            if (request.getRequestURL().equals("/")) {
                WebUtils.setCookieSessionTime(response, CommonConstants.redirect, "/");
            } else {
                WebUtils.setCookieSessionTime(response, CommonConstants.redirect, request.getRequestURI());
            }
        }
        String agent = request.getHeader("User-Agent");
        if (request.getRequestURL().indexOf("/mobile") > -1) {
            if (agent.toLowerCase().contains("micromessenger")) {//微信内置浏览器
                return true;
            }
        }
        response.sendRedirect("/login");

        return false;
    }

}
