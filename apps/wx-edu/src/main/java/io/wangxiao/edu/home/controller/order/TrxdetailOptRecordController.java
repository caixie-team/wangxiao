package io.wangxiao.edu.home.controller.order;


import io.wangxiao.edu.home.common.EduBaseController;
import io.wangxiao.edu.home.entity.order.TrxdetailOptRecord;
import io.wangxiao.edu.home.service.order.TrxdetailOptRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * TrxdetailOptRecord管理接口
 */
@Controller
public class TrxdetailOptRecordController extends EduBaseController {

    @Autowired
    private TrxdetailOptRecordService trxdetailOptRecordService;


    /**
     * 修改TrxdetailOptRecord
     *
     * @param trxdetailOptRecord 要修改的TrxdetailOptRecord
     */
    public void updateTrxdetailOptRecord(TrxdetailOptRecord trxdetailOptRecord) {
        trxdetailOptRecordService.updateTrxdetailOptRecord(trxdetailOptRecord);
    }


}