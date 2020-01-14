package com.evil.gaomao.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * Swagger配置类
 *
 * @author fangjiaxiaobai@gmail.com
 * @since 2019-08-09
 */
@Configuration
public class SwaggerConfiguration {
    @Bean
    public Docket swaggerSpringMvcPlugin() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.evil.gaomao"))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * API的解释说明
     *
     * @return apiInfo
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("GaoMao")
                .contact(new Contact("搞毛小队", "", ""))
                .version("1.0.0")
                .description("搞毛博客")
                .build();
    }

}
