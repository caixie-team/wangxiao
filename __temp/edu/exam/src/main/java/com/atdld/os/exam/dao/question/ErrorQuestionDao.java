package com.atdld.os.exam.dao.question;

import java.util.List;

import com.atdld.os.exam.entity.question.QueryErrorQuestion;


public interface ErrorQuestionDao {
    //批量添加queryErrorQuestion
    public void insertQueryErrorQuestion(List<QueryErrorQuestion> queryErrorQuestionList);

    //通过paperRecordId批量删除queryErrorQuestion
    public void delQueryErrorQuestionByPaperRecordId(QueryErrorQuestion queryErrorQuestion);

    //通过paperRecordId批量删除queryErrorQuestion
    public List<QueryErrorQuestion> queryErrorQuestionByQstId(List<QueryErrorQuestion> queryErrorQuestionList, Long cusId);

    //通过正确试题的list批量删除queryErrorQuestion
    public void delQueryErrorQuestionBatchByQueryErrorQuestionList(List<QueryErrorQuestion> queryErrorQuestionList, Long cusId);
}
