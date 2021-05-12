package com.thuctap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.thuctap.property.FileStorageProperties;

@SpringBootApplication
@EnableConfigurationProperties(FileStorageProperties.class)
public class ThucTapApplication {

	public static void main(String[] args) {
		SpringApplication.run(ThucTapApplication.class, args);
	}

}
