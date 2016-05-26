package io.wangxiao.auth.config;

import io.wangxiao.auth.domain.user.User;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.provider.token.DefaultUserAuthenticationConverter;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.Map;

/**
 * Created by bison on 1/15/16.
 */
public class CustomUserAuthenticationConvertor extends DefaultUserAuthenticationConverter {

    @Override
    public Authentication extractAuthentication(Map<String, ?> map) {

        if (map.containsKey("user")) {
//            Map userMap = (Map) map.get("user");
            User principal = (User)map.get("user");
//            principal.setId(Integer.parseInt(String.valueOf(userMap.get("id"))));
//            principal.setEmail(((Map)map.get("user")).get("email").toString());
//            principal.setPasswordUpdatedAt(new Date(Long.parseLong((String)map.get("user_secret"))));
//            Collection<? extends GrantedAuthority> authorities = getAuthorities(map);
            Collection<? extends GrantedAuthority> authorities = getAuthorities(map);
//            return new UserAuthentication(principal);
//            return new UsernamePasswordAuthenticationToken(principal, "N/A", authorities);
            return new UsernamePasswordAuthenticationToken(principal, "N/A", authorities);
        }
        return super.extractAuthentication(map);
    }
    // copied as is, because its not protected
    Collection<? extends GrantedAuthority> getAuthorities(Map<String, ?> map) {

        Object authorities = map.get(AUTHORITIES);
        if (authorities instanceof String) {
            return AuthorityUtils.commaSeparatedStringToAuthorityList((String) authorities);
        }
        if (authorities instanceof Collection) {
            return AuthorityUtils.commaSeparatedStringToAuthorityList(StringUtils
                    .collectionToCommaDelimitedString((Collection<?>) authorities));
        }
        throw new IllegalArgumentException("Authorities must be either a String or a Collection");
    }
}
