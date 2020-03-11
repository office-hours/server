package edu.cnm.deepdive.officehours.service;

import edu.cnm.deepdive.officehours.model.entity.Appointment;
import edu.cnm.deepdive.officehours.model.entity.Policy;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PolicyRepository extends JpaRepository<Policy, UUID> {

  Iterable<Policy> getAllByOrderByBlockTimeDesc();

  Iterable<Policy> getAllByOrderByStartAvailableDesc();

  List<Policy> findAllByStartAvailableBetween(Date startAvailable, Date endAvailable);

  Object getAllByOrderByCreatedDesc();

  default Policy findOrFail(UUID id) {
    return findById(id).get();
  }

}