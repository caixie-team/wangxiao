package com.atdld.os.sns.dao.impl.customer;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.atdld.os.core.dao.impl.common.GenericDaoImpl;
import com.atdld.os.core.entity.PageEntity;
import com.atdld.os.core.util.ObjectUtils;
import com.atdld.os.sns.dao.customer.SnsUserDao;
import com.atdld.os.sns.entity.customer.SnsUserExpandDto;
import com.atdld.os.sns.entity.friend.Friend;

/**
 * 
 * UserExpand User:  Date: 2014-01-10
 */
@Repository("snsUserDao")
public class SnsUserDaoImpl extends GenericDaoImpl implements SnsUserDao {

   
    /**
     * 查询我关注的用户的列表
     * 
     * @param cusAttention
     *            传入cusId
     * @param page
     *            分页参数
     * @return List<Customer> 我关注的用户的 list
     */
    public List<SnsUserExpandDto> queryMyAttentionCustomer(Friend friend, PageEntity page) {
        List<SnsUserExpandDto> list = this.queryForListPage("SnsUserMapper.queryMyAttentionCustomer", friend, page);// 查询我关注的用户的列表
        return  setShowName(list);
    }

    /**
     * 查询我的粉丝用户的列表
     * 
     * @param cusAttention
     *            cusAttentionId传入
     * @param page
     *            分页参数
     * @return List<QueryCustomer> 查询我的粉丝用户列表
     */
    public List<SnsUserExpandDto> queryMyFans(Friend friend, PageEntity page) {
        List<SnsUserExpandDto> list =this.queryForListPage("SnsUserMapper.queryMyFans", friend, page);// 查询我的粉丝用户的列表
        return  setShowName(list);
    }

    
   
    
    /**
     * 查询当前用户是否跟传来的userIds是好友关系，返回是好友的列表
     * @param userIds
     * @param cusId
     * @return
     */
    public List<SnsUserExpandDto> queryIsFrirndByIds(List<Long> userIds,Long cusId){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("cusId", cusId);
        map.put("list", userIds);
        List<SnsUserExpandDto> list = this.selectList("SnsUserMapper.queryIsFrirndByIds", map);
        return setShowName(list);
    }
    
    /**
     * 设置showname
     * @param list
     * @return
     */
    public List<SnsUserExpandDto> setShowName(List<SnsUserExpandDto> list){
        if(ObjectUtils.isNotNull(list)){
            for(SnsUserExpandDto expandDto:list){
                expandDto.setShowname(expandDto.getShowname());
            }
        }
        return list;
    }

   
    
}
