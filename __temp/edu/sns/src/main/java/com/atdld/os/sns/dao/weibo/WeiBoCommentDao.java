package com.atdld.os.sns.dao.weibo;

import java.util.List;

import com.atdld.os.core.entity.PageEntity;
import com.atdld.os.sns.entity.weibo.Comment;
import com.atdld.os.sns.entity.weibo.QueryComment;

/**
 * @author
 * @ClassName WeiBoCommentDao
 * @package com.atdld.open.sns.dao.weibo
 * @description
 * @Create Date: 2013-12-10 下午4:19:56
 */

public interface WeiBoCommentDao {
    /**
     * 添加评论
     *
     * @param comment 评论实体
     */
    public void addComment(Comment comment);

    /**
     * 查询该微博下的评论
     *
     * @param comment 评论实体
     * @param page    分页参数
     * @return List<QueryComment> 该微博 下评论的list
     */
    public List<QueryComment> queryCommentByWbId(Comment comment, PageEntity page);

    /**
     * 查询该微博下的评论
     *
     * @param comment 评论实体
     * @return QueryComment 评论
     */
    public QueryComment queryCommentById(Comment comment);

    /**
     * 根据微博id删除评论
     *
     * @param comment 评论实体传入微博id
     */
    public void delCommentByWbId(Comment comment);

    /**
     * 根据id删除评论
     *
     * @param comment 评论实体传入微博id
     */
    public Long delCommentById(Comment comment);
}
