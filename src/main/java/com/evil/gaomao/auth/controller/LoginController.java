package com.evil.gaomao.auth.controller;

import com.evil.gaomao.auth.entity.LoginParam;
import com.evil.gaomao.common.constant.LogType;
import com.evil.gaomao.common.entity.RestResult;
import com.evil.gaomao.common.model.LogEvent;
import com.evil.gaomao.common.model.LoginBean;
import com.evil.gaomao.common.utils.JwtUtil;
import io.micrometer.core.instrument.util.StringUtils;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

/**
 * 登录相关操作 控制器
 *
 * @author fangjiaxiaobai@gmail.com
 * @date 2019-12-24
 * @since 1.0.0
 */
@Api
@Slf4j
@RestController
@RequestMapping("/auth")
public class LoginController {

    private JwtUtil jwtUtil;

    private UserDetailsService userDetailsService;

    /**
     * 事件发布器
     */
    private ApplicationEventPublisher applicationEventPublisher;

    public LoginController(JwtUtil jwtUtil,
                           @Qualifier("userService") UserDetailsService userDetailsService,
                           ApplicationEventPublisher applicationEventPublisher) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
        this.applicationEventPublisher = applicationEventPublisher;
    }


    @PostMapping(value = "login")
    public RestResult<LoginBean> login(@RequestBody LoginParam loginParam) {

        UserDetails userDetails = userDetailsService.loadUserByUsername(loginParam.getEmail());
        if (userDetails == null) {
            return RestResult.newFailureInstance("认证失败,用户不存在");
        }
        String password = userDetails.getPassword();

        boolean passwordValid = !StringUtils.isBlank(password) && BCrypt.checkpw(loginParam.getPassword(), password);

        if (!passwordValid) {
            return RestResult.newFailureInstance("认证失败,密码错误");
        }

        if (userDetails instanceof LoginBean) {
            LoginBean loginBean = (LoginBean) userDetails;
            loginBean.setSucceed(true);
            loginBean.setToken(jwtUtil.generateToken(loginBean, false));
            applicationEventPublisher.publishEvent(new LogEvent(this,LogType.LOGGED_IN,userDetails.getUsername()));
            return RestResult.newFailureInstance(loginBean);
        }
        return RestResult.newFailureInstance("系统异常");
    }


}
