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

import com.atdld.os.core.entity.PageEntity;
import com.atdld.os.sns.constants.LuceneConstans;
import com.atdld.os.sns.entity.customer.SnsUserExpandDto;
import com.atdld.os.sns.entity.lucene.LuceneLast;
import com.atdld.os.sns.entity.suggest.SugSuggest;
import com.atdld.os.sns.service.lucene.LuceneLastNumService;
import com.atdld.os.sns.service.lucene.LuceneSearchSuggestService;
import com.atdld.os.sns.service.suggest.SugSuggestService;
import com.atdld.os.sns.service.user.SnsUserService;
import com.atdld.os.core.util.DateUtils;
import com.atdld.os.core.util.ObjectUtils;

/**
 * @author :
 * @ClassName com.atdld.os.sns.service.impl.lucene.LuceneSearchSuggestServiceImpl
 * @description 荐言堂搜索
 * @Create Date : 2014-1-3 上午9:24:38
 */
@Service("luceneSearchSuggestService")
public class LuceneSearchSuggestServiceImpl implements LuceneSearchSuggestService {

    @Autowired
    private LuceneLastNumService luceneLastNumService;
    @Autowired
    private SugSuggestService sugSuggestService;
    @Autowired
    private SnsUserService snsUserService;

    Analyzer analyzer = new IKAnalyzer();
    private Logger logger = Logger.getLogger(LuceneSearchSuggestServiceImpl.class);

    /**
     * 根据关键字查询建议，返回map: 总行数 当前页建议的list
     *
     * @param keyword     关键字
     * @param currentPage 当前页
     * @param pagesize    一次获取的行数
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    @Override
    public Map<String, Object> queryPageSuggestByKeyWord(Map<String, String> searchMap, PageEntity page) throws Exception {
        // 查询所有的匹配的
        Map<String, Object> tempMap = IndexSearcherFactory.queryMapByKeyWord(searchMap, LuceneConstans.LUCENE_SEARCH_TYPE_SUGGEST);
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
        // 查询建议数据
        if (!ObjectUtils.isNull(searchedids)) {
            List<SugSuggest> SugSuggestList = sugSuggestService.getLuceneSugSuggestByIds(searchedids);
            String cusIds="";
            //循环博客查询showname
            if (ObjectUtils.isNotNull(SugSuggestList)) {
                for (SugSuggest sug : SugSuggestList) {
                	cusIds+=sug.getCusId()+",";
                }
                //通过用户id查询出customer的map
                Map<String, SnsUserExpandDto> map = snsUserService.getUserExpandsByCusId(cusIds);
                if(ObjectUtils.isNotNull(map)){
                    for (SugSuggest sug : SugSuggestList) {
                        SnsUserExpandDto customer=map.get(sug.getCusId().toString());
                        if(ObjectUtils.isNotNull(customer)){
                            sug.setShowname(customer.getShowname());
                        }
                    }
                }
            }
            tempMap.put(LuceneConstans.LUCENE_SEARCHED_RESULT_IDS, SugSuggestList);
        }

        return tempMap;
    }

    /**
     * 初始化建议索引生成（定时调用）
     */
    @Override
    public boolean indexInitForSuggest() throws Exception {
        logger.info("Sugges luceneCreate Start :" + DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss"));
        long s = System.currentTimeMillis();
        // 获取上次处理到的行数
        LuceneLast luceneLast = new LuceneLast();
        luceneLast.setProject(LuceneConstans.LUCENE_SEARCH_TYPE_SUGGEST);
        luceneLast = luceneLastNumService.gainSelectByProject(luceneLast);
        Long minSugSuggestId = luceneLast.getMaxid();
        // 获取建议总行数以及本次处理的结束的建议id
        Map<String, Object> map = sugSuggestService.getSugSuggestCountAfterId(minSugSuggestId);
        // 建议总行数本次处理的行数
        Long sugSuggestCount = Long.valueOf(map.get(LuceneConstans.LUCENE_LINE_NUM).toString());
        // 本次处理最后一条的id
        Long sugSuggestMaxId = Long.valueOf(map.get(LuceneConstans.LUCENE_MAX_ID).toString());
        if (sugSuggestCount == 0L || luceneLast.getMaxid().longValue() == sugSuggestMaxId.longValue()) {
            return false;
        }
        // 分页生成
        Long pages = IndexSearcherFactory.getPageSize(sugSuggestCount);
        for (Long begin = 0L; begin < pages; begin++) {
            // 从数据库中查询要创建的索引的数据
            Long beginRow = begin * LuceneConstans.LUCENE_CREATE_PAGE_SIZE;// 起始的行数
            List<SugSuggest> suggestlist = sugSuggestService.getSugSuggestByPageQuery(beginRow, LuceneConstans.LUCENE_CREATE_PAGE_SIZE, minSugSuggestId + 1, sugSuggestMaxId);
            // 统一生成索引方法
            IndexSearcherFactory.indexCreate(suggestlist, LuceneConstans.LUCENE_SEARCH_TYPE_SUGGEST);
        }
        // 更新数据SugSuggestMaxId存到库中
        luceneLast.setMaxid(sugSuggestMaxId);
        luceneLastNumService.updateLuceneLast(luceneLast);
        logger.info("Suggest Lucene End,minId :" + minSugSuggestId + ",MaxId :" + sugSuggestMaxId + ",SugCount :" + sugSuggestCount + ",times:" + (System.currentTimeMillis() - s) / 1000);
        return true;
    }

}
