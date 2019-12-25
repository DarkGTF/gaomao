package com.evil.gaomao.entity;

import com.evil.gaomao.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

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
@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name = "tb_user_info")
public class UserInfo extends BaseEntity {

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
        return this.userId;
    }
}
