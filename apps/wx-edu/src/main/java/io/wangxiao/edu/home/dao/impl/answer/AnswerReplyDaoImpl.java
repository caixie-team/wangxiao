package io.wangxiao.edu.home.dao.impl.answer;

import io.wangxiao.commons.dao.impl.GenericDaoImpl;
import io.wangxiao.edu.home.dao.answer.AnswerReplyDao;
import io.wangxiao.edu.home.entity.answer.AnswerReply;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 学生答疑回复
 */
@Repository("answerReplyDao")
public class AnswerReplyDaoImpl extends GenericDaoImpl implements AnswerReplyDao {

    /**
     * 添加回复
     */
    public void addAnswerReply(AnswerReply reply) {
        this.insert("AnswerReplyMapper.addAnswerReply", reply);
    }

    /**
     * 更新回复状态
     */
    public void updateAnswerReplyStatus(AnswerReply reply) {
        this.update("AnswerReplyMapper.updateAnswerReplyStatus", reply);
    }

    /**
     * 更新回复
     */
    public void updateAnswerReply(AnswerReply reply) {
        this.update("AnswerReplyMapper.updateAnswerReply", reply);
    }

    /**
     * 删除回复
     */
    public void delAnswerReply(Long id) {
        this.delete("AnswerReplyMapper.deleteReply", id);
    }

    /**
     * 前台查询回复集合
     */
    public List<AnswerReply> getAnswerReplyList(Long answerId) {
        return this.selectList("AnswerReplyMapper.queryReplyList", answerId);
    }

    /**
     * 后台查询回复集合
     */
    public List<AnswerReply> getAdminReplyList(Long answerId) {
        return this.selectList("AnswerReplyMapper.queryAdminReplyList", answerId);
    }
}
