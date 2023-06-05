package carsniffer.storage.h2;

import java.io.Serializable;
import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "input")
public class JpaInput implements Serializable {

	private Long pk;

	private String raw;
	private String identifier;
	private String extendedIdentifier;
	private String data;
	private String crc;
	private String ack;
	private Timestamp arrival;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pk", unique = true, nullable = false)
	public Long getPk() {
		return this.pk;
	}

	public void setPk(Long pk) {
		this.pk = pk;
	}
	
	@Column(name = "raw", length = 8000)
	public String getRaw() {
		return this.raw;
	}
	
	public void setRaw(final String raw) {
		this.raw = raw;
	}
	
	@Column(name = "identifier")
	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	
	@Column(name = "extended_identifier")
	public String getExtendedIdentifier() {
		return extendedIdentifier;
	}
	
	public void setExtendedIdentifier(String extendedIdentifier) {
		this.extendedIdentifier = extendedIdentifier;
	}
	
	@Column(name = "data", length = 8000)
	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
	
	@Column(name = "crc")
	public String getCrc() {
		return crc;
	}

	public void setCrc(String crc) {
		this.crc = crc;
	}
	
	@Column(name = "ack")
	public String getAck() {
		return ack;
	}

	public void setAck(String ack) {
		this.ack = ack;
	}
	
	
	@Column(name = "arrival")
	public Timestamp getArrival() {
		return arrival;
	}

	public void setArrival(Timestamp arrival) {
		this.arrival = arrival;
	}
}
