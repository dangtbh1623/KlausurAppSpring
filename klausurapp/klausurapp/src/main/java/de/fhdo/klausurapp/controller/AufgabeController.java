package de.fhdo.klausurapp.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import de.fhdo.klausurapp.services.AufgabeService;

//Wird momentan nicht benötigt
//@RestController?
@Controller
@RequestMapping("/aufgabe")
public class AufgabeController {

	AufgabeService aufgabeService;
	
	@Autowired
	public AufgabeController(AufgabeService aufgabeService) {
		this.aufgabeService = aufgabeService;
	}
	
	
	
}
