package com.atdld.os.member.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * @ClassName  com.atdld.os.edu.entity.member.MemberOrderOptRecord
 * @description 操作记录
 * @author :
 * @Create Date : 2014年10月29日 下午3:28:23
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class MemberOrderOptRecord implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;//会员操作
	private Long userId;//用户id
	private Long optuser;//操作者id
	private String optusername;//操作者名称
	private Long memberRecordId;//开通记录id
	private String type;//操作类型
	private String description;//描述
	private Date createTime;//创建时间
}
