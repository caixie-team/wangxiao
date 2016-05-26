package com.atdld.os.sns.service.impl.lucene;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser.Operator;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.Filter;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.MatchAllDocsQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TermRangeFilter;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.atdld.os.core.util.DateUtils;
import com.atdld.os.core.util.ObjectUtils;
import com.atdld.os.core.util.StringUtils;
import com.atdld.os.core.util.lucene.IndexDatasourcePathUtil;
import com.atdld.os.core.util.lucene.QueryWordFilter;
import com.atdld.os.sns.constants.LuceneConstans;
import com.atdld.os.sns.entity.blog.BlogBlog;
import com.atdld.os.sns.entity.suggest.SugSuggest;
import com.atdld.os.sns.entity.weibo.WeiBo;
import com.atdld.os.sns.util.SNSSimilarity;

/**
 * @author
 * @ClassName IndexSearcherFactory
 * @package com.atdld.open.exam.service.impl.lucene
 * @description
 * @Create Date: 2013-11-30 下午9:41:58
 */
public class IndexSearcherFactory {

    private IndexSearcherFactory() {
    }

    // 分词器
    private static Analyzer analyzer_index = new IKAnalyzer(true);

    // 分词器
    private static Analyzer analyzer_indexBak = new IKAnalyzer();

    private static Logger logger = Logger.getLogger(IndexSearcherFactory.class);

    // indexsearcher缓存
    private static Map<String, IndexSearcher> indexSearcherCache = new HashMap<String, IndexSearcher>();

    // 索引修改时间
    private static Map<String, Long> indexModifyTime = new HashMap<String, Long>();

    private static IndexSearcherFactory factory = null;

    private synchronized Long getIndexModifyTime(String city_en_name) {
        return indexModifyTime.get(city_en_name);
    }

    public synchronized static IndexSearcherFactory getInstance() {
        if (factory == null) {
            factory = new IndexSearcherFactory();
        }
        return factory;
    }

    /**
     * 设置indexsearch缓存,索引创建时间
     *
     * @param key
     * @param indexSearcher
     * @time Nov 24, 2011 11:04:44 AM
     */
    private synchronized void setIndexSearcher(String type, IndexSearcher indexSearcher, Long modifyTime) {
        indexSearcherCache.put(type, indexSearcher);
        indexModifyTime.put(type, modifyTime);
    }

    /**
     * 获取IndexSearcher
     *
     * @param indexdir
     * @param type
     * @return
     * @throws Exception
     */
    private synchronized IndexSearcher createIndexSearcher(String indexdir, String type) throws Exception {
        IndexSearcher indexSearcher = indexSearcherCache.get(type);
        Long indexCreateTime = null;
        if (indexSearcher == null) {
            File index = new File(indexdir);
            Directory directory = FSDirectory.open(index);
            IndexReader reader = DirectoryReader.open(directory);
            indexSearcher = new IndexSearcher(reader);
            indexCreateTime = index.lastModified();
            setIndexSearcher(type, indexSearcher, indexCreateTime);
        } else {
            // 判断索引创建时间
            File indextime = new File(indexdir);
            indexCreateTime = indextime.lastModified();
            if (indexCreateTime > getIndexModifyTime(type)) {
                // 关闭旧indexsearcher
                indexSearcher.getIndexReader().close();
                File index = new File(indexdir);
                Directory directory = FSDirectory.open(index);
                IndexReader reader = DirectoryReader.open(directory);
                indexSearcher = new IndexSearcher(reader);
                indexCreateTime = index.lastModified();
                setIndexSearcher(type, indexSearcher, indexCreateTime);
            }
        }
        return indexSearcher;
    }

    /**
     * @param key
     * @param indexdir    索引目录
     * @param indexdirbak 索引备份目录
     * @return IndexSearcher
     * @throws Exception
     * @time Nov 24, 2011 2:24:10 PM
     */
    public synchronized IndexSearcher getIndexSearcher(String type, String indexdir, String indexdirbak) throws Exception {
        try {
            return createIndexSearcher(indexdir, type);
        } catch (Exception e) {
            // 发生异常尝试备份索引
            return createIndexSearcher(indexdirbak, type);
        }
    }

    /**
     * @param key
     * @param indexdir    索引目录
     * @param indexdirbak 索引备份目录
     * @return IndexSearcher
     * @throws Exception
     * @time Nov 24, 2011 2:24:10 PM
     */
    public synchronized IndexSearcher getIndexSearcherBak(String type, String indexdirbak) throws Exception {
        return createIndexSearcher(indexdirbak, type);
    }

    // 根据行数获得页数
    public static Long getPageSize(Long count) {
        Long pages = 0L;
        if (count % LuceneConstans.LUCENE_CREATE_PAGE_SIZE == 0) {
            pages = count / LuceneConstans.LUCENE_CREATE_PAGE_SIZE;
        } else {
            pages = (count / LuceneConstans.LUCENE_CREATE_PAGE_SIZE) + 1L;
        }
        return pages;
    }

    // 根据页码获得分页的最后一个
    public static int nextPageOffset(int currentPage, int pagesize, int hits) {
        int searchedhits = hits;
        int nextPageOffset = currentPage * pagesize;
        if (nextPageOffset >= searchedhits) {
            nextPageOffset = searchedhits;
        }
        return nextPageOffset;
    }

    /**
     * lucene对象转换为document对象
     *
     * @param indexVOList
     * @return
     */
    @SuppressWarnings("unchecked")
    public static List<Document> convertVO2Doc(List<?> indexVOList, String type) {
        List<Document> list = new ArrayList<Document>();
        if ((LuceneConstans.LUCENE_SEARCH_TYPE_WEIBO).equals(type)) {// 微博
            WeiBo temp = null;
            List<WeiBo> webList = (List<WeiBo>) indexVOList;
            for (Iterator<WeiBo> iterator = webList.iterator(); iterator.hasNext(); ) {
                temp = (WeiBo) iterator.next();
                if (StringUtils.isNotEmpty(temp.getContent())) {
                    Document document = new Document();
                    // id
                    Field idField = new StringField(LuceneConstans.LUCENE_ID, temp.getId().toString(), Field.Store.YES);
                    document.add(idField);
                    // context
                    Field contentField = new TextField(LuceneConstans.WEIBOFIELD[0], temp.getContent(), Field.Store.YES);
                    document.add(contentField);
                    // user
                    Field userField = new StringField(LuceneConstans.WEIBOFIELD[1], temp.getShowname(), Field.Store.YES);
                    document.add(userField);

                    // date
                    Field dateField = new TextField(LuceneConstans.WEIBOFIELD[2], "" + DateUtils.formatDate(temp.getAddTime(), "yyyyMMddHHmmss"), Field.Store.YES);
                    document.add(dateField);

                    list.add(document);
                }

            }
        } else if ((LuceneConstans.LUCENE_SEARCH_TYPE_SUGGEST).equals(type)) {// 建议
            List<SugSuggest> sugList = (List<SugSuggest>) indexVOList;
            SugSuggest temp = null;
            for (Iterator<SugSuggest> iterator = sugList.iterator(); iterator.hasNext(); ) {
                temp = (SugSuggest) iterator.next();
                if (StringUtils.isNotEmpty(temp.getTitle()) && StringUtils.isNotEmpty(temp.getContent())) {
                    Document document = new Document();
                    // id
                    Field idField = new StringField(LuceneConstans.LUCENE_ID, temp.getId().toString(), Field.Store.YES);
                    document.add(idField);

                    // title字段分析并存储
                    Field titleField = new TextField(LuceneConstans.SUGGESTFIELD[0], temp.getTitle(), Field.Store.YES);
                    titleField.setBoost(100.0f);
                    document.add(titleField);
                    // context字段分析并存储
                    Field contentField = new TextField(LuceneConstans.SUGGESTFIELD[1], temp.getContent(), Field.Store.NO);
                    contentField.setBoost(1.0f);
                    document.add(contentField);
                    // user
                    Field userField = new StringField(LuceneConstans.SUGGESTFIELD[2], "" + temp.getShowname(), Field.Store.YES);
                    document.add(userField);
                    // date
                    Field dateField = new TextField(LuceneConstans.SUGGESTFIELD[3], "" + DateUtils.formatDate(temp.getAddtime(), "yyyyMMddHHmmss"), Field.Store.YES);
                    document.add(dateField);
                    // type
                    Field typeField = new StringField(LuceneConstans.SUGGESTFIELD[5], "TYPE" + Long.valueOf(temp.getType()), Field.Store.YES);
                    document.add(typeField);

                    list.add(document);
                }

            }
            return list;
        } else if ((LuceneConstans.LUCENE_SEARCH_TYPE_BLOG).equals(type)) {// 文章
            List<BlogBlog> blogList = (List<BlogBlog>) indexVOList;
            BlogBlog temp = null;
            for (Iterator<BlogBlog> iterator = blogList.iterator(); iterator.hasNext(); ) {
                temp = (BlogBlog) iterator.next();
                if (StringUtils.isNotEmpty(temp.getTitle()) && StringUtils.isNotEmpty(temp.getContent())) {
                    Document document = new Document();
                    // id
                    Field idField = new StringField(LuceneConstans.LUCENE_ID, temp.getId().toString(), Field.Store.YES);
                    document.add(idField);

                    // title字段分析并存储
                    Field titleField = new TextField(LuceneConstans.BLOGFIELD[0], temp.getTitle(), Field.Store.YES);
                    titleField.setBoost(100.0f);
                    document.add(titleField);
                    // context字段分析并存储
                    Field contentField = new TextField(LuceneConstans.BLOGFIELD[1], temp.getContent(), Field.Store.NO);
                    contentField.setBoost(1.0f);
                    document.add(contentField);
                    // user
                    Field userField = new StringField(LuceneConstans.BLOGFIELD[2], "" + temp.getShowName(), Field.Store.YES);
                    document.add(userField);
                    // date
                    Field dateField = new TextField(LuceneConstans.BLOGFIELD[3], "" + DateUtils.formatDate(temp.getAddTime(), "yyyyMMddHHmmss"), Field.Store.YES);
                    document.add(dateField);
                    list.add(document);
                }

            }
            return list;
        }

        return list;
    }

    /**
     * 统一从lucene中查询只返回分页的数据的id集合
     *
     * @param keyword     关键字
     * @param searchType  搜索类型
     * @param searchArray 建立索引时的字段数据
     * @throws Exception
     * @returnMap<String, Object>
     */
    public static Map<String, Object> queryMapByKeyWord(Map<String, String> searchMap, String searchType) throws Exception {

        logger.info("lucenesearch type:" + searchType + ",:data:" + searchMap);
        Map<String, Object> searchedMap = new HashMap<String, Object>();
        String keyword = searchMap.get(LuceneConstans.LUCENE_SEARCH_KEYWORD);// 关键字
        keyword = QueryWordFilter.filterQueryWord(keyword);
        logger.info("+++QueryWordFilter.filterQueryWord:" + keyword);
        /*
         * if (StringUtils.isEmpty(keyword)) { return searchedMap; }
         */

        List<Long> qstidList = new ArrayList<Long>();
        // lucene索引物理路径
        String indexDir = IndexDatasourcePathUtil.getIndexDir(searchType);
        // lucene索引备份物理路径
        String indexDirBak = IndexDatasourcePathUtil.getIndexDirBak(searchType);

        IndexSearcher indexSearcher = IndexSearcherFactory.getInstance().getIndexSearcher(searchType, indexDir, indexDirBak);
        IndexSearcher indexSearcherBak = IndexSearcherFactory.getInstance().getIndexSearcherBak(searchType, indexDirBak);

        try {
            indexSearcher.setSimilarity(new SNSSimilarity());
            // 搜索条件Query
            BooleanQuery booleanQuery = getBooleanQuery(searchMap, searchType, analyzer_index);
            BooleanQuery booleanQueryBak = getBooleanQuery(searchMap, searchType, analyzer_indexBak);
            // 时间过滤条件限定
            String date = searchMap.get(LuceneConstans.LUCENE_SEARCH_DATE);// 时间只传年月日
            Filter filter = null;
            if (StringUtils.isNotEmpty(date)) {
                date = date.replaceAll("-", "");
                filter = TermRangeFilter.newStringRange(LuceneConstans.LUCENE_SEARCH_DATE, date + "000000", date + "235959", true, true);
            }
            // 排序
            Sort sort = new Sort();
            sort.setSort(SortField.FIELD_SCORE);
            TopDocs resultDoc = null;
            TopDocs resultDocBak = null;
            if (booleanQuery.getClauses().length > 0) {// 有搜索条件
                if (filter == null) {
                    resultDoc = indexSearcher.search(booleanQuery, LuceneConstans.LUCENE_MAX_ROWS.intValue(), sort);
                } else {
                    resultDoc = indexSearcher.search(booleanQuery, filter, LuceneConstans.LUCENE_MAX_ROWS.intValue(), sort);
                }
                if (filter == null) {
                    resultDocBak = indexSearcherBak.search(booleanQueryBak, LuceneConstans.LUCENE_MAX_ROWS.intValue(), sort);
                } else {
                    resultDocBak = indexSearcherBak.search(booleanQueryBak, filter, LuceneConstans.LUCENE_MAX_ROWS.intValue(), sort);
                }
            } else {// 无条件时，显示5000条记录
                sort.setSort(new SortField(LuceneConstans.LUCENE_ID, SortField.Type.LONG, true));
                if (filter == null) {
                    // 无条件时，显示5000条记录
                    resultDoc = indexSearcher.search(new MatchAllDocsQuery(), LuceneConstans.LUCENE_MAX_ROWS.intValue(), sort);
                } else {//只按时间搜索
                    resultDoc = indexSearcher.search(new MatchAllDocsQuery(), filter, LuceneConstans.LUCENE_MAX_ROWS.intValue(), sort);
                }
            }

            Set<Long> tmmSet = new HashSet<Long>();
            if (ObjectUtils.isNotNull(resultDoc)) {
                ScoreDoc[] hits = resultDoc.scoreDocs;
                for (int andi = 0; andi < hits.length; andi++) {
                    //System.out.println("explain:"+indexSearcher.explain(booleanQuery, hits[andi].doc));
                    Long id = Long.valueOf(indexSearcher.doc(hits[andi].doc).get(LuceneConstans.LUCENE_ID));
                    if (!tmmSet.contains(id.longValue())) {
                        tmmSet.add(id);
                        qstidList.add(id);
                    }
                }
            }

            //bak
            if (ObjectUtils.isNotNull(resultDocBak)) {
                ScoreDoc[] hitsBak = resultDocBak.scoreDocs;
                for (int ori = 0; ori < hitsBak.length; ori++) {
                    Long id = Long.valueOf(indexSearcher.doc(hitsBak[ori].doc).get(LuceneConstans.LUCENE_ID));
                    if (!tmmSet.contains(id.longValue())) {
                        tmmSet.add(id);
                        qstidList.add(id);
                    }
                }
            }
            logger.info("+++++booleanQuery all:" + booleanQuery + ", hits :" + qstidList.size());

            searchedMap.put(LuceneConstans.LUCENE_SEARCH_RESULTS_COUNT, qstidList.size());
            searchedMap.put(LuceneConstans.LUCENE_SEARCHED_RESULT_IDS, qstidList);

        } catch (Exception e) {
            logger.error("+++ search  error[" + keyword + "]", e);
            throw new RuntimeException("lucene search  error");
        } finally {
            try {
                // indexSearcher.getIndexReader().close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return searchedMap;

    }

    /**
     * 统一创建索引
     *
     * @param datalist   数据类型
     * @param searchType 索引类型
     */
    public static void indexCreate(List<?> datalist, String searchType) {
        logger.info("indexCreate,lucene Type:" + searchType + ",size:" + datalist.size());
        // lucene索引物理路径
        String indexDir = IndexDatasourcePathUtil.getIndexDir(searchType);
        // lucene索引备份物理路径
        String indexDirBak = IndexDatasourcePathUtil.getIndexDirBak(searchType);

        if (ObjectUtils.isNull(datalist)) {
            return;
        }
        IndexWriter indexWriter = null;
        Directory directory = null;
        try {
            // 索引生成
            directory = FSDirectory.open(new File(indexDir));
            IndexWriterConfig iwc = new IndexWriterConfig(LuceneConstans.version, analyzer_index);
            iwc.setOpenMode(OpenMode.CREATE_OR_APPEND);
            indexWriter = new IndexWriter(directory, iwc);
            // 转换为索引文档
            List<Document> documents = convertVO2Doc(datalist, searchType);
            for (Iterator<Document> iterator = documents.iterator(); iterator.hasNext(); ) {
                indexWriter.addDocument(iterator.next(), analyzer_index);
            }
            indexWriter.close();
            directory.close();
            // 备份索引生成
            IndexWriterConfig iwcBak = new IndexWriterConfig(LuceneConstans.version, analyzer_indexBak);
            iwcBak.setOpenMode(OpenMode.CREATE_OR_APPEND);
            directory = FSDirectory.open(new File(indexDirBak));
            indexWriter = new IndexWriter(directory, iwcBak);
            for (Iterator<Document> iterator = documents.iterator(); iterator.hasNext(); ) {
                indexWriter.addDocument(iterator.next(), analyzer_indexBak);
            }
            indexWriter.close();
            directory.close();

        } catch (IOException e) {
            logger.error("create lucene index error,searchType:" + searchType + ",", e);
            throw new RuntimeException("create lucene index exception,searchType:" + searchType);
        } finally {
            try {
                if (ObjectUtils.isNotNull(indexWriter)) {
                    indexWriter.close();
                }
                if (ObjectUtils.isNotNull(directory)) {
                    directory.close();
                }
            } catch (CorruptIndexException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 拼凑出查询条件Query
     *
     * @param searchMap
     * @param searchType
     * @param searchArray
     * @return
     * @throws ParseException
     */
    private static BooleanQuery getBooleanQuery(Map<String, String> searchMap, String searchType, Analyzer analyzer) throws Exception {

        String keyword = searchMap.get(LuceneConstans.LUCENE_SEARCH_KEYWORD);// 关键字
        keyword = QueryWordFilter.filterQueryWord(keyword);
        String showname = searchMap.get(LuceneConstans.LUCENE_SEARCH_USER);// 用户
        BooleanQuery booleanQuery = new BooleanQuery();
        // 博文。
        if (searchType.equals(LuceneConstans.LUCENE_SEARCH_TYPE_BLOG)) {
            if (StringUtils.isNotEmpty(keyword)) {
                // 关键字搜索内容
                /*
                 * Term term_keyword = new Term(LuceneConstans.BLOGFIELD[1],
                 * keyword); Query query_key = new TermQuery(term_keyword);
                 * booleanQuery.add(query_key, Occur.MUST);
                 */
                // 关键字搜索标题+内容
                MultiFieldQueryParser queryParser = new MultiFieldQueryParser(Version.LUCENE_46, new String[]{LuceneConstans.BLOGFIELD[0], LuceneConstans.BLOGFIELD[1]}, analyzer);// 关键字搜建议标题和内容
                queryParser.setDefaultOperator(Operator.OR);
                Query query_keyword = queryParser.parse(keyword);
                booleanQuery.add(query_keyword, Occur.MUST);
            } else {
                // 搜索标题
                String title = searchMap.get(LuceneConstans.BLOGFIELD[0]);
                title = QueryWordFilter.filterQueryWord(title);
                if (StringUtils.isNotEmpty(title)) {
                    MultiFieldQueryParser queryParser = new MultiFieldQueryParser(Version.LUCENE_46, new String[]{LuceneConstans.BLOGFIELD[0]}, analyzer);
                    Query query_keyword = queryParser.parse(title);
                    booleanQuery.add(query_keyword, Occur.MUST);

                    /*
                     * Term term_title = new Term(LuceneConstans.BLOGFIELD[0],
                     * title); Query query_title = new TermQuery(term_title);
                     * booleanQuery.add(query_title, Occur.MUST);
                     */
                }
                // 用户
                if (StringUtils.isNotEmpty(showname)) {
                    Term term = new Term(LuceneConstans.BLOGFIELD[2], showname);
                    Query query_user = new TermQuery(term);
                    booleanQuery.add(query_user, Occur.MUST);
                }
            }

        } else if (searchType.equals(LuceneConstans.LUCENE_SEARCH_TYPE_WEIBO)) {// 微博
            // 关键字搜索标题和内容
            if (StringUtils.isNotEmpty(keyword)) {
                MultiFieldQueryParser queryParser = new MultiFieldQueryParser(Version.LUCENE_46, new String[]{LuceneConstans.WEIBOFIELD[0]}, analyzer);
                queryParser.setDefaultOperator(Operator.OR);
                Query query_keyword = queryParser.parse(keyword);
                booleanQuery.add(query_keyword, Occur.MUST);
            } else {
                // 用户
                if (StringUtils.isNotEmpty(showname)) {
                    Term term = new Term(LuceneConstans.WEIBOFIELD[1], showname);
                    Query query_user = new TermQuery(term);
                    booleanQuery.add(query_user, Occur.MUST);
                }
            }

        } else if (searchType.equals(LuceneConstans.LUCENE_SEARCH_TYPE_SUGGEST)) {// 建议
            // 关键字搜索标题+内容
            /*
             * MultiFieldQueryParser queryParser = new
             * MultiFieldQueryParser(Version.LUCENE_46, new String[] {
             * LuceneConstans.SUGGESTFIELD[0], LuceneConstans.SUGGESTFIELD[1] },
             * analyzer);// 关键字搜建议标题和内容
             * queryParser.setDefaultOperator(Operator.OR); Query query_keyword
             * = queryParser.parse(keyword); booleanQuery.add(query_keyword,
             * Occur.MUST);
             */
            // 关键字搜索内容
            if (StringUtils.isNotEmpty(keyword)) {
                /*
                 * Term term_keyword = new Term(LuceneConstans.SUGGESTFIELD[1],
                 * keyword); Query query_key = new TermQuery(term_keyword);
                 * booleanQuery.add(query_key, Occur.MUST);
                 */
                // 关键字搜索内容
                MultiFieldQueryParser queryParser = new MultiFieldQueryParser(Version.LUCENE_46, new String[]{LuceneConstans.SUGGESTFIELD[0], LuceneConstans.SUGGESTFIELD[1]}, analyzer);// 关键字搜建议标题和内容
                queryParser.setDefaultOperator(Operator.OR);
                Query query_keyword = queryParser.parse(keyword);
                booleanQuery.add(query_keyword, Occur.MUST);

            } else {
                String title = searchMap.get(LuceneConstans.SUGGESTFIELD[0]);
                title = QueryWordFilter.filterQueryWord(title);
                if (StringUtils.isNotEmpty(title)) {
                    // 搜索标题+内容
                    MultiFieldQueryParser queryParser = new MultiFieldQueryParser(Version.LUCENE_46, new String[]{LuceneConstans.SUGGESTFIELD[0]}, analyzer);
                    queryParser.setDefaultOperator(Operator.OR);
                    Query query_keyword = queryParser.parse(title);
                    booleanQuery.add(query_keyword, Occur.MUST);
                    // 搜索标题
                    /*
                     * Term term_title = new
                     * Term(LuceneConstans.SUGGESTFIELD[0], title); Query
                     * query_title = new TermQuery(term_title);
                     * booleanQuery.add(query_title, Occur.MUST);
                     */
                }
                // 用户
                if (StringUtils.isNotEmpty(showname)) {
                    Term term = new Term(LuceneConstans.SUGGESTFIELD[2], showname);
                    Query query_user = new TermQuery(term);
                    booleanQuery.add(query_user, Occur.MUST);
                }
                // acceptshowname
                if (StringUtils.isNotEmpty(searchMap.get(LuceneConstans.SUGGESTFIELD[4]))) {
                    Term term1 = new Term(LuceneConstans.SUGGESTFIELD[4], searchMap.get(LuceneConstans.SUGGESTFIELD[4]));
                    Query query_acceptshowname = new TermQuery(term1);
                    booleanQuery.add(query_acceptshowname, Occur.MUST);
                }
                // type
                if (StringUtils.isNotEmpty(searchMap.get(LuceneConstans.SUGGESTFIELD[5])) && !"0".equals(searchMap.get(LuceneConstans.SUGGESTFIELD[5]))) {
                    Term term2 = new Term(LuceneConstans.SUGGESTFIELD[5], "TYPE" + searchMap.get(LuceneConstans.SUGGESTFIELD[5]));
                    Query query_type = new TermQuery(term2);
                    booleanQuery.add(query_type, Occur.MUST);
                }
            }

        }
        return booleanQuery;

    }

    /**
     * 删除一个索引
     *
     * @param id
     * @param searchType 索引类型
     */
    public static void indexDeleteOne(Long id, String searchType) {

        if (ObjectUtils.isNull(id)) {
            return;
        }
        logger.info("indexDeleteOne searchType :" + searchType + ",id:" + id);
        // lucene索引物理路径
        String indexDir = IndexDatasourcePathUtil.getIndexDir(searchType);
        // lucene索引备份物理路径
        String indexDirBak = IndexDatasourcePathUtil.getIndexDirBak(searchType);
        IndexWriter indexWriter = null;
        Directory directory = null;
        try {
            // 先查询是否存在
            IndexSearcher indexSearcher = IndexSearcherFactory.getInstance().getIndexSearcher(searchType, indexDir, indexDirBak);
            BooleanQuery bQuery = new BooleanQuery();
            TermQuery termQuery = new TermQuery(new Term(LuceneConstans.LUCENE_ID, id.toString()));
            bQuery.add(termQuery, Occur.MUST);
            TopDocs resultDoc = indexSearcher.search(bQuery, indexSearcher.getIndexReader().maxDoc(), new Sort(SortField.FIELD_SCORE));
            logger.info("+++bQuery totalHits:" + resultDoc.totalHits);
            if (resultDoc.totalHits == 0) {// 不存在，还未生成直接返回
                logger.info("indexDeleteOne not exists :" + searchType + ",id:" + id);
                return;
            }

            directory = FSDirectory.open(new File(indexDir));
            IndexWriterConfig iwc = new IndexWriterConfig(LuceneConstans.version, analyzer_index);
            iwc.setOpenMode(OpenMode.CREATE_OR_APPEND);
            indexWriter = new IndexWriter(directory, iwc);
            indexWriter.deleteDocuments(new Term(LuceneConstans.LUCENE_ID, id.toString()));
            indexWriter.close();
            directory.close();
            // 备份索引 目录
            IndexWriterConfig iwcBak = new IndexWriterConfig(LuceneConstans.version, analyzer_indexBak);
            iwcBak.setOpenMode(OpenMode.CREATE_OR_APPEND);
            directory = FSDirectory.open(new File(indexDirBak));
            indexWriter = new IndexWriter(directory, iwcBak);
            indexWriter.deleteDocuments(new Term(LuceneConstans.LUCENE_ID, id.toString()));
            indexWriter.close();
            directory.close();
            logger.info("indexDeleteOne delete success :" + searchType + ",id:" + id);
        } catch (Exception e) {
            logger.error("indexDeleteOne index error:" + indexDir, e);
            throw new RuntimeException("indexDeleteOne error");
        } finally {
            try {
                if (ObjectUtils.isNotNull(indexWriter)) {
                    indexWriter.close();
                }
                if (ObjectUtils.isNotNull(directory)) {
                    directory.close();
                }
            } catch (CorruptIndexException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * @param id
     * @param object     索引的对象
     * @param searchType 类型
     */
    public static void indexUpdateOne(Long id, Object object, String searchType) {

        if (ObjectUtils.isNull(object)) {
            return;
        }
        String indexDir = IndexDatasourcePathUtil.getIndexDir(searchType);
        // lucene索引备份物理路径
        String indexDirBak = IndexDatasourcePathUtil.getIndexDirBak(searchType);

        logger.info("indexUpdateOne id:" + id + ",type=" + searchType);
        IndexWriter indexWriter = null;
        Directory directory = null;
        try {

            // 先查询是否存在
            IndexSearcher indexSearcher = IndexSearcherFactory.getInstance().getIndexSearcher(searchType, indexDir, indexDirBak);
            BooleanQuery bQuery = new BooleanQuery();
            TermQuery termQuery = new TermQuery(new Term(LuceneConstans.LUCENE_ID, id.toString()));
            bQuery.add(termQuery, Occur.MUST);
            TopDocs resultDoc = indexSearcher.search(bQuery, indexSearcher.getIndexReader().maxDoc(), new Sort(SortField.FIELD_SCORE));
            logger.info("+++bQuery totalHits:" + resultDoc.totalHits);
            if (resultDoc.totalHits == 0) {// 不存在，还未生成直接返回
                logger.info("indexUpdateOne not exists :" + searchType + ",id:" + id);
                return;
            }
            // 存在时更新索引
            directory = FSDirectory.open(new File(indexDir));
            IndexWriterConfig iwc = new IndexWriterConfig(LuceneConstans.version, analyzer_index);
            iwc.setOpenMode(OpenMode.CREATE_OR_APPEND);
            indexWriter = new IndexWriter(directory, iwc);

            List<Object> indexVOList = new ArrayList<Object>();
            indexVOList.add(object);
            // 调用通用的生成doc方法
            List<Document> documents = convertVO2Doc(indexVOList, searchType);
            indexWriter.updateDocument(new Term(LuceneConstans.LUCENE_ID, id.toString()), documents.get(0));

            indexWriter.close();
            directory.close();
            // 备份索引 目录

            IndexWriterConfig iwcBak = new IndexWriterConfig(LuceneConstans.version, analyzer_indexBak);
            iwcBak.setOpenMode(OpenMode.CREATE_OR_APPEND);
            directory = FSDirectory.open(new File(indexDirBak));
            indexWriter = new IndexWriter(directory, iwcBak);
            indexWriter.updateDocument(new Term(LuceneConstans.LUCENE_ID, id.toString()), documents.get(0));

            indexWriter.close();
            directory.close();
            logger.info("indexUpdateOne success :" + searchType + ",id:" + id);
        } catch (Exception e) {
            logger.error("indexUpdateOne index error:" + indexDir, e);
            throw new RuntimeException("indexUpdateOne error");
        } finally {
            try {
                if (ObjectUtils.isNotNull(indexWriter)) {
                    indexWriter.close();
                }
                if (ObjectUtils.isNotNull(directory)) {
                    directory.close();
                }
            } catch (CorruptIndexException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
