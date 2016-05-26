package io.wangxiao.edu.sysuser.service;

import io.wangxiao.edu.sysuser.entity.SysFunction;
import io.wangxiao.edu.sysuser.entity.SysUserGroup;

import java.util.List;


public interface SysUserService {


    /**
     * 查询 用户权限
     *
     * @param userId
     * @return List<SysFunction>
     */
    List<SysFunction> getUserFunction(Long userId);

    /**
     * 获取系统学员组
     *
     * @param sysUserGroup
     * @return
     */
    List<SysUserGroup> getSysUserGroupBySysUserId(SysUserGroup sysUserGroup);

    /**
     * 删除系统学员组
     *
     * @param id
     */
    void delSysUserGroup(Long id);

    /**
     * 创建系统学员组
     *
     * @param SysUserGroupList
     * @return
     */
    Long createSysUserGroup(List<SysUserGroup> SysUserGroupList);

}
