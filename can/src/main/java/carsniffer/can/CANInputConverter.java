package carsniffer.can;

import carsniffer.server.CarSnifferException;
import carsniffer.server.Input;
import carsniffer.server.InputConverter;
import carsniffer.server.RAWInput;
import carsniffer.server.RAWInputConverter;

public class CANInputConverter implements InputConverter {

	@Override
	public Input convert(RAWInput rawInput) throws CarSnifferException {

		final var rawCAN = convert2RAWCANMessage(rawInput);

		return new Input(rawInput, new CANMessage(rawCAN, //
				convertIdentifier(rawCAN.identifier()), //
				convertData(rawCAN.data()), //
				convertCrc(rawCAN.crc()), //
				convertAck(rawCAN.ack()) //
		));
	}

	public String convertAck(RAWInput ack) {
		return RAWInputConverter.rawInput2String(ack);
	}

	public String convertCrc(RAWInput crc) {
		return RAWInputConverter.rawInput2String(crc);
	}

	public String convertData(RAWInput data) {
		return RAWInputConverter.rawInput2String(data);
	}

	public String convertIdentifier(RAWInput identifier) {
		return RAWInputConverter.rawInput2String(identifier);
	}

	public RAWCANMessage convert2RAWCANMessage(RAWInput rawInput) throws CarSnifferException {
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

		final var startCrc = endData;
		final var endCrc = startAck;
		
		final var startAck = endAck - 2; // 1 bit
		final var endAck = rawInput.length();
		final var ack = rawInput.raw().get(startAck, endAck);
		
		return new RAWCANMessage( //
				new RAWInput(identifier, 11), //
				new RAWInput(data, controlLengthInBit), //
				new RAWInput(rawInput.raw().get(startCrc, endCrc), 16), //
				new RAWInput(ack, 1) //
		);
	}

}
