package io.wangxiao.edu.home.controller.knowledge;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.edu.home.common.EduBaseController;
import io.wangxiao.edu.home.entity.knowledge.Knowledge;
import io.wangxiao.edu.home.entity.knowledge.KnowledgeCatalogDTO;
import io.wangxiao.edu.home.service.knowledge.KnowledgeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Description:学习体系
 */
@Controller
@RequestMapping("/front")
public class KnowledgeController extends EduBaseController {
    private static Logger logger = LoggerFactory.getLogger(KnowledgeController.class);

    private static final String knowledgeList = getViewPath("/knowledge/knowledge_list");

    private static final String knowledgeInfo = getViewPath("/knowledge/knowledge_info");

    @Autowired
    private KnowledgeService knowledgeService;

    @InitBinder("knowledge")
    public void initBinderKnowledge(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("knowledge.");
    }

    /**
     * 学习体系列表
     *
     * @param knowledge
     * @param page
     * @param model
     * @return
     */
    @RequestMapping("/getKnowledgeList")
    public String getKnowledgeList(@ModelAttribute("knowledge") Knowledge knowledge, @ModelAttribute("page") PageEntity page, Model model) {
        try {
            page.setPageSize(4);
            List<Knowledge> knowledgeList = this.knowledgeService.getKnowledgeList(knowledge, page);
            model.addAttribute("knowledgeList", knowledgeList);
            model.addAttribute("page", page);
        } catch (Exception e) {
            logger.error("KnowledgeController.getKnowledgeList", e);
        }
        return knowledgeList;
    }

    /**
     * 获取学习体系
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/getKnowledge/{id}")
    public String getKnowledge(@PathVariable("id") Long id, Model model) {
        try {
            Knowledge knowledge = this.knowledgeService.getKnowledgeById(id);
            model.addAttribute("knowledge", knowledge);
            List<KnowledgeCatalogDTO> knowledgeCatalogList = this.knowledgeService.getKnowledgeCatalog(id);
            model.addAttribute("knowledgeCatalogList", knowledgeCatalogList);
        } catch (Exception e) {
            logger.error("KnowledgeController.getKnowledgeList", e);
        }
        return knowledgeInfo;
    }
}
