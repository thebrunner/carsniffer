package carsniffer.storage.h2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration
@ComponentScan
public class Application {
	
	public static void main(final String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
