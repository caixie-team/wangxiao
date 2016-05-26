package io.wangxiao.edu.home.controller.article;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.commons.util.ObjectUtils;
import io.wangxiao.commons.util.StringUtils;
import io.wangxiao.edu.common.util.DateEditor;
import io.wangxiao.edu.home.common.EduBaseController;
import io.wangxiao.edu.home.entity.article.Article;
import io.wangxiao.edu.home.entity.website.WebsiteClassify;
import io.wangxiao.edu.home.service.article.ArticleService;
import io.wangxiao.edu.home.service.website.WebsiteClassifyService;
import io.wangxiao.edu.sysuser.entity.SysUserOptRecord;
import io.wangxiao.edu.sysuser.service.SysUserOptRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminArticleController extends EduBaseController {

    private static final Logger logger = LoggerFactory.getLogger(AdminArticleController.class);

    private static final String toAddArticle = getViewPath("/admin/article/addArticle");// 添加资讯页面
    private static final String toArticleList = getViewPath("/admin/article/article_list");// 资讯列表
    private static final String toUpdateArticle = getViewPath("/admin/article/article_update");// 资讯列表
    @Autowired
    private ArticleService articleService;
    @Autowired
    private SysUserOptRecordService sysUserOptRecordService;
    @Autowired
    private WebsiteClassifyService websiteClassifyService;

    // 绑定变量名字和属性，参数封装进类
    @InitBinder("article")
    public void initBinderArticle(HttpServletRequest request, ServletRequestDataBinder binder) {
        binder.setFieldDefaultPrefix("article.");
        binder.registerCustomEditor(Date.class, new DateEditor());
    }

    /**
     * 跳转添加资讯页面
     *
     * @return
     */
    @RequestMapping("/article/toAdd")
    public ModelAndView toAddArticle() {
        ModelAndView mav = new ModelAndView(toAddArticle);
        try {
            WebsiteClassify websiteClassify = new WebsiteClassify();
            websiteClassify.setType("article");
            List<WebsiteClassify> classifyList = websiteClassifyService.getWebsiteClassifyByCondition(websiteClassify);
            mav.addObject("classifyList", classifyList);
        } catch (Exception e) {
            logger.error("AdminArticleController.toAddArticle", e);
        }
        return mav;
    }

    /**
     * 添加资讯
     *
     * @param request
     * @param article
     * @return
     */
    @RequestMapping("/article/add")
    public String addArticle(HttpServletRequest request, @ModelAttribute("article") Article article) {
        try {
            if (ObjectUtils.isNotNull(article) && StringUtils.isNotEmpty(article.getTitle()) && StringUtils.isNotEmpty(article.getContent())) {
                article.setUpdateTime(new Date());
                articleService.addArticle(article);// 添加资讯
            }
        } catch (Exception e) {
            logger.error("AdminArticleController.addArticle", e);
        }
        return "redirect:/admin/article/list";
    }

    /**
     * 查询资讯列表
     *
     * @param model
     * @param article
     * @param page
     * @return
     */
    @RequestMapping("/article/list")
    public String queryArticleList(Model model, @ModelAttribute Article article, @ModelAttribute("page") PageEntity page) {
        try {
            // 设置分页

            page.setPageSize(10);
            // 查询资讯列表
            List<Article> articleList = articleService.queryArticleListPage(article, page);
            // 把返回的数据放到model中返回
            model.addAttribute("articleList", articleList);
            model.addAttribute("page", page);
            //分类
            WebsiteClassify websiteClassify = new WebsiteClassify();
            websiteClassify.setType("article");
            List<WebsiteClassify> classifyList = websiteClassifyService.getWebsiteClassifyByCondition(websiteClassify);
            model.addAttribute("classifyList", classifyList);
            model.addAttribute("article", article);
        } catch (Exception e) {
            logger.error("AdminArticleController.queryArticleList", e);
        }
        return toArticleList;
    }

    /**
     * 跳转到更新页面
     *
     * @param model
     * @param id
     * @return
     */
    @RequestMapping("/article/toUpdate/{id}")
    public String toUpdateArticle(Model model, @PathVariable("id") Long id) {
        try {
            //资讯分类
            WebsiteClassify websiteClassify = new WebsiteClassify();
            websiteClassify.setType("article");
            List<WebsiteClassify> classifyList = websiteClassifyService.getWebsiteClassifyByCondition(websiteClassify);
            model.addAttribute("classifyList", classifyList);
            // 查询资讯详情
            Article article = articleService.getArticleById(id);
            model.addAttribute("article", article);
        } catch (Exception e) {
            logger.error("AdminArticleController.toUpdateArticle", e);
        }
        return toUpdateArticle;
    }

    /**
     * 更新资讯
     *
     * @param article
     * @return
     */
    @RequestMapping("/article/updateArticle")
    public String updateArticle(HttpServletRequest request, @ModelAttribute Article article) {
        try {
            if (ObjectUtils.isNotNull(article) && StringUtils.isNotEmpty(article.getTitle()) && StringUtils.isNotEmpty(article.getContent())) {
                article.setUpdateTime(new Date());
                Article brforeRecord = articleService.getArticleById(article.getId());
                articleService.updateArticle(article);
                Article afterRecord = articleService.getArticleById(article.getId());
                SysUserOptRecord record = new SysUserOptRecord(request, "更新资讯", "咨询记录表-" + article.getId(), brforeRecord, afterRecord);
                if (record != null) sysUserOptRecordService.addRecord(record);
            }
        } catch (Exception e) {
            logger.error("AdminArticleController.updateArticle", e);
        }
        return "redirect:/admin/article/list";
    }

    /**
     * 批量删除资讯
     *
     * @param request
     * @param artIds
     * @return
     */
    @RequestMapping("/article/delArticleBatch")
    @ResponseBody
    public Map<String, Object> delArticleBatch(HttpServletRequest request, @RequestParam("artIds") String artIds) {
        Map<String, Object> json = null;
        try {
            if (StringUtils.isNotEmpty(artIds)) {
                // 批量刪除
                for (String s : artIds.replace(" ", "").split(",")) {
                    Article beforeRecord = articleService.getArticleById(Long.valueOf(s));
                    SysUserOptRecord record = new SysUserOptRecord(request, "删除资讯", "资讯记录表-" + s, beforeRecord, null);
                    if (record != null) sysUserOptRecordService.addRecord(record);
                }
                articleService.delArticleBatch(artIds);
                json = this.getJsonMap(true, "success", null);
            } else {
                json = this.getJsonMap(false, "false", null);
            }
        } catch (Exception e) {
            logger.error("AdminArticleController.delArticleBatch", e);
            json = this.getJsonMap(false, "false", null);
        }
        return json;
    }
}
