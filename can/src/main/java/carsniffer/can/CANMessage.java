package carsniffer.can;

public record CANMessage(RAWCANMessage rawConverted, String identifier, String extendedIdentifier, String data, String crc, boolean ack) {

}
