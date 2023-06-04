package carsniffer.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import carsniffer.server.RAWInputConverter;
import carsniffer.server.Server;

@RestController
public class RESTController {

	private final static Logger LOGGER = LoggerFactory.getLogger(RESTController.class);

	@Autowired
	private Server server;
	
	private RAWInputConverter inputConverter = new RAWInputConverter();

	@PostMapping("/receive")
	public ResponseEntity<?> receive(byte[] rawInput) {
		try {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Receiving: " + rawInput);
			}

			server.receive(inputConverter.convert(rawInput));

			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Successfully converting " + rawInput);
			}

			return ResponseEntity.ok(null);
		} catch (Throwable t) {
			LOGGER.error("", t);
			return ResponseEntity.internalServerError().body(t);
		}
	}

}