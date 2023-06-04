package carsniffer.can;

import carsniffer.server.Input;
import carsniffer.server.InputConverter;
import carsniffer.server.RAWInput;

public class CANInputConverter implements InputConverter {

	@Override
	public Input convert(RAWInput rawInput) {
		
		final var rawCAN = convert2RAWCANMessage(rawInput);
		
		return new Input(rawInput, new CANMessage(rawCAN));
	}

	public RAWCANMessage convert2RAWCANMessage(RAWInput rawInput) {
		
		final var length = rawInput.length();
		
		final var endAck = length - 1;
		final var startAck = endAck - 2 + 1; // 2 bit
		final var endCrc = startAck - 1;
		final var startCrc = endCrc - 16 + 1; // 16 Bit
		final var endData = startCrc - 1;
		
		return new RAWCANMessage(
				rawInput.raw().get(0, 10),
				rawInput.raw().get(11, endData),
				rawInput.raw().get(startCrc, endCrc),
				rawInput.raw().get(startAck, endAck)
				);
	}

}
