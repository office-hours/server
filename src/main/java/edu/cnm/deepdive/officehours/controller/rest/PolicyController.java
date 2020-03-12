package edu.cnm.deepdive.officehours.controller.rest;

import edu.cnm.deepdive.officehours.model.entity.Appointment;
import edu.cnm.deepdive.officehours.model.entity.Policy;
import edu.cnm.deepdive.officehours.model.entity.Student;
import edu.cnm.deepdive.officehours.model.entity.Teacher;
import edu.cnm.deepdive.officehours.service.AppointmentRepository;
import edu.cnm.deepdive.officehours.service.PolicyRepository;
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
 * Designates REST endpoints for http requests on {@link Policy} entity fields, using {@link
 * PolicyRepository} methods as the medium.
 */
@RestController
@RequestMapping("/policies")
@ExposesResourceFor(Policy.class)
public class PolicyController {

  private final PolicyRepository policyRepository;
  private final TeacherRepository teacherRepository;

  /**
   * Initializes this instance, injecting an instance of {@link PolicyRepository}, and {@link
   * TeacherRepository}.
   *
   * @param policyRepository  repository used to implement methods on the {@link Policy} entity.
   * @param teacherRepository repository used to implement methods on the {@link Teacher} entity.
   */
  @Autowired
  public PolicyController(PolicyRepository policyRepository,
      TeacherRepository teacherRepository) {
    this.policyRepository = policyRepository;
    this.teacherRepository = teacherRepository;
  }

  /**
   * Adds a new instance of {@link Policy} to the database and returns an instance that include the
   * autogenerated fields such as timestamps, id. The instance provided has to contain a valid
   * teacherId from the {@link Teacher} entity, the startAvailable, endAvailable, and block-time for
   * this instance.
   *
   * @param policy fields that are non-nullable on the {@link Policy} class.
   * @return the created {@link Policy} instance.
   */
  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public Policy post(@RequestBody Policy policy) {
    Teacher teacher = teacherRepository.findOrFail(policy.getTeacher().getId());
    policy.setTeacher(teacher);
    policyRepository.save(policy);
    return policy;
  }

  /**
   * @return a list of all instances of {@link Policy} in the database.
   */
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public Iterable<Policy> get() {
    return policyRepository.getAllByOrderByStartAvailableDesc();
  }

  /**
   * Returns all of the instances of {@link Policy} that are between the dates in the given
   * parameters.
   *
   * @param startAvailable the beginningIndex for the range.
   * @param endAvailable   the endIndex for the range.
   * @return instances of {@link Policy} that match the given range.
   */
  @GetMapping(value = "/times", produces = MediaType.APPLICATION_JSON_VALUE)
  public List<Policy> getBetweenDate(
      @RequestParam(value = "start", required = false) @DateTimeFormat(iso = ISO.DATE) Date startAvailable,
      @RequestParam(value = "end", required = false) @DateTimeFormat(iso = ISO.DATE) Date endAvailable) {
    return policyRepository.findAllByStartAvailableBetween(startAvailable, endAvailable);
  }

  /**
   * Returns the instance of {@link Policy} that are matches the provided Id.
   *
   * @param id UUID of the {@link Policy} instance.
   * @return fetched {@link Policy} instance.
   */
  @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public Policy get(@PathVariable UUID id) {
    return policyRepository.findOrFail(id);
  }

  /**
   * Updates the startTime and EndTime of the {@link Policy} instance, fetched by the provide Id,
   * with the provided parameters.
   *
   * @param id             UUID of the {@link Policy} instance.
   * @param modifiedPolicy partial instance of {@link Policy} which contains the modifications to be
   *                       made.
   * @return updated {@link Policy} instance.
   */
  @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public Policy put(@PathVariable UUID id, @RequestBody Policy modifiedPolicy) {
    Policy policy = get(id);
    policy.setStartAvailable(modifiedPolicy.getStartAvailable());
    policy.setEndAvailable(modifiedPolicy.getEndAvailable());
    return policyRepository.save(policy);
  }
}
