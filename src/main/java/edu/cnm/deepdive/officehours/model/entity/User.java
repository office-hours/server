package edu.cnm.deepdive.officehours.model.entity;

import java.util.Date;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.lang.NonNull;

@Entity
@Table(
    indexes = {
        @Index(columnList = "created")
    }
)
public class User {

  @NonNull
  @Id
  @GeneratedValue(generator =  "uuid2")
  @GenericGenerator(name = "uuid2", strategy = "uuid2")
  @Column( name = "user_id", columnDefinition = "CHAR(16) FOR BIT DATA",
  nullable = false, updatable = false)
  private UUID id;

  @NonNull
  @Column(length = 4696, nullable = false, unique = true)
  private String oauth;

  @Column(unique = true)
  private String nickname;

  @NonNull
  @Column(nullable = false, unique = true)
  private String email;

  @NonNull
  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  @Column(nullable = false, updatable = false)
  private Date created;

  @OneToOne(mappedBy = "user",
      cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
  private Teacher teacher;

  @OneToOne (mappedBy = "user",
      cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
  private Student student;

  @NonNull
  public UUID getId() {
    return id;
  }

  @NonNull
  public String getOauth() {
    return oauth;
  }

  public void setOauth(@NonNull String oauth) {
    this.oauth = oauth;
  }

  public String getNickname() {
    return nickname;
  }

  public void setNickname(String nickname) {
    this.nickname = nickname;
  }

  @NonNull
  public String getEmail() {
    return email;
  }

  public void setEmail(@NonNull String email) {
    this.email = email;
  }

  @NonNull
  public Date getCreated() {
    return created;
  }

  public Teacher getTeacher() {
    return teacher;
  }

  public Student getStudent() {
    return student;
  }
}
