package com.evil.gaomao.repo;

import com.evil.gaomao.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 用户Repository
 *
 * @author fangjiaxiaobai@gmail.com
 * @date 2019-12-25
 * @since 1.0.0
 */
@Repository
public interface UserRepository extends JpaRepository<UserInfo, Integer> {


}
