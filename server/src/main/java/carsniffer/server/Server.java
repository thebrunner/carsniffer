package carsniffer.server;

public class Server {

	private InputConverter inputConverter;

	private Collection<Storage> storage;

	public Server(InputConverter inputConverter, Collecction<Storage> storage) {
		this.inputConverter = inputConverter;
		this.storage = storage;
	}

	public void receive(RAWInput rawInput) throws CarSnifferException {
		final var input = inputConverter.convert(rawInput);
		storage.foreach(s -> s.store(input));
	}

}
