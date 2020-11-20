package de.fhdo.klausurapp.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 * Datenbankentitaet fuer Klausureintraege. Ein Klausureintrag ist das
 * Bindeglied zwischen einer Klausur, einem Studenten/einer Studentin sowie
 * einer Menge von zugehoerigen Bewertungen.
 * 
 * Nutzt Annotationen der Java Persistence API (JPA), um zu beschreiben, wie ein
 * entsprechendes Objekt auf eine relationale Datenstruktur abgebildet werden
 * soll (objektrelationales Mapping, ORM).
 * 
 * @see de.fhdo.klausurapp.domain.Klausur
 * @see de.fhdo.klausurapp.domain.Student
 * @see de.fhdo.klausurapp.domain.Bewertung 
 */
@Entity
public class KlausurEintrag {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "klausur_id")
	private Klausur klausur;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "student_id")
	private Student student;
	private Integer versuch;
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "klausur_eintrag_id")
	private Set<Bewertung> bewertungen;

	public KlausurEintrag() {
	}

	public KlausurEintrag(Klausur klausur, Student student, Integer versuch) {
		this.klausur = klausur;
		this.student = student;
		this.versuch = versuch;
	}

	public Klausur getKlausur() {
		return klausur;
	}

	public void setKlausur(Klausur klausur) {
		this.klausur = klausur;
	}

	public Integer getVersuch() {
		return versuch;
	}

	public void setVersuch(Integer versuch) {
		this.versuch = versuch;
	}

	public Set<Bewertung> getBewertungen() {
		return bewertungen;
	}

	public void setBewertungen(Set<Bewertung> bewertungen) {
		this.bewertungen = bewertungen;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bewertungen == null) ? 0 : bewertungen.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((klausur == null) ? 0 : klausur.hashCode());
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
		KlausurEintrag other = (KlausurEintrag) obj;
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
		if (versuch == null) {
			if (other.versuch != null)
				return false;
		} else if (!versuch.equals(other.versuch))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "KlausurEintrag [id=" + id + ", klausur=" + klausur + ", versuch=" + versuch + ", bewertungen="
				+ bewertungen + "]";
	}
}
