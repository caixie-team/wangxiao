package io.wangxiao.edu.home.common;

import io.wangxiao.commons.controller.BaseController;
import io.wangxiao.commons.util.ObjectUtils;
import io.wangxiao.commons.util.StringUtils;
import io.wangxiao.edu.home.entity.user.User;
import io.wangxiao.edu.home.entity.user.UserGroup;
import io.wangxiao.edu.home.service.user.UserGroupService;
import io.wangxiao.edu.home.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Description:验证字段是否唯一
 */
@Controller
public class AjaxCheckExistsController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(AjaxCheckExistsController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private UserGroupService userGroupService;

    /**
     * 验证用户名是否存在
     *
     * @param nickName 用户名
     * @return
     */
    @RequestMapping("/existsNickName")
    public String existsNickName(@RequestParam("nickName") String nickName, HttpServletRequest request, HttpServletResponse response) {
        try {
            if (StringUtils.isNotEmpty(nickName)) {
                User user = userService.getUserByNickName(nickName);
                if (ObjectUtils.isNotNull(user)) {
                    this.sendMessage(request, response, "true");
                } else {
                    this.sendMessage(request, response, "false");
                }
            }
        } catch (Exception e) {
            logger.error("AjaxCheckExistsController.existsNickName", e);
        }
        return null;
    }

    /**
     * 验证手机号是否存在
     *
     * @param mobile 手机号
     * @return
     */
    @RequestMapping("/existsMobile")
    public String existsMobile(@RequestParam("mobile") String mobile, HttpServletRequest request, HttpServletResponse response) {
        try {
            if (StringUtils.isNotEmpty(mobile)) {
                User user = userService.getUserByLoginMobile(mobile);
                if (ObjectUtils.isNotNull(user)) {
                    this.sendMessage(request, response, "true");
                } else {
                    this.sendMessage(request, response, "false");
                }
            }
        } catch (Exception e) {
            logger.error("AjaxCheckExistsController.existsMobile", e);
        }
        return null;
    }

    /**
     * 验证邮箱是否存在
     *
     * @param email 邮箱
     * @return
     */
    @RequestMapping("/existsEmail")
    public String existsEmail(@RequestParam("email") String email, HttpServletRequest request, HttpServletResponse response) {
        try {
            if (StringUtils.isNotEmpty(email)) {
                User user = userService.getUserByLoginEmail(email);
                if (ObjectUtils.isNotNull(user)) {
                    this.sendMessage(request, response, "true");
                } else {
                    this.sendMessage(request, response, "false");
                }
            }
        } catch (Exception e) {
            logger.error("AjaxCheckExistsController.existsEmail", e);
        }
        return null;
    }

    /**
     * 验证岗位名称是否存在
     *
     * @param groupName 岗位名称
     * @return
     */
    @RequestMapping("/admin/existsGroupName")
    public String existsGroupName(@RequestParam("groupName") String groupName, HttpServletRequest request, HttpServletResponse response) {
        try {
            if (StringUtils.isNotEmpty(groupName)) {
                UserGroup userGroup = new UserGroup();
                userGroup.setName(groupName);
                Boolean ok = userGroupService.checkUserGroup(userGroup);
                if (!ok) {
                    this.sendMessage(request, response, "false");
                } else {
                    this.sendMessage(request, response, "true");
                }
            }
        } catch (Exception e) {
            logger.error("AjaxCheckExistsController.existsEmail", e);
        }
        return null;
    }


}
