package com.atdld.os.edu.dao.impl.order;

import java.util.List;
import com.atdld.os.edu.entity.order.TrxorderOptRecord;
import com.atdld.os.edu.dao.order.TrxorderOptRecordDao;
import org.springframework.stereotype.Repository;
import com.atdld.os.core.dao.impl.common.GenericDaoImpl;

/**
 *
 * TrxorderOptRecord
 * User:
 * Date: 2014-05-27
 */
 @Repository("trxorderOptRecordDao")
public class TrxorderOptRecordDaoImpl extends GenericDaoImpl implements TrxorderOptRecordDao{

    public java.lang.Long addTrxorderOptRecord(TrxorderOptRecord trxorderOptRecord) {
        return this.insert("TrxorderOptRecordMapper.createTrxorderOptRecord",trxorderOptRecord);
    }

    public void deleteTrxorderOptRecordById(Long id){
        this.delete("TrxorderOptRecordMapper.deleteTrxorderOptRecordById",id);
    }

    public void updateTrxorderOptRecord(TrxorderOptRecord trxorderOptRecord) {
        this.update("TrxorderOptRecordMapper.updateTrxorderOptRecord",trxorderOptRecord);
    }

    public TrxorderOptRecord getTrxorderOptRecordById(Long id) {
        return this.selectOne("TrxorderOptRecordMapper.getTrxorderOptRecordById",id);
    }

    public List<TrxorderOptRecord> getTrxorderOptRecordList(TrxorderOptRecord trxorderOptRecord) {
        return this.selectList("TrxorderOptRecordMapper.getTrxorderOptRecordList",trxorderOptRecord);
    }
}
