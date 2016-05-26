package io.wangxiao.edu.home.dao.impl.userprofile;

import io.wangxiao.commons.dao.impl.GenericDaoImpl;
import io.wangxiao.edu.home.dao.userprofile.UserProfileDao;
import io.wangxiao.edu.home.entity.userprofile.UserProfile;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("userProfileDao")
public class UserProfileDaoImpl extends GenericDaoImpl implements UserProfileDao {

    public Long addUserProfile(UserProfile userProfile) {
        return this.insert("UserProfileMapper.createUserProfile", userProfile);
    }

    public void deleteUserProfileById(Long id) {
        this.delete("UserProfileMapper.deleteUserProfileById", id);
    }

    public void updateUserProfile(UserProfile userProfile) {
        this.update("UserProfileMapper.updateUserProfile", userProfile);
    }

    public List<UserProfile> getUserProfileByUserId(Long userid) {
        return this.selectList("UserProfileMapper.getUserProfileByUserId", userid);
    }

    public List<UserProfile> getUserProfileList(UserProfile userProfile) {
        return this.selectList("UserProfileMapper.getUserProfileList", userProfile);
    }
}
