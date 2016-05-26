package com.atdld.os.exam.dao.question;

import java.util.List;

import com.atdld.os.exam.entity.question.QstMiddle;
import com.atdld.os.exam.entity.question.QueryQstMiddle;


public interface QstMiddleDao {
    /**
     * 批量添加试题于试卷中间表
     */
    public void addExamQstMiddleBatch(List<QstMiddle> qstMiddleList);

    /**
     * 批量添加qstMiddle
     */
    public void addExamQstMiddleBatchByMap(List<QstMiddle> qstMiddleList);

    /**
     * 添加试题于试卷中间表
     */
    public void addExamQstMiddle(QstMiddle qstMiddle);

    /**
     * 获得试题中间表
     */
    public List<QueryQstMiddle> queryQstMiddleList(QstMiddle qstMiddle);

    /**
     * 获得一张试卷所有试题的list
     */
    public List<Integer> queryQstMiddleListQstIds(QstMiddle qstMiddle);

    /**
     * 获得试卷记录的QstMiddle
     */
    public List<QueryQstMiddle> queryQstMiddleListBypaperRecord(QstMiddle qstMiddle);

    /**
     * 获得试题的最大sort的值
     */
    public Integer queryQstMiddleListMaxSort(QstMiddle qstMiddle);

    /**
     * 删除试题于试卷中间表信息通过id
     */
    public void delQstMiddleById(QstMiddle qstMiddle);

    /**
     * 获得材料题
     */
    public void updateQstMiddleBySort(QstMiddle qstMiddle);

}
