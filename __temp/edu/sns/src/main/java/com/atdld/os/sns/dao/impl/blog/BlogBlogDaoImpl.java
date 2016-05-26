package com.atdld.os.sns.dao.impl.blog;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.atdld.os.core.dao.impl.common.GenericDaoImpl;
import com.atdld.os.sns.constants.LuceneConstans;
import com.atdld.os.sns.dao.blog.BlogBlogDao;
import com.atdld.os.sns.entity.blog.BlogBlog;
import com.atdld.os.sns.entity.blog.QueryBlogAndReply;
import com.atdld.os.core.entity.PageEntity;
import com.atdld.os.sns.entity.discuss.Artclassify;

/**
 * @author :
 * @ClassName com.atdld.os.sns.dao.impl.blog.BlogBlogDaoImpl
 * @description 博客的Dao
 * @Create Date : 2013-12-30 上午9:31:42
 */
@Repository("blogBlogDao")
public class BlogBlogDaoImpl extends GenericDaoImpl implements BlogBlogDao {
    /**
     * 添加BlogBlog
     *
     * @param blogBlog 要添加的BlogBlog
     * @return
     */
    public Long addBlogBlog(BlogBlog blogBlog) {
        return this.insert("BlogBlogMapper.createBlogBlog", blogBlog);
    }

    /**
     * 根据id删除一个BlogBlog
     *
     * @param id 要删除的id
     */
    public Long deleteBlogBlogById(Long id) {
        return this.delete("BlogBlogMapper.deleteBlogBlogById", id);
    }

    /**
     * 修改BlogBlog
     *
     * @param blogBlog 要修改的BlogBlog
     */
    public Long updateBlogBlog(BlogBlog blogBlog) {
        return this.update("BlogBlogMapper.updateBlogBlog", blogBlog);
    }

    /**
     * 根据id获取单个BlogBlog对象
     *
     * @param id 要查询的id
     * @return BlogBlog
     */
    public List<BlogBlog> getBlogBlogById(int id) {
        List<BlogBlog> blogBlogList = this.selectList("BlogBlogMapper.getBlogBlogById", id);
        /*
         * if(blogBlogList!=null&&blogBlogList.size()>0){ return
         * blogBlogList.get(0); }
         */
        return blogBlogList;
    }

    /**
     * 根据条件获取BlogBlog列表
     *
     * @param blogBlog 查询条件
     * @return List<BlogBlog>
     */
    public List<BlogBlog> getBlogBlogList(BlogBlog blogBlog) {
        return this.selectList("BlogBlogMapper.getBlogBlogList", blogBlog);
    }

    /**
     * 查询全站博客
     *
     * @param page 分页参数
     * @return List<BlogBlog>
     */
    public List<QueryBlogAndReply> getBlogBlogAllList(PageEntity page) {
        return this.queryForListPage("BlogBlogMapper.getBlogBlogAllList", null, page);
    }

    /**
     * 查询热门博客
     *
     * @param page 分页参数
     * @return List<QueryBlogAndReply>
     */
    public List<QueryBlogAndReply> getHotBlogBlogList(PageEntity page) {
        return this.queryForListPage("BlogBlogMapper.getHotBlogBlogList", null, page);
    }

    /**
     * 查询财经文章排行(根据activity 活跃度进行排序)
     *
     * @param num 排行显示数量
     * @return List<BlogBlog>
     */
    public List<BlogBlog> getCaijingBlogBlogList(int num) throws Exception {
        return this.selectList("BlogBlogMapper.getCaijingBlogBlogList", num);// 查询财经文章排行
    }

    /**
     * 查询博主排行（根据发博客的数量）
     *
     * @param num 排行显示数量
     * @return List<QueryBlogAndReply>
     */
    public List<QueryBlogAndReply> getBozhuListByBolgNum(int num) throws Exception {
        return this.selectList("BlogBlogMapper.getBozhuListByBolgNum", num);// 查询博主排行（根据发博客的数量）
    }

    /**
     * 查询我发表的博文
     *
     * @param cusId 用户id
     * @param page  分页参数
     * @return List<QueryBlogAndReply>
     */
    public List<QueryBlogAndReply> getMyBlogBlogList(Long cusId, PageEntity page) {
        return this.queryForListPage("BlogBlogMapper.getMyBlogBlogList", cusId, page);
    }

    /**
     * 查询好友（好友文章）
     *
     * @param cusId 用户id
     * @param page  分页参数
     * @return List<QueryBlogAndReply>
     */
    public List<QueryBlogAndReply> getFriendBlogBlogList(Long cusId, PageEntity page) {
        return this.queryForListPage("BlogBlogMapper.getFriendBlogBlogList", cusId, page);
    }

    /**
     * 查询一周内的博文
     *
     * @return List<BlogBlog>
     */
    public List<BlogBlog> getOneWeekBlogBlogList() {
        return this.selectList("BlogBlogMapper.getOneWeekBlogBlogList", null);
    }

    /**
     * 根据评论数查询排行
     *
     * @param page 分页参数
     * @return List<QueryBlogAndReply>
     */
    public List<QueryBlogAndReply> getBlogBlogListByReply(PageEntity page) {
        return this.queryForListPage("BlogBlogMapper.getBlogBlogListByReply", null, page);
    }

    /**
     * 根据浏览数查询排行
     *
     * @param page 分页参数
     * @return List<QueryBlogAndReply>
     */
    public List<QueryBlogAndReply> getBlogBlogListByView(PageEntity page) {
        return this.queryForListPage("BlogBlogMapper.getBlogBlogListByView", null, page);
    }

    /**
     * 后台查询博文
     *
     * @param page
     * @return List <BlogBlog>
     */
    public List<BlogBlog> getAdminBlogBlogList(BlogBlog blogBlog, PageEntity page) {
        return this.queryForListPage("BlogBlogMapper.getAdminBlogBlogList", blogBlog, page);
    }

    /**
     * 后台置顶博文
     *
     * @param id
     * @return
     */
    public Long updateBlogBlogByTop(Long id) {
        return this.update("BlogBlogMapper.updateBlogBlogByTop", id);
    }

    /**
     * 查询博客详情
     *
     * @param blogBlog 用户id 博客id
     * @param page     分页参数
     * @return
     */
    public List<QueryBlogAndReply> getBlogBlogDetail(BlogBlog blogBlog, PageEntity page) {
        return this.queryForListPage("BlogBlogMapper.getBlogBlogDetail", blogBlog, page);
    }

    /**
     * 后台取消置顶
     *
     * @param id 博客id
     * @return
     */
    public Long updateCancelBlogBlogByTop(Long id) {
        return this.update("BlogBlogMapper.updateCancelBlogBlogByTop", id);
    }

    /**
     * 后台添加文章分类
     *
     * @param artclassify 分类
     */
    public void addArticleClassify(Artclassify artclassify) {
        this.insert("BlogBlogMapper.addArticleClassify", artclassify);

    }

    /**
     * 根据id删除文章分类
     *
     * @param artId 文章分类id
     * @return
     */
    public Long deleteArticleClassifyById(Long artId) {
        return this.delete("BlogBlogMapper.deleteArticleClassifyById", artId);
    }

    /**
     * 根据id获得文章分类
     *
     * @param artId 文章分类id
     * @return Artclassify
     */
    public Artclassify getArticleClassifyById(Long artId) {
        List<Artclassify> artclassify = this.selectList("BlogBlogMapper.getArticleClassifyById", artId);
        if (artclassify != null && artclassify.size() > 0) {
            return artclassify.get(0);
        }
        return null;
    }

    /**
     * 修改文章分类
     *
     * @param artclassify
     */
    public void updateArticleClassify(Artclassify artclassify) {
        this.update("BlogBlogMapper.updateArticleClassify", artclassify);
    }

    /**
     * 搜索博文
     *
     * @param blogBlog 标题 作者 关键词
     * @param page     分页参数
     * @return
     */
    public List<QueryBlogAndReply> getBlogBlogResult(BlogBlog blogBlog, PageEntity page) {
        return this.queryForListPage("BlogBlogMapper.getBlogBlogResult", blogBlog, page);
    }

    /**
     * 查看数每浏览一次+1
     *
     * @param blogId 博文id
     */
    public void updateBlogViewCount(Long blogId, int count) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", blogId);
        map.put("count", count);
        this.update("BlogBlogMapper.updateBlogViewCount", map);
    }

    /**
     * 活跃度 财经采纳+500回复+10，浏览+1
     *
     * @param blogBlog
     */
    public void updateBlogActivity(BlogBlog blogBlog) {
        this.update("BlogBlogMapper.updateBlogActivity", blogBlog);
    }

    /**
     * 查询分类下的博文列表
     *
     * @param ClassifyId 分类id
     * @param page       分页参数
     * @return
     */
    public List<QueryBlogAndReply> getArticleListByClassifyId(int classifyId, PageEntity page) {
        return this.queryForListPage("BlogBlogMapper.getArticleListByClassifyId", classifyId, page);
    }

    /**
     * 查询文章
     *
     * @param id 博文id
     * @return
     */
    public BlogBlog getAdminBlogBlogById(Long id) {
        List<BlogBlog> blogBlogList = this.selectList("BlogBlogMapper.getAdminBlogBlogById", id);
        if (blogBlogList != null && blogBlogList.size() > 0) {
            return blogBlogList.get(0);
        }
        return null;
    }

    /**
     * 获得单个博文详情
     *
     * @param blogId 博文id
     * @return
     */
    public BlogBlog getBlogBlogDetailById(Long blogId) {
        List<BlogBlog> blogBlogList = this.selectList("BlogBlogMapper.getBlogBlogDetailById", blogId);
        if (blogBlogList != null && blogBlogList.size() > 0) {
            return blogBlogList.get(0);
        }
        return null;
    }

    /**
     * 删除回复评论数-1
     *
     * @param blogId
     */
    public void delBlogReplycount(Long blogId) {
        this.update("BlogBlogMapper.delBlogReplycount", blogId);// 删除回复评论数-1
    }

    /**
     * 删除博文回复判断是否是本人
     *
     * @param blogReply 回复id 和 cusId
     * @return
     */
    public Integer getBlogIsMine(BlogBlog blogBlog) {
        List<Integer> integerList = this.selectList("BlogBlogMapper.getBlogIsMine", blogBlog);
        if (integerList != null && integerList.size() > 0) {
            return integerList.get(0);
        }
        return 0;
    }

    /**
     * 被财经选中 改变状态 活跃度增加
     *
     * @param BlogId 博文id
     * @return
     */
    public Long updateBlogBlogIsBest(BlogBlog blogBlog) {
        return this.update("BlogBlogMapper.updateBlogBlogIsBest", blogBlog);
    }

    /**
     * 查出这个博文id之后的博文行数和最大博文id
     *
     * @param blogId 博文id
     * @return Map<String, Object> 返回两个参数一个是博文的行数一个是最大的博文id
     * @throws Exception
     */
    public Map<String, Object> getBlogBlogCountAfterId(Long blogId) throws Exception {
        List<BlogBlog> blogBlogList = this.selectList("BlogBlogMapper.getBlogBlogCountAfterId", blogId);
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put(LuceneConstans.LUCENE_LINE_NUM, blogBlogList.get(0).getLineNum());// 把得到的行数数据放入map中
        map.put(LuceneConstans.LUCENE_MAX_ID, blogBlogList.get(0).getMaxId());// 把得到的最大博文id数据放入map中
        return map;
    }

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
    public List<BlogBlog> getBlogBlogByPageQuery(Long beginRow, Long pageSize, Long minBlogBlogId, Long maxBlogBlogId) throws Exception {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("beginRow", beginRow);// 把起始行数 放入map中
        map.put("pageSize", pageSize);// 把需要查出多少条 放入map中
        map.put("minBlogBlogId", minBlogBlogId);// 把 查询的where条件的最小建议id
        // 放入map中
        map.put("maxBlogBlogId", maxBlogBlogId);// 把 查询的where条件的最大建议id
        // 放入map中
        return this.selectList("BlogBlogMapper.getBlogBlogByPageQuery", map);
    }

    /**
     * lucene方法 查询传入博文id 查询博文
     *
     * @param blogIds 博文id的list
     * @return List<BlogBlog>
     * @throws Exception
     */
    public List<QueryBlogAndReply> getLuceneBlogBlogByIds(List<Long> blogIds) throws Exception {
        return this.selectList("BlogBlogMapper.getLuceneBlogBlogByIds", blogIds);// 查询传入博文id
        // 查询博文
    }

    /**
     * 回复是更新时间，最后回复人
     *
     * @param blogBlog
     */
    public void updateBlogBlogLastReply(BlogBlog blogBlog) {
        this.update("BlogBlogMapper.updateBlogBlogLastReply", blogBlog);
    }

    /**
     * 个人首页查询我的博文
     *
     * @param cusId
     * @return
     */
    public List<BlogBlog> getPersonMyArticleList(Long cusId, int num) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("cusId", cusId);
        map.put("num", num);
        return this.selectList("BlogBlogMapper.getPersonMyArticleList", map);
    }

    /**
     * 软删除文章分类
     *
     * @param artId
     * @return
     */
    public Long updateArticleClassifyById(Long artId) {
        return this.update("BlogBlogMapper.updateArticleClassifyById", artId);
    }

    /**
     * 同步财经文章修改标示
     *
     * @param blogId
     * @return
     */
    public Long updateFinBlogFlagByBlogId(Long blogId) {
        return this.update("BlogBlogMapper.updateFinBlogFlagByBlogId", blogId);
    }

}
