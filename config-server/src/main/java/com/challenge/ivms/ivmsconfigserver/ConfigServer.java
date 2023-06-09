package com.challenge.ivms.ivmsconfigserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
class ConfigServer {
	public static void main(final String[] args) {
		SpringApplication.run(ConfigServer.class, args);
	}
}
