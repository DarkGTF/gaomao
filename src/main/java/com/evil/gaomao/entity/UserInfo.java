package com.evil.gaomao.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author fangjiaxiaobai@gmail.com
 * @date 2019-12-24
 * @since 1.0.0
 */
@Data
@Entity(name = "t_user_info")
public class UserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 头像id
     */
    private Integer profileImg;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 性别
     */
    private String gender;

    /**
     * 用户状态
     */
    private String enableStatus;

    /**
     * 用户类型
     */
    private String userType;

    /**
     * 创建时间
     */
    private Date creatTime;

    /**
     * 创建用户
     */
    private Integer createUser;

    /**
     * 最后更新时间
     */
    private String lastEditTime;

    /**
     * 逻辑删除标识
     */
    private boolean deleted;

}
