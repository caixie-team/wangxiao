package io.wangxiao.edu.home.service.impl.cash;

import java.util.Map;

public interface CashHessianService {
    /**
     * 订单支付成功回调操作
     *
     * @param sourceMap
     * @return
     */
    Map<String, String> noCashOrderComplete(Map<String, String> sourceMap);
}
