package com.atdld.os.sns.service.friend;

import java.util.List;
import java.util.Map;

import com.atdld.os.core.entity.PageEntity;
import com.atdld.os.sns.entity.customer.SnsUserExpandDto;
import com.atdld.os.sns.entity.friend.Friend;

/**
 * @author
 * @ClassName FriendService
 * @package com.atdld.open.sns.service.friend
 * @description 好友接口
 * @Create Date: 2013-12-10 下午4:19:05
 */

public interface FriendService {
    /**
     * 添加好友
     *
     * @param friend 好友实体
     * @throws Exception
     */
    public String addFriend(Friend friend) throws Exception;

    /**
     * 查询我的好友
     *
     * @param friend 好友实体
     * @param page   分页参数
     * @return
     * @throws Exception
     */
    public List<SnsUserExpandDto> queryMyFriend(Friend friend, PageEntity page)
            throws Exception;

    /**
     * 删除好友
     *
     * @param friend 好友实体
     * @throws Exception
     */
    public String delFriend(Friend friend) throws Exception;

    /**
     * 删除全部好友
     *
     * @param id 好友实体
     * @throws Exception
     */
    public Long delAllFriendByUid(Long id) throws Exception;

    /**
     * 更新好友备注
     *
     * @param friend 传入Remarks（备注） CusId（自己的id） CusFriendId（好友的id）
     * @throws Exception
     */
    public String updateFriendForRemarksByCusIdAndCusFriendId(Friend friend)
            throws Exception;

    /**
     * 获取共同好友的数量
     *
     * @param userId  用户id
     * @param friends 好友的ids
     * @return Map<Long,Integer>
     */
    public Map<Long, Integer> getCommonfriend(Long userId, List<Long> friends)
            throws Exception;

    /**
     * 通过用户id获得好友列表
     *
     * @param cusids 用户id
     * @return
     */
    public List<Friend> getFriendByCusids(List<Long> cusids);

    /**
     * 通过cusId和CusFriendId查询Friend
     *
     * @param friend 传入cusId和CusFriendId
     * @return Friend
     * @throws Exception
     */
    public Friend queryFriendByCusIdAndCusFriendId(Friend friend) throws Exception;

    /**
     * 查询我加为好友的用户的列表
     *
     * @param cusAttention 用户加为好友的实体
     * @return Map<String,QueryCustomer>用户list
     * @throws Exception
     */
    public Map<Long, Friend> queryMyFriendCustomer(Friend friend) throws Exception;

    /**
     * 查询该用户是否加过好友
     *
     * @return 返回查询的结果数
     */
    public int queryFriendByCusIdAndFriendIdNum(Friend friend) throws Exception;
}
