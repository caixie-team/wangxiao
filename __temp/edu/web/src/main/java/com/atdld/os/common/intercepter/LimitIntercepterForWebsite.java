package com.atdld.os.common.intercepter;


import com.atdld.os.core.service.cache.MemCache;
import com.atdld.os.edu.constants.enums.WebSiteProfileType;
import com.atdld.os.edu.service.website.WebsiteNavigateService;
import com.atdld.os.edu.service.website.WebsiteProfileService;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author :
 * @ClassName com.atdld.os.common.intercepter.LimitIntercepterForWebsite
 * @description 网站配置管理拦截器
 * @Create Date : 2014年10月9日 下午5:50:49
 */
public class LimitIntercepterForWebsite extends HandlerInterceptorAdapter {
    //logger
    Logger logger = LoggerFactory.getLogger(LimitIntercepterForWebsite.class);
    //获取memcache
    MemCache memCache = MemCache.getInstance();
    @Autowired
    private WebsiteProfileService websiteProfileService;
    @Autowired
    private WebsiteNavigateService websiteNavigateService;

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
//        	 if(SingletonLoginUtils.JudgeIsMoblie(request)){
//        		 response.sendRedirect(CommonConstants.wzPath);// 手机访问跳转微站
//        		 return false;
//        	 }
            //获得网站配置
            Map<String, Object> websitemap = websiteProfileService.getWebsiteProfileByType(WebSiteProfileType.web.toString());
            //获得LOGO配置
            Map<String, Object> logomap = websiteProfileService.getWebsiteProfileByType(WebSiteProfileType.logo.toString());
            request.setAttribute("websitemap", websitemap);
            request.setAttribute("logomap", logomap);

            //网站统计代码
            Map<String, Object> tongjiemap = websiteProfileService.getWebsiteProfileByType(WebSiteProfileType.censusCode.toString());
            request.setAttribute("tongjiemap", tongjiemap);
            //网站导航配置
            Map<String, Object> navigatemap = websiteNavigateService.getWebNavigate();

            Gson gson = new Gson();
//            gson.toJson(navigatemap);
            request.setAttribute("navigatemap", gson.toJson(navigatemap));

            //购买方式配置
            Map<String, Object> salemap = websiteProfileService.getWebsiteProfileByType(WebSiteProfileType.sale.toString());
            request.setAttribute("salemap", salemap);
            //网站开关配置
            Map<String, Object> keywordmap = websiteProfileService.getWebsiteProfileByType(WebSiteProfileType.keyword.toString());
            request.setAttribute("keywordmap", keywordmap);
        } catch (Exception e) {
            logger.error("LimitIntercepterForWebsite.preHandle 网站配置出错", e);
        }

        return super.preHandle(request, response, handler);
    }
}
