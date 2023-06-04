package carsniffer;

@Service
public class Server {
  
  @Autowire
  private InputConverter inputConverter;
  
  @Autowire
  private Storage storage;
  
  public void receive(byte[] arrayInput) {
    final var input = inputConverter.convert(arrayInput);
    
    storage.store(input);
  }
  
}
