package com.evil.gaomao;

import com.evil.gaomao.cache.InitCache;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class GaomaoApplication {

	public static void main(String[] args) {

		SpringApplication.run(GaomaoApplication.class, args);

		InitCache.initAll();
	}

}
