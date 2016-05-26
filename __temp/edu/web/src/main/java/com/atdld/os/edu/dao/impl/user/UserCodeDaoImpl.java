package com.atdld.os.edu.dao.impl.user;

import java.util.List;
import com.atdld.os.edu.entity.user.UserCode;
import com.atdld.os.edu.dao.user.UserCodeDao;
import org.springframework.stereotype.Repository;
import com.atdld.os.core.dao.impl.common.GenericDaoImpl;

/**
 *
 * UserCode
 * User:
 * Date: 2014-09-15
 */
 @Repository("userCodeDao")
public class UserCodeDaoImpl extends GenericDaoImpl implements UserCodeDao{

    public java.lang.Long addUserCode(UserCode userCode) {
        return this.insert("UserCodeMapper.createUserCode",userCode);
    }

    public void deleteUserCodeById(Long id){
        this.delete("UserCodeMapper.deleteUserCodeById",id);
    }

    public void updateUserCode(UserCode userCode) {
        this.update("UserCodeMapper.updateUserCode",userCode);
    }

    public UserCode getUserCodeById(Long id) {
        return this.selectOne("UserCodeMapper.getUserCodeById",id);
    }
    
}
