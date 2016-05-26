package com.atdld.os.exam.constants;

import com.atdld.os.core.util.PropertyUtil;

/**
 * @author
 * @ClassName Constant
 * @package com.atdld.open.core.util
 * @description 统一常量定义
 * @Create Date: 2013-3-1 上午11:06:11
 */
public class ExamConstants {


    // 存用户的专业id
    public static final String COOKIE_SUBJECTID_KEY = "e.subject";
  
    // *****lucene常量定义，全部以LUCENE_开头
    public static final Long LUCENE_PAGE_SIZE = 1000L;// 生成索引时分页查询的行数

    // 搜索类型
    public static final String LUCENE_SEARCH_TYPE_QUESTION = "question";// 问题
    public static final String LUCENE_SEARCH_TYPE_ARTICLE = "article";// 文章
    public static final String LUCENE_SEARCH_TYPE_WEIBO = "weibo";// 微博
    public static final String LUCENE_SEARCH_TYPE_SUGGEST = "suggest";// 建议
    // 不同搜索结果分页大小
    public static final int LUCENE_PAGE_SIZE_QUESTION = 20;
    public static final int LUCENE_PAGE_SIZE_ARTICLE = 20;
    public static final int LUCENE_PAGE_SIZE_WEIBO = 20;
    public static final int LUCENE_PAGE_SIZE_SUGGEST = 20;

    public final static String LUCENE_CURRENT_SEARCH_STYLE = "current_search";
    public static final int LUCENE_CITY_SEARCH_UNION_PAGE_SIZE = 36;
    public static final int LUCENE_UNION_PAGE_SIZE = 3;
    // 搜索结果key值
    public final static String LUCENE_SEARCH_RESULTS_COUNT = "searchedResultsCount";// 存储结果
    public final static String LUCENE_SEARCHED_RESULT_IDS = "searchedids";// 搜索到的id集合
   
    public static String DEFAULT_STYLE_COLOR = "blue";// 默认蓝色风格
    //
    public static final String QUESTION_ID = "id";
    //当前用户key
    public static final String sidadmin="sidadmin";


}
