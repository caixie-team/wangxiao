package io.wangxiao.core.model;

import java.io.Serializable;
import java.util.Date;

//BaseIncrementIdModel
public class BaseModel extends BaseIncrementIdModel implements Serializable{
	private static final long serialVersionUID = -8719003747106030351L;

	private Long createBy;
	
	private String createByName;
	
	private Date createDate;

	public Long getCreateBy() {
		return createBy;
	}

	public void setCreateBy(Long createBy) {
		this.createBy = createBy;
	}

	public String getCreateByName() {
		return createByName;
	}

	public void setCreateByName(String createByName) {
		this.createByName = createByName;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
}
