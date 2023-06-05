package carsniffer.can;

import carsniffer.server.CarSnifferException;
import carsniffer.server.Input;
import carsniffer.server.InputConverter;
import carsniffer.server.RAWInput;
import carsniffer.server.RAWInputConverter;

public class CANInputConverter implements InputConverter {

	private final CANBaseFrameConverter baseFrameConverter = new CANBaseFrameConverter();
	
	@Override
	public Input convert(RAWInput rawInput) throws CarSnifferException {

		final var rawCAN = baseFrameConverter.convert(rawInput);

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

}
