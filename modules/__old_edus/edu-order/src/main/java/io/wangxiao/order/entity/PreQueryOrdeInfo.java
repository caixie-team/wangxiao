package io.wangxiao.order.entity;

import lombok.Data;

/**
 * @description 订单操作预处理查询结果记录
 * @author :
 */
@Data
public class PreQueryOrdeInfo {

    private Long userId;
    private boolean isRecharge=false;//是否充值操作
    private java.math.BigDecimal amount;//本次操作的金额
}
