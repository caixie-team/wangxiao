package io.wangxiao.edu.home.service.user;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.edu.home.entity.user.UserFeedback;
import io.wangxiao.edu.home.entity.user.UserFeedbacks;

import java.util.List;

/**
 * UserFeedback管理接口
 */
public interface UserFeedbackService {

    /**
     * 添加UserFeedback
     *
     * @param userFeedback 要添加的UserFeedback
     * @return id
     */
    java.lang.Long addUserFeedback(UserFeedback userFeedback);

    /**
     * 根据id删除一个UserFeedback
     *
     * @param id 要删除的id
     */
    void deleteUserFeedbackById(Long id);

    /**
     * 修改UserFeedback
     *
     * @param userFeedback 要修改的UserFeedback
     */
    void updateUserFeedback(UserFeedback userFeedback);

    /**
     * 根据id获取单个UserFeedback对象
     *
     * @param id 要查询的id
     * @return UserFeedback
     */
    UserFeedback getUserFeedbackById(Long id);

    /**
     * 根据条件获取UserFeedback列表
     *
     * @param userFeedback 查询条件
     * @return List<UserFeedback>
     */
    List<UserFeedback> getUserFeedbackList(UserFeedback userFeedback);


    /**
     * 根据条件获取UserFeedback列表
     *
     * @param userFeedback 查询条件
     * @return List<UserFeedback>
     */
    List<UserFeedback> getUserFeedbackListCondtion(UserFeedbacks userFeedback, PageEntity page);


}