package com.atdld.os.user.dao.impl;

import com.atdld.os.dao.impl.common.GenericDaoImpl;
import com.atdld.os.entity.PageEntity;
import com.atdld.os.user.dao.UserEmailMsgDao;
import com.atdld.os.user.entity.UserEmailMsg;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author :
 * @ClassName com.atdld.os.edu.dao.impl.user.UserEmailMsgDaoImpl
 * @description
 * @Create Date : 2015年1月6日 下午
 */
@Repository("userEmailDao")
public class UserEmailMsgDaoImpl extends GenericDaoImpl implements UserEmailMsgDao {

    /**
     * 添加发送用户邮箱记录
     */
    public Long addUserEmailMsg(List<UserEmailMsg> userEmailMsgList) {
        return this.insert("UserEmailMsgMapper.addUserEmailMsg", userEmailMsgList);
    }

    /**
     * 查询记录
     *
     * @param userEmailMsg
     * @param page
     * @return
     */
    public List<UserEmailMsg> queryUserEmailMsgList(UserEmailMsg userEmailMsg,
                                                    PageEntity page) {
        return this.queryForListPage("UserEmailMsgMapper.queryUserEmailMsgList", userEmailMsg, page);
    }

    /**
     * 按条件查询邮箱记录
     */
    public List<UserEmailMsg> queryUserEmailList(UserEmailMsg userEmailMsg) {
        return this.selectList("UserEmailMsgMapper.queryUserEmailList", userEmailMsg);
    }

    /**
     * 更新邮件为已发送
     */
    public void updateUserEmailStatus(UserEmailMsg userEmailMsg) {
        this.update("UserEmailMsgMapper.updateUserEmailStatus", userEmailMsg);
    }

    /**
     * 获得单个记录
     */
    public UserEmailMsg queryUserEmailMsgById(Long id) {
        return this.selectOne("UserEmailMsgMapper.queryUserEmailMsgById", id);
    }

    /**
     * 更新 UserEmailMsg
     */
    public void updateUserEmailMsgById(UserEmailMsg userEmailMsg) {
        this.update("UserEmailMsgMapper.updateUserEmailMsgById", userEmailMsg);
    }

    /**
     * 删除发送邮件记录
     */
    public void delUserEmailMsgById(Long id) {
        this.delete("UserEmailMsgMapper.delUserEmailMsgById", id);
    }
}
