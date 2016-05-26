package co.bluepx.edu.course.entity;

import co.bluepx.edu.core.model.BaseIncrementIdModel;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Table(name = "sys_subject")
public class Subject extends BaseIncrementIdModel {

    @Id
    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
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

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }


    public List<Subject> getChildSubjectList() {
        return childSubjectList;
    }

    public void setChildSubjectList(List<Subject> childSubjectList) {
        this.childSubjectList = childSubjectList;
    }

    private Long subjectId; // 专业id

    private String subjectName;// 专业名称
    private int status;// 状态
    private Date createTime;// 创建时间
    private Date updateTime;// 修改时间
    private Long parentId;// 父节点
    private int level;// 等级 1，2，3

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    private String image;
    // 二级level的List
    private List<Subject> childSubjectList = new ArrayList<Subject>();

    @SuppressWarnings("unused")
    private String subjectNameAndId;// 树中显示用

//    @NotColumn
    public String getSubjectNameAndId() {
        return this.getSubjectName() + " -id:" + this.getSubjectId() + "";
    }

    public void setSubjectNameAndId(String subjectNameAndId) {
        this.subjectNameAndId = subjectNameAndId;
    }

}
