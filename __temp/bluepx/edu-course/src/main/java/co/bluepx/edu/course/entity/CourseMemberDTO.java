package co.bluepx.edu.course.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
public class CourseMemberDTO implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
    private Long courseId;//课程Id
    private Long memberTypeId;//会员类型Id
    private String memberTitle;//会员类型名称
    private String memberTag;//会员id字符串
}
