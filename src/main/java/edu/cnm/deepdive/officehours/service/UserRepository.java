package edu.cnm.deepdive.officehours.service;

import edu.cnm.deepdive.officehours.model.entity.User;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, UUID> {

  Iterable<User> findAllByOrderById();

  default User findOrFail(UUID id) {
    return findById(id).get();
  }
}
