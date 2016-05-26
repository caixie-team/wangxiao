package com.atdld.os.sns.service.blog;

import java.util.List;

import com.atdld.os.sns.entity.blog.BlogReply;
import com.atdld.os.core.entity.PageEntity;

/**
 * BlogReply管理接口 User:  Date: 2013-12-26
 */
public interface BlogReplyService {

    /**
     * 添加BlogReply
     *
     * @param blogReply 要添加的BlogReply
     * @return id
     */
    public String addBlogReply(BlogReply blogReply) throws Exception;

    /**
     * 删除博文回复
     *
     * @param blogReply 博文id 和回复id
     * @return
     */
    public String deleteBlogReplyById(BlogReply blogReply) throws Exception;

    /**
     * 修改BlogReply
     *
     * @param blogReply 要修改的BlogReply
     */
    public void updateBlogReply(BlogReply blogReply) throws Exception;

    /**
     * 根据id获取单个BlogReply对象
     *
     * @param id 要查询的id
     * @return BlogReply
     */
    public BlogReply getBlogReplyById(int id) throws Exception;

    /**
     * 根据条件获取BlogReply列表
     *
     * @param blogReply 查询条件
     * @return List<BlogReply>
     */
    public List<BlogReply> getBlogReplyList(BlogReply blogReply) throws Exception;

    /**
     * 查询回复列表
     *
     * @param blogId 博客id
     * @param page   分页参数
     * @return List<BlogReply>
     */
    public List<BlogReply> getBlogReplyByBlogId(Long blogId, PageEntity page) throws Exception;

    /**
     * 更新回复数+1
     *
     * @param blogId 博文id
     * @param count  +1
     * @throws Exception
     */
    public void updateBlogReplyCount(Long blogId, int count) throws Exception;

    /**
     * 根据博文id删除博文
     *
     * @param blogId
     */
    public void deleteBlogReplyByBlogId(Long blogId) throws Exception;

    /**
     * 删除博文回复判断是否是本人
     *
     * @param blogReply 回复id 和 cusId
     * @return
     */
    public Integer getBlogReplyIsMine(BlogReply blogReply) throws Exception;

    ;
}