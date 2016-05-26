package co.bluepx.edu.course.entity;

import co.bluepx.edu.core.model.BaseIncrementIdModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
public class CourseProfile extends BaseIncrementIdModel implements Serializable{
    /**
     * 
     */
    private static final long serialVersionUID = 7322830318541029947L;
    
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
