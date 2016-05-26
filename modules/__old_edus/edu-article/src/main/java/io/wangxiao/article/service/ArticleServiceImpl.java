package io.wangxiao.article.service;

import io.wangxiao.article.entity.Article;
import io.wangxiao.core.BeetlSqlService;
import io.wangxiao.core.PageInfo;
import io.wangxiao.core.entity.PageEntity;
import org.beetl.sql.core.SQLReady;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("articleService")
public class ArticleServiceImpl extends BeetlSqlService<Article> implements ArticleService {

    /**
     * 查找首页公告
     *
     * @return
     */
    public List<Article> findArticleForIndex() {
        return dao.execute(
                new SQLReady("select * from edu_article where type = ?", 2),
                Article.class
        );
    }


    /**
     * 根据条件查询资讯分页列表
     *
     * @param type     0:全部 1:资讯 2:公告
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageInfo<Article> findArticlesByPage(Long type, int pageNum, int pageSize) {


        Article article = new Article();
        article.setType(type);
        return super.findByTemplatePage(article, pageNum, pageSize);


//        PageInfo pageInfo = new PageInfo(dao.template(article, pageNum, pageSize), 10);
//        return pageInfo;

//        return new PageInfo<>(Size), pageSize);
//        dao.template()
//        return super.findByPage(pageNum,pageSize);
//        return PageHelper
//                .startPage(pageNum, pageSize)
//                .doSelectPageInfo(
//                        () -> baseDao.find(
//                                null,
//                                Arrays.asList(
//                                        Condition.parseCondition("type.long.eq").setValue(type)
//                                )
//                        )
//                );
    }

    @Override
    public int addArticle(Article article) {
        return 0;
    }

    @Override
    public void deleteArticleById(Long id) {

    }

    @Override
    public void updateArticle(Article article) {

    }

    @Override
    public Article findById(Long id) {
        return dao.unique(Article.class, id);
    }

    @Override
    public List<Article> getArticleList(Article article) {
        return null;
    }

    @Override
    public List<Article> queryArticleListPage(Article article, PageEntity page) {
        return null;
    }

    @Override
    public List<Article> queryArticleIndex() {
        return null;
    }

    @Override
    public List<Article> queryArticleListOrderclickTimes(int num) {
        return null;
    }

    @Override
    public Article queryArticleUpOrDown(Article article) {
        return null;
    }

    @Override
    public void updateArticleClickTimes(Long id) {

    }

    @Override
    public void delArticleBatch(String artIds) {

    }

    /**
     * 添加Article
     *
     * @param article
     *            要添加的Article
     * @return id
     */
//	public Long addArticle(Article article);

    /**
     * 根据id删除一个Article
     *
     * @param id
     *            要删除的id
     */
//	public void deleteArticleById(Long id);

    /**
     * 修改Article
     *
     * @param article
     *            要修改的Article
     */
//	public void updateArticle(Article article);

    /**
     * 根据id获取单个Article对象
     *
     * @param id
     *            要查询的id
     * @return Article
     */
//	public Article getArticleById(Long id);

    /**
     * 根据条件获取Article列表
     *
     * @param article
     *            查询条件
     * @return List<Article>
     */
//	public List<Article> getArticleList(Article article);

    /**
     * 根据条件获取Article列表
     *
     * @param article
     *            查询条件
     * @return List<Article>
     */
//	public List<Article> queryArticleListPage(Article article, PageEntity page);

    /**
     * 首页公告
     *
     * @return List<Article>
     */
//    public List<Article> queryArticleIndex();

    /**
     * 根据点击量排行
     *
     * @return List<Article>
     */
//	public List<Article> queryArticleListOrderclickTimes(int num);

    /**
     * 查询上一篇下一篇
     *
     * @return List<Article>
     */
//	public Article queryArticleUpOrDown(Article article);

    /**
     * 修改Article
     *
     * @param article
     *            要修改的Article访问数量
     */
//	public void updateArticleClickTimes(Long id);

    /**
     * 批量删除资讯
     *
     * @param artIds
     */
//	public void delArticleBatch(String artIds);
}