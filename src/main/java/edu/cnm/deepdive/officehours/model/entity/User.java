package edu.cnm.deepdive.officehours.model.entity;

import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.lang.NonNull;

@Entity
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

  private String nickname;

  @NonNull
  @Column(nullable = false, unique = true)
  private String email;

  @NonNull
  public UUID getId() {
    return id;
  }

  @NonNull
  public String getOauth() {
    return oauth;
  }

  public String getNickname() {
    return nickname;
  }

  @NonNull
  public String getEmail() {
    return email;
  }

  public void setOauth(@NonNull String oauth) {
    this.oauth = oauth;
  }

  public void setNickname(String nickname) {
    this.nickname = nickname;
  }

  public void setEmail(@NonNull String email) {
    this.email = email;
  }
}
