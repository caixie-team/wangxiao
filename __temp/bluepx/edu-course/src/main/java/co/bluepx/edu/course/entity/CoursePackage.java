package co.bluepx.edu.course.entity;

import co.bluepx.edu.core.model.BaseIncrementIdModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
public class CoursePackage extends BaseIncrementIdModel implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
    private Long mainCourseId;//主课程id
    private Long courseId;//课程id
    
    private Long orderNum; // 排序值
}
