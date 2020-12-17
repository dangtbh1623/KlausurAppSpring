package de.fhdo.klausurapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.fhdo.klausurapp.converters.StudentMapper;
import de.fhdo.klausurapp.dto.KlausurDto;
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
	
	@Transactional
	public StudentDto addStudent(StudentDto student) {
        return studentMapper.studentToDto(studentRepository.save(studentMapper.dtoToStudent(student)));
    }
	
	@Transactional
	public StudentDto lesenStudentID(long id) {
		return studentMapper.studentToDto(studentRepository.findById(id).get());
	}
}
