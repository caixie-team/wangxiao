package io.wangxiao.user.member.entity;

import io.wangxiao.core.model.BaseIncrementIdModel;

import java.util.Date;

/**
 * 
 * @description 操作记录
 */
public class MemberOrderOptRecord extends BaseIncrementIdModel {

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getOptuser() {
		return optuser;
	}

	public void setOptuser(Long optuser) {
		this.optuser = optuser;
	}

	public String getOptusername() {
		return optusername;
	}

	public void setOptusername(String optusername) {
		this.optusername = optusername;
	}

	public Long getMemberRecordId() {
		return memberRecordId;
	}

	public void setMemberRecordId(Long memberRecordId) {
		this.memberRecordId = memberRecordId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	//	private Long id;//会员操作
	private Long userId;//用户id
	private Long optuser;//操作者id
	private String optusername;//操作者名称
	private Long memberRecordId;//开通记录id
	private String type;//操作类型
	private String description;//描述
	private Date createTime;//创建时间
}
