package com.atdld.os.sns.controller.common;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.google.gson.JsonObject;
import com.atdld.os.core.util.ObjectUtils;
import com.atdld.os.core.util.web.WebUtils;

/**
 * @author :
 * @ClassName com.atdld.os.sns.controller.common.LogAction
 * @description 日志统一打印
 * @Create Date : 2014-1-20 上午10:32:21
 */
public class LogController extends SnsBaseController {

    private static final Log logger = LogFactory.getLog(LogController.class);

    public static String ACTION = "action";// 操作动作事件
    public static String UID = "uid";// 用户id
    public static String IP = "ip";// 操作ip

    /**
     * 打印log
     *
     * @param map
     */
    public static void printLog(Map<String, Object> map) {
        try {
            String logJson = mapToJson(map);
            logger.info("sns_opt_log:" + logJson);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("exception,Map to JSON,map=" + map);
        }
    }

    /**
     * 获得log的map添加当前用户的uid和ip
     *
     * @param request
     * @return
     */
    public static Map<String, Object> getlogMap(HttpServletRequest request) {
        // 打印日志
        Map<String, Object> logMap = new HashMap<String, Object>();
        // 访问者IP
        String ip = WebUtils.getIpAddr(request);
        try {
            Long uid = getLoginUserId(request);
            logMap.put(IP, ip);
            logMap.put(UID, "" + uid);
        } catch (Exception e) {
        }
        return logMap;
    }

    /**
     * map专为json格式打印
     *
     * @param obj
     * @return
     * @throws JSONException
     */
    public static String mapToJson(Map<String, Object> obj) throws Exception {
        if (ObjectUtils.isNull(obj))
            return "";
        Set<String> set = obj.keySet();
        JsonObject jo = new JsonObject();
        for (String string : set) {
            jo.addProperty(string, obj.get(string).toString());
        }
        return jo.toString();
    }

}
