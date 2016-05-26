package com.atdld.os.edu.controller.order;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.atdld.os.edu.service.order.TrxorderOptRecordService;
import com.atdld.os.edu.common.EduBaseController;
import com.atdld.os.edu.entity.order.TrxorderOptRecord;
/**
 * TrxorderOptRecord管理接口
 * User:
 * Date: 2014-05-27
 */
@Controller
public class TrxorderOptRecordController extends EduBaseController{

 	@Autowired
    private TrxorderOptRecordService trxorderOptRecordService;
    
    
    
    /**
     * 修改TrxorderOptRecord
     * @param trxorderOptRecord 要修改的TrxorderOptRecord
     */
    public void updateTrxorderOptRecord(TrxorderOptRecord trxorderOptRecord){
     	trxorderOptRecordService.updateTrxorderOptRecord(trxorderOptRecord);
    }

   
}