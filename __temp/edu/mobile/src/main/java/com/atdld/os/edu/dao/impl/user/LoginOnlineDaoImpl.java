package com.atdld.os.edu.dao.impl.user;

import java.util.List;

import com.atdld.os.core.entity.PageEntity;
import com.atdld.os.edu.entity.user.LoginOnline;
import com.atdld.os.edu.dao.user.LoginOnlineDao;
import org.springframework.stereotype.Repository;
import com.atdld.os.core.dao.impl.common.GenericDaoImpl;

/**
 *
 * LoginOnline
 * User:
 * Date: 2014-11-18
 */
 @Repository("loginOnlineDao")
public class LoginOnlineDaoImpl extends GenericDaoImpl implements LoginOnlineDao{

    public java.lang.Long addLoginOnline(LoginOnline loginOnline) {
        return this.insert("LoginOnlineMapper.createLoginOnline",loginOnline);
    }

    public void deleteLoginOnlineById(Long id){
        this.delete("LoginOnlineMapper.deleteLoginOnlineById",id);
    }

    public java.lang.Long updateLoginOnline(LoginOnline loginOnline) {
        return this.update("LoginOnlineMapper.updateLoginOnline",loginOnline);
    }

    public LoginOnline getLoginOnlineById(Long id) {
        return this.selectOne("LoginOnlineMapper.getLoginOnlineById",id);
    }

    public List<LoginOnline> getLoginOnlineList(LoginOnline loginOnline,PageEntity pageEntity) {
        return this.queryForListPage("LoginOnlineMapper.getLoginOnlineList", loginOnline,pageEntity);
    }
}
