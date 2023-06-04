package carsniffer;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class RestController {

	@Autowired
	private Server server;

	@PostMapping("/tutorials")
	public ResponseEntity<Void> getAllTutorials(@RequestParam byte[] input) {
		try {
	
		return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
  
}
