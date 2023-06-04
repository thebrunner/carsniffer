package carsniffer.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import carsniffer.server.Boolean2RAWInputConverter;
import carsniffer.server.Server;

@RestController
public class RESTController {

	private final static Logger LOGGER = LoggerFactory.getLogger(RESTController.class);

	@Autowired
	private Server server;
	
	private final Boolean2RAWInputConverter bitSetConverter = new Boolean2RAWInputConverter();

	@PostMapping("/receive")
	public ResponseEntity<Void> receive(boolean[] booleanInput) {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Receiving: " + booleanInput);
		}

		server.receive(bitSetConverter.convert(booleanInput));

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Successfully converting " + booleanInput);
		}

		return ResponseEntity.ok(null);
	}

}