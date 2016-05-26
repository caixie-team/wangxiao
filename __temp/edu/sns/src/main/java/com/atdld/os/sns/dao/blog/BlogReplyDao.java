package com.atdld.os.sns.dao.blog;

import java.util.List;

import com.atdld.os.sns.entity.blog.BlogReply;
import com.atdld.os.core.entity.PageEntity;

/**
 * @author :
 * @ClassName com.atdld.os.sns.dao.blog.BlogReplyDao
 * @description 博客回复
 * @Create Date : 2013-12-30 下午1:12:11
 */
public interface BlogReplyDao {

    /**
     * 添加BlogReply
     *
     * @param blogReply 要添加的BlogReply
     * @return id
     */
    public Long addBlogReply(BlogReply blogReply);

    /**
     * 删除博文回复
     *
     * @param blogReply 博文id 和回复id
     * @return
     */
    public Long deleteBlogReplyById(BlogReply blogReply);

    /**
     * 修改BlogReply
     *
     * @param blogReply 要修改的BlogReply
     */
    public void updateBlogReply(BlogReply blogReply);

    /**
     * 根据id获取单个BlogReply对象
     *
     * @param id 要查询的id
     * @return BlogReply
     */
    public BlogReply getBlogReplyById(int id);

    /**
     * 根据条件获取BlogReply列表
     *
     * @param blogReply 查询条件
     * @return List<BlogReply>
     */
    public List<BlogReply> getBlogReplyList(BlogReply blogReply);

    /**
     * 查询回复列表
     *
     * @param blogId 博客id
     * @param page   分页参数
     * @return List<BlogReply>
     */
    public List<BlogReply> getBlogReplyByBlogId(Long blogId, PageEntity page);

    /**
     * 每回复文章一次回复数自动加一
     *
     * @param blogId 博文id
     * @param count  +1
     */
    public void updateBlogReplyCount(Long blogId, int count);

    /**
     * 根据博文id删除博文回复
     *
     * @param blogId
     */
    public void deleteBlogReplyByBlogId(Long blogId);

    /**
     * 删除博文回复判断是否是本人
     *
     * @param blogReply 回复id 和 cusId
     * @return
     */
    public Integer getBlogReplyIsMine(BlogReply blogReply);
}