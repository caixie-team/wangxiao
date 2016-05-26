package io.wangxiao.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.Map;

/**
 * Created by bison on 1/15/16.
 */
public class CustomDefaultTokenServices extends DefaultTokenServices {
    private JwtAccessTokenConverter tokenConverter;
    @Autowired
    UserDetailsService userDetailsService;
    public JwtAccessTokenConverter getTokenConverter() {
        return tokenConverter;
    }
    public void setTokenConverter(JwtAccessTokenConverter tokenConverter) {
        this.tokenConverter = tokenConverter;
    }
    @Override
    protected boolean isExpired(OAuth2RefreshToken refreshToken) {
        if (super.isExpired(refreshToken) )
            return true;
        try {
            Map<String, Object> allProps = ((CustomTokenEnhancer) getTokenConverter()).decoder(refreshToken.getValue());
            // this could be anything, last password update time, password...
//            String customerSecret = ((User) userDetailsService.loadUserByUsername(allProps.get("user_name").toString())).getPasswordUpdatedAt().getTime() + "";
//            return !customerSecret.equals(allProps.get("user_secret").toString());
            return false;
        }catch (Exception e){
            e.printStackTrace();
            return true;
        }
    }
}