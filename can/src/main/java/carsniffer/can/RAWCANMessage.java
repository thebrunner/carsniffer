package carsniffer.can;

import java.time.LocalDateTime;
import java.util.BitSet;

public record RAWCANMessage(BitSet identifier, BitSet extendedIdentifier, BitSet data, BitSet crc, BitSet ack, LocalDateTime arrival) {
  public static RAWCANMessage ofBaseFrame(BitSet identifier, BitSet data, BitSet crc, BitSet ack, LocalDateTime arrival) {
    return new RAWCANMessage(identifier, null, data, crc, ack, arrival);
  }
}
