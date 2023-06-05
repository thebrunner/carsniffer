package carsniffer.can;

import java.util.BitSet;

import carsniffer.server.CarSnifferException;
import carsniffer.server.RAWInput;

public class CANBaseFrameConverter {

	public RAWCANMessage convert(RAWInput rawInput) throws CarSnifferException {

		final var startControl = 17;
		final var endControl = 21;
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
		final var endAck = startCrc + 1;
		final var ack = rawInput.raw().get(startAck, endAck);

		return RAWCANMessage.ofBaseFrame(identifier, data, crc, ack, rawInput.arrival());
	}

	public void removeStuff(final BitSet identifier, int pos, int length) {
		for (int i = pos; i < length; i++) {
			identifier.set(i - 1, identifier.get(i));
		}
		identifier.clear(length - 1);
	}

}
