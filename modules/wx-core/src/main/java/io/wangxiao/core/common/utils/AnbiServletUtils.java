package io.wangxiao.core.common.utils;


import io.wangxiao.core.web.ServletUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created with IntelliJ IDEA.
 * User: basil
 * Date: 13-12-4
 * Time: 10:29
 */
public class AnbiServletUtils extends ServletUtils {


    public static HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    public static HttpSession getSession() {
        return getRequest().getSession();
    }

    /**
     * 获取Session中的对象
     * @param name
     * @return
     */
    public static Object getSessionAttribute(String name) {
        return getSession().getAttribute(name);

    }

    /**
     * 设置Session
     *
     * @param name
     * @param value
     */
    public static void setSessionAttribute(String name, Object value) {
        getSession().setAttribute(name, value);
    }

//    /**
//     * 需要判断是否为NULL
//     *
//     * @return
//     */
//    public static Customer getSessionUser() {
//        // 用户
//        return (Customer) getSessionAttribute(SecurityConstants.LOGIN_USER);
//
//    }


}
