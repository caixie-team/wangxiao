package io.wangxiao.website.entity;

import io.wangxiao.core.model.BaseIncrementIdModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 推荐分类
 * 
 * @description
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class WebsiteCourse extends BaseIncrementIdModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1383373953853661904L;

	private Long id;// 分类id
	private String name;//分类名称
	private String link;//更多链接
	private String description;//详细描述
	private int courseNum;//数量限制
}
