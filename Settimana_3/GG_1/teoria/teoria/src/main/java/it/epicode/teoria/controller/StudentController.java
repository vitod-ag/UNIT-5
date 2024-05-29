package it.epicode.teoria.controller;

import it.epicode.teoria.DTO.StudentDTO;
import it.epicode.teoria.entity.Student;
import it.epicode.teoria.exception.BadRequestException;
import it.epicode.teoria.exception.StudentNotFoundException;
import it.epicode.teoria.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class StudentController {
    @Autowired
    private StudentService studentService;

    @PostMapping("/api/students")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String saveStudent(@RequestBody @Validated StudentDTO studentDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors().stream().map(error -> error.getDefaultMessage()).
                    reduce("", (s, s2) -> s + s2));
        }
        return studentService.saveStudent(studentDTO);
    }

    @GetMapping("/api/students")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/api/students/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public Student getStudentById(@PathVariable int id) {
        Optional<Student> studentOptional = studentService.getStudentById(id);
        if (studentOptional.isPresent()) {
            return studentOptional.get();
        } else {
            throw new StudentNotFoundException("Student with id:" + id + " not found");
        }
    }

    @PutMapping("/api/students/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String updateStudent(@PathVariable int id, @RequestBody @Validated StudentDTO studentDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors().stream().map(error -> error.getDefaultMessage()).
                    reduce("", (s, s2) -> s + s2));
        }
        return studentService.saveStudent(studentDTO);
    }

    @DeleteMapping("/api/students/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String deleteStudentById(@PathVariable int id) {
        return studentService.deleteStudentById(id);
    }
}
