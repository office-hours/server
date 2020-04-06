package edu.cnm.deepdive.officehours.model.repository;

import edu.cnm.deepdive.officehours.model.entity.User;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Database operations that can be performed on {@link User} entity instances.
 */
public interface UserRepository extends JpaRepository<User, UUID> {

  /**
   * Selects and returns all {@link User} instances pertaining to and ordered by {@link User} id.
   *
   * @return {@link Iterable} sequence of {@link User} instances.
   */
  Iterable<User> findAllByOrderById();

  Iterable<User> findAllByOrderByNickname();

  Optional<User> findFirstByOauth(String oauth);

  /**
   * Defaults to finding a {@link User} id or failing.
   *
   * @param id for {@link User}.
   * @return a {@link User} id.
   */
  default User findOrFail(UUID id) {
    return findById(id).get();
  }
}
