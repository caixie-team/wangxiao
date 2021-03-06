package com.atdld.os.edu.entity.course;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class CourseSubject implements Serializable{
    private Long id;
    private Long courseId;
    private Long categoryId;
    private Long subjectId;
}
