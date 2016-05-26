package com.atdld.os.user.dao;

import com.atdld.os.user.entity.UserCode;

/**
 * UserCode管理接口
 * User:
 * Date: 2014-09-15
 */
public interface UserCodeDao {

    /**
     * 添加UserCode
     *
     * @param userCode 要添加的UserCode
     * @return id
     */
    Long addUserCode(UserCode userCode);

    /**
     * 根据id删除一个UserCode
     *
     * @param id 要删除的id
     */
    void deleteUserCodeById(Long id);

    /**
     * 修改UserCode
     *
     * @param userCode 要修改的UserCode
     */
    void updateUserCode(UserCode userCode);

    /**
     * 根据id获取单个UserCode对象
     *
     * @param id 要查询的id
     * @return UserCode
     */
    UserCode getUserCodeById(Long id);

}