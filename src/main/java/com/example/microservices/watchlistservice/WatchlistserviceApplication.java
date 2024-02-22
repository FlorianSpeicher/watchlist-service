package com.example.microservices.watchlistservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class WatchlistserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(WatchlistserviceApplication.class, args);
	}

}
