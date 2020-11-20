package de.fhdo.klausurapp.bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import de.fhdo.klausurapp.domain.Aufgabe;
import de.fhdo.klausurapp.domain.Klausur;
import de.fhdo.klausurapp.domain.KlausurEintrag;
import de.fhdo.klausurapp.domain.Semester;
import de.fhdo.klausurapp.domain.Student;
import de.fhdo.klausurapp.repositories.BewertungRepository;
import de.fhdo.klausurapp.repositories.KlausurEintragRepository;
import de.fhdo.klausurapp.repositories.KlausurRepository;
import de.fhdo.klausurapp.repositories.StudentRepository;

/**
 * Initialisiert die Datenbank mit Testdaten unter Benutzung der Repositories.
 * Erweitern Sie die Methode "initData", wenn Sie weitere Testdaten benoetigen.
 * 
 * Zu diesem Zweck registriert sich die Klasse als {@link ApplicationListener},
 * die auf das {@link ContextRefreshedEvent} lauscht.
 * 
 * Um zu ueberpruefen, ob die Datenbank erfolgreich initialisiert wurde, oeffnen
 * Sie nach Start der Anwendung die H2-Konsole im Web-Browser unter
 * {@link http://localhost:8080/h2-console/}.
 * 
 * Durchsuchen Sie zur Ermittlung der JDBC-URL die Ausgabe Ihrer Anwendung. Sie
 * finden die URL in einer Ausgabe der Form: "H2 console available at
 * '/h2-console'. Database available at '$JDBC_URL". Kopieren Sie den Wert $JDBC_URL.
 * 
 * Geben Sie folgende Daten in das Login-Formular ein: JDBC URL= $JDBC_URL, User
 * Name="sa", Password leer lassen. Nach erfolgreichem Login koennen Sie die
 * angelegten Tabellen und Daten inspizieren.
 */
@Component
public class DummyDataBootstrap implements ApplicationListener<ContextRefreshedEvent> {
	private KlausurEintragRepository klausurEintragRepository;
	private KlausurRepository klausurRepository;
	private StudentRepository studentRepository;

	@Autowired
	public DummyDataBootstrap(BewertungRepository bewertungRepository,
			KlausurEintragRepository klausurEintragRepository, KlausurRepository klausurRepository,
			StudentRepository studentRepository) {
		this.klausurEintragRepository = klausurEintragRepository;
		this.klausurRepository = klausurRepository;
		this.studentRepository = studentRepository;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
		initData();
	}

	private void initData() {
		Student student1 = new Student(7504832L, "Marina", "Manusova");
		Student student2 = new Student(7703343L, "Souad", "Ghanem");
		Student student3 = new Student(7504832L, "Jie", "Hsueh");
		studentRepository.save(student1);
		studentRepository.save(student2);
		studentRepository.save(student3);

		Klausur klausur = new Klausur("Web-Technologien", Semester.SOSE, 2018);
		Aufgabe aufgabe1 = new Aufgabe("Aufgabe 1", 15);
		Aufgabe aufgabe2 = new Aufgabe("Aufgabe 2", 25);
		Aufgabe aufgabe3 = new Aufgabe("Aufgabe 3", 30);
		Aufgabe aufgabe4 = new Aufgabe("Aufgabe 4", 10);
		Aufgabe aufgabe5 = new Aufgabe("Aufgabe 5", 20);
		klausur.getAufgaben().add(aufgabe1);
		klausur.getAufgaben().add(aufgabe2);
		klausur.getAufgaben().add(aufgabe3);
		klausur.getAufgaben().add(aufgabe4);
		klausur.getAufgaben().add(aufgabe5);
		klausurRepository.save(klausur);

		KlausurEintrag klausurEintrag1 = new KlausurEintrag(klausur, student1, 1);
		KlausurEintrag klausurEintrag2 = new KlausurEintrag(klausur, student2, 2);
		KlausurEintrag klausurEintrag3 = new KlausurEintrag(klausur, student3, 1);

		klausurEintragRepository.save(klausurEintrag1);
		klausurEintragRepository.save(klausurEintrag2);
		klausurEintragRepository.save(klausurEintrag3);
	}
}