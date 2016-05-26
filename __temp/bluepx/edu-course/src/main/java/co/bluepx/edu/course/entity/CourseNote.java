package co.bluepx.edu.course.entity;

import co.bluepx.edu.core.model.BaseIncrementIdModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
public class CourseNote extends BaseIncrementIdModel implements Serializable{
    private Long id;
    private Long kpointId;
    private Long courseId;
    private Long userId;
    private String content;
    private java.util.Date updateTime;
    private int status;
}
