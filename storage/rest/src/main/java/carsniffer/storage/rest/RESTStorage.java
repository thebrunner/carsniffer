package carsniffer.storage.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.http.HttpStatusCode;
import reactor.core.publisher.Mono;

import carsniffer.server.CarSnifferException;
import carsniffer.server.Input;
import carsniffer.server.Storage;

public class RESTStorage implements Storage {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(RESTStorage.class);

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
				.onStatus(HttpStatusCode::isError, 
					  error -> Mono.error(new CarSnifferException("Cannot send: " + error)) //
				.toBodilessEntity() //
				.log() //
		;
	}

}
