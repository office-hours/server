package edu.cnm.deepdive.officehours.view;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.Date;
import java.util.UUID;
import org.springframework.lang.NonNull;

@JsonPropertyOrder({"id", "created", "updated", "starAvailable", "endAvailable", "blockTime"})
public interface FlatPolicy {
  /**
   * returns the Id (UUID) of a policy resource.
   */
  @NonNull
  UUID getId();

  /**
   * Returns the date-time stamp recorded when a policy resource is first written to the database.
   */
  @NonNull
  Date getCreated();

  /**
   * Returns the date-time stamp recorded when a policy resource is updated in the database.
   */

  @NonNull
  Date getUpdated();
  /**
   * returns an available start time policy for teacher in appointment.
   */

  @NonNull
  Date getStartAvailable();

  /**
   * returns an available end time policy for teacher in appointment.
   */
  @NonNull
  Date getEndAvailable();


  /**
   * returns an available block time policy for teacher in appointment.
   */
  @NonNull
  Date getBlockTime();
}
