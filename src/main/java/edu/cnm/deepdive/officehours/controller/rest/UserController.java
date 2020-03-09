package edu.cnm.deepdive.officehours.controller.rest;

import edu.cnm.deepdive.officehours.model.entity.Appointment;
import edu.cnm.deepdive.officehours.model.entity.Student;
import edu.cnm.deepdive.officehours.model.entity.User;
import edu.cnm.deepdive.officehours.service.UserRepository;
import java.util.Set;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@ExposesResourceFor(User.class)
public class UserController {

 private final UserRepository repository;

 @Autowired
 public UserController(UserRepository repository) {
   this.repository = repository;
 }

 @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
     produces = MediaType.APPLICATION_JSON_VALUE)
 public ResponseEntity<User> post(@RequestBody User user) {
   repository.save(user);
   return ResponseEntity.created(user.getHref()).body(user);
 }

 @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
 public User get(@PathVariable UUID id) {
   return repository.findById(id).get();
 }

// @DeleteMapping(value = "/{id}")
// @ResponseStatus(HttpStatus.NO_CONTENT)
// public void delete(@PathVariable UUID id) {
//   repository.findById(id).ifPresent((user) -> {
//     Set<User> users = user.;
//     user.forEach((User) -> user.getCreated()));
//     user.clear();
//     repository.delete(user);
//   });
// }


 @PutMapping(value = "/{id}",
     consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
 public User put(@PathVariable UUID id, @RequestBody User updated) {
   User user = get(id);
   user.setEmail(updated.getEmail());
   return repository.save(user);
 }

}
