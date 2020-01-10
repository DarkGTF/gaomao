package com.evil.gaomao.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * 用户权限信息
 *
 * @author fangjiaxiaobai@gmail.com
 * @date 2019-12-24
 * @since 1.0.0
 */
@Entity(name = "tb_user_auth")
@Data
public class UserAuth {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userAuthId;

    /**
     * 用户Id
     */
    private Integer userId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 权限类型
     */
    private String authType;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建用户
     */
    private Integer createUser;

    /**
     * 最后更新时间
     */
    private Date lastEditTime;

    /**
     * 逻辑删除标识
     */
    private Boolean deleted = Boolean.FALSE;

}
