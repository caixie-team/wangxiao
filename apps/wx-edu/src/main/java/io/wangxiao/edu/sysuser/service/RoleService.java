/**
 * @ClassName RoleService
 * @package com.supergenius.sns.service.user
 * @description
 * @author liuyidao
 * @Create Date: 2013-3-1 下午10:19:50
 */
package io.wangxiao.edu.sysuser.service;

import java.util.List;
import java.util.Set;

import io.wangxiao.edu.sysuser.entity.QueryRoleCondition;
import io.wangxiao.edu.sysuser.entity.SysRole;
import io.wangxiao.edu.sysuser.entity.UserRole;

public interface RoleService {
    /**
     * 根据用户id获得roleList
     *
     * @param userId
     * @return List<Role>
     */
    List<SysRole> getRoleListByUserId(Long userId);

    /**
     * 获得所有roleList
     *
     * @return List<Role>
     */
    List<SysRole> getRoleList();

    /**
     * 根据id获得有role
     *
     * @param roleId
     * @return Role
     */
    SysRole getRoleById(Long roleId);

    /**
     * 更新角色对应的权限。先删除后增加
     *
     * @param roleId
     * @param functions
     */
    void updateRoleFunction(Long roleId, Set<Long> functions);

    /**
     * 根据id删除role
     *
     * @param roleId
     */
    void deleteRoleById(Long roleId);

    /**
     * 添加角色
     *
     * @param SysRole role
     */
    void addRole(SysRole role);

    /**
     * 删除用户对应的角色
     *
     * @param userId 用户id
     * @param roleId 角色id
     */
    void deleteUserRoleRef(Long userId, Long roleId);

    /**
     * 根据条件获得roleList
     *
     * @param queryRoleCondition
     * @return List<Role>
     */
    List<SysRole> getRoleListByCondition(
            QueryRoleCondition queryRoleCondition);

    /**
     * 用户添加角色对应关系
     *
     * @param userId
     * @param roleId
     */
    void addUserRoleRef(Long userId, Long roleId);

    /**
     * 修改角色名称
     *
     * @param roleName
     * @param roleId
     */
    void updateRoleName(String roleName, Long roleId);

    /**
     * 通过userId删除相应的角色
     * @param userId
     */
    void delUserRoleByUserId(Long userId);


    /**
     * 批量添加用户角色
     * @param userRolesList
     */
    void addbatchAddUserRole(List<UserRole> userRolesList);

}
