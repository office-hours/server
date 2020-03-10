package edu.cnm.deepdive.officehours.view;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import edu.cnm.deepdive.officehours.model.entity.Appointment.Status;
import edu.cnm.deepdive.officehours.model.entity.Appointment.Subject;
import edu.cnm.deepdive.officehours.model.entity.Student;
import edu.cnm.deepdive.officehours.model.entity.Teacher;
import java.net.URI;
import java.util.Date;
import java.util.UUID;
import org.springframework.lang.NonNull;

@JsonPropertyOrder({"id", "created", "updated", "status", "subject", "start_time", "end_time", "href"})
public interface FlatAppointment {

    @NonNull
     UUID getId();

    @NonNull
    Date getCreated();

    @NonNull
    Date getUpdated();

    @NonNull
    Status getStatus();

    Subject getSubject();

    @NonNull
    Date getStartTime();

    @NonNull
    Date getEndTime();

    @NonNull
    URI getHref();
  }

