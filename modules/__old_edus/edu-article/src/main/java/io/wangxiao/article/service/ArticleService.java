package io.wangxiao.article.service;

import io.wangxiao.article.entity.Article;
import io.wangxiao.core.PageInfo;
import io.wangxiao.core.entity.PageEntity;

import java.util.List;

/**
 * 文章 Service 类
 */
public interface ArticleService {

	/**
	 * 查找首页公告
	 *
	 * @return
     */
	List<Article> findArticleForIndex();


	/**
	 * 根据条件查询资讯分页列表
	 *
	 * @param type 0:全部 1:资讯 2:公告
	 * @param pageNum
	 * @param pageSize
     * @return
     */
	PageInfo<Article> findArticlesByPage(Long type, int pageNum, int pageSize);

	/**
	 * 添加Article
	 * 
	 * @param article
	 *            要添加的Article
	 * @return id
	 */
	int addArticle(Article article);

	/**
	 * 根据id删除一个Article
	 * 
	 * @param id
	 *            要删除的id
	 */
	void deleteArticleById(Long id);

	/**
	 * 修改Article
	 * 
	 * @param article
	 *            要修改的Article
	 */
	void updateArticle(Article article);

	/**
	 * 根据id获取单个Article对象
	 * 
	 * @param id
	 *            要查询的id
	 * @return Article
	 */
	Article findById(Long id);

	/**
	 * 根据条件获取Article列表
	 * 
	 * @param article
	 *            查询条件
	 * @return List<Article>
	 */
	List<Article> getArticleList(Article article);

	/**
	 * 根据条件获取Article列表
	 * 
	 * @param article
	 *            查询条件
	 * @return List<Article>
	 */
	List<Article> queryArticleListPage(Article article, PageEntity page);

    /**
     * 首页公告
     *
     * @return List<Article>
     */
	List<Article> queryArticleIndex();

	/**
	 * 根据点击量排行
	 * 
	 * @return List<Article>
	 */
	List<Article> queryArticleListOrderclickTimes(int num);

	/**
	 * 查询上一篇下一篇
	 * 
	 * @return List<Article>
	 */
	Article queryArticleUpOrDown(Article article);

	/**
	 * 修改Article
	 * 
	 * @param article
	 *            要修改的Article访问数量
	 */
	void updateArticleClickTimes(Long id);

	/**
	 * 批量删除资讯
	 * 
	 * @param artIds
	 */
	void delArticleBatch(String artIds);
}