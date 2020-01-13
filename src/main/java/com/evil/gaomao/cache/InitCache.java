package com.evil.gaomao.cache;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author dc
 * @date 2020-01-13
 * @since 1.0.0
 */
@Component
@Slf4j
public class InitCache {

    /**
     * 此方法项目启动即会调用，用于初始化静态对象
     * @author dc
     * @date 2020-01-13 11:14
     * @since 1.0.0
     */
    public static void initAll(){
        initBlog();
        initUser();
        log.info("初始化静态对象完成");
    }

    private static void initBlog(){

    }

    public static void initUser(){

    }

}
