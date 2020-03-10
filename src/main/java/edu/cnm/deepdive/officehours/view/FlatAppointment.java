package edu.cnm.deepdive.officehours.view;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import edu.cnm.deepdive.officehours.Status;
import edu.cnm.deepdive.officehours.Subject;
import edu.cnm.deepdive.officehours.model.entity.Student;
import edu.cnm.deepdive.officehours.model.entity.Teacher;
import java.net.URI;
import java.util.Date;
import java.util.UUID;
import org.springframework.lang.NonNull;

@JsonPropertyOrder({"id", "endTime", "status", "subject", "startTime", "teacher",  "student", "created"})
public interface FlatAppointment {

    @NonNull
    Date getEnd();

    @NonNull
     UUID getId();

    @NonNull
    Date getStart();

    @NonNull
    Date getCreated();

    @NonNull
    Date getUpdated();

    @NonNull
    Student getStudent();

    @NonNull
    Teacher getTeacher();

    @NonNull
    Date getStartTime();

    @NonNull
    Date getEndTime();


  }

