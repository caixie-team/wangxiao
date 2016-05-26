package io.wangxiao.edu.sysuser.controller;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.commons.util.ObjectUtils;
import io.wangxiao.edu.common.util.DatePwdUtil;
import io.wangxiao.edu.home.constants.enums.UserExpandFrom;
import io.wangxiao.edu.home.constants.enums.UserOptType;
import io.wangxiao.edu.home.entity.user.User;
import io.wangxiao.edu.home.entity.user.UserExpand;
import io.wangxiao.edu.home.entity.user.UserGroup;
import io.wangxiao.edu.home.entity.user.UserGroupMiddle;
import io.wangxiao.edu.home.service.user.UserExpandService;
import io.wangxiao.edu.home.service.user.UserGroupMiddleService;
import io.wangxiao.edu.home.service.user.UserGroupService;
import io.wangxiao.edu.home.service.user.UserService;
import io.wangxiao.edu.sysuser.entity.*;
import io.wangxiao.edu.sysuser.service.GroupService;
import io.wangxiao.edu.sysuser.service.RoleService;
import io.wangxiao.edu.sysuser.service.SysUserService;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * @description 后台用户管理
 */
@Controller
@RequestMapping("/admin")
public class SysUserController extends SysBaseController {

    private static final Logger logger = LoggerFactory.getLogger(SysUserController.class);

    @Getter
    @Setter
    private User user;
    @Autowired
    private SysUserService sysUserService;
    private QueryUserCondition queryUserCondition;
    @Autowired
    private GroupService groupService;
    private QueryGroupCondition queryGroupCondition = new QueryGroupCondition();
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserGroupService userGroupService;
    @Autowired
    private UserExpandService userExpandService;
    @Autowired
    private UserGroupMiddleService userGroupMiddleService;

    // 路径
    private String toUserList = getViewPath("/sysuser/sysuser_list");// 用户列表页
    private String toAdduser = getViewPath("/sysuser/user_add");// 添加用户页面
    private String toEditUser = getViewPath("/sysuser/user_edit");// 修改用户页
    private String toEditUserRole = getViewPath("/sysuser/setRole");// 设置用户角色页面
    private String toUpdateUserPwd = getViewPath("/sysuser/user_update_pwd");// 修改用户密码
    private String userManager = getViewPath("/sysuser/userManager");// 修改用户信息页面
    private static String sysuserGroupList = getViewPath("/sysuser/sys-user-group-list");

    /**
     * 后台统一操作成功提示页面
     *
     * @return
     */
    @RequestMapping("/sys/success")
    public String toAdminSuccess() {
        return "/common/success";
    }

    // 绑定变量名字和属性，参数封装进类
    @InitBinder("user")
    public void initBinder1(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("user.");
    }

    /**
     * 用户列表
     */
    @RequestMapping("/user/listAllUser")
    public ModelAndView listAllUser(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("user") User user, @ModelAttribute("page") PageEntity page) {
        ModelAndView modelAndView = new ModelAndView(toUserList);
        try {
            // 页面传来的数据放到page中
            page.setPageSize(20);// 设置每页为20，默认10
            user.setLevel(1);
            List<User> userList = userService.getUserListByGroup(user, page);
            // 获取所有部门
            List<SysGroup> groupList = this.groupService.getGroupList();
            modelAndView.addObject("groupList", gson.toJson(groupList));
            modelAndView.addObject("userList", userList);
            modelAndView.addObject("page", page);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("UserAction.listAllUser", e);
            return new ModelAndView(setExceptionRequest(request, e));
        }
        return modelAndView;
    }

    /**
     * 修改用户信息显示
     */
    @RequestMapping("/user/toEditUser")
    public ModelAndView toEditUser(@ModelAttribute("user") User user) {
        ModelAndView modelAndView = new ModelAndView(toEditUser);
        try {
            // 此处查询一次查询出所有的页面来操作
            List<SysGroup> groupList = this.groupService.getGroupList();

            // 获得用户信息
            user = userService.getUserById(user.getId());
            if (user.getSysUserId() != null) {
                SysGroup sysGroup = groupService.getGroupById((long) user.getSysGroupId());
                if (sysGroup != null) {
                    user.setSysGroupName(sysGroup.getGroupName());
                }
            }

            modelAndView.addObject("groupList", gson.toJson(groupList));
            modelAndView.addObject("user", user);

            // 角色获取
            List<SysRole> roleList = roleService.getRoleList();
            List<SysRole> userRoleList = roleService.getRoleListByUserId(user.getId());
            if (ObjectUtils.isNotNull(userRoleList)) {
                for (SysRole sysRole : roleList) {
                    for (SysRole _sysRole : userRoleList) {
                        if (sysRole.getRoleId() == _sysRole.getRoleId()) {
                            sysRole.setCheck(1);
                            break;
                        }
                    }
                }
            }
            modelAndView.addObject("roleList", roleList);

            List<UserGroupMiddle> UserGroupMiddleList = userGroupMiddleService.getUserGroupByUserId(user.getId());
            PageEntity page = new PageEntity();
            UserGroup userGroup = new UserGroup();
            page.setPageSize(9999);
            List<UserGroup> userGroupList = userGroupService.queryUserGroupListPage(userGroup, page);
            if (ObjectUtils.isNotNull(UserGroupMiddleList)) {
                for (UserGroup _userGroup : userGroupList) {
                    for (UserGroupMiddle userGroupMiddle : UserGroupMiddleList) {
                        if (_userGroup.getId() == userGroupMiddle.getGroupId()) {
                            _userGroup.setCheck(1);
                            continue;
                        }
                    }
                }
            }
            modelAndView.addObject("userGroupList", userGroupList);
        } catch (Exception e) {
            logger.error("UserAction.toEditUser", e);
        }
        return modelAndView;
    }

    /**
     * 用户组级联联动
     */
    @RequestMapping(value = "/user/getAllGroup")
    @ResponseBody
    public Map<String, Object> getAllGroup(@RequestParam String groupId) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            List<SysGroup> groupList = this.groupService.getChildGroupById(Long.valueOf(groupId));
            map.put("entity", groupList);
        } catch (Exception e) {
            logger.error("UserAction.getAllGroup", e);
        }
        return map;
    }

    /**
     * 更新用户信息
     */
    @RequestMapping("/user/editUser")
    public ModelAndView editUser(@ModelAttribute("user") User user, HttpServletRequest request) {
        try {
            logger.info("++++ user" + user.getGroupId());
            user.setUpdateEmail("YES");
            user.setLevel(-1);
            userService.updateUser(user);
            //更新UserExpand的realname
            UserExpand userExpand = new UserExpand();
            userExpand.setCusId(user.getId());
            userExpand.setRealname(user.getRealname());
            userExpandService.updateUserExpandFromUser(userExpand);
            String roleIds = request.getParameter("hiddenRoleIds");
            String groupIds = request.getParameter("hiddenGroupIds");

            // 清除用户角色
            if (StringUtils.isNotEmpty(roleIds)) {
                roleService.delUserRoleByUserId(user.getId());
                List<UserRole> userRoleList = new ArrayList<UserRole>();
                String[] roleArray = roleIds.split(",");
                for (int i = 0; i < roleArray.length; i++) {
                    UserRole userRole = new UserRole();
                    userRole.setUserId(user.getId());
                    userRole.setRoleId(Long.parseLong(roleArray[i]));
                    userRole.setCreateTime(new Date());
                    userRoleList.add(userRole);
                }
                roleService.addbatchAddUserRole(userRoleList);
            }
            UserGroupMiddle userGroupMiddle = new UserGroupMiddle();
            userGroupMiddle.setUserId(user.getId());
            userGroupMiddleService.delteUserGroupMiddleByUserId(userGroupMiddle);
            // 可以添加多个组
            if (StringUtils.isNotEmpty(groupIds)) {
                List<UserGroupMiddle> userGroupMiddleList = new ArrayList<UserGroupMiddle>();
                // 学员信息分组
                String[] groupIdArray = groupIds.split(",");
                for (int i = 0; i < groupIdArray.length; i++) {
                    UserGroupMiddle _userGroupMiddle = new UserGroupMiddle();
                    _userGroupMiddle.setUserId(user.getId());
                    _userGroupMiddle.setGroupId(Long.parseLong(groupIdArray[i]));
                    userGroupMiddleList.add(_userGroupMiddle);
                }
                userGroupMiddleService.addUserGroupMiddle(userGroupMiddleList);
            }
        } catch (Exception e) {
            logger.error("UserAction.editUser", e);
        }
        return new ModelAndView("redirect:/admin/user/listAllUser");
    }

    /**
     * 冻结、解冻用户
     */
    @RequestMapping("/user/freezeUser")
    @ResponseBody
    public Map<String, Object> freezeUser(@ModelAttribute("user") User user, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            // 当前系统用户
            User memUser = getSysLoginedUser(request);
            Long userId = memUser.getId();
            if (userId == user.getId()) {
                map.put("result", "不能冻结自己");
                return map;
            }
            user = userService.getUserById(user.getId());
            // 冻结和解冻状态
            if (user.getIsavalible() == 0) {//冻结
                user.setIsavalible(1);
            } else {//解冻
                user.setIsavalible(0);
            }
            userService.updateUserForIsavalibleById(user);

            // 记录系统用户操作
            map.put("result", "success");
        } catch (Exception e) {
            map.put("result", "error");
            logger.error("UserAction.freezeUser", e);
        }
        return map;
    }

    /**
     * 删除用户
     */
    @RequestMapping("/user/deleteUser/{user}")
    public ModelAndView deleteUser(@RequestParam Long userId, HttpServletRequest request, @ModelAttribute("user") User user) {
        try {// 软删除状态设置为2
            User memuser = getSysLoginedUser(request);
            Long userIds = memuser.getId();
            if (userIds == userId) {
                return new ModelAndView("redirect:/admin/user/listAllUser?page.currentPage=1");
            }
            user = this.userService.getUserById(userId);
            user.setIsavalible(User.USER_DELETE_STATUS);
            this.userService.updateUser(user);
            // 记录系统用户操作
            Map<String, Object> descMap = new HashMap<String, Object>();
            descMap.put("optuser", "操作id_" + userIds);
            descMap.put("optType", "操作_" + UserOptType.SYSDELETE.toString() + "_执行删除");
            descMap.put("userId", "用户id_" + user.getId() + "");
            userService.addUserOptRecord(user.getId(), UserOptType.SYSDELETE.toString(), userIds, user.getNickname(), user.getId(), gson.toJson(descMap));
        } catch (Exception e) {
            logger.error("UserAction.deleteUser", e);
        }
        return new ModelAndView("redirect:/admin/user/listAllUser?page.currentPage=1");
    }

    /**
     * 删除
     *
     * @param request
     * @param id
     * @return
     */

    @RequestMapping("/user/deleteUsers/{id}")
    @ResponseBody
    public Map<String, Object> delUsers(HttpServletRequest request, @PathVariable("id") Long id) {
        Map<String, Object> json = null;
        try {
            userService.deleteUserById(id);
            json = this.getJsonMap(true, "true", null);
        } catch (Exception e) {
            // TODO Auto-generated catch block

            logger.error("SysUserController.delUsers", e);
            json = this.getJsonMap(false, "false", null);
        }
        return json;
    }


    /**
     * 修改密码显示页面(自己修改)
     */
    @RequestMapping("/user/toUpdatePwd")
    public ModelAndView toUpdatePwd(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView(toUpdateUserPwd);
        try {
            user = this.getSysLoginedUser(request);
            user = this.userService.getUserById(user.getId());
            modelAndView.addObject("user", user);
        } catch (Exception e) {
            logger.error("UserAction.toUpdatePwd", e);
        }
        return modelAndView;
    }

    /**
     * 修改密码显示页面 修改其他用户
     */
    @RequestMapping("/user/toUpdateUserPwd")
    public ModelAndView toUpdateUserPwd(@ModelAttribute("user") User user, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView(toUpdateUserPwd);
        try {
            user = this.userService.getUserById(user.getId());
            modelAndView.addObject("user", user);
        } catch (Exception e) {
            logger.error("UserAction.toUpdateUserPwd", e);
        }
        return modelAndView;
    }

    /**
     * 用户修改密码
     */
    @RequestMapping("/user/updatePwd")
    public ModelAndView updatePwd(@ModelAttribute("user") User user) {
        try {
            User _user = userService.getUserById(user.getId());
            _user.setPassword(user.getPassword());
            userService.updatePwdById(_user);
        } catch (Exception e) {
            logger.error("UserAction.updatePwd", e);
        }
        return new ModelAndView(ADMIN_SUCCESS);
    }

    /**
     * 用户角色修改(权限)
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/user/toEditUserGradeSubjectRoleSec")
    public ModelAndView toEditUserGradeSubjectRoleSec(@ModelAttribute("user") User user) throws Exception {
        ModelAndView modelAndView = new ModelAndView(toEditUserRole);
        try {
            // 查询用户信息
            user = userService.getUserById(user.getId());
            // 用户的角色list
            List<SysRole> roleList = roleService.getRoleListByUserId(user.getId());
            user.setRoleList(roleList);
            modelAndView.addObject("user", user);
            modelAndView.addObject("rolelist", roleList);
        } catch (Exception e) {
            logger.error("UserAction.toEditUserGradeSubjectRoleSec", e);
        }
        return modelAndView;
    }

    /**
     * 用户信息自修改
     *
     * @return
     */
    @RequestMapping("/user/userManagerSubmit")
    public String userManagerSubmit(@ModelAttribute("user") User user, HttpServletRequest request) {
        try {
            User memUser = getSysLoginedUser(request);
            memUser.setNickname(user.getNickname());
            memUser.setEmail(user.getEmail());
            memUser.setMobile(user.getMobile());
            userService.updateUser(memUser);
        } catch (Exception e) {
            logger.error("UserAction.userManagerSubmit", e);
            return ERROR;
        }
        return ADMIN_SUCCESS;
    }

    /**
     * 个人用户信息修改
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/user/userManager")
    public String userManager(HttpServletRequest request, HttpServletResponse response) {
        // 从session中获取信息
        user = this.getSysLoginedUser(request);
        request.setAttribute("user", user);
        return userManager;
    }

    /**
     * 修改密码
     *
     * @param changePwd
     */
    public String changePwdSubmit(HttpServletRequest request, HttpServletResponse response) {
        try {
            String changePwd = request.getParameter("changePwd");
            User memUser = getSysLoginedUser(request);
            if (DatePwdUtil.checkIsRight(memUser.getPassword(), this.user.getPassword(), memUser.getCustomerkey())) {
                memUser.setPassword(changePwd);
                // 密码加密处理
                DatePwdUtil.getUserSecretUser(memUser);
                userService.updateUser(memUser);
                // addActionError("密码修改成功!");
            } else {
                // addActionError("原密码输入错误！");
            }
        } catch (Exception e) {
            logger.error("UserAction.changePwdSubmit", e);
            return "";
        }
        return "changePwd";
    }

    /**
     * 新增用户显示
     *
     * @return String
     * @throws Exception
     */
    @RequestMapping("/user/toAddUser")
    public ModelAndView toAddUser() {
        ModelAndView modelAndView = new ModelAndView(toAdduser);
        // 一级组
        try {
            List<SysGroup> groupList = this.groupService.getGroupList();
            modelAndView.addObject("groupList", gson.toJson(groupList));
            // 角色获取
            List<SysRole> roleList = roleService.getRoleList();
            modelAndView.addObject("roleList", roleList);
            PageEntity page = new PageEntity();
            UserGroup userGroup = new UserGroup();
            page.setPageSize(9999);
            List<UserGroup> userGroupList = userGroupService.queryUserGroupListPage(userGroup, page);
            modelAndView.addObject("userGroupList", userGroupList);
        } catch (Exception e) {
            logger.error("UserAction.toAddUser", e);
        }
        return modelAndView;
    }

    /**
     * 添加新用户
     *
     * @return
     */
    @RequestMapping("/user/addUser")
    @ResponseBody
    public Object addUser(@ModelAttribute("user") User adminuser, HttpServletRequest request) {
        Map<String, Object> json = null;
        try {

		/*	// 验证邮箱是否为空
            if (ObjectUtils.isNull(adminuser) || StringUtils.isEmpty(adminuser.getEmail())) {
				json = this.getJsonMap(false, "emailIsNot", null);
				return json;
			}

			// 验证手机是否为空
			if (ObjectUtils.isNull(adminuser.getMobile()) || StringUtils.isEmpty(adminuser.getMobile())) {
				json = this.getJsonMap(false, "mobileIsNull", null);
				return json;
			}*/

		/*	User user = new User();
			user.setEmail(adminuser.getEmail().toLowerCase());// 邮箱存储时转为小写
			user.setMobile(adminuser.getMobile());
			List<User> list = userService.getUserList(user);*/
            // 验证邮箱唯一,邮箱是否已经注册
		/*	if (ObjectUtils.isNotNull(list)) {
				json = this.getJsonMap(false, "regEmailExist", null);
				return json;
			}*/
		/*	int ismobile = userService.getUserByMobile(user);*/
            // 验证手机唯一
			/*if (ismobile != 0) {
				json = this.getJsonMap(false, "regMobileExist", null);
				return json;
			}*/

            DatePwdUtil.getUserSecretUser(adminuser);
            //创建用户信息
            adminuser.setLevel(1);
            adminuser.setRegisterFrom(UserExpandFrom.adminFrom.toString());
            userService.addUser(adminuser);
            String roleIds = request.getParameter("hiddenRoleIds");
            String groupIds = request.getParameter("hiddenGroupIds");
            // 清除用户角色
            if (StringUtils.isNotEmpty(roleIds)) {
                List<UserRole> userRoleList = new ArrayList<UserRole>();
                String[] roleArray = roleIds.split(",");
                for (int i = 0; i < roleArray.length; i++) {
                    UserRole userRole = new UserRole();
                    userRole.setUserId(adminuser.getId());
                    userRole.setRoleId(Long.parseLong(roleArray[i]));
                    userRole.setCreateTime(new Date());
                    userRoleList.add(userRole);
                }
                roleService.addbatchAddUserRole(userRoleList);
            }
            if (StringUtils.isNotEmpty(groupIds)) {
                if (groupIds != null && !groupIds.equals("")) {
                    String[] idsArry = groupIds.split(",");
                    List<UserGroupMiddle> userGroupMiddleList = new ArrayList<UserGroupMiddle>();
                    for (int i = 0; i < idsArry.length; i++) {
                        UserGroupMiddle userGroupMiddle = new UserGroupMiddle();
                        userGroupMiddle.setGroupId(Long.parseLong(idsArry[i]));
                        userGroupMiddle.setUserId(adminuser.getId());
                        userGroupMiddleList.add(userGroupMiddle);
                    }
                    userGroupMiddleService.addUserGroupMiddle(userGroupMiddleList);
                }
            }
            json = this.getJsonMap(true, "success", null);
        } catch (Exception e) {
            logger.error("UserAction.addUser", e);
            json = this.getJsonMap(false, "false", null);
        }
        return json;
    }

    /**
     * 特定小组
     *
     * @param model
     * @param request
     * @return
     */
    @RequestMapping("/sysusergroup/querylist")
    public String querySysUserGroupList(Model model, HttpServletRequest request) {
        try {
            User user = getSysLoginedUser(request);
            SysUserGroup sysUserGroup = new SysUserGroup();
            sysUserGroup.setSysUserId(user.getId());
            List<UserGroupMiddle> userGroupList = userGroupMiddleService.getUserGroupByUserId(user.getId());
            if (ObjectUtils.isNotNull(userGroupList)) {
                List<Long> ids = new ArrayList<Long>();
                for (UserGroupMiddle userGroup : userGroupList) {
                    ids.add(userGroup.getGroupId());
                }
                List<UserGroup> _userGroupList = userGroupService.getUserGroupListByIds(ids);
                model.addAttribute("userGroupList", _userGroupList);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("queryUserGroupList" + e);
        }
        return sysuserGroupList;
    }

    // 绑定变量名字和属性，参数封装进类
    @InitBinder("queryUserCondition")
    public void initBinder2(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("queryUserCondition.");
    }

    /**
     * 检查用户名是否可用
     *
     * @return
     */
	/*@RequestMapping("/user/checkLoginName")
	public String checkLoginName(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("queryUserCondition") QueryUserCondition queryUserCondition) {
		try {
			String loginName = queryUserCondition.getSearchKey();
			if (StringUtils.isNotEmpty(loginName)) {
				SysUser user2 = sysUserService.getUserByLoginName(loginName.trim());
				if (user2 == null) {
					this.sendMessage(request, response, "true");
					return null;
				}
			}
			this.sendMessage(request, response, "false");
		} catch (Exception e) {
			logger.error("UserAction.checkLoginName", e);
			return "";
		}
		return null;
	}*/
    public QueryGroupCondition getQueryGroupCondition() {
        if (queryGroupCondition == null) {
            queryGroupCondition = new QueryGroupCondition();
        }
        return queryGroupCondition;
    }

    public void setQueryGroupCondition(QueryGroupCondition queryGroupCondition) {
        this.queryGroupCondition = queryGroupCondition;
    }

    public QueryUserCondition getQueryUserCondition() {
        if (queryUserCondition == null) {
            queryUserCondition = new QueryUserCondition();
            queryUserCondition.setUserType(-1);
        }
        return queryUserCondition;
    }

    public void setQueryUserCondition(QueryUserCondition queryUserCondition) {
        this.queryUserCondition = queryUserCondition;
    }

    /**
     * 批量删除用户
     *
     * @param request
     * @param artIds
     * @return
     */
    @RequestMapping("/user/deluserBatch")
    @ResponseBody
    public Map<String, Object> deluserBatch(HttpServletRequest request, @RequestParam("userIds") String userIds) {
        Map<String, Object> json = null;
        try {
            if (StringUtils.isNotEmpty(userIds)) {
                // 批量刪除
                userService.delUserBatch(userIds);
                json = this.getJsonMap(true, "success", null);
            } else {
                json = this.getJsonMap(false, "false", null);
            }
        } catch (Exception e) {
            logger.error("UserAction.deluserBatch", e);
            json = this.getJsonMap(false, "false", null);
        }
        return json;
    }

}
