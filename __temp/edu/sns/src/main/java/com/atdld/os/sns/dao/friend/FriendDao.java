package com.atdld.os.sns.dao.friend;

import java.util.List;

import com.atdld.os.core.entity.PageEntity;
import com.atdld.os.sns.entity.customer.SnsUserExpandDto;
import com.atdld.os.sns.entity.friend.Friend;

/**
 * @author
 * @ClassName FriendDao
 * @package com.atdld.open.sns.dao.friend
 * @description 好友Dao接口
 * @Create Date: 2013-12-10 下午4:19:36
 */

public interface FriendDao {
	/**
	 * 添加好友
	 * 
	 * @param friend
	 *            好友实体
	 * @throws Exception
	 */
	public void addFriend(Friend friend) throws Exception;

	/**
	 * 查询我的好友
	 * 
	 * @param friend
	 *            好友实体 传入cusId
	 * @param page
	 *            分页参数
	 * @return List<QueryCustomer> 我的好友list
	 * @throws Exception
	 */
	public List<SnsUserExpandDto> queryMyFriend(Friend friend, PageEntity page) throws Exception;

	/**
	 * 查询我的好友的用id
	 * 
	 * @param Long
	 *            cusId 好友实体 传入cusId
	 * @return List<Long> 我的好友list
	 * @throws Exception
	 */
	public List<Long> queryMyFriend(Long cusId) throws Exception;

	/**
	 * 删除好友
	 * 
	 * @param friend
	 *            好友实体
	 */
	public Long delFriend(Friend friend);

	/**
	 * 删除全部好友
	 * 
	 * @param id
	 *            好友实体
	 * @throws Exception
	 */
	public Long delAllFriendByUid(Long id) throws Exception;

	/**
	 * 通过cusId和CusFriendId查询Friend
	 * 
	 * @param friend
	 *            传入cusId和CusFriendId
	 * @return Friend
	 * @throws Exception
	 */
	public Friend queryFriendByCusIdAndCusFriendId(Friend friend) throws Exception;

	/**
	 * 通过cusId和CusFriendId查询Friend
	 * 
	 * @param friend
	 *            传入cusId和CusFriendId
	 * @throws Exception
	 */
	public int queryFriendByCusIdAndCusFriendIdNum(Friend friend) throws Exception;

	/**
	 * 更新好友备注
	 * 
	 * @param friend
	 *            传入Remarks（备注） CusId（自己的id） CusFriendId（好友的id）
	 * @throws Exception
	 */
	public Long updateFriendForRemarksByCusIdAndCusFriendId(Friend friend) throws Exception;

	/**
	 * 获得好友列表
	 * 
	 * @param cusids
	 *            用户id
	 * @return
	 */
	public List<Friend> getCommonFriend(List<Long> cusids);

	/**
	 * 查询我加好友的用户的列表
	 * 
	 * @param cusAttention
	 * @return
	 * @throws Exception
	 */
	public List<Friend> queryMyFriendCustomer(Friend friend) throws Exception;

	/**
	 * 返回用户关注表示
	 * 
	 * @param cusId
	 * @param cusFriendId
	 * @param mutual
	 * @return
	 */
	public Long updateFriendMutual(Long cusId, Long cusFriendId, Long mutual);
}
