package com.atdld.os.edu.controller.userprofile;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import weibo4j.Users;
import weibo4j.util.WeiboConfig;

import com.qq.connect.QQConnectException;
import com.qq.connect.api.OpenID;
import com.qq.connect.api.qzone.UserInfo;
import com.qq.connect.javabeans.AccessToken;
import com.qq.connect.javabeans.qzone.UserInfoBean;
import com.qq.connect.oauth.Oauth;
import com.atdld.os.core.controller.BaseController;
import com.atdld.os.core.util.ObjectUtils;
import com.atdld.os.edu.entity.user.User;
import com.atdld.os.edu.entity.userprofile.ProfileType;
import com.atdld.os.edu.entity.userprofile.UserProfile;
import com.atdld.os.edu.service.user.UserService;
import com.atdld.os.edu.service.userprofile.UserProfileService;

@Controller
public class OpenappWebController extends BaseController{
    /**
	 * 
	 */
    @SuppressWarnings("unused")
	private static final long serialVersionUID = -9046516845301034752L;

    private static final Logger logger = LoggerFactory.getLogger(OpenappWebController.class);
    
    @Autowired
    private UserService userService;
    @Autowired
    private UserProfileService userProfileService;
   
    
    /**
     * 跳转到登录连接，返回联合登录的地址 根据传的type处理不同的请求。
     */
    @RequestMapping("/app/authlogin")
    public String authlogin(HttpServletRequest request,HttpServletResponse response,@RequestParam String appType) {
    	String callBack="";
        try {
            // 检查类型并转为统一的大写格式
            appType = checkType(appType);
            if (StringUtils.isEmpty(appType)) {
                return "redirect:/login";
            }
            // QQ联合登录的返回处理
            if (ProfileType.QQ.toString().equalsIgnoreCase(appType)) {
                // 获取QQ联合登录的地址
                callBack = new Oauth().getAuthorizeURL(request);
                logger.info("++ QQ callBack:" + callBack);
                return "redirect:"+callBack;
            }
            if (ProfileType.SINA.toString().equalsIgnoreCase(appType)) {
                // 获取微博联合登录的地址
                try {
                    String weiboKey = WeiboConfig.getValue("client_ID");
                    String weiboSecret = WeiboConfig.getValue("client_SERCRET");
                    weibo4j.Oauth weibooauth = new weibo4j.Oauth();
                    callBack = weibooauth.authorize("code", weiboKey, weiboSecret);
                    logger.info("++ SINA callBack:" + callBack);
                    // BareBonesBrowserLaunch.openURL(weibooauth.authorize("code",weiboKey,weiboSecret));
                    return "redirect:"+callBack;
                } catch (Exception e) {
                    logger.info("Unable to get the sina access token.");
                }

            }
        } catch (QQConnectException e) {
            logger.error("+++authlogin：get unio login url error:", e);
            return "login";
            // setResult(new Result<String>(false, "false", "", null));
        }
        // setResult(new Result<String>(false, "success", "", callBack));
        return "success";
    }

    /**
     * QQ联合登录成功回调地址
     */
    @RequestMapping("/app/loginReturn")
    public String loginReturn(HttpServletRequest request,HttpServletResponse response) {	
        try {
        	String appType =request.getParameter("appType");
            appType = ProfileType.QQ.toString();
            AccessToken accessTokenObj = (new Oauth()).getAccessTokenByRequest(request);
            if (accessTokenObj.getAccessToken().equals("")) {
                logger.info("+++loginRetrun，获取返回的参数失败:");
                return "login";
            } else {
                String accessToken = accessTokenObj.getAccessToken();
                long tokenExpirein = accessTokenObj.getExpireIn();
                logger.info("+++accessToken:" + accessToken + ",tokenExpirein:" + tokenExpirein);
                // 利用获取到的accessToken 去获取当前用的openid -------- start
                OpenID openIDObj = new OpenID(accessToken);
                String appId = openIDObj.getUserOpenID();
                logger.info("+++ loginReturn openIDObj:" + appId);
                // 利用获取到的accessToken 去获取当前用户的openid --------- end
                UserInfo qzoneUserInfo = new UserInfo(accessToken, appId);
                UserInfoBean userInfoBean = qzoneUserInfo.getUserInfo();
                // 未绑定的跳转到登录页面,要将QQkey放到页面中，提交注册时用
                UserProfile userProfile = new UserProfile();
                userProfile.setValue(appId);
                userProfile.setProfiletype(appType);
                List<UserProfile> list = userProfileService.getUserProfileList(userProfile);
                if (ObjectUtils.isNotNull(list)) {//已经绑定过存在的帐号
                	userProfile = list.get(0);
                    // 登录操作
                    Long cusId = userProfile.getUserid();
                    logger.info("+++ already bindsuccess cusId:" + cusId + ",openkey:" + appId);
                    //userExpandService.getDoLogin(null, cusId, request, response);
                    //执行登陆操作
                    User user = userService.getUserById(Long.valueOf(cusId));
                    userService.setLoginStatus(user,"true",request, response);
                    return  "redirect:/index";// "bindJump";//跳转到绑定的登录页面
                } else {
                    // 自动生成邮箱帐号
                    String photo=null;
                    String  cusName=null;
                    if (userInfoBean != null) {
                    	//photo=HttpUtil.doPost(CommonConstants.uploadImageServer+"/netfile?url="+userInfoBean.getAvatar().getAvatarURL100()+"&base="+CommonConstants.projectName+"&param=customer", null);
                    	cusName = userInfoBean.getNickname().replaceAll("[^0-9a-zA-Z\u4e00-\u9fa5.，,。？“”]+","");
                       logger.info("++++ getNickname:" + cusName);
                    }
                    userProfileService.addOpenAppRegister(photo,cusName, request, response, ProfileType.QQ, appId);
                    return "redirect:/index";// "bindJump";//跳转到绑定的登录页面
                }
            }
        } catch (Exception e) {
            logger.error("++++ loginReturn exception", e);
            return "redirect:/login";
        }

    }

    /**
     * sina联合登录成功回调地址
     */
    @RequestMapping("/app/sinalogin")
    public String sinaloginReturn(HttpServletRequest request,HttpServletResponse response) {
        try {
            String appType = ProfileType.SINA.toString();
            logger.info("+++sinaloginReturn sina invoce:");
            // 根据code获得授权
            String code = request.getParameter("code");
            weibo4j.Oauth oauth = new weibo4j.Oauth();
            weibo4j.http.AccessToken accessToken = oauth.getAccessTokenByCode(code);
            if (accessToken == null) {
                logger.info("+++sinaloginReturn,gettoken null");
                return "redirect:/login";
            } else {

                Users um = new Users();
                um.client.setToken(accessToken.getAccessToken());
                weibo4j.model.User user = um.showUserById(accessToken.getUid());
                String photo="";
                String cusName="";
                if (user != null) {
                    logger.info("+++ sinauser name:" + user);
                    cusName = user.getScreenName().replaceAll("[^0-9a-zA-Z\u4e00-\u9fa5.，,。？“”]+","");
                    //photo=HttpUtil.doPost(CommonConstants.uploadImageServer+"/netfile?url="+user.getavatarLarge()+"&base="+CommonConstants.projectName+"&param=customer", null);
                }

                logger.info("++++sinaloginReturn token:" + accessToken.toString());
                String appId = accessToken.getUid();
                // 未绑定的跳转到登录页面,要将QQkey放到页面中，提交注册时用
                UserProfile userProfile = new UserProfile();
                userProfile.setValue(appId);
                userProfile.setProfiletype(appType);
                List<UserProfile> userProfileList = userProfileService.getUserProfileList(userProfile);
                if (userProfileList != null && userProfileList.size() > 0) {
                	userProfile = userProfileList.get(0);
                    // 登录操作
                    Long cusId = userProfile.getUserid();
                    logger.info("+++ already bindsuccess cusId:" + cusId + ",openkey:" + appId);
                    //执行登陆操作
                    User user1 = userService.getUserById(cusId);
                    userService.setLoginStatus(user1,"true",request, response);
                    // 登录成功跳转到首页
                    return "redirect:/index";
                } else {
                	userProfileService.addOpenAppRegister(photo,cusName, request, response, ProfileType.SINA, appId);
                    return "redirect:/index";
                }
            }
        } catch (Exception e) {
            logger.error("++++ sinaloginReturn exception", e);
            return "redirect:/login";
        }

    }

   /* *//**
     * 获得用户绑定的第三方网站
     * 
     * @return
     *//*
    public String getOpenlist() {
    	 Openapp openApp = new Openapp();
    	 openApp.setCusId();
        openappList = openappService.getOpenappList(openApp);
        for (Openapp openapp : openappList) {
            if (openapp.getAppType().equalsIgnoreCase(ProfileType.QQ.toString())) {
                //bindqq=true;
            } else if (openapp.getAppType().equalsIgnoreCase(ProfileType.SINA.toString())) {
                //bindsina=true;
            }
        }
        return "openlist";
    }

    *//**
     * 解绑操作
     * 
     * @return
     *//*
    public String unBindOpenApp() {
        try {
            if (StringUtils.isNotEmpty(appType)) {
                // 解绑
                Openapp openapp = new Openapp();
                openapp.setCusId(this.getLoginUserId());
                openapp.setAppType(appType);
                openappService.delOpenappById(openapp);
                this.setJson(false, "success", null);
            } else {
            	this.setJson(false, "error", null);
            }
        } catch (Exception e) {
            logger.error("++unBindOpenApp error", e);
            this.setJson(false, "error", null);
        }
        return "json";
    }*/

    // 检查登录类型是否符合
    public String checkType(String type) {
        if (StringUtils.isEmpty(type)) {
            return null;
        }
        if (ProfileType.QQ.toString().equalsIgnoreCase(type)) {
            type = ProfileType.QQ.toString();
        }
        if (ProfileType.SINA.toString().equalsIgnoreCase(type)) {
            type = ProfileType.SINA.toString();
        }
        return type;
    }

}
