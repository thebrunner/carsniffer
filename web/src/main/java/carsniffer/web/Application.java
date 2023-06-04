package carsniffer.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import carsniffer.can.CANInputConverter;
import carsniffer.can.CANStorage;
import carsniffer.server.Server;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public Server server() {
		return new Server(new CANInputConverter(), new CANStorage());
	}

}