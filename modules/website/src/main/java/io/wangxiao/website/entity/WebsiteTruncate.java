package io.wangxiao.website.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 清空表
 * @author Administrator
 *
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class WebsiteTruncate implements Serializable{
	private static final long serialVersionUID = 1L;
	private Long id;
    private String tableName;//表名
    private String type;//类型 web网校  exma考试 sns社区
    private String desc;//描述
	
}
