package com.atdld.os.edu.service.impl.userprofile;

import com.atdld.os.common.service.WebHessianService;
import com.atdld.os.core.service.gain.GuidGeneratorService;
import com.atdld.os.edu.constants.enums.IntegralKeyword;
import com.atdld.os.edu.constants.enums.UserExpandFrom;
import com.atdld.os.edu.constants.enums.WebSiteProfileType;
import com.atdld.os.edu.dao.userprofile.UserProfileDao;
import com.atdld.os.edu.entity.user.User;
import com.atdld.os.edu.entity.userprofile.ProfileType;
import com.atdld.os.edu.entity.userprofile.UserProfile;
import com.atdld.os.edu.service.letter.MsgReceiveService;
import com.atdld.os.edu.service.user.UserIntegralService;
import com.atdld.os.edu.service.user.UserService;
import com.atdld.os.edu.service.userprofile.UserProfileService;
import com.atdld.os.edu.service.website.WebsiteProfileService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * UserProfile管理接口 User:  Date: 2014-01-13
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
    @Autowired
    private WebHessianService webHessianService;
   
    /**
     * 添加UserProfile
     * 
     * @param userProfile
     *            要添加的UserProfile
     * @return id
     */
    public Long addUserProfile(UserProfile userProfile) {
        return userProfileDao.addUserProfile(userProfile);
    }

    /**
     * 根据id删除一个UserProfile
     * 
     * @param id
     *            要删除的id
     */
    public void deleteUserProfileById(Long id) {
        userProfileDao.deleteUserProfileById(id);
    }

    /**
     * 修改UserProfile
     * 
     * @param userProfile
     *            要修改的UserProfile
     */
    public void updateUserProfile(UserProfile userProfile) {
        userProfileDao.updateUserProfile(userProfile);
    }

    /**
     * 根据userId获取单个UserProfile对象
     * 
     * @param userId
     *            要查询的userId
     * @return List<UserProfile>
     */
    public List<UserProfile> getUserProfileByUserId(Long userId) {
        return userProfileDao.getUserProfileByUserId(userId);
    }

    /**
     * 根据条件获取UserProfile列表
     * 
     * @param userProfile
     *            查询条件
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
		String pwd=genericRandomPwd();
		User user = new User();
		user.setPassword(pwd);
		user.setNickname(cusName);
		//生成邮箱
		String uid= guidGeneratorService.gainCode("REG",false);
		user.setEmail(uid +appType.toString().toLowerCase());
        user.setMobile(uid);
        Map<String,String> userMap=new HashMap<String, String>();
        userMap.put("password", pwd);
		userMap.put("cusName",cusName);
		userMap.put("email", uid +appType.toString().toLowerCase());
		userMap.put("mobile", uid);
		userMap.put("registerFrom", UserExpandFrom.OpenAppRegisterFrom.toString());//账号来源是第三方注册
		Long userid=webHessianService.mobileAddUser(userMap);
		user.setId(userid);
		if(userid!=0){
			UserProfile userProfile = new UserProfile();
			userProfile.setProfiledate(new Date());
			userProfile.setProfiletype(appType.toString());
			userProfile.setUserid(userid);
			userProfile.setValue(oauthlogin);
			userProfile.setName(cusName);
			addUserProfile(userProfile);
			// 注册送积分
			Map<String,String> integralMap=new HashMap<String, String>();
            integralMap.put("keyWord",IntegralKeyword.register.toString() );
            integralMap.put("userId",userid+"" );
            integralMap.put("other",0L+"" );
            integralMap.put("fromUserId",0L+"" );
            integralMap.put("otherScorel","" );
            webHessianService.addUserIntegral(integralMap);
			// 注册时发送系统消息
            Map<String,Object> websitemap=websiteProfileService.getWebsiteProfileByType(WebSiteProfileType.web.toString());
            Map<String,Object> web=(Map<String,Object>)websitemap.get("web");
            String company=web.get("company").toString();

			String conent = "欢迎来到"+company+",希望您能够快乐的学习";
			webHessianService.mobileAddSystemMessageByCusId(conent, userid);
			
		}
		// 执行登录操作
		
        userService.setLoginStatus(user, "true", request,response);
		return null;
	}
	/**
	 * 生成随机密码
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