package com.atdld.os.sns.service.impl.lucene;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.LongField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.atdld.os.core.entity.PageEntity;
import com.atdld.os.core.util.DateUtils;
import com.atdld.os.core.util.ObjectUtils;
import com.atdld.os.core.util.lucene.IndexDatasourcePathUtil;
import com.atdld.os.sns.constants.LuceneConstans;
import com.atdld.os.sns.entity.customer.SnsUserExpandDto;
import com.atdld.os.sns.entity.lucene.LuceneLast;
import com.atdld.os.sns.entity.weibo.QueryWeiBo;
import com.atdld.os.sns.entity.weibo.WeiBo;
import com.atdld.os.sns.service.lucene.LuceneLastNumService;
import com.atdld.os.sns.service.lucene.LuceneSearchWeiBoService;
import com.atdld.os.sns.service.user.SnsUserService;
import com.atdld.os.sns.service.weibo.WeiBoService;

/**
 * @author :
 * @ClassName com.atdld.os.sns.service.impl.lucene.LuceneSearchWeiBoServiceImpl
 * @description
 * @Create Date : 2013-12-25 下午2:45:18
 */
@Service("luceneSearchWeiBoService")
public class LuceneSearchWeiBoServiceImpl implements LuceneSearchWeiBoService {

    // lucene索引物理路径
    public static String indexDir = IndexDatasourcePathUtil.getIndexDir(LuceneConstans.LUCENE_SEARCH_TYPE_WEIBO);
    // lucene索引备份物理路径
    public static String indexDirBak = IndexDatasourcePathUtil.getIndexDirBak(LuceneConstans.LUCENE_SEARCH_TYPE_WEIBO);

    @Autowired
    private LuceneLastNumService luceneLastNumService;
    @Autowired
    private WeiBoService weiBoService;
    @Autowired
    private SnsUserService snsUserService;
    Analyzer analyzer = new IKAnalyzer();

    private Logger logger = Logger.getLogger(LuceneSearchWeiBoServiceImpl.class);


    /**
     * 根据关键字查询微博，返回map: 总行数 当前页微博的list
     *
     * @param keyword     关键字
     * @param currentPage 当前页
     * @param pagesize    一次获取的行数
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public Map<String, Object> queryPageWeiBoByKeyWord(Map<String, String> searchMap, PageEntity page) throws Exception {
        // 查询所有的匹配的
        // this.queryAllWeiBoMapByKeyWord(keyword);
        Map<String, Object> tempMap = IndexSearcherFactory.queryMapByKeyWord(searchMap, LuceneConstans.LUCENE_SEARCH_TYPE_WEIBO);
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
        Long loginuserid = Long.valueOf(searchMap.get(LuceneConstans.LUCENE_CURRID));
        // 查询微博数据
        if (!ObjectUtils.isNull(searchedids)) {
            List<QueryWeiBo> WeiBoList = weiBoService.getLuceneWeiBoByIds(searchedids, loginuserid);
            String cusIds="";
            //循环博客查询showname
            if (ObjectUtils.isNotNull(WeiBoList)) {
                for (QueryWeiBo weibo : WeiBoList) {
                	cusIds+=weibo.getCusId()+",";
                }
                //通过用户id查询出customer的map
                Map<String, SnsUserExpandDto> map = snsUserService.getUserExpandsByCusId(cusIds);
                if(ObjectUtils.isNotNull(map)){
                    for (QueryWeiBo weibo : WeiBoList) {
                        SnsUserExpandDto customer=map.get(weibo.getCusId() .toString());
                        if(ObjectUtils.isNotNull(customer)){
                            weibo.setShowname(customer.getShowname());
                        }
                    } 
                }
                
            }
            tempMap.put(LuceneConstans.LUCENE_SEARCHED_RESULT_IDS, WeiBoList);
        }
        return tempMap;
    }

    /**
     * 微博索引生成（定时调用）
     */
    @Override
    public synchronized boolean indexInitForWeiBo() throws Exception {
        logger.info("WeiBO luceneCreate Start :" + DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss"));
        long s = System.currentTimeMillis();
        // 获取上次处理到的行数
        LuceneLast luceneLast = new LuceneLast();
        luceneLast.setProject(LuceneConstans.LUCENE_SEARCH_TYPE_WEIBO);
        luceneLast = luceneLastNumService.gainSelectByProject(luceneLast);
        Long minWeiBoId = luceneLast.getMaxid();
        // 获取微博总行数以及本次处理的结束的微博id
        Map<String, Object> map = weiBoService.getWeiBoCountAfterId(minWeiBoId);
        // 微博总行数本次处理的行数
        Long weiBoCount = Long.valueOf(map.get(LuceneConstans.LUCENE_LINE_NUM).toString());
        // 本次处理最后一条的id
        Long weiBoMaxId = Long.valueOf(map.get(LuceneConstans.LUCENE_MAX_ID).toString());
        if (weiBoCount == 0L || luceneLast.getMaxid().longValue() == weiBoMaxId.longValue()) {
            return false;
        }
        // 分页生成
        Long pages = IndexSearcherFactory.getPageSize(weiBoCount);
        for (Long begin = 0L; begin < pages; begin++) {
            // 从数据库中查询要创建的索引的数据
            Long beginRow = begin * LuceneConstans.LUCENE_CREATE_PAGE_SIZE;// 起始的行数
            List<WeiBo> weiBolist = weiBoService.getQuestionByPageQuery(beginRow, LuceneConstans.LUCENE_CREATE_PAGE_SIZE, minWeiBoId + 1, weiBoMaxId);
            // 统一生成索引方法
            IndexSearcherFactory.indexCreate(weiBolist, LuceneConstans.LUCENE_SEARCH_TYPE_WEIBO);
        }
        // 更新数据weiBoMaxId存到库中
        luceneLast.setMaxid(weiBoMaxId);
        luceneLastNumService.updateLuceneLast(luceneLast);
        logger.info("WeiBO LuceneCreate End,minWeiBoId :" + minWeiBoId + ",weiBoMaxId :" + weiBoMaxId + ",WeiBoCount :" + weiBoCount + ",times:" + (System.currentTimeMillis() - s) / 1000);
        return true;
    }

    /**
     * 删除微博索引(demo,未用)
     */
    @Override
    public void indexDeleteForWeiBo(List<WeiBo> WeiBolist) throws Exception {
        if (WeiBolist == null || WeiBolist.size() == 0) {
            return;
        }
        logger.info("iindex Delete For WeiBo WeiBoCount:" + WeiBolist.size());
        IndexWriter indexWriter = null;
        Directory directory = null;
        try {
            directory = FSDirectory.open(new File(indexDir));
            IndexWriterConfig iwc = new IndexWriterConfig(LuceneConstans.version, analyzer);
            iwc.setOpenMode(OpenMode.CREATE_OR_APPEND);
            indexWriter = new IndexWriter(directory, iwc);
            for (WeiBo weiBo : WeiBolist) {
                Document doc = new Document();
                // id
                Field idField = new LongField(LuceneConstans.LUCENE_ID, weiBo.getId(), Field.Store.YES);
                doc.add(idField);
                indexWriter.deleteDocuments(new Term(LuceneConstans.LUCENE_ID, weiBo.getId() + ""));
            }
            indexWriter.close();
            directory.close();
            // 备份索引 目录
            String indexDirBak = IndexDatasourcePathUtil.getIndexDirBak(LuceneConstans.LUCENE_SEARCH_TYPE_WEIBO);
            IndexWriterConfig iwcBak = new IndexWriterConfig(LuceneConstans.version, analyzer);
            iwcBak.setOpenMode(OpenMode.CREATE_OR_APPEND);
            directory = FSDirectory.open(new File(indexDirBak));
            indexWriter = new IndexWriter(directory, iwcBak);
            for (WeiBo WeiBo : WeiBolist) {
                indexWriter.deleteDocuments(new Term(LuceneConstans.LUCENE_ID, WeiBo.getId() + ""));
            }
            indexWriter.close();
            directory.close();
            // 备份索引 目录
        } catch (IOException e) {
            logger.error("indexDeleteForWeiBo index error:" + indexDir, e);
            throw new RuntimeException("indexDeleteForWeiBo error");
        } finally {
            try {
                indexWriter.close();
                directory.close();
            } catch (CorruptIndexException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 更新微博索引(demo，未用)
     */
    @Override
    public void indexUpdateForWeiBo(List<WeiBo> WeiBolist) throws Exception {
        if (WeiBolist == null || WeiBolist.size() == 0) {
            return;
        }
        String indexDir = IndexDatasourcePathUtil.getIndexDir(LuceneConstans.LUCENE_SEARCH_TYPE_WEIBO);
        logger.info("indexUpdateForWeiBo_indexdir=" + indexDir + ",type=" + LuceneConstans.LUCENE_SEARCH_TYPE_WEIBO + ",WeiBoCount:" + WeiBolist.size());
        IndexWriter indexWriter = null;
        Directory directory = null;
        try {
            directory = FSDirectory.open(new File(indexDir));
            IndexWriterConfig iwc = new IndexWriterConfig(LuceneConstans.version, analyzer);
            iwc.setOpenMode(OpenMode.CREATE_OR_APPEND);
            indexWriter = new IndexWriter(directory, iwc);
            for (WeiBo weiBo : WeiBolist) {
                Document doc = new Document();
                // Content
                Field ContentField = new TextField(LuceneConstans.WEIBOFIELD[0], weiBo.getContent(), Field.Store.YES);
                doc.add(ContentField);
                // id
                Field idField = new LongField(LuceneConstans.LUCENE_ID, weiBo.getId(), Field.Store.YES);
                doc.add(idField);
                indexWriter.updateDocument(new Term(LuceneConstans.LUCENE_ID, weiBo.getId() + ""), doc);
            }
            indexWriter.close();
            directory.close();
            // 备份索引 目录
            String indexDirBak = IndexDatasourcePathUtil.getIndexDirBak(LuceneConstans.LUCENE_SEARCH_TYPE_WEIBO);
            IndexWriterConfig iwcBak = new IndexWriterConfig(LuceneConstans.version, analyzer);
            iwcBak.setOpenMode(OpenMode.CREATE_OR_APPEND);
            directory = FSDirectory.open(new File(indexDirBak));
            indexWriter = new IndexWriter(directory, iwcBak);
            for (WeiBo weiBo : WeiBolist) {
                Document doc = new Document();
                Field ContentField = new TextField(LuceneConstans.WEIBOFIELD[0], weiBo.getContent(), Field.Store.YES);
                doc.add(ContentField);
                Field idField = new LongField(LuceneConstans.LUCENE_ID, weiBo.getId(), Field.Store.YES);
                doc.add(idField);
                indexWriter.updateDocument(new Term(LuceneConstans.LUCENE_ID, weiBo.getId() + ""), doc);
            }
            indexWriter.close();
            directory.close();
            // 备份索引 目录
        } catch (IOException e) {
            logger.error("indexUpdateForWeiBo index error:" + indexDir, e);
            throw new RuntimeException("indexUpdateForWeiBo erro");
        } finally {
            try {
                indexWriter.close();
                directory.close();
            } catch (CorruptIndexException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 根据微博的id在索引中获得数据(demo，未用)
     */
    public String getLuceneWeiBoByWeiBoId(int weiBoId) throws Exception {

        String indexdir = IndexDatasourcePathUtil.getIndexDir(LuceneConstans.LUCENE_SEARCH_TYPE_WEIBO);
        String indexdirbak = IndexDatasourcePathUtil.getIndexDirBak(LuceneConstans.LUCENE_SEARCH_TYPE_WEIBO);
        IndexSearcher indexSearcher = IndexSearcherFactory.getInstance().getIndexSearcher(LuceneConstans.LUCENE_SEARCH_TYPE_WEIBO, indexdir, indexdirbak);
        BooleanQuery bQuery = new BooleanQuery();
        TermQuery termQuery = new TermQuery(new Term(LuceneConstans.LUCENE_ID, weiBoId + ""));
        bQuery.add(termQuery, Occur.MUST);
        TopDocs resultDoc = indexSearcher.search(bQuery, indexSearcher.getIndexReader().maxDoc(), new Sort(SortField.FIELD_SCORE));
        ScoreDoc[] rs = resultDoc.scoreDocs;
        logger.info("+++bQuery totalHits:" + resultDoc.totalHits);
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < resultDoc.totalHits; i++) {
            logger.info("id:" + indexSearcher.doc(rs[i].doc).get(LuceneConstans.LUCENE_ID) + ",Context:" + indexSearcher.doc(rs[i].doc).get(LuceneConstans.WEIBOFIELD[0]));
            buffer.append(indexSearcher.doc(rs[i].doc).get(LuceneConstans.WEIBOFIELD[0]));
        }
        return buffer.toString();
    }
}
