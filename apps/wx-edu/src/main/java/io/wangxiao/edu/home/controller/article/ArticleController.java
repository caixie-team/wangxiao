package io.wangxiao.edu.home.controller.article;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.commons.util.ObjectUtils;
import io.wangxiao.edu.home.common.EduBaseController;
import io.wangxiao.edu.home.entity.article.Article;
import io.wangxiao.edu.home.entity.website.WebsiteClassify;
import io.wangxiao.edu.home.service.article.ArticleService;
import io.wangxiao.edu.home.service.website.WebsiteClassifyService;
import io.wangxiao.edu.home.service.website.WebsiteImagesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
public class ArticleController extends EduBaseController {
    private static final Logger logger = LoggerFactory.getLogger(ArticleController.class);
    // 资讯列表
    private String articlelist = getViewPath("/article/article-list");
    // 资讯详情
    private String toArticle = getViewPath("/article/article-infor");

    @Autowired
    private WebsiteImagesService websiteImagesService;
    @Autowired
    private ArticleService articleService;
    @Autowired
    private WebsiteClassifyService websiteClassifyService;

    /**
     * 资讯列表
     *
     * @return
     */
    @RequestMapping("/front/articlelist/{type}")
    public ModelAndView articlelist(HttpServletRequest request, @ModelAttribute("page") PageEntity page, @PathVariable String type) {
        ModelAndView modelAndView = new ModelAndView(articlelist);
        try {
            //获得banner图
            Map<String, Object> websiteImages = websiteImagesService.getIndexPageBanner(null);
            modelAndView.addObject("websiteImages", websiteImages);
            // 页面传来的数据放到page中

            page.setPageSize(10);
            // 资讯列表
            Article article = new Article();
            article.setType(type);
            List<Article> articleList = articleService.queryArticleListPage(article, page);
            //article类型的classify
            WebsiteClassify websiteClassify = new WebsiteClassify();
            websiteClassify.setType("article");
            List<WebsiteClassify> classifyList = websiteClassifyService.getWebsiteClassifyByCondition(websiteClassify);
            modelAndView.addObject("classifyList", classifyList);
            // 点击量资讯排行
            List<Article> articleListOrderclickTimes = articleService.queryArticleListOrderclickTimes(10);
            modelAndView.addObject("articleList", articleList);
            modelAndView.addObject("articleListOrderclickTimes", articleListOrderclickTimes);
            modelAndView.addObject("article", article);
            modelAndView.addObject("page", page);
        } catch (Exception e) {
            logger.error("ArticleController.articlelist", e);
            return new ModelAndView(setExceptionRequest(request, e));
        }
        return modelAndView;
    }

    /**
     * 资讯列表
     *
     * @return
     */
    @RequestMapping("/front/toArticle/{id}")
    public String toArticle(HttpServletRequest request, @PathVariable Long id,
                            RedirectAttributes redirectAttributes, Model model) {
        try {
            Article article = articleService.getArticleById(id);
            if (ObjectUtils.isNull(article)) {
                redirectAttributes.addAttribute("msg", "对不起该资讯不存在或者已删除");
                return "redirect:/front/success";
            }
            articleService.updateArticleClickTimes(article.getId());//更新浏览次数
            // 页面传来的数据放到page中
            List<Article> articleListOrderclickTimes = articleService.queryArticleListOrderclickTimes(10);
            Article queryArticle = new Article();
            queryArticle.setId(id);
            queryArticle.setType("info");
            //上一篇
            Article upArticle = articleService.queryArticleUpOrDown(queryArticle);
            queryArticle.setType("notice");
            //上一篇
            Article downArticle = articleService.queryArticleUpOrDown(queryArticle);
            model.addAttribute("articleListOrderclickTimes", articleListOrderclickTimes);
            model.addAttribute("article", article);
            model.addAttribute("upArticle", upArticle);
            model.addAttribute("downArticle", downArticle);
        } catch (Exception e) {
            logger.error("ArticleController.teacherlist", e);
            return setExceptionRequest(request, e);
        }
        return toArticle;
    }

}