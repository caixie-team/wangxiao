package io.wangxiao.edu.home.entity.course;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class CourseProfile implements Serializable{

    public static String ADD = "+";
    public static String SUBTRACTION = "-";
    
    private Long id;
    private Long courseId;
    private Long buycount=0L;
    private Long viewcount=0L;
    private Long commentcount=0L;
    private Long questiongcount=0L;
    private Long notecount=0L;
    private Long playcount=0L;
    private Long wacthPersonCount=0L;
    private String name;//课程名称
}
