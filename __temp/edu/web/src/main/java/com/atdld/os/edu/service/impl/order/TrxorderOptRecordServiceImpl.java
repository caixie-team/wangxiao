package com.atdld.os.edu.service.impl.order;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.atdld.os.edu.entity.order.TrxorderOptRecord;
import com.atdld.os.edu.dao.order.TrxorderOptRecordDao;
import com.atdld.os.edu.service.order.TrxorderOptRecordService;
/**
 * TrxorderOptRecord管理接口
 * User:
 * Date: 2014-05-27
 */
@Service("trxorderOptRecordService")
public class TrxorderOptRecordServiceImpl implements TrxorderOptRecordService{

 	@Autowired
    private TrxorderOptRecordDao trxorderOptRecordDao;
    
    /**
     * 添加TrxorderOptRecord
     * @param trxorderOptRecord 要添加的TrxorderOptRecord
     * @return id
     */
    public java.lang.Long addTrxorderOptRecord(TrxorderOptRecord trxorderOptRecord){
    	return trxorderOptRecordDao.addTrxorderOptRecord(trxorderOptRecord);
    }

    /**
     * 根据id删除一个TrxorderOptRecord
     * @param id 要删除的id
     */
    public void deleteTrxorderOptRecordById(Long id){
    	 trxorderOptRecordDao.deleteTrxorderOptRecordById(id);
    }

    /**
     * 修改TrxorderOptRecord
     * @param trxorderOptRecord 要修改的TrxorderOptRecord
     */
    public void updateTrxorderOptRecord(TrxorderOptRecord trxorderOptRecord){
     	trxorderOptRecordDao.updateTrxorderOptRecord(trxorderOptRecord);
    }

    /**
     * 根据id获取单个TrxorderOptRecord对象
     * @param id 要查询的id
     * @return TrxorderOptRecord
     */
    public TrxorderOptRecord getTrxorderOptRecordById(Long id){
    	return trxorderOptRecordDao.getTrxorderOptRecordById( id);
    }

    /**
     * 根据条件获取TrxorderOptRecord列表
     * @param trxorderOptRecord 查询条件
     * @return List<TrxorderOptRecord>
     */
    public List<TrxorderOptRecord> getTrxorderOptRecordList(TrxorderOptRecord trxorderOptRecord){
    	return trxorderOptRecordDao.getTrxorderOptRecordList(trxorderOptRecord);
    }
}