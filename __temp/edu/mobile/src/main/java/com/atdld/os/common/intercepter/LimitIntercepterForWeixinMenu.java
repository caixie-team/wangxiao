package com.atdld.os.common.intercepter;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.atdld.os.common.constants.CommonConstants;
import com.atdld.os.core.service.cache.MemCache;
import com.atdld.os.core.util.PropertyUtil;
import com.atdld.os.core.util.web.HttpUtil;
import com.atdld.os.core.util.web.WebUtils;
import com.atdld.os.edu.service.website.WebsiteProfileService;

/**
 * @author :
 * @ClassName com.atdld.os.sns.common.intercepter.LimitIntercepterForWeb
 * @description 前端用户拦截
 * @Create Date : 2013-12-17 下午2:23:10
 */
public class LimitIntercepterForWeixinMenu extends HandlerInterceptorAdapter {
	@Autowired
	private WebsiteProfileService websiteProfileService;
    Logger logger = LoggerFactory.getLogger(LimitIntercepterForWeixinMenu.class);

    MemCache memCache = MemCache.getInstance();
    // 读取配置文件类
    public static PropertyUtil propertyUtil = PropertyUtil.getInstance(CommonConstants.propertyFile);

    public boolean ischeck=false;//是否开启同时1个帐号只能1个人登录开关
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
        super.preHandle(request, response, handler);
        
        //code作为换取access_token的票据，每次用户授权带上的code将不一样，code只能使用一次，5分钟未被使用自动过期。
    	String code=request.getParameter("code");
    	logger.info("+++code:" + code);
    	if(code!=null){//微信菜单跳转
    		Map<String, Object> weixinMap = websiteProfileService.getWebsiteProfileByType("weixin");
    		
    		Map<String, Object> weixinMapVal=(Map<String, Object>) weixinMap.get("weixin");
    		String APPID=(String) weixinMapVal.get("wxAppID");
    		String APPSECRET=(String) weixinMapVal.get("wxAppSecret");
        	//code换取openid
        	String urlStr="https://api.weixin.qq.com/sns/oauth2/access_token";
    		String param="appid="+APPID+"&secret="+APPSECRET+"&code="+code+"&grant_type=authorization_code";
    		String resMsg=HttpUtil.doGet(urlStr, param);
    		Gson gson=new Gson();
    		Map<String,String> ps = gson.fromJson(resMsg, new TypeToken<Map<String,String>>(){}.getType());
    		String openId=ps.get("openid");
    		logger.info("openId:"+openId);
    		if(openId==null){
    		   response.sendRedirect("/weixinerror/msg");
               return false;
            }else{
            	//openId存入cookie30天
            	WebUtils.setCookie(response, CommonConstants.USER_SINGEL_OPENID, openId, 30);
            }
    	}
        return true;
    }

}
