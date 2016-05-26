package io.wangxiao.edu.home.service.user;

import io.wangxiao.edu.home.entity.user.UserLevel;

import java.util.List;

public interface UserLevelService {

    /**
     * 添加UserLevel
     *
     * @param userLevel 要添加的UserLevel
     * @return id
     */
    java.lang.Long addUserLevel(UserLevel userLevel);

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
     * @return
     */
    List<UserLevel> getUserLevelList();
}