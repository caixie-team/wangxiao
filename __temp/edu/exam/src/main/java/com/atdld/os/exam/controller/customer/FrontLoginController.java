package com.atdld.os.exam.controller.customer;

import java.util.List;

import javax.security.auth.Subject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atdld.os.common.constants.CommonConstants;
import com.atdld.os.core.service.cache.MemCache;
import com.atdld.os.core.util.StringUtils;
import com.atdld.os.core.util.web.WebUtils;
import com.atdld.os.exam.constants.ExamConstants;
import com.atdld.os.exam.controller.common.ExamBaseController;
import com.atdld.os.exam.entity.professional.ExamProfessional;
import com.atdld.os.exam.entity.subject.QuerySubject;
import com.atdld.os.exam.service.professional.ProfessionalService;
import com.atdld.os.exam.service.subject.SubjectService;


/**
 * @author
 * @ClassName LoginAction
 * @package com.atdld.os.exam.controller.customer
 * @description 系统用户登录
 * @Create Date: 2013-3-1 下午10:21:44
 */
@Controller
public class FrontLoginController extends ExamBaseController {

    private static final Logger logger = Logger.getLogger(FrontLoginController.class);

    @Autowired
    private SubjectService subjectService;
    @Autowired
    private ProfessionalService professionalService;
    
    private MemCache memCache = MemCache.getInstance();

    /**
     * 考试主页面选择专业
     *
     * @return
     */
    @RequestMapping("/index")
    public String index(@ModelAttribute("querySubject") QuerySubject querySubject, HttpServletRequest request, HttpServletResponse response) {
        /*if ("1".equals(ExamConstants.single)) {// 使用demo的登录
            try {
                List<Subject> subjectList = subjectService.getSubjectListByLevel(querySubject);
                request.setAttribute("subjectList", subjectList);
            	List<ExamProfessional> professionalList = professionalService.queryProfessionalList();
            	request.setAttribute("professionalList", professionalList);
            } catch (Exception e) {
                logger.error("UserController.index", e);
                return setExceptionRequest(request, e);
            }
            return "/index";
        }*/
    	List<ExamProfessional> professionalList = professionalService.queryProfessionalList();
    	request.setAttribute("professionalList", professionalList);
        return "redirect:/quest/toQuestionitemList";
    }
    @RequestMapping("/exit")
    @ResponseBody
    public Object exit(HttpServletRequest request, HttpServletResponse response) {
        try {
        	String sid = WebUtils.getCookie(request, CommonConstants.USER_SINGEL_ID);
			if (StringUtils.isNotEmpty(sid)) {
				memCache.remove(sid);
			}
			WebUtils.deleteCookie(request, response, CommonConstants.USER_SINGEL_ID);
			WebUtils.deleteCookie(request, response, CommonConstants.USER_SINGEL_NAME);
			WebUtils.deleteCookie(request, response, "usercookieuserimg");
			WebUtils.deleteCookie(request, response, "e.subject");
			this.setJson(true, "", null);
            this.setJson(true, "", null);
        } catch (Exception e) {
            logger.error("FrontLoginController.exit", e);
            this.setJson(false, "", null);
            return setExceptionRequest(request, e);
        }
        return json;
    }
}
