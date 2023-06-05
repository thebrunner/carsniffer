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

		if (rawInput.length() < 11 + 16 + 2) {
			throw new CarSnifferException(rawInput, "Input to short: length " + rawInput.length());
		}

		final var endAck = rawInput.length();
		final var startAck = endAck - 2; // 2 bit
		final var endCrc = startAck;
		final var startCrc = endCrc - 16; // 16 Bit
		final var endData = startCrc;

		return new RAWCANMessage( //
				new RAWInput(rawInput.raw().get(0, 11), 11), //
				new RAWInput(rawInput.raw().get(11, endData), rawInput.length() - 11 - 16 - 2), //
				new RAWInput(rawInput.raw().get(startCrc, endCrc), 16), //
				new RAWInput(rawInput.raw().get(startAck, endAck), 2) //
		);
	}

}
