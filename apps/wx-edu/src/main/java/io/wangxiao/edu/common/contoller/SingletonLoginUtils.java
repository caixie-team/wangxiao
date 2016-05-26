package io.wangxiao.edu.common.contoller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.wangxiao.commons.service.cache.CacheKit;
import io.wangxiao.commons.util.ObjectUtils;
import io.wangxiao.commons.util.StringUtils;
import io.wangxiao.commons.util.web.WebUtils;
import io.wangxiao.edu.common.constants.CommonConstants;
import io.wangxiao.edu.common.constants.MemConstans;
import io.wangxiao.edu.common.util.EhcacheKit;
import io.wangxiao.edu.home.entity.user.UserExpandDto;

import javax.servlet.http.HttpServletRequest;

public class SingletonLoginUtils {

    static CacheKit cacheKit = CacheKit.getInstance();


    public static Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();
    public static JsonParser jsonParser = new JsonParser();

    static EhcacheKit ehcache = EhcacheKit.getInstance();

    /**
     * 获取登陆用户的id(前台用)
     *
     * @return int
     * @throws Exception
     */
    public static Long getLoginUserId(HttpServletRequest request) {
        JsonObject useObject = getLoginUser(request);
        if (ObjectUtils.isNotNull(useObject)) {
            if (StringUtils.isNotEmpty(useObject.get("id").toString())) {
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
     *
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
    public static JsonObject getLoginUser(HttpServletRequest request) {
        String sid = WebUtils.getCookie(request, CommonConstants.USER_SINGEL_ID);

        //
        Object ob;
        if (StringUtils.isNotEmpty(sid)) {
            ob = ehcache(sid);
            if (ObjectUtils.isNotNull(ob)) {
                JsonObject user = jsonParser.parse(ob.toString()).getAsJsonObject();
                return user;
            }
        }

        return null;
    }

    public static Object ehcache(String sid) {
        Object ob = null;
        ob = ehcache.get(sid);
        if (ObjectUtils.isNull(ob)) {
            ob = cacheKit.get(sid);
            if (ObjectUtils.isNotNull(ob)) {
                ehcache.put(sid, ob);
            }
        }
        return ob;
    }

    public static String getLoginUserName(HttpServletRequest request) {
        JsonObject user = SingletonLoginUtils.getLoginUser(request);
        if (ObjectUtils.isNotNull(user)) {
            return user.get("nickname").getAsString();
        }
        return "";
    }

    public static void setLoginInfo(String sid, UserExpandDto userExpandDto, String autoThirty) {
        JsonParser jsonParser = new JsonParser();
        JsonObject jsonObject = jsonParser.parse(new Gson().toJson(userExpandDto)).getAsJsonObject();
        if (autoThirty != null && autoThirty.equals("true")) {//7天自动登录
            cacheKit.set(sid, jsonObject.toString(), MemConstans.USER_AUTO_TIME);
        } else {
            cacheKit.set(sid, jsonObject.toString(), MemConstans.USER_TIME);
        }
    }

    //判断是否为手机浏览器
    public static boolean JudgeIsMoblie(HttpServletRequest request) {
        boolean isMoblie = false;
        String[] mobileAgents = {"iphone", "android", "ipad", "phone", "mobile", "wap", "netfront", "java", "opera mobi",
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
                "Googlebot-Mobile"};
        if (request.getHeader("User-Agent") != null) {
            String agent = request.getHeader("User-Agent");
            for (String mobileAgent : mobileAgents) {
                if (agent.toLowerCase().contains(mobileAgent) && agent.toLowerCase().indexOf("windows nt") <= 0 && agent.toLowerCase().indexOf("macintosh") <= 0) {
                    isMoblie = true;
                    break;
                }
            }
        }
        return isMoblie;
    }

    /**
     * 更新积分缓存
     *
     * @param request
     * @param score
     */
    public static void updateScore(HttpServletRequest request, Long score) {
        String sid = WebUtils.getCookie(request, CommonConstants.USER_SINGEL_ID);
        JsonObject loginUser = SingletonLoginUtils.getLoginUser(request);
        assert loginUser != null;
        loginUser.addProperty("score", score);
        cacheKit.set(sid, loginUser.toString());
        ehcache.put(sid, cacheKit.get(sid));
    }
}
