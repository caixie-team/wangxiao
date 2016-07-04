package io.wangxiao.website.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 
 * @description 推荐课程
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class WebsiteCourseDetail implements Serializable {

	private Long id;// 主键
	private Long recommendId;// 分类id
	private Long courseId;// 课程id
	private int orderNum;// 排序值
}
