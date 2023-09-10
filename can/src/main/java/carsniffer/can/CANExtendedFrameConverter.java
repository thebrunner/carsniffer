package carsniffer.can;

import carsniffer.server.CarSnifferException;
import carsniffer.server.RAWInput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CANExtendedFrameConverter extends CANBaseFrameConverter {

	private final static Logger LOGGER = LoggerFactory.getLogger(CANExtendedFrameConverter.class);

	
	/**
	 * Table 2: CAN 2.0B Message Frame
  	 * <table>
  <tr>
    <td>Field</td><td>Length (bits)</td><td>Description</td>
</tr>
  <tr>
<td>Start of Frame (SOF)</td><td>1</td><td>Must be dominant</td>
</tr>
  <tr>
<td>Identifier – Standard and Extended Formats</td><td>11</td><td>Unique identifier corresponds to Base ID in Extended Format</td>
</tr>
  <tr>
<td>Identifier – Extended Format</td><td>29</td><td>Comprised of 11 bit Base ID and 18 bit Extended ID</td>
</tr>
  <tr>
<td>Remote Transmission Request (RTR) – Standard and Extended Formats</td><td>1</td><td>Dominant in data frames; recessive in remote frames. In
Standard Format, the 11 bit identifier is followed by the
RTR bit.</td>
</tr>
  <tr>
<td>Substitute Remote Request (SRR) – Extended Format</td><td>1</td><td> Must be recessive. SRR is transmitted in Extended
Frames at the position of the RTR bit in Standard
Frames. In arbitration between standard and extended
frames, recessive SRR guarantees the standard message
frame prevails.</td>
</tr>
  <tr>
<td>IDE – Standard and Extended Frames</td> <td> 1</td> <td> Must be recessive for Extended Format; dominant for
Standard Format.</td>
</tr>
  <tr>
<td>Reserved r0 – Standard Format</td> <td> 1</td> <td> Must be dominant</td>
</tr>
  <tr>
<td>Reserved r1, r0 – Extended Format</td> <td> 2</td> <td> Must be recessive</td>
</tr>
  <tr>
<td>Data Length Code (DLC)</td> <td> 4</td> <td> Number of data bytes (0–8)</td>
</tr>
  <tr>
<td>Data Field</td> <td> 0–8 bytes</td> <td> Length determined by DLC field</td>
</tr>
  <tr>
<td>Cyclic Redundancy Check (CRC)</td> <td> 15</td> <td></td>
</tr>
  <tr>
<td>CRC Delimiter</td> <td> 1</td> <td> Must be recessive</td>
</tr>
  <tr>
<td>Acknowledge (ACK)</td><td>1</td><td>Transmitter sends recessive; receiver asserts dominant</td>
</tr>
  <tr>
<td>ACK Delimiter</td><td>1</td><td>Must be recessive</td>
</tr>
  <tr>
<td>End of Frame (EOF)</td><td>7</td><td>Must be recessive</td>
</tr>	
  */
	public RAWCANMessage convert(RAWInput rawInput) throws CarSnifferException {

		final var startControl = 34;
		final var endControl = 38;
		final var control = rawInput.raw().get(startControl, endControl);
		final int controlLengthInByte = calcControlLengthInByte(control);
		final int controlLengthInBit = controlLengthInByte + (controlLengthInByte * 8);

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("controlLengthInByte:" + controlLengthInByte);
		}
		
		final var minLength = endControl + controlLengthInBit + 16 + 2;
		if (rawInput.length() < minLength) {
			throw new CarSnifferException(rawInput, "Input to short: length " + rawInput.length()
						      + ", must be greater than " + minLength);
		}

		final var startIdentifier = 1;
		final var endIdentifier = 13; // stuff bit 5 (index 4)
		final var identifier = rawInput.raw().get(startIdentifier, endIdentifier);
		removeStuff(identifier, 4, 12);

		final var startExtendedIdentifier = endIdentifier + 2;
		final var endExtendedIdentifier = startExtendedIdentifier + 18;
		final var extendedIdentifier = rawInput.raw().get(startExtendedIdentifier, endExtendedIdentifier);

		final var startData = endControl;
		final var endData = startData + controlLengthInBit;
		final var data = rawInput.raw().get(startData, endData);
		for (int i = 0; i < controlLengthInByte; i++) {
			removeStuff(data, 5 + (i * 8), controlLengthInBit);
		}

		final var startCrc = endData;
		final var endCrc = startCrc + 16; // stuff bit end - 3
		final var crc = rawInput.raw().get(startCrc, endCrc);
		removeStuff(crc, 16 - 3, 16);

		final var startAck = endCrc + 1; // delimiter
		final var endAck = startAck + 1;
		final var ack = rawInput.raw().get(startAck, endAck);

		return new RAWCANMessage(identifier, extendedIdentifier, data, crc, ack, rawInput.arrival());
	}

}
