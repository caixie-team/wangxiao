package io.wangxiao.edu.home.entity.website;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 推荐分类
 * 
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class WebsiteCourse implements Serializable {

	private Long id;// 分类id
	private String name;//分类名称
	private String link;//更多链接
	private String description;//详细描述
	private int courseNum;//数量限制
}
