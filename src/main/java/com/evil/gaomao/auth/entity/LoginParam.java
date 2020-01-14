package com.evil.gaomao.auth.entity;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * 登录参数
 *
 * @author fangjiaxiaobai@gmail.com
 * @date 2020-01-14
 * @since 1.0.0
 */
@Data
public class LoginParam {

    /**
     * 登录邮箱
     */
    @NotBlank(message = "用户名或邮箱不能为空")
    @Size(max = 255,message = "用户名或邮箱的字符长度不能超过 {max}")
    private String email;

    @NotBlank(message = "登陆密码不能为空")
    @Size(max = 255,message = "用户密码字符长度不能超过 {max}")
    private String password;

}
