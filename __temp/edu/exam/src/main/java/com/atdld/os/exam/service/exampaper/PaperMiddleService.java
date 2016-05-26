package com.atdld.os.exam.service.exampaper;

import java.util.List;

import com.atdld.os.exam.entity.exampaper.PaperMiddle;
import com.atdld.os.exam.entity.exampaper.PaperRecord;
import com.atdld.os.exam.entity.question.QueryQuestion;

public interface PaperMiddleService {
    /**
     * 通过paperId获得试卷列表
     */
    public List<PaperMiddle> getPaperMiddleListByPaperId(Long paperId, Long cusId);

    /**
     * 更新paperMiddle
     */
    public void updatePaperMiddle(PaperMiddle paperMiddle);

    /**
     * 通过complex_id删除 QstMiddle
     */
    public void delQstMiddleBycomplexId(Long paperId, Long complexId);

    /**
     * 添加PaperMiddle
     */
    public void addPaperMiddle(PaperMiddle paperMiddle);

    /**
     * 试题上移交换两个试题的sort
     */
    public void updatePaperMiddleMoveUp(int oneSort, Long onePMId, int twotSort,
                                        Long twoPMId);

    /**
     * 通过id删除 QstMiddle
     */
    public void delQstMiddleById(PaperMiddle paperMiddle);

    /**
     * 通过paperId获得用户试卷记录
     */
    public List<PaperMiddle> getPaperMiddleListByExamPaperRecord(PaperRecord paperRecord);

    /**
     * 通过paperRecordId获得用户试卷记录
     */
    public PaperRecord queryPaperRecordById(PaperRecord paperRecord);

    /**
     * 通过paperRecordId和cusId获得用户试卷记录
     */
    public PaperRecord queryPaperRecordByIdAndCusId(PaperRecord paperRecord);

    /**
     * 获得用户随机组试卷记录
     */
    public List<QueryQuestion> getRandomQuestionByExamPaperRecord(PaperRecord paperRecord);
    /**
     * 查询paperMiddle
     */
    public PaperMiddle getPaperMiddleById(Long paperMiddleId);
}
