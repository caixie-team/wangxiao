package com.atdld.os.sns.service.discuss;

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
 * @ClassName com.atdld.os.sns.service.discuss.DisArticleServcie
 * @description 文章Service
 * @Create Date : 2013-12-11 下午2:07:16
 */
public interface DisArticleService {
    /**
     * 添加小组文章
     *
     * @param disArticle 文章
     * @throws Exception
     */
    public String addDisArticle(DisArticle disArticle) throws Exception;

    /**
     * 分页查询小组文章列表
     *
     * @param groupid    小组ID
     * @param pageEntity 分页参数
     * @return List<DisArticle> 小组文章 list
     * @throws Exception
     */
    public List<DisArticle> queryDisArticleList(Long groupId, PageEntity pageEntity) throws Exception;

    /**
     * 查看文章详情
     *
     * @param disArticle 文章的ID
     * @return DisArticle对象
     * @throws Exception
     */
    public DisArticle queryDisArticleDetail(DisArticle disArticle) throws Exception;

    /**
     * 添加文章回复
     *
     * @param disArticleReply 回复内容
     * @throws Exception
     */
    public String addDisArticleReply(DisArticleReply disArticleReply) throws Exception;

    /**
     * 删除回复
     *
     * @param disArticleReply 文章id和回复id
     * @return
     */
    public String deleteDisArticleReply(DisArticleReply disArticleReply) throws Exception;

    /**
     * 删除回复 文章的回复数减1
     *
     * @param articleId 文章id
     */
    public void updateDisArticleReNum(Long articleId) throws Exception;

    /**
     * 查询 此条回复是否是本人
     *
     * @param disArticleReply 回复id 和用户id
     * @return
     */
    public Integer getDisArticleReplyIsMine(DisArticleReply disArticleReply) throws Exception;

    /**
     * 查询回复列表
     *
     * @param disArticle 文章id
     * @param pageEntity 分页参数
     * @return List<DisArticleReply> 回复列表list
     * @throws Exception
     */
    public List<DisArticleReply> queryDisArticleReplyList(DisArticle disArticle, PageEntity pageEntity) throws Exception;

    /**
     * 查询用户浏览记录
     *
     * @param disLookArticle 博文或 小组文章id 类型id
     * @return List<DisLookArticle> 浏览记录 list
     */
    public List<DisLookArticle> queryDisLookArticleRecord(DisLookArticle disLookArticle) throws Exception;

    /**
     * 查看我的小组文章
     *
     * @param disArticle 用户的id
     * @return List<DisArticle> 小组文章list
     * @throws Exception
     */
    public List<DisArticle> queryMyDisArticle(DisArticle disArticle) throws Exception;

    /**
     * 查询文章分类
     *
     * @return List<Artclassify> 文章分类list
     */
    public List<Artclassify> queryArtclassifyList() throws Exception;

    /**
     * 首页查询我的小组文章
     *
     * @param cusId
     * @return
     */
    public List<QueryDisArtAndRep> queryMyArticleById(Long cusId, PageEntity page) throws Exception;

    /**
     * 更新文章查看数
     *
     * @param articleId 文章id
     * @param count     +1
     * @return
     */
    public Long updateDisArticleViewCount(Long articleId, int count) throws Exception;

    /**
     * 添加浏览记录
     *
     * @param disLookArticle
     */
    public void addLookDisArticle(DisLookArticle disLookArticle) throws Exception;

    /**
     * 判断用户是否存在浏览记录，如果存在只需更新时间即可
     *
     * @param disLookArticle
     * @return
     */
    public DisLookArticle queryLookArticleRecord(DisLookArticle disLookArticle) throws Exception;

    /**
     * 更新用户时间
     *
     * @param disLookArticle
     * @return
     */
    public String updateCusLookRecord(DisLookArticle disLookArticle) throws Exception;

    /**
     * 查询小组文章分类
     *
     * @return List<Artclassify>
     */
    public List<Artclassify> querydisArtcicleList() throws Exception;

    /**
     * 删除我的小组文章
     *
     * @param disArticle 小组id和文章id
     * @return
     */
    public String deleteMyArticleById(DisArticle disArticle) throws Exception;

    /**
     * 获得我的文章详情
     *
     * @param disArticle 小组文章id和小组id
     * @return
     */
    public DisArticle queryMyArticleDetail(DisArticle disArticle) throws Exception;

    /**
     * 修改我的小组文章
     *
     * @param disArticle 文章内容
     * @return
     */
    public String updateMyArticle(DisArticle disArticle) throws Exception;

    /**
     * 置顶小组文章
     *
     * @param id 小组文章id
     * @return
     */
    public String updateDisArticleByTop(Long articleId) throws Exception;

    /**
     * 取消置顶
     *
     * @param id 小组文章id
     * @return
     */
    public String updateCancelDisArticleByTop(Long articleId) throws Exception;

    /**
     * 查询文章分类是否存在
     *
     * @param artclassify
     * @return
     */
    public Integer queryDisArticleIsExsit(Artclassify artclassify) throws Exception;

    /**
     * 更新用户最后时间和发表人
     *
     * @param disArticle
     */
    public void updateArticleLastReply(DisArticle disArticle) throws Exception;

    /**
     * 查询分类 下的小组文章
     *
     * @param classifyId
     * @return
     */
    public List<DisArticle> queryClassifyDisArticleById(Long classifyId, PageEntity page) throws Exception;

    /**
     * 添加回复时统计数量数量加+1
     *
     * @param articleId 文章id
     * @param count     +1
     */
    public void updateDisArticleReplyCount(Long articleId, int count) throws Exception;

    /**
     * 查询文章浏览人数
     *
     * @param disLookArticle
     * @return
     * @throws Exception
     */
    public Integer queryVisitArticleNum(DisLookArticle disLookArticle) throws Exception;

    /**
     * 删除全部回复
     *
     * @param articleId
     * @return
     * @throws Exception
     */
    public Long deleteReplyArticleById(Long articleId) throws Exception;

    /**
     * 设置文章是否禁言
     *
     * @param disArticle
     * @return
     * @throws Exception
     */
    public String updateDisArticleStatus(DisArticle disArticle) throws Exception;

    /**
     * 修改話題喜欢数
     *
     * @param articleId
     * @param count     +1
     * @return
     */
    public Long updateDisArticleFavorCount(Long articleId, int count) throws Exception;

    /**
     * 修改話題喜欢数
     *
     * @param articleId
     * @param count     -1
     * @return
     */
    public Long deleteDisArticleFavorCount(Long articleId, int count) throws Exception;

    /**
     * 添加推荐喜欢列表
     *
     * @param disArticleFavor
     * @return
     */
    public String addDisArticleFavorAndRecom(DisArticleFavor disArticleFavor) throws Exception;

    /**
     * 修改推荐数
     *
     * @param articleId
     * @param count     +1
     * @return
     */
    public Long updateDisArticleRecommendCount(Long articleId, int count) throws Exception;

    /**
     * 判断是否推荐收藏
     *
     * @param disArticleFavor
     * @return
     */
    public Integer queryDisArticleIsFavorOrRecom(DisArticleFavor disArticleFavor) throws Exception;

    /**
     * 查询喜欢推荐
     *
     * @param disArticleFavor
     * @return
     */
    public List<DisArticleFavor> queryDisArticleFavorAndRecom(DisArticleFavor disArticleFavor, PageEntity page);

    /**
     * 删除喜欢推荐列表
     *
     * @param disArticleFavor
     * @return
     */
    public String deleteDisArticleFavorAndRecom(DisArticleFavor disArticleFavor) throws Exception;

    /**
     * 首页右侧查询话题
     *
     * @return
     * @throws Exception
     */
    public List<DisArticle> queryDisArticleListForHome() throws Exception;
    
    /**
     * 根据条件查询小组话题
     * */
    public List<DisArticle> queryDisArticleByCondition(DisArticle disArticle,PageEntity page);

}
