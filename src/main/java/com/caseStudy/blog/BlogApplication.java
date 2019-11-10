package com.caseStudy.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collections;

@SpringBootApplication
public class BlogApplication {

	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(BlogApplication.class);
		application.setDefaultProperties(Collections.singletonMap("server.port", "8082"));
		application.run(args);
	}

}
