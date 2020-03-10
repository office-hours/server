package edu.cnm.deepdive.officehours.controller.rest;

import edu.cnm.deepdive.officehours.model.entity.Appointment;
import edu.cnm.deepdive.officehours.model.entity.Teacher;
import edu.cnm.deepdive.officehours.model.entity.User;
import edu.cnm.deepdive.officehours.service.TeacherRepository;
import edu.cnm.deepdive.officehours.service.UserRepository;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/teachers")
@ExposesResourceFor(Teacher.class)
public class TeacherController {

private final TeacherRepository teacherRepository;
private final UserRepository userRepository;

  @Autowired
  public TeacherController(TeacherRepository teacherRepository,
      UserRepository userRepository) {
    this.teacherRepository = teacherRepository;
    this.userRepository = userRepository;
  }


  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public Teacher post(@RequestBody Teacher teacher) {
    User user = userRepository.findOrFail(teacher.getUser().getId());
    teacher.setUser(user);
    teacherRepository.save(teacher);
    return teacher;
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public Iterable<Teacher> getTeacherByName() {
    return teacherRepository.getAllByOrderByTeacherName();
  }

  @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public Teacher get(@PathVariable UUID id) {
    return teacherRepository.findById(id).get();
  }


  @PutMapping(value = "/{id}",
      consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public Teacher put(@PathVariable UUID id, @RequestBody Teacher updated) {
    Teacher teacher = get(id);
    teacher.setTeacherName(updated.getTeacherName());
    return teacherRepository.save(teacher);
  }

  @GetMapping(value = "/{id}/appointments")
  public Iterable<Appointment> getAppointments(@PathVariable UUID id) {
    return teacherRepository.findOrFail(id).getAppointment();
  }
}
