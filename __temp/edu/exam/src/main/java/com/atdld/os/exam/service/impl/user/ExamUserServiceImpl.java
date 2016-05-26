package com.atdld.os.exam.service.impl.user;



import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.atdld.os.common.service.WebHessianService;
import com.atdld.os.exam.entity.customer.ExamUserExpandDto;
import com.atdld.os.exam.service.user.ExamUserService;

/**
 * @author :
 * @ClassName com.atdld.os.Exam.service.impl.common.CommonServiceImpl
 * @description 博客serviceImpl
 * @Create Date : 2013-12-30 上午10:27:07
 */
@Service("examUserService")
public class ExamUserServiceImpl implements ExamUserService{
	@Autowired
    private WebHessianService webHessianService;
    private Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    /**
     * 用户详细信息
     * @param cusId
     * @return
     */
    @SuppressWarnings("serial")
	public ExamUserExpandDto getUserExpandByCusId(Long cusId){
    	Map<String,String> map=webHessianService.getUserInfo(cusId);
    	ExamUserExpandDto userExpandDto=new ExamUserExpandDto();
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
     * @param cusId
     * @return
     */
    @SuppressWarnings("serial")
	public Map<String, ExamUserExpandDto> getUserExpandsByCusId(String cusIds){
    	Map<String,String> map=webHessianService.getUserInfoByUids(cusIds);
    	Map<String, ExamUserExpandDto> userMap=new HashMap<String, ExamUserExpandDto>();
    	//usersString集合
		List<String> usersString=gson.fromJson(map.get("usersJson"), new TypeToken<List<String>>(){}.getType());
    	for(String userString:usersString){
    		ExamUserExpandDto userExpandDto=new ExamUserExpandDto();
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
    
   
	
	@SuppressWarnings("serial")
	public List<ExamUserExpandDto> forSetUserExpandDtos(List<String> usersString){
		List<ExamUserExpandDto> usersTemp=new ArrayList<ExamUserExpandDto>();
		for(String userString:usersString){
			Map<String,String> mapObject=gson.fromJson(userString, new TypeToken<Map<String,String>>(){}.getType());
    		ExamUserExpandDto userExpandDto=new ExamUserExpandDto();
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