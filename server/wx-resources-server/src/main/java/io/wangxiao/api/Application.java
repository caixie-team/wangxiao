package io.wangxiao.api;

import org.apache.ibatis.session.SqlSessionFactory;
import org.beetl.sql.core.ClasspathLoader;
import org.beetl.sql.core.UnderlinedNameConversion;
import org.beetl.sql.core.db.MySqlStyle;
import org.beetl.sql.ext.DebugInterceptor;
import org.beetl.sql.ext.spring.SpringBeetlSql;
import org.beetl.sql.ext.spring.SpringConnectionSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.sql.DataSource;

@RestController
//@MapperScan(basePackages = {"co.bluepx.edu"}, markerInterface = BaseDao.class, annotationClass = MyBatisRepository.class)
@SpringBootApplication(scanBasePackages = {"co.bluepx.edu"})
public class Application {

    @RequestMapping("/")
    public String home() {
        return "Api Server is OK.";
    }

    @Bean
    @Autowired
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {

        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
//        DynamicMapperSqlSessionFactoryBean bean = new DynamicMapperSqlSessionFactoryBean();
//
//        bean.setDataSource(dataSource);
//        bean.setTablePrefix("edu");
//        bean.setMapperModules("manage,customer");
//        bean.setConfigLocation(
//                resolver.getResource("mybatis/mybatis-config.xml"));

//        bean.setMapperLocations(
//                resolver.getResources("classpath*:/mybatis/*-mapper.xml"));
//
//        bean.setTypeAliasesPackage("co.bluepx.edu");
//
//        bean.setPlugins(new Interceptor[]{
//                new PageHelper()
//        });
//        return bean.getObject();
        return null;
    }

//    @Bean
//    @Autowired
//    public SpringConnectionSource springConnectionSource(DataSource dataSource){
//        SpringConnectionSource ss = new SpringConnectionSource();
//        ss.setMasterSource(dataSource);
//        return ss;
//    }

    @Bean
    @Autowired
    public SpringBeetlSql springBeetlSql(DataSource dataSource){
       SpringBeetlSql beetlSql = new SpringBeetlSql();
        SpringConnectionSource cs = new SpringConnectionSource();
        cs.setMasterSource(dataSource);

        beetlSql.setCs(cs);
        beetlSql.setDbStyle(new MySqlStyle());
//        beetlSql.setSqlLoader(new ClasspathLoader("/sql"));
        beetlSql.setSqlLoader(new ClasspathLoader());
        beetlSql.setNc(new UnderlinedNameConversion());
        beetlSql.setInterceptors(new org.beetl.sql.core.Interceptor[]{
                new DebugInterceptor()
        });

        return  beetlSql;
    }



    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**")
                        .allowedHeaders("Access-Control-Allow-Origin", "*")
//                        .allowedOrigins("http://localhost")
                        .allowedMethods("PUT", "DELETE", "GET", "POST", "OPTIONS")
//				.allowedHeaders("header1", "header2", "header3")
//				.exposedHeaders("header1", "header2")
//                        .allowCredentials(false)
                        .maxAge(3600);

            }
//            public void addInterceptors(InterceptorRegistry registry){
//                registry.addInterceptor(new UserSecurityInterceptor()).addPathPatterns("/user/**");
//            }
//            .allowedHeaders( "Access-Control-Allow-Origin", "*"          )       "x-requested-with" )
//                    .allowedHeaders("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE")
//            .allowedMethods("GET", "POST", "PUT", "DELETE")
//            .allowedMethods("Access-Control-Allow-Headers", "Content-Type")
//            .maxAge( 3600);
        };
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }

}