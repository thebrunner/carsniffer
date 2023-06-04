package carsniffer.can;

import carsniffer.server.RAWInput;

public record RAWCANMessage(RAWInput identifier, RAWInput data, RAWInput crc, RAWInput ack) {

}
