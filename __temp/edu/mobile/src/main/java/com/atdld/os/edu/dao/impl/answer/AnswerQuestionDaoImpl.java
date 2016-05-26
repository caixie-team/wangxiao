package com.atdld.os.edu.dao.impl.answer;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.atdld.os.core.dao.impl.common.GenericDaoImpl;
import com.atdld.os.core.entity.PageEntity;
import com.atdld.os.edu.dao.answer.AnswerQuestionDao;
import com.atdld.os.edu.entity.answer.AnswerQuestion;

/**
 * 学生答疑提问表
 * 
 * */
@Repository("answerQuestionDao")
public class AnswerQuestionDaoImpl extends GenericDaoImpl implements AnswerQuestionDao{
	/**
	 * 添加答疑
	 * */
	public void addAnswerQuestion(AnswerQuestion answer){
		this.insert("AnswerQuestionMapper.createAnswerQuestion", answer);
	}
	/**
	 * 更新答疑状态
	 * */
	public void updateAnswerQuestionStatus(AnswerQuestion answer){
		this.update("AnswerQuestionMapper.updateAnswerQuestionStatus", answer);
	}
	/**
	 * 更新答疑状态
	 * */
	public void updateAnswerQuestion(AnswerQuestion answer){
		this.update("AnswerQuestionMapper.updateAnswerQuestion", answer);
	}
	/**
	 * 更新回复次数
	 * */
	public void updateReplyCount(AnswerQuestion answer){
		this.update("AnswerQuestionMapper.updateReplyCount",answer);
	}
	/**
	 * 删除答疑
	 * */
	public void delAnswerQuestion(Long id){
		this.delete("AnswerQuestionMapper.deleteAnswerQuestion", id);
	}
	/**
	 * 前台查询问答集合
	 * */
	public List<AnswerQuestion> getAnswerQuestion(AnswerQuestion answer,PageEntity page){
		return this.queryForListPage("AnswerQuestionMapper.queryAnswerQuestionList", answer, page);
	}
	
	/**
	 * 后台查询答疑
	 * */
	public List<AnswerQuestion> queryCourseAnswerQuestionList(AnswerQuestion answer,PageEntity page){
		return this.queryForListPage("AnswerQuestionMapper.queryAdminCourseAnswerQuestionList", answer, page);
	}
	
	/**
	 * 查询答疑详情
	 * */
	public AnswerQuestion queryAnswerQuestionInfo(AnswerQuestion answer){
		return this.selectOne("AnswerQuestionMapper.queryAnswerById", answer);
	}
	
	/**
	 * 更新状态为已回复
	 * */
	public void updateIsReply(Long answerId){
		this.update("AnswerQuestionMapper.updateIsReply", answerId); 
	}
	
	/**
	 * 查询我的考试答疑
	 * */
	public List<AnswerQuestion> queryMyExamAnswerQuestionList(Long userId,PageEntity page){
		return this.queryForListPage("AnswerQuestionMapper.queryMyExamAnswerQuestionList", userId, page);
	}
	/**
	 * 查询我的课程答疑
	 * */
	public List<AnswerQuestion> queryMyCouAnswerQuestionList(Long userId,PageEntity page){
		return this.queryForListPage("AnswerQuestionMapper.queryMyCouAnswerQuestionList", userId, page);
	}
}
