package co.bluepx.edu;

import co.bluepx.edu.core.BaseDao;
import co.bluepx.edu.core.annotation.MyBatisRepository;
import co.bluepx.edu.core.spring.DynamicMapperSqlSessionFactoryBean;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
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
@MapperScan(basePackages = {"co.bluepx.edu"}, markerInterface = BaseDao.class, annotationClass = MyBatisRepository.class)
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
        DynamicMapperSqlSessionFactoryBean bean = new DynamicMapperSqlSessionFactoryBean();

        bean.setDataSource(dataSource);
        bean.setTablePrefix("edu");
//        bean.setMapperModules("manage,customer");
        bean.setConfigLocation(
                resolver.getResource("mybatis/mybatis-config.xml"));

        bean.setMapperLocations(
                resolver.getResources("classpath*:/mybatis/*-mapper.xml"));

        bean.setTypeAliasesPackage("co.bluepx.edu");

        bean.setPlugins(new Interceptor[]{
                new PageHelper()
        });
        return bean.getObject();
    }


    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**");
            }
//            public void addInterceptors(InterceptorRegistry registry){
//                registry.addInterceptor(new UserSecurityInterceptor()).addPathPatterns("/user/**");
//            }
        };
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }

}