package io.wangxiao.order.entity;

import io.wangxiao.core.model.BaseIncrementIdModel;
import org.beetl.sql.core.annotatoin.Table;

import java.math.BigDecimal;
import java.util.Date;

@Table(name="edu_trxorder")
public class Trxorder extends BaseIncrementIdModel {
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public BigDecimal getCouponAmount() {
        return couponAmount;
    }

    public void setCouponAmount(BigDecimal couponAmount) {
        this.couponAmount = couponAmount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
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

    public Long getCouponCodeId() {
        return couponCodeId;
    }

    public void setCouponCodeId(Long couponCodeId) {
        this.couponCodeId = couponCodeId;
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

    private Long userId;//用户id
    private java.util.Date createTime;//下单时间
    private java.util.Date payTime;//支付成功
    private java.math.BigDecimal orderAmount;//订单原始金额
    private java.math.BigDecimal couponAmount;//优惠券金额
    private java.math.BigDecimal amount;//实际支付金额
    private java.math.BigDecimal cashAmount;//实际支付的cash金额
    private java.math.BigDecimal vmAmount;//实际支付的vm金额
    private Long couponCodeId = 0L;//优惠券编码id
    private String requestId;//交易请求号
    private String trxStatus;//交易装态 INIT SUCCESS REFUND CACULE
    private String payType;//支付类型（ALIPY,KUAIQIAN,CARD,FREE,INTEGRAL）
    private String reqChannel;//请求渠道(WEB,APP)
    private String description;//备用描述
    private Long version = 0L;//乐观锁版本号
    private String reqIp;//客户端IP
}
