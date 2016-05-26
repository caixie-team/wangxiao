package io.wangxiao.edu.home.controller.subject;

import io.wangxiao.commons.util.StringUtils;
import io.wangxiao.edu.home.common.EduBaseController;
import io.wangxiao.edu.sysuser.entity.QuerySubject;
import io.wangxiao.edu.sysuser.entity.Subject;
import io.wangxiao.edu.sysuser.entity.SysUserOptRecord;
import io.wangxiao.edu.sysuser.service.SubjectService;
import io.wangxiao.edu.sysuser.service.SysUserOptRecordService;
import lombok.Getter;
import lombok.Setter;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminSubjectController extends EduBaseController {

    private static final Logger logger = Logger.getLogger(AdminSubjectController.class);
    @Autowired
    private SubjectService subjectService;
    @Autowired
    private SysUserOptRecordService sysUserOptRecordService;

    @Getter
    @Setter
    private Subject subject;

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

    // 保存初始化
    @RequestMapping("/subj/saveSubjectInit")
    public String saveSubjectInit(HttpServletRequest request, HttpServletResponse response) {
        try {
            subject = new Subject();
            subject.setLevel(1);
            QuerySubject querySubject = new QuerySubject();
            // querySubject.setLevel(1);
            List<Subject> subjectList = subjectService.getSubjectList(querySubject);
            request.setAttribute("subjectList", gson.toJson(subjectList).toString());
        } catch (Exception e) {
            logger.error("SubjectAction.saveSubjectInit", e);
            return setExceptionRequest(request, e);
        }
        return saveSubjectInit;
    }

    // 通过pid查找subjectList
    @RequestMapping("/subj/querySubjectByPid")
    @ResponseBody
    public Map<String, Object> querySubjectByPid(@ModelAttribute("subject") Subject subject, HttpServletRequest request,
                                                 HttpServletResponse response) {
        Map<String, Object> json = null;
        try {
            QuerySubject querySubject = new QuerySubject();
            querySubject.setParentId(subject.getParentId());
            List<Subject> subjectList = subjectService.getSubjectList(querySubject);
            if (subjectList != null && subjectList.size() > 0) {
                json = this.getJsonMap(true, "", subjectList);
            } else {
                json = this.getJsonMap(false, "", null);
            }
        } catch (Exception e) {
            logger.error("SubjectAction.querySubjectByPid", e);
        }
        return json;
    }

    // 通过id查找subject
    @RequestMapping("/subj/querySubjectById")
    @ResponseBody
    public Map<String, Object> querySubjectById(@ModelAttribute("subject") Subject subject, HttpServletRequest request,
                                                HttpServletResponse response) {
        Map<String, Object> json = null;
        try {
            subject = subjectService.getSubjectBySubjectId(subject);
            json = this.getJsonMap(true, "", subject);
        } catch (Exception e) {
            logger.error("SubjectAction.querySubjectById", e);
        }
        return json;
    }

    @RequestMapping("/subj/toUpdateSubject")
    public String toUpdateSubject(@ModelAttribute("subject") Subject subject, HttpServletRequest request,
                                  HttpServletResponse response) {
        try {

            // 获得一级的专业列表
            Subject subjectByList = new Subject();
            subjectByList.setLevel(1);
            QuerySubject querySubject = new QuerySubject();
            // querySubject.setLevel(1);
            List<Subject> subjectList = subjectService.getSubjectList(querySubject);
            // 获得专业信息
            subject = subjectService.getSubjectBySubjectId(subject);
            // 获得父级节点
            Subject parentSubject;
            if (subject.getParentId() == 0) {
                parentSubject = new Subject();
                parentSubject.setSubjectName("根目录");
            } else {
                parentSubject = new Subject();
                parentSubject.setSubjectId(subject.getParentId());
                parentSubject = subjectService.getSubjectBySubjectId(parentSubject);
            }
            request.setAttribute("parentSubject", parentSubject);
            request.setAttribute("thissubject", subject);
            request.setAttribute("subjectList", gson.toJson(subjectList));

        } catch (Exception e) {
            logger.error("SubjectAction.toUpdateSubject", e);
            return setExceptionRequest(request, e);
        }
        return toUpdateSubject;
    }

    @RequestMapping("/subj/updateSubject")
    public String updateSubject(@ModelAttribute("subject") Subject subject, HttpServletRequest request,
                                HttpServletResponse response) {
        try {
            if (subject != null) {
                Subject beforeRecord = subjectService.getSubjectBySubjectId(subject);
                subjectService.updateSubjectBySubjectId(subject);
                Subject afterRecord = subjectService.getSubjectBySubjectId(subject);
                SysUserOptRecord record = new SysUserOptRecord(request, "修改课程项目", "课程项目记录-" + subject.getSubjectId(),
                        beforeRecord, afterRecord);
                if (record != null) {
                    sysUserOptRecordService.addRecord(record);
                }
            }
        } catch (Exception e) {
            logger.error("SubjectAction.updateSubject", e);
            return setExceptionRequest(request, e);
        }
        return updateSubject;
    }

    // 到项目列表页面
    @RequestMapping("/subj/toSubjectList")
    public String toSubjectList(HttpServletRequest request, HttpServletResponse response) {
        try {
            QuerySubject querySubject = new QuerySubject();
            List<Subject> subjectList = subjectService.getSubjectList(querySubject);
            request.setAttribute("subjectList", gson.toJson(subjectList).toString());
        } catch (Exception e) {
            logger.error("SubjectAction.toSubjectList", e);
            return setExceptionRequest(request, e);
        }
        return toSubjectList;
    }

    // 保存subject
    @RequestMapping("/subj/saveSubject")
    public String saveSubject(@ModelAttribute("subject") Subject subject, HttpServletRequest request,
                              HttpServletResponse response) {
        try {
            subject.setCreateTime(new Date()); // 默认为当前时间
            subject.setUpdateTime(new Date()); // 默认为当前时间
            subjectService.addOneSubject(subject);
        } catch (Exception e) {
            logger.error("SubjectAction.saveSubject", e);
            return setExceptionRequest(request, e);
        }
        return saveSubject;
    }

    // 保存subject
    @RequestMapping("/subj/delSubjects")
    public String delSubjects(@RequestParam("ids") List<Long> ids, HttpServletRequest request,
                              HttpServletResponse response) {
        try {
            for (Long id : ids) {
                Subject subject = new Subject();
                subject.setSubjectId(id);
                Subject beforeRecord = subjectService.getSubjectBySubjectId(subject);
                SysUserOptRecord record = new SysUserOptRecord(request, "删除课程项目", "课程项目表-" + id, beforeRecord, null);
                if (record != null)
                    sysUserOptRecordService.addRecord(record);
            }
            subjectService.delSubjects(ids);
        } catch (Exception e) {
            logger.error("SubjectAction.delSubjects", e);
            return setExceptionRequest(request, e);
        }
        return delSubjects;
    }

    /**
     * 后台获得所有项目
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/subj/getAllSubjectList")
    @ResponseBody
    public Map<String, Object> getAllSubjectList(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> json = null;
        try {
            // 获得所有项目
            QuerySubject querySubject = new QuerySubject();
            List<Subject> subjectList = subjectService.getSubjectList(querySubject);
            json = this.getJsonMap(true, "", subjectList);
        } catch (Exception e) {
            logger.error("SubjectAction.getAllSubjectList", e);
            json = this.getJsonMap(false, "error", null);
        }
        return json;
    }

    @RequestMapping("/ajax/batchShowIndex")
    @ResponseBody
    public Map<String, Object> batchShowIndex(@RequestParam("ids") String ids, @RequestParam("showIndex") Long showIndex) {
        Map<String, Object> json = null;
        try {
            if (StringUtils.isNotEmpty(ids) && showIndex != null) {
                Long result = subjectService.updateSubjectShowIndexBatch(ids, showIndex);
                if (result.longValue() > 0) {
                    json = getJsonMap(true, "success", null);
                    return json;
                }
            }
            json = getJsonMap(false, "false", null);
        } catch (Exception e) {
            logger.error("SubjectAction.batchShowIndex", e);
            json = this.getJsonMap(false, "error", null);
        }
        return json;
    }

    private String test1 = getViewPath("/admin/static/test1");
    private String test2 = getViewPath("/admin/static/test2");
    private String test3 = getViewPath("/admin/static/test3");
    private String test4 = getViewPath("/admin/static/test4");

    // 静态页
    @RequestMapping("/test1")
    public String test1() {
        return test1;
    }

    // 静态页
    @RequestMapping("/test2")
    public String test2() {
        return test2;
    }

    // 静态页
    @RequestMapping("/test3")
    public String test3() {
        return test3;
    }

    // 静态页
    @RequestMapping("/test4")
    public String test4() {
        return test4;
    }

}
