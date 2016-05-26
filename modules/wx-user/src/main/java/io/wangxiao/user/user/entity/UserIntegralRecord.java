package io.wangxiao.user.user.entity;

import io.wangxiao.core.model.BaseIncrementIdModel;

import java.util.Date;

public class UserIntegralRecord extends BaseIncrementIdModel {
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getScore() {
        return score;
    }

    public void setScore(Long score) {
        this.score = score;
    }

    public Long getIntegralType() {
        return integralType;
    }

    public void setIntegralType(Long integralType) {
        this.integralType = integralType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getCurrentScore() {
        return currentScore;
    }

    public void setCurrentScore(Long currentScore) {
        this.currentScore = currentScore;
    }

    public Long getOther() {
        return other;
    }

    public void setOther(Long other) {
        this.other = other;
    }

    public Long getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(Long fromUserId) {
        this.fromUserId = fromUserId;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getTemplateScore() {
        return templateScore;
    }

    public void setTemplateScore(String templateScore) {
        this.templateScore = templateScore;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGiftName() {
        return giftName;
    }

    public void setGiftName(String giftName) {
        this.giftName = giftName;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    private Long userId;//用戶id
    private Long score;//分数
    private Long integralType;//积分类型
    private java.util.Date createTime;//创建时间
    private Long currentScore;//当前积分
    private Long other;//其它辅助
    private Long fromUserId;//来源id
    private Long status;//兑换状态 0未处理1已处理
    private String description;//描述
    private String templateName;//模板名称
    private String templateScore;//模板分数
    private String nickname;//用户名
    private String email;//邮箱
    private String giftName;//礼品名称
    private Long courseId;//课程id
    private String startDate;//开始时间
    private String endDate;//结束时间
    private Long addressId;//收货地址Id
    private String address;//收货地址
}
