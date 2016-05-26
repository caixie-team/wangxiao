package io.wangxiao.edu.home.service.impl.order;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.edu.home.dao.order.TrxorderOptRecordDao;
import io.wangxiao.edu.home.entity.order.TrxorderOptRecord;
import io.wangxiao.edu.home.service.order.TrxorderOptRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * TrxorderOptRecord管理接口
 */
@Service("trxorderOptRecordService")
public class TrxorderOptRecordServiceImpl implements TrxorderOptRecordService {

    @Autowired
    private TrxorderOptRecordDao trxorderOptRecordDao;

    /**
     * 添加TrxorderOptRecord
     *
     * @param trxorderOptRecord 要添加的TrxorderOptRecord
     * @return id
     */
    public java.lang.Long addTrxorderOptRecord(TrxorderOptRecord trxorderOptRecord) {
        return trxorderOptRecordDao.addTrxorderOptRecord(trxorderOptRecord);
    }

    /**
     * 根据id删除一个TrxorderOptRecord
     *
     * @param id 要删除的id
     */
    public void deleteTrxorderOptRecordById(Long id) {
        trxorderOptRecordDao.deleteTrxorderOptRecordById(id);
    }

    /**
     * 修改TrxorderOptRecord
     *
     * @param trxorderOptRecord 要修改的TrxorderOptRecord
     */
    public void updateTrxorderOptRecord(TrxorderOptRecord trxorderOptRecord) {
        trxorderOptRecordDao.updateTrxorderOptRecord(trxorderOptRecord);
    }

    /**
     * 根据id获取单个TrxorderOptRecord对象
     *
     * @param id 要查询的id
     * @return TrxorderOptRecord
     */
    public TrxorderOptRecord getTrxorderOptRecordById(Long id) {
        return trxorderOptRecordDao.getTrxorderOptRecordById(id);
    }

    /**
     * 根据条件获取TrxorderOptRecord列表
     *
     * @param trxorderOptRecord 查询条件
     * @return List<TrxorderOptRecord>
     */
    public List<TrxorderOptRecord> getTrxorderOptRecordList(TrxorderOptRecord trxorderOptRecord) {
        return trxorderOptRecordDao.getTrxorderOptRecordList(trxorderOptRecord);
    }

    @Override
    public List<TrxorderOptRecord> getTrxorderOptRecordListPage(TrxorderOptRecord trxorderOptRecord, PageEntity page) {
        return trxorderOptRecordDao.getTrxorderOptRecordListPage(trxorderOptRecord, page);
    }
}