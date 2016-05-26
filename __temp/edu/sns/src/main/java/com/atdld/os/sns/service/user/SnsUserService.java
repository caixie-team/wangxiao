package com.atdld.os.sns.service.user;



import java.util.List;
import java.util.Map;

import com.atdld.os.core.entity.PageEntity;
import com.atdld.os.sns.entity.course.Course;
import com.atdld.os.sns.entity.customer.SnsUserExpandDto;
import com.atdld.os.sns.entity.friend.Friend;

/**
 * @author :
 * @ClassName com.atdld.os.sns.service.impl.common.CommonServiceImpl
 * @description 博客serviceImpl
 * @Create Date : 2013-12-30 上午10:27:07
 */

public interface SnsUserService{

  
    /**
     * 用户详细信息
     * @param cusId
     * @return
     */
    public SnsUserExpandDto getUserExpandByCusId(Long cusId);
    /**
     * 批量用户详细信息
     * @param cusId
     * @return
     */
    public Map<String, SnsUserExpandDto> getUserExpandsByCusId(String cusIds);
	
	 /**
     * 查询当前用户是否跟传来的userIds是好友关系，返回是好友的列表
     * @param userIds
     * @param cusId
     * @return
     */
    public List<SnsUserExpandDto> queryIsFrirndByIds(List<Long> userIds,Long cusId);
    
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
	public Map<String, Object> queryAllCustomer(SnsUserExpandDto customer, PageEntity page) throws Exception;

	/**
	 * 通过用户id 查询该用户的好友列表
	 * 
	 * @param friend
	 *            传入cusId 用户id
	 * @param page
	 *            分页参数
	 * @return List<QueryCustomer>
	 * @throws Exception
	 */
	public List<SnsUserExpandDto> queryMyAttentionCustomer(Friend friend, PageEntity page) throws Exception;
	/**
	 * 通过showname 查询customer(精确搜索)
	 */
	public List<SnsUserExpandDto> queryCustomerByShowNameEquals(String showName);
	
	/**
     * 验证用户是否操作频繁
     * 
     * @param type
     *            验证的类型
     * @param cus_Id
     * @return
     */
    public boolean checkLimitOpt(String type, Long cus_Id);

    /**
     * 增加用户操作次数
     * 
     * @param type
     *            验证的类型
     * @param cus_Id
     * @return
     */
    public void customerOptLimitCountAdd(String type, Long cus_Id);
    
    /**
	 * 查询我的粉丝用户的列表
	 * 
	 * @param cusAttention
	 *            cusAttentionId传入
	 * @param page
	 *            分页参数
	 * @return List<QueryCustomer> 查询我的粉丝用户列表
	 */
	public List<SnsUserExpandDto> queryMyFans(Friend friend, PageEntity page);
	/**
	 * 通过showname 查询customer
	 * 
	 * @param showName
	 */
	public List<SnsUserExpandDto> queryCustomerByShowName(String showName, int size);
	/**
	 * 查询推荐课程
	 * @param recommendId
	 * @return
	 */
	public Map<String, List<Course>> getCourseListByHomePage(Long recommendId);
}