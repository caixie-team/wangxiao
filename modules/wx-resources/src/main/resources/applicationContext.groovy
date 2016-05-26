import com.alibaba.druid.pool.DruidDataSource
import com.github.pagehelper.PageHelper
import io.wangxiao.core.spring.DynamicMapperSqlSessionFactoryBean
import io.wangxiao.core.spring.SpringContextHolder
import org.beetl.sql.core.ClasspathLoader
import org.beetl.sql.core.UnderlinedNameConversion
import org.beetl.sql.core.db.MySqlStyle
import org.beetl.sql.ext.DebugInterceptor
import org.beetl.sql.ext.spring.SpringConnectionSource
import org.beetl.sql.ext.spring4.BeetlSqlDataSource
import org.beetl.sql.ext.spring4.BeetlSqlScannerConfigurer
import org.beetl.sql.ext.spring4.SqlManagerFactoryBean
import org.mybatis.spring.mapper.MapperScannerConfigurer

beans {

    xmlns context: "http://www.springframework.org/schema/context"
    xmlns aop: "http://www.springframework.org/schema/aop"
    context.'component-scan'('base-package': "io.wangxiao")

//    importBeans "classpath:spring-import.groovy"
    springContextHolder(SpringContextHolder)

    dataSource(DruidDataSource) { bean ->
        bean.destroyMethod = 'close'

        driverClassName = "com.mysql.jdbc.Driver"

        url = "jdbc:mysql://192.168.99.100:3308/wx-edu?useUnicode=true&characterEncoding=utf-8" +
                "&zeroDateTimeBehavior=convertToNull"
        username = "admin"
        password = 'abcd1234'

    }

//    paginationInterceptor(PaginationInterceptor)
    paginationInterceptor(PageHelper)

    sqlSessionFactory(DynamicMapperSqlSessionFactoryBean) {
        dataSource = dataSource
        tablePrefix = 'edu'
        mapperModules = "manage,customer"
        mapperLocations = ["classpath*:/mybatis/*-mapper.xml"]
        typeAliasesPackage = "io.wangxiao"
        configLocation = "mybatis/mybatis-config.xml"
        plugins = paginationInterceptor
    }

    mapperScannerConfigurer(MapperScannerConfigurer) {
        markerInterface = 'io.wangxiao.core.BaseDao'
        basePackage = 'io.wangxiao'
        annotationClass = 'io.wangxiao.core.annotation.MyBatisRepository'
    }

    springConnectionSrouce(SpringConnectionSource) {
        masterSource = ref('dataSource')
    }

    /// Beetl SQL
    ///
    beetlSqlDataSource(BeetlSqlDataSource){
        masterSource = ref('dataSource')
    }
    beetlSqlScannerConfigurer(BeetlSqlScannerConfigurer){
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

    sqlManagerFactoryBean(SqlManagerFactoryBean){
        cs = beetlSqlDataSource
        dbStyle = _dbStyle
        sqlLoader = _sqlLoader
        nc = ref('defaultNameConversion')
        interceptors = [debugInterceptor]
    }

}