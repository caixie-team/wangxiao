package io.wangxiao.edu.sysuser.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * @description 用户户对应权限
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserRole implements Serializable {
    private Long roleId;
    private Long userId;
    private Date createTime;
}
