package edu.cnm.deepdive.officehours.view;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import edu.cnm.deepdive.officehours.model.entity.User;
import java.net.URI;
import java.util.UUID;
import org.springframework.lang.NonNull;

  @JsonPropertyOrder({"id", "user", "href"})
  public interface FlatStudent {

    @NonNull
    UUID getId();

    @NonNull
    User getUser();

    @NonNull
    URI getHref();

  }


