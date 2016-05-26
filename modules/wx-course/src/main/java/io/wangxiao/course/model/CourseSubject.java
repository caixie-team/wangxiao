package io.wangxiao.course.model;

import lombok.Data;
import org.beetl.sql.core.annotatoin.Table;

/*
* 
* gen by beetlsql 2016-05-26
*/
//@Getter
//@Setter
@Data
@Table(name = "edu_course_subject")
public class CourseSubject {
    private Long id;
    //课程id
    private Long courseId;
    //分类id
    private Long subjectId;

}
