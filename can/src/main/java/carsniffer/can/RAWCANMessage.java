package carsniffer.can;

public record RAWCANMessage(boolean[] identifier, boolean[] data, boolean[] crc, boolean[] ack) {

}
