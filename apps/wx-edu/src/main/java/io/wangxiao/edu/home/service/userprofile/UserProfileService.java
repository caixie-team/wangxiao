package io.wangxiao.edu.home.service.userprofile;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.wangxiao.edu.home.entity.userprofile.ProfileType;
import io.wangxiao.edu.home.entity.userprofile.UserProfile;

public interface UserProfileService {

    /**
     * 添加UserProfile
     *
     * @param userProfile 要添加的UserProfile
     * @return id
     */
    Long addUserProfile(UserProfile userProfile);

    /**
     * 根据id删除一个UserProfile
     *
     * @param id 要删除的id
     */
    void deleteUserProfileById(Long id);

    /**
     * 修改UserProfile
     *
     * @param userProfile 要修改的UserProfile
     */
    void updateUserProfile(UserProfile userProfile);

    /**
     * 根据id获取单个UserProfile对象
     *
     * @param id 要查询的id
     * @return List<UserProfile>
     */
    List<UserProfile> getUserProfileByUserId(Long userId);

    /**
     * 根据条件获取UserProfile列表
     *
     * @param userProfile 查询条件
     * @return List<UserProfile>
     */
    List<UserProfile> getUserProfileList(UserProfile userProfile);

    /**
     * 第三方注册
     *
     * @param photo
     * @param cusName
     * @param request
     * @param response
     * @param appType
     * @param oauthlogin
     * @return
     * @throws Exception
     */
    String addOpenAppRegister(String photo, String cusName, HttpServletRequest request, HttpServletResponse response, ProfileType appType, String oauthlogin) throws Exception;
}