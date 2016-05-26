package com.atdld.os.sns.service.impl.lucene;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.lucene.analysis.Analyzer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.atdld.os.sns.constants.LuceneConstans;
import com.atdld.os.sns.entity.blog.BlogBlog;
import com.atdld.os.sns.entity.blog.QueryBlogAndReply;
import com.atdld.os.sns.entity.customer.SnsUserExpandDto;
import com.atdld.os.core.entity.PageEntity;
import com.atdld.os.sns.entity.lucene.LuceneLast;
import com.atdld.os.sns.service.blog.BlogBlogService;
import com.atdld.os.sns.service.lucene.LuceneLastNumService;
import com.atdld.os.sns.service.lucene.LuceneSearchBlogService;
import com.atdld.os.sns.service.user.SnsUserService;
import com.atdld.os.core.util.DateUtils;
import com.atdld.os.core.util.ObjectUtils;

/**
 * @author :
 * @ClassName com.atdld.os.sns.service.impl.lucene.LuceneSearchBlogServiceImpl
 * @description
 * @Create Date : 2014-1-17 上午9:56:46
 */
@Service("luceneSearchBlogService")
public class LuceneSearchBlogServiceImpl implements LuceneSearchBlogService {

    @Autowired
    private LuceneLastNumService luceneLastNumService;
    @Autowired
    private BlogBlogService blogBlogService;
    @Autowired
    private SnsUserService snsUserService;
    Analyzer analyzer = new IKAnalyzer();

    private Logger logger = Logger.getLogger(LuceneSearchBlogServiceImpl.class);

    /**
     * 根据关键字查询博文，返回map: 总行数 当前页博文的list
     *
     * @param searchMap     关键字
     * @param page 当前页
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    @Override
    public Map<String, Object> queryPageBlogByKeyWord(Map<String, String> searchMap, PageEntity page) throws Exception {

        // 查询所有的匹配的
        Map<String, Object> tempMap = IndexSearcherFactory.queryMapByKeyWord(searchMap, LuceneConstans.LUCENE_SEARCH_TYPE_BLOG);// this.queryAllBlogMapByKeyWord(keyword);
        List<Long> tmpSearchedids = ((List<Long>) tempMap.get(LuceneConstans.LUCENE_SEARCHED_RESULT_IDS));
        if (ObjectUtils.isNull(tempMap.get(LuceneConstans.LUCENE_SEARCH_RESULTS_COUNT))) {
            return null;
        }
        // page属性设置
        page.setTotalResultSize(Long.valueOf(tempMap.get(LuceneConstans.LUCENE_SEARCH_RESULTS_COUNT).toString()).intValue());
        int totalPageSize = (page.getTotalResultSize() - 1) / page.getPageSize() + 1;
        page.setTotalPageSize(totalPageSize);
        int nextPageOffset = IndexSearcherFactory.nextPageOffset(page.getCurrentPage(), page.getPageSize(), tmpSearchedids.size());
        List<Long> searchedids = new ArrayList<Long>();
        // 返回分页的数据。
        for (int i = ((page.getCurrentPage() - 1) * page.getPageSize()); i < nextPageOffset; i++) {
            searchedids.add(tmpSearchedids.get(i));
        }
        // 查询博文数据
        if (!ObjectUtils.isNull(searchedids)) {
            List<QueryBlogAndReply> BlogList = blogBlogService.getLuceneBlogBlogByIds(searchedids);
            String cusIds="";
            //循环博客查询showname
            if (ObjectUtils.isNotNull(BlogList)) {
                for (QueryBlogAndReply blog : BlogList) {
                	cusIds+=blog.getCusId()+",";
                }
                //通过用户id查询出customer的map
                Map<String, SnsUserExpandDto> map = snsUserService.getUserExpandsByCusId(cusIds);
                if(ObjectUtils.isNotNull(map)){
                    for (QueryBlogAndReply blog : BlogList) {
                        SnsUserExpandDto customer=map.get(blog.getCusId().toString());
                        if(ObjectUtils.isNotNull(customer)){
                            blog.setUserExpandDto(customer);
                            blog.setShowName(customer.getShowname());
                        }
                    }
                }
            }
            tempMap.put(LuceneConstans.LUCENE_SEARCHED_RESULT_IDS, BlogList);
        }
        return tempMap;
    }

    /**
     * 初始化博文索引生成（定时调用）
     */
    @Override
    public boolean indexInitForBlog() throws Exception {
        logger.info("Blog luceneCreate Start :" + DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss"));
        long s = System.currentTimeMillis();
        // 获取上次处理到的行数
        LuceneLast luceneLast = new LuceneLast();
        luceneLast.setProject(LuceneConstans.LUCENE_SEARCH_TYPE_BLOG);
        luceneLast = luceneLastNumService.gainSelectByProject(luceneLast);
        Long minBlogtId = luceneLast.getMaxid();
        // 获取博文总行数以及本次处理的结束的博文id
        Map<String, Object> map = blogBlogService.getBlogBlogCountAfterId(minBlogtId);
        // 博文总行数本次处理的行数
        Long blogCount = Long.valueOf(map.get(LuceneConstans.LUCENE_LINE_NUM).toString());
        // 本次处理最后一条的id
        Long blogMaxId = Long.valueOf(map.get(LuceneConstans.LUCENE_MAX_ID).toString());
        if (blogCount == 0L || luceneLast.getMaxid().longValue() == blogMaxId.longValue()) {
            return false;
        }
        // 分页生成
        Long pages = IndexSearcherFactory.getPageSize(blogCount);
        for (Long begin = 0L; begin < pages; begin++) {
            // 从数据库中查询要创建的索引的数据
            Long beginRow = begin * LuceneConstans.LUCENE_CREATE_PAGE_SIZE;// 起始的行数
            List<BlogBlog> blogList = blogBlogService.getBlogBlogByPageQuery(beginRow, LuceneConstans.LUCENE_CREATE_PAGE_SIZE, minBlogtId + 1, blogMaxId);
            // 统一生成索引方法
            IndexSearcherFactory.indexCreate(blogList, LuceneConstans.LUCENE_SEARCH_TYPE_BLOG);
        }
        // 更新数据BlogMaxId存到库中
        luceneLast.setMaxid(blogMaxId);
        luceneLastNumService.updateLuceneLast(luceneLast);
        logger.info("Blog luceneCreate End,minBlogId :" + minBlogtId + ",BlogMaxId :" + blogMaxId + ",BlogCount :" + blogCount + ",times:" + (System.currentTimeMillis() - s) / 1000);
        return true;
    }

}
