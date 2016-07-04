package io.wangxiao.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.*;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import javax.sql.DataSource;
import java.util.Arrays;

/**
 * @author Bison
 */

@Configuration
//@PropertySource({"classpath:persistence.properties"})
@EnableAuthorizationServer
public class OAuth2Config extends AuthorizationServerConfigurerAdapter {
    @Autowired
    private DataSource dataSource;

    @Autowired
    private Environment env;

    private static final String DUMMY_RESOURCE_ID = "dummy";

    @Autowired
    @Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager;

    //    @Autowired
//    @Qualifier("authenticationManager")
//    private AuthenticationManager authenticationManager;
    @Value("classpath:schema.sql")
    private Resource schemaScript;

    @Autowired
    UserDetailsService userDetailsService;


    // 在令牌端点上定义了安全约束
    @Override
    public void configure(final AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
//        oauthServer.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
        oauthServer.allowFormAuthenticationForClients();
    }

    //    @Value("${default.redirect:http://localhost:8080/tonr2/sparklr/redirect}")
    //    private String defaultRedirectUri;
    // 定义了授权和令牌端点和令牌服务
    @Override // [2]
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        // @formatter:off
        // Token 过滤增强
        final TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(Arrays.asList(tokenEnhancer(),accessTokenConverter()));
        endpoints
                .tokenServices(tokenServices())
                .tokenStore(tokenStore())
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService)
                .tokenEnhancer(tokenEnhancerChain);
        // @formatter:on
    }

    @Bean
    public JwtTokenStore tokenStore() {
        return new JwtTokenStore(accessTokenConverter());
    }

    @Bean
    protected JwtAccessTokenConverter accessTokenConverter() {

//        JwtAccessTokenConverter converter = new CustomTokenEnhancer();
//        converter.setAccessTokenConverter(getDefaultAccessTokenConverter());
        final JwtAccessTokenConverter converter = new JwtAccessTokenConverter();

        final KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new ClassPathResource("jwt-test.jks"),
                "testpass".toCharArray());
        converter.setKeyPair(keyStoreKeyFactory.getKeyPair("jwt-test"));

        return converter;
    }

    @Bean
    public TokenEnhancer tokenEnhancer() {
        return new CustomTokenEnhancer();
    }

    // AuthorizationServerTokenServices 接口里定义了 OAuth 2.0 令牌的操作方法

    @Bean
    @Primary
    public AuthorizationServerTokenServices tokenServices() {
        final DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenStore(tokenStore());
        defaultTokenServices.setSupportRefreshToken(true);
        return defaultTokenServices;
    }


    @Bean
    AccessTokenConverter getDefaultAccessTokenConverter() {
        DefaultAccessTokenConverter defaultAccessTokenConverter = new DefaultAccessTokenConverter();
        defaultAccessTokenConverter.setUserTokenConverter(getCustomUserAuthenticationConvertor());
        return defaultAccessTokenConverter;
    }

    @Bean
    UserAuthenticationConverter getCustomUserAuthenticationConvertor() {

        return new CustomUserAuthenticationConvertor();
    }

    // 这个configurer定义了客户端细节服务。客户详细信息可以被初始化,或者你可以参考现有的商店。
    // ClientDetailsServiceConfigurer 类（AuthorizationServerConfigurer类中的一个调用类）可以用来定义一个基于内存的或者JDBC的客户端信息服务。
    /*clientId：（必须）客户端id。

secret：（对于可信任的客户端是必须的）客户端的私密信息。

scope：客户端的作用域。如果scope未定义或者为空（默认值），则客户端作用域不受限制。

authorizedGrantTypes：授权给客户端使用的权限类型。默认值为空。

authorities：授权给客户端的权限（Spring普通的安全权限）*/


    @Override
    public void configure(final ClientDetailsServiceConfigurer clients) throws Exception {// @formatter:off
        clients
                .jdbc(dataSource)
//               .jdbc(dataSource())

               .inMemory()
               .withClient("sampleClientId")
                .resourceIds(DUMMY_RESOURCE_ID)
               .authorizedGrantTypes("implicit")
               .scopes("read","write","foo","bar")
               .autoApprove(false)
               .accessTokenValiditySeconds(3600)

               .and()
               .withClient("fooClientIdPassword")
                .resourceIds(DUMMY_RESOURCE_ID)
               .secret("secret")
               .authorizedGrantTypes("password","authorization_code", "refresh_token")
               .scopes("foo","read","write")
               .accessTokenValiditySeconds(3600) // 1 hour
               .refreshTokenValiditySeconds(2592000) // 30 days

               .and()
               .withClient("barClientIdPassword")
                .resourceIds(DUMMY_RESOURCE_ID)
               .secret("secret")
               .authorizedGrantTypes("password","authorization_code", "refresh_token")
               .scopes("bar","read","write")
               .accessTokenValiditySeconds(3600) // 1 hour
               .refreshTokenValiditySeconds(2592000) // 30 days
               ;
    } // @formatter:on

//    @Override // [3]
//    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//        clients.inMemory().
//                withClient("tonr")
//                .resourceIds(DUMMY_RESOURCE_ID)
//                .authorizedGrantTypes("authorization_code", "implicit")
//                .authorities("ROLE_CLIENT")
//                .scopes("read", "write")
//                .secret("secret")
//                .and()
//                .withClient("tonr-with-redirect")
//                .resourceIds(DUMMY_RESOURCE_ID)
//                .authorizedGrantTypes("authorization_code", "implicit")
//                .authorities("ROLE_CLIENT")
//                .scopes("read", "write")
//                .secret("secret")
//                .redirectUris(defaultRedirectUri)
//                .and()
//                .withClient("my-client-with-registered-redirect")
//                .resourceIds(DUMMY_RESOURCE_ID)
//                .authorizedGrantTypes("authorization_code", "client_credentials")
//                .authorities("ROLE_CLIENT")
//                .scopes("read", "trust")
//                .redirectUris("http://anywhere?key=value")
//                .and()
//                .withClient("my-trusted-client")
//                .authorizedGrantTypes("password", "authorization_code", "refresh_token", "implicit")
//                .authorities("ROLE_CLIENT", "ROLE_TRUSTED_CLIENT")
//                .scopes("read", "write", "trust")
//                .accessTokenValiditySeconds(3600)
//                .refreshTokenValiditySeconds(3600 * 24 * 30)
//                .and()
//                .withClient("my-trusted-client-with-secret")
//                .authorizedGrantTypes("password", "authorization_code", "refresh_token", "implicit", "client_credentials")
//                .authorities("ROLE_CLIENT", "ROLE_TRUSTED_CLIENT")
//                .scopes("read", "write", "trust")
//                .secret("somesecret")
//                .and()
//                .withClient("my-less-trusted-client")
//                .authorizedGrantTypes("authorization_code", "implicit")
//                .authorities("ROLE_CLIENT")
//                .scopes("read", "write", "trust")
//                .and()
//                .withClient("my-less-trusted-autoapprove-client")
//                .authorizedGrantTypes("implicit")
//                .authorities("ROLE_CLIENT")
//                .scopes("read", "write", "trust")
//                .autoApprove(true);
//    }



    /*protected static class Stuff {

        @Autowired
        private ClientDetailsService clientDetailsService;

        @Autowired
        private TokenStore tokenStore;

        @Bean
        public ApprovalStore approvalStore() throws Exception {
            TokenApprovalStore store = new TokenApprovalStore();
            store.setTokenStore(tokenStore);
            return store;
        }

        @Bean
        @Lazy
        @Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
        public SparklrUserApprovalHandler userApprovalHandler() throws Exception {
            SparklrUserApprovalHandler handler = new SparklrUserApprovalHandler();
            handler.setApprovalStore(approvalStore());
            handler.setRequestFactory(new DefaultOAuth2RequestFactory(clientDetailsService));
            handler.setClientDetailsService(clientDetailsService);
            handler.setUseApprovalStore(true);
            return handler;
        }
    }*/
}
