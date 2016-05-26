package com.atdld.os.user.dao.impl;

import com.atdld.os.dao.impl.common.GenericDaoImpl;
import com.atdld.os.entity.PageEntity;
import com.atdld.os.user.dao.UserAccounthistoryDao;
import com.atdld.os.user.entity.QueryUserAccounthistory;
import com.atdld.os.user.entity.UserAccounthistory;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 *
 * UserAccounthistory
 * User:
 * Date: 2014-05-27
 */
 @Repository("userAccounthistoryDao")
public class UserAccounthistoryDaoImpl extends GenericDaoImpl implements UserAccounthistoryDao {

    public Long addUserAccounthistory(UserAccounthistory userAccounthistory) {
        return this.insert("UserAccounthistoryMapper.createUserAccounthistory",userAccounthistory);
    }

    public UserAccounthistory getUserAccounthistoryById(Long id) {
        return this.selectOne("UserAccounthistoryMapper.getUserAccounthistoryById",id);
    }

    public List<UserAccounthistory> getUserAccounthistoryList(UserAccounthistory userAccounthistory) {
        return this.selectList("UserAccounthistoryMapper.getUserAccounthistoryList",userAccounthistory);
    }
  
    public List<UserAccounthistory> getUserAccounthistoryListByCondition(QueryUserAccounthistory queryUserAccounthistory, PageEntity page) {
    	return this.queryForListPage("UserAccounthistoryMapper.getUserAccounthistoryListByCondition", queryUserAccounthistory, page);
    }

	public UserAccounthistory getUserAccounthistoryByOutTtradeNo( String outTradeNo) {
		
		return this.selectOne("UserAccounthistoryMapper.getUserAccounthistoryByOutTtradeNo",outTradeNo);
	}
	public List<UserAccounthistory> getWebUserAccounthistoryListByCondition(QueryUserAccounthistory queryUserAccounthistory, PageEntity page) {
		
		return this.queryForListPage("UserAccounthistoryMapper.getWebUserAccounthistoryListByCondition", queryUserAccounthistory, page);
	}
}
