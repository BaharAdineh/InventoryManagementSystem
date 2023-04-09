package com.ivms.ivmsconfigserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class IvmsConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(IvmsConfigServerApplication.class, args);
	}

}
