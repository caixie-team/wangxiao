package io.wangxiao.edu.sysuser.service;

import io.wangxiao.edu.sysuser.entity.QueryFunctionCondition;
import io.wangxiao.edu.sysuser.entity.SysFunction;
import io.wangxiao.edu.sysuser.entity.SysRole;

import java.util.List;

/**
 * @description 权限管理
 */
public interface FunctionService {

    /**
     * 添加功能
     *
     * @param function 要添加的功能
     * @return id
     * 生成的主键
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
     * @return 年级
     */
    SysFunction getFunctionById(Long functionId);

    /**
     * 根据条件获取功能列表
     *
     * @param queryFunctionCondition 查询条件
     * @return 查询结果
     */
    List<SysFunction> getFunctionList(QueryFunctionCondition queryFunctionCondition);

    /**
     * 获取可以使用的功能权限
     *
     * @return List<Function>
     */
    List<SysFunction> getUsableFunctionList();

    /**
     * 根据id获取子权限列表
     *
     * @param parentFunctionId
     * @return List<Function>
     */
    List<SysFunction> getChildFunctionById(Long parentFunctionId);

    /**
     * 删除权限及子权限
     *
     * @return
     */
    void delFunctions(List<Long> list);

    /**
     * 根据parentId获得子权限
     *
     * @param parentId
     * @return List<Function>
     */
    List<SysFunction> getFunctionListByParentId(Long parentId);

    /**
     * 查询不同用户组下的权限
     *
     * @param roles
     * @return
     */
    List<SysFunction> getFunctionsByRoles(List<SysRole> roles);


    /**
     * 根据id获取子权限列表,左侧显示，全部的权限
     *
     * @param parentFunctionId
     * @return List<Function>
     */
    List<SysFunction> getLeftFunctionById(Long parentFunctionId);


}
