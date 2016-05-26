package com.atdld.os.common.service;


import java.util.Map;

import com.atdld.os.common.exception.AccountException;
import com.atdld.os.common.exception.StaleObjectStateException;


/**
 * Created by Administrator on 2014/12/25.
 */
public interface WebHessianService {

    /**
     * 修改用户最后考试的专业
     */
	public void updateUserExpandForSubject(Map<String,String> map);
	
    //调用项目专业接口-web
	
    /**
     * 查询用户信息
     * @param map
     * @return
     */
	public Map<String,String> getUserInfo(Long cusId);
	
	/**
     * 批量查询用户信息
     * @param map
     * @return
     */
	public Map<String,String> getUserInfoByUids(String ids);
	
	/**
	 * 添加积分
	 * @param map
	 */
	public void addUserIntegral(Map<String,String> map);
	
	/**
     * 修改CourseProfile浏览次数
     * @param map
     */
    public void updateCourseProfile(Map<String,String> map);

    /**
	 * 通过标识更新未读数加一或清零
	 */
	public void readMsgNumAddOrReset(String falg, Long cusId,String iType);
	
	/**
	 * 更新用户的上传统计系统消息时间
	 */
	public void updateCusForLST(Long cusId,Long date);
	
	/**
     * 修改UserExpand浏览次数
     * @param map
     */
    public void updateUserExpandCount(Map<String,String> map);
    
    
    /**
     * 查询全部好友
     * 
     * @param customer
     *            好友实体
     * @param page
     *            分页参数
     * @return List<QueryCustomer>
     * @throws Exception
     */
    public Map<String,String> queryAllCustomer(Map<String,String> map);

    
    /**
     * 通过showname 查询customer(精确搜索)
     */
    public Map<String,String> queryCustomerByShowNameEquals(String showName);


	/**
	 * 通过showname 查询customer
	 * 
	 * @param showName
	 */
	public Map<String,String> queryCustomerByShowName(String showName, int size);
	/**
	 * 首页获取推荐的课程 封装为map
	 * @param recommendId
	 * @return
	 * @throws Exception
	 */
    public Map<String,String> getCourseListByHomePage(Long recommendId);
    /**
     * 关键字过滤
     * @param str
     * @return
     */
    public String doFilter(String content);
    
    /**
     * 添加课程评论
     * @param map
     */
	public void mobileAddCourseAssess(Map<String,String> map);
	/**
	 * 添加反馈意见
	 * @param map
	 * @return
	 */
	public void mobileAddUserFeedback(Map<String,String> map);
	/**
	 * 修改用户信息
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public String mobileUpdateQueryUser(Map<String,String> map) throws Exception;
	/**
	 * 修改用户密码
	 * @param map
	 */
	public void mobileUpdatePwdById(Map<String,String> map);
	/**
	 * 删除播放历史
	 * @param ids
	 */
	public void mobileDelCourseStudyhistory(String ids);
	/**
	 * 添加播放记录
	 * @param courseStudyhistory
	 */
	public void mobilePlayertimes(Map<String,String> map);
	/**
     * 发送系统消息
     *
     * @param content 要发送的内容
     * @param cusId   用户id
     * @throws Exception
     */
    public String mobileAddSystemMessageByCusId(String content, Long cusId) throws Exception;
    
    /**
	 * 添加User
	 * 
	 * @param user
	 *            要添加的User
	 * @return id
	 */
	public Long mobileAddUser(Map<String,String> map);
	
	/**
     * 微站添加UserLoginLog
     * @param userLoginLog 要添加的UserLoginLog
     * @return id
     */
    public void mobileAddUserLoginLog(Map<String,String> map);
    /**
     * 微站添加LoginOnline
     * @param loginOnline 要添加的LoginOnline
     * @return id
     */
    public void mobileAddLoginOnline(Map<String,String> map);
    /**
     * 根据卡编码  修改   未使用  为  已使用  状态
     * @param card_code 
     */
    public void mobileUpdateCardStatusByCode(String cardCode);
    /**
     * 微站添加订单
     * @param map
     */
    public Long mobileAddTrxorder(Map<String,String> map);
    /**
     * 批量添加TrxorderDetail
     * @param List<trxorderDetail> 要添加的TrxorderDetail
     * @return id
     */
    public void mobileAddBatchTrxorderDetail(Map<String,String> map);
    /**
     * 更新订单状态为成功,网银的回调
     * @param trxorder
     */
    public void mobileUpdateTrxorderStatusSuccess(Map<String,String> map) throws StaleObjectStateException;
    
    /**
     * 账户扣款，消费，出账
     * @param map
     * @return
     * @throws AccountException
     * @throws StaleObjectStateException
     */
	public void mobiledebit(Map<String,String> map) throws AccountException, StaleObjectStateException;

	/**
	 * 账户入账,充值
	 * @param map
	 * @throws AccountException
	 * @throws StaleObjectStateException
	 */
	public void mobilecredit(Map<String,String> map) throws AccountException, StaleObjectStateException;
	
	/**
	 * 课程卡激活
	 * @param cardCode
	 * @param userId
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public String activationCard(Map<String, Object> map);

}
