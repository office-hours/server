package edu.cnm.deepdive.officehours.controller.rest;

import edu.cnm.deepdive.officehours.model.entity.Appointment;
import edu.cnm.deepdive.officehours.model.entity.Student;
import edu.cnm.deepdive.officehours.model.entity.Teacher;
import edu.cnm.deepdive.officehours.model.entity.User;
import edu.cnm.deepdive.officehours.service.StudentRepository;
import edu.cnm.deepdive.officehours.service.UserRepository;
import java.util.UUID;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/students")
@ExposesResourceFor(Student.class)
public class StudentController {


  private final StudentRepository studentRepository;
  private final UserRepository userRepository;

  public StudentController(StudentRepository studentRepository,
      UserRepository userRepository) {
    this.studentRepository = studentRepository;
    this.userRepository = userRepository;
  }

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public Student post(@RequestBody Student student) {
    User user = userRepository.findOrFail(student.getUser().getId());
    student.setUser(user);
    studentRepository.save(student);
    return student;
  }

  @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public Student getId(@PathVariable UUID id) {
    return studentRepository.getOne(id);
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public Iterable<Student> get() {
    return studentRepository.getAllByOrderByStudentName();
  }

  @PutMapping(value = "/{id}",
      consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public Student put(@PathVariable UUID id, @RequestBody Student updated) {
    Student student = getId(id);
    student.setStudentName(updated.getStudentName());
    return studentRepository.save(student);
  }

  @GetMapping(value = "/{id}/appointments")
  public Iterable<Appointment> getAppointments(@PathVariable UUID id) {
    return studentRepository.findOrFail(id).getAppointment();
  }
}
