package com.atdld.os.edu.service.impl.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atdld.os.common.constants.MemConstans;
import com.atdld.os.core.entity.PageEntity;
import com.atdld.os.core.service.cache.MemCache;
import com.atdld.os.core.util.ObjectUtils;
import com.atdld.os.core.util.StringUtils;
import com.atdld.os.edu.dao.user.UserExpandDao;
import com.atdld.os.edu.entity.user.UserExpand;
import com.atdld.os.edu.entity.user.UserExpandDto;
import com.atdld.os.edu.service.user.UserExpandService;

/**
 * UserExpand管理接口 User:  Date: 2014-01-10
 */
@Service("userExpandService")
public class UserExpandServiceImpl implements UserExpandService {
    @Autowired
    private UserExpandDao userExpandDao;

    private MemCache memCache = MemCache.getInstance();

    /**
     * 添加UserExpand
     * 
     * @param userExpand
     *            要添加的UserExpand
     * @return id
     */
    public Long addUserExpand(UserExpand userExpand) {
        return userExpandDao.addUserExpand(userExpand);
    }

    /**
     * 修改UserExpand
     * 
     * @param userExpand
     *            要修改的UserExpand
     */
    public void updateUserExpand(UserExpand userExpand) {
        userExpandDao.updateUserExpand(userExpand);
        // 删除缓存
        memCache.remove(MemConstans.USEREXPAND_INFO + userExpand.getCusId());
    }

    /**
     * 修改UserExpand的上传头像
     */
    public void updateAvatarById(UserExpand userExpand) {
        // 删除缓存
        memCache.remove(MemConstans.USEREXPAND_INFO + userExpand.getCusId());
        userExpandDao.updateAvatarById(userExpand);
    }

    /**
     * 通过userId修改UserExpand的subjectId
     */
    public void updateUserExpandForSubject(UserExpand userExpand) {
        userExpandDao.updateUserExpandForSubject(userExpand);
    }

    /**
     * 根据id获取单个UserExpand对象
     * 
     * @param userId
     *            要查询的id
     * @return UserExpand
     */
    public UserExpand getUserExpandByUserId(Long userId) {
        return userExpandDao.getUserExpandByUserId(userId);
    }

    /**
     * 根据条件获取UserExpand列表
     * 
     * @param userExpand
     *            查询条件
     * @return List<UserExpand>
     */
    public List<UserExpand> getUserExpandList(UserExpand userExpand) {
        return userExpandDao.getUserExpandList(userExpand);
    }
    
    /**
     * 拼用户id加逗号间隔
     * @param queryCustomerList
     * @return
     */
    public String getQueryCustomerListCusId(List<UserExpandDto> queryCustomerList) {// 获得用户ids
        String ids = "";
        if (queryCustomerList != null && queryCustomerList.size() > 0) {
            for (UserExpandDto queryCustomer : queryCustomerList) {
                ids += queryCustomer.getCusId() + ",";
            }
        }
        return ids;
    }

   

    /**
     * 通过showname 查询customer
     * 
     * @param showName
     */
    public List<UserExpandDto> queryCustomerByShowName(String showName, int size) {
        List<UserExpandDto> customerList = userExpandDao.queryCustomerByShowName(showName, size);// 通过showname
        return customerList;
    }

    public String getCustomerListCusId(List<UserExpand> customerList) {// 获得用户ids
        String ids = "";
        if (customerList != null && customerList.size() > 0) {
            for (UserExpand customer : customerList) {
                ids += customer.getCusId() + ",";
            }
        }
        return ids;
    }

    /**
     * 通过集合cusId 查询customer 返回map
     * 
     * @param cusIds
     * @return
     * @throws Exception
     */
    public Map<String, UserExpandDto> queryCustomerInCusIds(List<Long> cusIds) throws Exception {
        if(ObjectUtils.isNotNull(cusIds)){
            Map<String, UserExpandDto> map = new HashMap<String, UserExpandDto>();
            // 通过传入的cusIds 查询customer
            List<UserExpandDto> queryCustomerList = userExpandDao.queryUsersByIds(cusIds);
            // 整理数据放回map
            if (ObjectUtils.isNotNull(queryCustomerList)) {
                for (UserExpandDto queryCustomer : queryCustomerList) {
                    map.put(queryCustomer.getId().toString(), queryCustomer);
                }
            }
            return map;
        }else{
            return null;
        }
       
    }
    

    /**
     * 根据用户uid获取用户信息
     *
     * @param uids
     * @return
     */
    public Map<String, UserExpandDto> getUserExpandByUids(String uids) throws Exception {
        String [] arrays=uids.split(",");
        List<Long> list = new ArrayList<Long>();
        for(String lo:arrays){
            if(StringUtils.isNotEmpty(lo)&&!"null".equals(lo)){
                list.add(Long.valueOf(lo));
            }
        }
        return queryCustomerInCusIds(list);
    }

    /**
     * 根据用户uid获取用户信息
     *
     * @param cusId
     * @return
     */
    public UserExpandDto getUserExpandByUids(Long cusId) {
        UserExpandDto dto=(UserExpandDto) memCache.get(MemConstans.USEREXPAND_INFO+cusId);
        if(ObjectUtils.isNull(dto)){
            dto= userExpandDao.queryUserByid(cusId);
            if(ObjectUtils.isNotNull(dto)){
                dto.setShowname(dto.getShowname());
                memCache.set(MemConstans.USEREXPAND_INFO+cusId, dto,MemConstans.USEREXPAND_INFO_TIME);
            }
        }
        return dto;
    }
    
    /**
	 * 根据用户uid获取用户信息 （非缓存）
	 * 
	 * @param uids
	 * @return
	 */
	public UserExpandDto getUserExpandByUid(Long uid){
		return userExpandDao.queryUserByid(uid);
	}

    public String checkString(Object str) {// 检查字符串空的方法
        if (ObjectUtils.isNotNull(str) && !"null".equals(str.toString())) {
            return str.toString();
        } else {
            return "";
        }
    }

    public String checkInteger(Object str) {// 检查字符串空的方法
        if (ObjectUtils.isNotNull(str) && !"null".equals(str.toString())) {
            return str.toString();
        } else {
            return "0";
        }
    }

    /**
     * 补齐用户的信息list对象必须包含字段id
     * @return
     */
    public List<UserExpandDto> setUserExpandDtoInfo(List<UserExpandDto> list)  throws Exception{
        List<Long> cusIds = new ArrayList<Long>();
        for(UserExpandDto dto:list){
            cusIds.add(dto.getId());
        }
        Map<String, UserExpandDto>  map= queryCustomerInCusIds(cusIds);
        for(UserExpandDto dto:list){
            UserExpandDto dto2 = map.get(dto.getId());
            if(ObjectUtils.isNotNull(dto2)){
                dto.setNickname(dto2.getNickname());
                dto.setMobile(dto2.getMobile());
                dto.setEmail(dto2.getEmail());
                dto.setCusId(dto2.getCusId());
                dto.setRealname(dto2.getRealname());
                dto.setGender(dto2.getGender());
                dto.setAvatar(dto2.getAvatar());
                dto.setStudysubject(dto2.getStudysubject());
                dto.setWeiBoNum(dto2.getWeiBoNum());
                dto.setFansNum(dto2.getFansNum());
                dto.setAttentionNum(dto2.getAttentionNum());
                dto.setCommonFriendNum(dto2.getCommonFriendNum());
                dto.setMsgNum(dto2.getMsgNum());
                dto.setSysMsgNum(dto2.getSysMsgNum());
                dto.setLastSystemTime(dto2.getLastSystemTime());
                dto.setUnreadFansNum(dto2.getUnreadFansNum());
            }
        }
        return list;
    }
    /**
     * 通过showname 查询customer(精确搜索)
     * 
     */
    public List<UserExpandDto> queryCustomerByShowNameEquals(String showName){
        return userExpandDao.queryCustomerByShowNameEquals(showName);
    }
    /**
	 * 更新个人中心用户封面
	 * 
	 * @param userId
	 */
    public void updateUserExpandBannerUrl(Long userId, String bannerUrl) {
    	userExpandDao.updateUserExpandBannerUrl(userId, bannerUrl);
    }
   
   


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
	public List<UserExpandDto> queryAllCustomer(UserExpandDto customer, PageEntity page){
		return userExpandDao.queryAllCustomer(customer, page);
	}
	
	/**
	 * 通过标识更新未读数加一
	 */
	public void updateUnReadMsgNumAddOne(String falg, Long cusId){
		userExpandDao.updateUnReadMsgNumAddOne(falg, cusId);
	}

	/**
	 * 通过标识更新未读数清零
	 */
	public void updateUnReadMsgNumReset(String falg, Long cusId){
		userExpandDao.updateUnReadMsgNumReset(falg, cusId);
	}

	/**
	 * 更新用户的上传统计系统消息时间
	 */
	public void updateCusForLST(Long cusId, Date date){
		userExpandDao.updateCusForLST(cusId, date);
	}
	/**
     * 要更新的数量类型
     * 修改UserExpand浏览次数
     * @param SnsUserExpand 
     */
    public void updateUserExpandCount(String type,Long userId,Long count,String operation){
    	userExpandDao.updateUserExpandCount(type, userId, count, operation);
    }
    /**
     * 验证用户是否操作频繁
     * 
     * @param type
     *            验证的类型
     * @param cus_Id
     * @return
     */
    public boolean checkLimitOpt(String type, Long cus_Id) {

        String memkey = type + cus_Id;// mem的key
        // 获取上次的操作次数
        Object obj = memCache.get(memkey);
        if (ObjectUtils.isNull(obj)) {
            // 缓存中没有数据代表时间段内无操作记录
            return true;
        } else {
            Integer count = Integer.valueOf(obj.toString());
            if (MemConstans.WEIBO_LIMIT.equals(type)) {
                // 验证发微博
                return count < MemConstans.WEIBO_LIMIT_COUNT;
            } else if (MemConstans.WEIBO_REPLY_LIMIT.equals(type)) {
                // 微博发评论
                return count < MemConstans.WEIBO_REPLY_LIMIT_COUNT;
            } else if (MemConstans.ARTICLE_LIMIT.equals(type)) {
                // 发文章
                return count < MemConstans.ARTICLE_LIMIT_COUNT;
            } else if (MemConstans.ARTICLE_REPLY_LIMIT.equals(type)) {
                // 文章评论
                return count < MemConstans.ARTICLE_REPLY_LIMIT_COUNT;
            } else if (MemConstans.USER_LETTER_LIMIT.equals(type)) {
                // 站内信
                return count < MemConstans.USER_LETTER_LIMIT_COUNT;
            } else if (MemConstans.SUGGEST_LIMIT.equals(type)) {
                // 建议
                return count < MemConstans.SUGGEST_LIMIT_COUNT;
            } else if (MemConstans.SUGGEST_REPLY_LIMIT.equals(type)) {
                // 建议评论
                return count < MemConstans.SUGGEST_REPLY_LIMIT_COUNT;
            } else if (MemConstans.SEARCH_LIMIT.equals(type)) {
                // 搜素
                return count < MemConstans.SEARCH_LIMIT_COUNT;
            }
        }
        return false;
    }

    /**
     * 增加用户操作次数
     * 
     * @param type
     *            验证的类型
     * @param cus_Id
     * @return
     */
    public void customerOptLimitCountAdd(String type, Long cus_Id) {

        String memkey = type + cus_Id;// mem的key
        // 获取上次的操作次数
        Object obj = memCache.get(memkey);
        Integer count = 1;// 默认次数为1
        if (ObjectUtils.isNull(obj)) {
            // 缓存中没有数据代表时间段内无操作记录
            count = 1;
        } else {
            count = count + Integer.valueOf(obj.toString());
        }

        if (MemConstans.WEIBO_LIMIT.equals(type)) {
            // 验证发微博
            memCache.set(memkey, count, MemConstans.WEIBO_LIMIT_TIME);
        } else if (MemConstans.WEIBO_REPLY_LIMIT.equals(type)) {
            // 微博发评论
            memCache.set(memkey, count, MemConstans.WEIBO_REPLY_LIMIT_TIME);
        } else if (MemConstans.ARTICLE_LIMIT.equals(type)) {
            // 发文章
            memCache.set(memkey, count, MemConstans.ARTICLE_LIMIT_TIME);
        } else if (MemConstans.ARTICLE_REPLY_LIMIT.equals(type)) {
            // 文章评论
            memCache.set(memkey, count, MemConstans.ARTICLE_REPLY_LIMIT_TIME);
        } else if (MemConstans.USER_LETTER_LIMIT.equals(type)) {
            // 站内信
            memCache.set(memkey, count, MemConstans.USER_LETTER_LIMIT_TIME);
        } else if (MemConstans.SUGGEST_LIMIT.equals(type)) {
            // 建议
            memCache.set(memkey, count, MemConstans.SUGGEST_LIMIT_TIME);
        } else if (MemConstans.SUGGEST_REPLY_LIMIT.equals(type)) {
            // 建议评论
            memCache.set(memkey, count, MemConstans.SUGGEST_REPLY_LIMIT_TIME);
        } else if (MemConstans.SEARCH_LIMIT.equals(type)) {
            // 搜索
            memCache.set(memkey, count, MemConstans.SEARCH_LIMIT_TIME);
        }

    }
}