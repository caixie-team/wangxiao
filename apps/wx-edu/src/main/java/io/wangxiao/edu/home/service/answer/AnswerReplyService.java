package io.wangxiao.edu.home.service.answer;

import io.wangxiao.edu.home.entity.answer.AnswerReply;

import java.util.List;

/**
 * 学生答疑回复
 */
public interface AnswerReplyService {
    /**
     * 添加回复
     */
    void addAnswerReply(AnswerReply reply);

    /**
     * 更新回复状态
     */
    void updateAnswerReplyStatus(AnswerReply reply);

    /**
     * 更新回复
     */
    void updateAnswerReply(AnswerReply reply);

    /**
     * 删除回复
     */
    void delAnswerReply(AnswerReply reply);

    /**
     * 前台查询回复集合
     */
    List<AnswerReply> getAnswerReplyList(Long answerId) throws Exception;

    /**
     * 后台查询回复集合
     */
    List<AnswerReply> getAdminReplyList(Long answerId);
}
