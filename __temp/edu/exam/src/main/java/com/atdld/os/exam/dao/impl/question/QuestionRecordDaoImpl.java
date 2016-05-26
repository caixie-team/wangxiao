package com.atdld.os.exam.dao.impl.question;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.atdld.os.core.dao.impl.common.GenericDaoImpl;
import com.atdld.os.exam.dao.question.QuestionRecordDao;
import com.atdld.os.exam.entity.exampaper.ExamRecord;
import com.atdld.os.exam.entity.question.QuestionRecord;


@Repository("questionRecordDao")
public class QuestionRecordDaoImpl extends GenericDaoImpl implements QuestionRecordDao {

    public void addQuestionRecordListBatch(List<QuestionRecord> questionRecordList) {
        Map<String, List<QuestionRecord>> map = new HashMap<String, List<QuestionRecord>>();
        map.put("questionRecordList", questionRecordList);
        this.insert("QuestionRecordMapper.createQuestionRecord", map);
    }

    public void delQuestionRecordByPaperRecordId(QuestionRecord questionRecord) {
        this.delete("QuestionRecordMapper.delQuestionRecordByPaperRecordId", questionRecord);
    }

    public List<QuestionRecord> queryQuestionRecordByGroupByQstId(ExamRecord examRecord) {
        return this.selectList("QuestionRecordMapper.queryQuestionRecordByGroupByQstId", examRecord);
    }

    public int queryQuestionRecordMaxId() {
        return this.selectOne("QuestionRecordMapper.queryQuestionRecordMaxId", null);
    }

    /**
     * 通过id获得试题记录
     */
    public QuestionRecord queryQuestionRecordById(Long id) {
        return this.selectOne("QuestionRecordMapper.queryQuestionRecordById", id);
    }

    /**
     * 通过id更新分数
     */
    public void updateQuestionRecordForScore(Long id, String score) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", id);
        map.put("score", score);
        this.update("QuestionRecordMapper.updateQuestionRecordForScore", map);
    }
}
