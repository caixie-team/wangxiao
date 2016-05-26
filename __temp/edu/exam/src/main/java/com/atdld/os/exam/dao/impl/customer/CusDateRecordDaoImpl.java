package com.atdld.os.exam.dao.impl.customer;

import org.springframework.stereotype.Repository;

import com.atdld.os.core.dao.impl.common.GenericDaoImpl;
import com.atdld.os.exam.dao.customer.CusDateRecordDao;
import com.atdld.os.exam.entity.customer.CusDateRecord;

/**
 * @author
 * @ClassName CustomerDaoImpl
 * @package com.atdld.os.exam.dao.impl
 * @description
 * @Create Date: 2013-2-27 上午10:58:51
 */
@Repository("cusDateRecordDao")
public class CusDateRecordDaoImpl extends GenericDaoImpl implements CusDateRecordDao {

    public void addCusDateRecord(CusDateRecord cusDateRecord) {
        this.insert("CusDateRecordMapper.createCusDateRecord", cusDateRecord);
    }

    public CusDateRecord queryCusDateRecordByCusIdAndSubjectId(CusDateRecord cusDateRecord) {
        return this.selectOne("CusDateRecordMapper.getCusDateRecordByCusIdAndSubjectId", cusDateRecord);
    }

    public void updateCusDateRecord(CusDateRecord cusDateRecord) {
        this.update("CusDateRecordMapper.updateCusDateRecordById", cusDateRecord);
    }

    public int queryCusDateRecordAverageScoreRanking(CusDateRecord cusDateRecord) {
        return this.selectOne("CusDateRecordMapper.queryCusDateRecordAverageScoreRanking", cusDateRecord);
    }
}
