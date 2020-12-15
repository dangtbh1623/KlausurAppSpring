package de.fhdo.klausurapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import de.fhdo.klausurapp.dto.AufgabeDto;
import de.fhdo.klausurapp.dto.BewertungDto;
import de.fhdo.klausurapp.dto.KlausurDto;
import de.fhdo.klausurapp.dto.KlausurEintragDto;
import de.fhdo.klausurapp.dto.StudentDto;
import de.fhdo.klausurapp.services.AufgabeService;
import de.fhdo.klausurapp.services.KlausurEintragService;
import de.fhdo.klausurapp.services.KlausurService;
import de.fhdo.klausurapp.services.StudentService;

//@RestController?
@Controller
@RequestMapping("/klausur")
public class KlausurController {

	KlausurService klausurService;
	AufgabeService aufgabeService;
	StudentService studentService;
	KlausurEintragService klausurEintragService;

	@Autowired
	public KlausurController(KlausurService klausurService, AufgabeService aufgabeService, StudentService studentService, KlausurEintragService klausurEintragService) {
		this.klausurService = klausurService;
		this.aufgabeService = aufgabeService;
		this.studentService = studentService;
		this.klausurEintragService = klausurEintragService;
	}

	//Fertig
	//Geb alle Klausuren in der Tabelle aus
	@GetMapping("/listKlausuren")
	@ResponseStatus(HttpStatus.OK)
	public String showKlausuren(Model model) {
		List<KlausurDto> showKlausuren = klausurService.lesenKlausuren();
		model.addAttribute("klausuren", showKlausuren);
		return "start";
	}

	// Fertig
	// Geb die Infos einer bestimmten Klausur aus.
	@GetMapping("/{idDetail}")
	@ResponseStatus(HttpStatus.OK)
	public String showKlausurDetail(@PathVariable String idDetail, Model model) {
		KlausurDto klausurDto = klausurService.lesenKlausurID(Long.valueOf(idDetail));
		List<AufgabeDto> aufgaben = klausurDto.getAufgaben();
		List<KlausurEintragDto> klausurEintraege =  klausurEintragService.klausurEintragZuKlausurLesen(klausurDto);
		model.addAttribute("klausur", klausurDto);
		model.addAttribute("aufgaben", aufgaben);
		model.addAttribute("klausurEintraege", klausurEintraege);
		model.addAttribute("neuAufgabe", new AufgabeDto());
		model.addAttribute("neuerStudi", new StudentDto());
		return "uebersichtPruefung";
	}
	
	// Fertig
	//add Aufgabe zu einer Klausur. Die Id einer Klausur wird über dynamische Path gegeben.
	@PostMapping("/addAufgabe/{klausurID}")
	public String addAufgabe(@ModelAttribute AufgabeDto neuAufgabe, @PathVariable String klausurID) {
		AufgabeDto addAuf = aufgabeService.addAufgabe(neuAufgabe);
		KlausurDto klausurDto = klausurService.lesenKlausurID(Long.valueOf(klausurID));
		KlausurDto addAufgabeZuklausur = klausurService.addAufgabe(klausurDto, addAuf);
		return "redirect:/klausur/" + addAufgabeZuklausur.getId();
	}
	
	//Fertig
	// Funktion zur Klausurerstellung. Zur Verbindung zwischen Entität und Thymeleaf
	@GetMapping("/createKlausur")
	@ResponseStatus(HttpStatus.OK)
	public String addKlausurForm(Model model) {
		model.addAttribute("neuKlausur", new KlausurDto());
		return "neueKlausur";
	}

	//Fertig
	// Funktion zur Klausurerstellung. Receive ein Objekt eines Typs KlausurDto von Frontend über PostMethode 
	@PostMapping("/createKlausur")
	public String addKlausur(@ModelAttribute KlausurDto neuKlausur) {
		KlausurDto addklausur = klausurService.addKlausur(neuKlausur);
		return "redirect:/klausur/" + addklausur.getId();
	}
	
	
	@PostMapping("/addStudi/{klausurID}")
	public String addStudi(@ModelAttribute StudentDto neuerStudi, @PathVariable String klausurID) {
		StudentDto addedStudi = this.studentService.addStudent(neuerStudi);
		KlausurDto klausurDto = klausurService.lesenKlausurID(Long.valueOf(klausurID));
		//Wie komme ich an den Versuch? Im Formular kann ich kein Versuch übergeben, wegen dem StudentenObjekt.
		//Wie macht man das mit dem hidden Field, dass beim letzten mal angesprochen wurde?
		KlausurEintragDto neuerKlausurEintragDto = new KlausurEintragDto(klausurDto, addedStudi,1);
		klausurEintragService.addNewKlausurEintrag(neuerKlausurEintragDto);
		return "redirect:/klausur/" + klausurDto.getId();
	}
	
	@GetMapping("/bewertung/{klausurID}")
	@ResponseStatus(HttpStatus.OK)
	public String showKlausurEintraege(Model model, @PathVariable String klausurID) {
		KlausurDto klausurDto = klausurService.lesenKlausurID(Long.valueOf(klausurID));
		List<KlausurEintragDto> klausurEintraege = klausurEintragService.klausurEintragZuKlausurLesen(klausurDto);
		model.addAttribute("klausur", klausurDto);
		model.addAttribute("klausurEintraege", klausurEintraege);
		model.addAttribute("neueBewertung", new BewertungDto());
		return "uebersichtStudierende";
	}
	
	@PostMapping("/bewertung/{klausurID}")
	public String addBewertung(@ModelAttribute BewertungDto bewertungDto, @PathVariable String klausurID) {
		KlausurDto klausurDto = klausurService.lesenKlausurID(Long.valueOf(klausurID));
		List<KlausurEintragDto> klausurEintraege = klausurEintragService.klausurEintragZuKlausurLesen(klausurDto);
		//Wie komme ich an den Studenten in der entsprechenden Zeile aus dem Formular?!
		return "redirect:/klausur/bewertung/" + klausurDto.getId();
	}

}
