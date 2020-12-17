package de.fhdo.klausurapp.controller;




import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import de.fhdo.klausurapp.dto.AufgabeDto;
import de.fhdo.klausurapp.dto.BewertungDto;
import de.fhdo.klausurapp.dto.KlausurDto;
import de.fhdo.klausurapp.dto.KlausurEintragDto;
import de.fhdo.klausurapp.dto.StudentDto;
import de.fhdo.klausurapp.services.AufgabeService;
import de.fhdo.klausurapp.services.BewertungService;
import de.fhdo.klausurapp.services.KlausurEintragService;
import de.fhdo.klausurapp.services.KlausurService;
import de.fhdo.klausurapp.services.StudentService;
import net.bytebuddy.utility.visitor.ExceptionTableSensitiveMethodVisitor;


@Controller
@RequestMapping("/klausurEintrag")
public class KlausureintragController {
	
	private KlausurEintragService klausurEintragService;
	private KlausurService klausurService;
	private StudentService studentService;
	private AufgabeService aufgabeService;
	private BewertungService bewertungService;
	
	@Autowired
	public KlausureintragController(KlausurEintragService klausurEintragService, KlausurService klausurService, StudentService studentService, AufgabeService aufgabeService,BewertungService bewertungService) {
		this.klausurEintragService = klausurEintragService;
		this.klausurService = klausurService; 
		this.studentService = studentService;
		this.aufgabeService = aufgabeService;
		this.bewertungService = bewertungService;
	}
	
	@GetMapping("/{idKlausurDetail}/listEintraegen")
	@ResponseStatus(HttpStatus.OK)
	public String showEintraegen(Model model, @PathVariable String idKlausurDetail) {
		KlausurDto klausurDto = klausurService.lesenKlausurID(Long.valueOf(idKlausurDetail));
		List<KlausurEintragDto> klausurEintraege = klausurEintragService.klausurEintragZuKlausurLesen(klausurDto);
		model.addAttribute("klausurEintraege", klausurEintraege);
		model.addAttribute("klausur", klausurDto);
		return "uebersichtStudierende";
	}
	
	@GetMapping("/{idEintrag}/listEintraegen/{idAufgabe}")
	public String showEintraegen(@PathVariable String idEintrag, @PathVariable String idAufgabe, @RequestParam String punkte) {
		KlausurEintragDto klausurEintragDto = klausurEintragService.lesenKlausurEintragID(Long.valueOf(idEintrag));
		AufgabeDto aufgabeDto = aufgabeService.lesenAufgabeID(Long.valueOf(idAufgabe));
		BewertungDto addedBewertung = bewertungService.addBewertung(new BewertungDto(aufgabeDto, Double.valueOf(punkte)));
		KlausurEintragDto addedklausurEintragDto = klausurEintragService.addOneBewertung(klausurEintragDto, addedBewertung);
		return "redirect:/klausurEintrag/" + klausurEintragDto.getKlausur().getId() + "/listEintraegen";
		// return "redirect:/klausur/listKlausuren";
	}
	
	
	
	@GetMapping("/{idEintrag}")
	@ResponseStatus(HttpStatus.OK)
	public String eingebenBewertung(Model model, @PathVariable String idEintrag) {
		KlausurEintragDto klausurEintragDto = klausurEintragService.lesenKlausurEintragID(Long.valueOf(idEintrag));
		KlausurDto klausurDto = klausurService.lesenKlausurID(Long.valueOf(klausurEintragDto.getKlausur().getId()));
		StudentDto studentDto = studentService.lesenStudentID(Long.valueOf(klausurEintragDto.getStudent().getId()));
		model.addAttribute("klausurEintrag", klausurEintragDto);
		model.addAttribute("klausur", klausurDto);
		model.addAttribute("student", studentDto);
		KlausurEintragDto temp = klausurEintragDto;
		model.addAttribute("listBewertung", temp);
		return "neueBewertung";
	}
	
	@PostMapping("/{idEintrag}")
	public String addBewertung(@ModelAttribute KlausurEintragDto listBewertung, @PathVariable String idEintrag) {
		KlausurEintragDto klausurEintragDto = klausurEintragService.lesenKlausurEintragID(Long.valueOf(idEintrag));
		klausurEintragService.addBewertung(listBewertung, listBewertung.getBewertungen());
		return "redirect:/klausur/listKlausuren";
	}
	
	
}
