package io.wangxiao.edu.home.dao.user;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.edu.common.exception.StaleObjectStateException;
import io.wangxiao.edu.home.entity.user.User;
import io.wangxiao.edu.home.entity.user.UserAccount;
import io.wangxiao.edu.home.entity.user.UserAccountDTO;

import java.util.List;

public interface UserAccountDao {

    /**
     * 添加UserAccount
     *
     * @param userAccount 要添加的UserAccount
     * @return id
     */
    java.lang.Long addUserAccount(UserAccount userAccount);

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