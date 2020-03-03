package edu.cnm.deepdive.officehours.controller;

import edu.cnm.deepdive.officehours.model.entity.Appointment;
import edu.cnm.deepdive.officehours.model.entity.Teacher;
import edu.cnm.deepdive.officehours.service.AppointmentRepository;
import edu.cnm.deepdive.officehours.service.TeacherRepository;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
@RequestMapping("/appointments")
@ExposesResourceFor(Appointment.class)
public class AppointmentController {

  private final AppointmentRepository appointmentRepository;
  private final TeacherRepository teacherRepository;


  @Autowired
  public AppointmentController(
      AppointmentRepository appointmentRepository,
      TeacherRepository teacherRepository) {
    this.appointmentRepository = appointmentRepository;
    this.teacherRepository = teacherRepository;
  }

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public Appointment post(@RequestBody Appointment appointment) {
    appointmentRepository.save(appointment);
    return appointment;
  }

  @GetMapping(produces =  MediaType.APPLICATION_JSON_VALUE)
  public Iterable<Appointment> get() {
    return appointmentRepository.getAllByOrderByStartDesc();
  }

  @GetMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
  public Iterable<Appointment> getAppointments(@RequestParam("q") String status) {
//    if (status.length() < 3) {
//      throw new SearchTermTooShortException(); TODO add class for this exception.
//    }
    return  appointmentRepository.getAllByStatusContainsOrOrderByStartDesc(status);
  }

  @GetMapping(value = "/lookup", produces = MediaType.APPLICATION_JSON_VALUE)
  public Appointment getAppointments() {return getAppointments();}

  @GetMapping( value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public Appointment get(@PathVariable UUID id) {
    return appointmentRepository.findById(id).get();
  }

  @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public Appointment put(@PathVariable UUID id,@RequestBody Appointment modifiedAppointment) {
    Appointment appointment = get(id);
    appointment.setStatus(modifiedAppointment.getStatus());
    return appointmentRepository.save(appointment);
  }

}
