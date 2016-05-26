package io.wangxiao.edu.home.controller.subject;

import io.wangxiao.commons.service.cache.CacheKit;
import io.wangxiao.commons.util.web.WebUtils;
import io.wangxiao.edu.common.constants.CommonConstants;
import io.wangxiao.edu.common.constants.MemConstans;
import io.wangxiao.edu.common.service.WebHessianService;
import io.wangxiao.edu.home.common.EduBaseController;
import io.wangxiao.edu.sysuser.entity.QuerySubject;
import io.wangxiao.edu.sysuser.entity.Subject;
import io.wangxiao.edu.sysuser.service.SubjectService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/subj")
public class SubjectController extends EduBaseController {


    @Autowired
    private SubjectService subjectService;
    @Autowired
    private WebHessianService webHessianService;

    @Getter
    @Setter
    private Subject subject;
    @Getter
    @Setter
    private QuerySubject querySubject;

    private CacheKit cacheKit = CacheKit.getInstance();

    // 返回路径
    private String showAllSubject = getViewPath("/subject/allSubject");
    private String addSubjectCookies = "redirect:/quest/toQuestionitemList";

    // 绑定变量名字和属性，参数封装进类
    @InitBinder("subject")
    public void initBinderSubject(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("subject.");
    }

    // 绑定变量名字和属性，参数封装进类
    @InitBinder("querySubject")
    public void initBinderquerySubject(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("querySubject.");
    }

    // 查询一级项目
    @RequestMapping("/showTopSubject")
    public String showTopSubject(@ModelAttribute("querySubject") QuerySubject querySubject, HttpServletRequest request, HttpServletResponse response) {
        try {
            List<Subject> subjectList = subjectService.getSubjectList(querySubject);
            request.setAttribute("subjectList", subjectList);
        } catch (Exception e) {
            return setExceptionRequest(request, e);
        }
        return showAllSubject;
    }

    // 通过pid查找subjectList
    @RequestMapping("/querySubjectByPid")
    @ResponseBody
    public Map<String, Object> querySubjectByPid(@ModelAttribute("querySubject") QuerySubject querySubject) {
        Map<String, Object> json = null;
        try {
            List<Subject> subjectList = subjectService.getSubjectList(querySubject);
            if (subjectList != null && subjectList.size() > 0) {
                json = this.getJsonMap(true, "", subjectList);
            } else {
                json = this.getJsonMap(false, "", null);
            }
        } catch (Exception e) {
            json = this.getJsonMap(false, "error", null);
        }
        return json;
    }

    // 通过项目ID查询项目
    // 通过id查找subject
    @RequestMapping("/querySubjectById")
    @ResponseBody
    public Map<String, Object> querySubjectById(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> json = null;
        try {
            Subject subject = (Subject) cacheKit.get(MemConstans.MEM_SUBECJT + this.getLoginSubjectId(request));
            if (subject == null) {
                subject = new Subject();
                subject.setSubjectId(this.getLoginSubjectId(request));
                subject = subjectService.getSubjectBySubjectId(subject);
                if (subject != null) {
                    cacheKit.set(MemConstans.MEM_SUBECJT + this.getLoginSubjectId(request), subject, MemConstans.MEM_COMMON_TIME);
                }
            }
            json = this.getJsonMap(true, "", subject);

        } catch (Exception e) {
            json = this.getJsonMap(false, "error", null);
        }
        return json;
    }

    // 查询所有项目
    @RequestMapping("/showAllSubject")
    public String showAllSubject(@ModelAttribute("querySubject") QuerySubject querySubject, HttpServletRequest request, HttpServletResponse response) {
        try {
            List<Subject> subjectList = subjectService.getSubjectList(querySubject);
            request.setAttribute("subjectList", subjectList);
        } catch (Exception e) {
            return setExceptionRequest(request, e);
        }
        return showAllSubject;
    }

    /**
     * 跳转到某一个项目页面 将选的专业存到cookie中
     *
     * @return
     */
    @RequestMapping("/addSubjectCookies")
    public String addSubjectCookies(@ModelAttribute("subject") Subject subject, HttpServletRequest request, HttpServletResponse response) {
        try {
            // 通过登陆用户的id 更新该用户的最后一次选择的专业
            Map<String, String> map = new HashMap<String, String>();
            // 用户id
            map.put("userId", getLoginUserId(request) + "");
            // 专业id
            map.put("subjectId", subject.getSubjectId() + "");
            webHessianService.updateUserExpandForSubject(map);
            // 把选择的专业生成cookie
            WebUtils.setCookie(response, CommonConstants.COOKIE_SUBJECTID_KEY, subject.getSubjectId() + "", 1);
        } catch (Exception e) {
        }
        // 跳转到页面/quest/toQuestionitemList
        return addSubjectCookies;
    }

    /**
     * 获取所有的专业直接返回jsp ajaxload
     *
     * @param querySubject
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/ajax/getSubject")
    public String getajaxtext(HttpServletRequest request) {
        try {
            List<Subject> subjectList = subjectService.getAllSubjectList();
            request.setAttribute("subjectList", subjectList);
        } catch (Exception e) {
        }
        return getViewPath("/ajax/subject");
    }

}
