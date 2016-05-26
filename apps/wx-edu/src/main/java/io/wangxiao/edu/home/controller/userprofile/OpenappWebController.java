package io.wangxiao.edu.home.controller.userprofile;

import com.qq.connect.QQConnectException;
import com.qq.connect.api.OpenID;
import com.qq.connect.api.qzone.UserInfo;
import com.qq.connect.javabeans.AccessToken;
import com.qq.connect.javabeans.qzone.UserInfoBean;
import com.qq.connect.oauth.Oauth;
import io.wangxiao.commons.util.ObjectUtils;
import io.wangxiao.commons.util.Security.PurseSecurityUtils;
import io.wangxiao.edu.common.constants.WeixinConstants;
import io.wangxiao.edu.home.common.EduBaseController;
import io.wangxiao.edu.home.entity.user.User;
import io.wangxiao.edu.home.entity.user.UserExpand;
import io.wangxiao.edu.home.entity.userprofile.ProfileType;
import io.wangxiao.edu.home.entity.userprofile.UserProfile;
import io.wangxiao.edu.home.service.user.UserExpandService;
import io.wangxiao.edu.home.service.user.UserService;
import io.wangxiao.edu.home.service.userprofile.UserProfileService;
import net.sf.json.JSONObject;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import weibo4j.Users;
import weibo4j.util.WeiboConfig;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class OpenappWebController extends EduBaseController {

    private static final Logger logger = LoggerFactory.getLogger(OpenappWebController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private UserExpandService userExpandService;
    @Autowired
    private UserProfileService userProfileService;

    /**
     * 跳转到登录连接，返回联合登录的地址 根据传的type处理不同的请求。
     *
     * @param request
     * @param response
     * @param appType
     * @return
     */
    @RequestMapping("/app/authlogin")
    public String authlogin(HttpServletRequest request, HttpServletResponse response, @RequestParam String appType) {
        String callBack = "";
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
                return "redirect:" + callBack;
            } else if (ProfileType.SINA.toString().equalsIgnoreCase(appType)) {
                // 获取微博联合登录的地址
                try {
                    String weiboKey = WeiboConfig.getValue("client_ID");
                    String weiboSecret = WeiboConfig.getValue("client_SERCRET");
                    weibo4j.Oauth weibooauth = new weibo4j.Oauth();
                    callBack = weibooauth.authorize("code", weiboKey, weiboSecret);
                    logger.info("++ SINA callBack:" + callBack);
                    // BareBonesBrowserLaunch.openURL(weibooauth.authorize("code",weiboKey,weiboSecret));
                    return "redirect:" + callBack;
                } catch (Exception e) {
                    logger.info("Unable to get the sina access token.");
                }
            } else if (ProfileType.WEIXIN.toString().equalsIgnoreCase(appType)) {//微信联合登录
                try {
                    callBack += "https://open.weixin.qq.com/connect/qrconnect?appid=" + WeixinConstants.appid;
                    callBack += "&redirect_uri=" + URLEncoder.encode(WeixinConstants.redirect_uri, "utf-8");
                    callBack += "&response_type=" + WeixinConstants.response_type + "&scope=" + WeixinConstants.scope;
                    logger.info("++ WEIXIN callBack:" + callBack);
                    return "redirect:" + callBack;
                } catch (Exception e) {
                    logger.info("Unable to get the weixin access token.");
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
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/app/loginReturn")
    public String loginReturn(HttpServletRequest request, HttpServletResponse response, RedirectAttributes attributes) {
        try {
            Long userId = getLoginUserId(request);
            String appType = ProfileType.QQ.toString();
            AccessToken accessTokenObj = (new Oauth()).getAccessTokenByRequest(request);
            if (accessTokenObj.getAccessToken().equals("")) {
                logger.info("+++loginRetrun，获取返回的参数失败:");
                return "login";
            } else {

                //获取QQ返回信息
                String accessToken = accessTokenObj.getAccessToken();
                long tokenExpirein = accessTokenObj.getExpireIn();
                logger.info("+++accessToken:" + accessToken + ",tokenExpirein:" + tokenExpirein);
                OpenID openIDObj = new OpenID(accessToken);//获取当前用的openid
                String appId = openIDObj.getUserOpenID();//获取当前用的appId
                logger.info("+++ loginReturn openIDObj:" + appId);
                UserInfo qzoneUserInfo = new UserInfo(accessToken, appId);
                UserInfoBean userInfoBean = qzoneUserInfo.getUserInfo();


                /**
                 * 判断用户是否绑定
                 * 要将QQkey放到页面中，提交注册时用
                 * 未绑定＝》绑定页面，已绑定跳转到首页
                 */

                UserProfile userProfile = new UserProfile();
                userProfile.setValue(appId);
                userProfile.setProfiletype(appType);
                List<UserProfile> list = userProfileService.getUserProfileList(userProfile);

                if (ObjectUtils.isNotNull(list)) {//已经绑定
                    userProfile = list.get(0);
                    //执行登陆操作
                    Long cusId = userProfile.getUserid();
                    logger.info("+++ already bindsuccess cusId:" + cusId + ",openkey:" + appId);
                    User user = userService.getUserById(Long.valueOf(cusId));
                    userService.setLoginStatus(user, "true", request, response);
                    return "redirect:/index";// //跳转到首页
                } else {//未绑定
                    String cusName = null;
                    if (userInfoBean != null) {
                        cusName = userInfoBean.getNickname().replaceAll("[^0-9a-zA-Z\u4e00-\u9fa5.，,。？“”]+", "");
                        logger.info("++++ getNickname:" + cusName);
                    }
                    String avatar = getQQAcatar(userInfoBean);
                    //已登录
                    if (ObjectUtils.isNotNull(userId)) {
                        bind(userId, cusName, appId, avatar); // 第三方账号绑定
                        return "redirect:/uc/myProfile";
                    }
                    //未登录，跳转到绑定的登录页面
                    attributes.addAttribute("nickName", cusName);
                    attributes.addAttribute("profileType", appType);
                    attributes.addAttribute("value", appId);
                    attributes.addAttribute("photo", avatar);
                    return "redirect:/user/toBinding";

                }

            }
        } catch (Exception e) {
            logger.error("++++ loginReturn exception", e);
            return "redirect:/login";
        }

    }

    /**
     * 获取qq头像
     *
     * @param userInfoBean
     * @return
     */
    private String getQQAcatar(UserInfoBean userInfoBean) {
        if (userInfoBean.getAvatar().getAvatarURL100() == null || userInfoBean.getAvatar().getAvatarURL100().equals("")) {
            if (userInfoBean.getAvatar().getAvatarURL50() == null || userInfoBean.getAvatar().getAvatarURL50().equals("")) {
                if (userInfoBean.getAvatar().getAvatarURL30() == null || userInfoBean.getAvatar().getAvatarURL30().equals("")) {
                    return "";
                }
                return userInfoBean.getAvatar().getAvatarURL30();
            }
            return userInfoBean.getAvatar().getAvatarURL50();
        } else {
            return userInfoBean.getAvatar().getAvatarURL100();
        }
    }

    /**
     * sina联合登录成功回调地址
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/app/sinalogin")
    public String sinaloginReturn(HttpServletRequest request, HttpServletResponse response, RedirectAttributes attributes) {
        try {
            Long userId = getLoginUserId(request);
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
                String photo = "";
                String cusName = "";
                if (user != null) {
                    logger.info("+++ sinauser name:" + user);
                    cusName = user.getScreenName().replaceAll("[^0-9a-zA-Z\u4e00-\u9fa5.，,。？“”]+", "");
                    //photo=HttpUtil.doPost(CommonConstants.uploadImageServer+"/netfile?url="+user.getavatarLarge()+"&base="+CommonConstants.projectName+"&param=customer", null);
                    photo = user.getavatarLarge();
                }

                logger.info("++++sinaloginReturn token:" + accessToken.toString());
                String appId = accessToken.getUid();
                // 未绑定的跳转到登录页面,要将QQkey放到页面中，提交注册时用
                UserProfile userProfile = new UserProfile();
                userProfile.setValue(appId);
                userProfile.setProfiletype(appType);
                List<UserProfile> userProfileList = userProfileService.getUserProfileList(userProfile);
                if (ObjectUtils.isNotNull(userProfileList)) {//已经绑定过存在的帐号
                    userProfile = userProfileList.get(0);
                    // 登录操作
                    Long cusId = userProfile.getUserid();
                    logger.info("+++ already bindsuccess cusId:" + cusId + ",openkey:" + appId);
                    //执行登陆操作
                    User user1 = userService.getUserById(cusId);
                    userService.setLoginStatus(user1, "true", request, response);
                    // 登录成功跳转到首页
                    return "redirect:/index";
                } else {
                    // 是否登陆
                    if (ObjectUtils.isNotNull(userId)) {
                        // 第三方账号绑定
                        bind(userId, cusName, appId, photo);
                        return "redirect:/uc/myProfile";
                    }
                    attributes.addAttribute("nickName", cusName);
                    attributes.addAttribute("profileType", appType);
                    attributes.addAttribute("value", appId);
                    attributes.addAttribute("photo", photo);
                    //userProfileService.addOpenAppRegister(photo, cusName, request, response, ProfileType.QQ, appId);
                    return "redirect:/user/toBinding";// "bindJump";//跳转到绑定的登录页面
                }
            }
        } catch (Exception e) {
            logger.error("++++ sinaloginReturn exception", e);
            return "redirect:/login";
        }
    }

    /**
     * 微信登录回调(获取code参数)
     *
     * @param request
     * @param response
     * @param attributes
     * @return
     */
    @RequestMapping("/app/weixinlogin")
    public String weixinloginReturn(HttpServletRequest request, HttpServletResponse response, RedirectAttributes attributes) {
        try {
            Long userId = getLoginUserId(request);
            String code = request.getParameter("code");
            if (StringUtils.isNotEmpty(code)) {
                //拼写微信获取access_token访问地址
                String url = "https://api.weixin.qq.com/sns/oauth2/access_token";
                url += "?appid=" + WeixinConstants.appid;
                url += "&secret=" + WeixinConstants.secret;
                url += "&code=" + code;
                url += "&grant_type=authorization_code";

                //获取返回数据
                JSONObject userMap = urlHalder(url);

                //获取openid
                String openId = userMap.get("openid") + "";
                String accessToken = userMap.get("access_token") + "";
                if (StringUtils.isNotEmpty(openId) && StringUtils.isNotEmpty(accessToken)) {
                    UserProfile userProfile = new UserProfile();
                    userProfile.setValue(openId);
                    userProfile.setProfiletype(ProfileType.WEIXIN + "");
                    List<UserProfile> list = userProfileService.getUserProfileList(userProfile);
                    if (ObjectUtils.isNotNull(list)) {//已经绑定过存在的帐号
                        userProfile = list.get(0);
                        // 登录操作
                        Long cusId = userProfile.getUserid();
                        logger.info("+++ already bindsuccess cusId:" + cusId + ",openkey:" + openId);
                        //userExpandService.getDoLogin(null, cusId, request, response);
                        //执行登陆操作
                        User user = userService.getUserById(Long.valueOf(cusId));
                        Map<String, Object> userInfo = userService.setLoginStatus(user, "true", request, response);
                        if (ObjectUtils.isNotNull(userInfo)) {
                            if (userInfo.get("userInfo").toString().equals("mobileIsavalibleErr")) {
                                //跳转绑定邮箱手机号页面
                                return "redirect:/jump_user?userId=" + user.getId();
                            } else {
                                attributes.addAttribute("msg", "对不起,该账号已被冻结。");
                                return "redirect:/front/success";
                            }
                        } else {
                            // 登录成功跳转到首页
                            return "redirect:/index";
                        }
                    } else {
                        String infourl = "https://api.weixin.qq.com/sns/userinfo";
                        infourl += "?access_token=" + accessToken;
                        infourl += "&openid=" + openId;
                        //获取返回数据
                        JSONObject userInfoMap = urlHalder(infourl);
                        String photo = userInfoMap.get("headimgurl") + "";
                        String cusName = userInfoMap.get("nickname") + "";
                        cusName = cusName.replaceAll("[^0-9a-zA-Z\u4e00-\u9fa5.，,。？“”]+", "");
                        //String userId = userProfileService.addOpenAppRegister(photo, cusName, request, response, ProfileType.WEIXIN, openId);

                        // 是否登陆
                        if (ObjectUtils.isNotNull(userId)) {
                            // 第三方账号绑定
                            bind(userId, cusName, openId, photo);
                            return "redirect:/uc/myProfile";
                        }

                        attributes.addAttribute("nickName", cusName);
                        attributes.addAttribute("profileType", ProfileType.WEIXIN.toString());
                        attributes.addAttribute("value", openId);
                        attributes.addAttribute("photo", photo);
                        return "redirect:/user/toBinding";// "bindJump";//跳转到绑定的登录页面
                    }
                } else {
                    logger.info("++weixinloginReturn，获取返回的参数失败");
                    return "redirect:/login";
                }
            } else {
                logger.info("++weixinloginReturn，获取返回的参数失败");
                return "redirect:/login";
            }
        } catch (Exception e) {
            logger.error("++++ weixinloginReturn exception", e);
            return "redirect:/login";
        }

    }

    // 第三方账号绑定
    public void bind(Long userId, String name, String value, String photo) {
        UserProfile userProfile = new UserProfile();
        userProfile.setUserid(userId);
        userProfile.setName(name);
        userProfile.setValue(value);
        userProfile.setProfiledate(new Date());
        userProfileService.addUserProfile(userProfile);
        // 修改头像
        UserExpand user = userExpandService.getUserExpandByUserId(userId);

        //添加第三方登录信息
        if (StringUtils.isEmpty(user.getAvatar()) || "".equals(user.getAvatar())) {
            UserExpand userExpand = new UserExpand();
            userExpand.setCusId(user.getCusId());
            userExpand.setAvatar(photo);
            userExpandService.updateAvatarById(userExpand);
        }
    }

    /**
     * 访问处理
     *
     * @param url
     * @return
     * @throws Exception
     */
    private JSONObject urlHalder(String url) throws Exception {
        //请求访问
        HttpClient client = new HttpClient();
        client.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "utf-8");
        GetMethod method = new GetMethod(url);
        client.executeMethod(method);
        String result = method.getResponseBodyAsString();
        method.releaseConnection();
        JSONObject jsonObject = JSONObject.fromObject(result);
        return jsonObject;
    }

    /**
     * 检查登录类型是否符合
     *
     * @param type
     * @return
     */
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

    public boolean checkIsRight(String dbPassword, String userPassword, String userkey) {
        String despassword = PurseSecurityUtils.secrect(userPassword, userkey);
        return despassword.equals(dbPassword);
    }

    /**
     * 第三方绑定解绑操作
     *
     * @param request
     * @param id
     * @param pwd
     * @return
     */
    @RequestMapping("/uc/ajax/excludeBunging")
    @ResponseBody
    public Map<String, Object> excludeBunging(HttpServletRequest request, @RequestParam("id") Long id, @RequestParam("pwd") String pwd) {
        Map<String, Object> json = null;
        try {
            if (ObjectUtils.isNotNull(id)) {
                User user = userService.getUserById(getLoginUserId(request));
                boolean isOk = checkIsRight(user.getPassword(), pwd, user.getCustomerkey());
                if (!isOk) {
                    json = this.getJsonMap(false, "密码不正确", null);
                    return json;
                }

                userProfileService.deleteUserProfileById(id);
                json = this.getJsonMap(true, "解绑成功！", null);
            } else {
                json = this.getJsonMap(false, "系统繁忙,请稍后再试", null);
            }
        } catch (Exception e) {
            logger.error("OpenappWebController.excludeBunging()--error", e);
            json = this.getJsonMap(false, "系统繁忙,请稍后再试", null);
        }
        return json;
    }

    /**
     * 用户解绑密码确认
     *
     * @param request
     * @param pwd
     * @return
     */
    @RequestMapping("/uc/ajax/bundingPwdConfim")
    @ResponseBody
    public Map<String, Object> bundingPwdConfim(HttpServletRequest request, @RequestParam("pwd") String pwd) {
        Map<String, Object> json = null;
        try {
            User user = userService.getUserById(getLoginUserId(request));
            boolean isOk = checkIsRight(user.getPassword(), pwd, user.getCustomerkey());
            if (isOk) {
                json = this.getJsonMap(true, null, null);
            } else {
                json = this.getJsonMap(false, "密码不正确", null);
            }

        } catch (Exception e) {
            logger.error("OpenappWebController.bundingPwdConfim()--error", e);
            json = this.getJsonMap(false, "系统繁忙,请稍后再试", null);
        }
        return json;
    }
}
