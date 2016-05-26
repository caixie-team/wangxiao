package com.atdld.os.edu.entity.order;

import lombok.Data;

/**
 * @ClassName  com.atdld.os.edu.entity.order.PreQueryOrdeInfo
 * @description 订单操作预处理查询结果记录
 * @author :
 * @Create Date : 2014-9-19 下午2:10:03
 */
@Data
public class PreQueryOrdeInfo {

    private Long userId;
    private boolean isRecharge=false;//是否充值操作
    private java.math.BigDecimal amount;//本次操作的金额
}
