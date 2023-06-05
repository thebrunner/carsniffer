package carsniffer.server;

import java.time.LocalDateTime;
import java.util.BitSet;

public record RAWInput(BitSet raw, int length, LocalDateTime arrival) {
  
  public static RAWInput of(BitSet raw, int length) {
    return new RAWInput(raw, length, LocalDateTime.now());
  }
    
}
