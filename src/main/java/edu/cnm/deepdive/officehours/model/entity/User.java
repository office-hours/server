package edu.cnm.deepdive.officehours.model.entity;

import edu.cnm.deepdive.officehours.view.FlatUser;
import java.net.URI;
import java.util.Date;
import java.util.UUID;
import javax.annotation.PostConstruct;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
@Entity
@Table(
    indexes = {
        @Index(columnList = "created")
    }
)
public class User implements FlatUser {

  private static EntityLinks entityLinks;


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

  public URI getHref() {
    return entityLinks.linkForItemResource(Teacher.class, id).toUri();
  }

  @PostConstruct
  private void init() {
    entityLinks.toString();
  }

  @Autowired
  private void setEntityLinks(EntityLinks entityLinks) {
    User.entityLinks = entityLinks;
  }
}
