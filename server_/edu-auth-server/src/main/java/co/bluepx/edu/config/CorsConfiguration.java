package io.wangxiao.auth.config;

/**
 * Created by bison on 1/11/16.
 *
 */
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

//@Configuration
//@Order(-1)
public class CorsConfiguration extends WebSecurityConfigurerAdapter {
//    @Autowired
//    private UserDetailsService uds;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.requestMatchers().antMatchers(HttpMethod.OPTIONS, "/oauth/token", "/rest/**")
                .and()
                .csrf().disable()
                .authorizeRequests().anyRequest().permitAll()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

//    @Autowired
//    public void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(uds).passwordEncoder(new Md5PasswordEncoder());
//    }
}
