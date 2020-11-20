package de.fhdo.klausurapp.converters;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import de.fhdo.klausurapp.domain.KlausurEintrag;
import de.fhdo.klausurapp.dto.KlausurEintragDto;

/**
 * Bietet Methoden zur Konvertierung eines {@link KlausurEintragDto} in einen
 * {@link KlausurEintrag} und umgekehrt.
 */
@Component
public class KlausurEintragMapper {
	private StudentMapper studentMapper;
	private BewertungMapper bewertungMapper;
	private KlausurMapper klausurMapper;

	public KlausurEintragMapper(StudentMapper studentMapper, BewertungMapper bewertungMapper,
			KlausurMapper klausurMapper) {
		this.studentMapper = studentMapper;
		this.bewertungMapper = bewertungMapper;
		this.klausurMapper = klausurMapper;
	}

	public KlausurEintragDto klausurEintragToDto(KlausurEintrag klausurEintrag) {
		if (klausurEintrag == null) {
			return null;
		}

		KlausurEintragDto klausurEintragDto = new KlausurEintragDto();

		klausurEintragDto.setId(klausurEintrag.getId());
		klausurEintragDto.setKlausur(klausurMapper.klausurToDto(klausurEintrag.getKlausur()));
		klausurEintragDto.setVersuch(klausurEintrag.getVersuch());
		klausurEintragDto.setBewertungen(bewertungMapper.bewertungSetToDtoList(klausurEintrag.getBewertungen()));
		klausurEintragDto.setStudent(studentMapper.studentToDto(klausurEintrag.getStudent()));

		return klausurEintragDto;
	}

	public List<KlausurEintragDto> klausurEintraegeToDtoListe(List<KlausurEintrag> eintraege) {
		if (eintraege == null) {
			return null;
		}
		return eintraege.stream().map(e -> klausurEintragToDto(e)).collect(Collectors.toList());
	}

	public KlausurEintrag dtoToKlausurEintrag(KlausurEintragDto dto) {
		if (dto == null) {
			return null;
		}

		KlausurEintrag klausurEintrag = new KlausurEintrag();

		klausurEintrag.setId(dto.getId());
		klausurEintrag.setKlausur(klausurMapper.dtoToKlausur(dto.getKlausur()));
		klausurEintrag.setVersuch(dto.getVersuch());
		klausurEintrag.setBewertungen(bewertungMapper.dtoListToBewertungSet(dto.getBewertungen()));
		klausurEintrag.setStudent(studentMapper.dtoToStudent(dto.getStudent()));

		return klausurEintrag;
	}

	public List<KlausurEintrag> dtoToKlausurEintraegeListe(List<KlausurEintragDto> eintraege) {
		if (eintraege == null) {
			return null;
		}
		return eintraege.stream().map(e -> dtoToKlausurEintrag(e)).collect(Collectors.toList());
	}
}
