package de.fhdo.klausurapp.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import de.fhdo.klausurapp.domain.Semester;

/**
 * Data Transfer Object (DTO) fuer Klausuren. Oberhalb der Fachlogikschicht soll
 * zur besseren Entkopplung nur mit DTOs gearbeitet werden und *nicht* mit den
 * Datenbankentitaeten.
 * 
 * @see de.fhdo.klausurapp.domain.Klausur
 */
public class KlausurDto {
	private Long id;
	@NotEmpty
	private String veranstaltung;
	@NotNull
	private Semester semester;
	@Min(value = 1000)
	@Max(value = 9999)
	private Integer jahr;
	private List<AufgabeDto> aufgaben = new ArrayList<>();

	public KlausurDto() {
	}

	public KlausurDto(String veranstaltung, Semester semester, Integer jahr) {
		this.veranstaltung = veranstaltung;
		this.semester = semester;
		this.jahr = jahr;
	}

	public String getVeranstaltung() {
		return veranstaltung;
	}

	public void setVeranstaltung(String veranstaltung) {
		this.veranstaltung = veranstaltung;
	}

	public Semester getSemester() {
		return semester;
	}

	public void setSemester(Semester semester) {
		this.semester = semester;
	}

	public Integer getJahr() {
		return jahr;
	}

	public void setJahr(Integer jahr) {
		this.jahr = jahr;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public List<AufgabeDto> getAufgaben() {
		return aufgaben;
	}

	public void setAufgaben(List<AufgabeDto> aufgaben) {
		this.aufgaben = aufgaben;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((aufgaben == null) ? 0 : aufgaben.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((jahr == null) ? 0 : jahr.hashCode());
		result = prime * result + ((semester == null) ? 0 : semester.hashCode());
		result = prime * result + ((veranstaltung == null) ? 0 : veranstaltung.hashCode());
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
		KlausurDto other = (KlausurDto) obj;
		if (aufgaben == null) {
			if (other.aufgaben != null)
				return false;
		} else if (!aufgaben.equals(other.aufgaben))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (jahr == null) {
			if (other.jahr != null)
				return false;
		} else if (!jahr.equals(other.jahr))
			return false;
		if (semester != other.semester)
			return false;
		if (veranstaltung == null) {
			if (other.veranstaltung != null)
				return false;
		} else if (!veranstaltung.equals(other.veranstaltung))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "KlausurDto [id=" + id + ", veranstaltung=" + veranstaltung + ", semester=" + semester + ", jahr=" + jahr
				+ ", aufgaben=" + aufgaben + "]";
	}
}
