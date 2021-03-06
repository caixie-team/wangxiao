package com.atdld.open.cms.common.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.atdld.open.common.constants.CommonConstants;
import com.atdld.open.core.service.cache.MemCache;
import com.atdld.open.core.util.ObjectUtils;
import com.atdld.open.core.util.StringUtils;
import com.atdld.open.core.util.web.WebUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName  com.atdld.open.user.common.SingletonLoginUtils
 * @description
 */
public class SingletonLoginUtils {
    
    static MemCache memCache = MemCache.getInstance();

    public static Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();
    public static JsonParser jsonParser = new JsonParser();

    /**
     * 获取登陆用户的id(前台用)
     * 
     * @return int
     * @throws Exception
     */
    public static Long getLoginUserId(HttpServletRequest request)  {
        JsonObject useObject= getLoginUser(request);
        if (ObjectUtils.isNotNull(useObject)) {
            if ( StringUtils.isNotEmpty(useObject.get("id").toString())) {
                return Long.valueOf(useObject.get("id").toString());
            } else {
                return 0L;
            }
        } else {
            return 0L;
        }
    }
    
    /**
     * 是否登录
     * @param request
     * @return
     */
    public static boolean isLogin(HttpServletRequest request) {
        try {
            if (getLoginUserId(request).intValue() > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * 获取登陆用户
     * 
     * @return User
     * @throws Exception
     */
    public static JsonObject getLoginUser(HttpServletRequest request)  {
        String sid = WebUtils.getCookie(request, CommonConstants.USER_SINGEL_ID);
        if (StringUtils.isNotEmpty(sid)) {
            Object  ob =   memCache.get(sid);
            if(ObjectUtils.isNotNull(ob)){
                JsonObject user=  jsonParser.parse(ob.toString()).getAsJsonObject();
                return user;
            }
        }
        return null;
    }

    /**
     * 后台登陆用户（后台）
     *
     * @return SysUser
     */
    public static JsonObject getSysUser(HttpServletRequest request)  {
            String sid = WebUtils.getCookie(request, CommonConstants.sidadmin);
            if (StringUtils.isNotEmpty(sid)) {
                Object  ob =   memCache.get(sid);
                if(ObjectUtils.isNotNull(ob)){
                    JsonObject user=  jsonParser.parse(ob.toString()).getAsJsonObject();
                    return user;
                }
            }
            return null;
    }
    /**
     * 后台登陆用户（后台）
     *
     * @return SysUser
     */
    public static Long getSysUserId(HttpServletRequest request)  {
        JsonObject jsonObject = getSysUser(request);
        if(ObjectUtils.isNotNull(jsonObject)){
            return jsonObject.get("userId").getAsLong();
        }
        return 0L;
    }
    public static String getSysLoginLoginName (HttpServletRequest request){
        JsonObject syuser =SingletonLoginUtils.getSysUser(request);
        if(ObjectUtils.isNotNull(syuser)){
            return syuser.get("loginName").getAsString();
        }
        return "";
    }

 

}
