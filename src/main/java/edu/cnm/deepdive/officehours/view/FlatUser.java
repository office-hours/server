package edu.cnm.deepdive.officehours.view;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import edu.cnm.deepdive.officehours.model.entity.Student;
import edu.cnm.deepdive.officehours.model.entity.Teacher;
import java.net.URI;
import java.util.Date;
import java.util.UUID;
import org.springframework.lang.NonNull;

@JsonPropertyOrder({"id", "href", "oauth", "nickname", "email", "teacher", "student", "created"})
public interface FlatUser {

    @NonNull
    UUID getId();

    @NonNull
    String getOauth();

    @NonNull
    String getNickname();

    @NonNull
    String getEmail();

    @NonNull
    Teacher getTeacher();

    @NonNull
    Student getStudent();

    @NonNull
    Date getCreated();

    @NonNull
    URI getHref();

  }

