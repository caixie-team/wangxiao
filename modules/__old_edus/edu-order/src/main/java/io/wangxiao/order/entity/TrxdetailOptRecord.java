package io.wangxiao.order.entity;

import io.wangxiao.core.model.BaseIncrementIdModel;

import java.util.Date;

/**
 * 流水操作记录表
 * @author
 *
 */
public class TrxdetailOptRecord extends BaseIncrementIdModel {
    public Long getTrxorderId() {
        return trxorderId;
    }

    public void setTrxorderId(Long trxorderId) {
        this.trxorderId = trxorderId;
    }

    public Long getTrxorderDetailId() {
        return trxorderDetailId;
    }

    public void setTrxorderDetailId(Long trxorderDetailId) {
        this.trxorderDetailId = trxorderDetailId;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    private Long trxorderId;
    private Long trxorderDetailId;
    private String type;
    private Long optuser;
    private String optusername;
    private String desc;
    private java.util.Date createTime;
}
