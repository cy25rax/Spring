package com.example.controllers;

import com.example.models.Student;
import com.example.repositoryes.StudentRepository;
import com.fasterxml.jackson.core.PrettyPrinter;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    private final StudentRepository studentRepository;

    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

//   * 3.5 POST /student - создать студента (принимает JSON) (отладиться можно с помощью Postman)

    @GetMapping
    public List<Student> getAll() {
        return studentRepository.getAll();
    }

    @GetMapping("/{id}")
    public Student getByID(@PathVariable Long id) {
        return studentRepository.getById(id);
    }

    @GetMapping("/search")
    public Student getByName(@RequestParam String name) {
        return studentRepository.getByName(name);
    }

    @GetMapping("/group/{groupName}/student")
    public List<Student> getByGroupName(@PathVariable String groupName) {
        return studentRepository.getByGroupName(groupName);
    }

    @DeleteMapping("/{id}")
    public List<Student> deleteStudent(@PathVariable Long id) {
        return studentRepository.deleteStudent(id);
    }

    @PostMapping
    public List<Student> createStudent(@RequestBody Student newStudent) {
        return studentRepository.createStudent(newStudent);
    }
}
