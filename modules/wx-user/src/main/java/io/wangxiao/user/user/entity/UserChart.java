package io.wangxiao.user.user.entity;

import io.wangxiao.core.model.BaseIncrementIdModel;

import java.math.BigDecimal;

public class UserChart extends BaseIncrementIdModel {
	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public int getAllStudentNum() {
		return allStudentNum;
	}

	public void setAllStudentNum(int allStudentNum) {
		this.allStudentNum = allStudentNum;
	}

	public int getPayOrderNum() {
		return payOrderNum;
	}

	public void setPayOrderNum(int payOrderNum) {
		this.payOrderNum = payOrderNum;
	}

	public int getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}

	public BigDecimal getAmountNum() {
		return amountNum;
	}

	public void setAmountNum(BigDecimal amountNum) {
		this.amountNum = amountNum;
	}

	public BigDecimal getCashMonty() {
		return cashMonty;
	}

	public void setCashMonty(BigDecimal cashMonty) {
		this.cashMonty = cashMonty;
	}

	private String dateTime;	//返回的时间s
	private int allStudentNum;	//注册学生个数
	private int payOrderNum;	//付款订单
	private int orderNum;		//订单数量
	private BigDecimal amountNum;		//实付金额
	private BigDecimal cashMonty;//课程卡金额
}
