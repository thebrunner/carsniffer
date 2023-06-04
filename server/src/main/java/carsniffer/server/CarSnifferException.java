package carsniffer.server;

public class CarSnifferException extends Exception {

	public CarSnifferException(RAWInput rawInput, String message) {
		super(rawInputString(rawInput) + message);
	}

	public CarSnifferException(RAWInput rawInput, String message, Throwable cause) {
		super(rawInputString(rawInput) + message, cause);
	}

	private static String rawInputString(RAWInput rawInput) {
		String rawInputString = "";
		for (int i = 0; i < rawInput.length()-1; i++) {
			rawInputString += rawInput.raw().get(i) ? "1" : "0";
		}
		rawInputString += rawInput.raw().get(rawInput.length()-1) ? "1" : "0";
		return "Raw input: " + rawInputString + "|";
	}

}
