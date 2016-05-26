package com.atdld.os.sns.entity.genius;

import java.io.Serializable;

import com.atdld.os.sns.entity.customer.SnsUserExpand;

import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * @author :
 * @ClassName com.atdld.os.sns.entity.genius.UserExpand
 * @description 超天才用户信息
 * @Create Date : 2014-1-16 上午11:28:29
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SearchUser implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 3182856923246124322L;
    private Long oid;
    private String uid;
    private int level;
    private String usersn;
    private String studentsn;
    private String judgementsn;
    private String genderName;
    private String email;
    private String newemail;
    private String password;
    private String typeName;
    private String paypwd;
    private String resetpwd;
    private String name;
    private String nickname;
    private String showname;
    private java.math.BigDecimal account;
    private java.math.BigDecimal freezeaccount;
    private int type;
    private int channelfrom;
    private String avatarbig;
    private String avatar;
    private String avatarlittle;
    private String original;
    private String summary;
    private Long specialty;
    private String job;
    private String department;
    private String company;
    private String identityid;
    private String domain;
    private int gender;
    private java.util.Date birthday;
    private String college;
    private String address;
    private String qq;
    private String msn;
    private String phone;
    private String mobile;
    private String validcode;
    private Long logincount;
    private java.util.Date lastlogintime;
    private String lastloginip;
    private int status;
    private java.util.Date createtime;
    private java.util.Date updatetime;
    private SnsUserExpand userExpand;
    private Long cusAttentionId;// 是否关注过这个用户的 关注表id
    private int friendId;// 好友id
}
