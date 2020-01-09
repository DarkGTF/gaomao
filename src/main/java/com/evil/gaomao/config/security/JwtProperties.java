package com.evil.gaomao.config.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Jwt 配置
 * @author fangjiaxiaobai@gmail.com
 * @date 2019-11-27
 * @since 1.0.0
 */
@ConfigurationProperties("gaomao.jwt")
@Component
@Setter
@Getter
public class JwtProperties {

    /**
     * token
     */
    private String tokenHeader;

    /**
     * 过期时间
     */
    private long expiration;

    /**
     * 秘钥
     */
    private String secret;
}
