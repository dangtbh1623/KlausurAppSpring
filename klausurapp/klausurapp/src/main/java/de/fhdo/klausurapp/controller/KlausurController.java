package de.fhdo.klausurapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import de.fhdo.klausurapp.dto.AufgabeDto;
import de.fhdo.klausurapp.dto.KlausurDto;
import de.fhdo.klausurapp.services.AufgabeService;
import de.fhdo.klausurapp.services.KlausurService;

//@RestController?
@Controller
@RequestMapping("/klausureintrag")
public class KlausurController {
	
	KlausurService klausurService;
	AufgabeService aufgabeService;
	
	@Autowired
	public KlausurController(KlausurService klausurService, AufgabeService aufgabeService) {
		this.klausurService = klausurService;
		this.aufgabeService = aufgabeService;
	}
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<KlausurDto> showKlausuren() {
		return klausurService.lesenKlausuren();
	}
	
	@GetMapping("/{id:[\\d]+}")
	@ResponseStatus(HttpStatus.OK)
	public KlausurDto showKlausur(@PathVariable Long id) {
		return klausurService.lesenKlausurID(id);
	}
	
	@PostMapping("/createKlausur")
	@ResponseStatus(HttpStatus.OK)
	public KlausurDto addKlausur(@RequestBody() KlausurDto klausurDto) {
		return klausurService.addKlausur(klausurDto);
	}
	
	@PostMapping("/addAufgabe")
	@ResponseStatus(HttpStatus.OK)
	public KlausurDto addAufgabe(@RequestBody() KlausurDto klausurDto, @RequestBody() AufgabeDto aufgabeDtoParam) {
		AufgabeDto aufgabeDto = aufgabeService.addAufgabe(aufgabeDtoParam);
		return klausurService.addAufgabe(klausurDto, aufgabeDto);
	}

}
