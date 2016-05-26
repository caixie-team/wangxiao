package com.atdld.os.sns.service.impl.blog;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atdld.os.common.constants.MemConstans;
import com.atdld.os.common.service.WebHessianService;
import com.atdld.os.core.entity.PageEntity;
import com.atdld.os.core.service.cache.MemCache;
import com.atdld.os.core.util.ObjectUtils;
import com.atdld.os.core.util.StringUtils;
import com.atdld.os.core.util.web.HttpUtil;
import com.atdld.os.core.util.web.WebUtils;
import com.atdld.os.sns.constants.ArticleConstants;
import com.atdld.os.sns.constants.BlogConstans;
import com.atdld.os.sns.constants.DynamicConstans;
import com.atdld.os.sns.constants.SnsConstants;
import com.atdld.os.sns.dao.blog.BlogBlogDao;
import com.atdld.os.sns.entity.blog.BlogBlog;
import com.atdld.os.sns.entity.blog.QueryBlogAndReply;
import com.atdld.os.sns.entity.customer.SnsUserExpandDto;
import com.atdld.os.sns.entity.discuss.Artclassify;
import com.atdld.os.sns.entity.dynamic.DynamicWeb;
import com.atdld.os.sns.service.blog.BlogBlogService;
import com.atdld.os.sns.service.blog.BlogReplyService;
import com.atdld.os.sns.service.dynamic.DynamicWebService;
import com.atdld.os.sns.service.user.SnsUserService;

/**
 * @author :
 * @ClassName com.atdld.os.sns.service.impl.blog.BlogBlogServiceImpl
 * @description 博客serviceImpl
 * @Create Date : 2013-12-30 上午10:27:07
 */
@Service("blogBlogService")
public class BlogBlogServiceImpl implements BlogBlogService {

    @Autowired
    private BlogBlogDao blogBlogDao;
    @Autowired
    private BlogReplyService blogReplyService;
    @Autowired
    private SnsUserService snsUserService;
    @Autowired
    private WebHessianService webHessianService;
    @Autowired
    private DynamicWebService dynamicWebService;
    
    // 获得memcache
    private MemCache memCache = MemCache.getInstance();
    
   
    /**
     * 添加BlogBlog
     *
     * @param blogBlog 要添加的BlogBlog
     * @return
     */
    public String addBlogBlog(BlogBlog blogBlog) throws Exception {
        // 查看用户操作是否频繁
        boolean bool = snsUserService.checkLimitOpt(MemConstans.ARTICLE_LIMIT,blogBlog.getCusId());
        if (bool == true) {
            // 获得showname
            SnsUserExpandDto userExpandDto = snsUserService.getUserExpandByCusId(blogBlog.getCusId());
            blogBlog.setShowName(userExpandDto.getShowname());
            blogBlog.setAddTime(new Date());// 添加时间
            blogBlog.setContent(webHessianService.doFilter(blogBlog.getContent()));
            String summary = WebUtils.replaceTagHTML(blogBlog.getContent());
            if (StringUtils.isNotEmpty(summary)) {
                summary = StringUtils.getLength(summary, BlogConstans.BLOG_SUMMARY_LENGTH);
                blogBlog.setSummary(summary);
            } else {
                blogBlog.setSummary("");// 如果为空则set空
            }
            Long num = blogBlogDao.addBlogBlog(blogBlog);
            if (num == 1) {
                // 添加成功，该用户操作数加一
                snsUserService.customerOptLimitCountAdd(MemConstans.ARTICLE_LIMIT,blogBlog.getCusId());
                // 添加博客动态
                dynamicWebService.addDynamicWebForBlogBlog(blogBlog);
                return SnsConstants.SUCCESS;
            } else {
                return SnsConstants.FALSE;
            }
        } else {
            return SnsConstants.LIMITOPT;// 返回提示操作频繁
        }

    }

    /**
     * 根据id删除一个BlogBlog
     *
     * @param id 要删除的id
     */
    public String deleteBlogBlogById(Long id) throws Exception {
        // 删除返回结果
        BlogBlog blogBlog = this.getBlogBlogDetailById(id);
        Long num = blogBlogDao.deleteBlogBlogById(id);
        if (num == 1) {
            // 删除此博文下回复
            blogReplyService.deleteBlogReplyByBlogId(id);
            DynamicWeb dynamicWeb = new DynamicWeb();
            dynamicWeb.setBizId(id);
            if (blogBlog != null) {
                if (blogBlog.getType() == ArticleConstants.ACTIVITY_CLASSIFY_FINANCE) {
                    dynamicWeb.setType(DynamicConstans.DYNAMICWEB_TYPE_FINBLOG);
                    dynamicWebService.deleteDynamicWebByCondition(dynamicWeb);
                    dynamicWeb.setType(DynamicConstans.DYNAMICWEB_TYPE_REFINBLOG);
                    dynamicWebService.deleteDynamicWebByCondition(dynamicWeb);
                } else {
                    dynamicWeb.setType(DynamicConstans.DYNAMICWEB_TYPE_BLOG);
                    dynamicWebService.deleteDynamicWebByCondition(dynamicWeb);
                    dynamicWeb.setType(DynamicConstans.DYNAMICWEB_TYPE_REBLOG);
                    dynamicWebService.deleteDynamicWebByCondition(dynamicWeb);
                }
            }
            return SnsConstants.SUCCESS;
        } else {
            return SnsConstants.FALSE;
        }
    }

    /**
     * 修改BlogBlog
     *
     * @param blogBlog 要修改的BlogBlog
     */
    public String updateBlogBlog(BlogBlog blogBlog) throws Exception {
        blogBlog.setUpdateTime(new Date());
        blogBlog.setContent(webHessianService.doFilter(blogBlog.getContent()));
        String summary = WebUtils.replaceTagHTML(blogBlog.getContent());
        if (StringUtils.isNotEmpty(summary)) {
            summary = StringUtils.getLength(summary, BlogConstans.BLOG_SUMMARY_LENGTH);
            blogBlog.setSummary(summary);
        } else {
            blogBlog.setSummary("");// 如果为空则set空
        }
        Long num = blogBlogDao.updateBlogBlog(blogBlog);
        if (num == 1) {
            return SnsConstants.SUCCESS;
        }
        return SnsConstants.FALSE;
    }

    /**
     * 根据id获取单个BlogBlog对象
     *
     * @param id 要查询的id
     * @return BlogBlog
     */
    public List<BlogBlog> getBlogBlogById(int id) throws Exception {
        return blogBlogDao.getBlogBlogById(id);
    }

    /**
     * 根据条件获取BlogBlog列表
     *
     * @param blogBlog 查询条件
     * @return List<BlogBlog>
     */
    public List<BlogBlog> getBlogBlogList(BlogBlog blogBlog) throws Exception {
        return blogBlogDao.getBlogBlogList(blogBlog);
    }

    /**
     * 查询全站博客
     *
     * @param page 分页参数
     * @return List<QueryBlogAndReply>
     */
    public List<QueryBlogAndReply> getBlogBlogAllList(PageEntity page) throws Exception {
        List<QueryBlogAndReply> queryBlogAndReplyList = blogBlogDao.getBlogBlogAllList(page);
        if(queryBlogAndReplyList!=null&&queryBlogAndReplyList.size()>0){
        	 Map<String, SnsUserExpandDto> map = snsUserService.getUserExpandsByCusId(getblogBlogListCusId(queryBlogAndReplyList));
             //循环博客查询showname
             for (QueryBlogAndReply queryBlogAndReply : queryBlogAndReplyList) { //通过用户id查询出customer的map
                 SnsUserExpandDto userExpandDto = map.get(queryBlogAndReply.getCusId() + "");
                 if (ObjectUtils.isNotNull(userExpandDto)) {
                     queryBlogAndReply.setUserExpandDto(userExpandDto);
                     queryBlogAndReply.setShowName(userExpandDto.getShowname());
                 }
             }
             
        }
       
        return queryBlogAndReplyList;
    }

    /**
     * 查询热门博客
     *
     * @param page 分页参数
     * @return List<QueryBlogAndReply>
     */
    public List<QueryBlogAndReply> getHotBlogBlogList(PageEntity page) throws Exception {
        List<QueryBlogAndReply> queryBlogAndReplyList = blogBlogDao.getHotBlogBlogList(page);
        if(queryBlogAndReplyList!=null&&queryBlogAndReplyList.size()>0){
	        Map<String, SnsUserExpandDto> map = snsUserService.getUserExpandsByCusId(getblogBlogListCusId(queryBlogAndReplyList));
	        //循环博客查询showname
	        if (ObjectUtils.isNotNull(queryBlogAndReplyList) && queryBlogAndReplyList.size() > 0) {
	            for (QueryBlogAndReply queryBlogAndReply : queryBlogAndReplyList) { //通过用户id查询出customer的map
	                SnsUserExpandDto userExpandDto = map.get(queryBlogAndReply.getCusId() + "");
	                if (ObjectUtils.isNotNull(userExpandDto)) {
	                    queryBlogAndReply.setUserExpandDto(userExpandDto);
	                    queryBlogAndReply.setShowName(userExpandDto.getShowname());
	                }
	            }
	        }
        }
        return queryBlogAndReplyList;
    }

    /**
     * 查询财经文章排行(根据activity 活跃度进行排序)
     *
     * @param num 排行显示数量
     * @return List<BlogBlog>
     */
    @SuppressWarnings("unchecked")
    public List<BlogBlog> getCaijingBlogBlogList(int num) throws Exception {
        List<BlogBlog> blogBlogList = (List<BlogBlog>) memCache
                .get(MemConstans.INDEX_BLOG_BLOG);// 从缓存中读取
        if (ObjectUtils.isNull(blogBlogList)) {// 如果为null则调用接口获取数据
            blogBlogList = blogBlogDao.getCaijingBlogBlogList(num);
            memCache.set(MemConstans.INDEX_BLOG_BLOG, blogBlogList,
                    MemConstans.INDEX_BLOG_BLOG_TIME);// 把数据放入缓存中
        }
        return blogBlogList;
    }

    /**
     * 查询博主排行（根据发博客的数量）
     *
     * @param num 排行显示数量
     * @return List<QueryBlogAndReply>
     */
    @SuppressWarnings("unchecked")
    public List<QueryBlogAndReply> getBozhuListByBolgNum(int num) throws Exception {
        List<QueryBlogAndReply> queryBlogAndReplyList = (List<QueryBlogAndReply>) memCache
                .get(MemConstans.INDEX_QUERYBLOGANDREPLY);// 从缓存中读取

        if (ObjectUtils.isNull(queryBlogAndReplyList)) {// 如果为null则调用接口获取数据
            queryBlogAndReplyList = blogBlogDao.getBozhuListByBolgNum(num);// 查询博主排行（根据发博客的数量）
            if (queryBlogAndReplyList == null) {
                return null;
            }
            Map<String, SnsUserExpandDto> map = snsUserService.getUserExpandsByCusId(getQueryBlogAndReplyListCusId(queryBlogAndReplyList));// 查询用户的信息
            if (queryBlogAndReplyList != null && queryBlogAndReplyList.size() > 0) {
                for (QueryBlogAndReply queryBlogAndReply : queryBlogAndReplyList) {
                    queryBlogAndReply.setUserExpandDto(map.get("" + queryBlogAndReply.getCusId()));
                }
            }
            memCache.set(MemConstans.INDEX_QUERYBLOGANDREPLY, queryBlogAndReplyList,
                    MemConstans.INDEX_QUERYBLOGANDREPLY_TIME);// 把数据放入缓存中
        }
        return queryBlogAndReplyList;// 查询博主排行（根据发博客的数量）
    }

    public String getQueryBlogAndReplyListCusId(List<QueryBlogAndReply> queryBlogAndReplyList) {//获得用户ids
        String ids = "";
        if (queryBlogAndReplyList != null && queryBlogAndReplyList.size() > 0) {
            for (QueryBlogAndReply queryBlogAndReply : queryBlogAndReplyList) {
                ids += queryBlogAndReply.getCusId() + ",";
            }
        }
        return ids;
    }


    /**
     * 查询我发表的博文
     *
     * @param cusId 用户id
     * @param page  分页参数
     * @return List<BlogBlog>
     */
    public List<QueryBlogAndReply> getMyBlogBlogList(Long cusId, PageEntity page) throws Exception {
        List<QueryBlogAndReply> queryBlogAndReplyList = blogBlogDao.getMyBlogBlogList(cusId, page);
        if(queryBlogAndReplyList!=null&&queryBlogAndReplyList.size()>0){
        	Map<String, SnsUserExpandDto> map = snsUserService.getUserExpandsByCusId(getblogBlogListCusId(queryBlogAndReplyList));
            //循环博客查询showname
            if (ObjectUtils.isNotNull(queryBlogAndReplyList) && queryBlogAndReplyList.size() > 0) {
                for (QueryBlogAndReply queryBlogAndReply : queryBlogAndReplyList) { //通过用户id查询出customer的map
                    SnsUserExpandDto userExpandDto = map.get(queryBlogAndReply.getCusId() + "");
                    if (ObjectUtils.isNotNull(userExpandDto)) {
                        queryBlogAndReply.setUserExpandDto(userExpandDto);
                        queryBlogAndReply.setShowName(userExpandDto.getShowname());
                    }
                }
            }
        }
        
        return queryBlogAndReplyList;
    }

    /**
     * 查询好友（好友文章）
     *
     * @param cusId 用户id
     * @param page  分页参数
     * @return List<QueryBlogAndReply>
     */
    public List<QueryBlogAndReply> getFriendBlogBlogList(Long cusId, PageEntity page)
            throws Exception {
        List<QueryBlogAndReply> queryBlogAndReplyList = blogBlogDao.getFriendBlogBlogList(cusId, page);
        if(queryBlogAndReplyList!=null&&queryBlogAndReplyList.size()>0){
	        Map<String, SnsUserExpandDto> map = snsUserService.getUserExpandsByCusId(getblogBlogListCusId(queryBlogAndReplyList));
	        //循环博客查询showname
	        if (ObjectUtils.isNotNull(queryBlogAndReplyList) && queryBlogAndReplyList.size() > 0) {
	            for (QueryBlogAndReply queryBlogAndReply : queryBlogAndReplyList) { //通过用户id查询出customer的map
	                SnsUserExpandDto userExpandDto = map.get(queryBlogAndReply.getCusId() + "");
	                if (ObjectUtils.isNotNull(userExpandDto)) {
	                    queryBlogAndReply.setUserExpandDto(userExpandDto);
	                    queryBlogAndReply.setShowName(userExpandDto.getShowname());
	                }
	            }
	        }
        }
        return queryBlogAndReplyList;
    }

    /**
     * 查询一周内的博文
     *
     * @param page 分页参数
     * @return List<BlogBlog>
     */
    public List<BlogBlog> getOneWeekBlogBlogList() throws Exception {
        @SuppressWarnings("unchecked")
        // 根据key获得缓存数据
                List<BlogBlog> blogBlogList = (List<BlogBlog>) memCache
                .get(MemConstans.TOP_BLOG_WEEK);
        if (ObjectUtils.isNull(blogBlogList)) {
            blogBlogList = blogBlogDao.getOneWeekBlogBlogList();
            memCache.set(MemConstans.TOP_BLOG_WEEK, blogBlogList,
                    MemConstans.HOT_TIME_BLOG_WEEK);
        }
        return blogBlogList;
    }

    /**
     * 根据评论数查询排行
     *
     * @param page 分页参数
     * @return List<QueryBlogAndReply>
     */
    public List<QueryBlogAndReply> getBlogBlogListByReply(PageEntity page)
            throws Exception {
        List<QueryBlogAndReply> queryBlogAndReplyList = blogBlogDao.getBlogBlogListByReply(page);
        if(queryBlogAndReplyList!=null&&queryBlogAndReplyList.size()>0){
        	Map<String, SnsUserExpandDto> map = snsUserService.getUserExpandsByCusId(getblogBlogListCusId(queryBlogAndReplyList));
            //循环博客查询showname
            if (ObjectUtils.isNotNull(queryBlogAndReplyList) && queryBlogAndReplyList.size() > 0) {
                for (QueryBlogAndReply queryBlogAndReply : queryBlogAndReplyList) { //通过用户id查询出customer的map
                    SnsUserExpandDto userExpandDto = map.get(queryBlogAndReply.getCusId() + "");
                    if (ObjectUtils.isNotNull(userExpandDto)) {
                        queryBlogAndReply.setUserExpandDto(userExpandDto);
                        queryBlogAndReply.setShowName(userExpandDto.getShowname());
                    }
                }
            }
        }
        
        return queryBlogAndReplyList;
    }

    /**
     * 根据浏览数查询排行
     *
     * @param page 分页参数
     * @return List<QueryBlogAndReply>
     */
    public List<QueryBlogAndReply> getBlogBlogListByView(PageEntity page)
            throws Exception {
        return blogBlogDao.getBlogBlogListByView(page);
    }

    /**
     * 后台查询博文
     *
     * @param page
     * @return List <BlogBlog>
     */
    public List<BlogBlog> getAdminBlogBlogList(BlogBlog blogBlog, PageEntity page)
            throws Exception {
        return blogBlogDao.getAdminBlogBlogList(blogBlog, page);
    }

    /**
     * 后台置顶博文
     *
     * @param id
     * @return
     */
    public String updateBlogBlogByTop(Long id) throws Exception {
        Long num = blogBlogDao.updateBlogBlogByTop(id);
        if (num == 1) {
            return SnsConstants.SUCCESS;// 返回成功
        } else {
            return SnsConstants.FALSE;// 返回失败
        }
    }

    /**
     * 后台取消置顶
     *
     * @param id 博客id
     * @return
     */
    public String updateCancelBlogBlogByTop(Long id) throws Exception {
        Long num = blogBlogDao.updateCancelBlogBlogByTop(id);
        if (num == 1) {
            return SnsConstants.SUCCESS;// 返回成功
        } else {
            return SnsConstants.FALSE;// 返回失败
        }
    }

    /**
     * 后台添加文章分类
     *
     * @param artclassify 分类
     */
    public void addArticleClassify(Artclassify artclassify) throws Exception {
        // 删除缓存
        memCache.remove(MemConstans.BLOG_CLASSIFY);
        memCache.remove(MemConstans.DISGROUP_CLASSIFY);
        blogBlogDao.addArticleClassify(artclassify);
    }

    /**
     * 根据id删除文章分类
     *
     * @param artId 文章分类id
     * @return
     */
    public String deleteArticleClassifyById(Long artId) throws Exception {
        // 删除缓存
        memCache.remove(MemConstans.BLOG_CLASSIFY);
        memCache.remove(MemConstans.DISGROUP_CLASSIFY);
        Long num = blogBlogDao.deleteArticleClassifyById(artId);
        if (num == 1) {
            return SnsConstants.SUCCESS;// 返回成功
        } else {
            return SnsConstants.FALSE;// 返回失败
        }
    }

    /**
     * 根据id获得文章分类
     *
     * @param artId 文章分类id
     * @return Artclassify
     */
    public Artclassify getArticleClassifyById(Long artId) throws Exception {
        return blogBlogDao.getArticleClassifyById(artId);
    }

    /**
     * 修改文章分类
     *
     * @param artclassify
     */
    public void updateArticleClassify(Artclassify artclassify) throws Exception {
        // 删除缓存
        memCache.remove(MemConstans.BLOG_CLASSIFY);
        memCache.remove(MemConstans.DISGROUP_CLASSIFY);
        blogBlogDao.updateArticleClassify(artclassify);
    }

    /**
     * 搜索博文
     *
     * @param blogBlog 标题 作者 关键词
     * @param page     分页参数
     * @return
     */
    public List<QueryBlogAndReply> getBlogBlogResult(BlogBlog blogBlog, PageEntity page)
            throws Exception {
        return blogBlogDao.getBlogBlogResult(blogBlog, page);
    }

    /**
     * 查看博文博文数+1
     *
     * @param blogId 博文id
     * @param count  +1
     */
    public void updateBlogViewCount(Long blogId, int count) throws Exception {
        blogBlogDao.updateBlogViewCount(blogId, count);
    }

    /**
     * 活跃度 财经采纳+500回复+10，浏览+1
     *
     * @param blogBlog
     */
    public void updateBlogActivity(BlogBlog blogBlog) throws Exception {
        blogBlogDao.updateBlogActivity(blogBlog);
    }

    /**
     * 查询分类下的博文列表
     *
     * @param ClassifyId 分类id
     * @param page       分页参数
     * @return
     */
    public List<QueryBlogAndReply> getArticleListByClassifyId(int classifyId,
                                                              PageEntity page) throws Exception {
        return blogBlogDao.getArticleListByClassifyId(classifyId, page);
    }

    /**
     * 查询文章
     *
     * @param id 博文id
     * @return
     */
    public BlogBlog getAdminBlogBlogById(Long id) throws Exception {
        return blogBlogDao.getAdminBlogBlogById(id);
    }

    /**
     * 获得单个博文详情
     *
     * @param blogBlog 博文id 用户id
     * @return BlogBlog
     */
    public BlogBlog getBlogBlogDetailById(Long blogId) throws Exception {
        BlogBlog blogBlog = blogBlogDao.getBlogBlogDetailById(blogId);
        if (blogBlog != null) {
            SnsUserExpandDto userExpandDto = snsUserService.getUserExpandByCusId(blogBlog.getCusId());
            if (userExpandDto != null) {
                blogBlog.setUserExpandDto(userExpandDto);
            }
        }
        return blogBlog;

    }

    /**
     * 删除回复评论数-1
     *
     * @param blogId
     */
    public void delBlogReplycount(Long blogId) throws Exception {
        blogBlogDao.delBlogReplycount(blogId);

    }

    /**
     * 查询 此条博文是否是本人
     *
     * @param blogBlog 博文id 和用户
     * @return
     */
    public Integer getBlogIsMine(BlogBlog blogBlog) throws Exception {
        return blogBlogDao.getBlogIsMine(blogBlog);
    }

    /**
     * 被财经选中 改变状态 活跃度增加
     *
     * @param BlogId 博文id
     * @return
     */
    public String updateBlogBlogIsBest(BlogBlog blogBlog) throws Exception {
        Long num = blogBlogDao.updateBlogBlogIsBest(blogBlog);
        if (num == 1) {
            return SnsConstants.SUCCESS;
        } else {
            return SnsConstants.FALSE;
        }
    }

    /**
     * 查出这个博文id之后的博文行数和最大博文id
     *
     * @param blogId 博文id
     * @return Map<String, Object> 返回两个参数一个是博文的行数一个是最大的博文id
     * @throws Exception
     */
    public Map<String, Object> getBlogBlogCountAfterId(Long blogId) throws Exception {
        return blogBlogDao.getBlogBlogCountAfterId(blogId);
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
    public List<BlogBlog> getBlogBlogByPageQuery(Long beginRow, Long pageSize,
                                                 Long minBlogBlogId, Long maxBlogBlogId) throws Exception {
        return blogBlogDao.getBlogBlogByPageQuery(beginRow, pageSize, minBlogBlogId,
                maxBlogBlogId);
    }

    /**
     * lucene方法 查询传入博文id 查询博文
     *
     * @param blogIds 博文id的list
     * @return List<BlogBlog>
     * @throws Exception
     */
    public List<QueryBlogAndReply> getLuceneBlogBlogByIds(List<Long> blogIds)
            throws Exception {
        return blogBlogDao.getLuceneBlogBlogByIds(blogIds);
    }

    /**
     * 回复是更新时间，最后回复人
     *
     * @param blogBlog
     */
    public void updateBlogBlogLastReply(BlogBlog blogBlog) throws Exception {
        blogBlogDao.updateBlogBlogLastReply(blogBlog);
    }

    /**
     * 个人首页查询我的博文
     *
     * @param cusId
     * @return
     */
    public List<BlogBlog> getPersonMyArticleList(Long cusId, int num) throws Exception {
        List<BlogBlog> blogBlogList = blogBlogDao.getPersonMyArticleList(cusId, num);
        String cusIds="";
        //循环博客查询showname
        if (ObjectUtils.isNotNull(blogBlogList)) {
            for (BlogBlog blogBlog : blogBlogList) {
            	cusIds+=blogBlog.getCusId()+",";
            }
            //通过用户id查询出customer的map
            Map<String, SnsUserExpandDto> map = snsUserService.getUserExpandsByCusId(cusIds);
            if(ObjectUtils.isNotNull(map)){
                for (BlogBlog blogBlog : blogBlogList) {
                    SnsUserExpandDto customer = map.get(blogBlog.getCusId().toString());
                    if(ObjectUtils.isNotNull(customer)){
                        blogBlog.setUserExpandDto(customer);
                        blogBlog.setShowName(customer.getShowname());
                    }
                }
            }
        }
        return blogBlogList;
    }

    /**
     * 同步财经文章
     *
     * @param blogBlog param 参数
     * @throws Exception
     */
    public void createFinanceArticle(String url, BlogBlog Blogblog) throws Exception {
        Map<String, String> params = new HashMap<String, String>();
        params.put("blogBlog", Blogblog + "");
        HttpUtil.doPost(url, params);
    }

    /**
     * 软删除文章分类
     *
     * @param artId
     * @return
     * @throws Exception
     */
    public String updateArticleClassifyById(Long artId) throws Exception {
        // 删除缓存
        memCache.remove(MemConstans.BLOG_CLASSIFY);
        memCache.remove(MemConstans.DISGROUP_CLASSIFY);
        Long num = blogBlogDao.updateArticleClassifyById(artId);
        if (num == 1) {
            return SnsConstants.SUCCESS;// 返回成功
        } else {
            return SnsConstants.FALSE;// 返回失败
        }
    }

    /**
     * 同步财经文章修改标示
     *
     * @param blogId
     * @return
     */
    public Long updateFinBlogFlagByBlogId(Long blogId) throws Exception {
        return blogBlogDao.updateFinBlogFlagByBlogId(blogId);
    }

    public String getblogBlogListCusId(List<QueryBlogAndReply> blogBlogList) {// 获得用户ids
        String ids = "";
        if (blogBlogList != null && blogBlogList.size() > 0) {
            for (QueryBlogAndReply queryBlogAndReply : blogBlogList) {
                ids += queryBlogAndReply.getCusId() + ",";
            }
        }
        return ids;
    }
}