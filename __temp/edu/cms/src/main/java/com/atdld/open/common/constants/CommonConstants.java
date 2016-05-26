package com.atdld.open.common.constants;

import com.atdld.open.core.util.PropertyUtil;
import com.atdld.open.core.util.web.WebUtils;

/**
 * @ClassName com.atdld.open.common.entity.CommonConstant
 * @description
 */
public class CommonConstants {

    public static String propertyFile = "project";// 配置文件名字
    // 读取配置文件类
    public static PropertyUtil propertyUtil = PropertyUtil.getInstance(propertyFile);

    public static final String redirect = "redirect";
    
    public static PropertyUtil prosecurity = PropertyUtil.getInstance("prosecurity");
    
    // 统一加密key
    public static  String SecurityKey = prosecurity.getProperty("securitykey");

    public static String domiankey= prosecurity.getProperty("domiankey");

    // 资源文件版本号
    public static final String VERSION = System.currentTimeMillis() + "";
    // 存cookie存根域
    public static final String MYDOMAIN = propertyUtil.getProperty("mydomain");
    // 视频上传ukey
    public static final String UKEY = "ukey"; // 用户id
    // 用户登录
    public static final String USER_SINGEL_ID = "sid"; // 用户id

    public static final String USER_SINGEL_NAME = "sname"; // 用户名


    //后台当前用户key
    public static final String sidadmin="sidadmin";

    // 项目路径
    public static final String contextPath = propertyUtil.getProperty("contextPath");
    // 项目绝对路径
    public static final String realPath = propertyUtil.getProperty("realPath");
    // edu路径
    public static final String webPath = propertyUtil.getProperty("webPath");
    // 静态资源：图片、CSS、js 服务器地址
    public static final String staticServer = propertyUtil.getProperty("staticServer");

    // 上传服务用服务器地址，访问时用upStaticPath，数据库中不存储域名
    public static final String uploadImageServer = propertyUtil.getProperty("uploadImageServer");

    // 上传图片后访问的地址，使用importURL或者upStaticPath防止项目部署到多台机器，单独定义此变量
    public static final String staticImageServer = propertyUtil.getProperty("staticImage");

    public static final String projectName = propertyUtil.getProperty("projectName");
    
    /**模板路径 */
    public static final String lineTemplate = propertyUtil.getProperty("lineTemplate");

    /**文章生成目录*/
    public static final String staticArticle = propertyUtil.getProperty("staticArticle");
    /**资讯文章生成目录*/
    public static final String cmsIndexPath = propertyUtil.getProperty("cmsIndexPath");
    /**静态文章访问路径*/
    public static final String articleUrl = propertyUtil.getProperty("articleUrl");
    public static  String l = "1";
    public static  String w = "1";
    static {
        WebUtils.MYDOMAIN = MYDOMAIN;
    }
    

}
