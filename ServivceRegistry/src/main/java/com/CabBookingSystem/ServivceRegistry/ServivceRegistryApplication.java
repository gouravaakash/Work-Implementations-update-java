package com.CabBookingSystem.ServivceRegistry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class ServivceRegistryApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServivceRegistryApplication.class, args);
	}

}
