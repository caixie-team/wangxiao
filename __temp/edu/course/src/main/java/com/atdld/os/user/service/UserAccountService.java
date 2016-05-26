package com.atdld.os.user.service;

import com.atdld.os.common.exception.AccountException;
import com.atdld.os.common.exception.StaleObjectStateException;
import com.atdld.os.entity.PageEntity;
import com.atdld.os.user.constants.enums.AccountBizType;
import com.atdld.os.user.constants.enums.AccountHistoryType;
import com.atdld.os.user.constants.enums.AccountType;
import com.atdld.os.user.entity.User;
import com.atdld.os.user.entity.UserAccount;
import com.atdld.os.user.entity.UserAccountDTO;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * UserAccount管理接口 User:  Date: 2014-05-27
 */
public interface UserAccountService {

    /**
     * 添加UserAccount
     *
     * @param userAccount 要添加的UserAccount
     * @return id
     */
    Long addUserAccount(UserAccount userAccount);

    /**
     * 根据id获取单个UserAccount对象
     *
     * @param id 要查询的id
     * @return UserAccount
     */
    UserAccount getUserAccountByUserId(Long userId) throws AccountException;

    /**
     * 根据条件获取UserAccount列表
     *
     * @param userAccount 查询条件
     * @return List<UserAccount>
     */
    List<UserAccount> getUserAccountList(UserAccount userAccount);

    List<UserAccountDTO> getUserAccountListByCondition(PageEntity page, User user) throws StaleObjectStateException;

    /**
     * 账户扣款，消费，出账
     *
     * @param account
     * @param trxAmount          交易金额
     * @param accountType        账户操作类型，cash or vm
     * @param accountHistoryType 账户历史记录类型
     * @param userId             用户id
     * @param trxOrderId         订单号,订单表主键id
     * @param requestId          订单号,对外的
     * @param trxDate            时间
     * @param description        描述
     * @param isDisplay          在用户我的账户历史中是否显示
     * @param accountBizType     交易类型，课程、会员、图书等
     * @throws AccountException
     * @throws AccountException
     * @throws StaleObjectStateException
     */
    UserAccount debit(UserAccount account, BigDecimal trxAmount, AccountHistoryType accountHistoryType, Long userId, Long trxOrderId, String requestId, Date trxDate, String description,
                      boolean isDisplay, AccountBizType accountBizType) throws AccountException, StaleObjectStateException;

    /**
     * 账户入账,充值
     *
     * @param account
     * @param trxAmount          交易金额
     * @param accountType        账户操作类型，cash or vm
     * @param accountHistoryType 账户历史记录类型
     * @param userId             用户id
     * @param trxOrderId         订单号,订单表主键id
     * @param requestId          订单号,对系统的的
     * @param out_trade_no       提交到支付宝的
     * @param trxDate            时间
     * @param description        描述
     * @param isDisplay          在用户我的账户历史中是否显示 0显示 1不显示
     * @param accountBizType     交易类型，课程、会员、图书等
     * @throws AccountException
     * @throws StaleObjectStateException
     */
    UserAccount credit(UserAccount account, BigDecimal trxAmount, AccountType accountType, AccountHistoryType accountHistoryType, Long userId, Long trxOrderId, String requestId,
                       String out_trade_no, Date trxDate, String description, boolean isDisplay, AccountBizType accountBizType) throws AccountException, StaleObjectStateException;

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

    /**
     * 根据用户进行账户充值 /扣钱（后台操作）
     *
     * @param userId  用户id
     * @param balance 交易金额
     * @param flag    充值 扣款标示
     * @param sysUser 操作人
     * @return
     * @throws StaleObjectStateException
     * @throws AccountException
     */
//    public boolean gainUserRechargeAmount(SysUser sysUser, Long userId, BigDecimal balance, String flag) throws AccountException, StaleObjectStateException;

}