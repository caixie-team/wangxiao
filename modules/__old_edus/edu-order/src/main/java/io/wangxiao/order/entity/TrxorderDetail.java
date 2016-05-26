package io.wangxiao.order.entity;

import io.wangxiao.core.model.BaseIncrementIdModel;

import java.math.BigDecimal;
import java.util.Date;

public class TrxorderDetail extends BaseIncrementIdModel {
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getTrxorderId() {
        return trxorderId;
    }

    public void setTrxorderId(Long trxorderId) {
        this.trxorderId = trxorderId;
    }

    public Long getMembertype() {
        return membertype;
    }

    public void setMembertype(Long membertype) {
        this.membertype = membertype;
    }

    public int getLosetype() {
        return losetype;
    }

    public void setLosetype(int losetype) {
        this.losetype = losetype;
    }

    public Date getLoseAbsTime() {
        return loseAbsTime;
    }

    public void setLoseAbsTime(Date loseAbsTime) {
        this.loseAbsTime = loseAbsTime;
    }

    public String getLoseTime() {
        return loseTime;
    }

    public void setLoseTime(String loseTime) {
        this.loseTime = loseTime;
    }

    public Date getAuthTime() {
        return authTime;
    }

    public void setAuthTime(Date authTime) {
        this.authTime = authTime;
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

    public BigDecimal getSourcePrice() {
        return sourcePrice;
    }

    public void setSourcePrice(BigDecimal sourcePrice) {
        this.sourcePrice = sourcePrice;
    }

    public BigDecimal getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(BigDecimal currentPrice) {
        this.currentPrice = currentPrice;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getTrxStatus() {
        return trxStatus;
    }

    public void setTrxStatus(String trxStatus) {
        this.trxStatus = trxStatus;
    }

    public String getAuthStatus() {
        return authStatus;
    }

    public void setAuthStatus(String authStatus) {
        this.authStatus = authStatus;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
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

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    /**
     * 流水
     */
    private Long userId;//用户id
    private Long courseId;//相关联的课程id（前台快照）
    private Long trxorderId;//交易订单ID
    private Long membertype;//会员观看类型（前台快照）
    private int losetype;//有效期类型（前台快照）
    private java.util.Date loseAbsTime;//订单过期时间段（前台快照）
    private String loseTime;//订单过期时间点（前台快照）
    private java.util.Date authTime;//课程过期时间
    private java.util.Date createTime;//下单时间
    private java.util.Date payTime;//支付成功时间
    private java.math.BigDecimal sourcePrice;//原价格（前台快照）
    private java.math.BigDecimal currentPrice;//销售价格（前台快照）
    private String courseName;//课程名称（前台goods快照）
    private String trxStatus;//订单状态（前台goods快照）
    private String authStatus;//课程状态（INIT，SUCCESS，REFUND，CLOSED，LOSED）
    private String requestId;//订单请求号
    private String description;//描述
    private Long version=1L;//乐观锁版本号
    private java.util.Date lastUpdateTime;//最后更新时间
//    private List<Teacher> teacherList;//最后更新时间
}
