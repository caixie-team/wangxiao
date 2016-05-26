package com.atdld.os.exam.service.exampaper;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.atdld.os.core.entity.PageEntity;
import com.atdld.os.exam.entity.exampaper.PaperRecord;
import com.atdld.os.exam.entity.exampaper.QueryPaperMiddle;
import com.atdld.os.exam.entity.exampaper.QueryPaperRecord;
import com.atdld.os.exam.entity.question.QuestionRecordBean;

/**
 * @author :
 * @ClassName com.atdld.os.exam.service.exampaper.ExamPaperRecordService
 * @description
 * @Create Date : 2014-4-14 上午10:14:43
 */
public interface ExamPaperRecordService {
    /**
     * 获得该用户试卷记录列表
     */
    public List<QueryPaperRecord> queryExamPaperRecordList(PaperRecord paperRecord,
                                                           PageEntity pageEntity);


    /**
     * 通过条件获得试卷记录列表
     *
     * @param queryPaperRecord
     * @param pageEntity
     * @return
     */
    public List<QueryPaperRecord> queryExamPaperRecordAllList(QueryPaperRecord queryPaperRecord,
                                                              PageEntity pageEntity);

    /**
     * 通过条件获得包含主观题的试卷列表
     *
     * @param queryPaperRecord
     * @param pageEntity
     * @return
     */
    public List<QueryPaperRecord> queryExamPaperRecordAllListBySubjective(QueryPaperRecord queryPaperRecord,
                                                                          PageEntity pageEntity);


    /**
     * 通过试卷id查询每个试题多少分
     *
     * @param queryPaperRecord
     * @param pageEntity
     * @return
     */
    public Map<Long, QueryPaperMiddle> queryPaperMiddleMap(Long paperId);

    /**
     * 获取用户做完试卷后的结果
     *
     * @param paperRecord
     * @return
     */
    public PaperRecord queryCheckPaperResult(PaperRecord paperRecord);

    /**
     * 专项智能练习添加试卷 添加试卷记录 添加试题记录
     */
    public void addRandomPaperRecord(String paperTitle,
                                     List<QuestionRecordBean> recordBeans, PaperRecord paperRecord);

    /**
     * 专项智能练习更新试卷 更新试卷记录 更新试题记录
     */
    public void updateRandomPaperRecord(List<QuestionRecordBean> recordBeans,
                                        PaperRecord paperRecord);

    /**
     * 试题组卷 添加试卷记录 添加试题记录 PMIdList只有在考 组卷考试的时候用到
     */

    public void addPaperRecord(List<QuestionRecordBean> recordBeans,
                               PaperRecord paperRecord);

    /**
     * 试题组卷 更新试卷记录 添加试题记录
     */
    public void updatePaperRecord(List<QuestionRecordBean> recordBeans,
                                  PaperRecord paperRecord);

    /**
     * 获得该用户在paperRecord中未考完试卷中的最新的一个
     */
    public QueryPaperRecord continuePractise(PaperRecord paperRecord);

    /**
     * 查看试卷报告Report
     */
    public List<QueryPaperRecord> getExamPaperReport(PaperRecord paperRecord);

    public PaperRecord queryPaperRecordById(PaperRecord paperRecord);

    /**
     * 值传入cusId获得试卷考试记录
     */
    public List<QueryPaperRecord> queryExamPaperRecordListByCusId(PaperRecord paperRecord);

    /**
     * 获得该试卷的分数排行
     */
    public int queryExamPaperRecordScoreRanking(PaperRecord paperRecord);

    /**
     * 获得该试卷的正确题数排行
     */
    public int queryExamPaperRecordCorrectNumRanking(PaperRecord paperRecord);

    /**
     * 查询出最大的更新时间
     */
    public Date queryExamPaperRecordMaxUpdateTime();

    /**
     * 给学生阅卷加分
     */
    public void updateExampaperRecordAddScore(Long qstrcdId, Integer score);
}
