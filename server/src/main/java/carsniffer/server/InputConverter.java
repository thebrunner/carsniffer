package carsniffer.server;

public interface InputConverter {

	Input convert(boolean[] input) throws CarSnifferException;

}
