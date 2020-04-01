package edu.cnm.deepdive.officehours.model.repository;

import edu.cnm.deepdive.officehours.model.entity.Policy;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Database operations that can be performed on {@link Policy} entity instances.
 */
public interface PolicyRepository extends JpaRepository<Policy, UUID> {

  /**
   * Selects and returns all {@link Policy} block-time instances sorted in descending order.
   *
   * @return {@link Iterable} sequence of {@link Policy} instances.
   */
  Iterable<Policy> getAllByOrderByBlockTimeDesc();

  /**
   * Selects and returns all {@link Policy} available start time instances in descending order.
   *
   * @return {@link Iterable} sequence of {@link Policy} instances.
   */
  Iterable<Policy> getAllByOrderByStartAvailableDesc();

  /**
   * Selects and returns a {@link List} if all {@link Policy} consisting of available start times
   * between available start and end times.
   *
   * @param startAvailable time instances.
   * @param endAvailable   time instances.
   * @return a {@link List} sequence of {@link Policy} instances.
   */
  List<Policy> findAllByStartAvailableBetween(Date startAvailable, Date endAvailable);

  /**
   * Returns an {@link Object} of created {@link Policy} in descending order.
   *
   * @return {@link Object} sequence of {@link Policy} instances.
   */
  Object getAllByOrderByCreatedDesc();

  /**
   * Defaults to finding a {@link Policy} id or failing.
   *
   * @param id for {@link Policy}.
   * @return a {@link Policy} id.
   */
  default Policy findOrFail(UUID id) {
    return findById(id).get();
  }

}