package com.atdld.os.api.course.entity;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class CourseMember implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
    private Long courseId;//课程Id
    private Long memberTypeId;//会员类型Id
   
}
