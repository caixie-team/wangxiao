package com.atdld.os.sns.constants;

import org.apache.lucene.util.Version;

/**
 * @author :
 * @ClassName com.atdld.os.sns.entity.constans.LuceneConstans
 * @description
 * @Create Date : 2014-3-26 上午10:14:38
 */
public class LuceneConstans {
    /**
     * lucene版本号
     */
    public static final Version version = Version.LUCENE_46;
    /**
     * lucene所用接口返回的map的key
     */
    public static final String LUCENE_LINE_NUM = "lineNum";// 行数的key
    public static final String LUCENE_MAX_ID = "maxId";// 最大id的key
    /**
     * lucene相关常量定义
     */
    public static final Long LUCENE_MAX_ROWS = 5000L;// 生成索引查询返回的最大行数
    // 搜索类型
    public static final String LUCENE_SEARCH_TYPE_WEIBO = "weibo";// 微博
    public static final String LUCENE_SEARCH_TYPE_BLOG = "blog";// 文章
    public static final String LUCENE_SEARCH_TYPE_SUGGEST = "suggest";// 建议

    public static final Long LUCENE_PAGE_SIZE = 10L;// 搜索结果分页大小
    public static final Long LUCENE_CREATE_PAGE_SIZE = 1000L;// 生成索引时分页大小
    // 搜索结果key值
    public final static String LUCENE_SEARCH_RESULTS_COUNT = "searchedResultsCount";// 存储结果
    public final static String LUCENE_SEARCHED_RESULT_IDS = "searchedids";// 搜索到的id集合
    // 搜索微博的字段
    public static final String[] WEIBOFIELD = {"content", "showname", "date"};
    // 搜索建议的字段
    public static final String[] SUGGESTFIELD = {"title", "content", "showname", "date", "acceptshowname", "type"};
    // 搜索博文的字段
    public static final String[] BLOGFIELD = {"title", "content", "showname", "date"};
    // 存到lucene重点主键
    public static final String LUCENE_ID = "id";
    // 搜索用到的关键字类型
    public static final String LUCENE_SEARCH_KEYWORD = "keyword";// 关键字
    public static final String LUCENE_SEARCH_USER = "showname";// 用户
    public static final String LUCENE_SEARCH_DATE = "date";// 时间

    public static final String LUCENE_CURRID = "currid";// 时间


}
