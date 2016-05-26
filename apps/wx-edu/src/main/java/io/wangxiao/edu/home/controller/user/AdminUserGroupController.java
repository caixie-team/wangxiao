package io.wangxiao.edu.home.controller.user;

import com.google.gson.JsonObject;
import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.commons.util.ObjectUtils;
import io.wangxiao.edu.common.contoller.SingletonLoginUtils;
import io.wangxiao.edu.home.common.EduBaseController;
import io.wangxiao.edu.home.entity.user.User;
import io.wangxiao.edu.home.entity.user.UserGroup;
import io.wangxiao.edu.home.entity.user.UserGroupMiddle;
import io.wangxiao.edu.home.service.user.UserGroupMiddleService;
import io.wangxiao.edu.home.service.user.UserGroupService;
import io.wangxiao.edu.home.service.user.UserService;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminUserGroupController extends EduBaseController {

    private Logger logger = LoggerFactory.getLogger(AdminUserGroupController.class);


    @Autowired
    private UserGroupService userGroupService;

    @Autowired
    private UserGroupMiddleService userGroupMiddleService;

    @Autowired
    private UserService userService;

    // 修改学员组页面
    private static String updateUserGroup = getViewPath("/admin/user/group/user_group_update");
    // 部门列表页面
    private static String userGroupList = getViewPath("/admin/user/group/user-group-list");

    // 初始化学员组信息
    private static String initUserGroup = getViewPath("/admin/user/group/user-group-add");
    private static final String groupUserList = getViewPath("/admin/user/group/user_list");// 返回组的学员列表
    private static final String toAddUserForGroup = getViewPath("/admin/user/group/add_user_forGroup");// 添加组学员
    private static final String grouprecord = getViewPath("/admin/user/group/user_grouptask_record");// 部门大体信息页面

    // 绑定变量名字和属性，参数封装进类
    @InitBinder("userGroup")
    public void initBinderUserGroup(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("userGroup.");
    }

    // 绑定变量名字和属性，参数封装进类
    @InitBinder("user")
    public void initBinderUser(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("user.");
    }

    /**
     * 查询学员组信息
     *
     * @return
     */
    @RequestMapping("/userGroup/toUpdate/{id}")
    public String queryUserGroup(Model model, HttpServletRequest request, @PathVariable("id") Long id) {
        try {
            UserGroup userGroup = userGroupService.queryUserGroupById(id);
            model.addAttribute("userGroup", userGroup);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("queryUserGroup" + e);
        }
        return updateUserGroup;
    }

    /**
     * 初始化添加页面
     *
     * @return
     */
    @RequestMapping("/usergroup/init")
    public String toInitUserGroup() {
        return initUserGroup;
    }

    /**
     * 学员组信息添加
     *
     * @param model
     * @param request
     * @param userGroup
     * @return
     */
    @RequestMapping("/usergroup/add")
    public String toAddUserGroup(Model model, HttpServletRequest request, @ModelAttribute("userGroup") UserGroup userGroup) {
        try {
            if (ObjectUtils.isNotNull(userGroup)) {
                userGroupService.addUserGroup(userGroup);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("toAddUserGroup" + e);
        }
        return "redirect:/admin/usergroup/querylist";
    }

    /**
     * 判断所有组名称重复
     *
     * @param request
     * @return
     */
    @RequestMapping("/usergroup/checkGroupName")
    @ResponseBody
    public Object checkGroupName(HttpServletRequest request) {
        Map<String, Object> json = null;
        try {
            String groupName = request.getParameter("groupName");
            String groupId = request.getParameter("groupId");
            UserGroup userGroup = new UserGroup();
            userGroup.setName(groupName);
            if (StringUtils.isNotEmpty(groupId)) {
                userGroup.setId(Long.parseLong(groupId));
            }
            Boolean ok = userGroupService.checkUserGroup(userGroup);
            if (ok) {
                json = this.getJsonMap(true, "success", null);
            } else {
                json = this.getJsonMap(false, "", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("checkGroupName" + e);
        }
        return json;
    }

    /**
     * 学员组信息修改
     *
     * @param model
     * @param request
     * @param userGroup
     * @return
     */
    @RequestMapping("/usergroup/update")
    public String updateUserGroup(Model model, HttpServletRequest request, @ModelAttribute("userGroup") UserGroup userGroup) {
        try {
            if (ObjectUtils.isNotNull(userGroup)) {
                if (userGroup.getStatus() == 1) {
                    UserGroup _userGroup = userGroupService.queryUserGroupById(userGroup.getId());
                    _userGroup.setStatus(userGroup.getStatus());
                    userGroupService.updateUserGroup(_userGroup);
                } else {
                    userGroupService.updateUserGroup(userGroup);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("updateUserGroup" + e);
        }
        return "redirect:/admin/usergroup/querylist";
    }

    /**
     * 岗位列表查询
     *
     * @param model
     * @param request
     * @param page
     * @param userGroup
     * @return
     */
    @RequestMapping("/usergroup/querylist")
    public String queryUserGroupList(Model model, HttpServletRequest request, @ModelAttribute("page") PageEntity page, @ModelAttribute("userGroup") UserGroup userGroup) {
        try {
            List<UserGroup> userGroupList = userGroupService.queryUserGroupListPage(userGroup, page);
            model.addAttribute("userGroupList", userGroupList);
            model.addAttribute("userGroup", userGroup);
            model.addAttribute("page", page);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("queryUserGroupList" + e);
        }
        return userGroupList;
    }


    /**
     * 显示组中学员列表
     *
     * @param request
     * @param user
     * @param groupid
     * @param page
     * @return
     */
    @RequestMapping("/usergroup/getUserListByGroupId/{groupid}")
    public ModelAndView getUserListByGroupId(HttpServletRequest request, @ModelAttribute("user") User user, @PathVariable("groupid") int groupid, @ModelAttribute("page") PageEntity page) {
        ModelAndView modelandView = new ModelAndView(groupUserList);
        try {

            String type = request.getParameter("type");

            user.setGroupId(groupid);
            page.setPageSize(50);
            List<User> userListByGroupId = userService.getUserListByGroupId(user, page);
            // 把参数放到modelAndView中
            modelandView.addObject("userListByGroupId", userListByGroupId);
            modelandView.addObject("page", page);
            modelandView.addObject("groupId", groupid);
            modelandView.addObject("type", type);
            UserGroup userGroup = userGroupService.queryUserGroupById((long) groupid);
            modelandView.addObject("userGroup", userGroup);
        } catch (Exception e) {
            modelandView.addObject("user", user);
            logger.error("AdminUserGroupController.getUserListByGroupId", e);
        }
        return modelandView;
    }

    /**
     * 为学员组添加新学员
     *
     * @param request
     * @param userGroup
     * @param user
     * @param groupid
     * @param page
     * @return
     */
    @RequestMapping("/usergroup/toAddUserForGroup/{groupid}/{notInGroup}")
    public ModelAndView toAddUserForGroup(HttpServletRequest request, @PathVariable Long notInGroup, @ModelAttribute("userGroup") UserGroup userGroup, @ModelAttribute("user") User user, @PathVariable("groupid") int groupid, @ModelAttribute("page") PageEntity page) {
        ModelAndView modelandView = new ModelAndView(toAddUserForGroup);
        try {
            JsonObject sysUser = SingletonLoginUtils.getLoginUser(request);
            // 设置分页，默认每页10
            page.setPageSize(10);
            // 查询学员列表
            user.setSysUserId(sysUser.get("id").getAsLong());
            user.setGroupId(groupid);
            // 小组中是否可以重复添加学员
            user.setNotInGroup(notInGroup);
            List<User> userListByCondition = userService.getUserListByCondition(user, page);
            // 把参数放到modelAndView中
            modelandView.addObject("userListByCondition", userListByCondition);
            modelandView.addObject("page", page);
            modelandView.addObject("user", user);
            modelandView.addObject("groupId", groupid);
            modelandView.addObject("notInGroup", notInGroup);
        } catch (Exception e) {
            logger.error("AdminUserGroupController.toAddUserForGroup", e);
        }
        return modelandView;
    }

    /**
     * 添加组学员
     *
     * @param request
     * @return
     */
    @RequestMapping("/usergroup/addGroupDetail")
    @ResponseBody
    public Map<String, Object> addGroupDetail(HttpServletRequest request) {
        Map<String, Object> json = null;
        try {
            // 获取添加的学员id
            String ids = request.getParameter("ids");
            Long groupId = Long.parseLong(request.getParameter("groupId"));
            if (ObjectUtils.isNotNull(ids)) {
                String[] idsArry = ids.split(",");
                List<UserGroupMiddle> userGroupMiddleDetails = new ArrayList<UserGroupMiddle>();
                for (int i = 0; i < idsArry.length; i++) {
                    UserGroupMiddle userGroupMiddle = new UserGroupMiddle();
                    userGroupMiddle.setGroupId(groupId);
                    userGroupMiddle.setUserId(Long.parseLong(idsArry[i]));
                    userGroupMiddleDetails.add(userGroupMiddle);
                }
                userGroupMiddleService.addUserGroupMiddle(userGroupMiddleDetails);
                json = this.getJsonMap(true, "success", null);
            } else {
                json = this.getJsonMap(false, "false", null);
            }
        } catch (Exception e) {
            logger.error("AdminUserGroupController.addGroupDetail--添加学员出错", e);
            json = this.getJsonMap(false, "error", null);
        }
        return json;
    }


    /**
     * 删除组
     *
     * @return
     */
    @RequestMapping("/usergroup/delquery/{id}")
    @ResponseBody
    public Map<String, Object> delUserGroup(HttpServletRequest request, @PathVariable Long id) {
        Map<String, Object> json = null;
        try {
            userGroupService.delUserGroup(id);
            json = this.getJsonMap(true, "true", null);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            logger.error("AdminUserGroup.delUserGroup------->组删除错误", e);
            json = this.getJsonMap(false, "false", null);
        }
        return json;
    }


    /**
     * 删除学员组中学员
     *
     * @param request
     * @return
     */
    @RequestMapping("/usergroup/deleteUserGroupMiddle")
    @ResponseBody
    public Map<String, Object> deleteUserGroupMiddle(HttpServletRequest request) {
        Map<String, Object> json = null;
        try {
            String userId = request.getParameter("userId");
            String groupId = request.getParameter("groupId");
            UserGroupMiddle userGroupMiddle = new UserGroupMiddle();
            userGroupMiddle.setUserId(Long.parseLong(userId));
            userGroupMiddle.setGroupId(Long.parseLong(groupId));
            userGroupMiddleService.deleteUserGroupMiddle(userGroupMiddle);
            json = this.getJsonMap(true, "success", null);
        } catch (Exception e) {
            logger.error("AdminUserGroupController.deleteUserGroupMiddle--删除学员出错", e);
            json = this.getJsonMap(false, "false", null);
        }
        return json;
    }

    /**
     * 删除学员组中学员
     *
     * @param request
     * @return
     */
    @RequestMapping("/usergroup/batchdeleteUserGroupMiddle")
    @ResponseBody
    public Map<String, Object> batchdeleteUserGroupMiddle(HttpServletRequest request) {
        Map<String, Object> json;
        try {
            String userIds = request.getParameter("userIds");
            String groupId = request.getParameter("groupId");
            if (ObjectUtils.isNotNull(userIds)) {
                String[] idsArry = userIds.split(",");
                for (int i = 0; i < idsArry.length; i++) {
                    UserGroupMiddle userGroupMiddle = new UserGroupMiddle();
                    userGroupMiddle.setGroupId(Long.parseLong(groupId));
                    userGroupMiddle.setUserId(Long.parseLong(idsArry[i]));
                    userGroupMiddleService.deleteUserGroupMiddle(userGroupMiddle);
                }
            }
            json = this.getJsonMap(true, "success", null);
        } catch (Exception e) {
            logger.error("AdminUserGroupController.batchdeleteUserGroupMiddle--删除学员出错", e);
            json = this.getJsonMap(false, "false", null);
        }
        return json;
    }

    /**
     * 我的部门大体信息
     *
     * @param request
     */
    @RequestMapping("/usergroup/webgroup")
    public ModelAndView getwebtaskgrouprecord(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView(grouprecord);
        try {
            Long userid = 565L;
            List<UserGroup> grouplist = userGroupService.getGroupTask(userid);
            modelAndView.addObject("grouplist", grouplist);

        } catch (Exception e) {
            logger.error("AdminUserGroupController.getwebtaskgrouprecord" + e);
            return new ModelAndView(this.setExceptionRequest(request, e));
        }
        return modelAndView;
    }


}

