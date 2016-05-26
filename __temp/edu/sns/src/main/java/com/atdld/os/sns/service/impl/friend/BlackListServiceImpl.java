package com.atdld.os.sns.service.impl.friend;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atdld.os.sns.constants.SnsConstants;
import com.atdld.os.sns.dao.friend.BlackListDao;
import com.atdld.os.sns.dao.friend.FriendDao;
import com.atdld.os.common.service.WebHessianService;
import com.atdld.os.core.entity.PageEntity;
import com.atdld.os.core.util.ObjectUtils;
import com.atdld.os.sns.entity.customer.SnsUserExpandDto;
import com.atdld.os.sns.entity.friend.BlackList;
import com.atdld.os.sns.entity.friend.Friend;
import com.atdld.os.sns.service.friend.BlackListService;
import com.atdld.os.sns.service.user.SnsUserService;

/**
 * @author
 * @ClassName BlackListServiceImpl
 * @package com.atdld.open.sns.service.impl.friend
 * @description 黑名单实现
 * @Create Date: 2013-12-10 下午4:14:15
 */
@Service("blackListService")
public class BlackListServiceImpl implements BlackListService {
    @Autowired
    private BlackListDao blackListDao;
    @Autowired
    private FriendDao friendDao;
    @Autowired
    private SnsUserService snsUserService;
    @Autowired
    private WebHessianService webHessianService;
    
    /**
     * 添加黑名单
     *
     * @param blackList 黑名单实体
     * @throws Exception
     */
    public String addBlackList(BlackList blackList) throws Exception {
        if (blackListDao.queryBlackListByCusIdAndCusBlacklistId(blackList) == 0 && blackList.getCusId().longValue() != blackList.getCusBlackListId().longValue()) {// 检查是否添加过黑名单
            blackListDao.addBlackList(blackList);// 添加黑名单
            Friend friend = new Friend();
            friend.setCusFriendId(blackList.getCusId());
            friend.setCusId(blackList.getCusBlackListId());
            friendDao.delFriend(friend);// 删除好友
            friend.setCusFriendId(blackList.getCusBlackListId());
            friend.setCusId(blackList.getCusId());
            friendDao.delFriend(friend);// 删除好友 好友为双向的 从对方好友中和我的好友中都要删除
            return SnsConstants.SUCCESS;
        } else {
            return SnsConstants.BLACKLIST;
        }
    }

    /**
     * 检查黑名单
     *
     * @param blackList 黑名单实体
     * @return int 返回是否存在该黑名单如果等于1则存在等于0 则不存在
     * @throws Exception
     */
    public int queryBlackListByCusIdAndCusBlacklistId(BlackList blackList) throws Exception {
        return blackListDao.queryBlackListByCusIdAndCusBlacklistId(blackList);// 检查黑名单
    }

    /**
     * 我的黑名单列表
     *
     * @param blackList 黑名单实体
     * @param page      分页参数
     * @return List<QueryCustomer> 黑名当列表
     * @throws Exception
     */
    public List<SnsUserExpandDto> queryBlackListByCusId(BlackList blackList, PageEntity page) throws Exception {
        //查询黑名单用户id,只查黑名单表
        List<SnsUserExpandDto> queryCustomerList = blackListDao.queryBlackListByCusId(blackList, page);// 我的黑名单列表
        //根据获取的用户id再去查询用户的信息
        if(ObjectUtils.isNotNull(queryCustomerList)){
            String cusIds="";
            for(SnsUserExpandDto dto:queryCustomerList){
            	cusIds+=dto.getId()+"";
            }
            queryCustomerList =new ArrayList<SnsUserExpandDto>();
            Map<String, SnsUserExpandDto> map = snsUserService.getUserExpandsByCusId(cusIds);// 查询用户的信息
            for(Entry<String, SnsUserExpandDto> entry: map.entrySet()) { 
            	queryCustomerList.add(entry.getValue()); 
			}
        }
        return queryCustomerList;
    }

    public String getCustomerListCusId(List<SnsUserExpandDto> queryCustomerList) {// 获得用户ids
        String ids = "";
        if (queryCustomerList != null && queryCustomerList.size() > 0) {
            for (SnsUserExpandDto queryCustomer : queryCustomerList) {
                ids += queryCustomer.getCusId() + ",";
            }
        }
        return ids;
    }

    /**
     * 删除黑名单
     *
     * @param blackList 黑名单实体
     * @throws Exception
     */
    public String delBlackList(BlackList blackList) throws Exception {
        Long num = blackListDao.delBlackList(blackList);// 删除黑名单
        if (num > 0) {
            return SnsConstants.SUCCESS;// 成功
        } else {
            return SnsConstants.FALSE;// 失败
        }
    }

}
