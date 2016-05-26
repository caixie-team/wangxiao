package io.wangxiao.edu.home.controller.order;


import io.wangxiao.edu.home.common.EduBaseController;
import io.wangxiao.edu.home.entity.order.TrxorderDetail;
import io.wangxiao.edu.home.service.order.TrxorderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * TrxorderDetail管理接口
 */
@Controller
public class TrxorderDetailController extends EduBaseController {

    @Autowired
    private TrxorderDetailService trxorderDetailService;


    /**
     * 修改TrxorderDetail
     *
     * @param trxorderDetail 要修改的TrxorderDetail
     */
    public void updateTrxorderDetail(TrxorderDetail trxorderDetail) {
        trxorderDetailService.updateTrxorderDetail(trxorderDetail);
    }

}