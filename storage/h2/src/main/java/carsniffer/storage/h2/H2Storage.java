package carsniffer.storage.h2;

import org.springframework.beans.factory.annotation.Autowired;

import carsniffer.server.CarSnifferException;
import carsniffer.server.Input;
import carsniffer.server.RAWInputConverter;
import carsniffer.server.Storage;

public class H2Storage implements Storage {

	@Autowired
	private JpaInputRepository inputRepository;

	@Override
	public void store(Input input) throws CarSnifferException {
		inputRepository.save(convert(input));
	}

	public JpaInput convert(Input input) {
		final var jpaInput = new JpaInput();
		jpaInput.setRaw(RAWInputConverter.rawInput2String(input.raw()));
		jpaInput.setConverted(input.converted().toString());
		return jpaInput;
	}

}