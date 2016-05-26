package com.atdld.os.user.dao;

import com.atdld.os.entity.PageEntity;
import com.atdld.os.user.entity.UserLoginLog;

import java.util.List;


/**
 * UserLoginLog管理接口 User:  Date: 2014-10-12
 */
public interface UserLoginLogDao {

    /**
     * 添加UserLoginLog
     *
     * @param userLoginLog 要添加的UserLoginLog
     * @return id
     */
    public Long addUserLoginLog(UserLoginLog userLoginLog);

    /**
     * 修改UserLoginLog
     *
     * @param userLoginLog 要修改的UserLoginLog
     */
    public void updateUserLoginLog(UserLoginLog userLoginLog);

    /**
     * 根据id获取单个UserLoginLog对象
     *
     * @param id 要查询的id
     * @return UserLoginLog
     */
    public UserLoginLog getUserLoginLogById(Long id);

    /**
     * 根据条件获取UserLoginLog列表
     *
     * @return List<UserLoginLog>
     */
    public List<UserLoginLog> getUserLoginLogList();

    /**
     * 查询登陆log前台
     *
     * @param userLoginLog
     * @param page
     * @return
     */
    public List<UserLoginLog> getUserLoginLogListPage(UserLoginLog userLoginLog, PageEntity page);
}