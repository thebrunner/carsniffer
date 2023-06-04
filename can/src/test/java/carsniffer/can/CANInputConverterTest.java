package carsniffer.can;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

class CANInputConverterTest {

	private CANInputConverter canInputConverter = new CANInputConverter();

	@Test
	void testconvert2RAWCANMessage() throws Exception {
		var input = new boolean[] { // Identifier 11
				false, false, false, false, false, //
				false, false, false, true, false, false, // == 4
				// Data (2)
				true, true, // == 3
				// CRC 16
				false, false, false, false, false, //
				false, false, false, false, false, //
				false, false, false, false, true, false, // == 2
				// Ack 2
				false, true // == 1
		};

		final var result = canInputConverter.convert2RAWCANMessage(input);

		assertNotNull(result);
		assertEquals(11, result.identifier().length);
		for (int i = 0; i < 8; i++) {
			assertFalse(result.identifier()[i]);
		}
		assertTrue(result.identifier()[8]);
		assertFalse(result.identifier()[9]);
		assertFalse(result.identifier()[10]);
		
		assertEquals(2, result.data().length);
		assertTrue(result.data()[0]);
		assertTrue(result.data()[1]);
		

		assertEquals(16, result.crc().length);
		for (int i = 0; i < 14; i++) {
			assertFalse(result.crc()[i]);
		}
		assertTrue(result.crc()[14]);
		assertFalse(result.crc()[15]);
		
		assertEquals(2, result.ack().length);
		assertFalse(result.ack()[0]);
		assertTrue(result.ack()[1]);
	}
}
