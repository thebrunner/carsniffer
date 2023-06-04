package carsniffer.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import carsniffer.can.CANInputConverter;
import carsniffer.server.Server;
import carsniffer.storage.DummyStorage;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public Server server() {
		return new Server(new CANInputConverter(), new DummyStorage());
	}

}