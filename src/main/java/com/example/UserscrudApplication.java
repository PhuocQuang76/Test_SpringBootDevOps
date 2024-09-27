package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;

import com.example.userscrud.entity.Post;
import com.example.userscrud.entity.User;
import com.fasterxml.jackson.databind.type.ArrayType;

@SpringBootApplication
public class UserscrudApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(UserscrudApplication.class, args);
		
	}
	public SpringApplicationBuilder configure(SpringApplicationBuilder builder){
		return builder.sources(SpringApplication.class);
	}

}


