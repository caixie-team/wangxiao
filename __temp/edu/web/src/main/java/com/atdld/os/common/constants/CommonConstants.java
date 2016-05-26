package com.atdld.os.common.constants;

import com.atdld.os.core.util.PropertyUtil;
import com.atdld.os.core.util.web.WebUtils;

/**
 * @author :
 * @ClassName com.atdld.os.common.entity.CommonConstant
 * @description
 * @Create Date : 2014-5-27 下午4:55:03
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
    // 268视频上传ukey
    public static final String UKEY = "ukey"; // 用户id
    // 用户登录
    public static final String USER_SINGEL_ID = "sid"; // 用户id

    public static final String USER_SINGEL_NAME = "sname"; // 用户名
    
    public static final String UP_USER_ID = "userId";
    
    // 存用户的专业id
    public static final String COOKIE_SUBJECTID_KEY = "e.subject";

    // 项目路径
    public static final String contextPath = propertyUtil.getProperty("contextPath");
    // SNS路径
    public static final String snsPath = propertyUtil.getProperty("snsPath");
    // 考试路径
    public static final String examPath = propertyUtil.getProperty("examPath");
    // 视频路径
    public static final String videoPath = propertyUtil.getProperty("videoPath");
    // 微站路径
    public static final String wzPath = propertyUtil.getProperty("wzPath");

    // 静态资源：图片、CSS、js 服务器地址
    public static final String staticServer = propertyUtil.getProperty("staticServer");

    // 上传服务用服务器地址，访问时用upStaticPath，数据库中不存储域名
    public static final String uploadImageServer = propertyUtil.getProperty("uploadImageServer");

    // 上传图片后访问的地址，使用importURL或者upStaticPath防止项目部署到多台机器，单独定义此变量
    public static final String staticImageServer = propertyUtil.getProperty("staticImage");

    public static final String projectName = propertyUtil.getProperty("projectName");

    //亲加通讯云帐号
    public static final String imEmail=propertyUtil.getProperty("im.email");
    //亲加通讯云密码
    public static final String imDevpwd=propertyUtil.getProperty("im.devpwd");
    //亲加通讯云应用标识
    public static final String imAppKey=propertyUtil.getProperty("im.appkey");
    public static final String defaultPartner=propertyUtil.getProperty("defaultPartner");
    public static final String defaultSeller=propertyUtil.getProperty("defaultSeller");
    public static final String privateKey=propertyUtil.getProperty("private");
    public static final String publicKey=propertyUtil.getProperty("publicKey");
    
    
    public static  String l = "1";
    public static  String w = "1";
    static {
        WebUtils.MYDOMAIN = MYDOMAIN;
    }
}
