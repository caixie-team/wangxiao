package io.wangxiao.edu.home.controller.library;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.edu.home.common.EduBaseController;
import io.wangxiao.edu.home.entity.library.Library;
import io.wangxiao.edu.home.entity.library.LibraryDTO;
import io.wangxiao.edu.home.entity.library.QueryLibrary;
import io.wangxiao.edu.home.service.library.LibraryService;
import io.wangxiao.edu.sysuser.entity.QuerySubject;
import io.wangxiao.edu.sysuser.entity.Subject;
import io.wangxiao.edu.sysuser.service.SubjectService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 系统登录action
 */
@Controller
@RequestMapping("admin")
public class AdminLibraryController extends EduBaseController {

    private static final Logger logger = Logger.getLogger(LibraryController.class);
    @Autowired
    private LibraryService libraryService;
    @Autowired
    private SubjectService subjectService;

    @InitBinder("library")
    public void initBinderLibrary(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("library.");
    }

    @InitBinder("subject")
    public void initBinderSubject(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("subject.");
    }

    @InitBinder("queryLibrary")
    public void initBinderQueryLibrary(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("queryLibrary.");
    }

    private static final String getLibrarys = getViewPath("/admin/library/library_lists");// 文库列表
    private static final String addLibrary = getViewPath("/admin/library/library_add");// 添加文库
    private static final String updateLibrary = getViewPath("/admin/library/library_update");// 更新文库

    /**
     * 跳转添加图库
     *
     * @return
     */
    @RequestMapping("/library/doadd")
    public ModelAndView toAddLibrary(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(addLibrary);
        try {
            // 查询专业
            QuerySubject querySubject = new QuerySubject();
            List<Subject> subjectList = subjectService.getSubjectList(querySubject);
            request.setAttribute("subjectList", gson.toJson(subjectList));
        } catch (Exception e) {
            logger.error("AdminCouponController.toAddLibrary--跳转添加文库出错", e);
            return new ModelAndView(setExceptionRequest(request, e));
        }
        return modelAndView;
    }

    /**
     * 添加图库
     */
    @RequestMapping("/library/add")
    public String addLibrary(HttpServletRequest request, Library library) {
        try {
            library.setAddTime(new Date());
            libraryService.addLibrary(library);
        } catch (Exception e) {
            logger.error("AdminLibraryController.addLibrary", e);
            return this.setExceptionRequest(request, e);
        }
        return "redirect:/admin/library/list";
    }

    /**
     * 到更新图库的页面
     */
    @RequestMapping("/library/doupdate")
    public ModelAndView toUpdateLibrary(HttpServletRequest request, Library library) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(updateLibrary);
        try {
            // 查询专业
            QuerySubject querySubject = new QuerySubject();
            List<Subject> subjectList = subjectService.getSubjectList(querySubject);
            modelAndView.addObject("subjectList", gson.toJson(subjectList));
            // 文库信息
            library = libraryService.queryLibraryById(library);
            modelAndView.addObject("library", library);
            // 选择专业
            Subject subject = new Subject();
            subject.setSubjectId(library.getSubjectId());
            subject = subjectService.getSubjectBySubjectId(subject);
            modelAndView.addObject("subject", subject);
        } catch (Exception e) {
            logger.error("AdminLibraryController.toUpdateLibrary", e);
            return new ModelAndView(this.setExceptionRequest(request, e));
        }
        return modelAndView;
    }

    /**
     * 更新图库
     */
    @RequestMapping("/library/update")
    public String updateLibrary(HttpServletRequest request, Library library) {
        try {
            libraryService.updateLibrary(library);
        } catch (Exception e) {
            logger.error("AdminLibraryController.updateLibrary", e);
            return this.setExceptionRequest(request, e);
        }
        return "redirect:/admin/library/list";
    }

    /**
     * 删除图库
     */
    @RequestMapping("/library/del/{id}")
    @ResponseBody
    public Map<String, Object> delLibraryById(HttpServletRequest request, @PathVariable Long id) {
        Map<String, Object> json = null;
        try {
            libraryService.delLibrary(id);
            json = this.getJsonMap(true, "true", null);
        } catch (Exception e) {
            logger.error("AdminLibraryController.updateLibrary", e);
            json = this.getJsonMap(false, "error", null);
        }
        return json;
    }

    /**
     * 图库列表
     */
    @RequestMapping("/library/list")
    public ModelAndView toLibraryList(HttpServletRequest request, QueryLibrary queryLibrary, @ModelAttribute("page") PageEntity page) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(getLibrarys);
        try {
            // 查询专业
            QuerySubject querySubject = new QuerySubject();
            List<Subject> subjectList = subjectService.getSubjectList(querySubject);
            request.setAttribute("subjectList", gson.toJson(subjectList));

            page.setPageSize(10);
            List<LibraryDTO> libarys = libraryService.queryLibrayList(queryLibrary, page);
            modelAndView.addObject("libarys", libarys);
        } catch (Exception e) {
            logger.error("AdminLibraryController.addLibrary", e);
            return new ModelAndView(this.setExceptionRequest(request, e));
        }
        return modelAndView;
    }

    /**
     * 查找子专业方法
     */
    @RequestMapping("/library/subject/{id}")
    @ResponseBody
    public Map<String, Object> getTwoSubjects(HttpServletRequest request, @PathVariable Long id) {
        Map<String, Object> json = null;
        try {
            List<Subject> subjects = subjectService.getSubjectListByOne(id);
            json = this.getJsonMap(true, "true", subjects);
        } catch (Exception e) {
            logger.error("LibraryController.getTwoSubjects", e);
            json = this.getJsonMap(false, "error", null);
        }
        return json;
    }
}
