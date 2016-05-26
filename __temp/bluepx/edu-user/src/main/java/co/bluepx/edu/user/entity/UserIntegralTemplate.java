package co.bluepx.edu.user.entity;

import co.bluepx.edu.core.model.BaseIncrementIdModel;

import java.math.BigDecimal;
import java.util.Date;

public class UserIntegralTemplate extends BaseIncrementIdModel {

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public void setShowScore(int showScore) {
        this.showScore = showScore;
    }

    private String name;// 模板名称
    private Long type;// 0学员
    private String keyword;// 使用场景
    private java.math.BigDecimal score;// 功能分数
    private Long status;// 0正常1停用
    private java.util.Date createTime;// 创建时间
    private java.util.Date updateTime;// 更新时间
    private String createUser;// 创建人
    private int showScore;

    public int getShowScore() {// 显示整数
        showScore = score.intValue();//转化为int
        return showScore;
    }
}
