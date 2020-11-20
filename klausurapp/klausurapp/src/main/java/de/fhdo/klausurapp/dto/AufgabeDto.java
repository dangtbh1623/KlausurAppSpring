package de.fhdo.klausurapp.dto;

/**
 * Data Transfer Object (DTO) fuer Aufgaben. Oberhalb der Fachlogikschicht soll
 * zur besseren Entkopplung nur mit DTOs gearbeitet werden und *nicht* mit den
 * Datenbankentitaeten.
 * 
 * @see de.fhdo.klausurapp.domain.Aufgabe
 */
public class AufgabeDto {
	private Long id;
	private String name;
	private Integer maxPunkte;

	public AufgabeDto() {
	}

	public AufgabeDto(String name, Integer maxPunkte) {
		this.name = name;
		this.maxPunkte = maxPunkte;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getMaxPunkte() {
		return maxPunkte;
	}

	public void setMaxPunkte(Integer maxPunkte) {
		this.maxPunkte = maxPunkte;
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
		result = prime * result + ((maxPunkte == null) ? 0 : maxPunkte.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		AufgabeDto other = (AufgabeDto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (maxPunkte == null) {
			if (other.maxPunkte != null)
				return false;
		} else if (!maxPunkte.equals(other.maxPunkte))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AufgabeDto [id=" + id + ", name=" + name + ", maxPunkte=" + maxPunkte + "]";
	}
}
