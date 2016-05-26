package com.atdld.os.edu.common;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.JsonObject;
import com.atdld.os.core.util.ObjectUtils;

import org.apache.log4j.Logger;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.atdld.os.common.constants.CommonConstants;
import com.atdld.os.common.contoller.SingletonLoginUtils;
import com.atdld.os.common.util.DateEditor;
import com.atdld.os.core.controller.BaseController;
import com.atdld.os.core.service.cache.MemCache;
import com.atdld.os.core.util.StringUtils;
import com.atdld.os.core.util.web.WebUtils;

/**
 * @ClassName com.atdld.os.edu.controller.common.EduBaseController
 * @description
 * @author :
 * @Create Date : 2014-5-27 下午2:11:59
 */
public class EduBaseController extends BaseController {
    protected static final String EDU_VIEW_PATH = "edu";// edu的view路径
    static MemCache memCache = MemCache.getInstance();
    protected static final String msgjsp = ("/common/frontmsg");
   // log对象
    private static final Logger logger = Logger.getLogger(EduBaseController.class);
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
     * @param request
     * @return
     */
    public boolean isLogin(HttpServletRequest request) {
        return SingletonLoginUtils.isLogin(request);
    }

    /** spring接受date类型转换
     */
    @InitBinder
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
		// 对于需要转换为Date类型的属性，使用DateEditor进行处理
		binder.registerCustomEditor(Date.class, new DateEditor());
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
            logger.error("SubjectAction.getLoginSubjectId", e);
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
            logger.error("EduBaseController.getUpLoginId", e);
        }
        return 0L;
    }
}
