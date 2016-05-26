package com.atdld.os.sns.dao.impl.lucene;

import java.util.Date;

import org.springframework.stereotype.Repository;

import com.atdld.os.core.dao.impl.common.GenericDaoImpl;
import com.atdld.os.sns.dao.lucene.LuceneLastDao;
import com.atdld.os.sns.entity.lucene.LuceneLast;

/**
 * @author :
 * @ClassName com.atdld.os.sns.dao.impl.lucene.LuceneLastDaoImpl
 * @description
 * @Create Date : 2013-12-25 下午3:57:11
 */
@Repository("luceneLastDao")
public class LuceneLastDaoImpl extends GenericDaoImpl implements LuceneLastDao {

    /**
     * 增加LuceneLast
     *
     * @param record
     * @return
     */
    @Override
    public Long insertLuceneLast(LuceneLast luceneLast) {
        luceneLast.setMaxid(0L);
        luceneLast.setUpdatetime(new Date());
        return this.insert("LucenelastMapper.insertLuceneLast", luceneLast);
    }

    /**
     * 根据Project查询
     *
     * @param id
     * @return
     */
    @Override
    public LuceneLast selectByProject(LuceneLast luceneLast) {
        LuceneLast result = this.selectOne("LucenelastMapper.selectByProject", luceneLast);
        if (result == null) {
            insertLuceneLast(luceneLast);
        }
        result = this.selectOne("LucenelastMapper.selectByProject", luceneLast);
        return result;
    }

    /**
     * 修改LuceneLast
     *
     * @param record
     * @return
     */
    @Override
    public Long updateLuceneLast(LuceneLast luceneLast) {
        return this.update("LucenelastMapper.updateLuceneLast", luceneLast);
    }

    /**
     * 修改LuceneLast都为0
     *
     * @return
     */
    @Override
    public boolean updateLuceneAllClear() {
        this.update("LucenelastMapper.clearLuceneLastData", null);
        return true;
    }

}
