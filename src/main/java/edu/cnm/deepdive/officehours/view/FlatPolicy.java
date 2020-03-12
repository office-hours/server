package edu.cnm.deepdive.officehours.view;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.Date;
import java.util.UUID;
import org.springframework.lang.NonNull;

@JsonPropertyOrder({"id", "created", "updated", "starAvailable", "endAvailable", "blockTime"})
public interface FlatPolicy {
  /**
   * returns the Id (UUID) of a policy resource.
   *
   * @return policy UUID
   */
  @NonNull
  UUID getId();

  /**
   * Returns the date-time stamp recorded when a policy resource is first written to the database.
   *
   * @return creation timestamp
   */
  @NonNull
  Date getCreated();

  /**
   * Returns the date-time stamp recorded when a policy resource is updated in the database.
   *
   * @return updated timestamp
   */

  @NonNull
  Date getUpdated();
  /**
   * returns an available start time policy for teacher in appointment.
   *
   * @return policy available start date
   */

  @NonNull
  Date getStartAvailable();

  /**
   * returns an available end time policy for teacher in appointment.
   *
   * @return policy available end date.
   */
  @NonNull
  Date getEndAvailable();


  /**
   * returns an available block time policy for teacher in appointment.
   *
   * @return policy available block-time.
   */
  @NonNull
  Date getBlockTime();
}
