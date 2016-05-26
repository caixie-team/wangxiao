package io.wangxiao.edu.app.common;

import io.wangxiao.commons.controller.BaseController;
import io.wangxiao.commons.service.cache.CacheKit;
import io.wangxiao.commons.util.StringUtils;
import io.wangxiao.edu.common.contoller.SingletonLoginUtils;
import io.wangxiao.edu.common.util.DateEditor;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

public class AppBaseController extends BaseController {
    protected static final String EDU_VIEW_PATH = "app";// app的view路径
    static CacheKit cacheKit = CacheKit.getInstance();
    protected static final String msgjsp = ("/common/frontmsg");

    /**
     * 返回edu的view路径
     */
    public static String getViewPath(String path) {
        if (StringUtils.isNotEmpty(path)) {
            return "/" + EDU_VIEW_PATH + path;
        }
        return "";
    }

    /**
     * 获取IP
     *
     * @param request
     * @return
     */
    public String getRemoteHost(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip.equals("0:0:0:0:0:0:0:1") ? "127.0.0.1" : ip;
    }

    /**
     * 获取登陆用户的id(前台用)
     *
     * @return int
     * @throws Exception
     */
    protected static Long getLoginUserId(HttpServletRequest request) throws Exception {
        return SingletonLoginUtils.getLoginUserId(request);
    }

    /**
     * 是否登录
     *
     * @param request
     * @return
     */
    public boolean isLogin(HttpServletRequest request) {
        return SingletonLoginUtils.isLogin(request);
    }

    /**
     * spring接受date类型转换
     */
    @InitBinder
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
        // 对于需要转换为Date类型的属性，使用DateEditor进行处理
        binder.registerCustomEditor(Date.class, new DateEditor());
    }

}
