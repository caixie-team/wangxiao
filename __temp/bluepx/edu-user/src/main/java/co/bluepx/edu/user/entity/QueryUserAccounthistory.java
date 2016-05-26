
package co.bluepx.edu.user.entity;

import co.bluepx.edu.core.model.BaseIncrementIdModel;

import java.util.Date;

public class QueryUserAccounthistory extends BaseIncrementIdModel {
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getActHistoryType() {
		return actHistoryType;
	}

	public void setActHistoryType(String actHistoryType) {
		this.actHistoryType = actHistoryType;
	}

	public String getBizType() {
		return bizType;
	}

	public void setBizType(String bizType) {
		this.bizType = bizType;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	private Long userId;// 用户id
	private String actHistoryType;//交易类型
	private String bizType;//业务类型
	private java.util.Date startTime;	//开始时间
	private java.util.Date endTime;		//结束时间
}
