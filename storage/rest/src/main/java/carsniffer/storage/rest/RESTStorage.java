package carsniffer.storage.rest;

import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import carsniffer.server.CarSnifferException;
import carsniffer.server.Input;
import carsniffer.server.Storage;

public class RESTStorage implements Storage {

	private final WebClient webClient = WebClient.create();
	
	private Config config;
	
	public RESTStorage(Config config) {
		this.config = config;
	}
	
	@Override
	public void store(Input input) throws CarSnifferException {
		webClient //
				.post() //
				.uri(config.getUri()) //
				.body(BodyInserters.fromValue(input)) //
				.retrieve() //
		;
	}

}
