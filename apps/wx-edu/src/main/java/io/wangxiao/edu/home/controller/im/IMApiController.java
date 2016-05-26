package io.wangxiao.edu.home.controller.im;

import com.google.gson.JsonObject;
import io.wangxiao.commons.service.cache.CacheKit;
import io.wangxiao.commons.util.ObjectUtils;
import io.wangxiao.commons.util.StringUtils;
import io.wangxiao.edu.home.common.EduBaseController;
import io.wangxiao.edu.home.entity.user.UserExpandDto;
import io.wangxiao.edu.home.service.user.UserExpandService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 对外接口 （IM系统专业）
 */
@Controller()
@RequestMapping("/web/im")
public class IMApiController extends EduBaseController {
    private static Logger logger = LoggerFactory.getLogger(IMApiController.class);

    private CacheKit cacheKit = CacheKit.getInstance();
    @Autowired
    private UserExpandService userExpandService;

    /**
     * 获取登录用户接口
     *
     * @param request
     * @return
     */
    @RequestMapping("/getloginuser/{sid}")
    @ResponseBody
    public Map<String, Object> qeuryLoginUser(HttpServletRequest request, @PathVariable("sid") String sid) {
        Map<String, Object> json = null;
        try {
            json = this.getJsonMap(false, null, null);
            if (StringUtils.isNotEmpty(sid)) {
                Object ob = cacheKit.get(sid);
                if (ObjectUtils.isNotNull(ob)) {
                    JsonObject user = jsonParser.parse(ob.toString()).getAsJsonObject();
                    json = this.getJsonMap(true, null, user);
                }
            }
        } catch (Exception e) {
            logger.error("qeuryLoginUser()--error", e);
        }
        return json;
    }

    /**
     * 获取用户，根据用户ID
     *
     * @param requet
     * @param userId
     * @return
     */
    @RequestMapping("/getuserById/{userId}")
    @ResponseBody
    public Map<String, Object> getUserById(HttpServletRequest requet, @PathVariable("userId") long userId) {
        Map<String, Object> json = null;
        try {
            UserExpandDto user = userExpandService.getUserExpandByUid(userId);
            json = this.getJsonMap(true, null, user);
        } catch (Exception e) {
            json = this.getJsonMap(false, null, null);
            logger.error("getUserById()--error", e);
        }
        return json;
    }
}
