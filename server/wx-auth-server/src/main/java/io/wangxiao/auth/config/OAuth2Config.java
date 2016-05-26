package io.wangxiao.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
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

/**
 * @author Bison
 */
@Configuration
@EnableAuthorizationServer
@EnableWebMvcSecurity
public class OAuth2Config extends AuthorizationServerConfigurerAdapter {

    private static final String DUMMY_RESOURCE_ID = "dummy";

    @Autowired
//    @Qualifier("authenticationManager")
    private AuthenticationManager authenticationManager;

    @Autowired
    UserDetailsService userDetailsService;

//    @Value("${default.redirect:http://localhost:8080/tonr2/sparklr/redirect}")
//    private String defaultRedirectUri;


    @Bean
    public JwtTokenStore tokenStore() {
        return new JwtTokenStore(accessTokenConverter());
    }

    @Bean
    protected JwtAccessTokenConverter accessTokenConverter() {

        JwtAccessTokenConverter converter = new CustomTokenEnhancer();
        converter.setAccessTokenConverter(getDefaultAccessTokenConverter());

        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new ClassPathResource("jwt-test.jks"), "testpass".toCharArray());
        converter.setKeyPair(keyStoreKeyFactory.getKeyPair("jwt-test"));

        return converter;
    }

    @Override // [2]
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {

        endpoints
                .tokenServices(tokenServices())
                .tokenStore(tokenStore())
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService)
                .accessTokenConverter(accessTokenConverter());
    }

    @Bean
    public AuthorizationServerTokenServices tokenServices() {
        CustomDefaultTokenServices defaultTokenServices = new CustomDefaultTokenServices();
        defaultTokenServices.setTokenStore(tokenStore());
        defaultTokenServices.setReuseRefreshToken(true);
        defaultTokenServices.setSupportRefreshToken(true);
        defaultTokenServices.setSupportRefreshToken(true);

        //same thing
        defaultTokenServices.setTokenEnhancer(accessTokenConverter());
        defaultTokenServices.setTokenConverter(accessTokenConverter()); // until there is a getTokenEnhancer
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

    //    .secret(clientSecret)
//    .accessTokenValiditySeconds(3600)
//    .refreshTokenValiditySeconds(3600*24*30);
    @Override // [3]
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory().
                withClient("tonr")
                .resourceIds(DUMMY_RESOURCE_ID)
                .authorizedGrantTypes("authorization_code", "implicit")
                .authorities("ROLE_CLIENT")
                .scopes("read", "write")
                .secret("secret")
                .and()
//                .withClient("tonr-with-redirect")
//                .resourceIds(DUMMY_RESOURCE_ID)
//                .authorizedGrantTypes("authorization_code", "implicit")
//                .authorities("ROLE_CLIENT")
//                .scopes("read", "write")
//                .secret("secret")
//                .redirectUris(defaultRedirectUri)
//                .and()
                .withClient("my-client-with-registered-redirect")
                .resourceIds(DUMMY_RESOURCE_ID)
                .authorizedGrantTypes("authorization_code", "client_credentials")
                .authorities("ROLE_CLIENT")
                .scopes("read", "trust")
                .redirectUris("http://anywhere?key=value")
                .and()
                .withClient("my-trusted-client")
                .authorizedGrantTypes("password", "authorization_code", "refresh_token", "implicit")
                .authorities("ROLE_CLIENT", "ROLE_TRUSTED_CLIENT")
                .scopes("read", "write", "trust")
                .accessTokenValiditySeconds(3600)
                .refreshTokenValiditySeconds(3600 * 24 * 30)
                .and()
                .withClient("my-trusted-client-with-secret")
                .authorizedGrantTypes("password", "authorization_code", "refresh_token", "implicit", "client_credentials")
                .authorities("ROLE_CLIENT", "ROLE_TRUSTED_CLIENT")
                .scopes("read", "write", "trust")
                .secret("somesecret")
                .and()
                .withClient("my-less-trusted-client")
                .authorizedGrantTypes("authorization_code", "implicit")
                .authorities("ROLE_CLIENT")
                .scopes("read", "write", "trust")
                .and()
                .withClient("my-less-trusted-autoapprove-client")
                .authorizedGrantTypes("implicit")
                .authorities("ROLE_CLIENT")
                .scopes("read", "write", "trust")
                .autoApprove(true);
    }


    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        oauthServer.allowFormAuthenticationForClients();
    }

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
