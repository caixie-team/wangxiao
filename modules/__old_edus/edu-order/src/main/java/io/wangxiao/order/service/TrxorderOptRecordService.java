package io.wangxiao.order.service;

import io.wangxiao.order.entity.TrxorderOptRecord;

import java.util.List;

/**
 * TrxorderOptRecord管理接口
 * User:
 * Date: 2014-05-27
 */
public interface TrxorderOptRecordService {

    /**
     * 添加TrxorderOptRecord
     * @param trxorderOptRecord 要添加的TrxorderOptRecord
     * @return id
     */
    Long addTrxorderOptRecord(TrxorderOptRecord trxorderOptRecord);

    /**
     * 根据id删除一个TrxorderOptRecord
     * @param id 要删除的id
     */
    void deleteTrxorderOptRecordById(Long id);

    /**
     * 修改TrxorderOptRecord
     * @param trxorderOptRecord 要修改的TrxorderOptRecord
     */
    void updateTrxorderOptRecord(TrxorderOptRecord trxorderOptRecord);

    /**
     * 根据id获取单个TrxorderOptRecord对象
     * @param id 要查询的id
     * @return TrxorderOptRecord
     */
    TrxorderOptRecord getTrxorderOptRecordById(Long id);

    /**
     * 根据条件获取TrxorderOptRecord列表
     * @param trxorderOptRecord 查询条件
     * @return List<TrxorderOptRecord>
     */
    List<TrxorderOptRecord> getTrxorderOptRecordList(TrxorderOptRecord trxorderOptRecord);
}