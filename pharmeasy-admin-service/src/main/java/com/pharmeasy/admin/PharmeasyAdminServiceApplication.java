package com.pharmeasy.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableAutoConfiguration
@EntityScan(basePackages = "com.pharmeasy.qsr.commonpersistence.entity")
@EnableJpaRepositories(basePackages = "com.pharmeasy.qsr.commonpersistence.repository")

public class PharmeasyAdminServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PharmeasyAdminServiceApplication.class, args);
	}

}
