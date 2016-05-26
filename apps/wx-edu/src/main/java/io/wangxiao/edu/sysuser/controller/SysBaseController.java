package io.wangxiao.edu.sysuser.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import io.wangxiao.commons.controller.BaseController;
import io.wangxiao.commons.service.cache.CacheKit;
import io.wangxiao.commons.util.StringUtils;
import io.wangxiao.edu.common.contoller.SingletonLoginUtils;
import io.wangxiao.edu.home.entity.user.User;

import javax.servlet.http.HttpServletRequest;

/**
 * @description 通用的action.所有的Controller继承，公用的写到此方法中
 */
public class SysBaseController extends BaseController {

    protected static final String SYSUSER_VIEW_PATH = "sysuser";// sysuser的view路径
    CacheKit cacheKit = CacheKit.getInstance();

    /**
     * 获得系统登录用户
     *
     * @param request
     * @return
     */
    public User getSysLoginedUser(HttpServletRequest request) {
        JsonObject jsonObject = SingletonLoginUtils.getLoginUser(request);
        Gson gson = new Gson();
        User user = gson.fromJson(gson.toJson(jsonObject), User.class);

        return user;
    }

    /**
     * 返回sns的view路径
     */
    public static String getViewPath(String path) {
        if (StringUtils.isNotEmpty(path)) {
            return "/" + SYSUSER_VIEW_PATH + path;
        }
        return "";
    }

}
