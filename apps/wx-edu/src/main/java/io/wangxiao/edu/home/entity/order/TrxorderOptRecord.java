package io.wangxiao.edu.home.entity.order;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 订单操作记录表
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TrxorderOptRecord implements Serializable {
    private Long id;
    private Long orderId;
    private String type;
    private Long optuser;
    private String optusername;
    private String desc;
    private java.util.Date createtime;
    private String startDate;
    private String endDate;
}
