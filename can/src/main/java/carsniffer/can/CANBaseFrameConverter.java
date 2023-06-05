package carsniffer.can;

import carsniffer.server.CarSnifferException;
import carsniffer.server.Input;
import carsniffer.server.InputConverter;
import carsniffer.server.RAWInput;
import carsniffer.server.RAWInputConverter;

public class CANBaseFrameConverter {

	public RAWCANMessage convert(RAWInput rawInput) throws CarSnifferException {
// BASE FRAME CAN A / CAN B
		
		final var startControl = 17;
		final var endControl = 21;
		final var control = rawInput.raw().get(startControl, endControl);
		final int controlLengthInByte =  --controlToInt == length data
		final int controlLengthInBit = controlLengthInByte + (controlLengthInByte * 8);
		
		if (rawInput.length() < 21 + controlLengthInBit + 16 + 2) {
			throw new CarSnifferException(rawInput, "Input to short: length " + rawInput.length());
		}

		final var startIdentifier = 1;
		final var endIdentifier = 13; // stuff bit 5 (index 4)
		final var identifier = rawInput.raw().get(startIdentifier, endIdentifier);
		-- delete bit 5 (index 4)

		final var startData = endControl;
		final var endData = startData + controlLengthInBit + 1;
		final var data = rawInput.raw().get(startData, endData);
		-- delete stuff bit immer das 6. Bit im Byte (pos 5)

		final var startCrc = endData;
		final var endCrc = startCrc + 16; // stuff bit end - 3
		final var crc = rawInput.raw().get(startCrc, endCrc);
		-- delete stuff bit end - 3
		
		final var startAck = endCrc + 1; // delimiter
		final var endAck = startCrc + 1;
		final var ack = rawInput.raw().get(startAck, endAck);
		
		return new RAWCANMessage( //
				new RAWInput(identifier, 11), //
				new RAWInput(data, controlLengthInBit), //
				new RAWInput(crc, 15), //
				new RAWInput(ack, 1) //
		);
	}

}
