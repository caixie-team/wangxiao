package io.wangxiao.edu.home.entity.course;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
public class CourseTeacher implements Serializable{
	private Long id;
    private Long courseId;
    private Long teacherId;
}
