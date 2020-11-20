package de.fhdo.klausurapp.converters;

import org.springframework.stereotype.Component;

import de.fhdo.klausurapp.domain.Student;
import de.fhdo.klausurapp.dto.StudentDto;

/**
 * Bietet Methoden zur Konvertierung eines {@link StudentDto} in einen
 * {@link Student} und umgekehrt.
 */
@Component
public class StudentMapper {

	public StudentDto studentToDto(Student student) {
		if (student == null) {
			return null;
		}

		StudentDto studentDto = new StudentDto();

		studentDto.setId(student.getId());
		studentDto.setMatrikelNr(student.getMatrikelNr());
		studentDto.setVorname(student.getVorname());
		studentDto.setNachname(student.getNachname());

		return studentDto;
	}

	public Student dtoToStudent(StudentDto dto) {
		if (dto == null) {
			return null;
		}

		Student student = new Student();

		student.setId(dto.getId());
		student.setMatrikelNr(dto.getMatrikelNr());
		student.setVorname(dto.getVorname());
		student.setNachname(dto.getNachname());

		return student;
	}
}
