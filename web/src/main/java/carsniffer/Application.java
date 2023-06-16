package carsniffer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import carsniffer.can.CANInputConverter;
import carsniffer.server.Server;
import carsniffer.storage.h2.H2Storage;
import carsniffer.storage.h2.JpaInputRepository;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Autowired
	private JpaInputRepository jpaInputRepository;
	
	@Bean
	public Server server() {
		return new Server( //
			List.of(new CANInputConverter()), 
			List.of(new H2Storage(jpaInputRepository)) //
		);
	}

}
