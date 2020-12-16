package de.fhdo.klausurapp.controller;




import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import de.fhdo.klausurapp.dto.KlausurDto;
import de.fhdo.klausurapp.dto.KlausurEintragDto;
import de.fhdo.klausurapp.services.KlausurEintragService;
import de.fhdo.klausurapp.services.KlausurService;


@Controller
@RequestMapping("/klausurEintrag")
public class KlausureintragController {
	
	private KlausurEintragService klausurEintragService;
	private KlausurService klausurService;
	
	@Autowired
	public KlausureintragController(KlausurEintragService klausurEintragService, KlausurService klausurService) {
		this.klausurEintragService = klausurEintragService;
		this.klausurService = klausurService; 
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
}
