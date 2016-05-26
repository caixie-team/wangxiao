package com.atdld.os.edu.dao.impl.user;

import java.util.List;

import com.atdld.os.edu.entity.user.UserFeedback;
import com.atdld.os.edu.dao.user.UserFeedbackDao;

import org.springframework.stereotype.Repository;

import com.atdld.os.core.dao.impl.common.GenericDaoImpl;
import com.atdld.os.core.entity.PageEntity;

/**
 *
 * UserFeedback
 * User:
 * Date: 2014-10-15
 */
 @Repository("userFeedbackDao")
public class UserFeedbackDaoImpl extends GenericDaoImpl implements UserFeedbackDao{

    public java.lang.Long addUserFeedback(UserFeedback userFeedback) {
        return this.insert("UserFeedbackMapper.createUserFeedback",userFeedback);
    }

    public void deleteUserFeedbackById(Long id){
        this.delete("UserFeedbackMapper.deleteUserFeedbackById",id);
    }

    public void updateUserFeedback(UserFeedback userFeedback) {
        this.update("UserFeedbackMapper.updateUserFeedback",userFeedback);
    }

    public UserFeedback getUserFeedbackById(Long id) {
        return this.selectOne("UserFeedbackMapper.getUserFeedbackById",id);
    }

    public List<UserFeedback> getUserFeedbackList(UserFeedback userFeedback) {
/*        return this.selectList("UserFeedbackMapper.getUserFeedbackList",userFeedback);*/
    	return null;
    }
    @Override
    public List<UserFeedback> getUserFeedbackListByCondtion(UserFeedback userFeedback, PageEntity page) {
    	// TODO Auto-generated method stub
    	return this.queryForListPage("UserFeedbackMapper.getUserFeedbackList", userFeedback, page);
    }
}
