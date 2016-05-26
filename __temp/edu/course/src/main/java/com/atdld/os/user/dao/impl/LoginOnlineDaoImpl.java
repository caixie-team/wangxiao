package com.atdld.os.user.dao.impl;

import com.atdld.os.dao.impl.common.GenericDaoImpl;
import com.atdld.os.entity.PageEntity;
import com.atdld.os.user.dao.LoginOnlineDao;
import com.atdld.os.user.entity.LoginOnline;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * LoginOnline
 * User:
 * Date: 2014-11-18
 */
@Repository("loginOnlineDao")
public class LoginOnlineDaoImpl extends GenericDaoImpl implements LoginOnlineDao {

    public Long addLoginOnline(LoginOnline loginOnline) {
        return this.insert("LoginOnlineMapper.createLoginOnline", loginOnline);
    }

    public void deleteLoginOnlineById(Long id) {
        this.delete("LoginOnlineMapper.deleteLoginOnlineById", id);
    }

    public Long updateLoginOnline(LoginOnline loginOnline) {
        return this.update("LoginOnlineMapper.updateLoginOnline", loginOnline);
    }

    public LoginOnline getLoginOnlineById(Long id) {
        return this.selectOne("LoginOnlineMapper.getLoginOnlineById", id);
    }

    public List<LoginOnline> getLoginOnlineList(LoginOnline loginOnline, PageEntity pageEntity) {
        return this.queryForListPage("LoginOnlineMapper.getLoginOnlineList", loginOnline, pageEntity);
    }
}
