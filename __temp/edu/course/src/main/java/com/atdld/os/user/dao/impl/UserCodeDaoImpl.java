package com.atdld.os.user.dao.impl;

import com.atdld.os.dao.impl.common.GenericDaoImpl;
import com.atdld.os.user.dao.UserCodeDao;
import com.atdld.os.user.entity.UserCode;
import org.springframework.stereotype.Repository;

/**
 * UserCode
 * User:
 * Date: 2014-09-15
 */
@Repository("userCodeDao")
public class UserCodeDaoImpl extends GenericDaoImpl implements UserCodeDao {

    public Long addUserCode(UserCode userCode) {
        return this.insert("UserCodeMapper.createUserCode", userCode);
    }

    public void deleteUserCodeById(Long id) {
        this.delete("UserCodeMapper.deleteUserCodeById", id);
    }

    public void updateUserCode(UserCode userCode) {
        this.update("UserCodeMapper.updateUserCode", userCode);
    }

    public UserCode getUserCodeById(Long id) {
        return this.selectOne("UserCodeMapper.getUserCodeById", id);
    }

}
