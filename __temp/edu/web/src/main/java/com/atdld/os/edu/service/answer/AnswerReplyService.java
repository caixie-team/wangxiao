package com.atdld.os.edu.service.answer;

import java.util.List;

import com.atdld.os.core.entity.PageEntity;
import com.atdld.os.edu.entity.answer.AnswerQuestion;
import com.atdld.os.edu.entity.answer.AnswerReply;

/**
 * 学生答疑回复
 * 
 * */
public interface AnswerReplyService {
	/**
	 * 添加回复
	 * */
	public void addAnswerReply(AnswerReply reply);
	/**
	 * 更新回复状态
	 * */
	public void updateAnswerReplyStatus(AnswerReply reply);
	/**
	 * 更新回复
	 * */
	public void updateAnswerReply(AnswerReply reply);
	/**
	 * 删除回复
	 * */
	public void delAnswerReply(AnswerReply reply);
	
	/**
	 * 前台查询回复集合
	 * */
	public List<AnswerReply> getAnswerReplyList(Long answerId) throws Exception;
	
	/**
	 * 后台查询回复集合 
	 * */
	public List<AnswerReply> getAdminReplyList(Long answerId);
}
