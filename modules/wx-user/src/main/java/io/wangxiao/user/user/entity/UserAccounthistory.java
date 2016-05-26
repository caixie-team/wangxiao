package io.wangxiao.user.user.entity;

import io.wangxiao.core.model.BaseIncrementIdModel;

import java.math.BigDecimal;
import java.util.Date;

public class UserAccounthistory extends BaseIncrementIdModel {
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getTrxorderId() {
        return trxorderId;
    }

    public void setTrxorderId(Long trxorderId) {
        this.trxorderId = trxorderId;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getIsDisplay() {
        return isDisplay;
    }

    public void setIsDisplay(int isDisplay) {
        this.isDisplay = isDisplay;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getCashAmount() {
        return cashAmount;
    }

    public void setCashAmount(BigDecimal cashAmount) {
        this.cashAmount = cashAmount;
    }

    public BigDecimal getVmAmount() {
        return vmAmount;
    }

    public void setVmAmount(BigDecimal vmAmount) {
        this.vmAmount = vmAmount;
    }

    public BigDecimal getTrxAmount() {
        return trxAmount;
    }

    public void setTrxAmount(BigDecimal trxAmount) {
        this.trxAmount = trxAmount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getActHistoryType() {
        return actHistoryType;
    }

    public void setActHistoryType(String actHistoryType) {
        this.actHistoryType = actHistoryType;
    }

    public String getBizType() {
        return bizType;
    }

    public void setBizType(String bizType) {
        this.bizType = bizType;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    private Long userId;// 用户id
    private Long trxorderId;// 订单id
    private String requestId ;//外部请求订单号
    private String outTradeNo;//支付宝订单号
    private Long accountId;// 相关账户ID
    private java.util.Date createTime;
    private int isDisplay;// 是否显示账户历史记录:0显示;1不显示
    private java.math.BigDecimal balance;// 当前余额
    private java.math.BigDecimal cashAmount;// cash余额
    private java.math.BigDecimal vmAmount;// vm余额
    private java.math.BigDecimal trxAmount;// 交易金额
    private String description;// 账户历史内容描述
    private String actHistoryType;// 帐务历史类型.充值。消费等
    private String bizType;// 业务类型(课程订单、会员订单、图书订单)
    private Long version;// 乐观锁版本号
    private String createUser;//操作人
}
