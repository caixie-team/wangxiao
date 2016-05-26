package io.wangxiao.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * @author bison
 */
@Configuration
@EnableWebSecurity
//@Order(2)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService uds;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(uds).passwordEncoder(new Md5PasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // @formatter:off
//        , "/rest/**
        http.requestMatchers().antMatchers(HttpMethod.OPTIONS, "/oauth/token")
                .and()
            // TODO: put CSRF protection back into this endpoint
                .csrf()
//                    .requireCsrfProtectionMatcher(new AntPathRequestMatcher("/oauth/authorize"))
                    .disable()
                .logout()
                    .logoutUrl("/logout")
                    .and()
                .authorizeRequests().anyRequest().permitAll()
                    .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        // @formatter:on
    }
}
