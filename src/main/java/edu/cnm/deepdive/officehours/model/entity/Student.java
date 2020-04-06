package edu.cnm.deepdive.officehours.model.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import edu.cnm.deepdive.officehours.view.FlatAppointment;
import edu.cnm.deepdive.officehours.view.FlatStudent;
import edu.cnm.deepdive.officehours.view.FlatUser;
import java.net.URI;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import javax.annotation.PostConstruct;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

/**
 * Defines a database entity and its relationships to {@link User} and {@link Appointment}
 * entities.
 */
@Component
@Entity
public class Student implements FlatStudent {

  private static EntityLinks entityLinks;

  @SuppressWarnings("JpaDataSourceORMInspection")
  @NonNull
  @Id
  @GeneratedValue(generator = "uuid2")
  @GenericGenerator(name = "uuid2", strategy = "uuid2")
  @Column(name = "student_id", columnDefinition = "CHAR(16) FOR BIT DATA",
      nullable = false, updatable = false)
  private UUID id;

  @NonNull
  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false, updatable = false)
  @JsonSerialize(as = FlatUser.class)
  private User user;

  @NonNull
  @OneToMany(mappedBy = "student",
      cascade = CascadeType.ALL)
  @JsonSerialize(contentAs = FlatAppointment.class)
  private List<Appointment> appointment = new LinkedList<>();

  @NonNull
  @Column(name = "student_name")
  private String studentName;

  @NonNull
  public String getStudentName() {
    return studentName;
  }

  /**
   * Sets a string student name based on preference.
   */
  public void setStudentName(@NonNull String studentName) {
    this.studentName = studentName;
  }

  /**
   * Returns a {@link List} of {@link Appointment} instances related to {@link Student}.
   */
  @NonNull
  public List<Appointment> getAppointment() {
    return appointment;
  }

  /**
   * Sets a {@link List} of {@link Appointment} instances by said {@link Student} available by
   * {@link Teacher}.
   */
  public void setAppointment(
      @NonNull List<Appointment> appointment) {
    this.appointment = appointment;
  }

  @NonNull
  public UUID getId() {
    return id;
  }

  @NonNull
  public User getUser() {
    return user;
  }

  /**
   * Sets {@link User} to {@link Student}.
   */
  public void setUser(@NonNull User user) {
    this.user = user;
  }


  public URI getHref() {
    return entityLinks.linkForItemResource(Student.class, id).toUri();
  }

  @PostConstruct
  private void init() {
    entityLinks.toString();
  }

  @Autowired
  private void setEntityLinks(EntityLinks entityLinks) {
    Student.entityLinks = entityLinks;
  }
}
