package com.atdld.os.user.dao.impl;

import com.atdld.os.common.exception.StaleObjectStateException;
import com.atdld.os.dao.impl.common.GenericDaoImpl;
import com.atdld.os.entity.PageEntity;
import com.atdld.os.user.dao.UserAccountDao;
import com.atdld.os.user.entity.User;
import com.atdld.os.user.entity.UserAccount;
import com.atdld.os.user.entity.UserAccountDTO;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * UserAccount
 * User:
 * Date: 2014-05-27
 */
 @Repository("userAccountDao")
public class UserAccountDaoImpl extends GenericDaoImpl implements UserAccountDao {

    public Long addUserAccount(UserAccount userAccount) {
        return this.insert("UserAccountMapper.createUserAccount",userAccount);
    }

    public void updateUserAccount(UserAccount userAccount) throws StaleObjectStateException {
       Long c= this.update("UserAccountMapper.updateUserAccount",userAccount);
       if(c.longValue()==0){
           throw new StaleObjectStateException(StaleObjectStateException.OPTIMISTIC_LOCK_ERROR);
       }
    }

    public UserAccount getUserAccountByUserId(Long userId) {
        return this.selectOne("UserAccountMapper.getUserAccountByUserId",userId);
    }

    public List<UserAccount> getUserAccountList(UserAccount userAccount) {
        return this.selectList("UserAccountMapper.getUserAccountList",userAccount);
    }
    @Override
    public List<UserAccountDTO> getuserAccountListByCondition(User user, PageEntity pageEntity) {
    	// TODO Auto-generated method stub
    	return this.queryForListPage("UserAccountMapper.getuserAccountListByCondition",user,pageEntity);
    }
    /**
	 * 更新账户状态
	 * 
	 * @param userId
	 * @param status
	 */
    public void updateUserAccountStatus(Long userId, String status) {
    	Map<String,Object> map = new HashMap<String,Object>();
    	map.put("userId", userId);
    	map.put("status", status);
    	this.update("UserAccountMapper.updateUserAccountStatus", map);
    }
    /**
	 * 根据用户id获得详情
	 * 
	 * @param userId
	 * @return
	 */
    public UserAccountDTO getuserAccountInfo(Long userId) {
    	return this.selectOne("UserAccountMapper.getuserAccountInfo", userId);
    }
}
