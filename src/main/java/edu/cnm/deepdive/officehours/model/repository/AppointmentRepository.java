package edu.cnm.deepdive.officehours.model.repository;

import edu.cnm.deepdive.officehours.model.entity.Appointment;
import edu.cnm.deepdive.officehours.model.entity.Appointment.Status;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Database operations that can be performed on {@link Appointment} entity instances.
 */
public interface AppointmentRepository extends JpaRepository<Appointment, UUID> {

  /**
   * Selects and returns all {@link Appointment} instances pertaining to start times in descending
   * order.
   *
   * @return {@link Iterable} sequence of {@link Appointment} instances.
   */
  Iterable<Appointment> getAllByOrderByStartTimeDesc();

  /**
   * Selects and returns a {@link List} if all {@link Appointment} consisting of available times
   * between start and end times.
   *
   * @param startTime time instances.
   * @param endTime   instances.
   * @return a {@link List} sequence of {@link Appointment} instances.
   */
  List<Appointment> findAllByStartTimeBetween(Date startTime, Date endTime);

  /**
   * Selects and returns a {@link List} if all {@link Appointment} pertaining to {@link Status} of
   * {@link Appointment} between start and end time.
   *
   * @param status    instance.
   * @param startTime time instances.
   * @param endTime   instances.
   * @return a {@link List} sequence of {@link Appointment} status and start time instances.
   */
  List<Appointment> findAllByStatusAndStartTimeIsBetween(Status status, Date startTime,
      Date endTime);

  /**
   * Returns an {@link Object} of created {@link Appointment} in descending order.
   *
   * @return {@link Object} sequence of {@link Appointment} instances.
   */
  Object getAllByOrderByCreatedDesc();

  /**
   * Defaults to finding a {@link Appointment} id or failing.
   *
   * @param id for {@link Appointment}.
   * @return a {@link Appointment} id.
   */
  default Appointment findOrFail(UUID id) {
    return findById(id).get();
  }
}

