package com.atdld.os.exam.service.impl.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atdld.os.exam.dao.customer.CusDateRecordDao;
import com.atdld.os.exam.entity.customer.CusDateRecord;
import com.atdld.os.exam.service.customer.CusDateRecordService;

@Service("cusDateRecordService")
public class CusDateRecordServiceImpl implements CusDateRecordService {
    @Autowired
    private CusDateRecordDao cusDateRecordDao;

    public CusDateRecord queryCusDateRecordByCusIdAndSubjectId(CusDateRecord cusDateRecord) {
        return cusDateRecordDao.queryCusDateRecordByCusIdAndSubjectId(cusDateRecord);
    }

    public int queryCusDateRecordAverageScoreRanking(CusDateRecord cusDateRecord) {
        return cusDateRecordDao.queryCusDateRecordAverageScoreRanking(cusDateRecord);
    }

}
