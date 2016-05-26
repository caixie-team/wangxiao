package io.wangxiao.auth.repository;

/**
 * Created by bison on 1/10/16.
 */

import io.wangxiao.auth.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, String> {

    @Query("SELECT u FROM User u WHERE LOWER(u.email) = LOWER(:email)")
    User findByUsernameCaseInsensitive(@Param("email") String email);

    @Query
    User findByEmail(String email);

//    @Query
//    User findByEmailAndActivationKey(String email, String activationKey);

//    @Query
//    User findByEmailAndResetPasswordKey(String email, String resetPasswordKey);

}
