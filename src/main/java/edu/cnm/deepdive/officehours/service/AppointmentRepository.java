package edu.cnm.deepdive.officehours.service;

import edu.cnm.deepdive.officehours.model.entity.Appointment;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AppointmentRepository extends JpaRepository<Appointment, UUID> {

  Iterable<Appointment> getAllByOrderByStartTimeDesc();

  List<Appointment> findAllByStartTimeBetween(Date startTime, Date endTime);

  List<Appointment> findAllByAppointmentDateBetween(Date startDate, Date endDate);

//  Iterable<Appointment> getAllByOrderByStartTimeAsc(String status);


  Object getAllByOrderByCreatedDesc();

//  List<Object> getRandom();

  }

