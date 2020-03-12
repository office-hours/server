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

/**
 * Designates REST endpoints for http requests on {@link Appointment} entity fields, using
 * {@link AppointmentRepository} operations as the medium.
 */
@RestController
@RequestMapping("/appointments")
@ExposesResourceFor(Appointment.class)
public class AppointmentController {

  private final AppointmentRepository appointmentRepository;
  private final TeacherRepository teacherRepository;
  private final StudentRepository studentRepository;

  /**
   * Initializes this instance, injecting an instance of {@link AppointmentRepository},
   * {@link TeacherRepository}, and {@link StudentRepository}.
   * @param appointmentRepository repository used to implement operations on the {@link Appointment} entity.
   * @param teacherRepository repository used to implement operations on the {@link Teacher} entity.
   * @param studentRepository repository used to implement operations on the {@link Student} entity.
   */
  @Autowired
  public AppointmentController(
      AppointmentRepository appointmentRepository,
      TeacherRepository teacherRepository, StudentRepository studentRepository) {
    this.appointmentRepository = appointmentRepository;
    this.teacherRepository = teacherRepository;
    this.studentRepository = studentRepository;
  }

  /**
   * Adds a new instance of {@link Appointment} to the database and returns an instance that include
   * the autogenerated fields such as timestamps, id. The instance provided has to contain a valid teacherId from the {@link Teacher} entity,
   * a valid studentId from the {@link Student} entity, the startTime and endTime for this instance.
   * @param appointment fields that are non-nullable on the {@link Appointment} class.
   * @return the created {@link Appointment} instance.
   */
  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public Appointment post(@RequestBody Appointment appointment) {
    Teacher teacher = teacherRepository.findOrFail(appointment.getTeacher().getId());
    Student student = studentRepository.findOrFail(appointment.getStudent().getId());
    appointment.setTeacher(teacher);
    appointment.setStudent(student);
    appointmentRepository.save(appointment);
    return appointment;
  }

  /**
   * Returns all of the instances of {@link Appointment} that are on the database order by the
   * start time
   * @return a list of all instances of {@link Appointment} in the database.
   */
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public Iterable<Appointment> get() {
    return appointmentRepository.getAllByOrderByStartTimeDesc();
  }

  /**
   * Returns all of the instances of {@link Appointment} that are between the dates in the given parameters.
   * @param startDate the beginningIndex for the range.
   * @param endDate the endIndex for the range.
   * @return instances of {@link Appointment} that match the given range.
   */
  @GetMapping(value = "/range", produces = MediaType.APPLICATION_JSON_VALUE)
  public List<Appointment> getBetweenDate(
      @RequestParam(value = "start", required = false) @DateTimeFormat(iso = ISO.DATE) Date startDate,
      @RequestParam(value = "end", required = false) @DateTimeFormat(iso = ISO.DATE) Date endDate) {
    return appointmentRepository.findAllByStartTimeBetween(startDate, endDate);
  }

  /**
   * Returns all of the instances of {@link Appointment} that are between the dates in the given
   * parameters and match the provide enum.
   * @param status the enum that is being queried
   * @param startDate the beginningIndex for the range.
   * @param endDate the endIndex for the range.
   * @return instances of {@link Appointment} that match the given range and status.
   */
  @GetMapping(value = "/status", produces = MediaType.APPLICATION_JSON_VALUE)
  public List<Appointment> getStatusByDateRange(@RequestParam(value = "status") Status status,
      @RequestParam(value = "start", required = false) @DateTimeFormat(iso = ISO.DATE) Date startDate,
      @RequestParam(value = "end", required = false) @DateTimeFormat(iso = ISO.DATE) Date endDate) {
    return appointmentRepository.findAllByStatusAndStartTimeIsBetween(status,startDate, endDate);
  }

  /**
   * Returns the instance of {@link Appointment} that matches the provided Id.
   * @param id UUID of the {@link Appointment} instance.
   * @return fetched {@link Appointment} instance.
   */
  @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public Appointment get(@PathVariable UUID id) {
    return appointmentRepository.findById(id).get();
  }

  /**
   * Updates the startTime and EndTime of the {@link Appointment} instance, fetched by the provide Id, with the provided parameters.
   * @param id UUID of the {@link Appointment} instance.
   * @param modifiedAppointment partial instance of {@link Appointment} which contains the modifications to be made.
   * @return updated {@link Appointment} instance.
   */
  @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public Appointment put(@PathVariable UUID id, @RequestBody Appointment modifiedAppointment) {
    Appointment appointment = get(id);
    appointment.setStartTime(modifiedAppointment.getStartTime());
    appointment.setEndTime(modifiedAppointment.getEndTime());
    return appointmentRepository.save(appointment);
  }

  /**
   * Updates the Status of the {@link Appointment} instance, fetched by the provide Id, with the provided parameters.
   * @param id UUID of the {@link Appointment} instance.
   * @param status enum of {@link Appointment} which will replace the enum previously assigned.
   * @return updated {@link Appointment} instance.
   */
  @PutMapping(value = "/{id}/status", consumes = MediaType.TEXT_PLAIN_VALUE,
      produces = MediaType.TEXT_PLAIN_VALUE)
  public String updateStatus(@PathVariable UUID id, @RequestBody String status) {
    Appointment appointment = appointmentRepository.findOrFail(id);
    appointment.setStatus(Status.valueOf(status.toUpperCase()));
    appointmentRepository.save(appointment);
    return appointment.getStatus().name();
  }

}
