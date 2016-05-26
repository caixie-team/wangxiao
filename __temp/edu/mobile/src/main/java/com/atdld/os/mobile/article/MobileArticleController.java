package com.atdld.os.mobile.article;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.atdld.os.core.entity.PageEntity;
import com.atdld.os.core.util.ObjectUtils;
import com.atdld.os.edu.common.MobileBaseController;
import com.atdld.os.edu.entity.article.Article;
import com.atdld.os.edu.service.article.ArticleService;

/**
 * Article管理接口 User:  Date: 2014-05-27
 */
@Controller
public class MobileArticleController extends MobileBaseController {
    private static final Logger logger = LoggerFactory.getLogger(MobileArticleController.class);
    // 资讯列表
    private String articlelist = getViewPath("/news/news-list");
    // 资讯列表ajax
    private String articlelistAjax = getViewPath("/news/news-list-ajax");
    // 资讯详情
    private String toArticle = getViewPath("/news/news-infor");
    @Autowired
    private ArticleService articleService;

    /**
     * 资讯列表
     * 
     * @return
     */
    @RequestMapping("/mobile/article/list")
    public String articlelist(HttpServletRequest request, @ModelAttribute("page") PageEntity page) {
        try {
        	Long type=Long.parseLong(request.getParameter("type"));
            // 页面传来的数据放到page中
            this.setPage(page);
            this.getPage().setPageSize(6);
            // 资讯列表
            Article article = new Article();
            article.setType(type);
            List<Article> articleList = articleService.queryArticleListPage(article, page);
            request.setAttribute("articleList", articleList);
            request.setAttribute("type", type);
            request.setAttribute("page", this.getPage());
        } catch (Exception e) {
            logger.error("MobileArticleController.articlelist", e);
            return setExceptionRequest(request, e);
        }
        return articlelist;
    }
    
    /**
     * 资讯列表ajax
     * 
     * @return
     */
    @RequestMapping("/mobile/article/ajax/list")
    public String articleAjaxlist(HttpServletRequest request, @ModelAttribute("page") PageEntity page) {
        try {
        	Long type=Long.parseLong(request.getParameter("type"));
            // 页面传来的数据放到page中
            this.setPage(page);
            this.getPage().setPageSize(6);
            // 资讯列表
            Article article = new Article();
            article.setType(type);
            List<Article> articleList = articleService.queryArticleListPage(article, page);
            request.setAttribute("articleList", articleList);
        } catch (Exception e) {
            logger.error("MobileArticleController.articleAjaxlist", e);
            return setExceptionRequest(request, e);
        }
        return articlelistAjax;
    }

    /**
     * 资讯详情
     * 
     * @return
     */
    @RequestMapping("/mobile/article/{id}")
    public String toArticle(HttpServletRequest request, @PathVariable Long id,
    		RedirectAttributes redirectAttributes,Model model) {
        try {
            Article article = articleService.getArticleById(id);
            if(ObjectUtils.isNull(article)){
				redirectAttributes.addAttribute("msg","对不起该资讯不存在或者已删除");
		        return "redirect:/mobile/success";
			}
            articleService.updateArticleClickTimes(article.getId());//更新浏览次数
            Article queryArticle = new Article();
            queryArticle.setId(id);
            queryArticle.setType(1l);
            //上一篇
            Article upArticle = articleService.queryArticleUpOrDown(queryArticle);
            queryArticle.setType(2l);
            //上一篇
            Article downArticle = articleService.queryArticleUpOrDown(queryArticle);
            model.addAttribute("article", article);
            model.addAttribute("upArticle", upArticle);
            model.addAttribute("downArticle", downArticle);
        } catch (Exception e) {
            logger.error("MobileArticleController.teacherlist", e);
            return setExceptionRequest(request, e);
        }
        return toArticle;
    }
}