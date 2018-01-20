package com.demo.demo;

import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import com.demo.controller.MainController;

@ComponentScan("com.demo")
@Component
public class JerseyConfig extends ResourceConfig {

	public JerseyConfig() {
		packages("com.demo.controller");
		register(MainController.class);
		// register(MultiPartFeature.class);
		register(MultiPartFeature.class);
	}

}
