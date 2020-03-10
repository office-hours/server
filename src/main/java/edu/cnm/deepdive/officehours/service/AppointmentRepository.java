package edu.cnm.deepdive.officehours.service;

import edu.cnm.deepdive.officehours.model.entity.Appointment;
import edu.cnm.deepdive.officehours.model.entity.Appointment.Status;
import edu.cnm.deepdive.officehours.model.entity.Student;
import edu.cnm.deepdive.officehours.model.entity.Teacher;
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

  List<Appointment> findAllByStudentAndAppointmentDateIsBetween(Student student, Date startDate, Date endDate);

  List<Appointment> findAllByTeacherAndAppointmentDateIsBetween(Teacher teacher, Date startTime, Date endTime);

  List<Appointment> findAllByStatusAndAppointmentDateIsBetween(Status status,Date startTime, Date endTime);

//  Iterable<Appointment> getAllByOrderByStartTimeAsc(String status);


  Object getAllByOrderByCreatedDesc();

//  List<Object> getRandom();

  }

