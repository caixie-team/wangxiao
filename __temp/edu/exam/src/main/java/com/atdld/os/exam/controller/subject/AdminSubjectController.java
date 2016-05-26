package com.atdld.os.exam.controller.subject;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.security.auth.Subject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.Getter;
import lombok.Setter;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atdld.os.exam.controller.common.ExamBaseController;
import com.atdld.os.exam.entity.professional.ExamSubject;
import com.atdld.os.exam.entity.subject.QuerySubject;
import com.atdld.os.exam.service.subject.SubjectService;

/**
 * @author
 * @ClassName AdminSubjectAction
 * @package com.atdld.os.exam.controller.subject
 * @description
 * @Create Date: 2013-9-7 下午3:46:17
 */
@Controller
@RequestMapping("/admin")
public class AdminSubjectController extends ExamBaseController {

    private static final Logger logger = Logger.getLogger(AdminSubjectController.class);
    @Autowired
    private SubjectService subjectService;

    @Getter
    @Setter
    private ExamSubject subject;

    // 返回路径
    private String saveSubjectInit = getViewPath("/admin/subject/subject_add");
    private String toSubjectList = getViewPath("/admin/subject/subject_list");
    private String delSubjects = "redirect:/admin/subj/toSubjectList";
    private String saveSubject = "redirect:/admin/subj/toSubjectList";
    private String updateSubject = "redirect:/admin/subj/toSubjectList";
    private String toUpdateSubject = getViewPath("/admin/subject/subject_update");

    // 绑定变量名字和属性，参数封装进类
    @InitBinder("subject")
    public void initBinderSubject(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("subject.");
    }
    // 绑定变量名字和属性，参数封装进类
    @InitBinder("querySubject")
    public void initBinderQuerySubject(WebDataBinder binder) {
    	binder.setFieldDefaultPrefix("querySubject.");
    }

    // 通过id查找subject
    @RequestMapping("/subj/querySubjectByProId")
    @ResponseBody
    public Map<String, Object> querySubjectByProId(
            @ModelAttribute("querySubject") QuerySubject querySubject, HttpServletRequest request,
            HttpServletResponse response) {
        try {
            List<ExamSubject> subjectList = subjectService.getSubjectListByProfessionalId(querySubject);
            if (subjectList != null && subjectList.size() > 0) {
                this.setJson(true, "", subjectList);
            } else {
                this.setJson(false, "", null);
            }
        } catch (Exception e) {
            logger.error("SubjectAction.querySubjectByProId", e);
        }
        return json;
    }

  

}
