package edu.cnm.deepdive.officehours.model.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import edu.cnm.deepdive.officehours.view.FlatAppointment;
import edu.cnm.deepdive.officehours.view.FlatPolicy;
import edu.cnm.deepdive.officehours.view.FlatTeacher;
import edu.cnm.deepdive.officehours.view.FlatUser;
import java.net.URI;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
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
 * Defines a database entity and REST resource representing the a teacher, and its
 * relationships to zero or more {@link Appointment} resources.
 */
@SuppressWarnings("JpaDataSourceORMInspection")
@Component
@Entity
public class Teacher implements FlatTeacher {

  private static EntityLinks entityLinks;


  @NonNull
  @Id
  @GeneratedValue(generator = "uuid2")
  @GenericGenerator(name = "uuid2", strategy = "uuid2")
  @Column(name = "teacher_id", columnDefinition = "CHAR(16) FOR BIT DATA",
      nullable = false, updatable = false)
  private UUID id;

  @NonNull
  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false, updatable = false)
  @JsonSerialize(as = FlatUser.class)
  private User user;

  @NonNull
  @OneToMany(mappedBy = "teacher",
      cascade =
          CascadeType.ALL)
  @JsonSerialize(contentAs = FlatAppointment.class)
  private List<Appointment> appointment = new LinkedList<>();

  @NonNull
  @OneToMany(mappedBy = "teacher",
      cascade =
       CascadeType.ALL)
  @JsonSerialize(contentAs = FlatPolicy.class)
  private List<Policy> policy = new LinkedList<>();

  @NonNull
  @Column(name = "teacher_name", unique = true)
  private String teacherName;

  /**
   * returns a {@linklist} of policies for one or many {@link Appointment}.
   * @return
   */
  @NonNull
  public List<Policy> getPolicy() {
    return policy;
  }

  /**
   * Allows a {@link Teacher} to set up an {@link Appointment} with restrictions.
   *
   * @param policy the restriction of an {@link Appointment}.
   */
  public void setPolicy(@NonNull List<Policy> policy) {
    this.policy = policy;
  }

  @Override
  public UUID getId() {
    return id;
  }

  @Override
  public String getTeacherName() {
    return teacherName;
  }

  /**
   * Sets a {@link Teacher} name for an {@link Appointment}.
   * @param teacherName
   */
  public void setTeacherName(@NonNull String teacherName) {
    this.teacherName = teacherName;
  }

  @Override
  public URI getHref() {
    return entityLinks.linkForItemResource(Teacher.class, id).toUri();
  }

  /**
   * returns a {@link User} which is connected to a {@link Teacher} resource.
   * @return User
   */
  @NonNull
  public User getUser() {
    return user;
  }

  /**
   * Sets the context of the {@link User} for a {@link Teacher}.
   * @param user actual user
   */
  public void setUser(@NonNull User user) {
    this.user = user;
  }

  /**
   * returns a {@link List} of {@link Appointment}.
   * @return Appointments made by {@link Teacher}
   */
  @NonNull
  public List<Appointment> getAppointment() {
    return appointment;
  }

  /**
   * Sets a {@link List} of appointments.
   * @param appointment appointment made by {@link Teacher}
   */
  public void setAppointment(
      @NonNull List<Appointment> appointment) {
    this.appointment = appointment;
  }
  /**
   * Computes and returns a hash value computed from {@link #getTeacherName()}.
   *
   *
   * @return hash value.
   */
  @Override
  public int hashCode() {
    return Objects.hash(id, teacherName);
  }

  @Override
  public boolean equals(Object obj) {
    boolean result = false;
    if (obj == this) {
      result = true;
    } else if (obj instanceof Teacher && obj.hashCode() == hashCode()) {
      Teacher other = (Teacher) obj;
      result = id.equals(other.id) && teacherName.equals(other.teacherName);
    }
    return result;
  }

  @PostConstruct
  private void init() {
    entityLinks.toString();
  }

  @Autowired
  private void setEntityLinks(EntityLinks entityLinks) {
    Teacher.entityLinks = entityLinks;
  }


}

