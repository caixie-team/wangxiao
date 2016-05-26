package io.wangxiao.auth.domain;

/**
 * Created by bison on 1/10/16.
 */

import org.hibernate.validator.constraints.Email;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "user_user")
public class User {
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




//    public String getRegisterFrom() {
//        return registerFrom;
//    }
//
//    public void setRegisterFrom(String registerFrom) {
//        this.registerFrom = registerFrom;
//    }
//
//    public String getUpdateEmail() {
//        return updateEmail;
//    }
//
//    public void setUpdateEmail(String updateEmail) {
//        this.updateEmail = updateEmail;
//    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String nickname = "";//用户名
    @Email
    @Size(min = 0, max = 50)
    private String email = "";// 邮件

    @Column(name = "email_isavalible",columnDefinition = "tinyint")
    private int emailIsavalible = 0;// 邮件是否验证
    @Size(min = 0, max = 30)
    @Column(name = "mobile", columnDefinition = "char")
    private String mobile = "";// 手机号

    @Column(name = "mobile_isavalible",columnDefinition = "tinyint")
    private int mobileIsavalible = 0;// 手机号是否验证
    @Column(name = "password", columnDefinition = "char")
    private String password;
    @Column(name = "isavalible",columnDefinition = "tinyint")
    private int isavalible = 0;// 是否激活(0正常 1冻结)
    @Size(min = 0, max = 50)
    @Column(name = "customerkey", columnDefinition = "char")
    private String customerkey;
    private Date createdate;
    private String userip = "";

//    private String registerFrom;//账号来源
//    private String updateEmail;//是否可以修改邮箱号 YES可以
    @Id
    @Column(updatable = false, nullable = false)
    private int id;


//    @Id
//    @Column(updatable = false, nullable = false)
//    private Long id;
//
//    @Size(min = 0, max = 50)
//    private String username;
//
//    @Size(min = 0, max = 500)
//    private String password;

//    @Email
//    @Size(min = 0, max = 50)
//    private String email;
//
//    private boolean activated;
//
//    @Size(min = 0, max = 100)
//    @Column(name = "activationkey")
//    private String activationKey;
//
//    @Size(min = 0, max = 100)
//    @Column(name = "resetpasswordkey")
//    private String resetPasswordKey;
//
//    @ManyToMany
//    @JoinTable(
//            name = "user_authority",
//            joinColumns = @JoinColumn(name = "username"),
//            inverseJoinColumns = @JoinColumn(name = "authority"))
//    private Set<Authority> authorities;
//
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public boolean isActivated() {
//        return activated;
//    }
//
//    public void setActivated(boolean activated) {
//        this.activated = activated;
//    }
//
//    public String getActivationKey() {
//        return activationKey;
//    }
//
//    public void setActivationKey(String activationKey) {
//        this.activationKey = activationKey;
//    }
//
//    public String getResetPasswordKey() {
//        return resetPasswordKey;
//    }
//
//    public void setResetPasswordKey(String resetPasswordKey) {
//        this.resetPasswordKey = resetPasswordKey;
//    }
//
//    public Set<Authority> getAuthorities() {
//        return authorities;
//    }
//
//    public void setAuthorities(Set<Authority> authorities) {
//        this.authorities = authorities;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        User user = (User) o;
//
//        if (!username.equals(user.username)) return false;
//
//        return true;
//    }
//
//    @Override
//    public int hashCode() {
//        return username.hashCode();
//    }
//
//    @Override
//    public String toString() {
//        return "User{" +
//                "username='" + username + '\'' +
//                ", password='" + password + '\'' +
//                ", email='" + email + '\'' +
//                ", activated='" + activated + '\'' +
//                ", activationKey='" + activationKey + '\'' +
//                ", resetPasswordKey='" + resetPasswordKey + '\'' +
//                ", authorities=" + authorities +
//                '}';
//    }
}
