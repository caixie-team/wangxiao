package com.atdld.os.sns.service.blog;

import java.util.List;
import java.util.Map;

import com.atdld.os.sns.entity.blog.BlogBlog;
import com.atdld.os.sns.entity.blog.QueryBlogAndReply;
import com.atdld.os.core.entity.PageEntity;
import com.atdld.os.sns.entity.discuss.Artclassify;

/**
 * @author :
 * @ClassName com.atdld.os.sns.service.blog.BlogBlogService
 * @description 博客servcie
 * @Create Date : 2013-12-30 上午9:35:56
 */
public interface BlogBlogService {

    /**
     * 添加BlogBlog
     *
     * @param blogBlog 要添加的BlogBlog
     * @return
     */
    public String addBlogBlog(BlogBlog blogBlog) throws Exception;

    /**
     * 根据id删除一个BlogBlog
     *
     * @param id 要删除的id
     */
    public String deleteBlogBlogById(Long id) throws Exception;

    /**
     * 修改BlogBlog
     *
     * @param blogBlog 要修改的BlogBlog
     */
    public String updateBlogBlog(BlogBlog blogBlog) throws Exception;

    /**
     * 根据id获取单个BlogBlog对象
     *
     * @param id 要查询的id
     * @return BlogBlog
     */
    public List<BlogBlog> getBlogBlogById(int id) throws Exception;

    /**
     * 获得单个博文详情
     *
     * @param blogId 博文id
     * @return
     */
    public BlogBlog getBlogBlogDetailById(Long blogId) throws Exception;

    /**
     * 根据条件获取BlogBlog列表
     *
     * @param blogBlog 查询条件
     * @return List<BlogBlog>
     */
    public List<BlogBlog> getBlogBlogList(BlogBlog blogBlog) throws Exception;

    /**
     * 查询全站博客
     *
     * @param page 分页参数
     * @return List<QueryBlogAndReply>
     */
    public List<QueryBlogAndReply> getBlogBlogAllList(PageEntity page) throws Exception;

    /**
     * 查询热门博客
     *
     * @param page 分页参数
     * @return List<QueryBlogAndReply>
     */
    public List<QueryBlogAndReply> getHotBlogBlogList(PageEntity page) throws Exception;

    /**
     * 查询财经文章排行(根据activity 活跃度进行排序)
     *
     * @param num 排行显示数量
     * @return List<BlogBlog>
     */
    public List<BlogBlog> getCaijingBlogBlogList(int num) throws Exception;

    /**
     * 查询博主排行（根据发博客的数量）
     *
     * @param num 排行显示数量
     * @return List<QueryBlogAndReply>
     */
    public List<QueryBlogAndReply> getBozhuListByBolgNum(int num) throws Exception;

    /**
     * 查询我发表的博文
     *
     * @param cusId 用户id
     * @param page  分页参数
     * @return List<BlogBlog>
     */
    public List<QueryBlogAndReply> getMyBlogBlogList(Long cusId, PageEntity page) throws Exception;

    /**
     * 查询好友（好友文章）
     *
     * @param cusId 用户id
     * @param page  分页参数
     * @return List<BlogBlog>
     */
    public List<QueryBlogAndReply> getFriendBlogBlogList(Long cusId, PageEntity page) throws Exception;

    /**
     * 查询一周内的博文
     *
     * @param page 分页参数
     * @return List<BlogBlog>
     */
    public List<BlogBlog> getOneWeekBlogBlogList() throws Exception;

    /**
     * 根据评论数查询排行
     *
     * @param page 分页参数
     * @return List<QueryBlogAndReply>
     */
    public List<QueryBlogAndReply> getBlogBlogListByReply(PageEntity page) throws Exception;

    /**
     * 根据浏览数查询排行
     *
     * @param page 分页参数
     * @return List<QueryBlogAndReply>
     */
    public List<QueryBlogAndReply> getBlogBlogListByView(PageEntity page) throws Exception;

    /**
     * 后台查询博文
     *
     * @param page
     * @return List <BlogBlog>
     */
    public List<BlogBlog> getAdminBlogBlogList(BlogBlog blogBlog, PageEntity page) throws Exception;

    /**
     * 后台置顶博文
     *
     * @param id
     * @return
     */
    public String updateBlogBlogByTop(Long id) throws Exception;

    /**
     * 后台取消置顶
     *
     * @param id 博客id
     * @return
     */
    public String updateCancelBlogBlogByTop(Long id) throws Exception;

    /**
     * 后台添加文章分类
     *
     * @param artclassify 分类
     */
    public void addArticleClassify(Artclassify artclassify) throws Exception;

    /**
     * 根据id删除文章分类
     *
     * @param artId 文章分类id
     * @return
     */
    public String deleteArticleClassifyById(Long artId) throws Exception;

    /**
     * 软删除文章分类
     *
     * @param artId
     * @return
     * @throws Exception
     */
    public String updateArticleClassifyById(Long artId) throws Exception;

    /**
     * 根据id获得文章分类
     *
     * @param artId 文章分类id
     * @return Artclassify
     */
    public Artclassify getArticleClassifyById(Long artId) throws Exception;

    /**
     * 修改文章分类
     *
     * @param artclassify
     */
    public void updateArticleClassify(Artclassify artclassify) throws Exception;

    /**
     * 搜索博文
     *
     * @param blogBlog 标题 作者 关键词
     * @param page     分页参数
     * @return
     */
    public List<QueryBlogAndReply> getBlogBlogResult(BlogBlog blogBlog, PageEntity page) throws Exception;

    /**
     * 查看博文博文数+1
     *
     * @param blogId 博文id
     * @param count  +1
     */
    public void updateBlogViewCount(Long blogId, int count) throws Exception;

    /**
     * 活跃度 财经采纳+500回复+10，浏览+1
     *
     * @param blogBlog
     */
    public void updateBlogActivity(BlogBlog blogBlog) throws Exception;

    /**
     * 同步财经文章
     *
     * @param blogBlog param 参数
     * @throws Exception
     */
    public void createFinanceArticle(String url, BlogBlog Blogblog) throws Exception;

    /**
     * 查询分类下的博文列表
     *
     * @param ClassifyId 分类id
     * @param page       分页参数
     * @return
     */
    public List<QueryBlogAndReply> getArticleListByClassifyId(int classifyId, PageEntity page) throws Exception;

    /**
     * 查询文章
     *
     * @param id 博文id
     * @return
     */
    public BlogBlog getAdminBlogBlogById(Long id) throws Exception;

    /**
     * 删除回复评论数-1
     *
     * @param blogId
     */
    public void delBlogReplycount(Long blogId) throws Exception;

    /**
     * 查询 此条博文是否是本人
     *
     * @param blogBlog 博文id 和用户
     * @return
     */
    public Integer getBlogIsMine(BlogBlog blogBlog) throws Exception;

    /**
     * 被财经选中 改变状态 活跃度增加
     *
     * @param BlogId 博文id
     * @return
     */
    public String updateBlogBlogIsBest(BlogBlog blogBlog) throws Exception;

    /**
     * 回复是更新时间，最后回复人
     *
     * @param blogBlog
     */
    public void updateBlogBlogLastReply(BlogBlog blogBlog) throws Exception;

    /**
     * 个人首页查询我的博文
     *
     * @param cusId
     * @return
     */
    public List<BlogBlog> getPersonMyArticleList(Long cusId, int num) throws Exception;

    /**
     * 同步财经文章修改标示
     *
     * @param blogId
     * @return
     */
    public Long updateFinBlogFlagByBlogId(Long blogId) throws Exception;

    /**
     * lucene方法 查询传入博文id 查询博文
     *
     * @param blogIds 博文id的list
     * @return List<BlogBlog>
     * @throws Exception
     */
    public List<QueryBlogAndReply> getLuceneBlogBlogByIds(List<Long> blogIds) throws Exception;

    /**
     * 查出这个博文id之后的博文行数和最大博文id
     *
     * @param blogId 博文id
     * @return Map<String, Object> 返回两个参数一个是博文的行数一个是最大的博文id
     * @throws Exception
     */
    public Map<String, Object> getBlogBlogCountAfterId(Long blogId) throws Exception;

    /**
     * 查出从starBlogBlogId（起始建议id）开始的建议 一共 pageSize（需要查出多少条）条
     * 要在minBlogBlogId（查询的where条件的最小建议id ）和maxBlogBlogId（查询的where条件的最大建议id）之间查
     *
     * @param starBlogBlogId 起始建议id
     * @param pageSize       需要查出多少条
     * @param minBlogBlogId  查询的where条件的最小建议id
     * @param maxBlogBlogId  查询的where条件的最大建议id
     * @return List<BlogBlog>
     * @throws Exception
     */
    public List<BlogBlog> getBlogBlogByPageQuery(Long beginRow, Long pageSize, Long minBlogBlogId, Long maxBlogBlogId) throws Exception;
}