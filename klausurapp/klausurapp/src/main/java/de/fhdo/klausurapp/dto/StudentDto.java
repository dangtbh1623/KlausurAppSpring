package de.fhdo.klausurapp.dto;

import javax.persistence.DiscriminatorColumn;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

/**
 * Data Transfer Object (DTO) fuer Studierende. Oberhalb der Fachlogikschicht soll
 * zur besseren Entkopplung nur mit DTOs gearbeitet werden und *nicht* mit den
 * Datenbankentitaeten.
 * 
 * @see de.fhdo.klausurapp.domain.Student
 */
public class StudentDto {
	private Long id;
	
	@Min(value= 1000000)
	@Max(value= 9999999)
	private Long matrikelNr;
	@NotEmpty
	private String vorname;
	@NotEmpty
	private String nachname;
	
	public StudentDto() {}

	public StudentDto(Long matrikelNr, String vorname, String nachname) {
		this.matrikelNr = matrikelNr;
		this.vorname = vorname;
		this.nachname = nachname;
	}

	public Long getMatrikelNr() {
		return matrikelNr;
	}

	public void setMatrikelNr(Long matrikelNr) {
		this.matrikelNr = matrikelNr;
	}

	public String getVorname() {
		return vorname;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	public String getNachname() {
		return nachname;
	}

	public void setNachname(String nachname) {
		this.nachname = nachname;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((matrikelNr == null) ? 0 : matrikelNr.hashCode());
		result = prime * result + ((nachname == null) ? 0 : nachname.hashCode());
		result = prime * result + ((vorname == null) ? 0 : vorname.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StudentDto other = (StudentDto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (matrikelNr == null) {
			if (other.matrikelNr != null)
				return false;
		} else if (!matrikelNr.equals(other.matrikelNr))
			return false;
		if (nachname == null) {
			if (other.nachname != null)
				return false;
		} else if (!nachname.equals(other.nachname))
			return false;
		if (vorname == null) {
			if (other.vorname != null)
				return false;
		} else if (!vorname.equals(other.vorname))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "StudentDto [id=" + id + ", matrikelNr=" + matrikelNr + ", vorname=" + vorname + ", nachname=" + nachname
				+ "]";
	}
}
