package com.atdld.os.exam.controller.common;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.atdld.os.common.contoller.SingletonLoginUtils;
import com.atdld.os.common.service.BaseService;
import com.atdld.os.core.controller.BaseController;
import com.atdld.os.core.util.StringUtils;
import com.atdld.os.core.util.web.WebUtils;
import com.atdld.os.exam.constants.ExamConstants;

/**
 * @author :
 * @ClassName com.supergenius.sns.action.common.CommonAction
 * @description 通用的action.所有的Controller继承，公用的写到此方法中
 * @Create Date : 2013-12-13 下午2:30:00
 */
public class ExamBaseController extends BaseController{

    // log对象
    private static final Logger logger = Logger.getLogger(ExamBaseController.class);

    protected static final String EXAM_VIEW_PATH = "exam";// exam的view路径
    protected String msgError = "/common/msg_error";
    /**
     * 获取登陆用户的id(前台用)
     *
     * @return int
     */
    protected Long getLoginUserId(HttpServletRequest request) {
        return SingletonLoginUtils.getLoginUserId(request);
    }

    protected Long getLoginSubjectId(HttpServletRequest request) {
        try {
            String ukey = WebUtils.getCookie(request, ExamConstants.COOKIE_SUBJECTID_KEY);
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

    /**
     * 返回sns的view路径
     */
    public static String getViewPath(String path) {
        if (StringUtils.isNotEmpty(path)) {
            return "/" + EXAM_VIEW_PATH + path;
        }
        return "";
    }
 

}
