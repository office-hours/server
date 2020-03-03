package edu.cnm.deepdive.officehours.view;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.net.URI;
import java.util.Date;
import java.util.UUID;
import org.springframework.lang.NonNull;

public interface FlatUser {

  @JsonPropertyOrder({"id", "created", "updated", "text", "href"})
  public interface FlatQuote {

    @NonNull
    UUID getId();

    @NonNull
    Date getCreated();

    @NonNull
    Date getUpdated();

    @NonNull
    String getText();

    @NonNull
    URI getHref();
  }
}
