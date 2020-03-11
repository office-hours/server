package edu.cnm.deepdive.officehours.model.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import edu.cnm.deepdive.officehours.view.FlatAppointment;
import edu.cnm.deepdive.officehours.view.FlatStudent;
import edu.cnm.deepdive.officehours.view.FlatTeacher;
import java.net.URI;
import java.util.Date;
import java.util.UUID;
import javax.annotation.PostConstruct;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Component
@Entity
@Table(
    indexes = {
        @Index(columnList = "status"),
        @Index(columnList = "start_time"),
        @Index(columnList = "end_time"),
        @Index(columnList = "created")
    }
)
public class Appointment implements FlatAppointment {

  private static EntityLinks entityLinks;


  @NonNull
  @Id
  @GeneratedValue(generator = "uuid2")
  @GenericGenerator(name = "uuid2", strategy = "uuid2")
  @Column(name = "appointment_id", columnDefinition = "CHAR(16) FOR BIT DATA",
      nullable = false, updatable = false)
  private UUID id;

  @NonNull
  @ManyToOne(
      cascade = {
          CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH
      })
  @JoinColumn(name = "student_id")
  @JsonSerialize(as = FlatStudent.class)
  private Student student;

  @NonNull
  @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE,
      CascadeType.PERSIST, CascadeType.REFRESH})
  @JoinColumn(name = "teacher_id")
  @JsonSerialize(as = FlatTeacher.class)
  private Teacher teacher;

  @NonNull
  @Enumerated(EnumType.ORDINAL)
  @Column(nullable = false)
  private Status status;

  @Enumerated(EnumType.ORDINAL)
  private Subject subject;

  @NonNull
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "start_time", nullable = false)
  private Date startTime;

  @NonNull
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "end_time", nullable = false)
  private Date endTime;

  @NonNull
  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  @Column(nullable = false, updatable = false)
  private Date created;

  @NonNull
  @UpdateTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  @Column(nullable = false)
  private Date updated;

  public void setStudent(@NonNull Student student) {
    this.student = student;
  }

  public void setTeacher(@NonNull Teacher teacher) {
    this.teacher = teacher;
  }

  @NonNull
  public UUID getId() {
    return id;
  }

  @NonNull
  public Status getStatus() {
    return status;
  }

  public void setStatus(@NonNull Status status) {
    this.status = status;
  }

  public Subject getSubject() {
    return subject;
  }

  public void setSubject(Subject subject) {
    this.subject = subject;
  }

  @NonNull
  public Date getCreated() {
    return created;
  }

  @NonNull
  public Date getUpdated() {
    return updated;
  }

  @NonNull
  public Student getStudent() {
    return student;
  }

  @NonNull
  public Teacher getTeacher() {
    return teacher;
  }

  @NonNull
  public Date getStartTime() {
    return startTime;
  }

  public void setStartTime(@NonNull Date startTime) {
    this.startTime = startTime;
  }

  @NonNull
  public Date getEndTime() {
    return endTime;
  }

  public void setEndTime(@NonNull Date endTime) {
    this.endTime = endTime;
  }

  public URI getHref() {
    return entityLinks.linkForItemResource(Appointment.class, id).toUri();
  }
  @PostConstruct
  private void init() {
    entityLinks.toString();
  }
  @Autowired
  private void setEntityLinks(EntityLinks entityLinks) {
    Appointment.entityLinks = entityLinks;
  }

  public enum Status {
    NO_SHOW("N"),
    PENDING("P"),
    LATE("L"),
    CANCELLED("X"),
    ARRIVED("K");

    private String code;


    private Status(String code) {
      this.code = code;
    }

    public String getCode() {
      return code;
    }
  }

  public enum Subject {
    ANDROID_PROJECT("A"),
    CAPSTONE_PROJECT("C"),
    TUTORING("T"),
    GRADES("G"),
    OTHER("O");

    private String code;

    private Subject(String code) {
      this.code = code;
    }

    public String getCode() {
      return code;
    }
  }


}
