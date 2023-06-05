package carsniffer.can;

import carsniffer.server.RAWInput;

public record RAWCANMessage(RAWInput identifier, RAWInput extendedIdentifier, RAWInput data, RAWInput crc, RAWInput ack) {
  public static RAWCANMessage ofBaseFrame(RAWInput identifier, RAWInput data, RAWInput crc, RAWInput ack) {
    return new RAWCANMessage(identifier, new BitSet(), data, crc, ack);
  }
}
