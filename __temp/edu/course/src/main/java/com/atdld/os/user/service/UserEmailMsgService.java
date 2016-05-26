package com.atdld.os.user.service;


import com.atdld.os.entity.PageEntity;
import com.atdld.os.user.entity.UserEmailMsg;

import java.util.List;

/**
 * @author :
 * @ClassName com.atdld.os.edu.service.user.UserEmailMsgService
 * @description
 * @Create Date : 2015年1月6日 下午10:00:56
 */
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
    void batchSendEmail(String[] mailto, String text, String title, int num);
}
