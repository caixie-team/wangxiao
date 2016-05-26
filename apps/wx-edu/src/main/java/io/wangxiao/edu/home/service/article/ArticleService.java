package io.wangxiao.edu.home.service.article;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.edu.home.entity.article.Article;

import java.util.List;

public interface ArticleService {

    /**
     * 添加Article
     *
     * @param article 要添加的Article
     * @return id
     */
    java.lang.Long addArticle(Article article);

    /**
     * 根据id删除一个Article
     *
     * @param id 要删除的id
     */
    void deleteArticleById(Long id);

    /**
     * 修改Article
     *
     * @param article 要修改的Article
     */
    void updateArticle(Article article);

    /**
     * 根据id获取单个Article对象
     *
     * @param id 要查询的id
     * @return Article
     */
    Article getArticleById(Long id);

    /**
     * 根据条件获取Article列表
     *
     * @param article 查询条件
     * @return List<Article>
     */
    List<Article> getArticleList(Article article);

    /**
     * 根据条件获取Article列表
     *
     * @param article 查询条件
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
     * 首页动态
     *
     * @return
     */
    List<Article> queryArticleNewsIndex();

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
     * 要修改的Article访问数量
     */
    void updateArticleClickTimes(Long id);

    /**
     * 批量删除资讯
     *
     * @param artIds
     */
    void delArticleBatch(String artIds);

    /**
     * 查询app的公告
     *
     * @param num
     * @return
     */
    List<Article> getAppArticleList(int num);
}