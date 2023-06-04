package carsniffer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Server {

	private static final Logger LOGGER = LoggerFactory.getLogger(Server.class);
	
	@Autowired
	private InputConverter inputConverter;

	@Autowired
	private Storage storage;

	public void receive(byte[] arrayInput) {
		
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info("Incomming: " + arrayInput);
		}
		
		final var input = inputConverter.convert(arrayInput);

		if (LOGGER.isInfoEnabled()) {
			LOGGER.info("Converter result: " + input);
		}
		
		storage.store(input);
	}

}
