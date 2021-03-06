package com.atdld.os.common.intercepter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.atdld.os.common.constants.CommonConstants;
import com.atdld.os.common.constants.MemConstans;
import com.atdld.os.common.contoller.SingletonLoginUtils;
import com.atdld.os.core.service.cache.MemCache;
import com.atdld.os.core.util.ObjectUtils;
import com.atdld.os.core.util.PropertyUtil;
import com.atdld.os.core.util.StringUtils;
import com.atdld.os.core.util.web.WebUtils;

/**
 * @author :
 * @ClassName com.atdld.os.sns.common.intercepter.LimitIntercepterForWeb
 * @description 前端用户拦截
 * @Create Date : 2013-12-17 下午2:23:10
 */
public class LimitIntercepterForMobile extends HandlerInterceptorAdapter {

    Logger logger = LoggerFactory.getLogger(LimitIntercepterForMobile.class);

    MemCache memCache = MemCache.getInstance();
    // 读取配置文件类
    public static PropertyUtil propertyUtil = PropertyUtil.getInstance(CommonConstants.propertyFile);

    public boolean ischeck=false;//是否开启同时1个帐号只能1个人登录开关
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
        if(!"1".equals(CommonConstants.l)){
            return false;
        }

        JsonObject userJsonObject = SingletonLoginUtils.getLoginUser(request);

        if(ObjectUtils.isNotNull(userJsonObject)){
            if(ischeck){
                 //登录状态的验证,是否被其他人踢掉
                String mcuurent=(String) memCache.get(MemConstans.USER_CURRENT_LOGINTIME + userJsonObject.get("id"));
                if(StringUtils.isNotEmpty(mcuurent)){
                    if(Long.valueOf(userJsonObject.get("current").toString())!=Long.valueOf(mcuurent).longValue()){
                            //跳转到登录页面并提示信息根据msg
                            response.sendRedirect("/login?msg=other");
                        //清除登录状态
                        String sid = WebUtils.getCookie(request, CommonConstants.USER_SINGEL_ID);
                        if (StringUtils.isNotEmpty(sid)) {
                            memCache.remove(sid);
                        }
                        WebUtils.deleteCookie(request, response, CommonConstants.USER_SINGEL_ID);
            			WebUtils.deleteCookie(request, response, CommonConstants.USER_SINGEL_NAME);
            			WebUtils.deleteCookie(request, response, "usercookieuserimg");
                        return false;
                    } 
                }
                return true;
            }else{
                return true;
            }
        }
        //未登录时跳转到登录页面
        if (WebUtils.isNotAjaxRequest(request)) {
            if (request.getRequestURL().equals("/mobile/index")) {
                WebUtils.setCookieSessionTime(response, CommonConstants.redirect, "/mobile/index");
            } else {
                WebUtils.setCookieSessionTime(response, CommonConstants.redirect, request.getRequestURI());
            }
        }
        response.sendRedirect("/mobile/login");
        return false;
    }

}
