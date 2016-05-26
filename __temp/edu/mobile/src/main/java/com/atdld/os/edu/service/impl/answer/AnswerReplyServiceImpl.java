package com.atdld.os.edu.service.impl.answer;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atdld.os.core.entity.PageEntity;
import com.atdld.os.core.util.ObjectUtils;
import com.atdld.os.edu.dao.answer.AnswerReplyDao;
import com.atdld.os.edu.entity.answer.AnswerQuestion;
import com.atdld.os.edu.entity.answer.AnswerReply;
import com.atdld.os.edu.entity.user.UserExpandDto;
import com.atdld.os.edu.service.answer.AnswerQuestionService;
import com.atdld.os.edu.service.answer.AnswerReplyService;
import com.atdld.os.edu.service.user.UserExpandService;

/**
 * 学生答疑回复
 * 
 * */
@Service("answerReplyService")
public class AnswerReplyServiceImpl implements AnswerReplyService{
	@Autowired
	private AnswerReplyDao answerReplyDao;
	@Autowired
	private UserExpandService userExpandService;
	@Autowired
	private AnswerQuestionService answerQuestionService;
	
	/**
	 * 添加回复
	 * */
	public void addAnswerReply(AnswerReply reply){
		answerReplyDao.addAnswerReply(reply);
		AnswerQuestion answer =new AnswerQuestion();
		answer.setId(reply.getAnswerId());
		answer =answerQuestionService.queryAnswerQuestionInfo(answer);
		answer.setReplyCount(answer.getReplyCount()+1);
		answerQuestionService.updateReplyCount(answer);
	}
	/**
	 * 更新回复状态
	 * */
	public void updateAnswerReplyStatus(AnswerReply reply){
		answerReplyDao.updateAnswerReplyStatus(reply);
		/**
		 * 如果是恢复正常，则回复次数加一
		 * */
		AnswerQuestion answer=new AnswerQuestion();
		answer.setId(reply.getAnswerId());
		answer =answerQuestionService.queryAnswerQuestionInfo(answer);
		answer.setId(reply.getAnswerId());
		if(reply.getStatus()==0){
			answer.setReplyCount(answer.getReplyCount()+1);
		}else{
			if(answer.getReplyCount()>0){
				answer.setReplyCount(answer.getReplyCount()-1);
			}else{
				answer.setReplyCount(0);
			}
		}
		answerQuestionService.updateReplyCount(answer);
	}
	/**
	 * 更新回复
	 * */
	public void updateAnswerReply(AnswerReply reply){
		answerReplyDao.updateAnswerReply(reply);
	}
	/**
	 * 删除回复
	 * */
	public void delAnswerReply(AnswerReply reply){
		answerReplyDao.delAnswerReply(reply.getId());
		/**
		 * 则回复次数减一
		 * */
		AnswerQuestion answer=new AnswerQuestion();
		answer.setId(reply.getAnswerId());
		answer =answerQuestionService.queryAnswerQuestionInfo(answer);
		answer.setId(reply.getAnswerId());
		if(answer.getReplyCount()>0){
			answer.setReplyCount(answer.getReplyCount()-1);
		}else{
			answer.setReplyCount(0);
		}
		answerQuestionService.updateReplyCount(answer);
	}
	
	/**
	 * 前台查询回复集合
	 * @throws Exception 
	 * */
	public List<AnswerReply> getAnswerReplyList(Long answerId) throws Exception{
		List<AnswerReply> replyList= answerReplyDao.getAnswerReplyList(answerId);
		 if (ObjectUtils.isNotNull(replyList)) {
	            String cusIdList = "";
	            for (AnswerReply reply : replyList) {
	            	if(reply.getUserId().intValue()!=0){
	            		cusIdList = cusIdList + reply.getUserId() + ",";
	            	}
	            }
	            if(cusIdList!=null && cusIdList!=""){
	            	 cusIdList = cusIdList.substring(0, cusIdList.length() - 1);
	            }
	            Map<String, UserExpandDto> map = userExpandService.getUserExpandByUids(cusIdList);// 查询用户的信息
	            if (ObjectUtils.isNotNull(map)) {
	                for (AnswerReply reply : replyList) {
	                	if(reply.getUserId().intValue()!=0){
		                    UserExpandDto user = map.get(reply.getUserId() + "");
		                    if (user != null) {// 如果能够查到则set 头像信息
		                        reply.setUserExpandDto(user);
		                    }
	                	}
	                }
	            }
	        }
		return replyList;
	}
	
	/**
	 * 后台查询回复集合 
	 * */
	public List<AnswerReply> getAdminReplyList(Long answerId){
		return answerReplyDao.getAdminReplyList(answerId);
	}
}
