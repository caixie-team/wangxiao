package com.atdld.os.app.entity.website;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 
 * @ClassName com.atdld.os.edu.entity.website.WebsiteCourseDetail
 * @description 推荐课程
 * @author :
 * @Create Date : 2014年6月7日 下午1:52:22
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AppWebsiteCourseDetail implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7475674095165175841L;

	private Long id;// 主键
	private Long recommendId;// 分类id
	private Long courseId;// 课程id
	private int orderNum;// 排序值
}
