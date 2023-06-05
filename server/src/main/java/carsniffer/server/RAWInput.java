package carsniffer.server;

import java.util.BitSet;

public record RAWInput(BitSet raw, int length, LocalDateTime arrival) {
  
  public RAWInput of(BitSet raw, int length) {
    return new RAWInput(raw, length, LocalDateTime.now());
  }
    
}
