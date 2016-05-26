package io.wangxiao.edu.home.controller.order;


import io.wangxiao.edu.home.common.EduBaseController;
import io.wangxiao.edu.home.entity.order.TrxorderOptRecord;
import io.wangxiao.edu.home.service.order.TrxorderOptRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * TrxorderOptRecord管理接口
 */
@Controller
public class TrxorderOptRecordController extends EduBaseController {

    @Autowired
    private TrxorderOptRecordService trxorderOptRecordService;


    /**
     * 修改TrxorderOptRecord
     *
     * @param trxorderOptRecord 要修改的TrxorderOptRecord
     */
    public void updateTrxorderOptRecord(TrxorderOptRecord trxorderOptRecord) {
        trxorderOptRecordService.updateTrxorderOptRecord(trxorderOptRecord);
    }


}