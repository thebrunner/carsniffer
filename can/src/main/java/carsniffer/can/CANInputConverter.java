package carsniffer.can;

import java.util.Arrays;

import carsniffer.server.Input;
import carsniffer.server.InputConverter;

public class CANInputConverter implements InputConverter {

	@Override
	public Input convert(boolean[] rawInput) {

		final var rawCAN = convert2RAWCANMessage(rawInput);

		return new Input(rawInput, new CANMessage(rawCAN));
	}

	public RAWCANMessage convert2RAWCANMessage(boolean[] rawInput) {

		final var endAck = rawInput.length;
		final var startAck = endAck - 2; // 2 bit
		final var endCrc = startAck;
		final var startCrc = endCrc - 16; // 16 Bit
		final var endData = startCrc;

		return new RAWCANMessage( //
				Arrays.copyOfRange(rawInput, 0, 11), //
				Arrays.copyOfRange(rawInput, 11, endData), //
				Arrays.copyOfRange(rawInput, startCrc, endCrc), //
				Arrays.copyOfRange(rawInput, startAck, endAck) //
		);
	}

}
