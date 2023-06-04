package carsniffer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Server {

	@Autowired
	private InputConverter inputConverter;

	@Autowired
	private Storage storage;

	public void receive(byte[] arrayInput) {
		final var input = inputConverter.convert(arrayInput);

		storage.store(input);
	}

}
