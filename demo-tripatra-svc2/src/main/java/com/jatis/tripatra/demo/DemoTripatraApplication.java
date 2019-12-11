package com.jatis.tripatra.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class DemoTripatraApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoTripatraApplication.class, args);
	}

}
