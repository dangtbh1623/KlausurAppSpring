package de.fhdo.klausurapp.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import de.fhdo.klausurapp.domain.KlausurEintrag;

/**
 * Repository fuer Klausureintraege.
 * 
 * Hinweis: Wir verwenden hier nicht die in der Vorlesung erwaehnte
 * Stereotyp-Annotation "@Repository", da wir Spring Data JPA einsetzen, welches
 * Repositories auf eigene Weise verwaltet. Hier muss nichts weiter
 * implementiert werden: Die konkrete Repository-Bean wird von Spring Data JPA
 * automatisch zur Laufzeit zur Verfuegung gestellt!
 * 
 * @see de.fhdo.klausurapp.domain.KlausurEintrag
 * @see de.fhdo.klausurapp.bootstrap.DummyDataBootstrap
 */
public interface KlausurEintragRepository extends CrudRepository<KlausurEintrag, Long> {
	/**
	 * Liefert alle Klausureintraege zu der Klausur mit der gegebenen ID.
	 * 
	 * Muss nicht implementiert werden - die Implementierung wird von Spring Data
	 * JPA automatisch zur Laufzeit zur Verfuegung gestellt!
	 * 
	 * @param id
	 *            ID der Klausur, deren Eintraege ermittelt werden soll
	 * @return Liste von {@link KlausurEintrag}-Objekten
	 */
	List<KlausurEintrag> findByKlausur_Id(Long id);
}
