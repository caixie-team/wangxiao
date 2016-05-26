package io.wangxiao.auth.service;

/**
 * Created by bison on 1/9/16.
 */

import io.wangxiao.auth.domain.User;
import io.wangxiao.auth.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;


@Component("userDetailsService")
public class UserService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final Logger log = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String login) {

        log.debug("Authenticating {}", login);
        String lowercaseLogin = login.toLowerCase();

        User userFromDatabase;
        if (lowercaseLogin.contains("@")) {
            userFromDatabase = userRepository.findByEmail(lowercaseLogin);
        } else {
            userFromDatabase = userRepository.findByUsernameCaseInsensitive(lowercaseLogin);
        }

        if (userFromDatabase == null) {
            throw new UsernameNotFoundException("User " + lowercaseLogin + " was not found in the database");
        } else if (userFromDatabase.getIsavalible()>0) {
//            throw new UserNotActivatedException("User " + lowercaseLogin + " is not activated");
        }

        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
//        grantedAuthorities.add(new SimpleGrantedAuthority("USER"));
//        for (Authority authority : userFromDatabase.getAuthorities()) {
//            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(authority.getName());
//            grantedAuthorities.add(grantedAuthority);
//        }
        return new org.springframework.security.core.userdetails.User(userFromDatabase.getEmail(), userFromDatabase.getPassword(), AuthorityUtils.createAuthorityList("USER"));

    }

}
