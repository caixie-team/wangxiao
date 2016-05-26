package io.wangxiao.auth.domain.user;

/**
 * Created by bison on 1/10/16.
 *
 */

import org.hibernate.validator.constraints.Email;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "user_user")
public class User implements UserDetails {
    @Transient
    protected static final String ROLE_PREFIX = "ROLE_";
    @Transient
    protected static final GrantedAuthority DEFAULT_USER_ROLE = new SimpleGrantedAuthority(ROLE_PREFIX + Privilege.USER.name());


    @Transient
    protected List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

//    public User() {
//    }

    public User() {
        initialAuthorities();
    }

    private void initialAuthorities() {
        //Default, everyone have it
        this.grantedAuthorities.add(DEFAULT_USER_ROLE);
        //default user have all privileges

//        if (user.defaultUser()) {
        this.grantedAuthorities.add(new SimpleGrantedAuthority(ROLE_PREFIX + Privilege.UNITY.name()));
        this.grantedAuthorities.add(new SimpleGrantedAuthority(ROLE_PREFIX + Privilege.MOBILE.name()));
//        } else {
//            final List<Privilege> privileges = user.privileges();
//            for (Privilege privilege : privileges) {
//                this.grantedAuthorities.add(new SimpleGrantedAuthority(ROLE_PREFIX + privilege.name()));
//            }
//        }
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return this.grantedAuthorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }




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

    @Override
    public String getUsername() {

        return String.valueOf(getId());
        // name id email
        // TODO: getPrincipal
//        return this.getEmail();
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
    private Long id;


}
