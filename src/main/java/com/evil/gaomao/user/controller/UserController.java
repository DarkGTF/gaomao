package com.evil.gaomao.user.controller;

import com.evil.gaomao.common.constant.ResponseCode;
import com.evil.gaomao.common.entity.RestResult;
import com.evil.gaomao.entity.UserInfo;
import com.evil.gaomao.service.UserService;
import io.swagger.annotations.Api;
import jdk.nashorn.internal.objects.annotations.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

/**
 * 用户相关控制器
 *
 * @author fangjiaxiaobai@gmail.com
 * @date 2019-12-24
 * @since 1.0.0
 */
@Slf4j
@RestController
@RequestMapping("user")
@Api
public class UserController {

    private UserService userService;

    @GetMapping("{userId}")
    public RestResult<UserInfo> get(@PathVariable("userId") Integer userId) {
        Assert.notNull(userId, "userId 不能为空");
        UserInfo userInfo = userService.get(userId);
        log.debug("根据 userId = [{}] 查询到一条数据", userId);
        return RestResult.newSuccessInstance(userInfo);
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
