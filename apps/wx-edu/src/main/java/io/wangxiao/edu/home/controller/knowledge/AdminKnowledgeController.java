package io.wangxiao.edu.home.controller.knowledge;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.commons.util.ObjectUtils;
import io.wangxiao.edu.home.common.EduBaseController;
import io.wangxiao.edu.home.entity.knowledge.Knowledge;
import io.wangxiao.edu.home.entity.knowledge.KnowledgeCatalog;
import io.wangxiao.edu.home.entity.knowledge.KnowledgeCatalogMiddleCourse;
import io.wangxiao.edu.home.service.knowledge.KnowledgeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/knowledge")
public class AdminKnowledgeController extends EduBaseController {
    private static Logger logger = LoggerFactory.getLogger(AdminKnowledgeController.class);

    private static final String toAddKnowledge = getViewPath("/admin/knowledge/knowledge_add");
    private static final String knowledgeList = getViewPath("/admin/knowledge/knowledge_list");
    private static final String toUpdateKnowledge = getViewPath("/admin/knowledge/knowledge_update");

    private static final String toAddKnowledgeCatalog = getViewPath("/admin/knowledge/knowledge_catalog_add");
    private static final String toKnowledgeCatalogList = getViewPath("/admin/knowledge/knowledge_catalog_list");
    private static final String toUpdateKnowledgeCatalog = getViewPath("/admin/knowledge/knowledge_catalog_update");

    private static final String toKnowledgeCatalogCourseList = getViewPath("/admin/knowledge/knowledge_catalog_course_list");
    private static final String toUpdateKnowledgeCatalogCourse = getViewPath("//admin/knowledge/knowledge_catalog_course_update");
    @Autowired
    KnowledgeService knowledgeService;

    @InitBinder("knowledge")
    public void initBinderKnowledge(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("knowledge.");
    }

    @InitBinder("knowledgeCatalog")
    public void initBinderKnowledgeCatalog(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("knowledgeCatalog.");
    }

    @InitBinder("catalogCourse")
    public void initBinderCatalogCourse(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("catalogCourse.");
    }

    /**
     * 跳转体系添加页
     *
     * @return
     */
    @RequestMapping("/toAddKnowledge")
    public String toAddKnowledge() {
        return toAddKnowledge;
    }

    /**
     * 添加体系
     *
     * @param knowledge
     * @return
     */
    @RequestMapping("/addKnowledge")
    public String addKnowledge(@ModelAttribute("knowledge") Knowledge knowledge) {
        try {
            if (ObjectUtils.isNotNull(knowledge)) {
                this.knowledgeService.addKnowledge(knowledge);
            }
        } catch (Exception e) {
            logger.error("AdminKnowledgeController.addKnowledge", e);
        }
        return "redirect:/admin/knowledge/getKnowledgeList";
    }

    /**
     * 删除目录
     *
     * @param id id集合
     * @return
     */
    @RequestMapping("/deleteKnowledge")
    @ResponseBody
    public Map<String, Object> deleteKnowledge(@RequestParam("id") Long id) {
        Map<String, Object> json = null;
        try {
            this.knowledgeService.deleteKnowledge(id);
            json = this.getJsonMap(true, "success", null);
        } catch (Exception e) {
            logger.error("AdminKnowledgeController.deleteKnowledge", e);
            json = this.getJsonMap(false, "error", null);
        }
        return json;
    }

    /**
     * 跳转体系修改页
     *
     * @param id
     * @param request
     * @return
     */
    @RequestMapping("/toUpdateKnowledge/{id}")
    public String toUpdateKnowledge(@PathVariable("id") Long id, HttpServletRequest request) {
        try {
            if (ObjectUtils.isNotNull(id) && id > 0) {
                Knowledge knowledge = this.knowledgeService.getKnowledgeById(id);
                request.setAttribute("knowledge", knowledge);
            }
        } catch (Exception e) {
            logger.error("AdminKnowledgeController.toUpdateKnowledge", e);
        }
        return toUpdateKnowledge;
    }

    /**
     * 修改体系信息
     *
     * @param knowledge
     * @return
     */
    @RequestMapping("/updateKnowledge")
    public String updateKnowledge(@ModelAttribute("knowledge") Knowledge knowledge) {
        try {
            if (ObjectUtils.isNotNull(knowledge) && ObjectUtils.isNotNull(knowledge.getId()) && knowledge.getId() > 0) {
                this.knowledgeService.updateKnowledge(knowledge);
            }
        } catch (Exception e) {
            logger.error("AdminKnowledgeController.updateKnowledge");
        }
        return "redirect:/admin/knowledge/getKnowledgeList";
    }

    /**
     * 获取体系列表
     *
     * @param knowledge
     * @param page
     * @param model
     * @return
     */
    @RequestMapping("/getKnowledgeList")
    public String getKnowledgeList(@ModelAttribute("knowledge") Knowledge knowledge, @ModelAttribute("page") PageEntity page, Model model) {
        try {
            page.setPageSize(10);
            List<Knowledge> knowledgeList = this.knowledgeService.getKnowledgeList(knowledge, page);
            model.addAttribute("knowledgeList", knowledgeList);
            model.addAttribute("page", page);
        } catch (Exception e) {
            logger.error("AdminKnowledgeController.getKnowledgeList", e);
        }
        return knowledgeList;
    }

    /**
     * 跳转添加目录页
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/toAddKnowledgeCatalog/{id}")
    public String toAddKnowledgeCatalog(@PathVariable("id") Long id, Model model) {
        try {
            model.addAttribute("knowledgeId", id);
        } catch (Exception e) {
            logger.error("AdminKnowledgeController.toAddKnowledgeCatalog");
        }
        return toAddKnowledgeCatalog;
    }

    /**
     * 添加目录
     *
     * @param knowledgeCatalog
     * @return
     */
    @RequestMapping("/addKnowledgeCatalog")
    public String addKnowledgeCatalog(@ModelAttribute("knowledgeCatalog") KnowledgeCatalog knowledgeCatalog) {
        try {
            if (ObjectUtils.isNotNull(knowledgeCatalog)) {
                this.knowledgeService.addKnowledgeCatalog(knowledgeCatalog);
            }
        } catch (Exception e) {
            logger.error("AdminKnowledgeController.addKnowledgeCatalog", e);
        }
        return "redirect:/admin/knowledge/getKnowledgeCatalogList?knowledgeId=" + knowledgeCatalog.getKnowledgeId();
    }

    /**
     * 删除目录
     *
     * @param id id集合
     * @return
     */
    @RequestMapping("/deleteCatalog")
    @ResponseBody
    public Map<String, Object> deleteCatalog(@RequestParam("id") Long id) {
        Map<String, Object> json = null;
        try {
            this.knowledgeService.deleteKnowledgeCatalog(id);
            json = this.getJsonMap(true, "success", null);
        } catch (Exception e) {
            logger.error("AdminKnowledgeController.deleteCatalog", e);
            json = this.getJsonMap(false, "error", null);
        }
        return json;
    }

    /**
     * 跳转修改目录页
     *
     * @param id
     * @param request
     * @return
     */
    @RequestMapping("/toUpdateKnowledgeCatalog/{id}")
    public String toUpdateKnowledgeCatalog(@PathVariable("id") Long id, HttpServletRequest request) {
        try {
            if (ObjectUtils.isNotNull(id) && id > 0) {
                KnowledgeCatalog knowledgeCatalog = this.knowledgeService.getKnowledgeCatalogById(id);
                request.setAttribute("knowledgeCatalog", knowledgeCatalog);
            }
        } catch (Exception e) {
            logger.error("AdminKnowledgeController.toUpdateKnowledgeCatalog", e);
        }
        return toUpdateKnowledgeCatalog;
    }

    /**
     * 修改目录信息
     *
     * @param knowledgeCatalog
     * @return
     */
    @RequestMapping("/updateKnowledgeCatalog")
    public String updateKnowledgeCatalog(@ModelAttribute("knowledgeCatalog") KnowledgeCatalog knowledgeCatalog) {
        try {
            if (ObjectUtils.isNotNull(knowledgeCatalog) && ObjectUtils.isNotNull(knowledgeCatalog.getId()) && knowledgeCatalog.getId() > 0) {
                this.knowledgeService.updateKnowledgeCatalog(knowledgeCatalog);
            }
        } catch (Exception e) {
            logger.error("AdminKnowledgeController.updateKnowledgeCatalog", e);
        }
        return "redirect:/admin/knowledge/getKnowledgeCatalogList?knowledgeId=" + knowledgeCatalog.getKnowledgeId();
    }

    /**
     * 获取目录列表
     *
     * @param knowledgeCatalog
     * @param model
     * @return
     */
    @RequestMapping("/getKnowledgeCatalogList")
    public String getKnowledgeCatalogList(@ModelAttribute("knowledgeCatalog") KnowledgeCatalog knowledgeCatalog, Model model) {
        try {
            List<KnowledgeCatalog> knowledgeCatalogList = this.knowledgeService.getKnowledgeCatalogList(knowledgeCatalog);
            model.addAttribute("knowledgeCatalogList", knowledgeCatalogList);
            Knowledge knowledge = this.knowledgeService.getKnowledgeById(knowledgeCatalog.getKnowledgeId());
            model.addAttribute("knowledge", knowledge);
        } catch (Exception e) {
            logger.error("AdminKnowledgeController.getKnowledgeCatalogList", e);
        }
        return toKnowledgeCatalogList;
    }

    /**
     * 获取课程列表
     *
     * @param knowledgeCatalogMiddleCourse
     * @param model
     * @return
     */
    @RequestMapping("/getKnowledgeCatalogCourseList")
    public String getKnowledgeCatalogCourseList(@ModelAttribute("knowledgeCatalogCourse") KnowledgeCatalogMiddleCourse knowledgeCatalogMiddleCourse, Model model) {
        try {
            List<KnowledgeCatalogMiddleCourse> knowledgeCatalogMiddleCourseList = this.knowledgeService.getKnowledgeCatalogMiddleCourseList(knowledgeCatalogMiddleCourse);
            model.addAttribute("knowledgeCatalogCourseList", knowledgeCatalogMiddleCourseList);
            KnowledgeCatalog knowledgeCatalog = this.knowledgeService.getKnowledgeCatalogById(knowledgeCatalogMiddleCourse.getCatalogId());
            model.addAttribute("knowledgeCatalog", knowledgeCatalog);
            Knowledge knowledge = this.knowledgeService.getKnowledgeById(knowledgeCatalog.getKnowledgeId());
            model.addAttribute("knowledge", knowledge);
        } catch (Exception e) {
            logger.error("AdminKnowledgeController.getKnowledgeCatalogCourseList", e);
        }
        return toKnowledgeCatalogCourseList;
    }

    /**
     * 添加课程
     *
     * @param catalogCourse
     * @return
     */
    @RequestMapping("/addCourse")
    @ResponseBody
    public Map<String, Object> addCourse(@ModelAttribute("catalogCourse") KnowledgeCatalogMiddleCourse catalogCourse) {
        Map<String, Object> json = null;
        try {
            this.knowledgeService.addKnowledgeCatalogMiddleCourse(catalogCourse);
            json = this.getJsonMap(true, "success", null);
        } catch (Exception e) {
            logger.error("AdminKnowledgeController.addCourse", e);
            json = this.getJsonMap(false, "error", null);
        }
        return json;
    }

    /**
     * 删除课程
     *
     * @param ids id集合
     * @return
     */
    @RequestMapping("/deleteCourse")
    @ResponseBody
    public Map<String, Object> deleteCourse(@RequestParam("ids") String ids) {
        Map<String, Object> json = null;
        try {
            this.knowledgeService.deleteKnowledgeCatalogMiddleCourse(ids);
            json = this.getJsonMap(true, "success", null);
        } catch (Exception e) {
            logger.error("AdminKnowledgeController.deleteCourse", e);
            json = this.getJsonMap(false, "error", null);
        }
        return json;
    }

    /**
     * 跳转课程排序修改页
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/toUpdateKnowledgeCatalogMiddleCourse/{id}")
    public String toUpdateKnowledgeCatalogMiddleCourse(@PathVariable("id") Long id, Model model) {
        try {
            KnowledgeCatalogMiddleCourse knowledgeCatalogMiddleCourse = this.knowledgeService.getKnowledgeCatalogMiddleCourseById(id);
            model.addAttribute("catalogCourse", knowledgeCatalogMiddleCourse);
        } catch (Exception e) {
            logger.error("AdminKnowledgeController.toUpdateKnowledgeCatalogMiddleCourse", e);
        }
        return toUpdateKnowledgeCatalogCourse;
    }

    /**
     * 修改课程排序
     *
     * @param catalogCourse
     * @return
     */
    @RequestMapping("/updateKnowledgeCatalogMiddleCourse")
    public String updateKnowledgeCatalogMiddleCourse(@ModelAttribute("catalogCourse") KnowledgeCatalogMiddleCourse catalogCourse) {
        try {
            this.knowledgeService.updateKnowledgeCatalogMiddleCourseSort(catalogCourse);
        } catch (Exception e) {
            logger.error("AdminKnowledgeController.updateKnowledgeCatalogMiddleCourse", e);
        }
        return "redirect:/admin/knowledge/getKnowledgeCatalogCourseList?catalogId=" + catalogCourse.getCatalogId();
    }
}
