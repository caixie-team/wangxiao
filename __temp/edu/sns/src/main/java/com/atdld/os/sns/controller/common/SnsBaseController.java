package com.atdld.os.sns.controller.common;

import javax.servlet.http.HttpServletRequest;

import com.atdld.os.common.contoller.SingletonLoginUtils;
import com.atdld.os.core.controller.BaseController;
import com.atdld.os.core.util.StringUtils;

/**
 * @author :
 * @ClassName com.atdld.os.sns.controller.common.CommonAction
 * @description 通用的action.所有的Controller继承，公用的写到此方法中
 * @Create Date : 2013-12-13 下午2:30:00
 */
public class SnsBaseController extends BaseController {

    protected static final String ERROR_404 = "/error/404";// 404错误
    protected static final String NOEXSIT = "/common/inexistence";// 提示不存在信息
    protected static final String ADMIN_ERROR = "/admin/error/error";// 后台错误信息

    protected static final String SNS_VIEW_PATH = "sns";// sns的view路径

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
     * 提示不存在
     */
    public String setExceptionRequestExsit(HttpServletRequest request, String messages) {
        if (StringUtils.isNotEmpty(messages)) {
            request.setAttribute("messages", messages);
        }
        return NOEXSIT;
    }

    /**
     * 返回sns的view路径
     */
    public static String getViewPath(String path) {
        if (StringUtils.isNotEmpty(path)) {
            return "/" + SNS_VIEW_PATH + path;
        }
        return "";
    }
    /**
     * 是否登录
     * @param request
     * @return
     */
    public boolean isLogin(HttpServletRequest request) {
        return SingletonLoginUtils.isLogin(request);
    }
//    /**
//     * 获得系统登录用户
//     *
//     * @param request
//     * @return
//     */
//    public SysUser getSysLoginedUser(HttpServletRequest request) {
//        return (SysUser) SingletonLoginUtils.getSysUser(request);
//    }
}
