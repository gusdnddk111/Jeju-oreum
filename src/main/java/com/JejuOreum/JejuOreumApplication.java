package com.JejuOreum;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class JejuOreumApplication {

	public static void main(String[] args) {
		SpringApplication.run(JejuOreumApplication.class, args);
	}

}
