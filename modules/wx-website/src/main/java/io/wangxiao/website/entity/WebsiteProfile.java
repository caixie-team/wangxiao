package io.wangxiao.website.entity;

import io.wangxiao.core.model.BaseIncrementIdModel;

import java.io.Serializable;

/**
 * 网站配置实体
 * 
 * @ClassName com.atdld.os.edu.entity.website.WebsiteProfile
 * @description
 * @author :
 * @Create Date : 2014年6月11日 下午3:36:07
 */
//@Table(name = "website_profile")
public class WebsiteProfile extends BaseIncrementIdModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6689726203603217717L;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}


	public String getDesciption() {
		return desciption;
	}

	public void setDesciption(String desciption) {
		this.desciption = desciption;
	}

	public String getExplain() {
		return explain;
	}

	public void setExplain(String explain) {
		this.explain = explain;
	}
	private String type;//类型
	private String desciption;//描述内容JSON格式
	private String explain;//说明
}
