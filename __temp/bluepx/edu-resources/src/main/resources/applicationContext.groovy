import co.bluepx.edu.core.spring.DynamicMapperSqlSessionFactoryBean
import co.bluepx.edu.core.spring.SpringContextHolder
import com.alibaba.druid.pool.DruidDataSource
import com.github.pagehelper.PageHelper
import org.mybatis.spring.mapper.MapperScannerConfigurer

beans {

    xmlns context: "http://www.springframework.org/schema/context"
    xmlns aop: "http://www.springframework.org/schema/aop"
    context.'component-scan'('base-package': "co.bluepx.edu")

//    importBeans "classpath:spring-import.groovy"
    springContextHolder(SpringContextHolder)

    dataSource(DruidDataSource) { bean ->
        bean.destroyMethod = 'close'

        driverClassName = "com.mysql.jdbc.Driver"

        url = "jdbc:mysql://rdsqbjjbe2evein.mysql.rds.aliyuncs.com:3306/edu?useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull"
        username = "grow"
        password = 'Grow2015'

    }

//    paginationInterceptor(PaginationInterceptor)
    paginationInterceptor(PageHelper)

    sqlSessionFactory(DynamicMapperSqlSessionFactoryBean) {
        dataSource = dataSource
        tablePrefix = 'edu'
        mapperModules = "manage,customer"
        mapperLocations = ["classpath*:/mybatis/*-mapper.xml"]
        typeAliasesPackage = "co.bluepx.edu"
        configLocation = "mybatis/mybatis-config.xml"
        plugins = paginationInterceptor
    }

    mapperScannerConfigurer(MapperScannerConfigurer){
        markerInterface = 'co.bluepx.edu.core.BaseDao'
        basePackage = 'co.bluepx.edu'
        annotationClass = 'co.bluepx.edu.core.annotation.MyBatisRepository'
    }
}