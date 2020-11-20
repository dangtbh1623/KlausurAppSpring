package de.fhdo.klausurapp.dto;

/**
 * Data Transfer Object (DTO) fuer Bewertungen. Oberhalb der Fachlogikschicht
 * soll zur besseren Entkopplung nur mit DTOs gearbeitet werden und *nicht* mit
 * den Datenbankentitaeten.
 * 
 * @see de.fhdo.klausurapp.domain.Bewertung
 */
public class BewertungDto {
	private Long id;
	private AufgabeDto aufgabe;
	private Double punkte;

	public BewertungDto() {
	}

	public BewertungDto(AufgabeDto aufgabe, Double punkte) {
		this.aufgabe = aufgabe;
		this.punkte = punkte;
	}

	public AufgabeDto getAufgabe() {
		return aufgabe;
	}

	public void setAufgabe(AufgabeDto aufgabe) {
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
		BewertungDto other = (BewertungDto) obj;
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
		return "BewertungDto [id=" + id + ", aufgabe=" + aufgabe + ", punkte=" + punkte + "]";
	}
}
