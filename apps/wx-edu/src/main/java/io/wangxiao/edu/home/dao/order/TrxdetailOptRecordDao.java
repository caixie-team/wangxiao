package io.wangxiao.edu.home.dao.order;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.edu.home.entity.order.TrxdetailOptRecord;

import java.util.List;

/**
 * TrxdetailOptRecord管理接口
 */
public interface TrxdetailOptRecordDao {

    /**
     * 添加TrxdetailOptRecord
     *
     * @param trxdetailOptRecord 要添加的TrxdetailOptRecord
     * @return id
     */
    java.lang.Long addTrxdetailOptRecord(TrxdetailOptRecord trxdetailOptRecord);

    /**
     * 根据id删除一个TrxdetailOptRecord
     *
     * @param id 要删除的id
     */
    void deleteTrxdetailOptRecordById(Long id);

    /**
     * 修改TrxdetailOptRecord
     *
     * @param trxdetailOptRecord 要修改的TrxdetailOptRecord
     */
    void updateTrxdetailOptRecord(TrxdetailOptRecord trxdetailOptRecord);

    /**
     * 根据id获取单个TrxdetailOptRecord对象
     *
     * @param id 要查询的id
     * @return TrxdetailOptRecord
     */
    TrxdetailOptRecord getTrxdetailOptRecordById(Long id);

    /**
     * 根据条件获取TrxdetailOptRecord列表
     *
     * @param trxdetailOptRecord 查询条件
     * @return List<TrxdetailOptRecord>
     */
    List<TrxdetailOptRecord> getTrxdetailOptRecordList(TrxdetailOptRecord trxdetailOptRecord);

    /**
     * 根据条件获取TrxdetailOptRecord列表
     *
     * @param trxdetailOptRecord 查询条件
     * @return List<TrxdetailOptRecord>
     */
    List<TrxdetailOptRecord> getTrxdetailOptRecordListPage(TrxdetailOptRecord trxdetailOptRecord, PageEntity page);
}