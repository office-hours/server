package edu.cnm.deepdive.officehours.controller.rest;

import edu.cnm.deepdive.officehours.model.entity.Appointment;
import edu.cnm.deepdive.officehours.model.entity.Student;
import edu.cnm.deepdive.officehours.model.entity.User;
import edu.cnm.deepdive.officehours.model.repository.AppointmentRepository;
import edu.cnm.deepdive.officehours.model.repository.StudentRepository;
import edu.cnm.deepdive.officehours.model.repository.TeacherRepository;
import edu.cnm.deepdive.officehours.model.repository.UserRepository;
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

/**
 * Designates REST endpoints for http requests on {@link Student} entity fields, using {@link
 * StudentRepository} operations as the medium.
 */
@RestController
@RequestMapping("/students")
@ExposesResourceFor(Student.class)
public class StudentController {

  private final StudentRepository studentRepository;
  private final UserRepository userRepository;

  /**
   * Initializes this instance, injecting an instance of {@link AppointmentRepository}, {@link
   * TeacherRepository}, and {@link StudentRepository}.
   *
   * @param userRepository    repository used to implement operations on the {@link User} entity.
   * @param studentRepository repository used to implement operations on the {@link Student}
   *                          entity.
   */
  public StudentController(StudentRepository studentRepository,
      UserRepository userRepository) {
    this.studentRepository = studentRepository;
    this.userRepository = userRepository;
  }

  /**
   * Adds a new instance of {@link Student} to the database and returns an instance that include the
   * autogenerated id. The instance provided has to contain a valid UserId from the {@link User}
   * entity, a student name for this instance.
   *
   * @param student fields that are non-nullable on the {@link Student} class.
   * @return the created {@link Student} instance.
   */
  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public Student post(@RequestBody Student student) {
    User user = userRepository.findOrFail(student.getUser().getId());
    student.setUser(user);
    studentRepository.save(student);
    return student;
  }

  /**
   * Returns the instance of {@link Student} that matches the provided Id.
   *
   * @param id UUID of the {@link Student} instance.
   * @return fetched {@link Student} instance.
   */
  @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public Student getId(@PathVariable UUID id) {
    return studentRepository.findOrFail(id);
  }

  /**
   * Returns all of the instances of {@link Student} that are on the database ordered by student
   * name.
   *
   * @return a list of all instances of {@link Student} in the database.
   */
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public Iterable<Student> get() {
    return studentRepository.getAllByOrderByStudentName();
  }

  /**
   * Updates the student name of the {@link Student} instance, fetched by the provide Id, with the
   * provided parameter.
   *
   * @param id      UUID of the {@link Student} instance.
   * @param updated partial instance of {@link Student} which contains the modifications to be
   *                made.
   * @return updated {@link Student} instance.
   */
  @PutMapping(value = "/{id}",
      consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public Student put(@PathVariable UUID id, @RequestBody Student updated) {
    Student student = getId(id);
    student.setStudentName(updated.getStudentName());
    return studentRepository.save(student);
  }

  /**
   * Returns a list of {@link Appointment} that is assigned to the {@link Student} with the provided
   * Id.
   *
   * @param id UUID of the {@link Student} instance.
   * @return a list of {@link Appointment} assigned to the given {@link Student}.
   */
  @GetMapping(value = "/{id}/appointments")
  public Iterable<Appointment> getAppointments(@PathVariable UUID id) {
    return studentRepository.findOrFail(id).getAppointment();
  }
}
