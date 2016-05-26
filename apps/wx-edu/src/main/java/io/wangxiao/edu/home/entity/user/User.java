package io.wangxiao.edu.home.entity.user;

import io.wangxiao.edu.sysuser.entity.SysRole;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class User implements Serializable {

    public static int USER_DELETE_STATUS = 1;
    public static int USER_DEFAULT_STATUS = 0;

    private Long id;// 主键 id
    private String nickname = "";// 用户名
    private String email = "";// 邮件
    private int emailIsavalible = 0;// 邮件是否验证
    private String mobile = "";// 手机号
    private int mobileIsavalible = 0;// 手机号是否验证
    private String password;
    private int isavalible = 0;// 是否激活(0正常 1冻结)
    private String customerkey;
    private Date createdate;
    private String userip = "";

    private String startDate;// 开始时间
    private String endDate;// 结束时间
    private String courseName;// 课程名称 用于查询
    private String registerFrom;// 账号来源
    private String updateEmail;// 是否可以修改邮箱号 YES可以
    private int groupId;// 用户组id
    private String groupIds; // 学员ids
    private String verifyType;// 验证类型
    private String note;// 备注
    private int rowsNumber;// 行数
    private Long sysUserId;// 系统用户编号
    private int level;// 级别0学员1员工
    private int sysGroupId;//部门编号

    private List<SysRole> roleList;//角色列表
    private String sysGroupName; //部门名称
    private String name;//部门name
    private String realname;//真实姓名
    private String groupnames;//部门name集合（一人对多部门）

    private int studyTime;// 学习时长
    private int courseNum;// 学习课程数

    private Long notInGroup;
}
