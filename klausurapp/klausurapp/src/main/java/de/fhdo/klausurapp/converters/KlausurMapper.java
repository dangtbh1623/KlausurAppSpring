package de.fhdo.klausurapp.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.fhdo.klausurapp.domain.Klausur;
import de.fhdo.klausurapp.dto.KlausurDto;

/**
 * Bietet Methoden zur Konvertierung eines {@link KlausurDto} in eine
 * {@link Klausur} und umgekehrt.
 */
@Component
public class KlausurMapper {
	private AufgabeMapper aufgabeMapper;

	@Autowired
	public KlausurMapper(AufgabeMapper aufgabeMapper) {
		this.aufgabeMapper = aufgabeMapper;
	}

	public KlausurDto klausurToDto(Klausur klausur) {
		if (klausur == null) {
			return null;
		}

		KlausurDto klausurDto = new KlausurDto();

		klausurDto.setId(klausur.getId());
		klausurDto.setVeranstaltung(klausur.getVeranstaltung());
		klausurDto.setSemester(klausur.getSemester());
		klausurDto.setJahr(klausur.getJahr());
		klausurDto.setAufgaben(aufgabeMapper.aufgabeSetToDtoList(klausur.getAufgaben()));

		return klausurDto;
	}

	public Klausur dtoToKlausur(KlausurDto dto) {
		if (dto == null) {
			return null;
		}

		Klausur klausur = new Klausur();

		klausur.setId(dto.getId());
		klausur.setVeranstaltung(dto.getVeranstaltung());
		klausur.setSemester(dto.getSemester());
		klausur.setJahr(dto.getJahr());
		klausur.setAufgaben(aufgabeMapper.dtoListToAufgabeSet(dto.getAufgaben()));

		return klausur;
	}
}
