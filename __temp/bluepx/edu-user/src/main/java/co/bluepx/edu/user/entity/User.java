package co.bluepx.edu.user.entity;

import co.bluepx.edu.core.model.BaseIncrementIdModel;

import java.util.Date;

public class User extends BaseIncrementIdModel {

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getEmailIsavalible() {
        return emailIsavalible;
    }

    public void setEmailIsavalible(int emailIsavalible) {
        this.emailIsavalible = emailIsavalible;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public int getMobileIsavalible() {
        return mobileIsavalible;
    }

    public void setMobileIsavalible(int mobileIsavalible) {
        this.mobileIsavalible = mobileIsavalible;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getIsavalible() {
        return isavalible;
    }

    public void setIsavalible(int isavalible) {
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

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getRegisterFrom() {
        return registerFrom;
    }

    public void setRegisterFrom(String registerFrom) {
        this.registerFrom = registerFrom;
    }

    public String getUpdateEmail() {
        return updateEmail;
    }

    public void setUpdateEmail(String updateEmail) {
        this.updateEmail = updateEmail;
    }

    private String nickname = "";//用户名
    private String email = "";// 邮件
    private int emailIsavalible = 0;// 邮件是否验证
    private String mobile = "";// 手机号
    private int mobileIsavalible = 0;// 手机号是否验证
    private String password;
    private int isavalible = 0;// 是否激活(0正常 1冻结)
    private String customerkey;
    private Date createdate;
    private String userip = "";

    private String startDate;//开始时间
    private String endDate;//结束时间
    private String courseName;//课程名称 用于查询
    private String registerFrom;//账号来源
    private String updateEmail;//是否可以修改邮箱号 YES可以
}
