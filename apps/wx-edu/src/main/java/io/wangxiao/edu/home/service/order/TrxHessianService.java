package io.wangxiao.edu.home.service.order;

import java.util.Map;

public interface TrxHessianService {
    /**
     * 订单支付成功回调操作
     * @param sourceMap
     * @return
     */
    Map<String,String> noTrxTrxOrderComplete(Map<String, String> sourceMap);
}
