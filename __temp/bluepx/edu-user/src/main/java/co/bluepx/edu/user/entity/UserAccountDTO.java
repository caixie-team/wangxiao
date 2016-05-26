package co.bluepx.edu.user.entity;

import co.bluepx.edu.core.model.BaseIncrementIdModel;

import java.math.BigDecimal;
import java.util.Date;

public class UserAccountDTO extends BaseIncrementIdModel {

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getForzenAmount() {
        return forzenAmount;
    }

    public void setForzenAmount(BigDecimal forzenAmount) {
        this.forzenAmount = forzenAmount;
    }

    public BigDecimal getCashAmount() {
        return cashAmount;
    }

    public void setCashAmount(BigDecimal cashAmount) {
        this.cashAmount = cashAmount;
    }

    public BigDecimal getVmAmount() {
        return vmAmount;
    }

    public void setVmAmount(BigDecimal vmAmount) {
        this.vmAmount = vmAmount;
    }

    public String getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    private Long userId;// 用户id
    private java.util.Date createTime;// 创建时间
    private java.util.Date lastUpdateTime;// 最后更新时间
    private java.math.BigDecimal balance;// 账户余额
    private java.math.BigDecimal forzenAmount;// 冻结金额
    private java.math.BigDecimal cashAmount;// 银行入账
    private java.math.BigDecimal vmAmount;// 课程卡入账
    private String accountStatus;// 账户状态
    private String email;		//电子邮箱
    private String nickName;	//昵称
    private String mobile;      //手机号

}
