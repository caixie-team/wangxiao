package io.wangxiao.edu.common.intercepter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.wangxiao.commons.service.cache.CacheKit;
import io.wangxiao.commons.util.PropertyUtil;
import io.wangxiao.commons.util.web.HttpUtil;
import io.wangxiao.commons.util.web.WebUtils;
import io.wangxiao.edu.common.constants.CommonConstants;
import io.wangxiao.edu.home.service.website.WebsiteProfileService;
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
 * @description 前端用户拦截
 */
public class LimitIntercepterForWeixinPay extends HandlerInterceptorAdapter {
    @Autowired
    private WebsiteProfileService websiteProfileService;
    Logger logger = LoggerFactory.getLogger(LimitIntercepterForWeixinPay.class);

    CacheKit cacheKit = CacheKit.getInstance();
    // 读取配置文件类
    public static PropertyUtil propertyUtil = PropertyUtil.getInstance(CommonConstants.propertyFile);

    public boolean ischeck = false;//是否开启同时1个帐号只能1个人登录开关

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
        String agent = request.getHeader("User-Agent");
        logger.info("agent:", agent);
        if (agent.toLowerCase().contains("micromessenger")) {//微信内置浏览器
            String openId = WebUtils.getCookie(request, CommonConstants.USER_SINGEL_OPENID);
            if (openId == null || openId.equals("")) {//cookie中没有openId
                //code作为换取access_token的票据，每次用户授权带上的code将不一样，code只能使用一次，5分钟未被使用自动过期。
                String code = request.getParameter("code");
                logger.info("+++code:" + code);
                if (code != null) {//微信获取openId所需code
                    Map<String, Object> weixinMap = websiteProfileService.getWebsiteProfileByType("weixin");

                    Map<String, Object> weixinMapVal = (Map<String, Object>) weixinMap.get("weixin");
                    String APPID = (String) weixinMapVal.get("wxAppID");
                    String APPSECRET = (String) weixinMapVal.get("wxAppSecret");
                    //code换取openid
                    String urlStr = "https://api.weixin.qq.com/sns/oauth2/access_token";
                    String param = "appid=" + APPID + "&secret=" + APPSECRET + "&code=" + code + "&grant_type=authorization_code";
                    String resMsg = HttpUtil.doGet(urlStr, param);
                    Gson gson = new Gson();
                    Map<String, String> ps = gson.fromJson(resMsg, new TypeToken<Map<String, String>>() {
                    }.getType());
                    openId = ps.get("openid");
                    logger.info("openId:" + openId);
                    if (openId == null) {
                        response.sendRedirect("/weixinerror/msg");
                        return false;
                    } else {
                        //openId存入cookie30天
                        WebUtils.setCookie(response, CommonConstants.USER_SINGEL_OPENID, openId, 30);
                        request.setAttribute("openId", openId);
                    }
                }
            }
        }
        return true;
    }

}
