package io.wangxiao.edu.sysuser.service.impl;

import io.wangxiao.commons.util.ObjectUtils;
import io.wangxiao.edu.common.constants.CommonConstants;
import io.wangxiao.edu.sysuser.dao.SysUserDao;
import io.wangxiao.edu.sysuser.entity.SysFunction;
import io.wangxiao.edu.sysuser.entity.SysRole;
import io.wangxiao.edu.sysuser.entity.SysUserGroup;
import io.wangxiao.edu.sysuser.service.FunctionService;
import io.wangxiao.edu.sysuser.service.RoleService;
import io.wangxiao.edu.sysuser.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description 系统用户service
 */
@Service("sysUserService")
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserDao sysUserDao;
    @Autowired
    private RoleService roleService;
    @Autowired
    private FunctionService functionService;

    /**
     * 查询 用户权限
     *
     * @return List<Function>
     */
    @Override
    public List<SysFunction> getUserFunction(Long userId) {

        // 查询所拥有的role
        List<SysRole> roleList = roleService.getRoleListByUserId(userId);
        if (ObjectUtils.isNull(roleList)) {
            return null;
        } else {
            //将权限放到list中
            List<SysFunction> functionList = null;
            if (ObjectUtils.isNotNull(userId)) {
                functionList = functionService.getFunctionsByRoles(roleList);
            } else {
                functionList = functionService.getFunctionList(null);
            }
            if (ObjectUtils.isNotNull(functionList)) {
                for (SysFunction function : functionList) {
                    //根据 链接地址类型：1，网校 2，社区 3，考试   拼装权限地址
                    if (function.getFunctionUrlType() == 1) {
                        function.setFunctionUrl(CommonConstants.contextPath + function.getFunctionUrl());
                    } else if (function.getFunctionUrlType() == 2) {
                        function.setFunctionUrl(CommonConstants.snsPath + function.getFunctionUrl());
                    } else if (function.getFunctionUrlType() == 3) {
                        function.setFunctionUrl(CommonConstants.examPath + function.getFunctionUrl());
                    } else if (function.getFunctionUrlType() == 4) {
                        function.setFunctionUrl(CommonConstants.videoPath + function.getFunctionUrl());
                    }
                }
            }
            return functionList;
        }
    }

    @Override
    public Long createSysUserGroup(List<SysUserGroup> SysUserGroupList) {
        return sysUserDao.createSysUserGroup(SysUserGroupList);
    }

    @Override
    public void delSysUserGroup(Long id) {
        sysUserDao.delSysUserGroup(id);
    }

    @Override
    public List<SysUserGroup> getSysUserGroupBySysUserId(SysUserGroup sysUserGroup) {
        return sysUserDao.getSysUserGroupBySysUserId(sysUserGroup);
    }

}
