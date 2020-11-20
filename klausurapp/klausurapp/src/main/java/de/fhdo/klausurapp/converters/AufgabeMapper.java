package de.fhdo.klausurapp.converters;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;

import de.fhdo.klausurapp.domain.Aufgabe;
import de.fhdo.klausurapp.dto.AufgabeDto;

/**
 * Bietet Methoden zur Konvertierung eines {@link AufgabeDto} in eine
 * {@link Aufgabe} und umgekehrt.
 */
@Component
public class AufgabeMapper {

	public AufgabeDto aufgabeToDto(Aufgabe aufgabe) {
		if (aufgabe == null) {
			return null;
		}

		AufgabeDto aufgabeDto = new AufgabeDto();

		aufgabeDto.setId(aufgabe.getId());
		aufgabeDto.setName(aufgabe.getName());
		aufgabeDto.setMaxPunkte(aufgabe.getMaxPunkte());

		return aufgabeDto;
	}

	public Aufgabe dtoToAufgabe(AufgabeDto dto) {
		if (dto == null) {
			return null;
		}

		Aufgabe aufgabe = new Aufgabe();

		aufgabe.setId(dto.getId());
		aufgabe.setName(dto.getName());
		aufgabe.setMaxPunkte(dto.getMaxPunkte());

		return aufgabe;
	}

	protected List<AufgabeDto> aufgabeSetToDtoList(Set<Aufgabe> set) {
		if (set == null) {
			return null;
		}

		List<AufgabeDto> result = new ArrayList<AufgabeDto>(Math.max((int) (set.size() / .75f) + 1, 16));
		for (Aufgabe aufgabe : set) {
			result.add(aufgabeToDto(aufgabe));
		}

		return result;
	}

	protected Set<Aufgabe> dtoListToAufgabeSet(List<AufgabeDto> list) {
		if (list == null) {
			return null;
		}

		Set<Aufgabe> result = new HashSet<Aufgabe>(Math.max((int) (list.size() / .75f) + 1, 16));
		for (AufgabeDto aufgabeDto : list) {
			result.add(dtoToAufgabe(aufgabeDto));
		}

		return result;
	}
}
