package com.atdld.os.edu.entity.website;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * @ClassName com.atdld.os.edu.entity.website.WebsiteCourseDetail
 * @description 推荐课程DTO
 * @author :
 * @Create Date : 2014年6月7日 下午1:52:22
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class WebsiteCourseDetailDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7475674095165175841L;

	private Long id;// 主键
	private Long recommendId;// 分类id
	private Long courseId;// 课程id
	private int orderNum;// 排序值
	private String recommendName;//推荐名称
	private String courseName;//课程名称
	private int isavaliable;//课程是否下架
}
