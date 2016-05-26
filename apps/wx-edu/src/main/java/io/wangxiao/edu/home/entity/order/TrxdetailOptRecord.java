package io.wangxiao.edu.home.entity.order;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 流水操作记录表
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TrxdetailOptRecord implements Serializable {
    private Long id;
    private Long trxorderId;
    private Long trxorderDetailId;
    private String type;
    private Long optuser;
    private String optusername;
    private String desc;
    private java.util.Date createTime;
    private String startDate;
    private String endDate;
}
