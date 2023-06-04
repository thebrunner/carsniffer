package carsniffer.web;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import carsniffer.web.RestController;

@WebMvcTest(RestController.class)
class ServerIT {

	@Autowired
	private MockMvc mvc;

	@Test
	void test() throws Exception {
		mvc.perform(MockMvcRequestBuilders
	  			.post("/receive")
	  			.content("0101"))
		.andExpect(status().isOk());
	}

}
