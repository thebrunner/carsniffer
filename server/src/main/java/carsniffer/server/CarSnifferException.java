package carsniffer.server;

public class CarSnifferException extends Exception {
	
	public CarSnifferException(RAWInput rawInput, String message) {
		super(rawInputString(rawInput) + message);
	}

	public CarSnifferException(RAWInput rawInput, String message, Throwable cause) {
		super(rawInputString(rawInput) + message, cause);
	}

	private static String rawInputString(RAWInput rawInput) {
		final var rawInputString = RAWInputConverter.rawInput2String(rawInput);
		return "Raw input: " + rawInputString + "|";
	}

}
