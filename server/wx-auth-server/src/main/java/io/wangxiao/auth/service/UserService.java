package io.wangxiao.auth.service;

import io.wangxiao.auth.domain.dto.UserFormDto;
import io.wangxiao.auth.domain.dto.UserJsonDto;
import io.wangxiao.auth.domain.dto.UserOverviewDto;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author Shengzhao Li
 */
public interface UserService extends UserDetailsService {

    UserJsonDto loadCurrentUserJsonDto();

    UserOverviewDto loadUserOverviewDto(UserOverviewDto overviewDto);

    boolean isExistedUsername(String username);

    String saveUser(UserFormDto formDto);
}