package co.bluepx.edu.user.entity;

import co.bluepx.edu.core.model.BaseIncrementIdModel;

import java.util.Date;

/**
 * @description 用户操作总记录
 */
public class UserOptRecord extends BaseIncrementIdModel {

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public Long getBizId() {
        return bizId;
    }

    public void setBizId(Long bizId) {
        this.bizId = bizId;
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

    private Long userId;//用户id
    private String type;//操作类型
    private Long optuser;//操作者id
    private String optusername;//操作者名字
    private Long bizId;//业务id
    private String description;//描述 Json 格式
    private Date createTime;//创建时间
    private String startDate;//开始时间
    private String endDate;//结束时间
}
