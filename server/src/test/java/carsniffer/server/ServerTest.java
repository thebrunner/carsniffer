package carsniffer.server;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.BitSet;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ServerTest {

	@Mock
	InputConverter inputConverter;
	
	@Mock
	Storage storage;
	
	@Test
	void testReceive() throws Exception {
		var server = new Server(List.of(inputConverter), List.of(storage));
		
		var b = RAWInput.of(new BitSet(), 0);
		var o = new Object();
		var input = new Input(b, o);
		
		when(inputConverter.convert(b)).thenReturn(input);
		
		ArgumentCaptor<Input> inputArg = ArgumentCaptor.forClass(Input.class);
		
		server.receive(b);
	
		verify(storage).store(inputArg.capture());
		
		Input result = inputArg.getValue();
		
		assertEquals(input, result);
		assertEquals(b, result.raw());
		assertEquals(o, result.converted());
	}

}
