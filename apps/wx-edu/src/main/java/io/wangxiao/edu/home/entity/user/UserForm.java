package io.wangxiao.edu.home.entity.user;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
public class UserForm implements Serializable {
    private int id;// 主键 id
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

    private String nickName;    //昵称
    private String realname;
    private Long groupId;
    private String groupIds;    //组ids
    private int level;//级别1员工2领导
    private int sysGroupId;//部门编号
    private String value;//绑定第三方key值
    private String profileType;//第三方绑定类型
    private String photo;//第三方绑定头像
}
