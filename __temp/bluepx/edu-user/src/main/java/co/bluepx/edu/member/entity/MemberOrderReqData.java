package co.bluepx.edu.member.entity;

import co.bluepx.edu.core.model.BaseIncrementIdModel;
import co.bluepx.edu.user.entity.UserAccount;

import java.math.BigDecimal;
import java.util.Date;

public class MemberOrderReqData extends BaseIncrementIdModel {
    public Long getMemberOrderId() {
        return memberOrderId;
    }

    public void setMemberOrderId(Long memberOrderId) {
        this.memberOrderId = memberOrderId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
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

    public BigDecimal getBankAmount() {
        return bankAmount;
    }

    public void setBankAmount(BigDecimal bankAmount) {
        this.bankAmount = bankAmount;
    }

    public BigDecimal getVmAmount() {
        return vmAmount;
    }

    public void setVmAmount(BigDecimal vmAmount) {
        this.vmAmount = vmAmount;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
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

//    public PayType getPayType() {
//        return payType;
//    }
//
//    public void setPayType(PayType payType) {
//        this.payType = payType;
//    }

    public int getMemberDays() {
        return memberDays;
    }

    public void setMemberDays(int memberDays) {
        this.memberDays = memberDays;
    }

    public Long getMemberType() {
        return memberType;
    }

    public void setMemberType(Long memberType) {
        this.memberType = memberType;
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

    public boolean isRecharge() {
        return isRecharge;
    }

    public void setRecharge(boolean recharge) {
        isRecharge = recharge;
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    //订单id
    private Long memberOrderId;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 会员类型id
     */
    private Long memberId;
    /**
     * 下单时间
     */
    private Date createTime;
    /**
     * 支付成功/确认时间
     */
    private Date payTime;
    /**
     * 订单原始金额
     */
    private BigDecimal orderAmount;
    /**
     * 优惠券金额
     */
    private BigDecimal couponAmount;
    /**
     * 实际支付金额
     */
    private BigDecimal amount;
    /**
     * 现金支付
     */
    private BigDecimal cashAmount;
    private BigDecimal bankAmount;//本次还需提交到银行的金额，账户中有余额的情况bankAmount<=amount
    /**
     * 虚拟币
     */
    private BigDecimal vmAmount;
    private String couponCode;//优惠券编码
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
//    private PayType payType;
    /**
     * 开通天数
     */
    private int memberDays;
    /**
     * 开通会员类型
     */
    private Long memberType;
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
    private Long version;
    /**
     * 客户端IP
     */
    private String reqIp;
    private boolean isRecharge = false;//是否充值操作
    private UserAccount userAccount;
    private String out_trade_no;//提交到支付宝的请求号
}
