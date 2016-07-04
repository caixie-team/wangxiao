package io.wangxiao.course;

import org.beetl.sql.core.*;
import org.beetl.sql.core.db.DBStyle;
import org.beetl.sql.core.db.MySqlStyle;
import org.beetl.sql.ext.DebugInterceptor;
import org.beetl.sql.ext.gen.GenConfig;

/**
 * Created by bison on 5/25/16.
 */
public class SqlMan {
    public static String driver = "com.mysql.jdbc.Driver";
    public static String dbName = "wx-edu";
    public static String password = "abcd1234";
    public static String userName = "admin";
    public static String url = "jdbc:mysql://192.168.99.100:3308/" + dbName + "?loglevel=2";

    public static void main(String[] args) throws Exception {

        ConnectionSource source = ConnectionSourceHelper.getSimple(driver, url, userName, password);
        DBStyle mysql = new MySqlStyle();

        // sql语句放在classpagth的/sql 目录下
        SQLLoader loader = new ClasspathLoader("/sql");
        // 数据库命名跟java命名一样，所以采用DefaultNameConversion，还有一个是UnderlinedNameConversion，下划线风格的
        UnderlinedNameConversion nc = new UnderlinedNameConversion();

        // 最后，创建一个SQLManager,DebugInterceptor 不是必须的，但可以通过它查看sql执行情况
        SQLManager sqlManager = new SQLManager(mysql, loader, source, nc, new Interceptor[]{new DebugInterceptor()});
        GenConfig genConfig = new GenConfig();
//        sqlManager.genPojoCode("edu_course","io.wangxiao.course.model");
//        sqlManager.genSQLFile("edu_course");
//        sqlManager.genPojoCode("edu_course_subject","io.wangxiao.course.model");
//        sqlManager.genSQLFile("edu_course_subject");

//        CourseProfile

//        sqlManager.genPojoCode("edu_course_profile","io.wangxiao.course.model");
//        sqlManager.genSQLFile("edu_course_profile");
//        CourseTeacher
//        sqlManager.genPojoCode("edu_course_teacher","io.wangxiao.course.model");
//        sqlManager.genSQLFile("edu_course_teacher");

//        Teacher
//        sqlManager.genPojoCode("edu_teacher","io.wangxiao.course.model");
        sqlManager.genSQLFile("edu_website_navigate");

//        sqlManager.genPojoCodeToConsole("edu_course");
//        sqlManager.genSQLTemplateToConsole("edu_website_navigate");
    }

}
