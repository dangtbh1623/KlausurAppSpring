package de.fhdo.klausurapp.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.fhdo.klausurapp.converters.KlausurEintragMapper;
import de.fhdo.klausurapp.domain.KlausurEintrag;
import de.fhdo.klausurapp.dto.BewertungDto;
import de.fhdo.klausurapp.dto.KlausurDto;
import de.fhdo.klausurapp.dto.KlausurEintragDto;
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
		KlausurEintrag klausureinEintragRaw = klausurEintragMapper.dtoToKlausurEintrag(klausurEintrag);
		return klausurEintragMapper.klausurEintragToDto(klausurEintragRepository.save(klausureinEintragRaw));
	}

	@Transactional
	public KlausurEintragDto addBewertung(KlausurEintragDto klausurEintrag, List<BewertungDto> bewertungen) {
//		for(BewertungDto bewertung : bewertungen) {
//			klausurEintrag.getBewertungen().add(bewertung);
//		}
		klausurEintrag.setBewertungen(bewertungen);
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
}
