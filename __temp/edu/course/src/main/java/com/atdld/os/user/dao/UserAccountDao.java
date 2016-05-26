package com.atdld.os.user.dao;

import com.atdld.os.common.exception.StaleObjectStateException;
import com.atdld.os.entity.PageEntity;
import com.atdld.os.user.entity.User;
import com.atdld.os.user.entity.UserAccount;
import com.atdld.os.user.entity.UserAccountDTO;

import java.util.List;

/**
 * UserAccount管理接口 User:  Date: 2014-05-27
 */
public interface UserAccountDao {

    /**
     * 添加UserAccount
     *
     * @param userAccount 要添加的UserAccount
     * @return id
     */
    Long addUserAccount(UserAccount userAccount);

    /**
     * 修改UserAccount
     *
     * @param userAccount 要修改的UserAccount
     */
    void updateUserAccount(UserAccount userAccount) throws StaleObjectStateException;

    /**
     * 根据id获取单个UserAccount对象
     *
     * @param id 要查询的id
     * @return UserAccount
     */
    UserAccount getUserAccountByUserId(Long userId);

    /**
     * 根据条件获取UserAccount列表
     *
     * @param userAccount 查询条件
     * @return List<UserAccount>
     */
    List<UserAccount> getUserAccountList(UserAccount userAccount);

    /**
     * 获取用户账户信息
     *
     * @param pageEntity
     * @return
     */
    List<UserAccountDTO> getuserAccountListByCondition(User user, PageEntity pageEntity) throws StaleObjectStateException;

    /**
     * 更新账户状态
     *
     * @param userId
     * @param status
     */
    void updateUserAccountStatus(Long userId, String status);

    /**
     * 根据用户id获得详情
     *
     * @param userId
     * @return
     */
    UserAccountDTO getuserAccountInfo(Long userId);
}