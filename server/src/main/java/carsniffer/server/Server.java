package carsniffer.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Collection;

public class Server {

	private final static Logger LOGGER = LoggerFactory.getLogger(Server.class);
	
	private Collection<InputConverter> inputConverter;

	private Collection<Storage> storage;

	public Server(Collection<InputConverter> inputConverter, Collection<Storage> storage) {
		this.inputConverter = inputConverter;
		this.storage = storage;
	}

	public void receive(RAWInput rawInput) throws CarSnifferException {
		inputConverter.forEach(ic -> {
			try {
				final var input = ic.convert(rawInput);
				storage.forEach(s -> {
					try {
						s.store(input);
					} catch (CarSnifferException e) {
						LOGGER.error("Error storing with " + s.getClass().getSimpleName(), e);
					}
				});
			} catch (CarSnifferException e) {
				LOGGER.error("Error converting", e);
			}
		});
	}

}
