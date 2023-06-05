package carsniffer.can;

public record CANMessage(RAWCANMessage rawConverted, String identifier, String data, String crc, String ack) {

}
