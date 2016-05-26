package com.atdld.os.app.entity.website;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 推荐分类
 * 
 * @ClassName com.atdld.os.edu.entity.website.WebsiteCourse
 * @description
 * @author :
 * @Create Date : 2014年6月7日 下午1:52:08
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AppWebsiteCourse implements Serializable {
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
