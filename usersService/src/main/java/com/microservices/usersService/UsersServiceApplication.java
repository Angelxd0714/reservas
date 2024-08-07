package com.microservices.usersService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


// Generated by https://start.springboot.io
// 优质的 spring/boot/data/security/cloud 框架中文文档尽在 => https://springdoc.cn
@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
public class UsersServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UsersServiceApplication.class, args);
	}

}
