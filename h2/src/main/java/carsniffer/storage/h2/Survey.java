package carsniffer.storage.h2;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "survey")
public class Survey implements Serializable {

	private Long pk;

	private String _name;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pk", unique = true, nullable = false)
	public Long getPk() {
		return this.pk;
	}

	public void setPk(Long pk) {
		this.pk = pk;
	}
	
	@Column(name = "name")
	public String getName() {
		return this._name;
	}
	
	public void setName(final String name) {
		this._name = name;
	}

}
