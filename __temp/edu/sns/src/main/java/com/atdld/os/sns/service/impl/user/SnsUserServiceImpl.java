package com.atdld.os.sns.service.impl.user;



import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.atdld.os.common.constants.MemConstans;
import com.atdld.os.common.service.WebHessianService;
import com.atdld.os.core.entity.PageEntity;
import com.atdld.os.core.service.cache.MemCache;
import com.atdld.os.core.util.ObjectUtils;
import com.atdld.os.sns.dao.customer.SnsUserDao;
import com.atdld.os.sns.entity.course.Course;
import com.atdld.os.sns.entity.customer.SnsUserExpandDto;
import com.atdld.os.sns.entity.friend.Friend;
import com.atdld.os.sns.service.user.SnsUserService;

/**
 * @author :
 * @ClassName com.atdld.os.sns.service.impl.user.snsUserService
 * @description 博客serviceImpl
 * @Create Date : 2013-12-30 上午10:27:07
 */
@Service("snsUserService")
public class SnsUserServiceImpl implements SnsUserService{
	
	private MemCache memCache = MemCache.getInstance();
    @Autowired
    private WebHessianService webHessianService;
    @Autowired
    private SnsUserDao snsUserDao;
    private Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    
    /**
     * 用户详细信息
     * @param cusId
     * @return
     */
    @SuppressWarnings("serial")
	public SnsUserExpandDto getUserExpandByCusId(Long cusId){
		Map<String,String> map=webHessianService.getUserInfo(cusId);
    	SnsUserExpandDto userExpandDto=new SnsUserExpandDto();
    	//重新set防止属性不一致
    	Map<String,String> mapObject=gson.fromJson(map.get("userJson"), new TypeToken<Map<String,String>>(){}.getType());
    	userExpandDto.setId( Long.parseLong(mapObject.get("id")));//id
    	userExpandDto.setCusId( Long.parseLong(mapObject.get("cusId")));//用户id
    	userExpandDto.setEmail( mapObject.get("email"));//用户邮箱
    	userExpandDto.setMobile( mapObject.get("mobile"));//电话
    	userExpandDto.setNickname( mapObject.get("nickname"));//昵称
    	userExpandDto.setRealname( mapObject.get("realname"));//真实姓名
    	userExpandDto.setUserInfo(mapObject.get("userInfo"));//个人简介
    	userExpandDto.setGender(Integer.parseInt(mapObject.get("gender")));//性别0男 1女
    	userExpandDto.setAvatar(mapObject.get("avatar"));//头像
    	userExpandDto.setBannerUrl(mapObject.get("bannerUrl"));//自定义背景图地址
    	userExpandDto.setStudysubject(getStudySubject(mapObject));//考试最后选择的专业
    	userExpandDto.setWeiBoNum(Integer.parseInt(mapObject.get("weiBoNum")));//微博数
    	userExpandDto.setFansNum(Integer.parseInt(mapObject.get("fansNum")));//粉丝数
    	userExpandDto.setAttentionNum(Integer.parseInt(mapObject.get("attentionNum")));//关注人数
    	userExpandDto.setMsgNum(Integer.parseInt(mapObject.get("msgNum")));//站内信未读消息数
    	userExpandDto.setSysMsgNum(Integer.parseInt(mapObject.get("sysMsgNum")));//系统自动消息未读消息数
    	userExpandDto.setLastSystemTime(getLastSystemTime(mapObject));//上传统计系统消息时间
    	userExpandDto.setUnreadFansNum(Integer.parseInt(mapObject.get("unreadFansNum")));//未读粉丝数
    	return userExpandDto;
    }
    
    /**
     * 批量用户详细信息
     * @param cusIds
     * @return
     */
    @SuppressWarnings("serial")
	public Map<String, SnsUserExpandDto> getUserExpandsByCusId(String cusIds){
    	Map<String,String> map=webHessianService.getUserInfoByUids(cusIds);
    	Map<String, SnsUserExpandDto> userMap=new HashMap<String, SnsUserExpandDto>();
    	//usersString集合
		List<String> usersString=gson.fromJson(map.get("usersJson"), new TypeToken<List<String>>(){}.getType());
    	for(String userString:usersString){
    		SnsUserExpandDto userExpandDto=new SnsUserExpandDto();
    		Map<String,String> mapObject=gson.fromJson(userString, new TypeToken<Map<String,String>>(){}.getType());
    		userExpandDto.setId( Long.parseLong(mapObject.get("id")));//id
        	userExpandDto.setCusId( Long.parseLong(mapObject.get("cusId")));//用户id
        	userExpandDto.setEmail( mapObject.get("email"));//用户邮箱
        	userExpandDto.setMobile( mapObject.get("mobile"));//电话
        	userExpandDto.setNickname( mapObject.get("nickname"));//昵称
        	userExpandDto.setRealname( mapObject.get("realname"));//真实姓名
        	userExpandDto.setUserInfo(mapObject.get("userInfo"));//个人简介
        	userExpandDto.setGender(Integer.parseInt(mapObject.get("gender")));//性别0男 1女
        	userExpandDto.setAvatar(mapObject.get("avatar"));//头像
        	userExpandDto.setBannerUrl(mapObject.get("bannerUrl"));//自定义背景图地址
        	userExpandDto.setStudysubject(getStudySubject(mapObject));//考试最后选择的专业
        	userExpandDto.setWeiBoNum(Integer.parseInt(mapObject.get("weiBoNum")));//微博数
        	userExpandDto.setFansNum(Integer.parseInt(mapObject.get("fansNum")));//粉丝数
        	userExpandDto.setAttentionNum(Integer.parseInt(mapObject.get("attentionNum")));//关注人数
        	userExpandDto.setMsgNum(Integer.parseInt(mapObject.get("msgNum")));//站内信未读消息数
        	userExpandDto.setSysMsgNum(Integer.parseInt(mapObject.get("sysMsgNum")));//系统自动消息未读消息数
        	userExpandDto.setLastSystemTime(getLastSystemTime(mapObject));//上传统计系统消息时间
        	userExpandDto.setUnreadFansNum(Integer.parseInt(mapObject.get("unreadFansNum")));//未读粉丝数
        	userMap.put(Long.toString(userExpandDto.getCusId()), userExpandDto);
    	}
    	return userMap;
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
    @SuppressWarnings("serial")
	public Map<String, Object> queryAllCustomer(SnsUserExpandDto customer, PageEntity page) throws Exception {
    	Map<String,String> map=new HashMap<String, String>();
    	String email=customer.getEmail();
    	email=email!=null?email:"";
    	map.put("email",email);//邮箱
    	Long cusId=customer.getCusId();
    	cusId=cusId!=null?cusId:0L;
    	map.put("cusId",cusId+"");//用户id
    	map.put("pageSize",page.getPageSize()+"");//分页size
    	map.put("currentPage",page.getCurrentPage()+"");//当前页数
    	
    	//查询结果
    	Map<String, Object> resultMap = new HashMap<String, Object>();
    	Map<String,String> mapResult=webHessianService.queryAllCustomer(map);
    	List<String> usersString=gson.fromJson(mapResult.get("usersJson"), new TypeToken<List<String>>(){}.getType());
    	List<SnsUserExpandDto> snsUserExpand = forSetUserExpandDtos(usersString);
    	resultMap.put("allCustomer", snsUserExpand);
    	String pageJson = mapResult.get("pageJson");
    	PageEntity pageResult = gson.fromJson(pageJson, new TypeToken<PageEntity>(){}.getType());
    	resultMap.put("page", pageResult);
        return resultMap;
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
   
    
    /**
     * 通过showname 查询customer(精确搜索)
     */
    @SuppressWarnings("serial")
	public List<SnsUserExpandDto> queryCustomerByShowNameEquals(String showName) {
    	Map<String,String> map=webHessianService.queryCustomerByShowNameEquals(showName);
        List<String> users=gson.fromJson(map.get("usersJson").toString(),new TypeToken<List<String>>(){}.getType());
        return forSetUserExpandDtos(users);
    }
   
	/**
	 * 通过showname 查询customer
	 * 
	 * @param showName
	 */
	@SuppressWarnings("serial")
	public List<SnsUserExpandDto> queryCustomerByShowName(String showName, int size){
    	Map<String,String> mapResult=webHessianService.queryCustomerByShowName(showName, size);
        List<String> usersString=gson.fromJson(mapResult.get("usersJson").toString(),new TypeToken<List<String>>(){}.getType());
        return forSetUserExpandDtos(usersString);  
	}
	
	@SuppressWarnings("serial")
	public List<SnsUserExpandDto> forSetUserExpandDtos(List<String> usersString){
		List<SnsUserExpandDto> usersTemp=new ArrayList<SnsUserExpandDto>();
		for(String userString:usersString){
			Map<String,String> mapObject=gson.fromJson(userString, new TypeToken<Map<String,String>>(){}.getType());
    		SnsUserExpandDto userExpandDto=new SnsUserExpandDto();
    		userExpandDto.setId( Long.parseLong(mapObject.get("id")));//id
    		userExpandDto.setCusId( Long.parseLong(mapObject.get("cusId")));//用户id
        	userExpandDto.setEmail( mapObject.get("email"));//用户邮箱
        	userExpandDto.setMobile( mapObject.get("mobile"));//电话
        	userExpandDto.setNickname( mapObject.get("nickname"));//昵称
        	userExpandDto.setRealname( mapObject.get("realname"));//真实姓名
        	userExpandDto.setUserInfo(mapObject.get("userInfo"));//个人简介
        	userExpandDto.setGender(Integer.parseInt(mapObject.get("gender")));//性别0男 1女
        	userExpandDto.setAvatar(mapObject.get("avatar"));//头像
        	userExpandDto.setBannerUrl(mapObject.get("bannerUrl"));//自定义背景图地址
        	userExpandDto.setStudysubject(getStudySubject(mapObject));//考试最后选择的专业
        	userExpandDto.setWeiBoNum(Integer.parseInt(mapObject.get("weiBoNum")));//微博数
        	userExpandDto.setFansNum(Integer.parseInt(mapObject.get("fansNum")));//粉丝数
        	userExpandDto.setAttentionNum(Integer.parseInt(mapObject.get("attentionNum")));//关注人数
        	userExpandDto.setMsgNum(Integer.parseInt(mapObject.get("msgNum")));//站内信未读消息数
        	userExpandDto.setLastSystemTime(getLastSystemTime(mapObject));//上传统计系统消息时间
        	userExpandDto.setSysMsgNum(Integer.parseInt(mapObject.get("sysMsgNum")));//系统自动消息未读消息数
        	usersTemp.add(userExpandDto);
    	}
        return usersTemp;  
	}
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
        return  snsUserDao.queryMyAttentionCustomer(friend, page);
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
        return  snsUserDao.queryMyFans(friend, page);
    }
    
    /**
     * 查询当前用户是否跟传来的userIds是好友关系，返回是好友的列表
     * @param userIds
     * @param cusId
     * @return
     */
    public List<SnsUserExpandDto> queryIsFrirndByIds(List<Long> userIds,Long cusId){
        return snsUserDao.queryIsFrirndByIds(userIds, cusId);
    }
    
    /**
	 * 查询推荐课程
	 * @param recommendId
	 * @return
	 */
	@SuppressWarnings("serial")
	public Map<String, List<Course>> getCourseListByHomePage(Long recommendId){
		Map<String, List<Course>> resultMap=new HashMap<String, List<Course>>();
		Map<String,String> map=webHessianService.getCourseListByHomePage(recommendId);
		Map<String,String> coursesMapJson=gson.fromJson(map.get("coursesMapJson"),new TypeToken<Map<String,String>>(){}.getType());
		for(Entry<String, String> entry: coursesMapJson.entrySet()) { 
			List<Course> courses=new ArrayList<Course>();
			List<String> coursesString=gson.fromJson(entry.getValue(),new TypeToken<List<String>>(){}.getType());
			for(String courseStr:coursesString){
				Map<String,String> courseMap=gson.fromJson(courseStr,new TypeToken<Map<String,String>>(){}.getType());
				Course course=new Course();
				course.setId(Long.parseLong(courseMap.get("id")));//课程id
				course.setLogo(courseMap.get("logo"));//课程封面
				course.setName(courseMap.get("name"));//课程名称
				course.setLessionnum(Long.parseLong(courseMap.get("lessionnum")));//课时
				courses.add(course);
			}
			resultMap.put(entry.getKey(),courses);
		} 
		return resultMap;
	}
	
	/**
	 * 学习专业防止为null
	 * @param map
	 * @return
	 */
	public Long getStudySubject(Map<String,String> map){
		String studySubject=map.get("studysubject");
		if(studySubject==null){
			studySubject="0";
		}
		return Long.parseLong(studySubject);
	}
	
	public Date getLastSystemTime(Map<String,String> map){
		try {
			SimpleDateFormat s=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String dateStr=map.get("lastSystemTime");
			if(dateStr==null){
				return new Date();
			}
			return s.parse(dateStr);//上传统计系统消息时间
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
}