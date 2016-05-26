package com.atdld.os.user.dao.impl;

import com.atdld.os.dao.impl.common.GenericDaoImpl;
import com.atdld.os.user.dao.UserLevelDao;
import com.atdld.os.user.entity.UserLevel;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * UserLevel
 * User:
 * Date: 2014-05-27
 */
 @Repository("userLevelDao")
public class UserLevelDaoImpl extends GenericDaoImpl implements UserLevelDao {
     /**
	 * 添加UserLevel
	 * 
	 * @param userLevel
	 *            要添加的UserLevel
	 * @return id
	 */
    public Long addUserLevel(UserLevel userLevel) {
        return this.insert("UserLevelMapper.createUserLevel",userLevel);
    }
    /**
	 * 根据id删除一个UserLevel
	 * 
	 * @param id
	 *            要删除的id
	 */
    public void deleteUserLevelById(Long id){
        this.delete("UserLevelMapper.deleteUserLevelById",id);
    }
    /**
	 * 修改UserLevel
	 * 
	 * @param userLevel
	 *            要修改的UserLevel
	 */
    public void updateUserLevel(UserLevel userLevel) {
        this.update("UserLevelMapper.updateUserLevel",userLevel);
    }
    /**
	 * 根据id获取单个UserLevel对象
	 * 
	 * @param id
	 *            要查询的id
	 * @return UserLevel
	 */
    public UserLevel getUserLevelById(Long id) {
        return this.selectOne("UserLevelMapper.getUserLevelById",id);
    }
    /**
	 * 根据条件获取UserLevel列表
	 * 
	 * @param userLevel
	 *            查询条件
	 * @return List<UserLevel>
	 */
    public List<UserLevel> getUserLevelList() {
        return this.selectList("UserLevelMapper.getUserLevelList",null);
    }
}
