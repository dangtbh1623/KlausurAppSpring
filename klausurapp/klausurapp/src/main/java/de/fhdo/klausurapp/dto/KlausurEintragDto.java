package de.fhdo.klausurapp.dto;

import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Data Transfer Object (DTO) fuer Klausureintraege. Oberhalb der Fachlogikschicht soll
 * zur besseren Entkopplung nur mit DTOs gearbeitet werden und *nicht* mit den
 * Datenbankentitaeten.
 * 
 * @see de.fhdo.klausurapp.domain.KlausurEintrag
 */
public class KlausurEintragDto {
	private Long id;
	@NotNull
	private KlausurDto klausur;
	@NotNull
	private StudentDto student;
	
	@Min(value= 1)
	@Max(value= 3)
	private Integer versuch;
	private List<BewertungDto> bewertungen;

	public KlausurEintragDto() {
	}

	public KlausurEintragDto(KlausurDto klausur, StudentDto student, Integer versuch) {
		this.klausur = klausur;
		this.student = student;
		this.versuch = versuch;
	}

	public KlausurDto getKlausur() {
		return klausur;
	}

	public void setKlausur(KlausurDto klausur) {
		this.klausur = klausur;
	}

	public Integer getVersuch() {
		return versuch;
	}

	public void setVersuch(Integer versuch) {
		this.versuch = versuch;
	}

	public List<BewertungDto> getBewertungen() {
		return bewertungen;
	}

	public void setBewertungen(List<BewertungDto> bewertungen) {
		this.bewertungen = bewertungen;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public StudentDto getStudent() {
		return student;
	}

	public void setStudent(StudentDto student) {
		this.student = student;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bewertungen == null) ? 0 : bewertungen.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((klausur == null) ? 0 : klausur.hashCode());
		result = prime * result + ((student == null) ? 0 : student.hashCode());
		result = prime * result + ((versuch == null) ? 0 : versuch.hashCode());
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
		KlausurEintragDto other = (KlausurEintragDto) obj;
		if (bewertungen == null) {
			if (other.bewertungen != null)
				return false;
		} else if (!bewertungen.equals(other.bewertungen))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (klausur == null) {
			if (other.klausur != null)
				return false;
		} else if (!klausur.equals(other.klausur))
			return false;
		if (student == null) {
			if (other.student != null)
				return false;
		} else if (!student.equals(other.student))
			return false;
		if (versuch == null) {
			if (other.versuch != null)
				return false;
		} else if (!versuch.equals(other.versuch))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "KlausurEintragDto [id=" + id + ", klausur=" + klausur + ", student=" + student + ", versuch=" + versuch
				+ ", bewertungen=" + bewertungen + "]";
	}
}
