package io.wangxiao.edu.sysuser.dao;

import io.wangxiao.edu.sysuser.entity.QueryFunctionCondition;
import io.wangxiao.edu.sysuser.entity.SysFunction;
import io.wangxiao.edu.sysuser.entity.SysRole;

import java.util.List;

/**
 * @description 权限操作类
 */
public interface FunctionDao {
    /**
     * 根据parentId获得子权限
     *
     * @param parentId
     * @return
     */
    List<SysFunction> getFunctionListByParentId(Long parentId);

    /**
     * 根据roleId获得权限列表
     *
     * @param roleId
     * @return
     */
    List<SysFunction> getFunctionListByRoleId(Long roleId);

    /**
     * 添加功能
     *
     * @param function 要添加的功能
     * @return id
     */
    Long addFunction(SysFunction function);

    /**
     * 根据id删除一个功能
     *
     * @param functionId 要删除的id
     */
    void delFunctionById(Long functionId);

    /**
     * 修改功能
     *
     * @param function 要修改的功能
     */
    void updateFunction(SysFunction function);

    /**
     * 根据id获取单个功能对象
     *
     * @param functionId 要查询的id
     * @return Function
     */
    SysFunction getFunctionById(Long functionId);

    /**
     * 根据条件获取功能列表
     *
     * @param queryFunctionCondition 查询条件类
     * @return List<Function>
     */
    List<SysFunction> getFunctionList(QueryFunctionCondition queryFunctionCondition);

    /**
     * 获取可以使用的功能权限
     *
     * @return
     */
    List<SysFunction> getUsableFunctionList();

    /**
     * 获取第一级权限菜单列表
     *
     * @param queryFunctionCondition
     * @return
     */
    List<SysFunction> getFirFunction(
            QueryFunctionCondition queryFunctionCondition);

    /**
     * 根据id获取子权限列表
     *
     * @param parentFunctionId
     * @return
     */
    List<SysFunction> getChildFunctionById(Long parentFunctionId);

    /**
     * 删除权限及子权限
     *
     * @return
     */
    void delFunctions(List<Long> list);

    /**
     * 根据多个parentId 查询
     *
     * @param parentList
     * @return
     */
    List<SysFunction> getFunctionListByParentIdSet(List<Long> parentList);

    /**
     * 查询不同用户组下的权限
     *
     * @param roles
     * @return
     */
    List<SysFunction> getFunctionsByRoles(List<SysRole> roles);


}
