package io.wangxiao.edu.sysuser.entity;

import io.wangxiao.commons.entity.PageEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;


/**
 * @description 查询用户角色权限条件类
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class QueryRoleFunctionCondition extends PageEntity implements Serializable {
    private Long roleId;//角色id
    private Long functionId;//功能id
}