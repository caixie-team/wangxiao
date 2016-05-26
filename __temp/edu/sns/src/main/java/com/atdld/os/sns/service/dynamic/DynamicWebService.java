package com.atdld.os.sns.service.dynamic;

import java.util.List;

import com.atdld.os.core.entity.PageEntity;
import com.atdld.os.sns.entity.blog.BlogBlog;
import com.atdld.os.sns.entity.blog.BlogReply;
import com.atdld.os.sns.entity.discuss.DisArticle;
import com.atdld.os.sns.entity.discuss.DisArticleReply;
import com.atdld.os.sns.entity.discuss.DisGroup;
import com.atdld.os.sns.entity.discuss.DisMember;
import com.atdld.os.sns.entity.dynamic.DynamicWeb;
import com.atdld.os.sns.entity.friend.Friend;
import com.atdld.os.sns.entity.suggest.SugSuggest;
import com.atdld.os.sns.entity.weibo.Comment;
import com.atdld.os.sns.entity.weibo.WeiBo;

/**
 * @author :
 * @ClassName com.atdld.os.sns.service.dynamic.DynamicWebService
 * @description
 * @Create Date : 2014-1-11 下午2:25:15
 */
public interface DynamicWebService {
    /**
     * 添加小组文章动态
     *
     * @param disArticle 文章
     * @throws Exception
     */
    public void addDynamicWebForDisArticle(DisArticle disArticle) throws Exception;

    /**
     * 添加回复小组文章动态
     *
     * @param disArticleReply
     * @throws Exception
     */
    public void replyDynamicWebForDisArticle(DisArticleReply disArticleReply)
            throws Exception;

    /**
     * 添加博文动态
     *
     * @param blogBlog
     * @throws Exception
     */
    public void addDynamicWebForBlogBlog(BlogBlog blogBlog) throws Exception;

    /**
     * 添加博文回复动态
     *
     * @param blogReply
     * @throws Exception
     */
    public void addDynamicWebForBlogBlogreply(BlogReply blogReply) throws Exception;

    /**
     * 添加微博动态
     *
     * @param weibo
     * @throws Exception
     */
    public void addDynamicWebForWeiBo(WeiBo weibo) throws Exception;

    /**
     * 添加回复微博动态
     *
     * @param comment
     * @throws Exception
     */
    public void replyDynamicWebForWeiBo(Comment comment) throws Exception;

    /**
     * 申请加入小组动态
     *
     * @param disMember
     * @throws Exception
     */
    public void addDynamicWebForDisMember(DisMember disMember) throws Exception;

    /**
     * 小组创建通过动态
     *
     * @param disGroup
     * @throws Exception
     */
    public void addDynamicWebForDisGroup(DisGroup disGroup) throws Exception;

    /**
     * 关注动态
     *
     * @param weibo
     * @throws Exception
     */
    public void addDynamicWebForFriends(Friend friend) throws Exception;


    /**
     * 添加发布建议动态
     *
     * @param sugSuggest
     * @throws Exception
     */
    public void addDynamicWebForSuggest(SugSuggest sugSuggest) throws Exception;

    /**
     * 添加推荐动态建议
     *
     * @param sugSuggestRecommend
     */
    public void addDynamicWebForSuggestRecommend(Long suggestId)
            throws Exception;

    /**
     * 添加学习动态接口
     *
     * @param map
     * @throws Exception
     */
    public void addDynamicWebForLearning(DynamicWeb dynamicWeb) throws Exception;

    /**
     * 查看全站动态
     *
     * @return List<DynamicWeb> 动态列表list
     * @throws Exception
     */
    public List<DynamicWeb> queryDynamicWebList(Long cusId, PageEntity page)
            throws Exception;

    /**
     * 显示小组组动态，只有小组成员才能看到
     *
     * @param cusId 用户Id
     * @return List<DynamicWeb> 1发表小组文章 2回复小组文章 3加入小组 list
     * @throws Exception
     */
    public List<DynamicWeb> queryDynamicWebDisGroup(List<Long> ids, PageEntity page)
            throws Exception;

    /**
     * 通过type查询动态
     *
     * @param page 分页参数
     * @return
     */
    public List<DynamicWeb> queryDynamicWebByTP(String type, PageEntity page)
            throws Exception;

    /**
     * 查看加好友的动态
     *
     * @param cus_id 用户Id
     * @return List<DynamicWeb> 加好友Friend list
     * @throws Exception
     */
    public List<DynamicWeb> queryDynamicWebFriend(Long cusId, PageEntity page)
            throws Exception;

    /**
     * 查看好友动态 1.发微博 2.回微博 3.加关注
     *
     * @param cusId 当前用户id
     * @param page  分页参数
     * @return
     */
    public List<DynamicWeb> queryDynamicWebJunZiHui(Long cusId, PageEntity page)
            throws Exception;


    /**
     * 公共删除动态方法 1微博 2博文 3小组文章 4建议
     *
     * @param dynamicWeb
     * @return
     * @throws Exception
     */
    public Long deleteDynamicWebByCondition(DynamicWeb dynamicWeb) throws Exception;

    /**
     * 公共删除 回复动态 1 回复微博 2回复博文 3回复小组文章 4回复建议
     *
     * @param dynamicWeb
     * @return
     * @throws Exception
     */
    public Long deleteReplyDynamicByCondition(DynamicWeb dynamicWeb) throws Exception;
    
    
    
    /**
     * 设置是否关注的FLAG
     *
     * @param dynamicWebList
     * @return
     * @throws Exception
     */
    public List<DynamicWeb> setFriendFlag(List<DynamicWeb> dynamicWebList,Long userId) throws Exception;
    
}
