package com.atdld.os.sns.dao.discuss;

import java.util.List;

import com.atdld.os.core.entity.PageEntity;
import com.atdld.os.sns.entity.discuss.Artclassify;
import com.atdld.os.sns.entity.discuss.DisArticle;
import com.atdld.os.sns.entity.discuss.DisArticleFavor;
import com.atdld.os.sns.entity.discuss.DisArticleReply;
import com.atdld.os.sns.entity.discuss.DisLookArticle;
import com.atdld.os.sns.entity.discuss.QueryDisArtAndRep;

/**
 * @author :
 * @ClassName com.atdld.os.sns.dao.discuss.DisArticleDao
 * @description 文章Dao
 * @Create Date : 2013-12-11 下午1:26:39
 */
public interface DisArticleDao {
    /**
     * 添加小组文章
     *
     * @param disArticle 文章
     */
    public Long addDisArticle(DisArticle disArticle);

    /**
     * 分页查询小组文章列表
     *
     * @param groupid    小组ID
     * @param pageEntity 分页参数
     * @return List<DisArticle> 小组文章 list
     */
    public List<DisArticle> queryDisArticleList(Long groupId, PageEntity pageEntity);

    /**
     * 查看文章详情
     *
     * @param disArticle 文章的ID
     * @return DisArticle对象
     */
    public DisArticle queryDisArticleDetail(DisArticle disArticle);

    /**
     * 添加文章回复
     *
     * @param disArticleReply 回复内容
     */
    public Long addDisArticleReply(DisArticleReply disArticleReply);

    /**
     * 删除回复
     *
     * @param disArticleReply 文章id和回复id
     * @return
     */
    public Long deleteDisArticleReply(DisArticleReply disArticleReply);

    /**
     * 查询 此条回复是否是本人
     *
     * @param disArticleReply 回复id 和用户id
     * @return
     */
    public Integer getDisArticleReplyIsMine(DisArticleReply disArticleReply);

    /**
     * 删除回复 文章的回复数减1
     *
     * @param articleId 文章id
     */
    public void updateDisArticleReNum(Long articleId);

    /**
     * 查询回复列表
     *
     * @param disArticle
     * @param pageEntity 分页参数
     * @return List<DisArticleReply> 回复列表list
     */
    public List<DisArticleReply> queryDisArticleReplyList(DisArticle disArticle, PageEntity pageEntity);

    /**
     * 查询用户浏览记录
     *
     * @param disLookArticle 博文或 小组文章id 类型id
     * @return List<DisLookArticle> 浏览记录 list
     */
    public List<DisLookArticle> queryDisLookArticleRecord(DisLookArticle disLookArticle);

    /**
     * 更新文章查看数
     *
     * @param articleId 文章id
     * @param count     +1
     * @return
     */
    public Long updateDisArticleViewCount(Long articleId, int count);

    /**
     * 添加浏览记录
     *
     * @param disLookArticle
     */
    public void addLookDisArticle(DisLookArticle disLookArticle);

    /**
     * 判断用户是否存在浏览记录，如果存在只需更新时间即可
     *
     * @param disLookArticle
     * @return
     */
    public DisLookArticle queryLookArticleRecord(DisLookArticle disLookArticle);

    /**
     * 更新用户时间
     *
     * @param disLookArticle
     * @return
     */
    public Long updateCusLookRecord(DisLookArticle disLookArticle);

    /**
     * 查看我的小组文章
     *
     * @param disArticle 用户的id
     * @return List<DisArticle> 我的文章list
     */
    public List<DisArticle> queryMyDisArticle(DisArticle disArticle);

    /**
     * 添加回复时统计数量数量加+1
     *
     * @param articleId 文章id
     * @param count     +1
     */
    public void updateDisArticleReplyCount(Long articleId, int count);

    /**
     * 删除小组文章，只有管理里员本人能删除
     *
     * @param dis_articleid 文章Id
     */
    public void deleteDisArticleByid(int dis_articleid);

    /**
     * 查询博客文章分类
     *
     * @return List<Artclassify> 文章分类list
     */
    public List<Artclassify> queryArtclassifyList();

    /**
     * 查询小组文章分类
     *
     * @return List<Artclassify>
     */
    public List<Artclassify> querydisArtcicleList();

    /**
     * 首页查询我的小组文章
     *
     * @param cusId
     * @return
     */
    public List<QueryDisArtAndRep> queryMyArticleById(Long cusId, PageEntity page);

    /**
     * 删除我的小组文章
     *
     * @param disArticle 小组id和文章id
     * @return
     */
    public Long deleteMyArticleById(DisArticle disArticle);

    /**
     * 获得我的文章详情
     *
     * @param disArticle 小组文章id和小组id
     * @return
     */
    public DisArticle queryMyArticleDetail(DisArticle disArticle);

    /**
     * 修改我的小组文章
     *
     * @param disArticle 文章内容
     * @return
     */
    public Long updateMyArticle(DisArticle disArticle);

    /**
     * 置顶小组文章
     *
     * @param id 小组文章id
     * @return
     */
    public Long updateDisArticleByTop(Long articleId);

    /**
     * 取消置顶
     *
     * @param id 小组文章id
     * @return
     */
    public Long updateCancelDisArticleByTop(Long articleId);

    /**
     * 查询文章分类是否存在
     *
     * @param artclassify
     * @return
     */
    public Integer queryDisArticleIsExsit(Artclassify artclassify);

    /**
     * 更新用户最后时间和发表人
     *
     * @param disArticle
     */
    public void updateArticleLastReply(DisArticle disArticle);

    /**
     * 查询分类 下的小组文章
     *
     * @param classifyId
     * @return
     */
    public List<DisArticle> queryClassifyDisArticleById(Long classifyId, PageEntity page);

    /**
     * 查询文章浏览人数
     *
     * @param disLookArticle
     * @return Integer
     */
    public Integer queryVisitArticleNum(DisLookArticle disLookArticle);

    /**
     * 删除全部回复
     *
     * @param articleId 文章id
     * @return
     */
    public Long deleteReplyArticleById(Long articleId);

    /**
     * 设置文章是否禁言
     *
     * @param disArticle
     */
    public Long updateDisArticleStatus(DisArticle disArticle);

    /**
     * 修改話題喜欢数
     *
     * @param articleId
     * @param count     +1
     * @return
     */
    public Long updateDisArticleFavorCount(Long articleId, int count);

    /**
     * 修改話題喜欢数
     *
     * @param articleId
     * @param count     -1
     * @return
     */
    public Long deleteDisArticleFavorCount(Long articleId, int count);

    /**
     * 添加推荐喜欢列表
     *
     * @param disArticleFavor
     * @return
     */
    public Long addDisArticleFavorAndRecom(DisArticleFavor disArticleFavor);

    /**
     * 删除喜欢推荐列表
     *
     * @param disArticleFavor
     * @return
     */
    public Long deleteDisArticleFavorAndRecom(DisArticleFavor disArticleFavor);

    /**
     * 修改推荐数
     *
     * @param articleId
     * @param count     +1
     * @return
     */
    public Long updateDisArticleRecommendCount(Long articleId, int count);

    /**
     * 判断是否推荐收藏
     *
     * @param disArticleFavor
     * @return
     */
    public Integer queryDisArticleIsFavorOrRecom(DisArticleFavor disArticleFavor);

    /**
     * 查询喜欢推荐
     *
     * @param disArticleFavor
     * @return
     */
    public List<DisArticleFavor> queryDisArticleFavorAndRecom(DisArticleFavor disArticleFavor, PageEntity page);

    /**
     * 首页右侧查询话题
     *
     * @return
     */
    public List<DisArticle> queryDisArticleListForHome();
    
    /**
     * 根据条件查询小组话题
     * */
    public List<DisArticle> queryDisArticleByCondition(DisArticle disArticle,PageEntity page);
}
