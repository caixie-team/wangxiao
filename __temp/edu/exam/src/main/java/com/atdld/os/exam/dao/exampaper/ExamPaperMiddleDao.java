package com.atdld.os.exam.dao.exampaper;

import java.util.List;

import com.atdld.os.exam.entity.exampaper.PaperMiddle;


public interface ExamPaperMiddleDao {
    /**
     * 添加试题题目
     */
    public void addExamPaperMiddleBatch(List<PaperMiddle> paperMiddleList);

    /**
     * 获得试题题目
     */
    public List<PaperMiddle> queryPaperMiddleList(PaperMiddle paperMiddle);

    /**
     * 通过paperId删除paperMiddle
     */
    public void delPaperMiddleByPaperId(PaperMiddle paperMiddle);

    /**
     * 更新paperMiddle
     */
    public void updatePaperMiddle(PaperMiddle paperMiddle);

    /**
     * 添加paperMiddle
     */
    public void addExamPaperMiddle(PaperMiddle paperMiddle);

    /**
     * 获得该试卷paperMiddle的最大sort
     */
    public Integer queryPaperMiddleListMaxSort(PaperMiddle paperMiddle);

    /**
     * 试题上移交换两个试题的sort
     */
    public void updatePaperMiddleMoveUp(PaperMiddle paperMiddle);

	public PaperMiddle getPaperMiddleById(Long paperMiddleId);
}
