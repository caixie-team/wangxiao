package com.atdld.os.app.entity.website;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 登陆统计
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class WebsiteLogin implements Serializable{
	private static final long serialVersionUID = 1L;
	private Long id;
    private String ip;//ip
    private String brand;//品牌
    private String type;//型号
    private String size;//尺寸
    @DateTimeFormat(pattern="yyyy-mm-dd hh-mm-ss")
    private Date createTime;//添加时间
	private Long userId;
    private String email;
    @DateTimeFormat(pattern="yyyy-mm-dd hh-mm-ss")
    private Date endTime;
    @DateTimeFormat(pattern="yyyy-mm-dd hh-mm-ss")
    private Date startTime;
}
