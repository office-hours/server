package edu.cnm.deepdive.officehours.model.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import edu.cnm.deepdive.officehours.view.FlatStudent;
import edu.cnm.deepdive.officehours.view.FlatTeacher;
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

/**
 * Defines a database entity and its relationships to {@link Teacher} and {@link Student} entities.
 */
@Component
@Entity
@Table(
    name = "base_user",
    indexes = {
        @Index(columnList = "created")
    }
)
@JsonInclude(Include.NON_NULL)
public class User {

  private static EntityLinks entityLinks;


  @SuppressWarnings("JpaDataSourceORMInspection")
  @NonNull
  @Id
  @GeneratedValue(generator = "uuid2")
  @GenericGenerator(name = "uuid2", strategy = "uuid2")
  @Column(name = "user_id", columnDefinition = "CHAR(16) FOR BIT DATA",
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
  @JsonSerialize(as = FlatTeacher.class)
  private Teacher teacher;

  @OneToOne(mappedBy = "user",
      cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
  @JsonSerialize(as = FlatStudent.class)
  private Student student;

  /**
   * Returns the UUID for the user instance.
   */
  @NonNull
  public UUID getId() {
    return id;
  }

  /**
   * Returns the oauth token for the user instance.
   */
  @NonNull
  public String getOauth() {
    return oauth;
  }

  /**
   * Sets the oauth token for the user instance.
   */
  public void setOauth(@NonNull String oauth) {
    this.oauth = oauth;
  }

  /**
   * Returns the nickname for the user.
   */
  public String getNickname() {
    return nickname;
  }

  /**
   * Sets the nickname for the user.
   */
  public void setNickname(String nickname) {
    this.nickname = nickname;
  }

  /**
   * Returns the email for the user instance.
   */
  @NonNull
  public String getEmail() {
    return email;
  }

  /**
   * Sets the email for the user instance.
   */
  public void setEmail(@NonNull String email) {
    this.email = email;
  }

  /**
   * Returns the timestamp when the user was created.
   */
  @NonNull
  public Date getCreated() {
    return created;
  }

  /**
   * Returns the {@link Teacher} instance related to the user.
   */
  public Teacher getTeacher() {
    return teacher;
  }

  public void setTeacher(Teacher teacher) {
    this.teacher = teacher;
  }

  /**
   * Returns the {@link User} instance related to the user.
   */
  public Student getStudent() {
    return student;
  }

  public void setStudent(Student student) {
    this.student = student;
  }

  /**
   * Returns the URL referencing the user.
   */
  public URI getHref() {
    return entityLinks.linkForItemResource(User.class, id).toUri();
  }

  @PostConstruct
  private void init() {
    entityLinks.toString();
  }

  @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
  @Autowired
  private void setEntityLinks(EntityLinks entityLinks) {
    User.entityLinks = entityLinks;
  }
}
