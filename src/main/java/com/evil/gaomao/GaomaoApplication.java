package com.evil.gaomao;

import com.evil.gaomao.cache.InitCache;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication
@EnableSwagger2
public class GaomaoApplication {

	public static void main(String[] args) {

		SpringApplication.run(GaomaoApplication.class, args);

		InitCache.initAll();
	}

}
