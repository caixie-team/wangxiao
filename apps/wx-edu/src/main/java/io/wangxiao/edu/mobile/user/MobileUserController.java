package io.wangxiao.edu.mobile.user;

import com.google.gson.JsonObject;
import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.commons.service.cache.CacheKit;
import io.wangxiao.commons.util.Security.PurseSecurityUtils;
import io.wangxiao.commons.util.web.WebUtils;
import io.wangxiao.edu.common.constants.CommonConstants;
import io.wangxiao.edu.common.constants.MemConstans;
import io.wangxiao.edu.common.contoller.SingletonLoginUtils;
import io.wangxiao.edu.home.common.MobileBaseController;
import io.wangxiao.edu.home.entity.user.QueryUserAccounthistory;
import io.wangxiao.edu.home.entity.user.UserAccount;
import io.wangxiao.edu.home.entity.user.UserAccounthistory;
import io.wangxiao.edu.home.entity.user.UserExpandDto;
import io.wangxiao.edu.home.service.user.UserAccountService;
import io.wangxiao.edu.home.service.user.UserAccounthistoryService;
import io.wangxiao.edu.home.service.user.UserExpandService;
import io.wangxiao.edu.home.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
public class MobileUserController extends MobileBaseController {

    private Logger logger = LoggerFactory.getLogger(MobileUserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private UserAccountService userAccountService;
    @Autowired
    private UserAccounthistoryService userAccounthistoryService;

    @Autowired
    private UserExpandService userExpandService;

    CacheKit cacheKit = CacheKit.getInstance();

    private static String loginpage = getViewPath("/user/login");
    private static String registerpage = getViewPath("/user/register");
    private static String ucenter = getViewPath("/ucenter/u-center");
    private static String userInfo = getViewPath("/ucenter/u-my-account");
    private static String toUcPwd = getViewPath("/ucenter/u-pwd-update");
    private static String accoutList = getViewPath("/ucenter/u-acc-number");
    private static String accoutListAjax = getViewPath("/ucenter/u-acc-number-ajax");

    @InitBinder("queryUser")
    public void initBinderqueryUser(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("queryUser.");
    }

    @InitBinder("userForm")
    public void initBinderUserForm(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("userForm.");
    }

    /**
     * 登录页
     *
     * @param request
     * @return
     */
    @RequestMapping("/mobile/login")
    public String loginpage(HttpServletRequest request) {
        return loginpage;
    }

    /**
     * 注册页
     *
     * @param request
     * @return
     */
    @RequestMapping("/mobile/register")
    public String registerpage(HttpServletRequest request) {
        return registerpage;
    }

    /**
     * 个人中心
     *
     * @param request
     * @return
     */
    @RequestMapping("/mobile/uc/home")
    public String ucenter(HttpServletRequest request) {
        try {
            UserExpandDto userExpandDto = userExpandService.getUserExpandByUids(getLoginUserId(request));
            request.setAttribute("user", userExpandDto);
        } catch (Exception e) {
            logger.error("UserController.ucenter", e);
            return setExceptionRequest(request, e);
        }
        return ucenter;
    }

    /**
     * 基本信息
     */
    @RequestMapping("/mobile/uc/uinfo")
    public String userInfo(HttpServletRequest request) {
        try {
            UserExpandDto userExpandDto = userExpandService.getUserExpandByUids(getLoginUserId(request));
            request.setAttribute("queryUser", userExpandDto);
        } catch (Exception e) {
            logger.error("UserController.updatepwd", e);
            return setExceptionRequest(request, e);
        }
        return userInfo;
    }

    /**
     * 修改基本信息
     */
    @RequestMapping("/mobile/uc/user/update")
    @ResponseBody
    public Object userUpdateInfo(HttpServletRequest request, @ModelAttribute("queryUser") UserExpandDto queryUser) {
        Map<String, Object> json = null;
        try {
            // 修改用户信息
            queryUser.setId(getLoginUserId(request));
            String falg = userService.updateQueryUser(queryUser);
            if ("success".equals(falg)) {
                JsonObject userJsonObject = SingletonLoginUtils.getLoginUser(request);
                userJsonObject.addProperty("nickname", queryUser.getNickname());
                userJsonObject.addProperty("showname", queryUser.getNickname());
                String sid = WebUtils.getCookie(request, CommonConstants.USER_SINGEL_ID);
                cacheKit.set(sid, userJsonObject.toString(), MemConstans.USER_TIME);
            }

            json = this.getJsonMap(true, "", falg);
        } catch (Exception e) {
            logger.error("UserController.updatepwd", e);
            json = this.getJsonMap(false, "", "");
        }
        return json;
    }

    /**
     * 跳转修改密码
     */
    @RequestMapping("/mobile/uc/dopwd")
    public String doUserPwd() {
        return toUcPwd;
    }

    public boolean checkIsRight(String dbPassword, String userPassword, String userkey) {
        String despassword = PurseSecurityUtils.secrect(userPassword, userkey);
        return despassword.equals(dbPassword);
    }

    /**
     * 个人账户信息
     *
     * @param request
     * @param page
     * @return
     */
    @RequestMapping("/mobile/uc/acc")
    public String perssonAccout(HttpServletRequest request, @ModelAttribute("page") PageEntity page) {
        QueryUserAccounthistory queryUserAccounthistory = new QueryUserAccounthistory();
        try {

            page.setPageSize(6);
            queryUserAccounthistory.setUserId(getLoginUserId(request));
            UserAccount userAccount = userAccountService.getUserAccountByUserId(getLoginUserId(request));
            List<UserAccounthistory> accList = userAccounthistoryService
                    .getWebUserAccountHistroyListByCondition(queryUserAccounthistory, page);
            request.setAttribute("userAccount", userAccount);
            request.setAttribute("accList", accList);
            request.setAttribute("page", page);
        } catch (Exception e) {
            e.printStackTrace();
            return this.setExceptionRequest(request, e);
        }
        return accoutList;
    }

    /**
     * 个人账户信息
     *
     * @param request
     * @param page
     * @return
     */
    @RequestMapping("/mobile/uc/ajax/acc")
    public String perssonAccoutAjax(HttpServletRequest request, @ModelAttribute("page") PageEntity page) {
        QueryUserAccounthistory queryUserAccounthistory = new QueryUserAccounthistory();
        try {

            page.setPageSize(6);
            queryUserAccounthistory.setUserId(getLoginUserId(request));
            UserAccount userAccount = userAccountService.getUserAccountByUserId(getLoginUserId(request));
            List<UserAccounthistory> accList = userAccounthistoryService
                    .getWebUserAccountHistroyListByCondition(queryUserAccounthistory, page);
            request.setAttribute("userAccount", userAccount);
            request.setAttribute("accList", accList);
            request.setAttribute("page", page);
        } catch (Exception e) {
            e.printStackTrace();
            return this.setExceptionRequest(request, e);
        }
        return accoutListAjax;
    }

}
