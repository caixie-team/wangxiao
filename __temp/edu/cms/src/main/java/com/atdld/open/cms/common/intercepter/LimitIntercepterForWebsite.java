package com.atdld.open.cms.common.intercepter;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.atdld.open.common.constants.MemConstans;
import com.atdld.open.core.service.cache.MemCache;
import com.atdld.open.core.util.web.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 
 * @ClassName  com.atdld.open.common.intercepter.LimitIntercepterForWebsite
 * @description 网站配置管理拦截器
 */
public class LimitIntercepterForWebsite extends HandlerInterceptorAdapter{
	 //logger
	 Logger logger = LoggerFactory.getLogger(LimitIntercepterForWebsite.class);
	 //获取memcache
     MemCache memCache = MemCache.getInstance();
     
     private Gson gson=new Gson();
     @Override
     public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
         super.afterCompletion(request, response, handler, ex);
     }

     @Override
     public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
         super.postHandle(request, response, handler, modelAndView);
     }
     @SuppressWarnings("unchecked")
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
         if (WebUtils.isAjaxRequest(request)) {
             return super.preHandle(request, response, handler);
         }
         try{
        	//获得网站配置
          	String webJson=(String) memCache.get(MemConstans.WEBSITE_PROFILE+"web");
            //获得LOGO配置
          	String logoJson=(String)memCache.get(MemConstans.WEBSITE_PROFILE+"logo");
            //网站导航配置
          	Map<String,List<String>> navigatesMapJson=(Map<String,List<String>>)memCache.get(MemConstans.WEBSITE_NAVIGATE);
          	if(webJson!=null){
          		Map<String,String> map=gson.fromJson(webJson, new TypeToken<Map<String,String>>(){}.getType());
          		Map<String,Object> websitemap=new HashMap<String, Object>();
          		websitemap.put("web",gson.fromJson(map.get("desciption"), new TypeToken<Map<String,Object>>(){}.getType()));
          		request.setAttribute("websitemap",websitemap);

          	}
          	if(logoJson!=null){
          		Map<String,String> map=gson.fromJson(logoJson, new TypeToken<Map<String,String>>(){}.getType());
          		Map<String,Object> logomap=new HashMap<String, Object>();
          		logomap.put("logo",gson.fromJson(map.get("desciption"), new TypeToken<Map<String,Object>>(){}.getType()));
          		request.setAttribute("logomap",logomap);
          		request.setAttribute("logomap",logomap);
          	}
          	if(navigatesMapJson!=null){
          		Map<String,Object> navigatemap=new HashMap<String, Object>();
          		for(Entry<String,List<String>> entry:navigatesMapJson.entrySet()){
            		List<Map<String,String>> navigates=new ArrayList<Map<String,String>>();
            		List<String> navigatesJson = entry.getValue();
    				for(String navigateJson:navigatesJson){
    					Map<String,String> mapTemp=gson.fromJson(navigateJson,new TypeToken<Map<String,String>>(){}.getType());
    					navigates.add(mapTemp);
            		}
    				navigatemap.put(entry.getKey(), navigates);
            	}
          		request.setAttribute("navigatemap",navigatemap);
          	}
         }catch(Exception e){
        	 logger.error("LimitIntercepterForWebsite.preHandle 网站配置出错",e);
         }
		
    	return super.preHandle(request, response, handler);
    } 
}
