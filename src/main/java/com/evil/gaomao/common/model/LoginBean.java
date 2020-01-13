package com.evil.gaomao.common.model;

import com.evil.gaomao.user.entity.UserInfo;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;

/**
 * 登录实体
 *
 * @author fangjiaxiaobai@gmail.com
 * @date 2019-11-14
 * @since 1.0.0
 */
@Data
public class LoginBean implements UserDetails {

    private Integer id;
    /**
     * 登录类型，手机号还是邮箱
     */
    private LoginType loginType;

    /**
     * 登录的token
     */
    private String token;

    /**
     * 用户名
     */
    private String username;

    /**
     * 头像地址
     */
    private String avatarUrl;

    /**
     * 登录描述
     */
    private String description;

    /**
     * 登录时间
     */
    private Date loginTime;

    /**
     * 别名，指定消息接收人
     */
    private String alias;

    private String email;

    /**
     * 是否登录成功
     */
    private boolean succeed = false;

    private String phoneNumber;

    public static  LoginBean anonymousLoginBean(){
        LoginBean loginBean = new LoginBean();
        loginBean.setUsername("doraemon");
        loginBean.setId(-1);
        return loginBean;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        // 密码默认返回 asdf123.
        return "$2a$10$/iy51mdRIrrUwDMB8.WjkeKixfjUTBWnNLtWuGVYYr9enbHjok89m";
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public enum LoginType {
        phone, email;
    }

    public static LoginBean fromUser(UserInfo user) {
        if (null == user) {
            return null;
        }
        LoginBean loginBean = new LoginBean();
        loginBean.setId(user.getId());
        loginBean.setEmail(user.getEmail());
        return loginBean;
    }

}
