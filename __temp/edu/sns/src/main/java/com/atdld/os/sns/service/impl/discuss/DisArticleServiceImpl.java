package com.atdld.os.sns.service.impl.discuss;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atdld.os.common.constants.MemConstans;
import com.atdld.os.common.service.WebHessianService;
import com.atdld.os.core.entity.PageEntity;
import com.atdld.os.core.service.cache.MemCache;
import com.atdld.os.core.util.ObjectUtils;
import com.atdld.os.core.util.StringUtils;
import com.atdld.os.core.util.web.WebUtils;
import com.atdld.os.sns.constants.ArticleConstants;
import com.atdld.os.sns.constants.DisGroupConstans;
import com.atdld.os.sns.constants.DynamicConstans;
import com.atdld.os.sns.constants.SnsConstants;
import com.atdld.os.sns.dao.discuss.DisArticleDao;
import com.atdld.os.sns.entity.customer.SnsUserExpandDto;
import com.atdld.os.sns.entity.discuss.Artclassify;
import com.atdld.os.sns.entity.discuss.DisArticle;
import com.atdld.os.sns.entity.discuss.DisArticleFavor;
import com.atdld.os.sns.entity.discuss.DisArticleReply;
import com.atdld.os.sns.entity.discuss.DisGroup;
import com.atdld.os.sns.entity.discuss.DisLookArticle;
import com.atdld.os.sns.entity.discuss.QueryDisArtAndRep;
import com.atdld.os.sns.entity.dynamic.DynamicWeb;
import com.atdld.os.sns.service.discuss.DisArticleService;
import com.atdld.os.sns.service.discuss.DisGroupService;
import com.atdld.os.sns.service.dynamic.DynamicWebService;
import com.atdld.os.sns.service.impl.visitstat.VisitStatServiceImpl;
import com.atdld.os.sns.service.user.SnsUserService;
import com.atdld.os.sns.service.visitstat.VisitStatService;

/**
 * @author :
 * @ClassName com.atdld.os.sns.service.impl.discuss.DisArticleServiceImpl
 * @description 文章ServiceImpl
 * @Create Date : 2013-12-11 下午2:09:06
 */
@Service("disArticleService")
public class DisArticleServiceImpl implements DisArticleService {
    @Autowired
    private DisArticleDao disArticleDao;
    @Autowired
    private DisGroupService disGroupService;
    @Autowired
    private DynamicWebService dynamicWebService;
    @Autowired
    private VisitStatService visitStatService;
    @Autowired
    private SnsUserService snsUserService;
    @Autowired
    private WebHessianService webHessianService;
    private MemCache memCache = MemCache.getInstance();

    /**
     * 添加小组文章
     *
     * @param disArticle 文章
     * @throws Exception
     */
    public String addDisArticle(DisArticle disArticle) throws Exception {
    	// 查看用户操作是否频繁
    	Map<String,String> map=new HashMap<String,String>();
    	map.put("type", MemConstans.ARTICLE_LIMIT);//操作类型
    	map.put("cusId",disArticle.getCusId()+"");//用户id
        // 验证用户操作时否频繁
        boolean bool = snsUserService.checkLimitOpt(MemConstans.ARTICLE_LIMIT,disArticle.getCusId());
        if (bool == true) {
            disArticle.setCreateTime(new Date());
            // 获得showname
            SnsUserExpandDto userExpandDto = snsUserService.getUserExpandByCusId(disArticle.getCusId());
            
            disArticle.setShowName(userExpandDto.getShowname());
            disArticle.setContent(webHessianService.doFilter(disArticle.getContent()));
            String summary = WebUtils.replaceTagHTML(disArticle.getContent());
            if (StringUtils.isNotEmpty(summary)) {
                summary = StringUtils.getLength(summary, ArticleConstants.ACTIVITY_SUMMARY_LENGTH);
                disArticle.setSummary(summary);
            } else {
                disArticle.setSummary("");// 如果为空则set空
            }

            Long num = disArticleDao.addDisArticle(disArticle);
            if (num == 1) {
                // 添加小组文章文章文章数+1
                disGroupService.updateArticleCount(disArticle.getGroupId(), 1); // 8.小组文章数
                // 文章数+1活跃度 y=文章数目 B=5
                DisGroup disGroup = new DisGroup();
                disGroup.setId(disArticle.getGroupId());
                disGroup.setActivity(DisGroupConstans.GROUP_ACTIVITY_ARTICLE);
                disGroupService.updateDisGroupActivity(disGroup);// 添加活跃度
                // 用户操作数+1
                snsUserService.customerOptLimitCountAdd(MemConstans.ARTICLE_LIMIT,disArticle.getCusId());
                // 存文章动态
                dynamicWebService.addDynamicWebForDisArticle(disArticle);
            } else {
                return SnsConstants.FALSE;
            }
        } else {
            return SnsConstants.LIMITOPT;// 返回提示操作太频繁
        }
        return SnsConstants.SUCCESS;
    }

    /**
     * 分页查询小组文章列表
     *
     * @param groupId    小组ID
     * @param pageEntity 分页参数
     * @return List<DisArticle> 小组文章 list
     * @throws Exception
     */
    public List<DisArticle> queryDisArticleList(Long groupId, PageEntity pageEntity) throws Exception {
        List<DisArticle> disArticleList = disArticleDao.queryDisArticleList(groupId, pageEntity);
        String cusIds="";
        if (ObjectUtils.isNotNull(disArticleList) && disArticleList.size() > 0) {
            for (DisArticle disArticle : disArticleList) {// 获得用户cusId集
            	cusIds+=disArticle.getCusId()+",";
            }
            Map<String, SnsUserExpandDto> map = snsUserService.getUserExpandsByCusId(cusIds);
            if(ObjectUtils.isNotNull(map)){
                for (DisArticle disArticle : disArticleList) {
                    SnsUserExpandDto customer = map.get(disArticle.getCusId().toString());
                    if(ObjectUtils.isNotNull(customer)){
                        disArticle.setUserExpandDto(customer);
                        disArticle.setShowName(customer.getShowname());
                    }
                }
            }
            
        }
        return disArticleList;
    }

    /**
     * 查看文章详情
     *
     * @param disArticle 文章的ID
     * @return DisArticle对象
     * @throws Exception
     */
    public DisArticle queryDisArticleDetail(DisArticle disArticle) throws Exception {
        DisArticle disArticleMsg = disArticleDao.queryDisArticleDetail(disArticle);
        if (ObjectUtils.isNotNull(disArticleMsg)) {
            SnsUserExpandDto userExpandDto = snsUserService.getUserExpandByCusId(disArticleMsg.getCusId());// 获得用户信息
            if (ObjectUtils.isNotNull(userExpandDto)) {
                disArticleMsg.setUserExpandDto(userExpandDto);
                disArticleMsg.setShowName(userExpandDto.getShowname());
            }
        }
        return disArticleMsg;
    }

    /**
     * 添加文章回复
     *
     * @param disArticleReply 回复内容
     * @throws Exception
     */
    public String addDisArticleReply(DisArticleReply disArticleReply) throws Exception {
    	// 查看用户操作是否频繁
        // 验证用户操作时否频繁
        boolean bool = snsUserService.checkLimitOpt(MemConstans.ARTICLE_LIMIT,disArticleReply.getRecusId());
        if (bool == true) {
            // 添加文章回复
            disArticleReply.setReplyTime(new Date());
            // 添加会员名
            // 获得showname
            SnsUserExpandDto userExpandDto = snsUserService.getUserExpandByCusId(disArticleReply.getRecusId());
            disArticleReply.setShowName(userExpandDto.getShowname());
            disArticleReply.setReplyContent(webHessianService.doFilter(disArticleReply.getReplyContent()));
            Long num = disArticleDao.addDisArticleReply(disArticleReply);
            if (num == 1) {
                // 文章回复数+1
                visitStatService.record(VisitStatServiceImpl.TYPES[7], disArticleReply.getArticleId());
                DisArticle disArticle = new DisArticle();
                disArticle.setLastReply(disArticleReply.getShowName());
                disArticle.setLastTime(new Date());
                disArticle.setId(disArticleReply.getArticleId());
                disArticleDao.updateArticleLastReply(disArticle);// 更新最后回复人和时间
                // 活跃度 z=回复数目 C=1
                DisGroup disGroup = new DisGroup();
                disGroup.setId(disArticleReply.getGroupId());
                disGroup.setActivity(DisGroupConstans.GROUP_ACTIVITY_REPLY);
                disGroupService.updateDisGroupActivity(disGroup);
                // 用户操作数+1
                snsUserService.customerOptLimitCountAdd(MemConstans.ARTICLE_LIMIT,disArticleReply.getRecusId());
                // 添加回复文章动态
                dynamicWebService.replyDynamicWebForDisArticle(disArticleReply);
                return SnsConstants.SUCCESS;// 返回成功
            } else {
                return SnsConstants.FALSE;// 返回失败
            }

        } else {
            return SnsConstants.LIMITOPT;// 返回提示操作太频繁
        }
    }

    /**
     * 查询回复列表
     *
     * @param disArticle 文章id
     * @param pageEntity 分页参数
     * @return List<DisArticleReply> 回复列表list
     * @throws Exception
     */
    public List<DisArticleReply> queryDisArticleReplyList(DisArticle disArticle, PageEntity pageEntity) throws Exception {
        List<DisArticleReply> disArticleReplyList = disArticleDao.queryDisArticleReplyList(disArticle, pageEntity);
        
        if (ObjectUtils.isNotNull(disArticleReplyList) && disArticleReplyList.size() > 0) {
        	Map<String, SnsUserExpandDto> map = snsUserService.getUserExpandsByCusId(getDisArticleReplyListRecusId(disArticleReplyList));// 查询用户的信息
            for (DisArticleReply disArticleReply : disArticleReplyList) {
                SnsUserExpandDto userExpandDto = map.get("" + disArticleReply.getRecusId());
                if (ObjectUtils.isNotNull(userExpandDto)) {
                    disArticleReply.setUserExpandDto(userExpandDto);
                    disArticleReply.setShowName(userExpandDto.getShowname());
                }
            }
        }
        return disArticleReplyList;
    }

    public String getDisArticleReplyListRecusId(List<DisArticleReply> disArticleReplyList) {// 获得用户ids
        String ids = "";
        if (disArticleReplyList != null && disArticleReplyList.size() > 0) {
            for (DisArticleReply disArticleReply : disArticleReplyList) {
                ids += disArticleReply.getRecusId() + ",";
            }
        }
        return ids;
    }

    /**
     * 查询用户浏览记录
     *
     * @param disLookArticle 博文或 小组文章id 类型id
     * @return
     * @throws Exception
     */
    public List<DisLookArticle> queryDisLookArticleRecord(DisLookArticle disLookArticle) throws Exception {
        List<DisLookArticle> disLookArticleList = disArticleDao.queryDisLookArticleRecord(disLookArticle);
        
        if (ObjectUtils.isNotNull(disLookArticleList) && disLookArticleList.size() > 0) {
        	Map<String, SnsUserExpandDto> map = snsUserService.getUserExpandsByCusId(getDisLookArticleListCusId(disLookArticleList));// 查询用户的信息
            for (DisLookArticle disLook : disLookArticleList) {
                SnsUserExpandDto userExpandDto = map.get("" + disLook.getCusId());
                if (ObjectUtils.isNotNull(userExpandDto)) {
                    disLook.setAvatar(userExpandDto.getAvatar());
                    disLook.setShowName(userExpandDto.getShowname());
                }
            }
        }

        return disLookArticleList;
    }

    public String getDisLookArticleListCusId(List<DisLookArticle> disLookArticleList) {// 获得用户ids
        String ids = "";
        if (disLookArticleList != null && disLookArticleList.size() > 0) {
            for (DisLookArticle disLookArticle : disLookArticleList) {
                ids += disLookArticle.getCusId() + ",";
            }
        }
        return ids;
    }

    /**
     * 查看我的小组文章
     *
     * @param disArticle 用户的id
     * @return List<DisArticle> 小组文章list
     * @throws Exception
     */
    public List<DisArticle> queryMyDisArticle(DisArticle disArticle) throws Exception {
        return disArticleDao.queryMyDisArticle(disArticle);
    }

    /**
     * 查询文章分类
     *
     * @return List<Artclassify> 文章分类list
     */
    public List<Artclassify> queryArtclassifyList() throws Exception {
        // 根据key从memcache中取
        @SuppressWarnings("unchecked")
        List<Artclassify> queryArtclassifyList = (List<Artclassify>) memCache.get(MemConstans.BLOG_CLASSIFY);
        if (ObjectUtils.isNull(queryArtclassifyList)) {
            queryArtclassifyList = disArticleDao.queryArtclassifyList();
            memCache.set(MemConstans.BLOG_CLASSIFY, queryArtclassifyList, MemConstans.DISGROUP_INFO_TIME);
        }
        return queryArtclassifyList;
    }

    /**
     * 首页查询我的小组文章
     *
     * @param cusId
     * @return
     */
    public List<QueryDisArtAndRep> queryMyArticleById(Long cusId, PageEntity page) throws Exception {
        List<QueryDisArtAndRep> queryDisArtAndRepList = disArticleDao.queryMyArticleById(cusId, page);
        if (ObjectUtils.isNotNull(queryDisArtAndRepList) && queryDisArtAndRepList.size() > 0) {
            SnsUserExpandDto userExpandDto =snsUserService.getUserExpandByCusId(cusId);
            for (QueryDisArtAndRep queryDisArtAndRep : queryDisArtAndRepList) {
                queryDisArtAndRep.setShowName(userExpandDto.getShowname());
            }
        }
        return queryDisArtAndRepList;
    }

    /**
     * 添加浏览记录
     *
     * @param disLookArticle
     */
    public void addLookDisArticle(DisLookArticle disLookArticle) throws Exception {
        disArticleDao.addLookDisArticle(disLookArticle);
    }

    /**
     * 判断用户是否存在浏览记录，如果存在只需更新时间即可
     *
     * @param disLookArticle
     * @return
     */
    public DisLookArticle queryLookArticleRecord(DisLookArticle disLookArticle) throws Exception {
        return disArticleDao.queryLookArticleRecord(disLookArticle);
    }

    /**
     * 更新用户时间
     *
     * @param disLookArticle
     * @return
     */
    public String updateCusLookRecord(DisLookArticle disLookArticle) throws Exception {
        disLookArticle.setAddTime(new Date());// 更新时间
        Long num = disArticleDao.updateCusLookRecord(disLookArticle);
        if (num == 1) {
            return SnsConstants.SUCCESS;// 返回成功
        } else {
            return SnsConstants.FALSE;// 返回失败
        }
    }

    /**
     * 查询小组文章分类
     *
     * @return List<Artclassify>
     */
    public List<Artclassify> querydisArtcicleList() throws Exception {
        @SuppressWarnings("unchecked")
        List<Artclassify> disClassifyArtcicleList = (List<Artclassify>) memCache.get(MemConstans.ARTICLE_CLASSIFY);
        if (ObjectUtils.isNull(disClassifyArtcicleList)) {
            disClassifyArtcicleList = disArticleDao.querydisArtcicleList();
            memCache.set(MemConstans.ARTICLE_CLASSIFY, disClassifyArtcicleList, MemConstans.DISGROUP_INFO_TIME);
        }
        return disClassifyArtcicleList;
    }

    /**
     * 删除我的小组文章
     *
     * @param disArticle 小组id和文章id
     * @return
     */
    public String deleteMyArticleById(DisArticle disArticle) throws Exception {
        Long num = disArticleDao.deleteMyArticleById(disArticle);
        if (num == 1) {
            disGroupService.updateArticleCountsReduce(disArticle.getGroupId());
            this.deleteReplyArticleById(disArticle.getId());
            DynamicWeb dynamicWeb = new DynamicWeb();
            dynamicWeb.setBizId(disArticle.getId());
            dynamicWeb.setType(DynamicConstans.DYNAMICWEB_TYPE_DISARTICLE);
            dynamicWebService.deleteDynamicWebByCondition(dynamicWeb);
            dynamicWeb.setType(DynamicConstans.DYNAMICWEB_TYPE_REDISARTICLE);
            dynamicWebService.deleteDynamicWebByCondition(dynamicWeb);
            return SnsConstants.SUCCESS;// 返回成功
        }
        return SnsConstants.FALSE;// 返回失败
    }

    /**
     * 获得我的文章详情
     *
     * @param disArticle 小组文章id和小组id
     * @return
     */
    public DisArticle queryMyArticleDetail(DisArticle disArticle) throws Exception {
        return disArticleDao.queryMyArticleDetail(disArticle);
    }

    /**
     * 修改我的小组文章
     *
     * @param disArticle 文章内容
     * @return
     */
    public String updateMyArticle(DisArticle disArticle) throws Exception {
        Long num = disArticleDao.updateMyArticle(disArticle);
        if (num == 1) {
            return SnsConstants.SUCCESS;// 返回成功
        }
        return SnsConstants.FALSE;// 返回失败
    }

    /**
     * 删除回复
     *
     * @param disArticleReply 文章id和回复id
     * @return
     */
    public String deleteDisArticleReply(DisArticleReply disArticleReply) throws Exception {
        Long num = disArticleDao.deleteDisArticleReply(disArticleReply);
        if (num == 1) {
            // 更新文章回复数
            disArticleDao.updateDisArticleReNum(disArticleReply.getArticleId());
            return SnsConstants.SUCCESS;// 返回成功
        }
        return SnsConstants.FALSE;// 返回失败
    }

    /**
     * 删除回复 文章的回复数减1
     *
     * @param articleId 文章id
     */
    public void updateDisArticleReNum(Long articleId) throws Exception {
        disArticleDao.updateDisArticleReNum(articleId);

    }

    /**
     * 查询 此条回复是否是本人
     *
     * @param disArticleReply 回复id 和用户id
     * @return
     */
    public Integer getDisArticleReplyIsMine(DisArticleReply disArticleReply) throws Exception {
        return disArticleDao.getDisArticleReplyIsMine(disArticleReply);
    }

    /**
     * 置顶小组文章
     *
     * @param id 小组文章id
     * @return
     */
    public String updateDisArticleByTop(Long articleId) throws Exception {
        Long top = disArticleDao.updateDisArticleByTop(articleId);
        if (top == 1) {
            return SnsConstants.SUCCESS;// 返回成功
        } else {
            return SnsConstants.FALSE;// 返回失败
        }

    }

    /**
     * 取消置顶
     *
     * @param id 小组文章id
     * @return
     */
    public String updateCancelDisArticleByTop(Long articleId) throws Exception {
        Long top = disArticleDao.updateCancelDisArticleByTop(articleId);
        if (top == 1) {
            return SnsConstants.SUCCESS;// 返回成功
        } else {
            return SnsConstants.FALSE;// 返回失败
        }
    }

    /**
     * 查询文章分类是否存在
     *
     * @param artclassify
     * @return
     */
    public Integer queryDisArticleIsExsit(Artclassify artclassify) throws Exception {
        return disArticleDao.queryDisArticleIsExsit(artclassify);
    }

    /**
     * 更新用户最后时间和发表人
     *
     * @param disArticle
     */
    public void updateArticleLastReply(DisArticle disArticle) throws Exception {
        disArticleDao.updateArticleLastReply(disArticle);
    }

    /**
     * 查询分类 下的小组文章
     *
     * @param classifyId
     * @return
     */
    public List<DisArticle> queryClassifyDisArticleById(Long classifyId, PageEntity page) throws Exception {
        List<DisArticle> disArticleList = disArticleDao.queryClassifyDisArticleById(classifyId, page);
        String cusIds="";
        if (ObjectUtils.isNotNull(disArticleList) && disArticleList.size() > 0) {
            for (DisArticle disArticle : disArticleList) {// 获得用户cusId集
            	cusIds+=disArticle.getCusId()+",";
            }
            Map<String, SnsUserExpandDto> map = snsUserService.getUserExpandsByCusId(cusIds);
            if(ObjectUtils.isNotNull(map)){
                for (DisArticle disArticle : disArticleList) {
                    SnsUserExpandDto customer = map.get(disArticle.getCusId().toString());
                    if(ObjectUtils.isNotNull(customer)){
                        disArticle.setUserExpandDto(customer);
                        disArticle.setShowName(customer.getShowname());
                    }
                }
            }
            
        }
        return disArticleList;
    }

    /**
     * 更新文章查看数
     *
     * @param articleId 文章id
     * @param count     +1
     * @return
     */
    public Long updateDisArticleViewCount(Long articleId, int count) throws Exception {
        return disArticleDao.updateDisArticleViewCount(articleId, count);
    }

    /**
     * 添加回复时统计数量数量加+1
     *
     * @param articleId 文章id
     * @param count     +1
     */
    public void updateDisArticleReplyCount(Long articleId, int count) throws Exception {
        disArticleDao.updateDisArticleReplyCount(articleId, count);
    }

    /**
     * 查询文章浏览人数
     *
     * @param disLookArticle
     * @return
     * @throws Exception
     */
    public Integer queryVisitArticleNum(DisLookArticle disLookArticle) throws Exception {
        return disArticleDao.queryVisitArticleNum(disLookArticle);
    }

    /**
     * 删除全部回复
     *
     * @param articleId
     * @return
     * @throws Exception
     */
    public Long deleteReplyArticleById(Long articleId) throws Exception {
        return disArticleDao.deleteReplyArticleById(articleId);
    }


    /**
     * 设置文章是否禁言
     *
     * @param disArticle
     * @return
     * @throws Exception
     */
    public String updateDisArticleStatus(DisArticle disArticle) throws Exception {
        Long num = disArticleDao.updateDisArticleStatus(disArticle);
        if (num == 1) {
            return SnsConstants.SUCCESS;
        }
        return SnsConstants.FALSE;
    }

    /**
     * 修改話題喜欢数
     *
     * @param articleId
     * @param count     +1
     * @return
     */
    public Long updateDisArticleFavorCount(Long articleId, int count) throws Exception {
        return disArticleDao.updateDisArticleFavorCount(articleId, count);
    }

    /**
     * 修改話題喜欢数
     *
     * @param articleId
     * @param count     -1
     * @return
     */
    public Long deleteDisArticleFavorCount(Long articleId, int count) throws Exception {
        return disArticleDao.deleteDisArticleFavorCount(articleId, count);
    }

    /**
     * 添加推荐喜欢列表
     *
     * @param disArticleFavor
     * @return
     */
    public String addDisArticleFavorAndRecom(DisArticleFavor disArticleFavor) throws Exception {
    	// 查看用户操作是否频繁
        boolean bool = snsUserService.checkLimitOpt(MemConstans.ARTICLE_LIMIT,disArticleFavor.getCusId());
        if (bool) {
            disArticleFavor.setAddTime(new Date());
            Long num = disArticleDao.addDisArticleFavorAndRecom(disArticleFavor);
            if (num == 1) {
                if (disArticleFavor.getType() == ArticleConstants.ACTIVITY_LIKE) {
                    //更新喜欢数
                    updateDisArticleFavorCount(disArticleFavor.getArticleId(), ArticleConstants.ACTIVITY_OPERITING_COUNT);
                } else {
                    //更新推荐数
                    updateDisArticleRecommendCount(disArticleFavor.getArticleId(), ArticleConstants.ACTIVITY_OPERITING_COUNT);
                }
            }
        } else {
            return SnsConstants.LIMITOPT;
        }
        return SnsConstants.SUCCESS;
    }

    /**
     * 修改推荐数
     *
     * @param articleId
     * @param count     +1
     * @return
     */
    public Long updateDisArticleRecommendCount(Long articleId, int count) throws Exception {
        return disArticleDao.updateDisArticleRecommendCount(articleId, count);
    }

    /**
     * 判断是否推荐收藏
     *
     * @param disArticleFavor
     * @return
     */
    public Integer queryDisArticleIsFavorOrRecom(DisArticleFavor disArticleFavor) throws Exception {
        return disArticleDao.queryDisArticleIsFavorOrRecom(disArticleFavor);
    }

    /**
     * 查询喜欢推荐
     *
     * @param disArticleFavor
     * @return
     */
    public List<DisArticleFavor> queryDisArticleFavorAndRecom(DisArticleFavor disArticleFavor, PageEntity page) {
        return disArticleDao.queryDisArticleFavorAndRecom(disArticleFavor, page);
    }

    /**
     * 删除喜欢推荐列表
     *
     * @param disArticleFavor
     * @return
     */
    public String deleteDisArticleFavorAndRecom(DisArticleFavor disArticleFavor) throws Exception {
        Long num = disArticleDao.deleteDisArticleFavorAndRecom(disArticleFavor);
        if (num == 1) {
            deleteDisArticleFavorCount(disArticleFavor.getArticleId(), ArticleConstants.ACTIVITY_OPERITING_COUNT);
            return SnsConstants.SUCCESS;
        }
        return SnsConstants.FALSE;
    }

    /**
     * 首页右侧查询话题
     *
     * @return
     * @throws Exception
     */
    public List<DisArticle> queryDisArticleListForHome() throws Exception {
        List<DisArticle> disArticleList = disArticleDao.queryDisArticleListForHome();
        String cusIds="";
        if (ObjectUtils.isNotNull(disArticleList) && disArticleList.size() > 0) {
            for (DisArticle disArticle : disArticleList) {// 获得用户cusId集
            	cusIds+=disArticle.getCusId()+",";
            }
            Map<String, SnsUserExpandDto> map = snsUserService.getUserExpandsByCusId(cusIds);
            if(ObjectUtils.isNotNull(map)){
                for (DisArticle disArticle : disArticleList) {
                    SnsUserExpandDto customer= map.get(disArticle.getCusId().toString());
                    if(ObjectUtils.isNotNull(customer)){
                        disArticle.setUserExpandDto(customer);
                        disArticle.setShowName(customer.getShowname());
                    }
                } 
            }
            
        }
        return disArticleList;
    }
    
    /**
     * 根据条件查询小组话题
     * */
    public List<DisArticle> queryDisArticleByCondition(DisArticle disArticle,PageEntity page){
    	return disArticleDao.queryDisArticleByCondition(disArticle, page);
    }

}
