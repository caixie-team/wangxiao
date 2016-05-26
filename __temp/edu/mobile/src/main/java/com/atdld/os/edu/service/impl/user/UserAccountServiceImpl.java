package com.atdld.os.edu.service.impl.user;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import com.atdld.os.core.service.gain.GuidGeneratorService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atdld.os.common.exception.AccountException;
import com.atdld.os.common.exception.StaleObjectStateException;
import com.atdld.os.core.entity.PageEntity;
import com.atdld.os.core.util.ObjectUtils;
import com.atdld.os.edu.constants.enums.AccountBizType;
import com.atdld.os.edu.constants.enums.AccountHistoryType;
import com.atdld.os.edu.constants.enums.AccountType;
import com.atdld.os.edu.dao.user.UserAccountDao;
import com.atdld.os.edu.dao.user.UserAccountOptRecordDao;
import com.atdld.os.edu.dao.user.UserAccounthistoryDao;
import com.atdld.os.edu.entity.user.User;
import com.atdld.os.edu.entity.user.UserAccount;
import com.atdld.os.edu.entity.user.UserAccountDTO;
import com.atdld.os.edu.entity.user.UserAccounthistory;
import com.atdld.os.edu.service.user.UserAccountService;
import com.atdld.os.edu.service.user.UserService;

/**
 * UserAccount管理接口 User:  Date: 2014-05-27
 */
@Service("userAccountService")
public class UserAccountServiceImpl implements UserAccountService {

	private Logger logger = LoggerFactory.getLogger(UserAccountServiceImpl.class);

	@Autowired
	private UserAccountDao userAccountDao;
	@Autowired
	private UserAccounthistoryDao userAccounthistoryDao;
	@Autowired
	private GuidGeneratorService guidGeneratorService;
	@Autowired
	private UserAccountOptRecordDao userAccountOptRecordDao;
	@Autowired
	private UserService userService;

	/**
	 * 添加UserAccount
	 * 
	 * @param userAccount
	 *            要添加的UserAccount
	 * @return id
	 */
	public java.lang.Long addUserAccount(UserAccount userAccount) {
		return userAccountDao.addUserAccount(userAccount);
	}

	/**
	 * 根据id获取单个UserAccount对象
	 * 
	 * @param userId
	 *            要查询的id
	 * @return UserAccount
	 */
	public UserAccount getUserAccountByUserId(Long userId) throws AccountException {
		UserAccount account = userAccountDao.getUserAccountByUserId(userId);
		if (ObjectUtils.isNull(account)) {
			throw new AccountException(AccountException.ACCOUNT_NOT_FOUND);// 账户不存在
		}
		return account;
	}

	@Override
	public List<UserAccountDTO> getUserAccountListByCondition(PageEntity page, User user) throws StaleObjectStateException {
		return userAccountDao.getuserAccountListByCondition(user, page);
	}

	/**
	 * 根据条件获取UserAccount列表
	 * 
	 * @param userAccount
	 *            查询条件
	 * @return List<UserAccount>
	 */
	public List<UserAccount> getUserAccountList(UserAccount userAccount) {
		return userAccountDao.getUserAccountList(userAccount);
	}

	// 扣款,消费操作
	public UserAccount debit(UserAccount account, BigDecimal trxAmount, AccountHistoryType accountHistoryType, Long userId, Long trxOrderId, String requestId, Date trxDate, String description,
			boolean isDisplay, AccountBizType accountBizType) throws AccountException, StaleObjectStateException {

		
		BigDecimal curBalance = account.getBalance();
		if (curBalance.compareTo(trxAmount) < 0)
			throw new AccountException(AccountException.ACCOUNT_NOT_ENOUGH);
		try {
			logger.info("debit befor:======balance:" + curBalance + "=====trxAmount:" + trxAmount + "============");
			account.debit(trxAmount);// 扣除操作,cash ,vm具体在此方法中
		} catch (IllegalArgumentException e) {
			throw new AccountException(e.getMessage());
		}

		// 更新账户dao
		userAccountDao.updateUserAccount(account);
		// 添加账户历史操作记录
		UserAccounthistory userAccounthistory = new UserAccounthistory();
		userAccounthistory.setUserId(userId);
		userAccounthistory.setTrxorderId(trxOrderId);// 订单id
		userAccounthistory.setAccountId(account.getId());// 相关账户ID
		userAccounthistory.setRequestId(requestId);
		userAccounthistory.setCreateTime(new Date());
		if (isDisplay) {// 是否显示账户历史记录:0显示;1不显示
			userAccounthistory.setIsDisplay(0);
		} else {
			userAccounthistory.setIsDisplay(1);
		}
		userAccounthistory.setBalance(account.getBalance());// 当前余额
		userAccounthistory.setCashAmount(account.getCashAmount());// cash余额
		userAccounthistory.setVmAmount(account.getVmAmount());// vm余额
		userAccounthistory.setTrxAmount(trxAmount);// 交易金额
		userAccounthistory.setDescription("消费金额" + trxAmount);// 账户历史内容描述
		userAccounthistory.setActHistoryType(accountHistoryType.toString());// 帐务历史类型.充值。消费等
		userAccounthistory.setBizType(accountBizType.toString());// 业务类型(课程订单、会员订单、图书订单)
		userAccounthistory.setVersion(1L);// 乐观锁版本号
		userAccounthistoryDao.addUserAccounthistory(userAccounthistory);

		logger.info("debit SUCCESS:======balance:" + account.getBalance() + "=====trxAmount:" + trxAmount + "============");
		return account;
	}

	/**
	 * 账户入账,充值 入款
	 * 
	 * @param account
	 * @param trxAmount
	 *            交易金额
	 * @param accountType
	 *            账户操作类型，cash or vm
	 * @param accountHistoryType
	 *            账户历史记录类型
	 * @param userId
	 *            用户id
	 * @param trxOrderId
	 *            订单号,订单表主键id
	 * @param requestId
	 *            订单号,对系统的的
	 * @param out_trade_no
	 *            提交到支付宝的,(充值卡是用卡号)
	 * @param trxDate
	 *            时间
	 * @param description
	 *            描述
	 * @param isDisplay
	 *            在用户我的账户历史中是否显示 0显示 1不显示
	 * @param accountBizType
	 *            交易类型，课程、会员、图书等
	 * @throws AccountException
	 * @throws StaleObjectStateException
	 */

	public UserAccount credit(UserAccount account, BigDecimal trxAmount, AccountType accountType, AccountHistoryType accountHistoryType, Long userId, Long trxOrderId, String requestId,
			String out_trade_no, Date trxDate, String description, boolean isDisplay, AccountBizType accountBizType) throws AccountException, StaleObjectStateException {
		BigDecimal curBalance = account.getBalance();
		try {
			logger.info("credit befor:======balance:" + curBalance + "=====trxAmount:" + trxAmount + "============");
			account.credit(trxAmount, accountType);
		} catch (IllegalArgumentException e) {
			throw new AccountException(e.getMessage());
		}
		// 每次充值都有out_trade_no，防止重复充值做查询判断 //TODO
		// 查询账户历史是否用out_trade_no，如果有了。抛出异常
		UserAccounthistory userAccounthistory = userAccounthistoryDao.getUserAccounthistoryByOutTtradeNo(out_trade_no);
		if (userAccounthistory != null) {
			throw new StaleObjectStateException(StaleObjectStateException.OPTIMISTIC_OUTTRADENO_EXIT);
		}
		// 更新账户dao
		account.setLastUpdateTime(new Date());
		userAccountDao.updateUserAccount(account);
		// 添加账户历史操作记录
		userAccounthistory = new UserAccounthistory();
		userAccounthistory.setUserId(userId);
		userAccounthistory.setTrxorderId(trxOrderId);// 订单id
		userAccounthistory.setAccountId(account.getId());// 相关账户ID
		userAccounthistory.setCreateTime(new Date());
		userAccounthistory.setRequestId(requestId);// 系统内订单编号
		userAccounthistory.setOutTradeNo(out_trade_no);// 对支付宝订单号
		if (isDisplay) {// 是否显示账户历史记录:0显示;1不显示
			userAccounthistory.setIsDisplay(0);
		} else {
			userAccounthistory.setIsDisplay(1);
		}
		userAccounthistory.setBalance(account.getBalance());// 当前余额
		userAccounthistory.setCashAmount(account.getCashAmount());// cash余额
		userAccounthistory.setVmAmount(account.getVmAmount());// vm余额
		userAccounthistory.setTrxAmount(trxAmount);// 交易金额
		userAccounthistory.setDescription("充值金额" + trxAmount);// 账户历史内容描述
		userAccounthistory.setActHistoryType(accountHistoryType.toString());// 帐务历史类型.充值。消费等
		userAccounthistory.setBizType(accountBizType.toString());// 业务类型(课程订单、会员订单、图书订单)
		userAccounthistory.setVersion(1L);// 乐观锁版本号
		userAccounthistoryDao.addUserAccounthistory(userAccounthistory);
		logger.info("credit SUCCESS:======balance:" + account.getBalance() + "=====trxAmount:" + trxAmount + "============");
		return account;
	}

	/**
	 * 更新账户状态
	 * 
	 * @param userId
	 * @param status
	 */
	public void updateUserAccountStatus(Long userId, String status) {
		userAccountDao.updateUserAccountStatus(userId, status);
	}

	/**
	 * 根据用户id获得详情
	 * 
	 * @param userId
	 * @return
	 */
	public UserAccountDTO getuserAccountInfo(Long userId) {
		return userAccountDao.getuserAccountInfo(userId);
	}

	

	
}