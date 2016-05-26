package io.wangxiao.edu.home.service.member;

import java.util.Map;

public interface MemberHessianService {
    /**
     * 订单支付成功回调操作
     * @param sourceMap
     * @return
     */
    Map<String,String> noMemberOrderComplete(Map<String, String> sourceMap);
}
