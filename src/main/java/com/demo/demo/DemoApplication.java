package com.demo.demo;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.demo")
@EntityScan("com.demo.domain") 
public class DemoApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		new DemoApplication().configure(new SpringApplicationBuilder(DemoApplication.class)).run(args);
	}
}
