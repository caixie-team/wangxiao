import com.alibaba.druid.pool.DruidDataSource
import org.beetl.sql.core.ClasspathLoader
import org.beetl.sql.core.UnderlinedNameConversion
import org.beetl.sql.core.db.MySqlStyle
import org.beetl.sql.ext.DebugInterceptor
import org.beetl.sql.ext.spring4.BeetlSqlDataSource
import org.beetl.sql.ext.spring4.BeetlSqlScannerConfigurer
import org.beetl.sql.ext.spring4.SqlManagerFactoryBean

beans {

    xmlns context: "http://www.springframework.org/schema/context"
    xmlns aop: "http://www.springframework.org/schema/aop"
    context.'component-scan'('base-package': "io.wangxiao")

//    springContextHolder(SpringContextHolder)

    dataSource(DruidDataSource) { bean ->
        bean.destroyMethod = 'close'
        driverClassName = "com.mysql.jdbc.Driver"
        url = "jdbc:mysql://192.168.99.100:3308/wx-edu?useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull"
        username = "admin"
        password = 'abcd1234'

    }

//    springConnectionSrouce(SpringConnectionSource) {
//        masterSource = ref('dataSource')
//    }

    /// Beetl SQL
    ///
    beetlSqlDataSource(BeetlSqlDataSource) {
        masterSource = ref('dataSource')
    }
    beetlSqlScannerConfigurer(BeetlSqlScannerConfigurer) {
        basePackage = 'io.wangxiao'
        daoSuffix = 'Dao'
        SqlManagerFactoryBeanName = 'sqlManagerFactoryBean'
    }
    _dbStyle(MySqlStyle)
    _sqlLoader(ClasspathLoader) {
        sqlRoot = "/sql"
    }
    debugInterceptor(DebugInterceptor) {}
    defaultNameConversion(UnderlinedNameConversion)

    sqlManagerFactoryBean(SqlManagerFactoryBean) {
        cs = beetlSqlDataSource
        dbStyle = _dbStyle
        sqlLoader = _sqlLoader
        nc = ref('defaultNameConversion')
        interceptors = [debugInterceptor]
    }

}