package com.evil.gaomao.entity;

import com.evil.gaomao.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Base64;
import java.util.Date;

/**
 * 用户权限信息
 *
 * @author fangjiaxiaobai@gmail.com
 * @date 2019-12-24
 * @since 1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@Entity(name = "tb_user_auth")
@Data
public class UserAuth extends BaseEntity {

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
    @CreatedDate
    private Date createTime;

    /**
     * 创建用户
     */
    @CreatedBy
    private Integer createUser;

    /**
     * 最后更新时间
     */
    @LastModifiedDate
    private Date lastEditTime;

    /**
     * 逻辑删除标识
     */
    private Boolean deleted = Boolean.FALSE;

    @Override
    public Integer getId() {
        return this.userAuthId;
    }
}
