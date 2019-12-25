package com.evil.gaomao.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.TreeSet;

import static com.evil.gaomao.config.GaoMaoProperties.PREFIX;

/**
 * 配置属性
 *
 * @author fangjiaxiaobai@gmail.com
 * @date 2019-12-24
 * @since 1.0.0
 */
@ConfigurationProperties(prefix = PREFIX)
@Component
@Setter
@Getter
public class GaoMaoProperties {

    public static final String PREFIX = "gaomao";

    /**
     * 开发者信息
     */
    private TreeSet<String> developers = new TreeSet<>();

}
