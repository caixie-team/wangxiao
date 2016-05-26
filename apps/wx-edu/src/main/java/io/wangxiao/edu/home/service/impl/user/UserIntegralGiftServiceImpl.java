package io.wangxiao.edu.home.service.impl.user;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.edu.home.dao.user.UserIntegralGiftDao;
import io.wangxiao.edu.home.entity.user.UserIntegralGift;
import io.wangxiao.edu.home.service.user.UserIntegralGiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userIntegralGiftService")
public class UserIntegralGiftServiceImpl implements UserIntegralGiftService {

    @Autowired
    private UserIntegralGiftDao userIntegralGiftDao;

    /**
     * 添加UserIntegralGift
     *
     * @param userIntegralGift 要添加的UserIntegralGift
     * @return id
     */
    public java.lang.Long addUserIntegralGift(UserIntegralGift userIntegralGift) {
        return userIntegralGiftDao.addUserIntegralGift(userIntegralGift);
    }

    /**
     * 根据id删除一个UserIntegralGift
     *
     * @param id 要删除的id
     */
    public void deleteUserIntegralGiftById(Long id) {
        userIntegralGiftDao.deleteUserIntegralGiftById(id);
    }

    /**
     * 修改UserIntegralGift
     *
     * @param userIntegralGift 要修改的UserIntegralGift
     */
    public void updateUserIntegralGift(UserIntegralGift userIntegralGift) {
        userIntegralGiftDao.updateUserIntegralGift(userIntegralGift);
    }

    /**
     * 根据id获取单个UserIntegralGift对象
     *
     * @param id 要查询的id
     * @return UserIntegralGift
     */
    public UserIntegralGift getUserIntegralGiftById(Long id) {
        return userIntegralGiftDao.getUserIntegralGiftById(id);
    }

    /**
     * 根据id获取单个UserIntegralGift对象
     *
     * @param id 要查询的id
     * @return UserIntegralGift
     */
    public UserIntegralGift getUserIntegralAndCourseGiftById(Long id) {
        return userIntegralGiftDao.getUserIntegralAndCourseGiftById(id);
    }

    /**
     * 根据条件获取UserIntegralGift列表
     *
     * @param userIntegralGift 查询条件
     * @return List<UserIntegralGift>
     */
    public List<UserIntegralGift> getUserIntegralGiftList(UserIntegralGift userIntegralGift) {
        return userIntegralGiftDao.getUserIntegralGiftList(userIntegralGift);
    }

    /**
     * 查询礼品列表
     *
     * @param userIntegralGift
     * @param page
     * @return
     */
    public List<UserIntegralGift> getUserIntegralGiftListPage(UserIntegralGift userIntegralGift, PageEntity page) {
        return userIntegralGiftDao.getUserIntegralGiftListPage(userIntegralGift, page);
    }

    /**
     * 获得用户礼品
     *
     * @param userId
     * @param page
     * @return
     */
    public List<UserIntegralGift> getIntegralGiftListByUserId(Long userId, PageEntity page) {
        return userIntegralGiftDao.getIntegralGiftListByUserId(userId, page);
    }
}