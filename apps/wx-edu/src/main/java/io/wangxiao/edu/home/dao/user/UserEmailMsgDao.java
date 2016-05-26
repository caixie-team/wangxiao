package io.wangxiao.edu.home.dao.user;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.edu.home.entity.user.UserEmailMsg;

import java.util.List;

public interface UserEmailMsgDao {
    /**
     * 添加发送用户邮箱记录
     *
     * @param userEmailMsg
     * @return
     */
    Long addUserEmailMsg(List<UserEmailMsg> userEmailMsgList);

    /**
     * 查询记录
     *
     * @param userEmailMsg
     * @param page
     * @return
     */
    List<UserEmailMsg> queryUserEmailMsgList(UserEmailMsg userEmailMsg,
                                             PageEntity page);

    /**
     * 按条件查询邮箱记录
     */
    List<UserEmailMsg> queryUserEmailList(UserEmailMsg userEmailMsg);

    /**
     * 更新邮件为已发送
     */
    void updateUserEmailStatus(UserEmailMsg userEmailMsg);

    /**
     * 获得单个记录
     *
     * @param id
     * @return
     */
    UserEmailMsg queryUserEmailMsgById(Long id);

    /**
     * 更新 UserEmailMsg
     */
    void updateUserEmailMsgById(UserEmailMsg userEmailMsg);

    /**
     * 删除发送邮件记录
     */
    void delUserEmailMsgById(Long id);
}
