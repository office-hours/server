package edu.cnm.deepdive.officehours.service;

import edu.cnm.deepdive.officehours.model.entity.Student;
import edu.cnm.deepdive.officehours.model.repository.StudentRepository;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

  private final StudentRepository studentRepository;

  @Autowired
  public StudentService(StudentRepository studentRepository) {
    this.studentRepository = studentRepository;
  }

  public Student create(Student student) {
    return studentRepository.save(student);
  }

  public Iterable<Student> readAll() {
    return studentRepository.getAllByOrderByStudentName();
  }

  public Optional<Student> readOne(UUID id) {
    return studentRepository.findById(id);
  }

  public Optional<Student> update(UUID id, Student student) {
    return studentRepository.findById(id).map((s) -> {
      s.setStudentName(student.getStudentName());
      return studentRepository.save(s);
    });
  }

  public Optional<String> update(UUID id, String studentName) {
    return studentRepository.findById(id).map((s) -> {
      s.setStudentName(studentName);
      return studentRepository.save(s).getStudentName();
    });
  }
}
