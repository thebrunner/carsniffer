package carsniffer.server;

import java.util.BitSet;

public class RAWInputConverter {

	public RAWInput convert(byte[] rawInput) {
		final var result = BitSet.valueOf(rawInput);
		return RAWInput.of(result, rawInput.length);
	}
	
	public static String rawInput2String(RAWInput rawInput) {
		return bitSet2String(rawInput.raw(), rawInput.length());
	}
	
	public static String bitSet2String(BitSet rawInput, int length) {
		String rawInputString = "";
		for (int i = 0; i < length-1; i++) {
			rawInputString += rawInput.get(i) ? "1" : "0";
		}
		rawInputString += rawInput.get(rawInput.length()-1) ? "1" : "0";
		return rawInputString;
	}
}
