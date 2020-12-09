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
import de.fhdo.klausurapp.dto.KlausurDto;
import de.fhdo.klausurapp.services.AufgabeService;
import de.fhdo.klausurapp.services.KlausurService;

//@RestController?
@Controller
@RequestMapping("/klausur")
public class KlausurController {

	KlausurService klausurService;
	AufgabeService aufgabeService;

	@Autowired
	public KlausurController(KlausurService klausurService, AufgabeService aufgabeService) {
		this.klausurService = klausurService;
		this.aufgabeService = aufgabeService;
	}

	//Fertig
	@GetMapping("/listKlausuren")
	@ResponseStatus(HttpStatus.OK)
	public String showKlausuren(Model model) {
		List<KlausurDto> showKlausuren = klausurService.lesenKlausuren();
		model.addAttribute("klausuren", showKlausuren);
		return "start";
	}

	// Fertig
	@GetMapping("/{idDetail}")
	@ResponseStatus(HttpStatus.OK)
	public String showKlausurDetail(@PathVariable String idDetail, Model model) {
		KlausurDto klausurDto = klausurService.lesenKlausurID(Long.valueOf(idDetail));
		List<AufgabeDto> aufgaben = klausurDto.getAufgaben();
		model.addAttribute("klausur", klausurDto);
		model.addAttribute("aufgaben", aufgaben);
		return "klausurDetail";
	}
	
	//Nur für Testzweck
	@GetMapping("/test")
	@ResponseBody
	public String showKlausurDetail() {
		return "Muss noch implementieren !!!";
	}

//	@GetMapping("/{id:[\\d]+}")
//	@ResponseStatus(HttpStatus.OK)
//	public KlausurDto showKlausur(@PathVariable Long id) {
//		return klausurService.lesenKlausurID(id);
//	}

//	@PostMapping("/createKlausur")
//	@ResponseStatus(HttpStatus.OK)
//	public KlausurDto addKlausur(@RequestBody() KlausurDto klausurDto) {
//		return klausurService.addKlausur(klausurDto);
//	}
	
	//Fertig
	@GetMapping("/createKlausur")
	@ResponseStatus(HttpStatus.OK)
	public String addKlausurForm(Model model) {
		model.addAttribute("neuKlausur", new KlausurDto());
		return "neueKlausur";
	}

	//Fertig
	@PostMapping("/createKlausur")
	public String addKlausur(@ModelAttribute KlausurDto neuKlausur) {
		KlausurDto addklausur = klausurService.addKlausur(neuKlausur);
		return "redirect:/klausur/listKlausuren";
	}

	//ToDo :D
	@PostMapping("/addAufgabe")
	@ResponseStatus(HttpStatus.OK)
	public KlausurDto addAufgabe(@RequestBody() KlausurDto klausurDto, @RequestBody() AufgabeDto aufgabeDtoParam) {
		AufgabeDto aufgabeDto = aufgabeService.addAufgabe(aufgabeDtoParam);
		return klausurService.addAufgabe(klausurDto, aufgabeDto);
	}

}
