package io.wangxiao.edu.home.controller.user;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.commons.util.StringUtils;
import io.wangxiao.edu.common.contoller.SingletonLoginUtils;
import io.wangxiao.edu.home.common.EduBaseController;
import io.wangxiao.edu.home.constants.enums.UserOptType;
import io.wangxiao.edu.home.entity.user.User;
import io.wangxiao.edu.home.entity.user.UserAccount;
import io.wangxiao.edu.home.entity.user.UserAccountDTO;
import io.wangxiao.edu.home.service.user.UserAccountService;
import io.wangxiao.edu.home.service.user.UserService;
import io.wangxiao.edu.sysuser.entity.SysUserOptRecord;
import io.wangxiao.edu.sysuser.service.SysUserOptRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class UserAccountController extends EduBaseController {

    private static final Logger logger = LoggerFactory.getLogger(UserAccountController.class);

    @Autowired
    private UserAccountService userAccountService;
    @Autowired
    private UserService userService;
    @Autowired
    private SysUserOptRecordService sysUserOptRecordService;

    private static final String account_list = getViewPath("/admin/accout/admin_account_list"); // 账户订单页面
    private static final String account_recharge = getViewPath("/admin/accout/admin_account_recharge");// 账户充值页面

    // 绑定变量名字和属性，参数封装进类
    @InitBinder("user")
    public void initBinderUserAccounthistory(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("user.");
    }

    /**
     * 获取用户账户信息
     *
     * @param request
     * @param page
     * @param user
     * @return
     */
    @RequestMapping("/account/list")
    public ModelAndView accountList(HttpServletRequest request, @ModelAttribute("page") PageEntity page, @ModelAttribute("user") User user) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(account_list);
        try {
            // 设置分页 ，默认每页10

            List<UserAccountDTO> userAccountList = userAccountService.getUserAccountListByCondition(page, user);
            modelAndView.addObject("userAccountList", userAccountList);
            modelAndView.addObject("page", page);
        } catch (Exception e) {
            e.printStackTrace();
            setExceptionRequest(request, e);
        }
        return modelAndView;
    }

    /**
     * 更新账户状态
     *
     * @param request
     * @param userId
     * @param status
     * @return
     */
    @RequestMapping("/account/update/{userId}")
    @ResponseBody
    public Map<String, Object> updateAccountStatus(HttpServletRequest request, @PathVariable("userId") Long userId, @RequestParam("status") String status) {
        Map<String, Object> json = null;
        try {
            if (StringUtils.isNotEmpty(status)) {
                UserAccount beforeRecord = userAccountService.getUserAccountByUserId(userId);
                userAccountService.updateUserAccountStatus(userId, status);
                UserAccount afterRecord = userAccountService.getUserAccountByUserId(userId);
                SysUserOptRecord record = new SysUserOptRecord(request, (status.indexOf(UserOptType.FROZEN.toString()) != -1 ? "冻结" : "解冻") + "用户账户", "用户账户表-" + beforeRecord.getId(), beforeRecord, afterRecord);
                if (record != null) {
                    sysUserOptRecordService.addRecord(record);
                }
                json = this.getJsonMap(true, "修改成功", null);

                // 查询账户
                UserAccount userAccount = userAccountService.getUserAccountByUserId(userId);
                // 记录系统用户操作
                Map<String, Object> descMap = new HashMap<String, Object>();
                descMap.put("optuser", "操作id_" + SingletonLoginUtils.getLoginUserId(request));
                descMap.put("optType", status.indexOf(UserOptType.FROZEN.toString()) != -1 ? "冻结" : "解冻");
                descMap.put("accountId", "账户id_" + userAccount != null ? userAccount.getId() : 0);
                descMap.put("userId", "用户id_" + userId);
                userService.addUserOptRecord(userId, UserOptType.GIVECOURSE.toString(), SingletonLoginUtils.getLoginUserId(request), this.getSysLoginLoginName(request), userAccount != null ? userAccount.getId() : 0L, gson.toJson(descMap));
            } else {
                json = this.getJsonMap(false, "请求数据错误", null);
            }
        } catch (Exception e) {
            logger.error("updateAccountStatus", e);
            json = this.getJsonMap(false, "系统错误", null);
        }
        return json;
    }

    /**
     * 账户详情
     *
     * @param request
     * @param userId
     * @return
     */
    @RequestMapping("/account/info/{userId}/{flag}")
    public String getUserAccountByUserId(HttpServletRequest request, Model model, @PathVariable("userId") Long userId, @PathVariable("flag") String flag) {
        try {
            // 获得用户账户详情
            UserAccountDTO userAccountDTO = userAccountService.getuserAccountInfo(userId);
            model.addAttribute("userAccountDTO", userAccountDTO);
            model.addAttribute("flag", flag);
        } catch (Exception e) {
            logger.error("getUserAccountByUserId", e);
            return setExceptionRequest(request, e);
        }
        return account_recharge;
    }

    /**
     * 后台账户充值,扣费
     *
     * @param request
     * @return
     */
    @RequestMapping("/account/recharge")
    @ResponseBody
    public Map<String, Object> gainUserRechargeAmount(HttpServletRequest request) {
        Map<String, Object> json = null;
        try {
            String userId = request.getParameter("userId");// 获得用户id
            String flag = request.getParameter("flag");// 获得用户id
            String balance = request.getParameter("balance");// 获得用户id
            if (StringUtils.isEmpty(userId) || StringUtils.isEmpty(flag) || StringUtils.isEmpty(balance)) {
                json = this.getJsonMap(true, "请求数据错误", null);
                return json;
            }
            // 后台账户充值,扣费
            User user = new User();
            user.setId(SingletonLoginUtils.getLoginUserId(request));
            user.setNickname(getSysLoginLoginName(request));
            UserAccount beforeRecord = userAccountService.getUserAccountByUserId(Long.valueOf(userId));
            boolean result = userAccountService.gainUserRechargeAmount(user, Long.valueOf(userId), new BigDecimal(balance), flag);
            UserAccount afterRecord = userAccountService.getUserAccountByUserId(Long.valueOf(userId));
            if (result) {
                String status = "";
                if ("credit".equals(flag)) {
                    status = "充值";
                } else if ("debit".equals(flag)) {
                    status = "扣款";
                }
                SysUserOptRecord record = new SysUserOptRecord(request, "用户账户" + status, "用户账户表-", beforeRecord, afterRecord);
                if (record != null)
                    sysUserOptRecordService.addRecord(record);
                json = this.getJsonMap(true, "操作成功", null);
            } else {
                json = this.getJsonMap(false, "操作失败", null);
            }
        } catch (Exception e) {
            logger.error("gainUserRechargeAmount--recharge is failure", e);
            json = this.getJsonMap(false, "recharge is failure", null);
        }
        return json;
    }
}