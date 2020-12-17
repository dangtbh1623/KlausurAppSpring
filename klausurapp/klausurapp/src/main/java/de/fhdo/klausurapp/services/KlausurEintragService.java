package de.fhdo.klausurapp.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.fhdo.klausurapp.converters.KlausurEintragMapper;
import de.fhdo.klausurapp.domain.KlausurEintrag;
import de.fhdo.klausurapp.dto.AufgabeDto;
import de.fhdo.klausurapp.dto.BewertungDto;
import de.fhdo.klausurapp.dto.KlausurDto;
import de.fhdo.klausurapp.dto.KlausurEintragDto;
import de.fhdo.klausurapp.dto.StudentDto;
import de.fhdo.klausurapp.repositories.KlausurEintragRepository;

@Service
public class KlausurEintragService {
	
	private KlausurEintragRepository klausurEintragRepository;
	private KlausurEintragMapper klausurEintragMapper;
	
	@Autowired
	public KlausurEintragService(KlausurEintragRepository klausurEintragRepository, KlausurEintragMapper klausurEintragMapper) {
		this.klausurEintragRepository = klausurEintragRepository;
		this.klausurEintragMapper = klausurEintragMapper;
	}
	
	public KlausurEintragDto addNewKlausurEintrag(KlausurEintragDto klausurEintrag) {
		klausurEintrag.setBewertungen(new ArrayList<BewertungDto>());
//		for(int i=0;i<klausurEintrag.getKlausur().getAufgaben().size();i++) {
//			klausurEintrag.getBewertungen().add(new BewertungDto());
//		}
//		for (AufgabeDto aufgabe : klausurEintrag.getKlausur().getAufgaben()) {
//			klausurEintrag.getBewertungen().add(new BewertungDto(aufgabe, 0.0));
//		}
		KlausurEintrag klausureinEintragRaw = klausurEintragMapper.dtoToKlausurEintrag(klausurEintrag);
		return klausurEintragMapper.klausurEintragToDto(klausurEintragRepository.save(klausureinEintragRaw));
	}

	@Transactional
	public KlausurEintragDto addBewertung(KlausurEintragDto klausurEintrag, List<BewertungDto> bewertungen) {
		klausurEintrag.setBewertungen(bewertungen);
		KlausurEintrag klausureinEintragRaw = klausurEintragMapper.dtoToKlausurEintrag(klausurEintrag);
		return klausurEintragMapper.klausurEintragToDto(klausurEintragRepository.save(klausureinEintragRaw));
	}
	
	@Transactional
	public KlausurEintragDto addOneBewertung(KlausurEintragDto klausurEintrag, BewertungDto bewertungen) {
		klausurEintrag.getBewertungen().add(bewertungen);
		KlausurEintrag klausureinEintragRaw = klausurEintragMapper.dtoToKlausurEintrag(klausurEintrag);
		return klausurEintragMapper.klausurEintragToDto(klausurEintragRepository.save(klausureinEintragRaw));
	}
	
	
	
	@Transactional
	public List<KlausurEintragDto> klausurEintragZuKlausurLesen(KlausurDto klausur) {
		List<KlausurEintragDto> klausurEintragResults = new ArrayList<KlausurEintragDto>();
		
		for (KlausurEintrag klausurEintrag : klausurEintragRepository.findByKlausur_Id(klausur.getId())) {
			klausurEintragResults.add(klausurEintragMapper.klausurEintragToDto(klausurEintrag));
		}		
		return klausurEintragResults;
	}
	
	@Transactional
	public KlausurEintragDto lesenKlausurEintragID(long id) {
		return klausurEintragMapper.klausurEintragToDto(klausurEintragRepository.findById(id).get());
	}
	
}
