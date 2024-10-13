package com.valrichter.docushield;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(Application.class, args);
		Environment env = context.getEnvironment();
		String appName = env.getProperty("spring.application.name");
        assert appName != null;
        System.out.println("<-----> " + appName.toUpperCase() + " <----->" );
	}

}
