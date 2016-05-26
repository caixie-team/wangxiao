package com.atdld.os.edu.dao.impl.answer;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.atdld.os.core.dao.impl.common.GenericDaoImpl;
import com.atdld.os.core.entity.PageEntity;
import com.atdld.os.edu.dao.answer.AnswerQuestionDao;
import com.atdld.os.edu.dao.answer.AnswerReplyDao;
import com.atdld.os.edu.entity.answer.AnswerQuestion;
import com.atdld.os.edu.entity.answer.AnswerReply;

/**
 * 学生答疑回复
 * 
 * */
@Repository("answerReplyDao")
public class AnswerReplyDaoImpl extends GenericDaoImpl implements AnswerReplyDao{

	/**
	 * 添加回复
	 * */
	public void addAnswerReply(AnswerReply reply){
		this.insert("AnswerReplyMapper.addAnswerReply", reply);
	}
	/**
	 * 更新回复状态
	 * */
	public void updateAnswerReplyStatus(AnswerReply reply){
		this.update("AnswerReplyMapper.updateAnswerReplyStatus", reply);
	}
	/**
	 * 更新回复
	 * */
	public void updateAnswerReply(AnswerReply reply){
		this.update("AnswerReplyMapper.updateAnswerReply", reply);
	}
	
	/**
	 * 删除回复
	 * */
	public void delAnswerReply(Long id){
		this.delete("AnswerReplyMapper.deleteReply", id);
	}
	
	/**
	 * 前台查询回复集合
	 * */
	public List<AnswerReply> getAnswerReplyList(Long answerId){
		return this.selectList("AnswerReplyMapper.queryReplyList", answerId);
	}
	
	/**
	 * 后台查询回复集合 
	 * */
	public List<AnswerReply> getAdminReplyList(Long answerId){
		return this.selectList("AnswerReplyMapper.queryAdminReplyList", answerId);
	}
}
