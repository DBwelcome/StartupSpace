package com.dbproject.startupspace;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class StartupspaceApplication {

	public static void main(String[] args) {
		SpringApplication.run(StartupspaceApplication.class, args);
	}

}
