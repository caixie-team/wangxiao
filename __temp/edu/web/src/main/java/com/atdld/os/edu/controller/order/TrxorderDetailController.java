package com.atdld.os.edu.controller.order;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.atdld.os.edu.service.order.TrxorderDetailService;
import com.atdld.os.edu.service.order.TrxorderService;
import com.atdld.os.edu.common.EduBaseController;
import com.atdld.os.edu.entity.order.TrxorderDetail;
/**
 * TrxorderDetail管理接口
 * User:
 * Date: 2014-05-27
 */
@Controller
public class TrxorderDetailController extends EduBaseController{

 	@Autowired
    private TrxorderDetailService trxorderDetailService;
	@Autowired
	private TrxorderService trxorderService;
    


    
    
    /**
     * 修改TrxorderDetail
     * @param trxorderDetail 要修改的TrxorderDetail
     */
    public void updateTrxorderDetail(TrxorderDetail trxorderDetail){
     	trxorderDetailService.updateTrxorderDetail(trxorderDetail);
    }
    



   
}