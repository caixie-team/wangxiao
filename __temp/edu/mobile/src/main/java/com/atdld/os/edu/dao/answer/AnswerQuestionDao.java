package com.atdld.os.edu.dao.answer;

import java.util.List;

import com.atdld.os.core.entity.PageEntity;
import com.atdld.os.edu.entity.answer.AnswerQuestion;

/**
 * 学生答疑提问表
 * 
 * */
public interface AnswerQuestionDao {
	/**
	 * 添加答疑
	 * */
	public void addAnswerQuestion(AnswerQuestion answer);
	/**
	 * 更新答疑状态
	 * */
	public void updateAnswerQuestionStatus(AnswerQuestion answer);
	
	/**
	 * 更新答疑状态
	 * */
	public void updateAnswerQuestion(AnswerQuestion answer);
	/**
	 * 更新回复次数
	 * */
	public void updateReplyCount(AnswerQuestion answer);
	
	/**
	 * 删除答疑
	 * */
	public void delAnswerQuestion(Long id);
	
	/**
	 * 前台查询问答集合
	 * */
	public List<AnswerQuestion> getAnswerQuestion(AnswerQuestion answer,PageEntity page);
	
	/**
	 * 后台查询答疑
	 * */
	public List<AnswerQuestion> queryCourseAnswerQuestionList(AnswerQuestion answer,PageEntity page);
	
	/**
	 * 查询答疑详情
	 * */
	public AnswerQuestion queryAnswerQuestionInfo(AnswerQuestion answer);
	
	/**
	 * 更新状态为已回复
	 * */
	public void updateIsReply(Long answerId); 
	
	/**
	 * 查询我的考试答疑
	 * */
	public List<AnswerQuestion> queryMyExamAnswerQuestionList(Long userId,PageEntity page);
	/**
	 * 查询我的课程答疑
	 * */
	public List<AnswerQuestion> queryMyCouAnswerQuestionList(Long userId,PageEntity page);
}
