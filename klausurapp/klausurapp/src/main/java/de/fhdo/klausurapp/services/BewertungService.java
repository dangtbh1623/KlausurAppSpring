package de.fhdo.klausurapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.fhdo.klausurapp.converters.BewertungMapper;
import de.fhdo.klausurapp.dto.BewertungDto;
import de.fhdo.klausurapp.repositories.BewertungRepository;

@Service
public class BewertungService {
	private BewertungRepository bewertungRepository;
	private BewertungMapper bewertungMapper;

	@Autowired
	public BewertungService(BewertungRepository bewertungRepository,BewertungMapper bewertungMapper) {
		this.bewertungRepository = bewertungRepository;
		this.bewertungMapper = bewertungMapper;
	}
	
	@Transactional
	public BewertungDto addBewertung(BewertungDto bewertungDto) {
        return bewertungMapper.bewertungToDto(bewertungRepository.save(bewertungMapper.dtoToBewertung(bewertungDto)));
    }	
}
