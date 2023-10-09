package com.example.thishouse;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.thishouse.mapper")
public class ThishouseApplication {
	public static void main(String[] args) {
		SpringApplication.run(ThishouseApplication.class, args);
	}
}
