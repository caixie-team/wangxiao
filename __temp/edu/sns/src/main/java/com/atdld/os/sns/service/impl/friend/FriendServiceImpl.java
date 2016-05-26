package com.atdld.os.sns.service.impl.friend;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atdld.os.common.constants.MemConstans;
import com.atdld.os.common.service.WebHessianService;
import com.atdld.os.core.entity.PageEntity;
import com.atdld.os.core.service.cache.MemCache;
import com.atdld.os.core.util.ObjectUtils;
import com.atdld.os.sns.constants.SnsConstants;
import com.atdld.os.sns.constants.eunms.SnsUserExpandType;
import com.atdld.os.sns.dao.friend.BlackListDao;
import com.atdld.os.sns.dao.friend.FriendDao;
import com.atdld.os.sns.entity.customer.SnsUserExpand;
import com.atdld.os.sns.entity.customer.SnsUserExpandDto;
import com.atdld.os.sns.entity.friend.BlackList;
import com.atdld.os.sns.entity.friend.Friend;
import com.atdld.os.sns.service.dynamic.DynamicWebService;
import com.atdld.os.sns.service.friend.FriendService;
import com.atdld.os.sns.service.letter.MsgReceiveService;
import com.atdld.os.sns.service.user.SnsUserService;

/**
 * @author
 * @ClassName FriendServiceImpl
 * @package com.atdld.open.sns.service.impl.friend
 * @description
 * @Create Date: 2013-12-10 下午4:14:22
 */

@Service("friendService")
public class FriendServiceImpl implements FriendService {
    @Autowired
    private FriendDao friendDao;

    @Autowired
    private BlackListDao blackListDao;
    @Autowired
    private MsgReceiveService msgReceiveService;
    @Autowired
    private SnsUserService snsUserService;
    @Autowired
    private WebHessianService webHessianService;
    @Autowired
    private DynamicWebService dynamicWebService;

    private MemCache memCache = MemCache.getInstance();// 缓存

    /**
     * 添加好友
     *
     * @param friend 好友实体
     * @throws Exception
     */
    public String addFriend(Friend friend) throws Exception {
        String falg = "";

        BlackList blackList = new BlackList();// 实例化黑名单
        blackList.setCusId(friend.getCusFriendId());// set 用户id
        blackList.setCusBlackListId(friend.getCusId());// set 黑名单的用户id
        Long cusFriendId = friend.getCusFriendId();// 获得要添加的用户id
        if (blackListDao.queryBlackListByCusIdAndCusBlacklistId(blackList) == 0) {// 检查我是否在对方的黑名单中且不能自己加自己

            if (friend.getCusFriendId().longValue() == friend.getCusId().longValue()) {
                return SnsConstants.ATTENTIONONESELF;// 自己不能关注自己
            }
            // 查询是否添加过好友如果没有添加过才可以添加为好友(判断自己)
            if (friendDao.queryFriendByCusIdAndCusFriendId(friend) == null) {
            	//对方是否添加好友
            	Long mutual=friendDao.updateFriendMutual(cusFriendId,friend.getCusId(),1l);
            	friend.setMutual(mutual);
                friendDao.addFriend(friend);// 添加好友
                SnsUserExpandDto userExpandDto = snsUserService.getUserExpandByCusId(friend.getCusId());
                Map<String,String> map=new HashMap<String, String>();
                map.put("cusId", friend.getCusId()+"");
                map.put("count", 1L+"");
                map.put("operation", SnsUserExpand.ADD);
                // 我的关注数加一
                map.put("type", SnsUserExpandType.attentionNum.toString());
                webHessianService.updateUserExpandCount(map);
                memCache.remove(MemConstans.USEREXPAND_INFO + userExpandDto.getCusId());// 移除该用户的缓存保证关注数正确
                // 我关注的人的粉丝数加一
                map.put("type",SnsUserExpandType.fansNum.toString());
                webHessianService.updateUserExpandCount(map);
                SnsUserExpandDto userExpandDto2 =snsUserService.getUserExpandByCusId(friend.getCusFriendId());
                memCache.remove(MemConstans.USEREXPAND_INFO + userExpandDto2.getCusId());// 移除该用户的缓存保证粉丝数正确
                falg = SnsConstants.SUCCESS;// 返回成功
            } else {
                falg = SnsConstants.FRIEND;
            }

        } else {
            falg = "blackList";// 对方把你放入黑名单中
        }
        if (falg.equals(SnsConstants.SUCCESS)) {// 如果添加好友成功
            // 返回success
        	webHessianService.readMsgNumAddOrReset("unreadFansNum",cusFriendId,"add");
            dynamicWebService.addDynamicWebForFriends(friend);
        }
        return falg;
    }

    /**
     * 查询我的好友
     *
     * @param friend 好友实体
     * @param page   分页参数
     * @return
     * @throws Exception
     */
    public List<SnsUserExpandDto> queryMyFriend(Friend friend, PageEntity page) throws Exception {
        return friendDao.queryMyFriend(friend, page);// 查询我的好友
    }

    /**
     * 删除好友
     *
     * @param friend 好友实体传入cusFriendId 和cusId好友id
     * @throws Exception
     */
    public String delFriend(Friend friend) throws Exception {
    	
        Long num = friendDao.delFriend(friend);// 删除好友
        if (num > 0) {
            SnsUserExpand customer = new SnsUserExpand();
            customer.setCusId(friend.getCusId());// set用户id
            memCache.remove(MemConstans.USEREXPAND_INFO + customer.getCusId());// 移除该用户的缓存保证粉丝数正确
            Map<String,String> map=new HashMap<String, String>();
            map.put("cusId", friend.getCusId()+"");
            map.put("count", 1L+"");
            map.put("operation",  SnsUserExpand.SUBTRACTION);
            if (!ObjectUtils.isNull(snsUserService.getUserExpandByCusId(friend.getCusId()))) {// 如果是第一次添加微博则往customer表中添加数据
            	// 我的关注数减一
                map.put("type", SnsUserExpandType.attentionNum.toString());
                webHessianService.updateUserExpandCount(map);
            }
            customer = new SnsUserExpand();
            customer.setCusId(friend.getCusFriendId());// set我关注的人的用户id
            if (!ObjectUtils.isNull(snsUserService.getUserExpandByCusId(friend.getCusId()))) {// 如果是第一次添加微博则往customer表中添加数据
            	// 我关注的人的粉丝数减一
            	map.put("type",SnsUserExpandType.fansNum.toString());
                webHessianService.updateUserExpandCount(map);
            }
            friendDao.updateFriendMutual(friend.getCusFriendId(), friend.getCusId(),0L);
            return SnsConstants.SUCCESS;// 成功
        } else {
            return SnsConstants.FALSE;// 成功
        }
    }

    /**
     * 删除全部好友
     *
     * @param id 好友实体
     * @throws Exception
     */
    public Long delAllFriendByUid(Long id) throws Exception {
        return friendDao.delAllFriendByUid(id);
    }

    /**
     * 更新好友备注
     *
     * @param friend 传入Remarks（备注） CusId（自己的id） CusFriendId（好友的id）
     * @throws Exception
     */
    public String updateFriendForRemarksByCusIdAndCusFriendId(Friend friend) throws Exception {
        Long num = friendDao.updateFriendForRemarksByCusIdAndCusFriendId(friend);// 更新好友备注
        if (num > 0) {// 如果影响行数大于0 则说明更新成功
            return SnsConstants.SUCCESS;// 返回成功
        } else {
            return SnsConstants.FALSE;// 返回失败
        }

    }

    /**
     * 获取共同好友的数量
     *
     * @param userId  用户id
     * @param friends 好友的ids
     * @return Map<Long,Integer> key好友id.value共同好友个数
     */
    public Map<Long, Integer> getCommonfriend(Long userId, List<Long> friends) throws Exception {
        // 获取当前用户的好友
        List<Long> meids = new ArrayList<Long>();
        meids.add(userId);
        List<Friend> list = friendDao.getCommonFriend(meids);
        List<Long> meList = new ArrayList<Long>();
        for (Friend friend : list) {
            meList.add(friend.getCusFriendId());
        }
        // 获取好友的二度好友。要以好友id排序 (直接查询好友list的sql很快，在数据库中计算,数据多时会很慢)
        List<Friend> friendList = friendDao.getCommonFriend(friends);
        // 计算共同好友
        Map<Long, Integer> result = new HashMap<Long, Integer>();

        if (ObjectUtils.isNotNull(friendList)) {
            Friend tmpfriend = friendList.get(0);
            List<Long> thisList = new ArrayList<Long>();
            for (Friend friend : friendList) {
                // id变化时代表一个好友的list结束
                if (tmpfriend.getCusId().longValue() != friend.getCusId().longValue()) {
                    result.put(tmpfriend.getCusId(), countFrieds(meList, thisList));// 设置共同好友个数
                    thisList = new ArrayList<Long>();
                    tmpfriend = friend;
                    thisList.add(friend.getCusFriendId());
                } else {
                    tmpfriend = friend;
                    thisList.add(friend.getCusFriendId());
                }
            }
            // 最后一条加入
            result.put(tmpfriend.getCusId(), countFrieds(meList, thisList));
        }
        // 没有好友或者为空的设置为0
        for (Long id : friends) {
            if (ObjectUtils.isNull(result.get(id))) {
                result.put(id, 0);
            }
        }
        return result;
    }

    /**
     * 计算共同好友个数
     *
     * @param e1
     * @param e2
     * @return
     */
    public int countFrieds(List<Long> e1, List<Long> e2) {
        if (ObjectUtils.isNull(e1) || ObjectUtils.isNull(e2)) {
            return 0;
        }
        // 复制e1
        ArrayList<Long> e = new ArrayList<Long>(e1);
        e.retainAll(e2);// 取交集为共同好友
        if (ObjectUtils.isNull(e)) {
            return 0;
        }
        return e.size();
    }

    /**
     * 通过用户id获得好友列表
     *
     * @param cusids 用户id
     * @return
     */
    public List<Friend> getFriendByCusids(List<Long> cusids) {
        return friendDao.getCommonFriend(cusids);// 获得好友列表
    }

    /**
     * 通过cusId和CusFriendId查询Friend
     *
     * @param friend 传入cusId和CusFriendId
     * @return Friend
     * @throws Exception
     */
    public Friend queryFriendByCusIdAndCusFriendId(Friend friend) throws Exception {
        return friendDao.queryFriendByCusIdAndCusFriendId(friend);
    }

    /**
     * 查询我加为好友的用户的列表
     *
     * @param cusAttention 用户加为好友的实体
     * @return Map<String,QueryCustomer>用户list
     * @throws Exception
     */
    public Map<Long, Friend> queryMyFriendCustomer(Friend friend) throws Exception {
        List<Friend> queryCustomerList = friendDao.queryMyFriendCustomer(friend);
        Map<Long, Friend> map = new HashMap<Long, Friend>();
        if (queryCustomerList != null && queryCustomerList.size() > 0) {
            for (Friend cusA : queryCustomerList) {
                map.put(cusA.getCusFriendId(), cusA);
            }
        }
        return map;
    }

    /**
     * 查询该用户是否加过好友
     *
     * @return 返回查询的结果数
     */
    public int queryFriendByCusIdAndFriendIdNum(Friend friend) throws Exception {
        return friendDao.queryFriendByCusIdAndCusFriendIdNum(friend);
    }
}
