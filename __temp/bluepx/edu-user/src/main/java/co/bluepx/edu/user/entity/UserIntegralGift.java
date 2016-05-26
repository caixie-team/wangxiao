package co.bluepx.edu.user.entity;

import co.bluepx.edu.core.model.BaseIncrementIdModel;

import java.util.Date;

public class UserIntegralGift extends BaseIncrementIdModel {

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getScore() {
        return score;
    }

    public void setScore(Long score) {
        this.score = score;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public String getCourseLogo() {
        return courseLogo;
    }

    public void setCourseLogo(String courseLogo) {
        this.courseLogo = courseLogo;
    }

    private String name;//礼品名称
    private Long score;//所需分数
    private String logo;//图片
    private String content;//内容
    private java.util.Date createTime;//创建时间
    private java.util.Date updateTime;//更新时间
    private Long courseId;//课程id
    private Long status;//状态1为正常，2为删除
    //课程礼品所需属性
    private String courseName;//课程名
    private String courseTitle;//简介
    private String courseLogo;//课程图片
}
