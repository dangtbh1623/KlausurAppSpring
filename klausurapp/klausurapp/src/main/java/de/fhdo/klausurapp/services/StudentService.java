package de.fhdo.klausurapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.fhdo.klausurapp.converters.StudentMapper;
import de.fhdo.klausurapp.dto.StudentDto;
import de.fhdo.klausurapp.repositories.StudentRepository;

@Service
public class StudentService {
	
	private StudentRepository studentRepository;
	private StudentMapper studentMapper;

	@Autowired
	public StudentService(StudentRepository studentRepository, StudentMapper studentMapper) {
		this.studentRepository = studentRepository;
		this.studentMapper = studentMapper;
	}
	
	public StudentDto addStudent(StudentDto student) {
        return studentMapper.studentToDto(studentRepository.save(studentMapper.dtoToStudent(student)));
    }
}
