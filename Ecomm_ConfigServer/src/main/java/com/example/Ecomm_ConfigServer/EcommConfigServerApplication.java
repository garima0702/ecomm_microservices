package com.example.Ecomm_ConfigServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class EcommConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcommConfigServerApplication.class, args);
	}

}
