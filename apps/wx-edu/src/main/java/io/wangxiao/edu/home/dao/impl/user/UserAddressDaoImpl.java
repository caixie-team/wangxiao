package io.wangxiao.edu.home.dao.impl.user;

import io.wangxiao.commons.dao.impl.GenericDaoImpl;
import io.wangxiao.edu.home.dao.user.UserAddressDao;
import io.wangxiao.edu.home.entity.user.UserAddress;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("userAddressDao")
public class UserAddressDaoImpl extends GenericDaoImpl implements UserAddressDao {

    public java.lang.Long addUserAddress(UserAddress userAddress) {
        return this.insert("UserAddressMapper.createUserAddress", userAddress);
    }

    public void deleteUserAddressById(Long id) {
        this.delete("UserAddressMapper.deleteUserAddressById", id);
    }

    /**
     * 根据id或用户id更新isFirst
     */
    public void updateUserAddressisFirst(UserAddress userAddress) {
        this.update("UserAddressMapper.updateUserAddressisFirst", userAddress);
    }

    public void updateUserAddress(UserAddress userAddress) {
        this.update("UserAddressMapper.updateUserAddress", userAddress);
    }

    public UserAddress getUserAddressById(Long id) {
        return this.selectOne("UserAddressMapper.getUserAddressById", id);
    }

    public List<UserAddress> getUserAddressList(UserAddress userAddress) {
        return this.selectList("UserAddressMapper.getUserAddressList", userAddress);
    }
}
