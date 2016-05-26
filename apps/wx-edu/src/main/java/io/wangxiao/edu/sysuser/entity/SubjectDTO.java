package io.wangxiao.edu.sysuser.entity;

import io.wangxiao.edu.home.entity.course.CourseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class SubjectDTO extends Subject {

    private List<CourseDto> courseList;
}
