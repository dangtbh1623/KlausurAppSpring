package de.fhdo.klausurapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.fhdo.klausurapp.converters.AufgabeMapper;
import de.fhdo.klausurapp.dto.AufgabeDto;
import de.fhdo.klausurapp.dto.KlausurEintragDto;
import de.fhdo.klausurapp.repositories.AufgabeRepository;

@Service
public class AufgabeService {
	private AufgabeRepository aufgabeRepository;
	private AufgabeMapper aufgabeMapper;
	
	@Autowired
	public AufgabeService(AufgabeRepository aufgabeRepository,AufgabeMapper aufgabeMapper )
	{
		this.aufgabeMapper = aufgabeMapper;
		this.aufgabeRepository = aufgabeRepository;
	}
	
	@Transactional
	public AufgabeDto addAufgabe(AufgabeDto aufgabeDto) {
        return aufgabeMapper.aufgabeToDto(aufgabeRepository.save(aufgabeMapper.dtoToAufgabe(aufgabeDto)));
    }
	
	@Transactional
	public AufgabeDto lesenAufgabeID(long id) {
		return aufgabeMapper.aufgabeToDto(aufgabeRepository.findById(id).get());
	}

}
