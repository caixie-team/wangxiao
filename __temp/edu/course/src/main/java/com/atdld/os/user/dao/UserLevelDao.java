package com.atdld.os.user.dao;

import com.atdld.os.user.entity.UserLevel;

import java.util.List;

/**
 * UserLevel管理接口 User:  Date: 2014-05-27
 */
public interface UserLevelDao {

    /**
     * 添加UserLevel
     *
     * @param userLevel 要添加的UserLevel
     * @return id
     */
    Long addUserLevel(UserLevel userLevel);

    /**
     * 根据id删除一个UserLevel
     *
     * @param id 要删除的id
     */
    void deleteUserLevelById(Long id);

    /**
     * 修改UserLevel
     *
     * @param userLevel 要修改的UserLevel
     */
    void updateUserLevel(UserLevel userLevel);

    /**
     * 根据id获取单个UserLevel对象
     *
     * @param id 要查询的id
     * @return UserLevel
     */
    UserLevel getUserLevelById(Long id);

    /**
     * 获取UserLevel列表
     *
     * @param userLevel
     * @return
     */
    List<UserLevel> getUserLevelList();

}