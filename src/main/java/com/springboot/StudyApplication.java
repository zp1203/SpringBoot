package com.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author zp
 */
@SpringBootApplication
@MapperScan(basePackages = "com.springboot.dao")
public class StudyApplication {

	public static void main(String[] args) {
		System.out.println("SpringBoot：启动开始...");
		SpringApplication.run(StudyApplication.class, args);
		System.out.println("SpringBoot：启动结束...");
	}
}
