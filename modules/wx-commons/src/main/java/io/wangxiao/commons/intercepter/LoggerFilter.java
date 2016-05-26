package io.wangxiao.commons.intercepter;

import java.util.Arrays;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.wangxiao.commons.service.cache.CacheKit;
import io.wangxiao.commons.util.StringUtils;
import io.wangxiao.commons.util.web.WebUtils;
import lombok.Getter;
import lombok.Setter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import io.wangxiao.commons.util.ObjectUtils;

/**
 * 用户访问log
 * 
 */
public class LoggerFilter extends HandlerInterceptorAdapter {

    private static Logger logger = LoggerFactory.getLogger(LoggerFilter.class);

    static CacheKit cacheKit=CacheKit.getInstance();
    String urlkey="urlcount";//统计请求的url访问次数
    static  int urlkeytime=60*60*24*7;//统计请求的url的存数时间7天
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
        Long uid =getLoginUserId(request);
        StringBuffer buffer = new StringBuffer("\n");

        buffer.append("Controller\t:" + path).append("\n");
        buffer.append("User IP    \t:" + ip + ",userId:"+ uid).append("\n");
        buffer.append("Parameter\t:");
        //参数
        Enumeration<String> enume = request.getParameterNames();
        while (enume.hasMoreElements()) {
            String key = enume.nextElement();
            String[] value = request.getParameterValues(key);
            if (buffer.toString().length() > 0) {
                buffer.append("  ");
            }
            buffer.append(key).append("=").append(Arrays.toString(value));
        }
        buffer.append("\n-------------------------------------------------------------------");
        logger.info(buffer.toString());
        CacheKit.getInstance().getCache().opsForZSet().incrementScore(urlkey,path,1);
        return true;
    }

    public static Long getLoginUserId(HttpServletRequest request)  {
        try {
            JsonObject useObject= getLoginUser(request);
            if (ObjectUtils.isNotNull(useObject)) {
                if ( StringUtils.isNotEmpty(useObject.get("id").toString())) {
                    return Long.valueOf(useObject.get("id").toString());
                } else {
                    return 0L;
                }
            } else {
                return 0L;
            }
        }catch (Exception e){
            return 0L;
        }
    }
    /**
     * 获取登陆用户
     *
     * @return User
     * @throws Exception
     */
    public static JsonObject getLoginUser(HttpServletRequest request)  {
        String sid = WebUtils.getCookie(request, "sid");
        if (StringUtils.isNotEmpty(sid)) {
            Object ob =   cacheKit.get(sid);
            if(ObjectUtils.isNotNull(ob)){
                JsonObject user=  jsonParser.parse(ob.toString()).getAsJsonObject();
                return user;
            }
        }
        return null;
    }

}
