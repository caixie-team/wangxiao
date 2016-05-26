package co.bluepx.edu.user.entity;

import co.bluepx.edu.core.model.BaseIncrementIdModel;

import java.util.Date;

public class UserForm extends BaseIncrementIdModel {
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public char getEmailIsavalible() {
        return emailIsavalible;
    }

    public void setEmailIsavalible(char emailIsavalible) {
        this.emailIsavalible = emailIsavalible;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public char getMobileIsavalible() {
        return mobileIsavalible;
    }

    public void setMobileIsavalible(char mobileIsavalible) {
        this.mobileIsavalible = mobileIsavalible;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public char getIsavalible() {
        return isavalible;
    }

    public void setIsavalible(char isavalible) {
        this.isavalible = isavalible;
    }

    public String getCustomerkey() {
        return customerkey;
    }

    public void setCustomerkey(String customerkey) {
        this.customerkey = customerkey;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public String getUserip() {
        return userip;
    }

    public void setUserip(String userip) {
        this.userip = userip;
    }

    private String email;// 邮件
    private char emailIsavalible = 0;// 邮件是否验证
    private String mobile;// 手机号
    private char mobileIsavalible = 0;// 手机号是否验证
    private String password;
    private String confirmPassword;
    private char isavalible = 1;// 是否激活(0冻结 1已激活)
    private String customerkey;
    private Date createdate;
    private String userip;
}
