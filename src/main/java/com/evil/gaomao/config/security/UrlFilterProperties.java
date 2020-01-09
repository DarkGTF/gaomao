package com.evil.gaomao.config.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fangjiaxiaobai@gmail.com
 * @date 2019-11-28
 * @since 1.0.0
 */
@Component
@ConfigurationProperties("gaomao.url.notfilter")
@Setter
@Getter
public class UrlFilterProperties {

    private List<String> urls =  new ArrayList<>();

}
