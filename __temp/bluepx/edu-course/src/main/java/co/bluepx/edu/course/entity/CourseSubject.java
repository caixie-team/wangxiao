package co.bluepx.edu.course.entity;

import co.bluepx.edu.core.model.BaseIncrementIdModel;

public class CourseSubject extends BaseIncrementIdModel{

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    private Long courseId;
    private Long categoryId;
    private Long subjectId;
}
