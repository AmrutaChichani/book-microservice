package com.springboot.microservice.book;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableJpaAuditing
@EnableJpaRepositories
//@EnableDiscoveryClient
public class BooksMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BooksMicroserviceApplication.class, args);
	}

}
