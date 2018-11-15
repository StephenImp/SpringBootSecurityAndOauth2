package com.mozi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@MapperScan("com.mozi.mapper")
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })// 关闭mybatis的自动配置--据说是为了配置多数据源
public class AttendanceSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(AttendanceSystemApplication.class, args);
	}
}
