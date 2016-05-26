package com.atdld.os.sns.dao.impl.friend;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.atdld.os.core.dao.impl.common.GenericDaoImpl;
import com.atdld.os.core.entity.PageEntity;
import com.atdld.os.sns.dao.friend.FriendDao;
import com.atdld.os.sns.entity.customer.SnsUserExpandDto;
import com.atdld.os.sns.entity.friend.Friend;

/**
 * @author
 * @ClassName FriendImpl
 * @package com.atdld.open.sns.dao.impl.friend
 * @description
 * @Create Date: 2013-12-10 下午4:20:54
 */
@Repository("friendDao")
public class FriendDaoImpl extends GenericDaoImpl implements FriendDao {

	/**
	 * 添加好友
	 * 
	 * @param friend
	 *            好友实体
	 * @throws Exception
	 */
	public void addFriend(Friend friend) {
		this.insert("FriendMapper.addFriend", friend);// 添加好友
	}

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
	public List<SnsUserExpandDto> queryMyFriend(Friend friend, PageEntity page) throws Exception {
		return this.queryForListPage("FriendMapper.queryMyFriend", friend, page);// 查询我的好友
	}

	/**
	 * 查询我的好友的用id
	 * 
	 * @param Long
	 *            cusId 好友实体 传入cusId
	 * @return List<Long> 我的好友list
	 * @throws Exception
	 */
	public List<Long> queryMyFriend(Long cusId) throws Exception {
		return this.selectList("FriendMapper.queryMyFriendByCusId", cusId);// 查询我的好友
	}

	/**
	 * 删除好友
	 * 
	 * @param friend
	 *            好友实体
	 */
	public Long delFriend(Friend friend) {
		return this.delete("FriendMapper.delFriend", friend);// 删除好友
	}

	/**
	 * 删除全部好友
	 * 
	 * @param id
	 *            好友实体
	 * @throws Exception
	 */
	public Long delAllFriendByUid(Long id) throws Exception {
		return this.delete("FriendMapper.delAllFriendByUid", id);// 删除好友
	}

	/**
	 * 通过cusId和CusFriendId查询Friend
	 * 
	 * @param friend
	 *            传入cusId和CusFriendId
	 * @return Friend
	 * @throws Exception
	 */
	public Friend queryFriendByCusIdAndCusFriendId(Friend friend) throws Exception {
		List<Friend> friendList = this.selectList("FriendMapper.queryFriendByCusIdAndCusFriendId", friend);// 通过cusId和CusFriendId查询Friend
		if (friendList != null && friendList.size() > 0) {// 如果查出的friendList有值则返回
			// 没值则返回null
			return friendList.get(0);
		}
		return null;
	}

	/**
	 * 通过cusId和CusFriendId查询Friend
	 * 
	 * @param friend
	 *            传入cusId和CusFriendId
	 * @throws Exception
	 */
	public int queryFriendByCusIdAndCusFriendIdNum(Friend friend) throws Exception {
		List<Friend> friendList = this.selectList("FriendMapper.queryFriendByCusIdAndCusFriendId", friend);// 通过cusId和CusFriendId查询Friend
		if (friendList != null && friendList.size() > 0) {// 如果查出的friendList有值则返回
			// 没值则返回null
			return friendList.size();
		}
		return 0;
	}

	/**
	 * 更新好友备注
	 * 
	 * @param friend
	 *            传入Remarks（备注） CusId（自己的id） CusFriendId（好友的id）
	 * @throws Exception
	 */
	public Long updateFriendForRemarksByCusIdAndCusFriendId(Friend friend) throws Exception {
		return this.update("FriendMapper.updateFriendForRemarksByCusIdAndCusFriendId", friend);// 更新好友备注
	}

	/**
	 * 获得好友列表
	 * 
	 * @param cusids
	 *            用户id
	 * @return
	 */
	public List<Friend> getCommonFriend(List<Long> cusids) {
		return this.selectList("FriendMapper.getCommonFriend", cusids);
	}

	/**
	 * 查询我加好友的用户的列表
	 * 
	 * @param cusAttention
	 * @return
	 * @throws Exception
	 */
	public List<Friend> queryMyFriendCustomer(Friend friend) throws Exception {
		return this.selectList("FriendMapper.queryMyFriendCustomer", friend);
	}

	/**
	 * 返回用户关注表示
	 * 
	 * @param cusId
	 * @param cusFriendId
	 * @param mutual
	 * @return
	 */
	public Long updateFriendMutual(Long cusId ,Long cusFriendId,Long mutual) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("cusId", cusId);
		map.put("cusFriendId", cusFriendId);
		map.put("mutual", mutual);
		return this.update("FriendMapper.updateFriendMutual", map);
	}
}
