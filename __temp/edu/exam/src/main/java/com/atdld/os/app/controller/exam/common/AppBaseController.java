package com.atdld.os.app.controller.exam.common;

import com.atdld.os.common.util.DateEditor;
import com.atdld.os.core.controller.BaseController;
import com.atdld.os.core.service.cache.MemCache;
import com.atdld.os.core.util.StringUtils;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @ClassName com.atdld.os.edu.controller.common.EduBaseController
 * @description
 * @author :
 * @Create Date : 2014-5-27 下午2:11:59
 */
public class AppBaseController extends BaseController {
    protected static final String EDU_VIEW_PATH = "app";// app的view路径
    static MemCache memCache = MemCache.getInstance();
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




    /** spring接受date类型转换
     */
    @InitBinder
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
		// 对于需要转换为Date类型的属性，使用DateEditor进行处理
		binder.registerCustomEditor(Date.class, new DateEditor());
	}

}
