package io.wangxiao.edu.app.common;


import io.wangxiao.commons.util.PropertyUtil;

/**
 * @description app memcache缓存相关常量
 */
public class AppMemConstans {
    public static PropertyUtil webPropertyUtil = PropertyUtil.getInstance("cachetime");
    //项目统一存memcache值时的前缀
    public static final String MEMFIX = webPropertyUtil.getProperty("memfix");

    public static final String APP_BANNER_IMAGES = MEMFIX + "app_banner_images";//广告图
    public static final int APP_BANNER_IMAGES_TIME = Integer.parseInt(webPropertyUtil.getProperty("APP_BANNER_IMAGES_TIME"));//缓存6小时

    public static final String APP_RECOMMEND_COURSE = MEMFIX + "app_recommend_course";//推荐课程
    public static final int APP_RECOMMEND_COURSE_TIME = Integer.parseInt(webPropertyUtil.getProperty("APP_RECOMMEND_COURSE_TIME"));//缓存一小时

}
