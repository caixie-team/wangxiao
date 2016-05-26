package com.atdld.os.exam.dao.exampaper;

import java.util.List;

import com.atdld.os.core.entity.PageEntity;
import com.atdld.os.exam.entity.exampaper.ExamPaper;
import com.atdld.os.exam.entity.exampaper.QueryPaper;

/**
 * @author
 * @ClassName ExamPaperDao
 * @package com.atdld.os.exam.dao.exampaper
 * @description
 * @Create Date: 2013-9-9 下午3:18:42
 */
public interface ExamPaperDao {

    /**
     * @param paperQuestion
     * @param pageEntity
     * @return
     */
    public List<QueryPaper> getPaperAllList(QueryPaper paperQuestion, PageEntity pageEntity);

    /**
     * 获得试卷分页前台
     */
    public List<QueryPaper> getPaperAllListForFront(QueryPaper queryPaper, PageEntity pageEntity);

    /**
     * 添加试卷
     */
    public void addExamPaper(ExamPaper paper);

    /**
     * 批量添加试卷
     */
    public void addExamPaperBatch(List<ExamPaper> paperList);

    /**
     * 通过Id获得试卷
     */
    public List<ExamPaper> queryExamPaperById(ExamPaper paper);

    /**
     * 通过Type获得试卷
     */
    public List<QueryPaper> queryExamPaperListByType(ExamPaper paper);

    /**
     * 修改试卷
     */
    public void updateExamPaperById(ExamPaper paper);

    /**
     * @param paperIds
     */
    void delPaperListBatch(String[] paperIds);

    /**
     * 修改试卷参加人数和平均分
     */
    public void updateExamPaperByJoinNumAndAvgscore(QueryPaper queryPaper);
}
