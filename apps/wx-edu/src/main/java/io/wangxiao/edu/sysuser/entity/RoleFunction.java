package io.wangxiao.edu.sysuser.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @description 角色功能
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class RoleFunction implements Serializable {
    public static int ROLE_FUNCTION_STATUS_DEFAULT = 1;// RoleFunction的默认状态
    private Long roleId;// 角色id
    private Long functionId;// 功能id
    private int status;// 功能
    private java.util.Date createTime;// 创建时间

}
