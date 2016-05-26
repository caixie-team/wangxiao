package com.atdld.os.sns.service.weibo;

import java.util.List;

import com.atdld.os.core.entity.PageEntity;
import com.atdld.os.sns.entity.weibo.Comment;
import com.atdld.os.sns.entity.weibo.QueryComment;

/**
 * @author
 * @ClassName WeiBoCommentService
 * @package com.atdld.open.sns.service.weibo
 * @description 微博评论的接口
 * @Create Date: 2013-12-10 下午4:03:50
 */
public interface WeiBoCommentService {
    /**
     * 添加微博的评论
     *
     * @param comment 评论实体
     * @throws Exception
     */
    public String addComment(Comment comment) throws Exception;

    /**
     * 查询该微博评论
     *
     * @param comment 评论实体
     * @param page    分页参数
     * @return List<QueryComment> 评论list
     * @throws Exception
     */
    public List<QueryComment> queryCommentByWbId(Comment comment, PageEntity page) throws Exception;

    /**
     * 删除微博评论
     *
     * @param comment 传入id
     * @throws Exception
     */
    public String delCommentById(Comment comment) throws Exception;
}
