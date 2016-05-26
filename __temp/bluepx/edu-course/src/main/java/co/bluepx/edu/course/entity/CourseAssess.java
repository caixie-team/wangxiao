package co.bluepx.edu.course.entity;

import co.bluepx.edu.core.model.BaseIncrementIdModel;

import java.util.Date;

/**
 * @description 课程评论
 */
public class CourseAssess extends BaseIncrementIdModel {

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

    public Long getKpointId() {
        return kpointId;
    }

    public void setKpointId(Long kpointId) {
        this.kpointId = kpointId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCourseIds() {
        return courseIds;
    }

    public void setCourseIds(String courseIds) {
        this.courseIds = courseIds;
    }

    private Long userId;// 用户id
    private Long courseId;// 课程id
    private Long kpointId;// 节点id
    private String content;// 内容
    private int status;//0显示 1隐藏
    private java.util.Date createTime;// 创建时间

    private String courseIds; // 套餐课程下所有课程ID集合字符串
}
