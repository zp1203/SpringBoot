package com.springboot;

import com.springboot.utils.EsUtils;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.io.IOException;

/**
 * @author zp
 */
@SpringBootApplication
@MapperScan(basePackages = "com.springboot.dao")
@ComponentScan("com.springboot.*")
public class StudyApplication {

	public static void main(String[] args) {
		System.out.println("SpringBoot：启动开始...");
		SpringApplication.run(StudyApplication.class, args);
		System.out.println("SpringBoot：启动结束...");
	}
}
