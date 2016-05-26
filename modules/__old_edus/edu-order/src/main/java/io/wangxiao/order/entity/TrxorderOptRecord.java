package io.wangxiao.order.entity;

import io.wangxiao.core.model.BaseIncrementIdModel;

import java.util.Date;

/**
 * 订单操作记录表
 */
public class TrxorderOptRecord extends BaseIncrementIdModel {
    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getOptuser() {
        return optuser;
    }

    public void setOptuser(Long optuser) {
        this.optuser = optuser;
    }

    public String getOptusername() {
        return optusername;
    }

    public void setOptusername(String optusername) {
        this.optusername = optusername;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    private Long orderId;
    private String type;
    private Long optuser;
    private String optusername;
    private String desc;
    private java.util.Date createtime;
}
