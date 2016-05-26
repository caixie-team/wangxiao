package com.atdld.os.edu.dao.impl.order;

import java.util.List;
import com.atdld.os.edu.entity.order.TrxdetailOptRecord;
import com.atdld.os.edu.dao.order.TrxdetailOptRecordDao;
import org.springframework.stereotype.Repository;
import com.atdld.os.core.dao.impl.common.GenericDaoImpl;

/**
 *
 * TrxdetailOptRecord
 * User:
 * Date: 2014-05-27
 */
 @Repository("trxdetailOptRecordDao")
public class TrxdetailOptRecordDaoImpl extends GenericDaoImpl implements TrxdetailOptRecordDao{

    public java.lang.Long addTrxdetailOptRecord(TrxdetailOptRecord trxdetailOptRecord) {
        return this.insert("TrxdetailOptRecordMapper.createTrxdetailOptRecord",trxdetailOptRecord);
    }

    public void deleteTrxdetailOptRecordById(Long id){
        this.delete("TrxdetailOptRecordMapper.deleteTrxdetailOptRecordById",id);
    }

    public void updateTrxdetailOptRecord(TrxdetailOptRecord trxdetailOptRecord) {
        this.update("TrxdetailOptRecordMapper.updateTrxdetailOptRecord",trxdetailOptRecord);
    }

    public TrxdetailOptRecord getTrxdetailOptRecordById(Long id) {
        return this.selectOne("TrxdetailOptRecordMapper.getTrxdetailOptRecordById",id);
    }

    public List<TrxdetailOptRecord> getTrxdetailOptRecordList(TrxdetailOptRecord trxdetailOptRecord) {
        return this.selectList("TrxdetailOptRecordMapper.getTrxdetailOptRecordList",trxdetailOptRecord);
    }
}
