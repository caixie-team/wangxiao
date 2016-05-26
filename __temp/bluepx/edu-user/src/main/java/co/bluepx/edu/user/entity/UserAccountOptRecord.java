package co.bluepx.edu.user.entity;

import co.bluepx.edu.core.model.BaseIncrementIdModel;

import java.math.BigDecimal;
import java.util.Date;

public class UserAccountOptRecord extends BaseIncrementIdModel {

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOutNo() {
        return outNo;
    }

    public void setOutNo(String outNo) {
        this.outNo = outNo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    private Long userId;//用户id
    private Long optuser;//操作者id
    private String optusername;//操作人名字
    private Long accountId;//账户id
    private java.math.BigDecimal amount;//操作金额
    private String type;//操作类型
    private String outNo;//操作标识
    private String description;//备注
    private java.util.Date createTime;//操作时间
}
