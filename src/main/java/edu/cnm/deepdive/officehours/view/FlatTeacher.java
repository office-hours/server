package edu.cnm.deepdive.officehours.view;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.net.URI;
import java.util.UUID;
import org.springframework.lang.NonNull;

/**
 * Declares the getters of teacher for serialization, excluding
 * references to other objects that could result in stack overflow on serialization.
 */
@JsonPropertyOrder({"id", "teacher_name","href"})
public interface FlatTeacher {

    /**
     * return the Id (UUID) of a teacher resource.
     *
     * @return teacher UUID
     */
    @NonNull
    UUID getId();

    /**
     * returns the name of a teacher
     *
     * @return teacherName
     */
    @NonNull
    String getTeacherName();

    /**
     * Returns a URL referring to the teacher resource.
     *
     * @return teacher URL
     */
    @NonNull
    URI getHref();
  }

