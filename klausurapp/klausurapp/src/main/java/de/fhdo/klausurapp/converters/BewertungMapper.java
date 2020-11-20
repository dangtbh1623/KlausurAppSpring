package de.fhdo.klausurapp.converters;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.fhdo.klausurapp.domain.Bewertung;
import de.fhdo.klausurapp.dto.BewertungDto;

/**
 * Bietet Methoden zur Konvertierung eines {@link BewertungDto} in eine
 * {@link Bewertung} und umgekehrt.
 */
@Component
public class BewertungMapper {
	private AufgabeMapper aufgabeMapper;

	@Autowired
	public BewertungMapper(AufgabeMapper aufgabeMapper) {
		this.aufgabeMapper = aufgabeMapper;
	}

	public BewertungDto bewertungToDto(Bewertung bewertung) {
		if (bewertung == null) {
			return null;
		}

		BewertungDto bewertungDto = new BewertungDto();

		bewertungDto.setId(bewertung.getId());
		bewertungDto.setAufgabe(aufgabeMapper.aufgabeToDto(bewertung.getAufgabe()));
		bewertungDto.setPunkte(bewertung.getPunkte());

		return bewertungDto;
	}

	public Bewertung dtoToBewertung(BewertungDto dto) {
		if (dto == null) {
			return null;
		}

		Bewertung bewertung = new Bewertung();

		bewertung.setId(dto.getId());
		bewertung.setAufgabe(aufgabeMapper.dtoToAufgabe(dto.getAufgabe()));
		bewertung.setPunkte(dto.getPunkte());

		return bewertung;
	}

	protected List<BewertungDto> bewertungSetToDtoList(Set<Bewertung> set) {
		if (set == null) {
			return null;
		}

		List<BewertungDto> result = new ArrayList<BewertungDto>(Math.max((int) (set.size() / .75f) + 1, 16));
		for (Bewertung bewertung : set) {
			result.add(bewertungToDto(bewertung));
		}

		return result;
	}
	
	protected Set<Bewertung> dtoListToBewertungSet(List<BewertungDto> list) {
		if (list == null) {
			return null;
		}

		Set<Bewertung> result = new HashSet<Bewertung>(Math.max((int) (list.size() / .75f) + 1, 16));
		for (BewertungDto bewertungDto : list) {
			result.add(dtoToBewertung(bewertungDto));
		}

		return result;
	}
}
