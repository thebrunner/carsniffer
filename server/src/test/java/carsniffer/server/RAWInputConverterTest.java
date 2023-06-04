package carsniffer.server;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Boolean2RAWInputConverterTest {

	private final RAWInputConverter inputConverter = new RAWInputConverter();
	
	@Test
	void testConvert() {
		var result = inputConverter.convert(new byte[]{2, 34});
		
		Assertions.assertTrue(result.raw().get(0));
		Assertions.assertFalse(result.raw().get(1));
		Assertions.assertEquals(2, result.length());
	}

}
