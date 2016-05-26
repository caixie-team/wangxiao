package io.wangxiao.edu.home.service.user;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.edu.home.entity.user.UserEmailMsg;

import java.util.List;

public interface UserEmailMsgService {
    /**
     * 添加发送用户邮件记录
     *
     * @return
     */
    void addUserEmailMsg(List<UserEmailMsg> userEmailMsgList);

    /**
     * 发送邮件和短信定时service
     */
    void queryTimingSendEmailMsg() throws Exception;

    /**
     * 查询记录
     *
     * @param userEmailMsg
     * @param page
     * @return
     */
    List<UserEmailMsg> queryUserEmailMsgList(UserEmailMsg userEmailMsg, PageEntity page);

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

    /**
     * 起四个线程批量发送邮件
     */
    void batchSendEmail(java.lang.String[] mailto, java.lang.String text, java.lang.String title, int num);
}
