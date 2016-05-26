package io.wangxiao.edu.home.dao.impl.user;

import io.wangxiao.commons.dao.impl.GenericDaoImpl;
import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.edu.home.dao.user.UserAccounthistoryDao;
import io.wangxiao.edu.home.entity.user.QueryUserAccounthistory;
import io.wangxiao.edu.home.entity.user.UserAccounthistory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("userAccounthistoryDao")
public class UserAccounthistoryDaoImpl extends GenericDaoImpl implements UserAccounthistoryDao {

    public java.lang.Long addUserAccounthistory(UserAccounthistory userAccounthistory) {
        return this.insert("UserAccounthistoryMapper.createUserAccounthistory", userAccounthistory);
    }

    public UserAccounthistory getUserAccounthistoryById(Long id) {
        return this.selectOne("UserAccounthistoryMapper.getUserAccounthistoryById", id);
    }

    public List<UserAccounthistory> getUserAccounthistoryList(UserAccounthistory userAccounthistory) {
        return this.selectList("UserAccounthistoryMapper.getUserAccounthistoryList", userAccounthistory);
    }

    public List<UserAccounthistory> getUserAccounthistoryListByCondition(QueryUserAccounthistory queryUserAccounthistory, PageEntity page) {
        return this.queryForListPage("UserAccounthistoryMapper.getUserAccounthistoryListByCondition", queryUserAccounthistory, page);
    }

    public UserAccounthistory getUserAccounthistoryByOutTtradeNo(String outTradeNo) {

        return this.selectOne("UserAccounthistoryMapper.getUserAccounthistoryByOutTtradeNo", outTradeNo);
    }

    public List<UserAccounthistory> getWebUserAccounthistoryListByCondition(QueryUserAccounthistory queryUserAccounthistory, PageEntity page) {

        return this.queryForListPage("UserAccounthistoryMapper.getWebUserAccounthistoryListByCondition", queryUserAccounthistory, page);
    }
}
