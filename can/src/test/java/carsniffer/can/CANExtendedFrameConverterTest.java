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
	
	//@Test TODO
	void testConvert() throws CarSnifferException {
		var bitset = new BitSet();
		bitset.set(5);
		bitset.set(8);
		bitset.set(10);
		bitset.set(16);
		bitset.set(20);
		bitset.set(26);
		bitset.set(29,33);
		bitset.set(34,37);
		bitset.set(38);
		bitset.set(40);
		bitset.set(43,47);
		bitset.set(48,59);
		
		var rawInput = RAWInput.of(bitset, 58);
		
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
