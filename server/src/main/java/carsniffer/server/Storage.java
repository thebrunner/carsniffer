package carsniffer.server;

public interface Storage {

	void store(Input input) throws CarSnifferException;

}
