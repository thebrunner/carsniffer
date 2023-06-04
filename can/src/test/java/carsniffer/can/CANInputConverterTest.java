package carsniffer.can;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.BitSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import carsniffer.server.RAWInput;

class CANInputConverterTest {

	private CANInputConverter canInputConverter = new CANInputConverter();
	
	@Test
	void testconvert2RAWCANMessage() throws Exception {
		var raw = new BitSet(11+2+16+2);
		raw.set(8); // == 4
		
		raw.set(0+11);
		raw.set(1+11); // == 3
		
		raw.set(14+2+11); // 2
		
		raw.set(1+16+2+11); // 1
		
		final var result = canInputConverter.convert2RAWCANMessage(new RAWInput(raw, 11+2+16+2));
		
		assertNotNull(result);
		assertEquals(11, result.identifier().length());
		assertEquals("{8}", result.identifier().raw().toString());
		assertEquals(2, result.data().length());
		assertEquals("{0, 1}", result.data().raw().toString());
		assertEquals(16, result.crc().length());
		assertEquals("{14}", result.crc().raw().toString());
		assertEquals(2, result.ack().length());
		assertEquals("{1}", result.ack().raw().toString());
	}
}
