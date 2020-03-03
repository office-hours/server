package edu.cnm.deepdive.officehours.view;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import edu.cnm.deepdive.officehours.model.entity.Appointment;
import edu.cnm.deepdive.officehours.model.entity.User;
import java.net.URI;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import org.springframework.lang.NonNull;

@JsonPropertyOrder({"id", "href", "user", "appointment"})
public interface FlatTeacher {

    @NonNull
    UUID getId();

    @NonNull
    User getUser();

    @NonNull
    List<Appointment> getAppointment();

    @NonNull
    URI getHref();
  }

