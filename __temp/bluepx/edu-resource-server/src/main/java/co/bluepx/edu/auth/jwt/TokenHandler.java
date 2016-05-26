package co.bluepx.edu.auth.jwt;

/**
 * Created by bison on 1/9/16.
 */

import co.bluepx.edu.auth.StringConditions;
import com.google.common.base.Preconditions;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.User;

public final class TokenHandler {

    private final String secret;
    private final UserService userService;

    public TokenHandler(String secret, UserService userService) {
        this.secret = StringConditions.checkNotBlank(secret);
        this.userService = Preconditions.checkNotNull(userService);
    }

    public User parseUserFromToken(String token) {
        String username = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
        return userService.loadUserByUsername(username);
    }

    public String createTokenForUser(User user) {
        return Jwts.builder()
                .setSubject(user.getUsername())
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }
}
