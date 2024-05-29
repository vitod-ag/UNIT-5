package it.epicode.teoria.service;

import it.epicode.teoria.DTO.StudentDTO;
import it.epicode.teoria.entity.Student;
import it.epicode.teoria.exception.StudentNotFoundException;
import it.epicode.teoria.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public String saveStudent(StudentDTO studentDTO) {
        Student student = new Student();
        student.setName(studentDTO.getName());
        student.setSurname(studentDTO.getSurname());
        student.setBirthDate(studentDTO.getBirthDate());

        studentRepository.save(student);
        return "Student with id: " + student.getId() + " saved";
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Optional<Student> getStudentById(int id) {
        return studentRepository.findById(id);
    }

    public Student updateStudent(int id, StudentDTO studentDTO) {
        Optional<Student> studentOptional = getStudentById(id);
        if (studentOptional.isPresent()) {
            Student student = studentOptional.get();
            student.setName(studentDTO.getName());
            student.setSurname(studentDTO.getSurname());
            student.setBirthDate(studentDTO.getBirthDate());
            return studentRepository.save(student);
        }else {
            throw new StudentNotFoundException("Student with id:" + id + " not found");
        }
    }

    public String deleteStudentById(int id) {
        Optional<Student> studentOptional = getStudentById(id);
        if (studentOptional.isPresent()) {
            studentRepository.delete(studentOptional.get());
            return "Student with id:" + id + " correctly deleted";
        }else {
            throw new StudentNotFoundException("Student with id:" + id + " not found");
        }
    }
}
