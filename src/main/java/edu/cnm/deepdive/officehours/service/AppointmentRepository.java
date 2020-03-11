package edu.cnm.deepdive.officehours.service;

import edu.cnm.deepdive.officehours.model.entity.Appointment;
import edu.cnm.deepdive.officehours.model.entity.Appointment.Status;
import edu.cnm.deepdive.officehours.model.entity.Teacher;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, UUID> {

  Iterable<Appointment> getAllByOrderByStartTimeDesc();

  List<Appointment> findAllByStartTimeBetween(Date startTime, Date endTime);

  List<Appointment> findAllByStatusAndStartTimeIsBetween(Status status,Date startTime, Date endTime);

  Object getAllByOrderByCreatedDesc();

  default Appointment findOrFail(UUID id) {
    return findById(id).get();
  }
  }

