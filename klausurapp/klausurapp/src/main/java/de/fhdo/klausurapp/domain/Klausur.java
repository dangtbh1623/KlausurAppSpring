package de.fhdo.klausurapp.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

/**
 * Datenbankentitaet fuer Klausuren. Zu einer Klausur gehoert eine Menge von Aufgaben.
 * 
 * Nutzt Annotationen der Java Persistence API (JPA), um zu beschreiben, wie ein
 * entsprechendes Objekt auf eine relationale Datenstruktur abgebildet werden
 * soll (objektrelationales Mapping, ORM).
 * 
 * @see de.fhdo.klausurapp.domain.Aufgabe
 */
@Entity
public class Klausur {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String veranstaltung;
	@Enumerated(EnumType.STRING)
	private Semester semester;
	private Integer jahr;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "klausur_id")
	@OrderBy("name ASC")
	private Set<Aufgabe> aufgaben = new HashSet<>();

	public Klausur() {
	}

	public Klausur(String veranstaltung, Semester semester, Integer jahr) {
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

	public Set<Aufgabe> getAufgaben() {
		return aufgaben;
	}

	public void setAufgaben(Set<Aufgabe> aufgaben) {
		this.aufgaben = aufgaben;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((semester == null) ? 0 : semester.hashCode());
		result = prime * result + ((veranstaltung == null) ? 0 : veranstaltung.hashCode());
		result = prime * result + ((jahr == null) ? 0 : jahr.hashCode());
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
		Klausur other = (Klausur) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (semester != other.semester)
			return false;
		if (veranstaltung == null) {
			if (other.veranstaltung != null)
				return false;
		} else if (!veranstaltung.equals(other.veranstaltung))
			return false;
		if (jahr == null) {
			if (other.jahr != null)
				return false;
		} else if (!jahr.equals(other.jahr))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Klausur [id=" + id + ", veranstaltung=" + veranstaltung + ", semester=" + semester + ", jahr=" + jahr
				+ "]";
	}
}
