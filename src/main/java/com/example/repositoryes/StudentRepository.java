package com.example.repositoryes;

import com.example.models.Student;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class StudentRepository {

  private final List<Student> students;

  public StudentRepository() {
    this.students = new ArrayList<>();
    students.add(new Student("student 1", "group 1"));
    students.add(new Student("student 2", "group 1"));
    students.add(new Student("student 3", "group 2"));
    students.add(new Student("student 4", "group 2"));
    students.add(new Student("student 5", "group 2"));
    students.add(new Student("student 6", "group 3"));
  }

  public List<Student> getAll() {
    return List.copyOf(students);
  }

  public Student getByName(String name) {
    return students.stream()
      .filter(it -> Objects.equals(it.getName(), name))
      .findFirst()
      .orElse(null);
  }

  public Student getById(long id) {
    return students.stream()
      .filter(it -> Objects.equals(it.getId(), id))
      .findFirst()
      .orElse(null);
  }

  public List<Student> getByGroupName(String groupName) {
    return students.stream()
            .filter(it -> Objects.equals(it.getGroupName(), groupName))
            .collect(Collectors.toList());
  }

  public List<Student> deleteStudent(Long id) {
    Student removingStudent = getById(id);
    if (removingStudent != null) {
      students.remove(removingStudent);
    }
    return getAll();
  }

  public List<Student> createStudent(Student newStudent) {
    students.add(newStudent);
    return getAll();
  }
}
