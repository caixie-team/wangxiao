package io.wangxiao.edu.home.dao.impl.order;

import io.wangxiao.commons.dao.impl.GenericDaoImpl;
import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.edu.home.dao.order.TrxdetailOptRecordDao;
import io.wangxiao.edu.home.entity.order.TrxdetailOptRecord;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("trxdetailOptRecordDao")
public class TrxdetailOptRecordDaoImpl extends GenericDaoImpl implements TrxdetailOptRecordDao {

    public java.lang.Long addTrxdetailOptRecord(TrxdetailOptRecord trxdetailOptRecord) {
        return this.insert("TrxdetailOptRecordMapper.createTrxdetailOptRecord", trxdetailOptRecord);
    }

    public void deleteTrxdetailOptRecordById(Long id) {
        this.delete("TrxdetailOptRecordMapper.deleteTrxdetailOptRecordById", id);
    }

    public void updateTrxdetailOptRecord(TrxdetailOptRecord trxdetailOptRecord) {
        this.update("TrxdetailOptRecordMapper.updateTrxdetailOptRecord", trxdetailOptRecord);
    }

    public TrxdetailOptRecord getTrxdetailOptRecordById(Long id) {
        return this.selectOne("TrxdetailOptRecordMapper.getTrxdetailOptRecordById", id);
    }

    public List<TrxdetailOptRecord> getTrxdetailOptRecordList(TrxdetailOptRecord trxdetailOptRecord) {
        return this.selectList("TrxdetailOptRecordMapper.getTrxdetailOptRecordList", trxdetailOptRecord);
    }

    @Override
    public List<TrxdetailOptRecord> getTrxdetailOptRecordListPage(TrxdetailOptRecord trxdetailOptRecord, PageEntity page) {
        return this.queryForListPage("TrxdetailOptRecordMapper.getTrxdetailOptRecordListPage", trxdetailOptRecord, page);
    }
}
