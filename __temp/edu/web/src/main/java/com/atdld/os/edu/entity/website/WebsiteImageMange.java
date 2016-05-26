package com.atdld.os.edu.entity.website;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class WebsiteImageMange implements Serializable{
	private static final long serialVersionUID = 1L;
	private Long id;//id
	private String type;//广告图的类型
	private String image_key;//广告图的key
	private Date createTime;

}
