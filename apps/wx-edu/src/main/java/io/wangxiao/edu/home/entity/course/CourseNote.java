package io.wangxiao.edu.home.entity.course;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class CourseNote implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 5558322615779544879L;
	private Long id;
    private Long kpointId;
    private Long courseId;
    private Long userId;
    private String content;
    private java.util.Date updateTime;
    private int status;
}
