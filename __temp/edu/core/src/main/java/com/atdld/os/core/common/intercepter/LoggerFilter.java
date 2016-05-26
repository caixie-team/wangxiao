package com.atdld.os.core.common.intercepter;

import java.util.Arrays;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.Getter;
import lombok.Setter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.atdld.os.core.util.ObjectUtils;
import com.atdld.os.core.util.web.WebUtils;

/**
 * 用户访问log
 * 
 * @author Administrator
 */
public class LoggerFilter extends HandlerInterceptorAdapter {

    private static Logger logger = LoggerFactory.getLogger(LoggerFilter.class);

    @Getter
    @Setter
    private String[] excludeUrls;

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 访问者IP
        String ip = WebUtils.getIpAddr(request);
        // 访问的路径
        String path = request.getContextPath() + request.getServletPath();
        // 先判断是否是允许例外的url
        if (!ObjectUtils.isNull(excludeUrls)) {
            for (String url : excludeUrls) {
                if (path.contains(url)) {
                    return true;
                }
            }
        }
        StringBuffer buffer = new StringBuffer("");
        Enumeration<String> enume = request.getParameterNames();
        while (enume.hasMoreElements()) {
            String key = enume.nextElement();
            String[] value = request.getParameterValues(key);
            if (buffer.toString().length() > 0) {
                buffer.append("  ");
            }
            buffer.append(key).append("=").append(Arrays.toString(value));
        }
        logger.info("+++user_access_log,ip=" + ip + ",url=" + path + ",parameter=" + buffer);
        return true;

    }

}
