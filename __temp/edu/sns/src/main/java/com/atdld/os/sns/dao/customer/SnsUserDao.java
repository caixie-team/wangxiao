package com.atdld.os.sns.dao.customer;



import java.util.List;

import com.atdld.os.core.entity.PageEntity;
import com.atdld.os.sns.entity.customer.SnsUserExpandDto;
import com.atdld.os.sns.entity.friend.Friend;

/**
 * UserExpand管理接口 User:  Date: 2014-01-10
 */
public interface SnsUserDao {

	
	/**
	 * 查询我关注的用户的列表
	 * 
	 * @param cusAttention
	 *            传入cusId
	 * @param page
	 *            分页参数
	 * @return List<Customer> 我关注的用户的 list
	 */
	public List<SnsUserExpandDto> queryMyAttentionCustomer(Friend friend, PageEntity page);

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
	 * 查询当前用户是否跟传来的userIds是好友关系，返回是好友的列表
	 * 
	 * @param userIds
	 * @param cusId
	 * @return
	 */
	public List<SnsUserExpandDto> queryIsFrirndByIds(List<Long> userIds, Long cusId);

	
}