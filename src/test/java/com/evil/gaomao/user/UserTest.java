package com.evil.gaomao.user;

import com.evil.gaomao.common.entity.RestResult;
import com.evil.gaomao.controller.UserController;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * 用户信息测试用例
 * @author fangjiaxiaobai@gmail.com
 * @date 2019-12-25
 * @since 1.0.0
 */
@SpringBootTest
@Profile("fangjiaxiaobai")
public class UserTest {

    private UserController userController;

    private TestRestTemplate restTemplate;
    
    @BeforeAll
    public void before (){

    }

    @Test
    public void getTest(){
        ResponseEntity<RestResult> forEntity = restTemplate.getForEntity("/user/1", RestResult.class);
        assert forEntity.getStatusCode() == HttpStatus.OK;
    }

    @Autowired
    public void setUserController(UserController userController) {
        this.userController = userController;
    }

    @Autowired
    public void setRestTemplate(TestRestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
}
