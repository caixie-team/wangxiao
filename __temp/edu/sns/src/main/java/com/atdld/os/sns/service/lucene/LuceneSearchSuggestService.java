package com.atdld.os.sns.service.lucene;

import java.util.Map;

import com.atdld.os.core.entity.PageEntity;

/**
 * @author :
 * @ClassName com.atdld.os.sns.service.lucene.LuceneSearchSuggestService
 * @description
 * @Create Date : 2014-1-3 上午9:21:44
 */
public interface LuceneSearchSuggestService {

    /**
     * 根据关键字查询建议，返回map: 总行数 当前页建议的list
     *
     * @param keyword     关键字
     * @param currentPage 当前页
     * @param pagesize    一次获取的行数
     * @return
     * @throws Exception
     */
    public Map<String, Object> queryPageSuggestByKeyWord(Map<String, String> searchMap, PageEntity page) throws Exception;

    /**
     * 初始化建议索引生成（定时调用）
     */
    public boolean indexInitForSuggest() throws Exception;

}
