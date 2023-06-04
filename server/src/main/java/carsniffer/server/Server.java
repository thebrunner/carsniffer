package carsniffer.server;

public class Server {

	private InputConverter inputConverter;

	private Storage storage;

	public Server(InputConverter inputConverter, Storage storage) {
		this.inputConverter = inputConverter;
		this.storage = storage;
	}

	public void receive(RAWInput rawInput) {
		
		final var input = inputConverter.convert(rawInput);
		
		storage.store(input);
	}

}
