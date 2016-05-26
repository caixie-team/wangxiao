package io.wangxiao.bookstore;

import org.springframework.stereotype.Service;

/**
 * Created by bison on 1/21/16.
 */
@Service
public class SqlMan {
    public static void main(String[] args) {
// 创建一个简单的ConnectionSource，只有一个master
//        ConnectionSource source = ConnectionSourceHelper.simple(driver,url,userName,password);
// 采用mysql 习俗
//        DBStyle mysql = new MysqlStyle();
// sql语句放在classpagth的/sql 目录下
//        SQLLoader loader = new ClasspathLoader("/sql");
// 数据库命名跟java命名一样，所以采用DefaultNameConversion，还有一个是UnderlinedNameConversion，下划线风格的
//        NameConversion nc = new  DefaultNameConversion();
// 最后，创建一个SQLManager,DebugInterceptor 不是必须的，但可以通过它查看sql执行情况
//        SqlManager sqlManager = new SqlManager(source,mysql,loader,nc,new Interceptor[]{new DebugInterceptor()});

//         SQLManager dao;


//        @Autowired
//        public void setSqlManager(SpringBeetlSql beetlsql){
//            dao = beetlsql.getSQLMananger();
//
//        }
//        public static void main(String[] args){
//            SQLManager
//            sqlManager.genPojoCodeToConsole("user");
//            sqlManager.genSQLTemplateToConsole("user");
//        }
    }
}
