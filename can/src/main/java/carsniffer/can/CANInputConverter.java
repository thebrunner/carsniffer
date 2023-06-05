package carsniffer.can;

import java.util.BitSet;

import carsniffer.server.CarSnifferException;
import carsniffer.server.Input;
import carsniffer.server.InputConverter;
import carsniffer.server.RAWInput;
import carsniffer.server.RAWInputConverter;

public class CANInputConverter implements InputConverter {

	private final CANBaseFrameConverter baseFrameConverter = new CANBaseFrameConverter();
	private final CANExtendedFrameConverter extendedFrameConverter = new CANExtendedFrameConverter();
	
	@Override
	public Input convert(RAWInput rawInput) throws CarSnifferException {

		final boolean isExtendedFrame = rawInput.raw().get(14);
				   
		final var rawCAN = isExtendedFrame ? extendedFrameConverter.convert(rawInput)
			: baseFrameConverter.convert(rawInput);

		return new Input(rawInput, new CANMessage(rawCAN, //
				convertIdentifier(rawCAN.identifier()), //
				convertExtendedIdentifier(rawCAN.extendedIdentifier()), //
				convertData(rawCAN.data()), //
				convertCrc(rawCAN.crc()), //
				convertAck(rawCAN.ack()) //
		));
	}

	public String convertIdentifier(BitSet identifier) {
		return RAWInputConverter.bitSet2String(identifier, 11);
	}

	public String convertExtendedIdentifier(BitSet identifier) {
		return RAWInputConverter.bitSet2String(identifier, 18);
	}
	
	public String convertData(BitSet data) {
		return RAWInputConverter.bitSet2String(data, data.length());
	}

	public String convertCrc(BitSet crc) {
		return RAWInputConverter.bitSet2String(crc, 15);
	}

	public boolean convertAck(BitSet ack) {
		return ack.get(0);
	}
}
