package carsniffer.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import carsniffer.server.CarSnifferException;
import carsniffer.server.Input;
import carsniffer.server.InputConverter;
import carsniffer.server.RAWInput;
import carsniffer.server.Server;
import carsniffer.server.Storage;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public Server server() {
		return new Server(new InputConverter() {
			
			@Override
			public Input convert(RAWInput input) throws CarSnifferException {
				return new Input(input, new Object());
			}
		}, new Storage() {
			
			@Override
			public void store(Input input) throws CarSnifferException {
			}
		});
	}

}