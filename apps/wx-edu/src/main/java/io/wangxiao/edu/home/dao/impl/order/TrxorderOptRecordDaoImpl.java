package io.wangxiao.edu.home.dao.impl.order;

import io.wangxiao.commons.dao.impl.GenericDaoImpl;
import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.edu.home.dao.order.TrxorderOptRecordDao;
import io.wangxiao.edu.home.entity.order.TrxorderOptRecord;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("trxorderOptRecordDao")
public class TrxorderOptRecordDaoImpl extends GenericDaoImpl implements TrxorderOptRecordDao {

    public java.lang.Long addTrxorderOptRecord(TrxorderOptRecord trxorderOptRecord) {
        return this.insert("TrxorderOptRecordMapper.createTrxorderOptRecord", trxorderOptRecord);
    }

    public void deleteTrxorderOptRecordById(Long id) {
        this.delete("TrxorderOptRecordMapper.deleteTrxorderOptRecordById", id);
    }

    public void updateTrxorderOptRecord(TrxorderOptRecord trxorderOptRecord) {
        this.update("TrxorderOptRecordMapper.updateTrxorderOptRecord", trxorderOptRecord);
    }

    public TrxorderOptRecord getTrxorderOptRecordById(Long id) {
        return this.selectOne("TrxorderOptRecordMapper.getTrxorderOptRecordById", id);
    }

    public List<TrxorderOptRecord> getTrxorderOptRecordList(TrxorderOptRecord trxorderOptRecord) {
        return this.selectList("TrxorderOptRecordMapper.getTrxorderOptRecordList", trxorderOptRecord);
    }

    @Override
    public List<TrxorderOptRecord> getTrxorderOptRecordListPage(TrxorderOptRecord trxorderOptRecord, PageEntity page) {
        return this.queryForListPage("TrxorderOptRecordMapper.getTrxorderOptRecordListPage", trxorderOptRecord, page);
    }
}
