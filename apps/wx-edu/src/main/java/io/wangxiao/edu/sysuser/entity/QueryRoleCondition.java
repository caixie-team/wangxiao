package io.wangxiao.edu.sysuser.entity;

import io.wangxiao.commons.entity.PageEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;


@Data
@EqualsAndHashCode(callSuper = false)
public class QueryRoleCondition extends PageEntity implements Serializable {
    private Long roleId;//角色id
    private int roleTypeId;//角色类别id
    private int roleApplyScopeId;//角色使用范围id
    private Long userId;//用户ID

}