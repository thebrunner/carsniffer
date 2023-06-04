package carsniffer.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import carsniffer.server.Server;

@RestController
public class RESTController {

	@Autowired
	private Server server;

	@PostMapping("/receive")
	public String receive() {
		server.receive(new byte[] { 10 });
		return "ok";
	}

}