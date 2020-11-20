package de.fhdo.klausurapp.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 * Datenbankentitaet fuer Bewertungen. Eine Bewertung ist stets einer Aufgabe zugeordnet.
 * 
 * Nutzt Annotationen der Java Persistence API (JPA), um zu beschreiben, wie ein
 * entsprechendes Objekt auf eine relationale Datenstruktur abgebildet werden
 * soll (objektrelationales Mapping, ORM).
 * 
 * @see de.fhdo.klausurapp.domain.Aufgabe
 */
@Entity
public class Bewertung {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@OneToOne
	private Aufgabe aufgabe;
	private Double punkte;

	public Bewertung() {
	}

	public Bewertung(Aufgabe aufgabe, Double punkte) {
		this.aufgabe = aufgabe;
		this.punkte = punkte;
	}

	public Aufgabe getAufgabe() {
		return aufgabe;
	}

	public void setAufgabe(Aufgabe aufgabe) {
		this.aufgabe = aufgabe;
	}

	public Double getPunkte() {
		return punkte;
	}

	public void setPunkte(Double punkte) {
		this.punkte = punkte;
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
		result = prime * result + ((aufgabe == null) ? 0 : aufgabe.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((punkte == null) ? 0 : punkte.hashCode());
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
		Bewertung other = (Bewertung) obj;
		if (aufgabe == null) {
			if (other.aufgabe != null)
				return false;
		} else if (!aufgabe.equals(other.aufgabe))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (punkte == null) {
			if (other.punkte != null)
				return false;
		} else if (!punkte.equals(other.punkte))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Bewertung [id=" + id + ", aufgabe=" + aufgabe + ", punkte=" + punkte + "]";
	}
}
