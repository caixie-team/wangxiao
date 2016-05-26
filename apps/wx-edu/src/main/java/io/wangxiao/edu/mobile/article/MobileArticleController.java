package io.wangxiao.edu.mobile.article;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.commons.util.ObjectUtils;
import io.wangxiao.edu.home.common.MobileBaseController;
import io.wangxiao.edu.home.entity.article.Article;
import io.wangxiao.edu.home.service.article.ArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
        	String type=request.getParameter("type");
            // 页面传来的数据放到page中

            page.setPageSize(6);
            // 资讯列表
            Article article = new Article();
            article.setType(type);
            List<Article> articleList = articleService.queryArticleListPage(article, page);
            request.setAttribute("articleList", articleList);
            request.setAttribute("type", type);
            request.setAttribute("page", page);
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
        	String type=request.getParameter("type");
            // 页面传来的数据放到page中

            page.setPageSize(6);
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
            queryArticle.setType("info");
            //上一篇
            Article upArticle = articleService.queryArticleUpOrDown(queryArticle);
            queryArticle.setType("notice");
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