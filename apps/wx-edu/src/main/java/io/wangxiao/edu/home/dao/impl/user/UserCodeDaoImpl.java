package io.wangxiao.edu.home.dao.impl.user;

import io.wangxiao.commons.dao.impl.GenericDaoImpl;
import io.wangxiao.edu.home.dao.user.UserCodeDao;
import io.wangxiao.edu.home.entity.user.UserCode;
import org.springframework.stereotype.Repository;

@Repository("userCodeDao")
public class UserCodeDaoImpl extends GenericDaoImpl implements UserCodeDao {

    public java.lang.Long addUserCode(UserCode userCode) {
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
