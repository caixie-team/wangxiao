package com.atdld.os.edu.dao.impl.user;

import java.util.List;
import com.atdld.os.edu.entity.user.UserAddress;
import com.atdld.os.edu.dao.user.UserAddressDao;
import org.springframework.stereotype.Repository;
import com.atdld.os.core.dao.impl.common.GenericDaoImpl;

/**
 *
 * UserAddress
 * User:
 * Date: 2014-05-27
 */
 @Repository("userAddressDao")
public class UserAddressDaoImpl extends GenericDaoImpl implements UserAddressDao{

    public java.lang.Long addUserAddress(UserAddress userAddress) {
        return this.insert("UserAddressMapper.createUserAddress",userAddress);
    }

    public void deleteUserAddressById(Long id){
        this.delete("UserAddressMapper.deleteUserAddressById",id);
    }
    /**
     * 根据id或用户id更新isFirst
     */
    public void updateUserAddressisFirst(UserAddress userAddress){
    	this.update("UserAddressMapper.updateUserAddressisFirst",userAddress);
    }
    public void updateUserAddress(UserAddress userAddress) {
        this.update("UserAddressMapper.updateUserAddress",userAddress);
    }

    public UserAddress getUserAddressById(Long id) {
        return this.selectOne("UserAddressMapper.getUserAddressById",id);
    }

    public List<UserAddress> getUserAddressList(UserAddress userAddress) {
        return this.selectList("UserAddressMapper.getUserAddressList",userAddress);
    }
}
