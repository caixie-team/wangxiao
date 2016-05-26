package co.bluepx.edu.course.entity;

import co.bluepx.edu.core.model.BaseIncrementIdModel;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @description 讲师实体
 */
public class Teacher extends BaseIncrementIdModel implements Serializable {

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getCareer() {
        return career;
    }

    public void setCareer(String career) {
        this.career = career;
    }

    public Long getIsStar() {
        return isStar;
    }

    public void setIsStar(Long isStar) {
        this.isStar = isStar;
    }

    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
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

    public String getCheckboxFalg() {
        return checkboxFalg;
    }

    public void setCheckboxFalg(String checkboxFalg) {
        this.checkboxFalg = checkboxFalg;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public List<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }
    private String name;// 讲师名称
    private String education;// 讲师资历
    private String career;// 讲师简介
    private Long isStar;// 头衔 0高级讲师1首席讲师
    private String picPath;// 头像
    private Long status;// 状态:0正常1删除
    private java.util.Date createTime;// 创建时间
    private java.util.Date updateTime;// 更新时间
    private String checkboxFalg;
    private Long subjectId; //专业id
    private List<Course> courseList;        //课程集合
}
