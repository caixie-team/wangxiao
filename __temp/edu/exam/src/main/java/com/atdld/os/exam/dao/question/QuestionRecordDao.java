package com.atdld.os.exam.dao.question;

import java.util.List;

import com.atdld.os.exam.entity.exampaper.ExamRecord;
import com.atdld.os.exam.entity.question.QuestionRecord;

/**
 * @author
 * @ClassName QuestionDao
 * @package com.atdld.os.exam.dao.question
 * @description
 * @Create Date: 2013-9-9 下午3:19:10
 */
public interface QuestionRecordDao {

    /**
     * 通过QuestionRecord的list批量添加
     */
    public void addQuestionRecordListBatch(List<QuestionRecord> questionRecordList);

    /**
     * 删除QuestionRecord
     */
    public void delQuestionRecordByPaperRecordId(QuestionRecord questionRecord);

    /**
     * 查询questionRecord获得每个试题做过多少次答对题数等
     */
    public List<QuestionRecord> queryQuestionRecordByGroupByQstId(ExamRecord examRecord);

    /**
     * 获得questionRecord的最大Id
     */
    public int queryQuestionRecordMaxId();

    /**
     * 通过id获得试题记录
     */
    public QuestionRecord queryQuestionRecordById(Long id);

    /**
     * 通过id更新分数
     */
    public void updateQuestionRecordForScore(Long id, String score);
}
