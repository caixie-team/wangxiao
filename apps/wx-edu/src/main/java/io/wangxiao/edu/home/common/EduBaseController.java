package io.wangxiao.edu.home.common;

import io.wangxiao.commons.controller.BaseController;
import io.wangxiao.commons.util.StringUtils;
import io.wangxiao.commons.util.web.WebUtils;
import io.wangxiao.edu.common.constants.CommonConstants;
import io.wangxiao.edu.common.contoller.SingletonLoginUtils;
import io.wangxiao.edu.common.util.DateEditor;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

public class EduBaseController extends BaseController {
    protected static final String EDU_VIEW_PATH = "edu";// edu的view路径
    protected static final String msgjsp = ("/common/frontmsg");

    /**
     * 返回edu的view路径
     */
    public static String getViewPath(String path) {
        if (StringUtils.isNotEmpty(path)) {
            return "/" + EDU_VIEW_PATH + path;
        }
        return "";
    }

    /**
     * 获取登陆用户的id(前台用)
     *
     * @return int
     * @throws Exception
     */
    protected static Long getLoginUserId(HttpServletRequest request) throws Exception {
        return SingletonLoginUtils.getLoginUserId(request);
    }

    /**
     * 是否登录
     *
     * @param request
     * @return
     */
    public boolean isLogin(HttpServletRequest request) {
        return SingletonLoginUtils.isLogin(request);
    }

    /**
     * spring接受date类型转换
     */
    @InitBinder
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
        // 对于需要转换为Date类型的属性，使用DateEditor进行处理
        binder.registerCustomEditor(Date.class, new DateEditor());
    }

    public String getSysLoginLoginName(HttpServletRequest request) {
        return SingletonLoginUtils.getLoginUserName(request);
    }

    protected Long getLoginSubjectId(HttpServletRequest request) {
        try {
            String ukey = WebUtils.getCookie(request, CommonConstants.COOKIE_SUBJECTID_KEY);
            if (ukey == null || ukey.trim().equals("")) {
                return 0L;
            } else {
                return Long.valueOf(ukey);
            }
        } catch (Exception e) {
        }
        return 0L;
    }

    protected Long getUpLoginId(HttpServletRequest request) {
        try {
            String ukey = WebUtils.getCookie(request, CommonConstants.UP_USER_ID);
            if (ukey == null || ukey.trim().equals("")) {
                return 0L;
            } else {
                return Long.valueOf(ukey);
            }
        } catch (Exception e) {
        }
        return 0L;
    }
}
