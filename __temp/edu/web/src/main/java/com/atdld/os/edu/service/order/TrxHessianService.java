package com.atdld.os.edu.service.order;

import java.util.Map;

/**
 * @ClassName  com.atdld.os.edu.service.order.TrxHessianService
 * @description
 * @author :
 * @Create Date : 2014-9-19 上午9:54:49
 */
public interface TrxHessianService {
    /**
     * 订单支付成功回调操作
     * @param sourceMap
     * @return
     */
    public Map<String,String> noTrxTrxOrderComplete(Map<String, String> sourceMap);
}
