package com.peapod.paymint.kafkademo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.peapod.paymint.kafkademo"})
public class KafkaDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaDemoApplication.class, args);
	}

}
