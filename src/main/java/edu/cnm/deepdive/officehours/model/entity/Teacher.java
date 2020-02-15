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
      cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH }
  )
  @JoinColumn(name = "user_ref", referencedColumnName = "user_id")
  @Column(name = "user_ref", nullable = false, updatable = false)
  private User user_id;

  @NonNull
  public UUID getId() {
    return id;
  }

  @NonNull
  public User getUser_id() {
    return user_id;
  }
}
