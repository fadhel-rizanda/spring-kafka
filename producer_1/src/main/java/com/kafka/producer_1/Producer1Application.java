package com.kafka.producer_1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class Producer1Application {

	public static void main(String[] args) {
		SpringApplication.run(Producer1Application.class, args);
	}

}
