package edu.cnm.deepdive.officehours.model.entity;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.lang.NonNull;

@Entity
public class Teacher {

  @NonNull
  @Id
  @GeneratedValue(generator = "uuid3")
  @GenericGenerator(name = "uuid3", strategy = "uuid3")
  @Column(name = "teacher_id", columnDefinition = "CHAR(16) FOR BIT DATA",
      nullable = false, updatable = false)
  private UUID id;

  @NonNull
  @OneToOne(
      cascade = {CascadeType.DETACH, CascadeType.MERGE,
          CascadeType.PERSIST, CascadeType.REFRESH }
      )
  @JoinColumn(name = "user_id")
  private User user;

  @NonNull
  @OneToMany(mappedBy = "teacher",
      cascade = {
      CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH
  })
  private List<Appointment> appointment = new LinkedList<>();

  @NonNull
  public UUID getId() {
    return id;
  }

  @NonNull
  public User getUser() {
    return user;
  }

  @NonNull
  public List<Appointment> getAppointment() {
    return appointment;
  }

  public void setAppointment(
      @NonNull List<Appointment> appointment) {
    this.appointment = appointment;
  }

  public void setUser(@NonNull User user) {
    this.user = user;
  }
}
