package de.fhdo.klausurapp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import de.fhdo.klausurapp.domain.Semester;
import de.fhdo.klausurapp.dto.AufgabeDto;
import de.fhdo.klausurapp.dto.KlausurDto;
import de.fhdo.klausurapp.dto.KlausurEintragDto;
import de.fhdo.klausurapp.dto.StudentDto;
import de.fhdo.klausurapp.services.KlausurEintragService;
import de.fhdo.klausurapp.services.KlausurService;
import de.fhdo.klausurapp.services.StudentService;

@SpringBootApplication
public class KlausurappApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(KlausurappApplication.class, args);
        KlausurService klausurService = context.getBean(KlausurService.class);
        KlausurEintragService klausurEintragService = context.getBean(KlausurEintragService.class);
        StudentService studentService = context.getBean(StudentService.class);
        
        /* Ausgabe alle Klausuren */
        System.out.println(klausurService.lesenKlausuren());
        
        /* Ausgabe Klausur nach ID */
        System.out.println(klausurService.lesenKlausurID(4));
        
        /* Klausur erstellen */
        KlausurDto klausur = new KlausurDto();
        klausur.setSemester(Semester.SOSE);
        klausur.setVeranstaltung("Test Veranstaltung");
        klausur.setJahr(2020);
        
        /* Liste von Aufgaben erstellen */
        List<AufgabeDto> aufgaben = new ArrayList<AufgabeDto>();
        
        /* Aufgaben für die Liste erstellen */
        AufgabeDto aufgabe1 = new AufgabeDto();
        aufgabe1.setName("Aufgabe 1");
        aufgabe1.setMaxPunkte(500);
        
        AufgabeDto aufgabe2 = new AufgabeDto();
        aufgabe2.setName("Aufgabe 2");
        aufgabe2.setMaxPunkte(250);
        
        /* Aufgaben der Liste hinzufügen */
        aufgaben.add(aufgabe1);
        aufgaben.add(aufgabe2);
        
        /* Aufgaben der Klausur hinzufügen */
        klausur.setAufgaben(aufgaben);
        
        /* Ausgabe neue Klausur erstellen */
        System.out.println(klausurService.addKlausur(klausur));
        
        /* Neue Aufgabe für die Klausur erstellen */
        AufgabeDto aufgabe3 = new AufgabeDto("Aufgabe 3", 51);
        
        /* Ausgabe Klausur eine Aufgabe hinzufügen */
        System.out.println(klausurService.addAufgabe(klausur, aufgabe3));
        
        /* Ausgabe Klausureintrag zu Klausur mit id 4 */
        System.out.println(klausurEintragService.klausurEintragZuKlausurLesen(klausurService.lesenKlausurID(4)));
        
        /* Student zum Hinzufügen erzeugen */
        StudentDto student = new StudentDto(1234667L, "Hallo", "Test");
        
        /* Ausgabe Student erzeugen */
        System.out.println(studentService.addStudent(student));
        
        /* Neuen Klausureintrag erstellen */
        KlausurEintragDto klausurEintrag = new KlausurEintragDto(klausur, student, 2);
        
        /* Ausgabe neuen Klauseintag anlegen */
        System.out.println(klausurEintragService.addNewKlausurEintrag(klausurEintrag));
	}

}
