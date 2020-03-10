package edu.cnm.deepdive.officehours.controller.rest;

import edu.cnm.deepdive.officehours.model.entity.Appointment;
import edu.cnm.deepdive.officehours.model.entity.Appointment.Status;
import edu.cnm.deepdive.officehours.model.entity.Student;
import edu.cnm.deepdive.officehours.model.entity.Teacher;
import edu.cnm.deepdive.officehours.service.AppointmentRepository;
import edu.cnm.deepdive.officehours.service.StudentRepository;
import edu.cnm.deepdive.officehours.service.TeacherRepository;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
@RequestMapping("/appointments")
@ExposesResourceFor(Appointment.class)
public class AppointmentController {

  private final AppointmentRepository appointmentRepository;
  private final TeacherRepository teacherRepository;
  private final StudentRepository studentRepository;

  @Autowired
  public AppointmentController(
      AppointmentRepository appointmentRepository,
      TeacherRepository teacherRepository, StudentRepository studentRepository) {
    this.appointmentRepository = appointmentRepository;
    this.teacherRepository = teacherRepository;
    this.studentRepository = studentRepository;
  }

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public Appointment post(@RequestBody Appointment appointment) {
    Teacher teacher = teacherRepository.findOrFail(appointment.getTeacher().getId());
    Student student = studentRepository.findOrFail(appointment.getStudent().getId());
    appointment.setTeacher(teacher);
    appointment.setStudent(student);
    appointmentRepository.save(appointment);
    return appointment;
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public Iterable<Appointment> get() {
    return appointmentRepository.getAllByOrderByStartTimeDesc();
  }

  @GetMapping(value = "/range", produces = MediaType.APPLICATION_JSON_VALUE)
  public List<Appointment> getBetweenDate(
      @RequestParam(value = "start", required = false) @DateTimeFormat(iso = ISO.DATE) Date startDate,
      @RequestParam(value = "end", required = false) @DateTimeFormat(iso = ISO.DATE) Date endDate) {
    return appointmentRepository.findAllByAppointmentDateBetween(startDate, endDate);
  }

  @GetMapping(value = "/find_status", produces = MediaType.APPLICATION_JSON_VALUE)
  public List<Appointment> getStatusByDateRange(@RequestParam(value = "status") Status status,
      @RequestParam(value = "start", required = false) @DateTimeFormat(iso = ISO.DATE) Date startDate,
      @RequestParam(value = "end", required = false) @DateTimeFormat(iso = ISO.DATE) Date endDate) {
    return appointmentRepository.findAllByStatusAndAppointmentDateIsBetween(status,startDate, endDate);
  }


  @GetMapping(value = "/find_teacher", produces = MediaType.APPLICATION_JSON_VALUE)
  public List<Appointment> getAppointmentWithTeacherByDateRange(@RequestParam(value = "teacher") Teacher teacher,
      @RequestParam(value = "start", required = false) @DateTimeFormat(iso = ISO.DATE) Date startDate,
      @RequestParam(value = "end", required = false) @DateTimeFormat(iso = ISO.DATE) Date endDate) {
    return appointmentRepository.findAllByTeacherAndAppointmentDateIsBetween(teacher, startDate, endDate);
  }

  @GetMapping(value = "/find_student", produces = MediaType.APPLICATION_JSON_VALUE)
  public List<Appointment> getAppointmentWithStudentByDateRange(@RequestParam(value = "student") Student student,
      @RequestParam(value = "start", required = false) @DateTimeFormat(iso = ISO.DATE) Date startDate,
      @RequestParam(value = "end", required = false) @DateTimeFormat(iso = ISO.DATE) Date endDate) {
    return appointmentRepository.findAllByStudentAndAppointmentDateIsBetween(student, startDate, endDate);
  }

  @GetMapping(value = "/lookup", produces = MediaType.APPLICATION_JSON_VALUE)
  public Appointment getAppointments() {
    return getAppointments();
  }

  @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public Appointment get(@PathVariable UUID id) {
    return appointmentRepository.findById(id).get();
  }

  @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public Appointment put(@PathVariable UUID id, @RequestBody Appointment modifiedAppointment) {
    Appointment appointment = get(id);
    appointment.setStatus(modifiedAppointment.getStatus());
    return appointmentRepository.save(appointment);
  }

  @PutMapping(value = "/{id}", consumes = MediaType.TEXT_PLAIN_VALUE)
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void changeStatus(@PathVariable UUID id, @RequestBody Status status) {
    appointmentRepository.findById(id).ifPresent(appointment -> appointment.setStatus(status));
  }
}
