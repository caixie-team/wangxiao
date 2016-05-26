package io.wangxiao.edu.home.entity.order;

import lombok.Data;

/**
 * @description 订单操作预处理查询结果记录
 */
@Data
public class PreQueryOrdeInfo {

    private Long userId;
    private boolean isRecharge = false;//是否充值操作
    private java.math.BigDecimal amount;//本次操作的金额
}
