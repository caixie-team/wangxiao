package com.atdld.open.cms.common.intercepter;

import com.google.gson.JsonObject;
import com.atdld.open.cms.common.controller.SingletonLoginUtils;
import com.atdld.open.common.constants.CommonConstants;
import com.atdld.open.core.service.cache.MemCache;
import com.atdld.open.core.util.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName LimitIntercepterForAdmin
 * @package com.atdld.open.sns.common.intercepter
 * @description 后台用户拦截器
 */
public class LimitIntercepterForAdmin extends HandlerInterceptorAdapter {

    private Logger logger = LoggerFactory.getLogger(LimitIntercepterForAdmin.class);
    MemCache memCache =MemCache.getInstance();
    // admin用户唯一ukey
    public static final String COOKIE_ADMIN_KEY = "sidadmink";

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
       
        boolean flag = super.preHandle(request, response, handler);
        // 访问的路径
        if(!"1".equals(CommonConstants.l)){
            return false;
        }
        JsonObject user = SingletonLoginUtils.getSysUser(request);
        if (ObjectUtils.isNotNull(user)) {//已经登录了验证权限
                flag = true;
        }else {
            logger.info("++ admin intercpter user not login:");
            System.out.println("CommonConstants.webPath:"+CommonConstants.webPath);
            response.sendRedirect(CommonConstants.webPath+"/admin");// 未登录状态跳转到登录页面
            flag = false;
        }

        return flag;
    }

}
