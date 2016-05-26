package io.wangxiao.edu.home.dao.user;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.edu.home.entity.user.UserMobileMsg;

import java.util.Date;
import java.util.List;

public interface UserMobileMsgDao {
    /**
     * 添加发送用户短信记录
     *
     * @param userMobileMsg
     * @return
     */
    Long addUserMobileMsg(List<UserMobileMsg> userMobileMsgList);

    /**
     * 查询记录
     *
     * @param userMobileMsg
     * @param page
     * @return
     */
    List<UserMobileMsg> queryUserMobileMsgList(UserMobileMsg userMobileMsg,
                                               PageEntity page);

    /**
     * 获得单个记录
     *
     * @param id
     * @return
     */
    UserMobileMsg queryUserMobileMsgById(Long id);

    /**
     * 查询和当前时间相等的短信记录 发送
     *
     * @param userMobileMsg
     * @return
     */
    List<UserMobileMsg> queryNowMobileMsgList(Date nowDate);

    /**
     * 修改短信发送状态
     */
    void updateMsgStatus(Long id);

    /**
     * 修改短信
     *
     * @param userMobileMsg
     */
    void updateUserMobileMsg(UserMobileMsg userMobileMsg);

    /**
     * 删除短信
     */
    void delUserMobileMsg(Long id);
}
