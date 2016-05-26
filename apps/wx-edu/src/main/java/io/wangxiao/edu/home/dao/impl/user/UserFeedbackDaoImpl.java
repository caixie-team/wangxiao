package io.wangxiao.edu.home.dao.impl.user;

import io.wangxiao.commons.dao.impl.GenericDaoImpl;
import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.edu.home.dao.user.UserFeedbackDao;
import io.wangxiao.edu.home.entity.user.UserFeedback;
import io.wangxiao.edu.home.entity.user.UserFeedbacks;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository("userFeedbackDao")
public class UserFeedbackDaoImpl extends GenericDaoImpl implements UserFeedbackDao {

    public java.lang.Long addUserFeedback(UserFeedback userFeedback) {
        return this.insert("UserFeedbackMapper.createUserFeedback", userFeedback);
    }

    public void deleteUserFeedbackById(Long id) {
        this.delete("UserFeedbackMapper.deleteUserFeedbackById", id);
    }

    public void updateUserFeedback(UserFeedback userFeedback) {
        this.update("UserFeedbackMapper.updateUserFeedback", userFeedback);
    }

    public UserFeedback getUserFeedbackById(Long id) {
        return this.selectOne("UserFeedbackMapper.getUserFeedbackById", id);
    }

    public List<UserFeedback> getUserFeedbackList(UserFeedback userFeedback) {
/*        return this.selectList("UserFeedbackMapper.getUserFeedbackList",userFeedback);*/
        return null;
    }

    @Override
    public List<UserFeedback> getUserFeedbackListByCondtion(UserFeedbacks userFeedback, PageEntity page) {
        return this.queryForListPage("UserFeedbackMapper.getUserFeedbackList", userFeedback, page);
    }
}
