package com.atdld.os.user.dao.impl;

import com.atdld.os.dao.impl.common.GenericDaoImpl;
import com.atdld.os.entity.PageEntity;
import com.atdld.os.user.dao.UserIntegralGiftDao;
import com.atdld.os.user.entity.UserIntegralGift;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * UserIntegralGift
 * User:
 * Date: 2014-09-24
 */
@Repository("userIntegralGiftDao")
public class UserIntegralGiftDaoImpl extends GenericDaoImpl implements UserIntegralGiftDao {
    /**
     * 添加UserIntegralGift
     *
     * @param userIntegralGift 要添加的UserIntegralGift
     * @return id
     */
    public Long addUserIntegralGift(UserIntegralGift userIntegralGift) {
        return this.insert("UserIntegralGiftMapper.createUserIntegralGift", userIntegralGift);
    }

    /**
     * 根据id删除一个UserIntegralGift
     *
     * @param id 要删除的id
     */
    public void deleteUserIntegralGiftById(Long id) {
        this.delete("UserIntegralGiftMapper.deleteUserIntegralGiftById", id);
    }

    /**
     * 根据id获取单个UserIntegralGift对象
     *
     * @param id 要查询的id
     * @return UserIntegralGift
     */
    public void updateUserIntegralGift(UserIntegralGift userIntegralGift) {
        this.update("UserIntegralGiftMapper.updateUserIntegralGift", userIntegralGift);
    }

    /**
     * 根据id获取单个UserIntegralGift对象
     *
     * @param id 要查询的id
     * @return UserIntegralGift
     */
    public UserIntegralGift getUserIntegralGiftById(Long id) {
        return this.selectOne("UserIntegralGiftMapper.getUserIntegralGiftById", id);
    }

    /**
     * 根据id获取单个UserIntegralGift对象
     *
     * @param id 要查询的id
     * @return UserIntegralGift
     */
    public UserIntegralGift getUserIntegralAndCourseGiftById(Long id) {
        return this.selectOne("UserIntegralGiftMapper.getUserIntegralAndCourseGiftById", id);
    }

    /**
     * 根据条件获取UserIntegralGift列表
     *
     * @param userIntegralGift 查询条件
     * @return List<UserIntegralGift>
     */
    public List<UserIntegralGift> getUserIntegralGiftList(UserIntegralGift userIntegralGift) {
        return this.selectList("UserIntegralGiftMapper.getUserIntegralGiftList", userIntegralGift);
    }

    /**
     * 查询礼品列表
     *
     * @param userIntegralGift
     * @param page
     * @return
     */
    public List<UserIntegralGift> getUserIntegralGiftListPage(UserIntegralGift userIntegralGift, PageEntity page) {
        return this.queryForListPage("UserIntegralGiftMapper.getUserIntegralGiftListPage", userIntegralGift, page);
    }

    /**
     * 获得用户礼品
     *
     * @param userId
     * @param page
     * @return
     */
    public List<UserIntegralGift> getIntegralGiftListByUserId(Long userId, PageEntity page) {
        return this.queryForListPage("UserIntegralGiftMapper.getIntegralGiftListByUserId", userId, page);
    }
}
