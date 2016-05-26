package com.atdld.os.sns.dao.impl.discuss;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.atdld.os.sns.dao.discuss.DisArticleDao;
import com.atdld.os.core.dao.impl.common.GenericDaoImpl;
import com.atdld.os.core.entity.PageEntity;
import com.atdld.os.core.util.ObjectUtils;
import com.atdld.os.sns.entity.discuss.Artclassify;
import com.atdld.os.sns.entity.discuss.DisArticle;
import com.atdld.os.sns.entity.discuss.DisArticleFavor;
import com.atdld.os.sns.entity.discuss.DisArticleReply;
import com.atdld.os.sns.entity.discuss.DisLookArticle;
import com.atdld.os.sns.entity.discuss.QueryDisArtAndRep;

/**
 * @author :
 * @ClassName com.atdld.os.sns.dao.impl.discuss.DisArticleDaoImpl
 * @description
 * @Create Date : 2013-12-11 下午1:41:43
 */
@Repository("disArticleDao")
public class DisArticleDaoImpl extends GenericDaoImpl implements DisArticleDao {
    /**
     * 添加小组文章
     *
     * @param disArticle 文章
     */
    public Long addDisArticle(DisArticle disArticle) {
        return this.insert("DisArticleMapper.addDisArticle", disArticle);
    }

    /**
     * 分页查询小组文章列表
     *
     * @param groupid    小组ID
     * @param pageEntity 分页参数
     * @return List<DisArticle> 小组文章 list
     */
    public List<DisArticle> queryDisArticleList(Long groupId, PageEntity pageEntity) {
        return this.queryForListPage("DisArticleMapper.querydisArticleList", groupId, pageEntity);
    }

    /**
     * 查看文章详情
     *
     * @param disArticle 文章的ID
     * @return DisArticle对象
     */
    public DisArticle queryDisArticleDetail(DisArticle disArticle) {
        List<DisArticle> disArticleList = this.selectList("DisArticleMapper.queryDisArticleDetail", disArticle);
        if (disArticleList != null && disArticleList.size() > 0) {
            return disArticleList.get(0);
        }
        return null;
    }

    /**
     * 查询回复列表
     *
     * @param disArticle
     * @param pageEntity 分页参数
     * @return List<DisArticleReply> 回复列表list
     */
    public List<DisArticleReply> queryDisArticleReplyList(DisArticle disArticle, PageEntity pageEntity) {
        return this.queryForListPage("DisArticleMapper.queryDisArticleReplyList", disArticle, pageEntity);
    }

    /**
     * 添加文章回复
     *
     * @param disArticleReply 回复内容
     */
    public Long addDisArticleReply(DisArticleReply disArticleReply) {
        return this.insert("DisArticleMapper.addDisArticleReply", disArticleReply);
    }

    /**
     * 查询用户浏览记录
     *
     * @param disLookArticle 博文或 小组文章id 类型id
     * @return List<DisLookArticle> 浏览记录 list
     */
    public List<DisLookArticle> queryDisLookArticleRecord(DisLookArticle disLookArticle) {
        return this.selectList("DisArticleMapper.queryDisLookArticleRecord", disLookArticle);
    }

    /**
     * 添加回复时统计数量数量加+1
     *
     * @param articleId 文章id
     * @param count     +1
     */
    public void updateDisArticleReplyCount(Long articleId, int count) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", articleId);
        map.put("count", count);
        this.update("DisArticleMapper.updateDisArticleReplyCount", map);
    }

    /**
     * 删除小组文章，只有管理里员本人能删除
     *
     * @param dis_articleid 文章Id
     */
    public void deleteDisArticleByid(int dis_articleid) {
        this.delete("DisArticleMapper.deleteDisArticleByid", dis_articleid);
    }

    /**
     * 查看我的小组文章
     *
     * @param disArticle 用户的id
     * @return List<DisArticle> 我的文章list
     */
    public List<DisArticle> queryMyDisArticle(DisArticle disArticle) {
        return this.selectList("DisArticleMapper.queryMyDisArticle", disArticle);
    }

    /**
     * 查询文章分类
     *
     * @return List<Artclassify> 文章分类list
     */
    public List<Artclassify> queryArtclassifyList() {
        return this.selectList("DisArticleMapper.queryArtclassifyList", null);
    }

    /**
     * 查询我的小组文章
     *
     * @param cusId
     * @return
     */
    public List<QueryDisArtAndRep> queryMyArticleById(Long cusId, PageEntity page) {
        return this.queryForListPage("DisArticleMapper.queryMyArticleById", cusId, page);
    }

    /**
     * 添加浏览记录
     *
     * @param disLookArticle
     */
    public void addLookDisArticle(DisLookArticle disLookArticle) {
        this.insert("DisArticleMapper.addLookDisArticle", disLookArticle);

    }

    /**
     * 判断用户是否存在浏览记录，如果存在只需更新时间即可
     *
     * @param disLookArticle
     * @return
     */
    public DisLookArticle queryLookArticleRecord(DisLookArticle disLookArticle) {
        List<DisLookArticle> disLookArticleList = this.selectList("DisArticleMapper.queryLookArticleRecord", disLookArticle);
        if (disLookArticleList != null && disLookArticleList.size() > 0) {
            return disLookArticleList.get(0);
        }
        return null;
    }

    /**
     * 更新用户时间
     *
     * @param disLookArticle
     * @return
     */
    public Long updateCusLookRecord(DisLookArticle disLookArticle) {
        return this.update("DisArticleMapper.updateCusLookRecord", disLookArticle);
    }

    /**
     * 查询小组文章分类
     *
     * @return List<Artclassify>
     */
    public List<Artclassify> querydisArtcicleList() {
        return this.selectList("DisArticleMapper.querydisArtcicleList", null);
    }

    /**
     * 删除我的小组文章
     *
     * @param disArticle 小组id和文章id
     * @return
     */
    public Long deleteMyArticleById(DisArticle disArticle) {
        return this.delete("DisArticleMapper.deleteMyArticleById", disArticle);
    }

    /**
     * 获得我的文章详情
     *
     * @param disArticle 小组文章id和小组id
     * @return
     */
    public DisArticle queryMyArticleDetail(DisArticle disArticle) {
        List<DisArticle> disArticleList = this.selectList("DisArticleMapper.queryMyArticleDetail", disArticle);
        if (disArticleList != null && disArticleList.size() > 0) {
            return disArticleList.get(0);
        }
        return null;
    }

    /**
     * 修改我的小组文章
     *
     * @param disArticle 文章内容
     * @return
     */
    public Long updateMyArticle(DisArticle disArticle) {
        return this.update("DisArticleMapper.updateMyArticle", disArticle);
    }

    /**
     * 删除回复
     *
     * @param disArticleReply 文章id和回复id
     * @return
     */
    public Long deleteDisArticleReply(DisArticleReply disArticleReply) {
        return this.delete("DisArticleMapper.deleteDisArticleReply", disArticleReply);
    }

    /**
     * 删除回复 文章的回复数减1
     *
     * @param articleId 文章id
     */
    public void updateDisArticleReNum(Long articleId) {
        this.update("DisArticleMapper.updateDisArticleReNum", articleId);

    }

    /**
     * 查询 此条回复是否是本人
     *
     * @param disArticleReply 小组文章文id 和用户id
     * @return
     */
    public Integer getDisArticleReplyIsMine(DisArticleReply disArticleReply) {
        List<Integer> integerList = this.selectList("DisArticleMapper.getDisArticleReplyIsMine", disArticleReply);
        if (integerList != null && integerList.size() > 0) {
            return integerList.get(0);
        }
        return 0;
    }

    /**
     * 置顶小组文章
     *
     * @param id 小组文章id
     * @return
     */
    public Long updateDisArticleByTop(Long articleId) {
        return this.update("DisArticleMapper.updateDisArticleByTop", articleId);
    }

    /**
     * 取消置顶
     *
     * @param id 小组文章id
     * @return
     */
    public Long updateCancelDisArticleByTop(Long articleId) {
        return this.update("DisArticleMapper.updateCancelDisArticleByTop", articleId);
    }

    /**
     * 查询文章分类是否存在
     *
     * @param artclassify
     * @return
     */
    public Integer queryDisArticleIsExsit(Artclassify artclassify) {
        List<Integer> integerList = this.selectList("DisArticleMapper.queryDisArticleIsExsit", artclassify);
        if (integerList != null && integerList.size() > 0) {
            return integerList.get(0);
        }
        return null;
    }

    /**
     * 更新用户最后时间和发表人
     *
     * @param disArticle
     */
    public void updateArticleLastReply(DisArticle disArticle) {
        this.update("DisArticleMapper.updateArticleLastReply", disArticle);
    }

    /**
     * 查询分类 下的小组文章
     *
     * @param classifyId
     * @return
     */
    public List<DisArticle> queryClassifyDisArticleById(Long classifyId, PageEntity page) {
        return this.queryForListPage("DisArticleMapper.queryClassifyDisArticleById", classifyId, page);
    }

    /**
     * 更新文章查看数
     *
     * @param articleId 文章id
     * @param count     +1
     * @return
     */
    public Long updateDisArticleViewCount(Long articleId, int count) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", articleId);
        map.put("count", count);
        return this.update("DisArticleMapper.updateDisArticleViewCount", map);
    }

    /**
     * 查询文章浏览人数
     *
     * @param disLookArticle
     * @return Integer
     */
    public Integer queryVisitArticleNum(DisLookArticle disLookArticle) {
        List<Integer> integerList = this.selectList("DisArticleMapper.queryVisitArticleNum", disLookArticle);
        if (integerList != null && integerList.size() > 0) {
            return integerList.get(0);
        }
        return 0;
    }

    /**
     * 删除全部回复
     *
     * @param articleId 文章id
     * @return
     */
    public Long deleteReplyArticleById(Long articleId) {
        return this.delete("DisArticleMapper.deleteReplyArticleById", articleId);
    }

    /**
     * 设置文章是否禁言
     *
     * @param disArticle
     */
    public Long updateDisArticleStatus(DisArticle disArticle) {
        return this.update("DisArticleMapper.updateDisArticleStatus", disArticle);
    }

    /**
     * 修改話題喜欢数
     *
     * @param articleId
     * @param count     +1
     * @return
     */
    public Long updateDisArticleFavorCount(Long articleId, int count) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", articleId);
        map.put("count", count);
        return this.update("DisArticleMapper.updateDisArticleFavorCount", map);
    }

    /**
     * 修改話題喜欢数
     *
     * @param articleId
     * @param count     -1
     * @return
     */
    public Long deleteDisArticleFavorCount(Long articleId, int count) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", articleId);
        map.put("count", count);
        return this.update("DisArticleMapper.deleteDisArticleFavorCount", map);
    }

    /**
     * 添加推荐喜欢列表
     *
     * @param disArticleFavor
     * @return
     */
    public Long addDisArticleFavorAndRecom(DisArticleFavor disArticleFavor) {
        return this.insert("DisArticleMapper.addDisArticleFavorAndRecom", disArticleFavor);
    }

    /**
     * 修改推荐数
     *
     * @param articleId
     * @param count     +1
     * @return
     */
    public Long updateDisArticleRecommendCount(Long articleId, int count) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", articleId);
        map.put("count", count);
        return this.update("DisArticleMapper.updateDisArticleRecommendCount", map);
    }

    /**
     * 判断是否推荐收藏
     *
     * @param disArticleFavor
     * @return
     */
    public Integer queryDisArticleIsFavorOrRecom(DisArticleFavor disArticleFavor) {
        List<Integer> integerList = this.selectList("DisArticleMapper.queryDisArticleIsFavorOrRecom", disArticleFavor);
        if (ObjectUtils.isNotNull(integerList) && integerList.size() > 0) {
            return integerList.get(0);
        }
        return 0;
    }

    /**
     * 查询喜欢推荐
     *
     * @param disArticleFavor
     * @return
     */
    public List<DisArticleFavor> queryDisArticleFavorAndRecom(DisArticleFavor disArticleFavor, PageEntity page) {
        return this.queryForListPage("DisArticleMapper.queryDisArticleFavorAndRecom", disArticleFavor, page);
    }

    /**
     * 删除喜欢推荐列表
     *
     * @param disArticleFavor
     * @return
     */
    public Long deleteDisArticleFavorAndRecom(DisArticleFavor disArticleFavor) {
        return this.delete("DisArticleMapper.deleteDisArticleFavorAndRecom", disArticleFavor);
    }

    /**
     * 首页右侧查询话题
     *
     * @return
     */
    public List<DisArticle> queryDisArticleListForHome() {
        return this.selectList("DisArticleMapper.queryDisArticleListForHome", null);
    }
    
    /**
     * 根据条件查询小组话题
     * */
    public List<DisArticle> queryDisArticleByCondition(DisArticle disArticle,PageEntity page){
    	return this.queryForListPage("DisArticleMapper.querydisArticleListByCondition", disArticle, page);
    }
}
