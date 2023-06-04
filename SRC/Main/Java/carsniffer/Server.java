package carsniffer;

@Service
public class Server {
  
  @Autowire
  private InputConverter inputConverter;
  
  public void receive(byte[] arrayInput) {
    final var input = inputConverter.convert(arrayInput);
  }
  
}
