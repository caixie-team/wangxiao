package io.wangxiao.cash.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class CashOrderDTO implements Serializable{

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public BigDecimal getPayCash() {
        return payCash;
    }

    public void setPayCash(BigDecimal payCash) {
        this.payCash = payCash;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public BigDecimal getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getTrxStatus() {
        return trxStatus;
    }

    public void setTrxStatus(String trxStatus) {
        this.trxStatus = trxStatus;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getReqChannel() {
        return reqChannel;
    }

    public void setReqChannel(String reqChannel) {
        this.reqChannel = reqChannel;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public String getReqIp() {
        return reqIp;
    }

    public void setReqIp(String reqIp) {
        this.reqIp = reqIp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getStartPayTime() {
        return startPayTime;
    }

    public void setStartPayTime(String startPayTime) {
        this.startPayTime = startPayTime;
    }

    public String getEndPayTime() {
        return endPayTime;
    }

    public void setEndPayTime(String endPayTime) {
        this.endPayTime = endPayTime;
    }

    private Long id;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 充值金额
     */
    private BigDecimal payCash;
    /**
     * 下单时间
     */
    private java.util.Date createTime;
    /**
     * 支付成功/确认时间
     */
    private java.util.Date payTime;
    /**
     * 订单原始金额
     */
    private BigDecimal orderAmount;
    /**
     * 实际支付金额
     */
    private BigDecimal amount;
    /**
     * 交易请求号
     */
    private String requestId;
    /**
     * 交易装态
     */
    private String trxStatus;
    /**
     * 支付类型（ALIPY,KUAIQIAN,VM,FREE,INTEGRAL)
     */
    private String payType;
    /**
     * 请求渠道(WEB,APP)
     */
    private String reqChannel;
    /**
     * 备用描述
     */
    private String description;
    /**
     * 乐观锁版本号
     */
    private Long version=0L;
    /**
     * 客户端IP
     */
    private String reqIp;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 开始下单时间
     */
    private String startTime;
    /**
     * 结束下单时间
     */
    private String endTime;
    /**
     * 开始支付时间
     */
    private String startPayTime;
    /**
     * 结束支付时间
     */
    private String endPayTime;
   
}
