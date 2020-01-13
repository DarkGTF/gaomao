package com.evil.gaomao.user.service;

import com.evil.gaomao.common.model.LoginBean;
import com.evil.gaomao.entity.UserInfo;
import com.evil.gaomao.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * 用户
 *
 * @author fangjiaxiaobai@gmail.com
 * @date 2019-12-24
 * @since 1.0.0
 */
@Service
public class UserService implements UserDetailsService {

    private UserRepository userRepository;

    /**
     * 查询用户信息
     *
     * @param userId 用户的Id
     * @return 用户信息
     */
    public UserInfo get(Integer userId) {
        return userRepository.getOne(userId);
    }

    public Optional<UserInfo> findOneByEmail(String email) {
        return userRepository.findOneByEmail(email);
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<UserInfo> oneByEmail = findOneByEmail(s);
        UserInfo userInfo = oneByEmail.orElse(null);
        return LoginBean.fromUser(userInfo);
    }
}
