package edu.cnm.deepdive.officehours.controller.rest;

import edu.cnm.deepdive.officehours.model.entity.Appointment;
import edu.cnm.deepdive.officehours.model.entity.Teacher;
import edu.cnm.deepdive.officehours.service.TeacherRepository;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teachers")
@ExposesResourceFor(Teacher.class)
public class TeacherController {

  private final TeacherRepository teacherRepository;

  @Autowired
  public TeacherController(TeacherRepository teacherRepository) {
    this.teacherRepository = teacherRepository;
  }

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Teacher> post(@RequestBody Teacher teacher) {
    teacherRepository.save(teacher);
    return ResponseEntity.created(teacher.getHref()).body(teacher);
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public Iterable<Teacher> get() {
    return teacherRepository.getAllByOrderByTeacherName();
  }

  @GetMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
  public Iterable<Teacher> search(@RequestParam("q") String fragment) {
    if (fragment.length() < 3) {
     // throw new SearchTermTooShortException();
    }
    return teacherRepository.getAllByTeacherNameContainsOrderByTeacherNameAsc(fragment);
  }

  @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public Teacher get(@PathVariable UUID id) {
    return teacherRepository.findById(id).get();
  }

  @DeleteMapping(value = "/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable UUID id) {
    teacherRepository.findById(id).ifPresent((teacher) -> {
      List<Appointment> appointments = teacher.getAppointment();
      appointments.forEach((appointment) -> appointment.setStatus("Cancelled"));
      appointments.clear();
      teacherRepository.delete(teacher);
    });
  }

  @PutMapping(value = "/{id}",
      consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public Teacher put(@PathVariable UUID id, @RequestBody Teacher updated) {
    Teacher teacher = get(id);
    teacher.setAppointment(updated.getAppointment());
    return teacherRepository.save(teacher);
  }
}