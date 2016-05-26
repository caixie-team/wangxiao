package co.bluepx.edu.course.entity;

import co.bluepx.edu.core.model.BaseIncrementIdModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
public class CourseTag extends BaseIncrementIdModel implements Serializable{
    private Long id;
    private Long couartId;
    private int type;
    private String name;
}
