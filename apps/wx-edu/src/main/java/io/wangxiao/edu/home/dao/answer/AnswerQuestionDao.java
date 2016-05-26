package io.wangxiao.edu.home.dao.answer;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.edu.home.entity.answer.AnswerQuestion;

import java.util.List;

/**
 * 学生答疑提问表
 */
public interface AnswerQuestionDao {
    /**
     * 添加答疑
     */
    void addAnswerQuestion(AnswerQuestion answer);

    /**
     * 更新答疑状态
     */
    void updateAnswerQuestionStatus(AnswerQuestion answer);

    /**
     * 更新答疑状态
     */
    void updateAnswerQuestion(AnswerQuestion answer);

    /**
     * 更新回复次数
     */
    void updateReplyCount(AnswerQuestion answer);

    /**
     * 删除答疑
     */
    void delAnswerQuestion(Long id);

    /**
     * 前台查询问答集合
     */
    List<AnswerQuestion> getAnswerQuestion(AnswerQuestion answer, PageEntity page);

    /**
     * 后台查询答疑
     */
    List<AnswerQuestion> queryCourseAnswerQuestionList(AnswerQuestion answer, PageEntity page);

    /**
     * 查询答疑详情
     */
    AnswerQuestion queryAnswerQuestionInfo(AnswerQuestion answer);

    /**
     * 更新状态为已回复
     */
    void updateIsReply(Long answerId);

    /**
     * 查询我的考试答疑
     */
    List<AnswerQuestion> queryMyExamAnswerQuestionList(Long userId, PageEntity page);

    /**
     * 查询我的课程答疑
     */
    List<AnswerQuestion> queryMyCouAnswerQuestionList(Long userId, PageEntity page);
}
