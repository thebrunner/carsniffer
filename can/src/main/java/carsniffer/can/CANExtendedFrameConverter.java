package carsniffer.can;

import carsniffer.server.CarSnifferException;
import carsniffer.server.RAWInput;

public class CANExtendedFrameConverter extends CANBaseFrameConverter {

	public RAWCANMessage convert(RAWInput rawInput) throws CarSnifferException {

		final var startControl = 34;
		final var endControl = 38;
		final var control = rawInput.raw().get(startControl, endControl);
		final int controlLengthInByte = Long.valueOf(control.toLongArray()[0]).intValue();
		final int controlLengthInBit = controlLengthInByte + (controlLengthInByte * 8);

		if (rawInput.length() < endControl + controlLengthInBit + 16 + 2) {
			throw new CarSnifferException(rawInput, "Input to short: length " + rawInput.length());
		}

		final var startIdentifier = 1;
		final var endIdentifier = 13; // stuff bit 5 (index 4)
		final var identifier = rawInput.raw().get(startIdentifier, endIdentifier);
		removeStuff(identifier, 4, 12);

		final var startExtendedIdentifier = endIdentifier + 2;
		final var endExtendedIdentifier = startExtendedIdentifier + 18;
		final var extendedIdentifier = rawInput.raw().get(startExtendedIdentifier, endExtendedIdentifier);

		final var startData = endControl;
		final var endData = startData + controlLengthInBit + 1;
		final var data = rawInput.raw().get(startData, endData);
		for (int i = 0; i < controlLengthInByte; i++) {
			removeStuff(data, 5 + (i * 8), controlLengthInBit);
		}

		final var startCrc = endData;
		final var endCrc = startCrc + 16; // stuff bit end - 3
		final var crc = rawInput.raw().get(startCrc, endCrc);
		removeStuff(crc, 16 - 3, 16);

		final var startAck = endCrc + 1; // delimiter
		final var endAck = startAck + 1;
		final var ack = rawInput.raw().get(startAck, endAck);

		return new RAWCANMessage(identifier, extendedIdentifier, data, crc, ack, rawInput.arrival());
	}

}
