package edu.cnm.deepdive.officehours.view;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.net.URI;
import java.util.UUID;
import org.springframework.lang.NonNull;

@JsonPropertyOrder({"id", "teacher_name","href"})
public interface FlatTeacher {

    @NonNull
    UUID getId();

    @NonNull
    String getTeacherName();

    @NonNull
    URI getHref();
  }

