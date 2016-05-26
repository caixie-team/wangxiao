package io.wangxiao.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;

import javax.sql.DataSource;

@ComponentScan
@EnableAutoConfiguration
@SpringBootApplication
public class Application {
    @Bean
    @Autowired
    public JdbcAuthorizationCodeServices jdbcAuthorizationCodeServices(DataSource dataSource){
        return new JdbcAuthorizationCodeServices(dataSource);
    }
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
