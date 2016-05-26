package io.wangxiao.edu.home.dao.order;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.edu.home.entity.order.TrxorderOptRecord;

import java.util.List;

/**
 * TrxorderOptRecord管理接口
 */
public interface TrxorderOptRecordDao {

    /**
     * 添加TrxorderOptRecord
     *
     * @param trxorderOptRecord 要添加的TrxorderOptRecord
     * @return id
     */
    public java.lang.Long addTrxorderOptRecord(TrxorderOptRecord trxorderOptRecord);

    /**
     * 根据id删除一个TrxorderOptRecord
     *
     * @param id 要删除的id
     */
    public void deleteTrxorderOptRecordById(Long id);

    /**
     * 修改TrxorderOptRecord
     *
     * @param trxorderOptRecord 要修改的TrxorderOptRecord
     */
    public void updateTrxorderOptRecord(TrxorderOptRecord trxorderOptRecord);

    /**
     * 根据id获取单个TrxorderOptRecord对象
     *
     * @param id 要查询的id
     * @return TrxorderOptRecord
     */
    public TrxorderOptRecord getTrxorderOptRecordById(Long id);

    /**
     * 根据条件获取TrxorderOptRecord列表
     *
     * @param trxorderOptRecord 查询条件
     * @return List<TrxorderOptRecord>
     */
    public List<TrxorderOptRecord> getTrxorderOptRecordList(TrxorderOptRecord trxorderOptRecord);

    /**
     * 根据条件获取TrxorderOptRecord列表
     *
     * @param trxorderOptRecord 查询条件
     * @return List<TrxorderOptRecord>
     */
    public List<TrxorderOptRecord> getTrxorderOptRecordListPage(TrxorderOptRecord trxorderOptRecord, PageEntity page);
}