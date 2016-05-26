package com.atdld.os.exam.dao.exampaper;

import com.atdld.os.exam.entity.exampaper.ExamRecord;

/**
 * @author
 * @ClassName ExamPaperDao
 * @package com.atdld.os.exam.dao.exampaper
 * @description
 * @Create Date: 2013-9-9 下午3:18:42
 */
public interface ExamRecordDao {
    /**
     * 获得examRecord
     */
    public ExamRecord queryExamRecordByType(ExamRecord examRecord);

    /**
     * 通过id更新ExamRecord
     */
    public void updateExamRecordById(ExamRecord examRecord);
}
