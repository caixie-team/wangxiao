package com.atdld.os.sns.dao.lucene;

import com.atdld.os.sns.entity.lucene.LuceneLast;

/**
 * @author :
 * @ClassName com.atdld.os.sns.dao.impl.lucene.LuceneLastDao
 * @description
 * @Create Date : 2013-12-25 下午3:56:41
 */
public interface LuceneLastDao {

    /**
     * 增加LuceneLast
     *
     * @param record
     * @return
     */
    Long insertLuceneLast(LuceneLast luceneLast);

    /**
     * 根据Project查询
     *
     * @param id
     * @return
     */
    LuceneLast selectByProject(LuceneLast luceneLast);

    /**
     * 修改LuceneLast
     *
     * @param record
     * @return
     */
    Long updateLuceneLast(LuceneLast luceneLast);

    /**
     * 修改LuceneLast都为0
     *
     * @return
     */
    boolean updateLuceneAllClear();


}
