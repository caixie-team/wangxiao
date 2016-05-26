package com.atdld.os.sns.service.impl.lucene;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atdld.os.core.util.lucene.IndexDatasourcePathUtil;
import com.atdld.os.sns.dao.lucene.LuceneLastDao;
import com.atdld.os.sns.entity.lucene.LuceneLast;
import com.atdld.os.sns.service.lucene.LuceneLastNumService;

/**
 * @author :
 * @ClassName com.atdld.os.sns.service.impl.lucene.LuceneLastNumServiceImpl
 * @description
 * @Create Date : 2014-1-3 下午3:28:59
 */
@Service("luceneLastNumService")
public class LuceneLastNumServiceImpl implements LuceneLastNumService {
    @Autowired
    private LuceneLastDao luceneLastDao;

    /**
     * 根据Project查询
     *
     * @param id
     * @return
     */
    public LuceneLast gainSelectByProject(LuceneLast luceneLast) {
        return luceneLastDao.selectByProject(luceneLast);
    }

    /**
     * 修改LuceneLast
     *
     * @param record
     * @return
     */
    public Long updateLuceneLast(LuceneLast luceneLast) {
        return luceneLastDao.updateLuceneLast(luceneLast);
    }

    /**
     * 修改LuceneLast 都置为0
     */
    public boolean updateLuceneLastClear() {
        //删除lucen目录
        try {
            FileUtils.cleanDirectory(new File(IndexDatasourcePathUtil.getIndexDir("")));
            FileUtils.cleanDirectory(new File(IndexDatasourcePathUtil.getIndexDirBak("")));
        } catch (IOException e) {
            e.printStackTrace();
        }
//        deleteDir(IndexDatasourcePathUtil.getIndexDir(LuceneConstans.LUCENE_SEARCH_TYPE_SUGGEST));
//        deleteDir(IndexDatasourcePathUtil.getIndexDirBak(LuceneConstans.LUCENE_SEARCH_TYPE_SUGGEST));
//        deleteDir(IndexDatasourcePathUtil.getIndexDir(LuceneConstans.LUCENE_SEARCH_TYPE_WEIBO));
//        deleteDir(IndexDatasourcePathUtil.getIndexDirBak(LuceneConstans.LUCENE_SEARCH_TYPE_WEIBO));
//        deleteDir(IndexDatasourcePathUtil.getIndexDir(LuceneConstans.LUCENE_SEARCH_TYPE_BLOG));
//        deleteDir(IndexDatasourcePathUtil.getIndexDir(LuceneConstans.LUCENE_SEARCH_TYPE_BLOG));
        return luceneLastDao.updateLuceneAllClear();
    }


}
