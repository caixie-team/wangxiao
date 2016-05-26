package io.wangxiao.auth.config;

import io.wangxiao.auth.domain.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by bison on 1/15/16.
 */
public class CustomTokenEnhancer extends JwtAccessTokenConverter {
    //    @Autowired
//    UserRepository userRepository;
    @Autowired
    UserDetailsService userDetailsService;

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        User user = (User) authentication.getPrincipal();

        final Map<String, Object> additionalInfo = new HashMap<>();
//        additionalInfo.put("user", userDetailsService.getBasicUserInfoForToken(user));
        //TODO: BasicUserInfo
        additionalInfo.put("user", user);

        additionalInfo.put("issue_time", System.currentTimeMillis());
//        additionalInfo.put("user_secret", user.getPasswordUpdatedAt().getTime() + "");
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);

        if (authentication.getOAuth2Request().getRefreshTokenRequest() != null) {
            Map<String, Object> originalRefreshMap = decoder(accessToken.getRefreshToken().getValue());

            long issueTime = (long) originalRefreshMap.get("issue_time");
            long currentTime = System.currentTimeMillis();

            long diffTime = currentTime - issueTime;
            long oneHour = (60 * 60 * 1000);

            if (diffTime > oneHour) {
                Set scope = new HashSet<>();
                scope.add("REMEMBERED");
                ((DefaultOAuth2AccessToken) accessToken).setScope(scope);

                return super.enhance(accessToken, authentication);
            }
        }
        return super.enhance(accessToken, authentication);
    }

    public Map<String, Object> decoder(String token) {
        return super.decode(token);
    }
}
