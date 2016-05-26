package com.atdld.os.common.constants;

import com.atdld.os.core.util.PropertyUtil;

/**
 * @author :
 * @ClassName com.atdld.os.sns.entity.constans.MemConstans
 * @description memcache缓存相关常量
 * @Create Date : 2014-3-26 上午9:42:06
 */
public class MemConstans {
    public static PropertyUtil webPropertyUtil = PropertyUtil.getInstance("memtimes");

    //项目统一存memcache值时的前缀
    public static final String MEMFIX = webPropertyUtil.getProperty("memfix");

    //******SNS限制用户使用频率*********
    // 微博限定时间60秒最多发n条
    public static int WEIBO_LIMIT_TIME = Integer.parseInt(webPropertyUtil.getProperty("WEIBO_LIMIT_TIME"));// 同时作为存memcache的时间
    public static int WEIBO_LIMIT_COUNT = Integer.parseInt(webPropertyUtil.getProperty("WEIBO_LIMIT_COUNT"));
    public static String WEIBO_LIMIT = MEMFIX + "SNS_WEIBO_LIMIT";// 验证时存memcache的key

    // 微博评论60秒最多发n条
    public static int WEIBO_REPLY_LIMIT_TIME = Integer.parseInt(webPropertyUtil.getProperty("WEIBO_REPLY_LIMIT_TIME"));// 同时作为存memcache的时间
    public static int WEIBO_REPLY_LIMIT_COUNT = Integer.parseInt(webPropertyUtil.getProperty("WEIBO_REPLY_LIMIT_COUNT"));
    public static String WEIBO_REPLY_LIMIT = MEMFIX + "SNS_WEIBO_REPLY_LIMIT";// 验证时存memcache的key

    // 博客、文章限定时间60秒最多发n条
    public static int ARTICLE_LIMIT_TIME = Integer.parseInt(webPropertyUtil.getProperty("ARTICLE_LIMIT_TIME"));// 同时作为存memcache的时间
    public static int ARTICLE_LIMIT_COUNT = Integer.parseInt(webPropertyUtil.getProperty("ARTICLE_LIMIT_COUNT"));
    public static String ARTICLE_LIMIT = MEMFIX + "SNS_ARTICLE_LIMIT";// 验证时存memcache的key

    // 博客、文章回复限定时间60秒最多发n条
    public static int ARTICLE_REPLY_LIMIT_TIME = Integer.parseInt(webPropertyUtil.getProperty("ARTICLE_REPLY_LIMIT_TIME"));// 同时作为存memcache的时间
    public static int ARTICLE_REPLY_LIMIT_COUNT = Integer.parseInt(webPropertyUtil.getProperty("ARTICLE_REPLY_LIMIT_COUNT"));
    public static String ARTICLE_REPLY_LIMIT = MEMFIX + "SNS_ARTICLE_REPLY_LIMIT";// 验证时存memcache的key

    // 用户主动发站内信限定时间60秒最多发n条
    public static int USER_LETTER_LIMIT_TIME = Integer.parseInt(webPropertyUtil.getProperty("USER_LETTER_LIMIT_TIME"));// 同时作为存memcache的时间
    public static int USER_LETTER_LIMIT_COUNT = Integer.parseInt(webPropertyUtil.getProperty("USER_LETTER_LIMIT_COUNT"));
    public static String USER_LETTER_LIMIT = MEMFIX + "SNS_USER_LETTER_LIMIT";// 验证时存memcache的key

    // 用户问题60秒最多发n条
    public static int SUGGEST_LIMIT_TIME = Integer.parseInt(webPropertyUtil.getProperty("SUGGEST_LIMIT_TIME"));// 同时作为存memcache的时间
    public static int SUGGEST_LIMIT_COUNT = Integer.parseInt(webPropertyUtil.getProperty("SUGGEST_LIMIT_COUNT"));
    public static String SUGGEST_LIMIT = MEMFIX + "SNS_SUGGEST_LIMIT";// 验证时存memcache的key

    // 用户问题回复60秒最多发2条
    public static int SUGGEST_REPLY_LIMIT_TIME = Integer.parseInt(webPropertyUtil.getProperty("SUGGEST_REPLY_LIMIT_TIME"));// 同时作为存memcache的时间
    public static int SUGGEST_REPLY_LIMIT_COUNT = Integer.parseInt(webPropertyUtil.getProperty("SUGGEST_REPLY_LIMIT_COUNT"));
    public static String SUGGEST_REPLY_LIMIT = MEMFIX + "SNS_SUGGEST_REPLY_LIMIT";// 验证时存memcache的key

    // 用户搜索30秒最多搜索10次
    public static int SEARCH_LIMIT_TIME = Integer.parseInt(webPropertyUtil.getProperty("SEARCH_LIMIT_TIME"));// 同时作为存memcache的时间
    public static int SEARCH_LIMIT_COUNT = Integer.parseInt(webPropertyUtil.getProperty("SEARCH_LIMIT_COUNT"));
    public static String SEARCH_LIMIT = MEMFIX + "SEARCH_LIMIT";// 验证时存memcache的key
    //******SNS限制用户使用频率*********


    //*******SNS全站排行数据缓存时间*******
    public static final int HOT_TIME = Integer.parseInt(webPropertyUtil.getProperty("HOT_TIME"));// 全站排行时间30分钟
    public static final int HOT_TIME_WEIBO_WEEK = Integer.parseInt(webPropertyUtil.getProperty("HOT_TIME_WEIBO_WEEK"));// 微博一周排行6个小时

    public static final String SUGGEST_WISDOM = MEMFIX + "SNS_SUGGEST_WISDOM";// 问题智慧排行
    public static final int SUGGEST_WISDOM_WEEK = Integer.parseInt(webPropertyUtil.getProperty("SUGGEST_WISDOM_WEEK"));// 问题智慧排行6个小时
    public static final int SUGGEST_HOT_WEEK = Integer.parseInt(webPropertyUtil.getProperty("SUGGEST_HOT_WEEK"));// 问题热心排行6个小时

    public static final String WEIBO_NUM_WEEK = MEMFIX + "SNS_WEIBO_NUM_WEEK";// 微博一周内发表微博最多的人排行
    public static final int WEIBO_NUM_WEEK_TIME = Integer.parseInt(webPropertyUtil.getProperty("WEIBO_NUM_WEEK_TIME"));// 微博一周内发表微博最多的人排行

    public static final String TOP_BLOG_WEEK = MEMFIX + "SNS_TOP_BLOG_WEEK";// 博客一周排行key
    public static final int HOT_TIME_BLOG_WEEK = Integer.parseInt(webPropertyUtil.getProperty("HOT_TIME_BLOG_WEEK"));// 博客一周排行6个小时
    public static final String BLOG_CLASSIFY = MEMFIX + "SNS_BLOG_CLASSIFY";// 文章分类key

    public static final String DISGROUP_DYNAMIC = MEMFIX + "SNS_DISGROUP_DYNAMIC";//查詢加入小组key
    public static final int DISGROUP_DYNAMIC_TIME = 5 * 60;//查詢加入的小组5分鐘

    public static final String DISGROUP_INFO = MEMFIX + "SNS_DISGROUP_INFO"; // 小组信息
    public static final int DISGROUP_INFO_TIME = Integer.parseInt(webPropertyUtil.getProperty("DISGROUP_INFO_TIME")); // 小组信息，小组分类，文章分类时间10分钟
    // 共用一个时间
    public static final String DISGROUP_CLASSIFY = MEMFIX + "SNS_DISGROUP_CLASSIFY";// 小组分类的key
    public static final String ARTICLE_CLASSIFY = MEMFIX + "SNS_ARTICLE_CLASSIFY";// 文章分类key

    public static final String INDEX_DIS_GROUP = MEMFIX + "INDEX_DIS_GROUP";// 首页小组排行
    public static final int INDEX_DIS_GROUP_TIME = Integer.parseInt(webPropertyUtil.getProperty("INDEX_DIS_GROUP_TIME")); // 首页小组排行缓存6个小时

    public static final String INDEX_BLOG_BLOG = MEMFIX + "INDEX_BLOG_BLOG";// 首页财经文章排行
    public static final int INDEX_BLOG_BLOG_TIME = Integer.parseInt(webPropertyUtil.getProperty("INDEX_BLOG_BLOG_TIME")); // 首页财经文章缓存6个小时

    public static final String INDEX_QUERYBLOGANDREPLY = MEMFIX + "INDEX_QUERYBLOGANDREPLY";// 首页博主排行
    public static final int INDEX_QUERYBLOGANDREPLY_TIME = Integer.parseInt(webPropertyUtil.getProperty("INDEX_QUERYBLOGANDREPLY_TIME")); // 首页小组博主缓存6个小时

    public static final String MSGRECEIVE_UNREAD = MEMFIX + "MSGRECEIVE_UNREAD";// 头部未读消息数量
    public static final int MSGRECEIVE_UNREAD_TIME = Integer.parseInt(webPropertyUtil.getProperty("MSGRECEIVE_UNREAD_TIME")); // 头部未读消息数量缓存1分钟
    //*******SNS全站排行数据缓存时间*******

    public static final String MEM_HOT_BAK = "_bak";//备份的后缀

    // 热门词按分类存mem前缀
    public static final String MEM_SEARCH_WORD = MEMFIX + "sns_search_word_";
    // 敏感词存缓存
    public static final String MEM_KEYWORD = MEMFIX + "sns_key_word_";
    public static final int MEM_KEYWORD_TIME = Integer.parseInt(webPropertyUtil.getProperty("MEM_KEYWORD_TIME"));

    //lucene索引是否生成开始生成标识。值存在代表正在生成
    public static final String MEM_LUCENE_CREATING = MEMFIX + "MEM_LUCENE_CREATING";

    // 考试exam  **memcache key 前缀统一，全部以MEM_开头 start
    public static final int MEM_COMMON_TIME = 600;//一般数据缓存的时间 10分钟
    public static final String MEM_SUBECJT =MEMFIX + "MEM_SUBECJT_";// 单个专业信息。后追加专业id

    public static final String MEM_ALL_SUBECJT = MEMFIX +"MEM_ALL_SUBECJT";// 所有专业信息
    public static final int  MEM_ALL_SUBECJT_TIME = Integer.parseInt(webPropertyUtil.getProperty("MEM_ALL_SUBECJT_TIME"));;// 所有专业信息时间

    public static final String MEM_CUS_LASTPAPER = MEMFIX +"MEM_CUS_LASTPAPER_";// 学员最近考过的信息，后缀加用户id
    public static final String MEM_CUS_NOTE = MEMFIX +"MEM_CUS_NOTE_";// 学员考试首页最新的笔记信息，后缀加用户id
    public static final String MEM_CUS_ERRORQUESTION = MEMFIX +"MEM_CUS_NOTE_";// 学员考试首页的错题信息，后缀加用户id
    public static final int  MEM_CUS_NOTE_TIME = Integer.parseInt(webPropertyUtil.getProperty("MEM_CUS_NOTE_TIME"));// 所有专业信息时间
    public static final String MEM_POINTID_POINTNAME = MEMFIX +"MEM_POINTID_POINTNAME_";// 单个考点的父节点的信息，在解析考点那用到。后追加考点id来区分

    // 考试exam end
    

    //网校相关 start

    public static final String INDEX_CUSTOMER_COURSE = MEMFIX + "customer_course";//首页自定义课程
    public static final int INDEX_CUSTOMER_COURSE_TIME = Integer.parseInt(webPropertyUtil.getProperty("INDEX_CUSTOMER_COURSE_TIME"));//自定义课程缓存1小时


    public static final String RECOMMEND_COURSE = MEMFIX + "recommend_course";//首页推荐课程
    public static final int RECOMMEND_COURSE_TIME = Integer.parseInt(webPropertyUtil.getProperty("RECOMMEND_COURSE_TIME"));//缓存一小时

    public static final String HOME_INDEX_ARTILE = MEMFIX + "home_index_artilce";//首页公告
    public static final String HOME_INDEX_ARTILE_TOP = MEMFIX + "home_index_artilce_top";//首页文章排行
    public static final int HOME_INDEX_ARTILE_TIME = Integer.parseInt(webPropertyUtil.getProperty("HOME_INDEX_ARTILE_TIME"));//缓存一小时

    public static final String USER_CURRENT_LOGINTIME = MEMFIX+"USER_CURRENT_LOGINTIME_";//记录当前用户当前的登录时间，下次登录时会更新此缓存
    public static final int USER_TIME = Integer.parseInt(webPropertyUtil.getProperty("USER_TIME"));//用户登录保存时间缓存6小时

    public static final String WEBSITE_PROFILE = MEMFIX + "website_profile";//网站配置
    public static final int WEBSITE_PROFILE_TIME = Integer.parseInt(webPropertyUtil.getProperty("USER_TIME"));//缓存6小时

    public static final String SHOPCART = MEMFIX + "shopcats";//购物车+userId
    public static final int SHOPCART_TIME = Integer.parseInt(webPropertyUtil.getProperty("SHOPCART_TIME"));//缓存1小时

    public static final String BANNER_IMAGES = MEMFIX + "banner_images";//广告图
    public static final int BANNER_IMAGES_TIME = Integer.parseInt(webPropertyUtil.getProperty("BANNER_IMAGES_TIME"));//缓存6小时

    public static final String WEBSITE_NAVIGATE = MEMFIX + "website_navigate";//导航配置
    public static final int WEBSITE_NAVIGATE_TIME = Integer.parseInt(webPropertyUtil.getProperty("WEBSITE_NAVIGATE_TIME"));//缓存12小时

    public static final String USER_CANLOOK = MEMFIX + "user_canlook_";//用户是否可观看某个课程+courseId
    public static final int USER_CANLOOK_TIME = Integer.parseInt(webPropertyUtil.getProperty("USER_CANLOOK_TIME"));//缓存6小时

    public static final String COURE_FREE = MEMFIX + "course_free_";//个人中心免费课程列表
    public static final int COURE_FREE_TIME = Integer.parseInt(webPropertyUtil.getProperty("COURE_FREE_TIME"));//缓存1小时;
    
    public static final String USEREXPAND_INFO = MEMFIX + "user_expand_info_";//单个用户的综合信息
    public static final int USEREXPAND_INFO_TIME = Integer.parseInt(webPropertyUtil.getProperty("USEREXPAND_INFO_TIME"));//缓存1小时;

    public static final String HELP_CENTER = MEMFIX + "help_center";//帮助页面左侧菜单
    public static final int HELP_CENTER_TIME = Integer.parseInt(webPropertyUtil.getProperty("HELP_CENTER_TIME"));//缓存1小时;

    
    public static final String WEB_STATISTICS = MEMFIX + "web_statistics";//网站统计
    public static final String WEB_STATISTICS_THIRTY = MEMFIX + "web_statistics_thirty";//网站最近30条活跃统计
    public static final int WEB_STATISTICS_TIME = Integer.parseInt(webPropertyUtil.getProperty("WEB_STATISTICS_TIME"));//缓存1小时;

    public static final int SYS_USER_TIME = Integer.parseInt(webPropertyUtil.getProperty("SYS_USER_TIME"));//缓存24小时;key为随机生成的
    public static final String SYS_USER_FUNCTION = MEMFIX + "SYS_USER_FUNCTION_";//系统当前用户的权限+userid
    public static final String SYS_USER_ALL_FUNCTION = MEMFIX + "SYS_USER_ALL_FUNCTION";//系统所有权限
    public static final int SYS_USER_ALL_FUNCTION_TIME = Integer.parseInt(webPropertyUtil.getProperty("SYS_USER_ALL_FUNCTION_TIME"));//缓存24小时;
    
    public static final String WX_ACCESS_TOKEN = MEMFIX + "WX_ACCESS_TOKEN";//微信access_token
    public static final int WX_ACCESS_TOKEN_TIME = Integer.parseInt(webPropertyUtil.getProperty("WX_ACCESS_TOKEN_TIME"));//缓存1小时;


}
