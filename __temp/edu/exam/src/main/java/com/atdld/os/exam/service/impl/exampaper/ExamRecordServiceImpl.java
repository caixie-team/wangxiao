package com.atdld.os.exam.service.impl.exampaper;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atdld.os.exam.dao.exampaper.ExamRecordDao;
import com.atdld.os.exam.entity.exampaper.ExamRecord;
import com.atdld.os.exam.service.exampaper.ExamRecordService;


@Service("examRecordService")
public class ExamRecordServiceImpl implements ExamRecordService {
    @Autowired
    private ExamRecordDao examRecordDao;

    public ExamRecord queryExamRecordByType(ExamRecord examRecord) {
        return examRecordDao.queryExamRecordByType(examRecord);
    }

    public void updateExamRecordById(ExamRecord examRecord) {
        examRecordDao.updateExamRecordById(examRecord);
    }

}
