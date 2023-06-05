package carsniffer.storage.h2;

import java.io.Serializable;

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
	
	private String converted;

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

	@Column(name = "converted", length = 8000)
	public String getConverted() {
		return converted;
	}

	public void setConverted(String converted) {
		this.converted = converted;
	}
	
}
