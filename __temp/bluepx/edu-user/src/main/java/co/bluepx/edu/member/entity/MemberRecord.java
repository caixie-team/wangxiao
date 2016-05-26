package co.bluepx.edu.member.entity;

import co.bluepx.edu.core.model.BaseIncrementIdModel;

import java.util.Date;

public class MemberRecord extends BaseIncrementIdModel {
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getMemberType() {
        return memberType;
    }

    public void setMemberType(Long memberType) {
        this.memberType = memberType;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    /**
     * 用户id
     */
    private Long userId;
    /**
     * 会员类型
     */
    private Long memberType;
    /**
     * 首次开通时间
     */
    private Date beginDate;
    /**
     * 会员到期时间
     */
    private Date endDate;
    /**
     * 描述
     */
    private String description;
    /**
     * 状态 0正常 1关闭
     */
    private Long status;
}