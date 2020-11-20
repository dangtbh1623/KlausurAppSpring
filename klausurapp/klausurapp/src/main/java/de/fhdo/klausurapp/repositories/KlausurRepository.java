package de.fhdo.klausurapp.repositories;

import org.springframework.data.repository.CrudRepository;

import de.fhdo.klausurapp.domain.Klausur;

/**
 * Repository fuer Klausuren.
 * 
 * Hinweis: Wir verwenden hier nicht die in der Vorlesung erwaehnte
 * Stereotyp-Annotation "@Repository", da wir Spring Data JPA einsetzen, welches
 * Repositories auf eigene Weise verwaltet. Hier muss nichts weiter
 * implementiert werden: Die konkrete Repository-Bean wird von Spring Data JPA
 * automatisch zur Laufzeit zur Verfuegung gestellt!
 * 
 * @see de.fhdo.klausurapp.domain.Klausur
 * @see de.fhdo.klausurapp.bootstrap.DummyDataBootstrap
 */
public interface KlausurRepository extends CrudRepository<Klausur, Long> {

}
