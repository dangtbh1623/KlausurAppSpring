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
@RequestMapping("/klausurEintrag")
public class KlausureintragController {
	
	KlausurService klausurEintragService;

}
