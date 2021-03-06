package io.wangxiao.edu.sysuser.dao.impl;

import io.wangxiao.commons.dao.impl.GenericDaoImpl;
import io.wangxiao.edu.sysuser.dao.RoleDao;
import io.wangxiao.edu.sysuser.entity.QueryRoleCondition;
import io.wangxiao.edu.sysuser.entity.RoleFunction;
import io.wangxiao.edu.sysuser.entity.SysRole;
import io.wangxiao.edu.sysuser.entity.UserRole;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("roleDao")
public class RoleDaoImpl extends GenericDaoImpl implements RoleDao {

    /**
     * 根据用户id查询所有role
     *
     * @param userId
     * @return List<Role>
     */
    @Override
    public List<SysRole> getRoleListByUserId(Long userId) {
        return this.selectList("RoleMapper.getRoleListByUserId", userId);
    }

    /**
     * 获得所有roleList
     *
     * @return List<Role>
     */
    @Override
    public List<SysRole> getRoleList() {
        return this.selectList("RoleMapper.getRoleList", null);
    }

    /**
     * 根据id获得有role
     *
     * @param roleId 角色id
     * @return Role
     */
    @Override
    public SysRole getRoleById(Long roleId) {
        return this.selectOne("RoleMapper.getRoleById", roleId);
    }

    /**
     * 根据roleId删除角色权限对应关系表
     *
     * @param roleId
     */
    @Override
    public void deleteRoleFunctionByRoleId(Long roleId) {
        this.delete("RoleFunctionMapper.deleteRoleFunctionByRoleId", roleId);
    }

    /**
     * 批量添加角色权限
     *
     * @param List <RoleFunction> list 角色list
     */
    @Override
    public void createRoleFunctionBatch(List<RoleFunction> list) {
        this.insert("RoleFunctionMapper.createRoleFunctionBatch", list);
    }

    /**
     * 删除角色
     *
     * @param roleId
     */
    @Override
    public void deleteRoleById(Long roleId) {
        this.delete("RoleMapper.deleteRoleById", roleId);
    }

    /**
     * 添加角色
     *
     * @param SysRole Role
     */
    @Override
    public void addRole(SysRole role) {
        this.insert("RoleMapper.createRole", role);
    }

    /**
     * 删除用户对应的角色
     *
     * @param userId
     * @param roleId
     */
    @Override
    public void deleteUserRoleRef(Long userId, Long roleId) {
        Map<String, Long> map = new HashMap<String, Long>();
        map.put("userId", userId);
        map.put("roleId", roleId);
        this.delete("UserRoleMapper.deleteUserRoleByuserIdRoleId", map);
    }

    /**
     * 根据roleID删除用户角色对应表
     *
     * @param roleId
     */
    @Override
    public void deleteUserRoleByRoleId(Long roleId) {
        this.delete("UserRoleMapper.deleteUserRoleByRole", roleId);
    }

    /**
     * 根据条件获得roleList
     *
     * @param queryRoleCondition
     * @return List<Role>
     */
    @Override
    public List<SysRole> getRoleListByCondition(
            QueryRoleCondition queryRoleCondition) {
        return this.selectList("RoleMapper.getRoleListByCondition",
                queryRoleCondition);
    }

    /**
     * 用户添加角色对应关系
     *
     * @param userId
     * @param roleId
     */
    @Override
    public void addUserRoleRef(Long userId, Long roleId) {

        UserRole ugsr = new UserRole();
        ugsr.setRoleId(roleId);
        ugsr.setUserId(userId);
        ugsr.setCreateTime(new Date());

        this.insert("UserRoleMapper.createUserRole", ugsr);
    }

    /**
     * 修改角色名称
     *
     * @param roleName
     * @param roleId
     */
    public void updateRoleName(String roleName, Long roleId) {
        SysRole role = new SysRole();
        role.setRoleId(roleId);
        role.setRoleName(roleName);
        this.update("RoleMapper.updateRoleNameByRoleId", role);
    }

    /**
     * 通过userId删除角色
     */
    public void deleteUserRoleByUserId(Long userId) {
        // TODO Auto-generated method stub
        this.delete("UserRoleMapper.delteUserRoleByUserId", userId);
    }

    /**
     * 批量添加用户角色
     */
    public void addbatchAddUserRole(List<UserRole> userRolesList) {
        // TODO Auto-generated method stub
        this.insert("UserRoleMapper.batchAddUserRole", userRolesList);
    }

}
