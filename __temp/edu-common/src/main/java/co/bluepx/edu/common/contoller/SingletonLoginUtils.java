package co.bluepx.edu.common.contoller;

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
import com.atdld.os.sysuser.constants.SysUserConstants;

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

    /**
     * 后台登陆用户（后台）
     *
     * @return SysUser
     */
    public static JsonObject getSysUser(HttpServletRequest request)  {
            String sid = WebUtils.getCookie(request, SysUserConstants.sidadmin);
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
    public static void setLoginInfo(String sid,UserExpandDto userExpandDto,String autoThirty){
        JsonParser jsonParser = new JsonParser();
        JsonObject jsonObject  = jsonParser.parse(new Gson().toJson(userExpandDto)).getAsJsonObject();
        if(autoThirty!=null&&autoThirty.equals("true")){//30天自动登录
        	memCache.set(sid, jsonObject.toString(), MemConstans.USER_AUTO_TIME);
        }else{
        	memCache.set(sid, jsonObject.toString(), MemConstans.USER_TIME);
        }
    }

  //判断是否为手机浏览器
    public static boolean JudgeIsMoblie(HttpServletRequest request) {
        boolean isMoblie = false;
        String[] mobileAgents = { "iphone", "android","ipad", "phone", "mobile", "wap", "netfront", "java", "opera mobi",
                "opera mini", "ucweb", "windows ce", "symbian", "series", "webos", "sony", "blackberry", "dopod",
                "nokia", "samsung", "palmsource", "xda", "pieplus", "meizu", "midp", "cldc", "motorola", "foma",
                "docomo", "up.browser", "up.link", "blazer", "helio", "hosin", "huawei", "novarra", "coolpad", "webos",
                "techfaith", "palmsource", "alcatel", "amoi", "ktouch", "nexian", "ericsson", "philips", "sagem",
                "wellcom", "bunjalloo", "maui", "smartphone", "iemobile", "spice", "bird", "zte-", "longcos",
                "pantech", "gionee", "portalmmm", "jig browser", "hiptop", "benq", "haier", "^lct", "320x320",
                "240x320", "176x220", "w3c ", "acs-", "alav", "alca", "amoi", "audi", "avan", "benq", "bird", "blac",
                "blaz", "brew", "cell", "cldc", "cmd-", "dang", "doco", "eric", "hipt", "inno", "ipaq", "java", "jigs",
                "kddi", "keji", "leno", "lg-c", "lg-d", "lg-g", "lge-", "maui", "maxo", "midp", "mits", "mmef", "mobi",
                "mot-", "moto", "mwbp", "nec-", "newt", "noki", "oper", "palm", "pana", "pant", "phil", "play", "port",
                "prox", "qwap", "sage", "sams", "sany", "sch-", "sec-", "send", "seri", "sgh-", "shar", "sie-", "siem",
                "smal", "smar", "sony", "sph-", "symb", "t-mo", "teli", "tim-", "tosh", "tsm-", "upg1", "upsi", "vk-v",
                "voda", "wap-", "wapa", "wapi", "wapp", "wapr", "webc", "winw", "winw", "xda", "xda-",
                "Googlebot-Mobile" };
        if (request.getHeader("User-Agent") != null) {
            System.out.println("User-Agent:"+request.getHeader("User-Agent"));
            String agent=request.getHeader("User-Agent");
            for (String mobileAgent : mobileAgents) {
                if (agent.toLowerCase().indexOf(mobileAgent) >= 0&&agent.toLowerCase().indexOf("windows nt")<=0 &&agent.toLowerCase().indexOf("macintosh")<=0) {
                    isMoblie = true;
                    break;
                }
            }
        }
        return isMoblie;
    }
}
