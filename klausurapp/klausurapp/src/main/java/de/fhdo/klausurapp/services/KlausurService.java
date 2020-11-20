package de.fhdo.klausurapp.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.fhdo.klausurapp.converters.KlausurMapper;
import de.fhdo.klausurapp.domain.Klausur;
import de.fhdo.klausurapp.dto.AufgabeDto;
import de.fhdo.klausurapp.dto.KlausurDto;
import de.fhdo.klausurapp.repositories.KlausurRepository;

@Service
public class KlausurService {

	private KlausurRepository klausurRepository;
	private KlausurMapper klausurMapper;

	@Autowired
	public KlausurService(KlausurRepository klausurRepository, KlausurMapper klausurMapper) {
		this.klausurRepository = klausurRepository;
		this.klausurMapper = klausurMapper;
	}

	@Transactional
	public List<KlausurDto> lesenKlausuren() {
		List<Klausur> klausuren = (List<Klausur>) klausurRepository.findAll();
		List<KlausurDto> klausurenResult = new ArrayList<KlausurDto>();
		
		for (Klausur klausur : klausuren) {
			klausurenResult.add(klausurMapper.klausurToDto(klausur));
		}
		
		return klausurenResult;
	}

	@Transactional
	public KlausurDto lesenKlausurID(long id) {
		return klausurMapper.klausurToDto(klausurRepository.findById(id).get());
	}
	
	public KlausurDto addKlausur(KlausurDto klausur) {
        return klausurMapper.klausurToDto(klausurRepository.save(klausurMapper.dtoToKlausur(klausur)));
    }
	
	public KlausurDto addAufgabe(KlausurDto klausur, AufgabeDto aufgabe) {
		klausur.getAufgaben().add(aufgabe);
		Klausur klausurRaw = klausurMapper.dtoToKlausur(klausur);
		return klausurMapper.klausurToDto(klausurRepository.save(klausurRaw));
	}
		
}
