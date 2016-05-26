package com.atdld.os.sns.service.lucene;

import com.atdld.os.sns.entity.lucene.LuceneLast;

/**
 * @author :
 * @ClassName com.atdld.os.sns.service.lucene.LuceneLastNumService
 * @description lucene数据生成最后数据记录
 * @Create Date : 2014-1-3 下午3:28:28
 */
public interface LuceneLastNumService {
    /**
     * 根据Project查询
     *
     * @param id
     * @return
     */
    LuceneLast gainSelectByProject(LuceneLast luceneLast);

    /**
     * 修改LuceneLast
     *
     * @param record
     * @return
     */
    Long updateLuceneLast(LuceneLast luceneLast);

    /**
     * 修改LuceneLast 都置为0
     *
     * @return boolean
     */
    boolean updateLuceneLastClear();

}
