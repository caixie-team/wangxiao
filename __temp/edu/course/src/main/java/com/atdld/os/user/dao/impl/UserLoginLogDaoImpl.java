package com.atdld.os.user.dao.impl;

import com.atdld.os.dao.impl.common.GenericDaoImpl;
import com.atdld.os.entity.PageEntity;
import com.atdld.os.user.dao.UserLoginLogDao;
import com.atdld.os.user.entity.UserLoginLog;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * UserLoginLog
 * User:
 * Date: 2014-10-12
 */
@Repository("userLoginLogDao")
public class UserLoginLogDaoImpl extends GenericDaoImpl implements UserLoginLogDao {

    public Long addUserLoginLog(UserLoginLog userLoginLog) {
        return this.insert("UserLoginLogMapper.createUserLoginLog", userLoginLog);
    }

    public void updateUserLoginLog(UserLoginLog userLoginLog) {
        this.update("UserLoginLogMapper.updateUserLoginLog", userLoginLog);
    }

    public UserLoginLog getUserLoginLogById(Long id) {
        return this.selectOne("UserLoginLogMapper.getUserLoginLogById", id);
    }

    public List<UserLoginLog> getUserLoginLogList() {
        return this.selectList("UserLoginLogMapper.getUserLoginLogList", null);
    }

    /**
     * 查询登陆log前台
     *
     * @param userLoginLog
     * @param page
     * @return
     */
    public List<UserLoginLog> getUserLoginLogListPage(UserLoginLog userLoginLog, PageEntity page) {
        return this.queryForListPage("UserLoginLogMapper.getUserLoginLogListPage", userLoginLog, page);
    }
}
