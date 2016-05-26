package io.wangxiao.image.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import io.wangxiao.commons.util.ObjectUtils;
import io.wangxiao.commons.util.web.WebUtils;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Enumeration;

/**
 * 用户访问log
 * 
 */
public class ImageLoggerFilter extends HandlerInterceptorAdapter {

    private static Logger logger = LoggerFactory.getLogger(ImageLoggerFilter.class);

    @Getter
    @Setter
    private String[] excludeUrls;

    public static Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();
    public static JsonParser jsonParser = new JsonParser();


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
        StringBuffer buffer = new StringBuffer("\n");

        buffer.append("Controller\t:" + path).append("\n");
        buffer.append("User IP    \t:" + ip).append("\n");
        buffer.append("Parameter\t:");
        //参数
        Enumeration<String> enume = request.getParameterNames();
        while (enume.hasMoreElements()) {
            String key = enume.nextElement();
            String[] value = request.getParameterValues(key);
            if (0 < buffer.toString().length()) {
                buffer.append("  ");
            }
            buffer.append(key).append("=").append(Arrays.toString(value));
        }
        buffer.append("\n-------------------------------------------------------------------");
        logger.info(buffer.toString());
        return true;
    }

}
