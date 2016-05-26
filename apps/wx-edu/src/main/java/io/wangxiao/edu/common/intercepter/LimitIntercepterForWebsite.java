package io.wangxiao.edu.common.intercepter;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import io.wangxiao.edu.common.contoller.SingletonLoginUtils;
import io.wangxiao.edu.home.constants.enums.WebSiteProfileType;
import io.wangxiao.edu.home.service.website.WebsiteNavigateService;
import io.wangxiao.edu.home.service.website.WebsiteProfileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @description 网站配置管理拦截器
 */
public class LimitIntercepterForWebsite extends HandlerInterceptorAdapter {
    //logger
    Logger logger = LoggerFactory.getLogger(LimitIntercepterForWebsite.class);
    @Autowired
    private WebsiteProfileService websiteProfileService;
    @Autowired
    private WebsiteNavigateService websiteNavigateService;
    private Gson gson = new Gson();

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
        try {
            String url = request.getRequestURL().toString();
            String requestType = request.getHeader("X-Requested-With");
//             //移动端访问网校，排除app、ajax请求进行拦截跳转
//             if(SingletonLoginUtils.JudgeIsMoblie(request)&&url.indexOf("mobile")==-1&&url.indexOf("webapp")==-1&&(requestType==null||!requestType.equals("XMLHttpRequest"))){
//                 if(url.indexOf("/front/couinfo")>-1){// 课程微站详情
//                     response.sendRedirect(url.replace("/front/couinfo/","/mobile/course/info/"));
//                     return false;
//                 }else if(url.equals("/")){
//                     response.sendRedirect("/mobile/index");// 手机访问跳转微站
//                     return false;
//                 }
//             }
            //获得网站配置
            Map<String, Object> websitemap = websiteProfileService.getWebsiteProfileByType(WebSiteProfileType.web.toString());
            //获得LOGO配置
            Map<String, Object> logomap = websiteProfileService.getWebsiteProfileByType(WebSiteProfileType.logo.toString());
            Map<String, Object> loadimagemap = websiteProfileService.getWebsiteProfileByType(WebSiteProfileType.loadimage.toString());
            Map<String, Object> courseimagemap = websiteProfileService.getWebsiteProfileByType(WebSiteProfileType.courseimage.toString());
            request.setAttribute("websitemap", websitemap);
            request.setAttribute("logomap", logomap);
            request.setAttribute("loadimagemap", loadimagemap);
            request.setAttribute("courseimagemap", courseimagemap);
            //网站统计代码
            Map<String, Object> tongjiemap = websiteProfileService.getWebsiteProfileByType(WebSiteProfileType.censusCode.toString());
            request.setAttribute("tongjiemap", tongjiemap);
            //网站导航配置
            Map<String, Object> navigatemap = websiteNavigateService.getWebNavigate();
            request.setAttribute("navigatemap", navigatemap);
            //购买方式配置
            Map<String, Object> salemap = websiteProfileService.getWebsiteProfileByType(WebSiteProfileType.sale.toString());
            request.setAttribute("salemap", salemap);
            //网站开关配置
            Map<String, Object> keywordmap = websiteProfileService.getWebsiteProfileByType(WebSiteProfileType.keyword.toString());
            request.setAttribute("keywordmap", keywordmap);
            //获取用户是否登录
            Long userId = SingletonLoginUtils.getLoginUserId(request);
            if (userId != null && userId > 0) {
                JsonObject loginUser = SingletonLoginUtils.getLoginUser(request);
                @SuppressWarnings("unchecked")
                HashMap<String, String> json = gson.fromJson(loginUser, HashMap.class);
                request.setAttribute("loginUser", json);
                request.setAttribute("userId", userId);
            } else {
                request.setAttribute("userId", 0);
            }
        } catch (Exception e) {
            logger.error("LimitIntercepterForWebsite.preHandle 网站配置出错", e);
        }

        return super.preHandle(request, response, handler);
    }
}
