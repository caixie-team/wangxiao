package io.wangxiao.edu.home.service.impl.user;

import io.wangxiao.commons.util.ObjectUtils;
import io.wangxiao.edu.home.dao.user.UserAddressDao;
import io.wangxiao.edu.home.entity.user.UserAddress;
import io.wangxiao.edu.home.entity.user.UserArea;
import io.wangxiao.edu.home.service.user.UserAddressService;
import io.wangxiao.edu.home.service.user.UserAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * UserAddress管理接口
 */
@Service("userAddressService")
public class UserAddressServiceImpl implements UserAddressService {

    @Autowired
    private UserAddressDao userAddressDao;

    /**
     * 添加UserAddress
     *
     * @param userAddress 要添加的UserAddress
     * @return id
     */
    public java.lang.Long addUserAddress(UserAddress userAddress) {
        userAddress.setCreateTime(new Date());
        return userAddressDao.addUserAddress(userAddress);
    }

    /**
     * 根据id删除一个UserAddress
     *
     * @param id 要删除的id
     */
    public void deleteUserAddressById(Long id) {
        userAddressDao.deleteUserAddressById(id);
    }

    /**
     * 根据id设置常用地址
     */
    public void updateUserAddressById(Long id, Long userId) {
        //更新该用户下全部的地址为不常用地址
        UserAddress userAddress = new UserAddress();
        userAddress.setIsFirst(2);
        userAddress.setUserId(userId);
        userAddressDao.updateUserAddressisFirst(userAddress);
        //更新用户所选id为常用地址
        userAddress = new UserAddress();
        userAddress.setIsFirst(1);
        userAddress.setId(id);
        userAddressDao.updateUserAddressisFirst(userAddress);
    }

    /**
     * 修改UserAddress
     *
     * @param userAddress 要修改的UserAddress
     */
    public void updateUserAddress(UserAddress userAddress) {
        userAddressDao.updateUserAddress(userAddress);
    }

    /**
     * 根据id获取单个UserAddress对象
     *
     * @param id 要查询的id
     * @return UserAddress
     */
    public UserAddress getUserAddressById(Long id) {
        UserAddress address = userAddressDao.getUserAddressById(id);
        Map<Long, UserArea> map = userAreaService.getMapUserAreaList(new UserArea());
        UserArea areaC = map.get(address.getCityId());
        UserArea areaP = map.get(address.getProvinceId());
        UserArea areaT = map.get(address.getTownId());
        if (ObjectUtils.isNotNull(areaC)) {
            address.setCityStr(areaC.getAreaName());
        }
        if (ObjectUtils.isNotNull(areaP)) {
            address.setProvinceStr(areaP.getAreaName());
        }
        if (ObjectUtils.isNotNull(areaT)) {
            address.setTownStr(areaT.getAreaName());
        }
        return address;

    }

    @Autowired
    private UserAreaService userAreaService;

    /**
     * 根据条件获取UserAddress列表
     *
     * @param userAddress 查询条件
     * @return List<UserAddress>
     */
    public List<UserAddress> getUserAddressList(UserAddress userAddress) {
        Map<Long, UserArea> map = userAreaService.getMapUserAreaList(new UserArea());
        List<UserAddress> userAddressList = userAddressDao.getUserAddressList(userAddress);
        if (ObjectUtils.isNotNull(userAddressList)) {
            for (UserAddress address : userAddressList) {
                UserArea areaC = map.get(address.getCityId());
                UserArea areaP = map.get(address.getProvinceId());
                UserArea areaT = map.get(address.getTownId());
                if (ObjectUtils.isNotNull(areaC)) {
                    address.setCityStr(areaC.getAreaName());
                }
                if (ObjectUtils.isNotNull(areaP)) {
                    address.setProvinceStr(areaP.getAreaName());
                }
                if (ObjectUtils.isNotNull(areaT)) {
                    address.setTownStr(areaT.getAreaName());
                }
            }
        }
        return userAddressList;
    }
}