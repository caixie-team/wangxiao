package io.wangxiao.website.entity;

import io.wangxiao.core.model.BaseIncrementIdModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper=false)
public class WebsiteImageMange extends BaseIncrementIdModel implements Serializable{
	private static final long serialVersionUID = 1L;
	private Long id;//id
	private String type;//广告图的类型
	private String image_key;//广告图的key
	private Date createTime;

}
