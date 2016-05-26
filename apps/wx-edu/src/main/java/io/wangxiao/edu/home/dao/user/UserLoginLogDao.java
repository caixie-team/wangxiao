package io.wangxiao.edu.home.dao.user;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.edu.home.entity.user.UserLoginLog;

import java.util.List;

public interface UserLoginLogDao {

    /**
     * 添加UserLoginLog
     *
     * @param userLoginLog 要添加的UserLoginLog
     * @return id
     */
    java.lang.Long addUserLoginLog(UserLoginLog userLoginLog);

    /**
     * 修改UserLoginLog
     *
     * @param userLoginLog 要修改的UserLoginLog
     */
    void updateUserLoginLog(UserLoginLog userLoginLog);

    /**
     * 根据id获取单个UserLoginLog对象
     *
     * @param id 要查询的id
     * @return UserLoginLog
     */
    UserLoginLog getUserLoginLogById(Long id);

    /**
     * 根据条件获取UserLoginLog列表
     *
     * @param userLoginLog 查询条件
     * @return List<UserLoginLog>
     */
    List<UserLoginLog> getUserLoginLogList();

    /**
     * 查询登陆log前台
     *
     * @param userLoginLog
     * @param page
     * @return
     */
    List<UserLoginLog> getUserLoginLogListPage(UserLoginLog userLoginLog, PageEntity page);
}