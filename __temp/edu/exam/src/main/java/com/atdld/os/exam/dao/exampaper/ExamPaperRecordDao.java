package com.atdld.os.exam.dao.exampaper;

import java.util.Date;
import java.util.List;

import com.atdld.os.core.entity.PageEntity;
import com.atdld.os.exam.entity.exampaper.PaperRecord;
import com.atdld.os.exam.entity.exampaper.QueryPaperMiddle;
import com.atdld.os.exam.entity.exampaper.QueryPaperRecord;

/**
 * @author
 * @ClassName ExamPaperDao
 * @package com.atdld.os.exam.dao.exampaper
 * @description
 * @Create Date: 2013-9-9 下午3:18:42
 */
public interface ExamPaperRecordDao {
    /**
     * 添加PaperRecord
     */
    public void addExamPaperRecord(PaperRecord paperRecord);

    /**
     * 获得试题题目
     */
    public PaperRecord queryPaperRecordById(PaperRecord paperRecord);

    /**
     * 通过paperRecordId和cusId获得用户试卷记录
     */
    public PaperRecord queryPaperRecordByIdAndCusId(PaperRecord paperRecord);

    /**
     * 获得试卷记录列表
     */
    public List<QueryPaperRecord> queryExamPaperRecordList(PaperRecord paperRecord, PageEntity pageEntity);

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
     * 通过条件获得包含主观题的试卷记录列表
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
    public List<QueryPaperMiddle> queryPaperMiddleMap(Long paperId);

    /**
     * 批量添加试卷记录
     */
    public void addExamPaperRecordBatch(List<PaperRecord> paperRecordList);

    /**
     * 查看单张试卷做的结果
     *
     * @param paperRecord
     * @return
     */
    public PaperRecord checkPaperResult(PaperRecord paperRecord);

    /*获取单张考试试卷的结果*/
    public int queryPaperRecordAverageScore(PaperRecord paperRecord);

    /**
     * 更新paperRecord
     */
    public void updateExamPaperRecordById(PaperRecord paperRecord);

    /**
     * 查询为考完的paperRecord的最新的一个
     */
    public QueryPaperRecord queryPaperRecordByCusIdNewest(PaperRecord paperRecord);

    /**
     * 查看试卷报告Report
     */
    public List<QueryPaperRecord> getExamPaperReport(PaperRecord paperRecord);

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
     * 通过试卷记录表获得每个试卷考试人数平均分
     */
    public List<QueryPaperRecord> queryExamPaperRecordGroupByEPId(QueryPaperRecord queryPaperRecord);

    /**
     * 通过试卷记录表获得最大的id
     */
    public Long queryExamPaperRecordMaxId();

    /**
     * 查询出最大的更新时间
     */
    public Date queryExamPaperRecordMaxUpdateTime();

    /**
     * 更新paperRecord的分数
     */
    public void updateExamPaperRecordForScore(Long id, String score);
}
