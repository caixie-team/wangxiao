package io.wangxiao.auth.service;

/**
 * Created by bison on 1/9/16.
 *
 */

import io.wangxiao.auth.domain.user.User;
import io.wangxiao.auth.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    private final Logger log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

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
            // TODO: 未激活用户
//            throw new UserNotActivatedException("User " + lowercaseLogin + " is not activated");
        }

        return userFromDatabase;

//        return new UserDetailsImpl(userFromDatabase);

    }
//    public UserDetails createUser(User user) {
//        return userRepository.save(user);
//    }
//    public User getBasicUserInfoForToken(User user) {
//        return new sicUserInfo(user);
//    }

}
