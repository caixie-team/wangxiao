package io.wangxiao.edu.sysuser.controller;

import io.wangxiao.commons.util.ObjectUtils;
import io.wangxiao.edu.home.entity.user.User;
import io.wangxiao.edu.home.service.user.UserService;
import io.wangxiao.edu.sysuser.entity.QueryRoleCondition;
import io.wangxiao.edu.sysuser.entity.SysFunction;
import io.wangxiao.edu.sysuser.entity.SysRole;
import io.wangxiao.edu.sysuser.service.FunctionService;
import io.wangxiao.edu.sysuser.service.RoleService;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * @description 角色管理
 */
@Controller
@RequestMapping("/admin")
public class SysRoleController extends SysBaseController {

    // log
    private static final Logger logger = LoggerFactory.getLogger(SysGroupController.class);

    @Autowired
    private RoleService roleService;
    @Autowired
    private FunctionService functionService;
    @Autowired
    private UserService userService;


    // 路径
    String to_add_ref = getViewPath("/sysuser/role_addRef");// 添加角色
    String role_list = getViewPath("/sysuser/role_list");// 角色列表

    @Getter
    @Setter
    private SysRole role;// 页面传递角色对象使用变量
    @Getter
    @Setter
    private SysRole addRole;
    @Getter
    @Setter
    private User user;

    /**
     * 角色管理显示列表
     **/
    @RequestMapping("/role/putRoleFunction/{roleId}")
    public ModelAndView putRoleFunction(HttpServletRequest request,
                                        @PathVariable long roleId) {
        ModelAndView modelAndView = new ModelAndView(role_list);
        try {
            SysRole role = roleService.getRoleById(roleId);
            List<SysFunction> firstLevelFunctionList = functionService
                    .getFunctionList(null);

            modelAndView.addObject("functionList",
                    gson.toJson(firstLevelFunctionList));
            modelAndView.addObject("myfunctionList",
                    gson.toJson(role.getFunctionList()));
            modelAndView.addObject("role", role);
        } catch (Exception e) {
            logger.error(" ++ SysRoleAction.getAllRoleList ", e);
        }
        return modelAndView;
    }


    /**
     * 更新角色下拥有的权限 selectedFunctionIds 为权限
     */


    @RequestMapping("/role/roleList")
    public ModelAndView showRole(HttpServletRequest request) {
        ModelAndView model = new ModelAndView(
                getViewPath("/sysuser/sysrole_list"));
        try {
            List<SysRole> roleList = roleService.getRoleList();
            model.addObject("roleList", roleList);
        } catch (Exception e) {
            logger.error("showRole()---error", e);
            model.setViewName(this.setExceptionRequest(request, e));
        }
        return model;
    }


    // 绑定变量名字和属性，参数封装进类
    @InitBinder("role")
    public void initBinder1(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("role.");
    }

    /**
     * json获得role.页面下拉用
     *
     * @return
     */
    @RequestMapping("/role/getJsonRoleById")
    @ResponseBody
    public Map<String, Object> getJsonRoleById(@ModelAttribute("role") SysRole role) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            role = roleService.getRoleById(role.getRoleId());
            map.put("entity", role);
        } catch (Exception e) {
            logger.error(" ++ getJsonRoleById ", e);
        }
        return map;
    }

    /**
     * 更新角色下拥有的权限 selectedFunctionIds 为权限
     */
    @RequestMapping("/role/updateRoleFunction/{roleId}")
    public ModelAndView updateRoleFunction(@ModelAttribute("role") SysRole role,
                                           @RequestParam("selectedFunctionIds") Set<Long> selectedFunctionIds) {
        try {
            // 更新角色权限
            logger.info("role.getRoleId:" + role.getRoleId() + ",selectedFunctionIds:"
                    + selectedFunctionIds);
            roleService.updateRoleFunction(role.getRoleId(), selectedFunctionIds);
        } catch (Exception e) {
            logger.error("RoleAction.updateRoleFunction", e);
        }
        return new ModelAndView("redirect:/admin/role/roleList?role.roleId=" + role.getRoleId());
    }

    /**
     * 修改角色名称
     *
     * @param roleName
     * @param roleId
     * @return
     */
    @RequestMapping("/role/updateRoleName")
    public ModelAndView updateRoleName(@RequestParam("updateRoleName") String roleName,
                                       @RequestParam("updateRoleId") Long roleId) {
        try {
            // 更新角色名称
            logger.info("role.getRoleId:" + roleId + ",updateRoleName:"
                    + roleName);
            if (ObjectUtils.isNull(roleName) || ObjectUtils.isNull(roleId)) {
                return new ModelAndView(ADMIN_SUCCESS);
            }
            roleService.updateRoleName(roleName, roleId);
        } catch (Exception e) {
            logger.error("RoleAction.updateRoleFunction", e);
        }
        return new ModelAndView("redirect:/admin/role/roleList?role.roleId=" + roleId);
    }


    /**
     * 删除角色
     *
     * @return String
     * @throws Exception
     */
    @RequestMapping(value = "/role/delRole/{roleId}")
    public ModelAndView delRole(@ModelAttribute("roleId") long roleId) {
        try {
            roleService.deleteRoleById(roleId);
            role = null;
        } catch (Exception e) {
            logger.error("RoleAction.delRole", e);
        }
        return new ModelAndView("redirect:/admin/role/roleList");
    }

    /**
     * 添加角色
     *
     * @return String
     * @throws Exception
     */


    @RequestMapping("/role/addRole")
    @ResponseBody
    public Map<String, Object> addRole(HttpServletRequest request,
                                       @ModelAttribute("role") SysRole role) {
        Map<String, Object> json = null;
        try {
            role.setCreateTime(new Date(System.currentTimeMillis()));
            role.setRoleApplyScopeId(SysRole.ROLE_APPLY_SCOPE_ALL);
            role.setRoleTypeId(SysRole.ROLE_TYPE_DEFAULT);
            role.setStatus(SysRole.ROLE_STATUS_DEFAULT);
            roleService.addRole(role);
            json = this.getJsonMap(true, null, role);
        } catch (Exception e) {
            json = this.getJsonMap(false, "系统繁忙，请稍后再操作", null);
            logger.error("RoleAction.addRole", e);
        }
        return json;
    }


    /**
     * 删除 用户对应的role(包括相关关联) 必须参数roleId userId
     *
     * @return String
     * @throws Exception
     */
    @RequestMapping("/role/deleteRoleRef")
    public ModelAndView deleteRoleRef(@RequestParam("roleId") String roleId,
                                      @RequestParam("userId") String userId) {

        try {
            if (StringUtils.isNotEmpty(userId) && StringUtils.isNotEmpty(roleId)) {
                //删除用户角色中间表
                this.roleService.deleteUserRoleRef(Long.valueOf(userId), Long.valueOf(roleId));
            }
        } catch (Exception e) {
            logger.error("RoleAction.deleteRoleRef", e);
        }
        return new ModelAndView(
                "redirect:/admin/user/toEditUserGradeSubjectRoleSec?user.userId=" + userId);
    }

    /**
     * 用户添加新角色显示
     *
     * @return String
     * @throws Exception
     */
    @RequestMapping("/role/toAddRoleRef")
    public ModelAndView toAddRoleRef(HttpServletRequest request,
                                     HttpServletResponse response, @RequestParam("userId") Long userId) {
        ModelAndView modelAndView = new ModelAndView(to_add_ref);
        try {
            QueryRoleCondition queryRoleCondition = new QueryRoleCondition();
            //queryRoleCondition.setRoleApplyScopeId(Role.ROLE_APPLY_SCOPE_ALL);// 不显示超级管理
            // 查询所有的角色list
            List<SysRole> roleList = this.roleService.getRoleListByCondition(queryRoleCondition);
            user = userService.getUserById(userId);
            modelAndView.addObject("roleList", roleList);
            modelAndView.addObject("userId", userId);
            modelAndView.addObject("user", user);
        } catch (Exception e) {
            logger.error("RoleAction.toAddRoleRef", e);
        }
        return modelAndView;
    }

    /**
     * 用户添加角色 需要参数 用户id 角色id
     *
     * @return String
     * @throws Exception
     */
    @RequestMapping("/role/addRoleRef")
    public ModelAndView addRoleRef(HttpServletRequest request,
                                   HttpServletResponse response, @RequestParam("userId") String userId,
                                   @RequestParam("roleId") String roleId) {
        try {
            if (StringUtils.isEmpty(userId) || StringUtils.isEmpty(roleId)) {
                return new ModelAndView(
                        "redirect:/admin/user/toEditUserGradeSubjectRoleSec?user.userId="
                                + userId);
            }
            // 添加
            this.roleService.addUserRoleRef(Long.valueOf(userId),
                    Long.valueOf(roleId));
        } catch (Exception e) {
            logger.error("RoleAction.addRoleRef", e);
        }
        return new ModelAndView(
                "redirect:/admin/user/toEditUserGradeSubjectRoleSec?user.userId=" + userId);
    }

}
