package io.wangxiao.edu.app.controller.edu;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.commons.service.gain.GuidGeneratorService;
import io.wangxiao.edu.app.common.AppBaseController;
import io.wangxiao.edu.home.entity.article.Article;
import io.wangxiao.edu.home.service.article.ArticleService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/app")
public class AppArticleController extends AppBaseController{
	private static Logger logger = Logger.getLogger(AppArticleController.class);

	private String toAppArticle = getViewPath("/article/article-app-info");
	@Autowired
	private ArticleService articleService;
	@Autowired
	GuidGeneratorService guidGeneratorService;

	/**
	 * app资讯列表
	 * @param page
	 * @param type
	 * @return
	 */
	@RequestMapping("/article/list")
	@ResponseBody
	public  Map<String,Object> queryArticleList(@ModelAttribute("page") PageEntity page, @RequestParam("type") String type){
		Map<String, Object> json=null;
		try{
			page.setPageSize(10);
			Article article = new Article();
			article.setType(type);
			List<Article> articleList = articleService.queryArticleListPage(article,page);
			Map<String,Object> dataMap =new HashMap<String, Object>();
			dataMap.put("articleList", articleList);
			dataMap.put("page", page);
			json=this.getJsonMap(true, "查询成功", dataMap);
		}catch (Exception e) {
			json=this.getJsonMap(false, "系统繁忙", null);
			logger.error("queryArticleList()----error", e);
		}
		return json;
	}

	/**
	 * app资讯详情
	 *
	 * @return
	 */
	@RequestMapping("/article/info/{id}")
	public String toAppArticle(HttpServletRequest request, @PathVariable Long id) {
		try {
			Article article = articleService.getArticleById(id);
			articleService.updateArticleClickTimes(article.getId());//更新浏览次数
			Article queryArticle = new Article();
			queryArticle.setId(id);
			queryArticle.setType("info");
			//上一篇
			Article upArticle = articleService.queryArticleUpOrDown(queryArticle);
			queryArticle.setType("notice");
			//上一篇
			Article downArticle = articleService.queryArticleUpOrDown(queryArticle);
			request.setAttribute("article", article);
			request.setAttribute("upArticle", upArticle);
			request.setAttribute("downArticle", downArticle);
		} catch (Exception e) {
			logger.error("ArticleController.toAppArticle", e);
			return setExceptionRequest(request, e);
		}
		return toAppArticle;
	}

}
