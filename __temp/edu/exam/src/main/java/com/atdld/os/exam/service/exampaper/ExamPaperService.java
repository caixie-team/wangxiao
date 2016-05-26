package com.atdld.os.exam.service.exampaper;

import java.util.List;

import com.atdld.os.core.entity.PageEntity;
import com.atdld.os.exam.entity.exampaper.ExamRecord;
import com.atdld.os.exam.entity.exampaper.ExamPaper;
import com.atdld.os.exam.entity.exampaper.PaperRecord;
import com.atdld.os.exam.entity.exampaper.QueryPaper;
import com.atdld.os.exam.entity.question.QstComplex;
import com.atdld.os.exam.entity.question.QstMiddle;

public interface ExamPaperService {
    /**
     * 试卷列表
     */
    public List<QueryPaper> getPaperAllList(QueryPaper paperQuestion,
                                            PageEntity pageEntity);

    /**
     * 前台试卷列表
     */
    public List<QueryPaper> getPaperAllListForFront(QueryPaper paperQuestion,
                                                    PageEntity pageEntity);

    /**
     * 添加试卷
     */
    public void addExamPaper(ExamPaper paper);

    /**
     * 通过Id获得试卷
     */
    public ExamPaper queryExamPaperById(ExamPaper paper);

    /**
     * 通过Type获得试卷
     */
    public List<QueryPaper> queryExamPaperListByType(ExamPaper paper);

    /**
     * 修改试卷
     */
    public void updateExamPaperById(ExamPaper paper);

    /**
     * 批量删除
     */
    public void delPaperListBatch(String paperIds);

    /**
     * 保存试题中间表数据
     */
    public List<QstMiddle> addExamQstMiddleBatch(String qstIds, QstMiddle qstMiddle);

    /**
     * 保存材料题
     */
    public QstComplex addCaiLiaoContent(QstComplex complex);

    /**
     * 更新材料题的材料内容
     */
    public void updateCaiLiaoContent(QstComplex complex);

    /**
     * 添加材料题的试题
     */
    public QstMiddle addExamQstMiddleCaiLiao(Long qstId, Long paperId, int type,
                                             Long complexId);

    /**
     * 获得该用户试卷记录列表
     */
    public List<PaperRecord> queryExamPaperRecord(PaperRecord paperRecord);

    /**
     * @param queryPaper
     * @param page
     * @return
     */
    public List<QueryPaper> getFavoritePaper(QueryPaper queryPaper, PageEntity page);

    /**
     * 修改试卷
     */
    public void updateAllExamPaperByPaperRecord(ExamRecord examRecord);
}
