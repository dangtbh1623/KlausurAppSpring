package de.fhdo.klausurapp.repositories;

import org.springframework.data.repository.CrudRepository;

import de.fhdo.klausurapp.domain.Aufgabe;

/**
 * Repository fuer Aufgaben.
 * 
 * Hinweis: Wir verwenden hier nicht die in der Vorlesung erwaehnte
 * Stereotyp-Annotation "@Repository", da wir Spring Data JPA einsetzen, welches
 * Repositories auf eigene Weise verwaltet. Hier muss nichts weiter
 * implementiert werden: Die konkrete Repository-Bean wird von Spring Data JPA
 * automatisch zur Laufzeit zur Verfuegung gestellt!
 * 
 * @see de.fhdo.klausurapp.domain.Aufgabe
 * @see de.fhdo.klausurapp.bootstrap.DummyDataBootstrap
 */
public interface AufgabeRepository extends CrudRepository<Aufgabe, Long> {

}
