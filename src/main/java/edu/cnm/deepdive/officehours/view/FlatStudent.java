package edu.cnm.deepdive.officehours.view;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.net.URI;
import java.util.UUID;
import org.springframework.lang.NonNull;

  @JsonPropertyOrder({"id", "student_name", "href"})
  public interface FlatStudent {

    @NonNull
    UUID getId();


    @NonNull
    String getStudentName();

    @NonNull
    URI getHref();

  }


