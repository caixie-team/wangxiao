package co.bluepx.edu.course.entity;

import co.bluepx.edu.core.model.BaseIncrementIdModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CourseKpoint extends BaseIncrementIdModel{
    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Long getSort() {
        return sort;
    }

    public void setSort(Long sort) {
        this.sort = sort;
    }

    public Long getPlaycount() {
        return playcount;
    }

    public void setPlaycount(Long playcount) {
        this.playcount = playcount;
    }

    public Long getIsfree() {
        return isfree;
    }

    public void setIsfree(Long isfree) {
        this.isfree = isfree;
    }

    public String getVideotype() {
        return videotype;
    }

    public void setVideotype(String videotype) {
        this.videotype = videotype;
    }

    public String getVideourl() {
        return videourl;
    }

    public void setVideourl(String videourl) {
        this.videourl = videourl;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public Long getCourseMinutes() {
        return courseMinutes;
    }

    public void setCourseMinutes(Long courseMinutes) {
        this.courseMinutes = courseMinutes;
    }

    public Long getCourseSeconds() {
        return courseSeconds;
    }

    public void setCourseSeconds(Long courseSeconds) {
        this.courseSeconds = courseSeconds;
    }

    public String getVideojson() {
        return videojson;
    }

    public void setVideojson(String videojson) {
        this.videojson = videojson;
    }

    public Long getPersonNum() {
        return personNum;
    }

    public void setPersonNum(Long personNum) {
        this.personNum = personNum;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public List<CourseKpoint> getChildKpoints() {
        return childKpoints;
    }

    public void setChildKpoints(List<CourseKpoint> childKpoints) {
        this.childKpoints = childKpoints;
    }

    private Long courseId;
    private String name;
    private Long parentId;
    private Long type;
    private Long status;
    private java.util.Date addTime;
    private Long sort;
    private Long playcount;
    private Long isfree;
    private String videotype;
    private String videourl;
    private Long teacherId;
    private Long courseMinutes;
    private Long courseSeconds;
    private String videojson;
    private Long personNum;
    private String courseName;
    private List<CourseKpoint> childKpoints=new ArrayList<CourseKpoint>();
}
