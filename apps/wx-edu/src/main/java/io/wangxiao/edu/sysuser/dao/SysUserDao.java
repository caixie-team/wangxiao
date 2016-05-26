/**
 * @ClassName UserDao
 * @package com.supergenius.sns.dao
 * @description
 * @author liuyidao
 * @Create Date: 2013-3-2 上午11:44:42
 */
package io.wangxiao.edu.sysuser.dao;

import io.wangxiao.edu.sysuser.entity.SysUserGroup;

import java.util.List;

/**
 * @description 系统 用户管理
 */
public interface SysUserDao {

    /**
     * 获取系统学员组
     * @param sysUserGroup
     * @return
     */
    List<SysUserGroup> getSysUserGroupBySysUserId(SysUserGroup sysUserGroup);

    /**
     * 删除系统学员组
     * @param id
     */
    void delSysUserGroup(Long id);

    /**
     * 创建系统学员组
     * @param SysUserGroupList
     * @return
     */
    Long createSysUserGroup(List<SysUserGroup> SysUserGroupList);

}
