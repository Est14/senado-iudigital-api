package com.est14.senadoiudigital;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "*")
@EnableAutoConfiguration
@EnableConfigurationProperties
@SpringBootApplication
public class SenadoIudigitalApplication {

	public static void main(String[] args) {
		SpringApplication.run(SenadoIudigitalApplication.class, args);
	}

}
