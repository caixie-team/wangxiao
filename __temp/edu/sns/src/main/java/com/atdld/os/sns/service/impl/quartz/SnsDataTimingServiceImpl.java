package com.atdld.os.sns.service.impl.quartz;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atdld.os.common.constants.CommonConstants;
import com.atdld.os.common.constants.MemConstans;
import com.atdld.os.core.service.cache.MemCache;
import com.atdld.os.core.service.email.EmailService;
import com.atdld.os.core.util.PropertyUtil;
import com.atdld.os.sns.service.blog.BlogBlogService;
import com.atdld.os.sns.service.discuss.DisArticleService;
import com.atdld.os.sns.service.discuss.DisGroupService;
import com.atdld.os.sns.service.lucene.LuceneSearchBlogService;
import com.atdld.os.sns.service.lucene.LuceneSearchSuggestService;
import com.atdld.os.sns.service.lucene.LuceneSearchWeiBoService;
import com.atdld.os.sns.service.quartz.SnsDataTimingService;
import com.atdld.os.sns.service.suggest.SugSuggestService;
import com.atdld.os.sns.service.visitstat.VisitStatService;
import com.atdld.os.sns.service.weibo.WeiBoService;

/**
 * @author :
 * @ClassName com.atdld.os.sns.service.impl.quartz.SnsDataTimingServiceImpl
 * @description 定时数据生成
 * @Create Date : 2014-1-3 下午4:53:14
 */
@Service("snsDataTimingService")
public class SnsDataTimingServiceImpl implements SnsDataTimingService {

    private Logger logger = Logger.getLogger(SnsDataTimingServiceImpl.class);

    // 读取配置文件类
    public static PropertyUtil propertyUtil = PropertyUtil.getInstance(CommonConstants.propertyFile);

    @Autowired
    private LuceneSearchSuggestService luceneSearchSuggestService;
    @Autowired
    private LuceneSearchWeiBoService luceneSearchWeiBoService;
    @Autowired
    private LuceneSearchBlogService luceneSearchBlogService;
    @Autowired
    private BlogBlogService blogBlogService;
    @Autowired
    private DisGroupService disGroupService;
    @Autowired
    private WeiBoService weiBoService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private VisitStatService visitStatService;
    @Autowired
    SugSuggestService sugSuggestService;
    @Autowired
    DisArticleService disArticleService;

    MemCache memCache = MemCache.getInstance();

    /**
     * 全站排行数据生成
     */
    @Override
    public void queryHotData() {
        // 全站中统一用到的数据缓存提高缓存命中
        // 首页中心位置
        // 微博一周top10
        try {
            weiBoService.queryCustomerForWeiBoNumByWeek();// 查询一个星期发表微博最多的用户数
        } catch (Exception e) {
            logger.error("+++queryHotData queryCustomerForWeiBoNumByWeek error :", e);
        }
        // 小组分类 包含小组数量
        try {
            disGroupService.querydisGroupList();
        } catch (Exception e) {
            logger.error("+++queryHotData querydisGroupList error :", e);
        }
        // 博客的文章分类，包含文章数量
        try {
            disArticleService.queryArtclassifyList();
        } catch (Exception e) {
            logger.error("+++queryHotData queryArtclassifyList error :", e);
        }

        logger.info("+++queryHotData start :" + new Date());
    }

    /**
     * 定时生成索引数据
     */
    @Override
    public void initLucenIndex() {
        logger.info("+++initLucenIndex start :" + new Date());
        Long now = System.currentTimeMillis();
        Object isCreating = memCache.get(MemConstans.MEM_LUCENE_CREATING);
        //判断是否在生成
        if (isCreating != null) {
            return;//正在生成直接返回
        } else {
            memCache.set(MemConstans.MEM_LUCENE_CREATING, "true",  MemConstans.MEM_COMMON_TIME);
        }
        try {
            // 微博索引生成
            luceneSearchWeiBoService.indexInitForWeiBo();
        } catch (Exception e) {
            logger.error("quartz WeiBO index error", e);
            // 发送邮件通知
            try {
                emailService.sendBatchMail(propertyUtil.getProperty("alertemail").split(","), "quartz weiBo IndexCreate Error:\n" + e, "lucene Index Error");
            } catch (Exception e2) {
            }
        }
        try {
            // 建议索引生成
            luceneSearchSuggestService.indexInitForSuggest();
        } catch (Exception e) {
            logger.error("quartz Suggest index error", e);
            // 发送邮件通知
            emailService.sendBatchMail(propertyUtil.getProperty("alertemail").split(","), "quartz Suggest IndexCreate Error:\n" + e, "lucene Index Error");

        }
        try {
            // 博文索引生成
            luceneSearchBlogService.indexInitForBlog();
        } catch (Exception e) {
            logger.error("quartz Blog index error", e);
            // 发送邮件通知
            emailService.sendBatchMail(propertyUtil.getProperty("alertemail").split(","), "quartz Blog IndexCreate Error:\n" + e, "lucene Index Error");

        }
        //生成完删除
        memCache.remove(MemConstans.MEM_LUCENE_CREATING);
        logger.info("+++initLucenIndex end,take seconds: " + (System.currentTimeMillis() - now) / 1000);

    }

    /**
     * 计数器定时
     */
    public void visitStat() {
        logger.info("visitStat start :" + new Date());
        Long now = System.currentTimeMillis();
        visitStatService.run();
        logger.info("visitStat end,take seconds: " + (System.currentTimeMillis() - now) / 1000);
    }
    
    
}
