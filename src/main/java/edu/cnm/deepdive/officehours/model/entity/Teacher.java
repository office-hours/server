package edu.cnm.deepdive.officehours.model.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import edu.cnm.deepdive.officehours.view.FlatAppointment;
import edu.cnm.deepdive.officehours.view.FlatPolicy;
import edu.cnm.deepdive.officehours.view.FlatTeacher;
import java.net.URI;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import javax.annotation.PostConstruct;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
  @OneToOne(
      cascade = {CascadeType.DETACH, CascadeType.MERGE,
          CascadeType.PERSIST, CascadeType.REFRESH}
  )
  @JoinColumn(name = "user_id")
  private User user;

  @NonNull
  @OneToMany(mappedBy = "teacher",
      cascade = {
          CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH
      })
  @JsonSerialize(contentAs = FlatAppointment.class)
  private List<Appointment> appointment = new LinkedList<>();

  @NonNull
  @OneToMany(mappedBy = "teacher",
      cascade = {
          CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH
      })
  @JsonSerialize(contentAs = FlatPolicy.class)
  private List<Policy> policy = new LinkedList<>();

  @NonNull
  @Column(name = "teacher_name", unique = true)
  private String teacherName;


  @NonNull
  public List<Policy> getPolicy() {
    return policy;
  }

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

  public void setTeacherName(@NonNull String teacherName) {
    this.teacherName = teacherName;
  }

  @Override
  public URI getHref() {
    return entityLinks.linkForItemResource(Teacher.class, id).toUri();
  }

  @NonNull
  public User getUser() {
    return user;
  }

  public void setUser(@NonNull User user) {
    this.user = user;
  }

  @NonNull
  public List<Appointment> getAppointment() {
    return appointment;
  }

  public void setAppointment(
      @NonNull List<Appointment> appointment) {
    this.appointment = appointment;
  }

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

