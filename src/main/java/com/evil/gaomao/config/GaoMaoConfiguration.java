package com.evil.gaomao.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

/**
 * 系统自定义全局配置
 *
 * @author wangxiyue@cyou-inc.com
 * @date 2019-12-24
 * @since 1.0.0
 */
@Configuration
public class GaoMaoConfiguration {

    private GaoMaoProperties properties;

    @Autowired
    public void setProperties(GaoMaoProperties properties) {
        this.properties = properties;
    }
}

