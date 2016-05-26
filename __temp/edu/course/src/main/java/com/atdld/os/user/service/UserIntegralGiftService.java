package com.atdld.os.user.service;

import com.atdld.os.entity.PageEntity;
import com.atdld.os.user.entity.UserIntegralGift;

import java.util.List;


/**
 * UserIntegralGift管理接口 User:  Date: 2014-09-24
 */
public interface UserIntegralGiftService {

    /**
     * 添加UserIntegralGift
     *
     * @param userIntegralGift 要添加的UserIntegralGift
     * @return id
     */
    Long addUserIntegralGift(UserIntegralGift userIntegralGift);

    /**
     * 根据id删除一个UserIntegralGift
     *
     * @param id 要删除的id
     */
    void deleteUserIntegralGiftById(Long id);

    /**
     * 修改UserIntegralGift
     *
     * @param userIntegralGift 要修改的UserIntegralGift
     */
    void updateUserIntegralGift(UserIntegralGift userIntegralGift);

    /**
     * 根据id获取单个UserIntegralGift对象
     *
     * @param id 要查询的id
     * @return UserIntegralGift
     */
    UserIntegralGift getUserIntegralGiftById(Long id);

    /**
     * 根据id获取单个UserIntegralGift对象
     *
     * @param id 要查询的id
     * @return UserIntegralGift
     */
    UserIntegralGift getUserIntegralAndCourseGiftById(Long id);

    /**
     * 根据条件获取UserIntegralGift列表
     *
     * @param userIntegralGift 查询条件
     * @return List<UserIntegralGift>
     */
    List<UserIntegralGift> getUserIntegralGiftList(UserIntegralGift userIntegralGift);

    /**
     * 查询礼品列表
     *
     * @param userIntegralGift
     * @param page
     * @return
     */
    List<UserIntegralGift> getUserIntegralGiftListPage(UserIntegralGift userIntegralGift, PageEntity page);

    /**
     * 获得用户礼品
     *
     * @param userId
     * @param page
     * @return
     */
    List<UserIntegralGift> getIntegralGiftListByUserId(Long userId, PageEntity page);
}