package io.wangxiao.user.user.entity;

import io.wangxiao.core.model.BaseIncrementIdModel;

import java.util.Date;

public class LoginOnline extends BaseIncrementIdModel {
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getLoginsid() {
        return loginsid;
    }

    public void setLoginsid(String loginsid) {
        this.loginsid = loginsid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    private Long userId;
    private String loginsid;
    private String type;
    private java.util.Date createTime;
}
