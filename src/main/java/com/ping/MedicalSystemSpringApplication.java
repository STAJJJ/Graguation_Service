package com.ping;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.ping.mapper")
public class MedicalSystemSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(MedicalSystemSpringApplication.class, args);
	}

}
	