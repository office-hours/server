package edu.cnm.deepdive.officehours.view;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.Date;
import java.util.UUID;
import org.springframework.lang.NonNull;

@JsonPropertyOrder({"id", "created", "updated", "starAvailable", "endAvailable", "blockTime"})
public interface FlatPolicy {
  @NonNull
  UUID getId();

  @NonNull
  Date getCreated();

  @NonNull
  Date getUpdated();

  @NonNull
  Date getStartAvailable();

  @NonNull
  Date getEndAvailable();

  @NonNull
  Date getBlockTime();
}
