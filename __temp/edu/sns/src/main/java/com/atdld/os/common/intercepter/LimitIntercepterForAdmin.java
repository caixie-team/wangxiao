package com.atdld.os.common.intercepter;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.atdld.os.core.service.cache.MemCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.atdld.os.common.constants.CommonConstants;
import com.atdld.os.common.contoller.SingletonLoginUtils;
import com.atdld.os.core.util.ObjectUtils;

/**
 * @author
 * @ClassName LimitIntercepterForAdmin
 * @package com.atdld.os.sns.common.intercepter
 * @description 后台用户拦截器
 * @Create Date: 2013-3-1 下午12:56:43
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
        if(!"1".equals(CommonConstants.l)){
            return false;
        }
        if (ObjectUtils.isNotNull(SingletonLoginUtils.getSysUser(request))){//已经登录了
            flag = true;
        }else {
            logger.info("++ admin intercpter user not login:");
            response.sendRedirect(CommonConstants.webPath+"/admin/sys/login");// 未登录状态跳转到登录页面
            flag = false;
        }

        return flag;
    }

}
