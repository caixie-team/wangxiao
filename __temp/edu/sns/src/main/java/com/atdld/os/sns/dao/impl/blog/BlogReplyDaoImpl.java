package com.atdld.os.sns.dao.impl.blog;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.atdld.os.sns.dao.blog.BlogReplyDao;
import com.atdld.os.core.dao.impl.common.GenericDaoImpl;
import com.atdld.os.sns.entity.blog.BlogReply;
import com.atdld.os.core.entity.PageEntity;

/**
 * @author :
 * @ClassName com.atdld.os.sns.dao.impl.blog.BlogReplyDaoImpl
 * @description 博客回复
 * @Create Date : 2013-12-30 下午2:37:04
 */
@Repository("blogReplyDao")
public class BlogReplyDaoImpl extends GenericDaoImpl implements BlogReplyDao {
    /**
     * 添加BlogReply
     *
     * @param blogReply 要添加的BlogReply
     * @return id
     */
    public Long addBlogReply(BlogReply blogReply) {
        return this.insert("BlogReplyMapper.createBlogReply", blogReply);
    }

    /**
     * 删除博文回复
     *
     * @param blogReply 博文id 和回复id
     * @return
     */
    public Long deleteBlogReplyById(BlogReply blogReply) {
        return this.delete("BlogReplyMapper.deleteBlogReplyById", blogReply);
    }

    /**
     * 修改BlogReply
     *
     * @param blogReply 要修改的BlogReply
     */
    public void updateBlogReply(BlogReply blogReply) {
        this.update("BlogReplyMapper.updateBlogReply", blogReply);
    }

    /**
     * 根据id获取单个BlogReply对象
     *
     * @param id 要查询的id
     * @return BlogReply
     */
    public BlogReply getBlogReplyById(int id) {
        List<BlogReply> blogReplyList = this.selectList("BlogReplyMapper.getBlogReplyById", id);
        if (blogReplyList != null && blogReplyList.size() > 0) {
            return blogReplyList.get(0);
        }
        return null;
    }

    /**
     * 根据条件获取BlogReply列表
     *
     * @param blogReply 查询条件
     * @return List<BlogReply>
     */
    public List<BlogReply> getBlogReplyList(BlogReply blogReply) {
        return this.selectList("BlogReplyMapper.getBlogReplyList", blogReply);
    }

    /**
     * 查询回复列表
     *
     * @param blogId 博客id
     * @param page   分页参数
     * @return List<BlogReply>
     */
    public List<BlogReply> getBlogReplyByBlogId(Long blogId, PageEntity page) {
        return this.queryForListPage("BlogReplyMapper.getBlogReplyByBlogId", blogId, page);
    }

    /**
     * 每回复文章一次回复数自动加一
     *
     * @param blogId 博文id
     * @param count  +1
     */
    public void updateBlogReplyCount(Long blogId, int count) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", blogId);
        map.put("count", count);
        this.update("BlogReplyMapper.updateBlogReplyCount", map);
    }

    /**
     * 根据博文id删除博文回复
     *
     * @param blogId
     */
    public void deleteBlogReplyByBlogId(Long blogId) {
        this.delete("BlogReplyMapper.deleteBlogReplyByBlogId", blogId);

    }

    /**
     * 删除博文回复判断是否是本人
     *
     * @param blogReply 回复id 和 cusId
     * @return
     */
    public Integer getBlogReplyIsMine(BlogReply blogReply) {
        List<Integer> integerList = this.selectList("BlogReplyMapper.getBlogReplyIsMine", blogReply);
        if (integerList != null && integerList.size() > 0) {
            return integerList.get(0);
        }
        return 0;
    }
}
