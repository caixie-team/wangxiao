package com.atdld.os.exam.service.exampaper;

import com.atdld.os.exam.entity.exampaper.ExamRecord;

public interface ExamRecordService {
    /**
     * 获得examRecord
     */
    public ExamRecord queryExamRecordByType(ExamRecord examRecord);

    /**
     * 通过id更新ExamRecord
     */
    public void updateExamRecordById(ExamRecord examRecord);
}
