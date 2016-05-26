package com.atdld.os.edu.service.order;

import java.util.List;
import com.atdld.os.edu.entity.order.TrxdetailOptRecord;

/**
 * TrxdetailOptRecord管理接口
 * User:
 * Date: 2014-05-27
 */
public interface TrxdetailOptRecordService {

    /**
     * 添加TrxdetailOptRecord
     * @param trxdetailOptRecord 要添加的TrxdetailOptRecord
     * @return id
     */
    public java.lang.Long addTrxdetailOptRecord(TrxdetailOptRecord trxdetailOptRecord);

    /**
     * 根据id删除一个TrxdetailOptRecord
     * @param id 要删除的id
     */
    public void deleteTrxdetailOptRecordById(Long id);

    /**
     * 修改TrxdetailOptRecord
     * @param trxdetailOptRecord 要修改的TrxdetailOptRecord
     */
    public void updateTrxdetailOptRecord(TrxdetailOptRecord trxdetailOptRecord);

    /**
     * 根据id获取单个TrxdetailOptRecord对象
     * @param id 要查询的id
     * @return TrxdetailOptRecord
     */
    public TrxdetailOptRecord getTrxdetailOptRecordById(Long id);

    /**
     * 根据条件获取TrxdetailOptRecord列表
     * @param trxdetailOptRecord 查询条件
     * @return List<TrxdetailOptRecord>
     */
    public List<TrxdetailOptRecord> getTrxdetailOptRecordList(TrxdetailOptRecord trxdetailOptRecord);
}