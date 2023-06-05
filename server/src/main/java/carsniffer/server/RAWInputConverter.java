package carsniffer.server;

import java.util.BitSet;

public class RAWInputConverter {

	public RAWInput convert(byte[] rawInput) {
		final var result = BitSet.valueOf(rawInput);
		return RAWInput.of(result, rawInput.length);
	}
	
	public static String rawInput2String(RAWInput rawInput) {
		String rawInputString = "";
		for (int i = 0; i < rawInput.length()-1; i++) {
			rawInputString += rawInput.raw().get(i) ? "1" : "0";
		}
		rawInputString += rawInput.raw().get(rawInput.length()-1) ? "1" : "0";
		return rawInputString;
	}
}
