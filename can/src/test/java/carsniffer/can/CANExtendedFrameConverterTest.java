package carsniffer.can;

import static org.junit.jupiter.api.Assertions.*;

import java.util.BitSet;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import carsniffer.server.CarSnifferException;
import carsniffer.server.RAWInput;

class CANExtendedFrameConverterTest {

	CANExtendedFrameConverter converter = new CANExtendedFrameConverter();

	@Test
	void testConvert() throws CarSnifferException {
		var bitset = new BitSet();
		bitset.set(5);
		bitset.set(8);
		bitset.set(10+18);
		bitset.set(16+18);
		bitset.set(20+18);
		bitset.set(26+18);
		bitset.set(29+18,33+18);
		bitset.set(34+18,37+18);
		bitset.set(38+18);
		bitset.set(40+18);
		bitset.set(43+18,47+18);
		bitset.set(48+18,59+18);
		
		var rawInput = RAWInput.of(bitset, 58+18);
		
		var result = converter.convert(rawInput);
		
		assertNotNull(result);
		assertNotNull(result.identifier());
		
		var identifier = new BitSet(11);
		identifier.set(6);
		identifier.set(8);
		assertEquals(identifier, result.identifier());
		
		var data = new BitSet(8);
		data.set(7);
		assertEquals(data, result.data());
		
		var crc = new BitSet(15);
		crc.set(0,3);
		crc.set(4,7);
		crc.set(8);
		crc.set(10);
		crc.set(13,15);
		assertEquals(crc, result.crc());
		
		assertEquals(new BitSet(1), result.ack());
	}

	@Test
	void testRemoveStuff() {
		var bitset = new BitSet(3);
		bitset.set(1);
		
		converter.removeStuff(bitset, 1, 3);
		
		assertEquals(new BitSet(2), bitset);
	}

}
