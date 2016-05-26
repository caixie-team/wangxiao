package com.atdld.os.exam.dao.customer;

import com.atdld.os.exam.entity.customer.CusDateRecord;


public interface CusDateRecordDao {
    /**
     * 添加试卷
     */
    public void addCusDateRecord(CusDateRecord cusDateRecord);

    /**
     * 通过cusId和subjectId查找CusDateRecord
     */
    public CusDateRecord queryCusDateRecordByCusIdAndSubjectId(CusDateRecord cusDateRecord);

    /**
     * 更新CusDateRecord
     */
    public void updateCusDateRecord(CusDateRecord cusDateRecord);

    /**
     * 通过cusId和subjectId查找CusDateRecord的个人平均分排名
     */
    public int queryCusDateRecordAverageScoreRanking(CusDateRecord cusDateRecord);
}
