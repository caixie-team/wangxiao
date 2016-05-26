package io.wangxiao.edu.sysuser.dao.impl;

import io.wangxiao.commons.dao.impl.GenericDaoImpl;
import io.wangxiao.edu.sysuser.dao.SysUserDao;
import io.wangxiao.edu.sysuser.entity.SysUserGroup;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @description 系统用户管理
 */
@Repository("sysUserDao")
public class SysUserDaoImpl extends GenericDaoImpl implements SysUserDao {

    /**
     * 新增系统用户
     *
     * @param SysUserGroupList
     */
    public Long createSysUserGroup(List<SysUserGroup> SysUserGroupList) {
        return this.insert("SysUserGroupMiddleMapper.addGroupMiddleSysUuser", SysUserGroupList);
    }

    /**
     * 删除系统用户
     *
     * @param id
     */
    public void delSysUserGroup(Long id) {
        this.delete("SysUserGroupMiddleMapper.deleteUserGroupMiddle", id);
    }

    /**
     * 获取系统用户组列表集合
     *
     * @param sysUserGroup
     */
    public List<SysUserGroup> getSysUserGroupBySysUserId(SysUserGroup sysUserGroup) {
        return this.selectList("SysUserGroupMiddleMapper.getUserGroupMiddle", sysUserGroup);
    }

}
