package carsniffer.server;

public class Server {

	private InputConverter inputConverter;

	private Storage storage;

	public Server(InputConverter inputConverter, Storage storage) {
		this.inputConverter = inputConverter;
		this.storage = storage;
	}

	public void receive(byte[] arrayInput) {
		
		final var input = inputConverter.convert(arrayInput);
		
		storage.store(input);
	}

}