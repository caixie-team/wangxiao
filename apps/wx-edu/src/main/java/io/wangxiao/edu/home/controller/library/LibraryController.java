package io.wangxiao.edu.home.controller.library;


import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.commons.util.ObjectUtils;
import io.wangxiao.edu.home.common.EduBaseController;
import io.wangxiao.edu.home.entity.library.Library;
import io.wangxiao.edu.home.entity.library.LibraryDTO;
import io.wangxiao.edu.home.entity.library.QueryLibrary;
import io.wangxiao.edu.home.service.library.LibraryService;
import io.wangxiao.edu.sysuser.entity.Subject;
import io.wangxiao.edu.sysuser.service.SubjectService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
public class LibraryController extends EduBaseController {


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

    private static final String getLibraryList = getViewPath("/library/library_list");//文库列表
    private static final String libraryInfo = getViewPath("/library/library_info");//文库详情


    /**
     * 查询文库
     *
     * @return
     */
    @RequestMapping("/library/list")
    public ModelAndView queryLibraryList(HttpServletRequest request, @ModelAttribute("page") PageEntity page, @ModelAttribute("queryLibrary") QueryLibrary queryLibrary) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(getLibraryList);
        try {
            //文库分页
            page.setPageSize(10);
            List<LibraryDTO> librarys = libraryService.queryLibrayList(queryLibrary, page);// 查询文库
            modelAndView.addObject("librarys", librarys);
            modelAndView.addObject("page", page);
            //查找热门文库
            List<Library> hotLibrary = libraryService.hotLibraries(8);
            modelAndView.addObject("hotLibrary", hotLibrary);
            //查询一级专业
            List<Subject> subjectList = subjectService.getSubjectOneList();
            modelAndView.addObject("subjectList", subjectList);
            // 查询条件专业,子专业
            if (ObjectUtils.isNotNull(queryLibrary.getSubjectId())) {
                Subject subject = new Subject();
                subject.setSubjectId(queryLibrary.getSubjectId());
                subject = subjectService.getSubjectBySubjectId(subject);
                if (ObjectUtils.isNotNull(subject.getParentId())) {
                    Subject _subject = new Subject();
                    _subject.setSubjectId(subject.getParentId());
                    _subject = subjectService.getSubjectBySubjectId(_subject);
                    subject.setSubjectName(_subject.getSubjectName() + ">" + subject.getSubjectName());
                }
                modelAndView.addObject("subject", subject);
                // 查询子专业
                List<Subject> sonSubjectList = null;
                if (subject.getParentId() != 0) {// 条件为二级专业
                    sonSubjectList = subjectService.getSubjectListByOne(subject.getParentId());
                } else {// 条件为一级专业
                    sonSubjectList = subjectService.getSubjectListByOne(subject.getSubjectId());
                }
                modelAndView.addObject("sonSubjectList", sonSubjectList);
            }

        } catch (Exception e) {
            logger.error("LibraryController.queryLibraryList", e);
            return new ModelAndView(this.setExceptionRequest(request, e));
        }
        return modelAndView;
    }

    /**
     * 查询单个文库
     *
     * @return
     */
    @RequestMapping("/library/info/{id}")
    public String queryLibraryById(HttpServletRequest request, @PathVariable("id") Long id, RedirectAttributes redirectAttributes, Model model) {
        try {
            Library library = new Library();
            library.setId(id);
            library = libraryService.queryLibraryById(library);
            if (ObjectUtils.isNull(library)) {
                redirectAttributes.addAttribute("msg", "对不起该文库已经下架或者删除");
                return "redirect:/front/success";
            }
            //更新浏览量
            libraryService.updateLibraryBrowseNumById(id);

            if (library.getPdfUrl().endsWith(".pdf")) {
                request.setAttribute("type", "pdf");
            } else if (library.getPdfUrl().endsWith(".swf")) {
                request.setAttribute("type", "swf");
            } else {
                request.setAttribute("type", library.getType());
            }

            model.addAttribute("library", library);
//				if(library.getType()==2){//如果是图片集
//					List<LibraryImages> libraryImagesList=libraryService.queryLibraryImagesList(library.getId());
//					model.addAttribute("libraryImagesList",libraryImagesList);
//				}

        } catch (Exception e) {
            logger.error("LibraryController.queryLibraryById", e);
            return this.setExceptionRequest(request, e);
        }
        return libraryInfo;
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
