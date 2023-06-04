package carsniffer;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/")
public class RestController {

	@Autowired
	private Server server;

	@PostMapping("/receive")
	public ResponseEntity<Void> receive(@RequestParam byte[] input) {
		try {
	
		server.receive(input);return
			new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
  
}
