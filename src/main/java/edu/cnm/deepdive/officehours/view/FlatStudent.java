package edu.cnm.deepdive.officehours.view;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.net.URI;
import java.util.UUID;
import org.springframework.lang.NonNull;

/**
 * Declares the getters of student for serialization, excluding references to other objects that
 * could result in stack overflow on serialization.
 */
@JsonPropertyOrder({"id", "studentName", "href"})
public interface FlatStudent {

  /**
   * return the Id (UUID) of a student resource.
   *
   * @return student UUID
   */
  @NonNull
  UUID getId();

  /**
   * returns the name of a student
   *
   * @return studentName
   */
  @NonNull
  String getStudentName();

  /**
   * Returns a URL referring to the teacher resource.
   *
   * @return student URL
   */
  @NonNull
  URI getHref();

}


