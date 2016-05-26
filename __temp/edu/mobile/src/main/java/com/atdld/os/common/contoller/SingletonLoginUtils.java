package com.atdld.os.common.contoller;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.atdld.os.common.constants.CommonConstants;
import com.atdld.os.common.constants.MemConstans;
import com.atdld.os.core.service.cache.MemCache;
import com.atdld.os.core.util.ObjectUtils;
import com.atdld.os.core.util.StringUtils;
import com.atdld.os.core.util.web.WebUtils;
import com.atdld.os.edu.entity.user.UserExpandDto;

/**
 * @ClassName  com.atdld.os.user.common.SingletonLoginUtils
 * @description
 * @author :
 * @Create Date : 2014-6-26 上午9:58:29
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


   
    public static void setLoginInfo(String sid,UserExpandDto userExpandDto){
        JsonParser jsonParser = new JsonParser();
        JsonObject jsonObject  = jsonParser.parse(new Gson().toJson(userExpandDto)).getAsJsonObject();
        memCache.set(sid, jsonObject.toString(), MemConstans.USER_TIME);
    }

}
