package com.evil.gaomao.service;

import com.evil.gaomao.entity.UserInfo;
import com.evil.gaomao.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户
 *
 * @author fangjiaxiaobai@gmail.com
 * @date 2019-12-24
 * @since 1.0.0
 */
@Service
public class UserService {

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


    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
