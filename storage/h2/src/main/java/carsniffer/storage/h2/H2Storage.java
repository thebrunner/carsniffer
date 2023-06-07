package carsniffer.storage.h2;

import java.sql.Timestamp;

import carsniffer.can.CANMessage;
import carsniffer.server.CarSnifferException;
import carsniffer.server.Input;
import carsniffer.server.RAWInputConverter;
import carsniffer.server.Storage;

public class H2Storage implements Storage {
	
	private JpaInputRepository jpaInputRepository;

	public H2Storage(JpaInputRepository jpaInputRepository) {
		this.jpaInputRepository = jpaInputRepository;
	}
	
	@Override
	public void store(Input input) throws CarSnifferException {
		jpaInputRepository.save(convert(input));
	}

	public JpaInput convert(Input input) throws CarSnifferException {
		final var jpaInput = new JpaInput();
		jpaInput.setRaw(RAWInputConverter.rawInput2String(input.raw()));
		final var converted = CANMessage.class.cast(input.converted());
		jpaInput.setIdentifier(converted.identifier());
		jpaInput.setExtendedIdentifier(converted.extendedIdentifier());
		jpaInput.setData(converted.data());
		jpaInput.setCrc(converted.crc());
		jpaInput.setAck(converted.ack());
		jpaInput.setArrival(Timestamp.valueOf(input.raw().arrival()));
		return jpaInput;
	}

}
