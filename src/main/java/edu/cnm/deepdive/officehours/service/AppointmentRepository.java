package edu.cnm.deepdive.officehours.service;

import edu.cnm.deepdive.officehours.model.entity.Appointment;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AppointmentRepository extends JpaRepository<Appointment, UUID> {

  Iterable<Appointment> getAllByOrderByStartDesc();

  Iterable<Appointment> getAllByStatusContainsOrOrderByStartDesc(String status);

  @Query(value = "SELECT * FROM Appointment ORDER BY start_time ", nativeQuery = true)
  List<Appointment> getAppointments();

  Object getAllByOrderByCreatedDesc();

  List<Object> getRandom();
}
