package edu.cnm.deepdive.officehours.model.entity;

import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.lang.NonNull;

@Entity
public class Student {

  @NonNull
  @Id
  @GeneratedValue(generator = "uuid2")
  @GenericGenerator(name = "uuid2", strategy = "uuid2")
  @Column(name = "student_id", columnDefinition = "CHAR(16) FOR BIT DATA",
      nullable = false, updatable = false)
  private UUID id;

  @NonNull
  @OneToOne(
      cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH }
      )
  @JoinColumn(name = "user_id")
  private User user_id;

  @NonNull
  @OneToOne (mappedBy = "student",
      cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
  private Appointment appointment;

  @NonNull
  public Appointment getAppointment() {
    return appointment;
  }

  public void setAppointment(@NonNull Appointment appointment) {
    this.appointment = appointment;
  }

  @NonNull
  public UUID getId() {
    return id;
  }

  @NonNull
  public User getUser_id() {
    return user_id;
  }
}
