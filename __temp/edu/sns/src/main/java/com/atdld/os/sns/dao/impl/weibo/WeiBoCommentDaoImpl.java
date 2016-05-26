package com.atdld.os.sns.dao.impl.weibo;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.atdld.os.core.dao.impl.common.GenericDaoImpl;
import com.atdld.os.sns.dao.weibo.WeiBoCommentDao;
import com.atdld.os.core.entity.PageEntity;
import com.atdld.os.sns.entity.weibo.Comment;
import com.atdld.os.sns.entity.weibo.QueryComment;

/**
 * @author
 * @ClassName WeiBoCommentDaoImpl
 * @package com.atdld.open.sns.dao.impl.weibo
 * @description
 * @Create Date: 2013-12-10 下午4:21:31
 */
@Repository("weiBoCommentDao")
public class WeiBoCommentDaoImpl extends GenericDaoImpl implements WeiBoCommentDao {
    /**
     * 添加评论
     *
     * @param comment 评论实体
     */
    public void addComment(Comment comment) {
        this.insert("WeiBoCommentMapper.addComment", comment);// 添加评论
    }

    /**
     * 查询该微博下的评论
     *
     * @param comment 评论实体
     * @param page    分页参数
     * @return List<QueryComment> 该微博 下评论的list
     */
    public List<QueryComment> queryCommentByWbId(Comment comment, PageEntity page) {
        // return this.selectList("WeiBoCommentMapper.queryCommentByWbId",
        // comment);//查询该微博下的评论
        return this.queryForListPage("WeiBoCommentMapper.queryCommentByWbId", comment, page);
    }

    /**
     * 通过评论id查询评论
     *
     * @param comment 评论实体
     * @return QueryComment 评论
     */
    public QueryComment queryCommentById(Comment comment) {
        List<QueryComment> queryCommentList = this.selectList("WeiBoCommentMapper.queryCommentById", comment);// 通过评论id查询评论
        if (queryCommentList != null && queryCommentList.size() > 0) {
            return queryCommentList.get(0);
        } else {
            return null;
        }
    }

    /**
     * 删除评论
     *
     * @param comment 评论实体
     */
    public void delCommentByWbId(Comment comment) {
        this.delete("WeiBoCommentMapper.delCommentByweiboId", comment);// 删除评论
    }

    /**
     * 根据id删除评论
     *
     * @param comment 评论实体传入微博id
     */
    public Long delCommentById(Comment comment) {
        return this.delete("WeiBoCommentMapper.delCommentById", comment);// 删除评论

    }

}
