package com.atdld.os.user.service;

import com.atdld.os.user.entity.UserAddress;

import java.util.List;

/**
 * UserAddress管理接口
 * User:
 * Date: 2014-05-27
 */
public interface UserAddressService {

    /**
     * 添加UserAddress
     *
     * @param userAddress 要添加的UserAddress
     * @return id
     */
    Long addUserAddress(UserAddress userAddress);

    /**
     * 根据id删除一个UserAddress
     *
     * @param id 要删除的id
     */
    void deleteUserAddressById(Long id);

    /**
     * 根据id设置常用地址
     */
    void updateUserAddressById(Long id, Long userId);

    /**
     * 修改UserAddress
     *
     * @param userAddress 要修改的UserAddress
     */
    void updateUserAddress(UserAddress userAddress);

    /**
     * 根据id获取单个UserAddress对象
     *
     * @param id 要查询的id
     * @return UserAddress
     */
    UserAddress getUserAddressById(Long id);

    /**
     * 根据条件获取UserAddress列表
     *
     * @param userAddress 查询条件
     * @return List<UserAddress>
     */
    List<UserAddress> getUserAddressList(UserAddress userAddress);
}