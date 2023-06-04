package carsniffer.server;

import java.util.BitSet;

public class RAWInputConverter {

	public RAWInput convert(byte[] rawInput) {
		final var result = BitSet.valueOf(rawInput);
		return new RAWInput(result, rawInput.length);
	}
}
