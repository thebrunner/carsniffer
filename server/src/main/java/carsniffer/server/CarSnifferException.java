package carsniffer.server;

public class CarSnifferException extends Exception {

	public CarSnifferException(boolean[] rawInput, String message) {
		super(rawInputString(rawInput) + message);
	}

	public CarSnifferException(boolean[] rawInput, String message, Throwable cause) {
		super(rawInputString(rawInput) + message, cause);
	}

	private static String rawInputString(boolean[] rawInput) {
		String rawInputString = "";
		for (int i = 0; i < rawInput.length-1; i++) {
			rawInputString += rawInput[i] ? "1" : "0";
		}
		rawInputString += rawInput[rawInput.length-1] ? "1" : "0";
		return "Raw input: " + rawInputString + "|";
	}

}
