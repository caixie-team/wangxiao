package com.atdld.os.user.dao;

import com.atdld.os.entity.PageEntity;
import com.atdld.os.user.entity.UserFeedback;

import java.util.List;


/**
 * UserFeedback管理接口
 * User:
 * Date: 2014-10-15
 */
public interface UserFeedbackDao {

    /**
     * 添加UserFeedback
     *
     * @param userFeedback 要添加的UserFeedback
     * @return id
     */
    public Long addUserFeedback(UserFeedback userFeedback);

    /**
     * 根据id删除一个UserFeedback
     *
     * @param id 要删除的id
     */
    public void deleteUserFeedbackById(Long id);

    /**
     * 修改UserFeedback
     *
     * @param userFeedback 要修改的UserFeedback
     */
    public void updateUserFeedback(UserFeedback userFeedback);

    /**
     * 根据id获取单个UserFeedback对象
     *
     * @param id 要查询的id
     * @return UserFeedback
     */
    public UserFeedback getUserFeedbackById(Long id);

    /**
     * 根据条件获取UserFeedback列表
     *
     * @param userFeedback 查询条件
     * @return List<UserFeedback>
     */
    public List<UserFeedback> getUserFeedbackList(UserFeedback userFeedback);


    /**
     * 根据条件获取UserFeedback列表
     *
     * @param userFeedback 查询条件
     * @return List<UserFeedback>
     */
    public List<UserFeedback> getUserFeedbackListByCondtion(UserFeedback userFeedback, PageEntity page);
}