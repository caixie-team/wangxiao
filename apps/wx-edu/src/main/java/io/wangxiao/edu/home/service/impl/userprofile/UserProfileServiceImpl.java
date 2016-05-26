package io.wangxiao.edu.home.service.impl.userprofile;

import io.wangxiao.commons.service.gain.GuidGeneratorService;
import io.wangxiao.edu.home.constants.enums.IntegralKeyword;
import io.wangxiao.edu.home.constants.enums.UserExpandFrom;
import io.wangxiao.edu.home.constants.enums.WebSiteProfileType;
import io.wangxiao.edu.home.dao.userprofile.UserProfileDao;
import io.wangxiao.edu.home.entity.user.User;
import io.wangxiao.edu.home.entity.userprofile.ProfileType;
import io.wangxiao.edu.home.entity.userprofile.UserProfile;
import io.wangxiao.edu.home.service.letter.MsgReceiveService;
import io.wangxiao.edu.home.service.user.UserIntegralService;
import io.wangxiao.edu.home.service.user.UserService;
import io.wangxiao.edu.home.service.userprofile.UserProfileService;
import io.wangxiao.edu.home.service.website.WebsiteProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * UserProfile管理接口 User: qinggang.liu Date: 2014-01-13
 */
@Service("userProfileService")
public class UserProfileServiceImpl implements UserProfileService {

    @Autowired
    private UserProfileDao userProfileDao;
    @Autowired
    private GuidGeneratorService guidGeneratorService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserIntegralService userIntegralService;
    @Autowired
    private WebsiteProfileService websiteProfileService;
    @Autowired
    private MsgReceiveService msgReceiveService;

    /**
     * 添加UserProfile
     *
     * @param userProfile 要添加的UserProfile
     * @return id
     */
    public Long addUserProfile(UserProfile userProfile) {
        return userProfileDao.addUserProfile(userProfile);
    }

    /**
     * 根据id删除一个UserProfile
     *
     * @param id 要删除的id
     */
    public void deleteUserProfileById(Long id) {
        userProfileDao.deleteUserProfileById(id);
    }

    /**
     * 修改UserProfile
     *
     * @param userProfile 要修改的UserProfile
     */
    public void updateUserProfile(UserProfile userProfile) {
        userProfileDao.updateUserProfile(userProfile);
    }

    /**
     * 根据userId获取单个UserProfile对象
     *
     * @param userId 要查询的userId
     * @return List<UserProfile>
     */
    public List<UserProfile> getUserProfileByUserId(Long userId) {
        return userProfileDao.getUserProfileByUserId(userId);
    }

    /**
     * 根据条件获取UserProfile列表
     *
     * @param userProfile 查询条件
     * @return List<UserProfile>
     */
    public List<UserProfile> getUserProfileList(UserProfile userProfile) {
        return userProfileDao.getUserProfileList(userProfile);
    }

    /**
     * 第三方注册
     *
     * @param photo
     * @param cusName,昵称
     * @param request
     * @param response
     * @param appType
     * @param oauthlogin 唯一的key
     * @return
     * @throws Exception
     */
    public String addOpenAppRegister(String photo, String cusName, HttpServletRequest request, HttpServletResponse response, ProfileType appType, String oauthlogin) throws Exception {
        String pwd = genericRandomPwd();
        User user = new User();
        user.setPassword(pwd);
        user.setNickname(cusName);
        //生成邮箱
        String uid = guidGeneratorService.gainCode("REG", false);
        user.setEmail(uid + appType.toString().toLowerCase());
        user.setMobile(uid);
        if ("QQ".equals(appType.toString())) {
            user.setRegisterFrom(UserExpandFrom.qqFrom.toString());
        } else if ("WEIXIN".equals(appType.toString())) {
            user.setRegisterFrom(UserExpandFrom.weixinFrom.toString());
        } else if ("SINA".equals(appType.toString())) {
            user.setRegisterFrom(UserExpandFrom.weiboFrom.toString());
        }
        Long userId = userService.addUser(user);
        if (userId != 0) {
            UserProfile userProfile = new UserProfile();
            userProfile.setProfiledate(new Date());
            userProfile.setProfiletype(appType.toString());
            userProfile.setUserid(userId);
            userProfile.setValue(oauthlogin);
            userProfile.setName(cusName);
            addUserProfile(userProfile);
            // 注册送积分
            userIntegralService.addUserIntegral(IntegralKeyword.register.toString(), userId, 0L, 0L, "");
            // 注册时发送系统消息
            Map<String, Object> websiteMap = websiteProfileService.getWebsiteProfileByType(WebSiteProfileType.web.toString());
            Map<String, Object> web = (Map<String, Object>) websiteMap.get("web");
            String company = web.get("company").toString();

            String conent = "欢迎来到" + company + ",希望您能够快乐的学习";
            msgReceiveService.addSystemMessageByCusId(conent, userId);

        }
        // 执行登录操作

        //userService.setLoginStatus(user, "true", request,response);
        return user.getId() + "";
    }

    /**
     * 生成随机密码
     *
     * @return
     */
    public String genericRandomPwd() {
        StringBuffer ranPwd = new StringBuffer("");
        Random ran = new Random();
        int location = 0;
        for (int i = 0; i < 9; i++) {
            location = ran.nextInt(10);
            ranPwd.append(location);
        }
        return ranPwd.toString();
    }
}