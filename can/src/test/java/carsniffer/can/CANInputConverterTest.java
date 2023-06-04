package carsniffer.can;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.BitSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class CANInputConverterTest {

	private CANInputConverter canInputConverter = new CANInputConverter();
	
	@Test
	void testconvert2RAWCANMessage() throws Exception {
		var identifier = new BitSet(11);
		identifier.set(8); // == 4
		
		var data = new BitSet(2);
		data.set(0);
		data.set(1); // == 3
		
		var crc = new BitSet(16);
		crc.set(14); // 2
		
		var ack = new BitSet(2);
		ack.set(1); // 1
		
		
		
		final var result = canInputConverter.convert2RAWCANMessage(input);
		
		assertNotNull(result);
		assertEquals(1, result.ack());
	}
}
