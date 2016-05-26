package io.wangxiao.edu.sysuser.entity;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class SysUserGroup implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -7871200987819633793L;
    private Long id;	//
    private Long sysUserId;//系统用户id
    private Long groupId;//学员组id
}
