package io.wangxiao.edu.home.dao.impl.user;

import io.wangxiao.commons.dao.impl.GenericDaoImpl;
import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.edu.home.dao.user.UserLoginLogDao;
import io.wangxiao.edu.home.entity.user.UserLoginLog;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository("userLoginLogDao")
public class UserLoginLogDaoImpl extends GenericDaoImpl implements UserLoginLogDao {

    public java.lang.Long addUserLoginLog(UserLoginLog userLoginLog) {
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
