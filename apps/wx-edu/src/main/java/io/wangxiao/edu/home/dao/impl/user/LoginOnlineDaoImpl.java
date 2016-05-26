package io.wangxiao.edu.home.dao.impl.user;

import io.wangxiao.commons.dao.impl.GenericDaoImpl;
import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.edu.home.dao.user.LoginOnlineDao;
import io.wangxiao.edu.home.entity.user.LoginOnline;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("loginOnlineDao")
public class LoginOnlineDaoImpl extends GenericDaoImpl implements LoginOnlineDao {

    public java.lang.Long addLoginOnline(LoginOnline loginOnline) {
        return this.insert("LoginOnlineMapper.createLoginOnline", loginOnline);
    }

    public void deleteLoginOnlineById(Long id) {
        this.delete("LoginOnlineMapper.deleteLoginOnlineById", id);
    }

    public java.lang.Long updateLoginOnline(LoginOnline loginOnline) {
        return this.update("LoginOnlineMapper.updateLoginOnline", loginOnline);
    }

    public LoginOnline getLoginOnlineById(Long id) {
        return this.selectOne("LoginOnlineMapper.getLoginOnlineById", id);
    }

    public List<LoginOnline> getLoginOnlineList(LoginOnline loginOnline, PageEntity pageEntity) {
        return this.queryForListPage("LoginOnlineMapper.getLoginOnlineList", loginOnline, pageEntity);
    }
}
