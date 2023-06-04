package carsniffer.server;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ServerTest {

	@Mock
	InputConverter inputConverter;
	
	@Mock
	Storage storage;
	
	@InjectMocks
	Server server;
	
	@Test
	void testReceive() {
		byte[] b = new byte[] {1};
		
		var input = new Input();
		when(inputConverter.convert(b)).thenReturn(input);
		
		server.receive(b);
	
		verify(storage).store(input);
	}

}
